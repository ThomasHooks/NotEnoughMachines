package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.world.block.MillstoneBlock;
import com.github.thomashooks.notenoughmachines.world.block.entity.base.MechanicalBlockEntity;
import com.github.thomashooks.notenoughmachines.world.inventory.MillstoneMenu;
import com.github.thomashooks.notenoughmachines.world.item.crafting.AbstractMachineRecipe;
import com.github.thomashooks.notenoughmachines.world.item.crafting.MillingRecipe;
import com.github.thomashooks.notenoughmachines.world.power.MechanicalType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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

public class MillstoneBlockEntity extends MechanicalBlockEntity implements MenuProvider
{
    protected ItemStackHandler itemInput;
    private LazyOptional<ItemStackHandler> itemInputHandler = LazyOptional.of(() -> itemInput);
    private static final int INPUTSLOTS = 1;
    protected ItemStackHandler itemOutput;
    private LazyOptional<ItemStackHandler> itemOutputHandler = LazyOptional.of(() -> itemOutput);
    private static final int OUTPUTSLOTS = 1;
    private LazyOptional<CombinedInvWrapper> combinedItemHandler = LazyOptional.of(() -> new CombinedInvWrapper(itemInput, itemOutput));
    private static final int NUMBER_OF_SLOTS = INPUTSLOTS + OUTPUTSLOTS;
    private int processTimer = 0;
    public static final int MAX_PROCESS_TIME = 400;
    protected int maxProcessTime = MAX_PROCESS_TIME;
    protected final ContainerData containerData;

    public MillstoneBlockEntity(BlockPos pos, BlockState state)
    {
        super(AllBlockEntities.MILLSTONE.get(), pos, state, 0, 20, MechanicalType.SINK);
        this.itemInput = makeItemHandler(INPUTSLOTS, 64);
        this.itemOutput = makeItemHandler(OUTPUTSLOTS, 64);
        this.containerData = new ContainerData()
        {
            @Override
            public int get(int index)
            {
                return switch (index)
                {
                    case 0 -> MillstoneBlockEntity.this.processTimer;
                    case 1 -> MillstoneBlockEntity.this.maxProcessTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value)
            {
                switch (index)
                {
                    case 0 -> MillstoneBlockEntity.this.processTimer = value;
                    case 1 -> MillstoneBlockEntity.this.maxProcessTime = value;
                };
            }

            @Override
            public int getCount() { return 2; }
        };
    }

    @Override
    public void tick()
    {
        if (!this.getLevel().isClientSide())
        {
            this.processTimer = isProcessing() ? ++this.processTimer : --this.processTimer;
            if (this.processTimer < 0)
                this.processTimer = 0;

            Optional<MillingRecipe> recipe = getRecipe();
            if (this.processTimer > this.maxProcessTime && recipe.isPresent())
            {
                processItem(recipe);
                this.processTimer = 0;
            }
        }
        super.tick();
    }

    @Override
    public void lazyTick()
    {
        if (!this.getLevel().isClientSide())
            this.getLevel().setBlock(this.getBlockPos(), this.getBlockState().setValue(MillstoneBlock.POWERED, isProcessing()), 1 | 2| 4);
        super.lazyTick();
    }

    protected Optional<MillingRecipe> getRecipe()
    {
        SimpleContainer container = new SimpleContainer(this.itemInput.getSlots());
        container.setItem(0, this.itemInput.getStackInSlot(0));
        return this.getLevel().getRecipeManager().getRecipeFor(MillingRecipe.Type.MILLING, container, this.getLevel());
    }

    protected boolean canProcess(Optional<MillingRecipe> recipe)
    {
        if (recipe.isEmpty() && this.itemInput.getStackInSlot(0).isEmpty())
            return false;
        this.maxProcessTime = recipe.map(AbstractMachineRecipe::getProcessingTime).orElse(MAX_PROCESS_TIME);
        ItemStack result = recipe.get().getResultItem(null);
        ItemStack outputTest = this.itemOutput.insertItem(0, result, true);
        return outputTest.isEmpty();
    }

    protected void processItem(Optional<MillingRecipe> recipe)
    {
        if (recipe.isEmpty())
            return;
        ItemStack result = recipe.get().getResultItem(null);
        if (canProcess(recipe))
        {
            this.itemOutput.insertItem(0, result, false);
            this.itemInput.extractItem(0, 1, false);
            setChanged();
        }
    }

    public boolean isProcessing()
    {
        Optional<MillingRecipe> recipe = getRecipe();
        if (recipe.isEmpty())
            return false;
        return canProcess(recipe) && isPowered();
    }

    @Override
    public void onLoad()
    {
        super.onLoad();
        itemInputHandler = LazyOptional.of(()-> itemInput);
        itemOutputHandler = LazyOptional.of(()-> itemOutput);
        combinedItemHandler = LazyOptional.of(() -> new CombinedInvWrapper(itemInput, itemOutput));
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            if (side == null)
                return this.combinedItemHandler.cast();
            else if (side == Direction.UP || side == Direction.DOWN)
                return this.itemOutputHandler.cast();
            else
                return this.itemInputHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps()
    {
        this.itemInputHandler.invalidate();
        this.itemOutputHandler.invalidate();
        this.combinedItemHandler.invalidate();
        super.invalidateCaps();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public AABB getRenderBoundingBox()
    {
        return new AABB(getBlockPos()).inflate(1.0D);
    }

    @Override
    public void load(CompoundTag nbt)
    {
        super.load(nbt);
        this.itemInput.deserializeNBT(nbt.getCompound("input_inventory"));
        this.itemOutput.deserializeNBT(nbt.getCompound("output_inventory"));
        this.processTimer = nbt.getInt("process_timer");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt)
    {
        super.saveAdditional(nbt);
        nbt.put("input_inventory", this.itemInput.serializeNBT());
        nbt.put("output_inventory", this.itemOutput.serializeNBT());
        nbt.putInt("process_timer", this.processTimer);
    }

    @Override
    public Component getDisplayName() { return MillstoneMenu.TITLE; }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) { return new MillstoneMenu(containerId, playerInventory, this, this.containerData); }
}
