package com.github.thomashooks.notenoughmachines.world.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemDuctBlockEntity extends ItemConduitBlockEntity
{
    private final static int NUMBER_OF_INVENTORY_SLOTS = 1;
    private final static int INVENTORY_INDEX = 0;
    private final static int ITEM_STACK_LIMIT = 1;
    private LazyOptional<ItemStackHandler> inventoryHandler = LazyOptional.of(() -> this.itemStackHandlers.get(INVENTORY_INDEX));

    protected ItemDuctBlockEntity(BlockPos pos, BlockState state)
    {
        super(AllBlockEntities.ITEM_DUCT.get(), pos, state);
        addItemStackHandler(NUMBER_OF_INVENTORY_SLOTS, ITEM_STACK_LIMIT);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
            return inventoryHandler.cast();

        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps()
    {
        inventoryHandler.invalidate();
        super.invalidateCaps();
    }

    @Override
    public int getMaxItemTransfer() { return ITEM_STACK_LIMIT; }

    @Override
    public boolean canPushItems() { return true; }
}
