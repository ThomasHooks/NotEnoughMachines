package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.world.block.CokeOvenBlock;
import com.github.thomashooks.notenoughmachines.world.inventory.CokeOvenMenu;
import com.github.thomashooks.notenoughmachines.world.item.crafting.AbstractMachineRecipe;
import com.github.thomashooks.notenoughmachines.world.item.crafting.CokingRecipe;
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
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CokeOvenBlockEntity extends LazyTickingBlockEntity implements MenuProvider
{
    protected ItemStackHandler inputItemHandler;
    protected ItemStackHandler outputItemHandler;
    private LazyOptional<ItemStackHandler> lazyInputItemHandler = LazyOptional.of(() -> inputItemHandler);
    private LazyOptional<ItemStackHandler> lazyOutputItemHandler = LazyOptional.of(() -> outputItemHandler);
    private LazyOptional<CombinedInvWrapper> combinedLazyItemHandler = LazyOptional.of(() -> new CombinedInvWrapper(inputItemHandler, outputItemHandler));
    private static final int INPUT_SLOTS = 1;
    private static final int OUTPUT_SLOTS = 1;
    private static final int NUMBER_OF_SLOTS = INPUT_SLOTS + OUTPUT_SLOTS;
    private int processTimer = 0;
    public static final int MAX_PROCESS_TIME = 2400;
    protected int maxProcessTime = MAX_PROCESS_TIME;
    protected final ContainerData containerData;

    public CokeOvenBlockEntity(BlockPos pos, BlockState state)
    {
        super(AllBlockEntities.COKE_OVEN.get(), pos, state);
        this.inputItemHandler = makeItemHandler(INPUT_SLOTS, 64);
        this.outputItemHandler = makeItemHandler(OUTPUT_SLOTS, 64);
        this.containerData = new ContainerData()
        {
            @Override
            public int get(int index)
            {
                return switch (index)
                {
                    case 0 -> CokeOvenBlockEntity.this.processTimer;
                    case 1 -> CokeOvenBlockEntity.this.maxProcessTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value)
            {
                switch (index)
                {
                    case 0 -> CokeOvenBlockEntity.this.processTimer = value;
                    case 1 -> CokeOvenBlockEntity.this.maxProcessTime = value;
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
            this.processTimer = isProcessing() ? ++this.processTimer : 0;

            Optional<CokingRecipe> recipe = getRecipe();
            if (this.processTimer > this.maxProcessTime && recipe.isPresent())
            {
                processItem(recipe);
                this.processTimer = 0;
            }
        }
        super.tick();
    }

    @Override
    protected void lazyTick()
    {
        if (!this.getLevel().isClientSide())
            this.getLevel().setBlock(this.getBlockPos(), this.getBlockState().setValue(CokeOvenBlock.LIT, isProcessing()), 1 | 2 | 4);
        super.lazyTick();
    }

    protected Optional<CokingRecipe> getRecipe()
    {
        SimpleContainer container = new SimpleContainer(this.inputItemHandler.getSlots());
        container.setItem(0, this.inputItemHandler.getStackInSlot(0));
        return this.getLevel().getRecipeManager().getRecipeFor(CokingRecipe.Type.COKING, container, this.getLevel());
    }

    protected boolean canProcess(Optional<CokingRecipe> recipe)
    {
        if (recipe.isEmpty() && this.inputItemHandler.getStackInSlot(0).isEmpty())
            return false;

        this.maxProcessTime = recipe.map(AbstractMachineRecipe::getProcessingTime).orElse(MAX_PROCESS_TIME);
        ItemStack primaryResult = recipe.get().getResultItem(null);
        ItemStack outputTest = this.outputItemHandler.insertItem(0, primaryResult, true);
        return outputTest.isEmpty();
    }

    protected void processItem(Optional<CokingRecipe> recipe)
    {
        if (recipe.isEmpty())
            return;

        if (canProcess(recipe))
        {
            ItemStack primaryResult = recipe.get().getResultItem(null);
            this.outputItemHandler.insertItem(0, primaryResult, false);
            this.inputItemHandler.extractItem(0, 1, false);
            setChanged();
        }
    }

    public boolean isProcessing()
    {
        Optional<CokingRecipe> recipe = getRecipe();
        if (recipe.isEmpty())
            return false;
        return canProcess(recipe);
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
    public AABB getRenderBoundingBox() { return new AABB(getBlockPos()).inflate(1.0D); }

    @Override
    public void load(CompoundTag nbt)
    {
        super.load(nbt);
        this.inputItemHandler.deserializeNBT(nbt.getCompound("input_inventory"));
        this.outputItemHandler.deserializeNBT(nbt.getCompound("output_inventory"));
        this.processTimer = nbt.getInt("process_timer");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt)
    {
        super.saveAdditional(nbt);
        nbt.put("input_inventory", this.inputItemHandler.serializeNBT());
        nbt.put("output_inventory", this.outputItemHandler.serializeNBT());
        nbt.putInt("process_timer", this.processTimer);
    }

    @Override
    public Component getDisplayName() { return CokeOvenMenu.TITLE; }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) { return new CokeOvenMenu(containerId, playerInventory, this, this.containerData); }
}
