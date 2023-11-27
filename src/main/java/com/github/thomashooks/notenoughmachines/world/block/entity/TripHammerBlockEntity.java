package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.world.block.entity.base.MechanicalBlockEntity;
import com.github.thomashooks.notenoughmachines.world.inventory.TripHammerMenu;
import com.github.thomashooks.notenoughmachines.world.item.crafting.AbstractMachineRecipe;
import com.github.thomashooks.notenoughmachines.world.item.crafting.MillingRecipe;
import com.github.thomashooks.notenoughmachines.world.item.crafting.StampingRecipe;
import com.github.thomashooks.notenoughmachines.world.power.MechanicalType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class TripHammerBlockEntity extends MechanicalBlockEntity implements MenuProvider
{
    protected ItemStackHandler inputItemHandler;
    protected ItemStackHandler outputItemHandler;
    protected LazyOptional<ItemStackHandler> lazyInputItemHandler = LazyOptional.of(() -> inputItemHandler);
    protected LazyOptional<ItemStackHandler> lazyOutputItemHandler = LazyOptional.of(() -> outputItemHandler);
    protected LazyOptional<CombinedInvWrapper> combinedLazyItemHandler = LazyOptional.of(() -> new CombinedInvWrapper(inputItemHandler, outputItemHandler));
    public static final int NUMBER_OF_INPUT_SLOTS = 1;
    public static final int NUMBER_OF_OUTPUT_SLOTS = 2;
    public static final int NUMBER_OF_TOTAL_SLOTS = NUMBER_OF_INPUT_SLOTS + NUMBER_OF_OUTPUT_SLOTS;
    private int processTimer = 0;
    public static final int MAX_PROCESS_TIME = 400;
    protected int maxProcessTime = MAX_PROCESS_TIME;
    protected double displacement = 0.0D;
    protected boolean hammerHasHit = false;
    protected final ContainerData containerData;

    public TripHammerBlockEntity(BlockPos pos, BlockState state)
    {
        super(AllBlockEntities.TRIP_HAMMER.get(), pos, state, 0, 60, MechanicalType.SINK);
        this.inputItemHandler = makeItemHandler(NUMBER_OF_INPUT_SLOTS, 64);
        this.outputItemHandler = makeItemHandler(NUMBER_OF_OUTPUT_SLOTS, 64);

        this.containerData = new ContainerData()
        {
            @Override
            public int get(int index)
            {
                return switch (index)
                {
                    case 0 -> TripHammerBlockEntity.this.processTimer;
                    case 1 -> TripHammerBlockEntity.this.maxProcessTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value)
            {
                switch (index)
                {
                    case 0 -> TripHammerBlockEntity.this.processTimer = value;
                    case 1 -> TripHammerBlockEntity.this.maxProcessTime = value;
                };
            }

            @Override
            public int getCount() { return 2; }
        };
    }

    @Override
    public void tick()
    {
        if (!getLevel().isClientSide())
        {
            if (isProcessing())
            {
                this.processTimer++;
                moveHammer();
                syncClient();
            }
            else
            {
                if (--this.processTimer < 0)
                    this.processTimer = 0;
            }

            Optional<StampingRecipe> recipe = getRecipe();
            if (this.processTimer > this.maxProcessTime && recipe.isPresent())
            {
                processItem(recipe);
                this.processTimer = 0;
            }
        }
        super.tick();
    }

    protected Optional<StampingRecipe> getRecipe()
    {
        SimpleContainer container = new SimpleContainer(this.inputItemHandler.getSlots());
        container.setItem(0, this.inputItemHandler.getStackInSlot(0));
        return this.getLevel().getRecipeManager().getRecipeFor(StampingRecipe.Type.STAMPING, container, this.getLevel());
    }

    protected boolean canProcess(Optional<StampingRecipe> recipe)
    {
        if (recipe.isEmpty() && this.inputItemHandler.getStackInSlot(0).isEmpty())
            return false;

        this.maxProcessTime = recipe.map(AbstractMachineRecipe::getProcessingTime).orElse(MAX_PROCESS_TIME);
        ItemStack primaryResult = recipe.get().getResultItem(null);
        ItemStack secondaryResult = recipe.get().getSecondaryResultItem();
        ItemStack outputTest = this.outputItemHandler.insertItem(0, primaryResult, true);
        ItemStack outputTest2 = this.outputItemHandler.insertItem(1, secondaryResult, true);
        return outputTest.isEmpty() && outputTest2.isEmpty();
    }

    protected void processItem(Optional<StampingRecipe> recipe)
    {
        if (recipe.isEmpty())
            return;

        if (canProcess(recipe))
        {
            ItemStack primaryResult = recipe.get().getResultItem(null);
            ItemStack secondaryResult = recipe.get().getSecondaryResultItem();
            this.outputItemHandler.insertItem(0, primaryResult, false);
            this.outputItemHandler.insertItem(1, secondaryResult, false);
            this.inputItemHandler.extractItem(0, 1, false);
            setChanged();
        }
    }

    public boolean isProcessing()
    {
        Optional<StampingRecipe> recipe = getRecipe();
        if (recipe.isEmpty())
            return false;
        return canProcess(recipe) && isPowered();
    }

    protected void moveHammer()
    {
        double angle = getSpeed() != 0 ? (getLevel().getGameTime() * 30.0F * 0.3F) % 360 : 0;
        this.displacement = 0.4375D * Math.sin(Math.toRadians(angle));

        if (this.displacement < 0.05D)
            this.displacement = 0.0D;

        if (!this.hammerHasHit && this.displacement <= 0.1D)
        {
            //getLevel().playLocalSound(getBlockPos(), SoundEvents.ANVIL_LAND, SoundSource.BLOCKS, 0.5F, 1.25F, false);
            getLevel().playSound(null, getBlockPos(), SoundEvents.ANVIL_LAND, SoundSource.BLOCKS, 0.5F, 1.25F);
            this.hammerHasHit = true;
        }
        else if (this.hammerHasHit && this.displacement > 0.1D)
            this.hammerHasHit = false;
    }

    @Override
    public void onLoad()
    {
        super.onLoad();
        this.lazyInputItemHandler = LazyOptional.of(()-> this.inputItemHandler);
        this.lazyOutputItemHandler = LazyOptional.of(()-> this.outputItemHandler);
        this.combinedLazyItemHandler = LazyOptional.of(() -> new CombinedInvWrapper(this.inputItemHandler, this.outputItemHandler));
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            if (side == null)
                return this.combinedLazyItemHandler.cast();
            else if (side == Direction.DOWN)
                return this.lazyOutputItemHandler.cast();
            else
                return this.lazyInputItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps()
    {
        this.lazyInputItemHandler.invalidate();
        this.lazyOutputItemHandler.invalidate();
        this.combinedLazyItemHandler.invalidate();
        super.invalidateCaps();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public AABB getRenderBoundingBox()
    {
        return new AABB(getBlockPos()).inflate(1.0D, 4.0D, 1.0D);
    }

    @Override
    public void load(CompoundTag nbt)
    {
        super.load(nbt);
        this.inputItemHandler.deserializeNBT(nbt.getCompound("input_inventory"));
        this.outputItemHandler.deserializeNBT(nbt.getCompound("output_inventory"));
        this.processTimer = nbt.getInt("process_timer");
        this.displacement = nbt.getDouble("displacement");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt)
    {
        super.saveAdditional(nbt);
        nbt.put("input_inventory", this.inputItemHandler.serializeNBT());
        nbt.put("output_inventory", this.outputItemHandler.serializeNBT());
        nbt.putInt("process_timer", this.processTimer);
        nbt.putDouble("displacement", this.displacement);
    }

    public double getDisplacement() { return this.displacement; }

    public ItemStack getInputItem() { return this.inputItemHandler.getStackInSlot(0).copy(); }

    @Override
    public Component getDisplayName() { return TripHammerMenu.TITLE; }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) { return new TripHammerMenu(containerId, playerInventory, this, this.containerData); }
}
