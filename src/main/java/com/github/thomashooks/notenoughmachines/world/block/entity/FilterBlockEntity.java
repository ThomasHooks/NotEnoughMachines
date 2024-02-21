package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.util.DirectionHelper;
import com.github.thomashooks.notenoughmachines.util.InventoryHelper;
import com.github.thomashooks.notenoughmachines.world.block.ItemConduitBlock;
import com.github.thomashooks.notenoughmachines.world.inventory.FilterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FilterBlockEntity extends ItemConduitBlockEntity implements MenuProvider
{
    public static final int INVENTORY_INDEX = 0;
    public static final int NUMBER_OF_INVENTORY_SLOTS = 1;
    public final static int RED_FILTER_INDEX = 1;
    public final static int GREEN_FILTER_INDEX = 2;
    public final static int BLUE_FILTER_INDEX = 3;
    public final static int YELLOW_FILTER_INDEX = 4;
    public final static int NUMBER_OF_FILTERS = 4;
    public final static int NUMBER_OF_FILTER_SLOTS = 7;
    private LazyOptional<ItemStackHandler> inventoryHandler = LazyOptional.of(() -> this.itemStackHandlers.get(INVENTORY_INDEX));
    private LazyOptional<CombinedInvWrapper> combinedHandler = LazyOptional.of(() ->
            new CombinedInvWrapper(this.itemStackHandlers.get(INVENTORY_INDEX),
                    this.itemStackHandlers.get(RED_FILTER_INDEX),
                    this.itemStackHandlers.get(GREEN_FILTER_INDEX),
                    this.itemStackHandlers.get(BLUE_FILTER_INDEX),
                    this.itemStackHandlers.get(YELLOW_FILTER_INDEX)
            ));

    public FilterBlockEntity(BlockPos pos, BlockState state)
    {
        super(AllBlockEntities.FILTER.get(), pos, state);

        addItemStackHandler(NUMBER_OF_INVENTORY_SLOTS);
        for ( ColorType filter : ColorType.values())
        {
            addItemStackHandler(NUMBER_OF_FILTER_SLOTS, 1);
        }
    }

    @Override
    public boolean canPushItems() { return true; }

    @Override
    protected void pushItems(ItemStackHandler itemStackHandler, int amount)
    {
        ItemStack inventoryStack = this.itemStackHandlers.get(getInventoryIndex()).getStackInSlot(0);
        if (inventoryStack.isEmpty())
            return;

        for (ColorType filterColor : ColorType.values())
        {
            for (int slot = 0; slot < NUMBER_OF_FILTER_SLOTS; slot++)
            {
                ItemStack filterItem = this.itemStackHandlers.get(filterColor.getNumber()).getStackInSlot(slot);
                if (filterItem.isEmpty() || !inventoryStack.getItem().equals(filterItem.getItem()))
                    continue;

                Direction facing = getFilterFacing(filterColor);
                BlockPos nextPos = getBlockPos().relative(facing);
                BlockEntity nextEntity = getLevel().getBlockEntity(nextPos);
                if (nextEntity != null)
                    nextEntity.getCapability(ForgeCapabilities.ITEM_HANDLER)
                            .ifPresent(h -> InventoryHelper.pushToContainer(getLevel(), nextPos, facing.getOpposite(), itemStackHandler, INVENTORY_INDEX, amount));
                else if (getLevel().getBlockState(nextPos).isAir())
                    ejectItems(itemStackHandler, amount, INVENTORY_INDEX, nextPos, facing);
                else
                    break; //We can only get here if this side of the filter is blocked

                return;
            }
        }
        //This pushes non-filtered items out through the blank side
        super.pushItems(itemStackHandler, amount);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            if (side == null)
                return combinedHandler.cast();
            else
                return inventoryHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps()
    {
        inventoryHandler.invalidate();
        combinedHandler.invalidate();
        super.invalidateCaps();
    }

    public Direction getFilterFacing(ColorType filter)
    {
        Direction facing = getBlockState().getValue(ItemConduitBlock.FACING);
        switch (filter)
        {
            case RED ->
            {
                if (facing == Direction.UP || facing == Direction.DOWN)
                    return DirectionHelper.rotateX(Direction.UP, facing == Direction.DOWN, 1);
                else
                    return Direction.UP;
            }
            case BLUE ->
            {
                if (facing == Direction.UP || facing == Direction.DOWN)
                    return DirectionHelper.rotateX(Direction.DOWN, facing == Direction.DOWN, 1);
                else
                    return Direction.DOWN;
            }
            case GREEN ->
            {
                if (facing == Direction.UP || facing == Direction.DOWN)
                    return Direction.WEST;
                else
                {
                    int times = 0;
                    switch (facing)
                    {
                        case EAST -> times = 1;
                        case SOUTH -> times = 2;
                        case WEST -> times = 3;
                    }
                    return DirectionHelper.rotateY(Direction.WEST, false, times);
                }
            }
            case YELLOW ->
            {
                if (facing == Direction.UP || facing == Direction.DOWN)
                    return Direction.EAST;
                else
                {
                    int times = 0;
                    switch (facing)
                    {
                        case EAST -> times = 1;
                        case SOUTH -> times = 2;
                        case WEST -> times = 3;
                    }
                    return DirectionHelper.rotateY(Direction.EAST, false, times);
                }
            }
            default -> throw new IllegalStateException(NotEnoughMachines.MOD_ID + ":FilterBlockEntity is in an unknown state");
        }
    }

    @Override
    public @NotNull Component getDisplayName() { return FilterMenu.TITLE; }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player)
    {
        return new FilterMenu(containerId, playerInventory, this);
    }

    public enum ColorType
    {
        RED(1),
        GREEN(2),
        BLUE(3),
        YELLOW(4);

        private final int color;

        private ColorType(int colorNumberIn)
        {
            this.color = colorNumberIn;
        }

        public int getNumber() { return this.color; }
    }
}
