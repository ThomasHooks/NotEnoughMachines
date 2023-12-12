package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.common.util.InventoryHelper;
import com.github.thomashooks.notenoughmachines.world.block.ItemConduitBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;

public abstract class ItemConduitBlockEntity extends LazyTickingBlockEntity
{
    protected ArrayList<ItemStackHandler> itemStackHandlers = new ArrayList<ItemStackHandler>();

    protected ItemConduitBlockEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state)
    {
        super(entityType, pos, state);

        setLazyTimerAlarm(getItemTransferRate());
    }

    @Override
    public void lazyTick()
    {
        ItemStackHandler inventoryIndex = this.itemStackHandlers.get(getInventoryIndex());
        if (canPullItems())
            pullItems(inventoryIndex, getMaxItemTransfer());
        if (canPushItems())
            pushItems(inventoryIndex, getMaxItemTransfer());
    }

    /**
     * @return Gets this item conduit's inventory index
     */
    public int getInventoryIndex() { return 0; }

    /**
     * @return Gets the item transfer rate of this item conduit
     */
    public int getItemTransferRate() { return 40; }

    /**
     * @return Gets the maximum number of items that can be transferred
     */
    public int getMaxItemTransfer() { return 1; }

    /**
     * @return True if this item conduit can pull Items from other inventories
     */
    public abstract boolean canPullItems();

    /**
     * @return True If this item conduit can push Items into other inventories
     */
    public abstract boolean canPushItems();

    /**
     * @return True if this item conduit is locked
     */
    public boolean isLocked() { return false; }

    /**
     * Will try to pull Items from the container in front of this item conduit
     * @param itemStackHandler The item stack handler for this block entity
     * @param amount Number of items in the stack that will be pulled to the next container
     */
    protected void pullItems(ItemStackHandler itemStackHandler, int amount)
    {
        Direction facing = getBlockState().getValue(ItemConduitBlock.FACING).getOpposite();
        BlockPos nextPos = getBlockPos().relative(facing);
        InventoryHelper.pullFromContainer(getLevel(), nextPos, facing.getOpposite(), itemStackHandler, 0, amount);
    }

    /**
     * Will try to push items to the container or drop Items behind this item conduit
     * @param itemStackHandler The item stack handler for this block entity
     * @param amount Number of items in the stack that will be pushed to the next container
     */
    protected void pushItems(ItemStackHandler itemStackHandler, int amount)
    {
        Direction facing = getBlockState().getValue(ItemConduitBlock.FACING);
        if (facing.equals(Direction.UP))
            return;

        BlockPos nextPos = getBlockPos().relative(facing);
        for (int slot = 0; slot < itemStackHandler.getSlots(); slot++)
        {
            if (!itemStackHandler.getStackInSlot(slot).isEmpty())
            {
                if (getLevel().getBlockState(nextPos).isAir())
                    ejectItems(itemStackHandler, amount, 0, nextPos, facing);
                else
                {
                    InventoryHelper.pushToContainer(getLevel(), getBlockPos().relative(facing), facing.getOpposite(), itemStackHandler, slot, amount);
                    break;
                }
            }
        }
    }

    protected void ejectItems(ItemStackHandler itemStackHandler, int amount, int index, BlockPos nextPos, Direction ejectorDirection)
    {
        getLevel().playSound(null, getBlockPos(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.3F, 0.5F);

        double x = (double)nextPos.getX();
        double y = (double)nextPos.getY();
        double z = (double)nextPos.getZ();
        getLevel().levelEvent(2000, getBlockPos(), ejectorDirection.get3DDataValue());
        switch (ejectorDirection)
        {
            case NORTH -> InventoryHelper.dropItemStackWithVel(getLevel(), itemStackHandler.extractItem(index, amount, false), x + 0.5D, y + 0.5D, z + 1.0D, 0.0D, 0.0D, -0.22D);
            case EAST -> InventoryHelper.dropItemStackWithVel(getLevel(), itemStackHandler.extractItem(index, amount, false), x - 0.1D, y + 0.5D, z + 0.5D, 0.22D, 0.0D, 0.0D);
            case SOUTH -> InventoryHelper.dropItemStackWithVel(getLevel(), itemStackHandler.extractItem(index, amount, false), x + 0.5D, y + 0.5D, z - 0.1D, 0.0D, 0.0D, 0.22D);
            case WEST -> InventoryHelper.dropItemStackWithVel(getLevel(), itemStackHandler.extractItem(index, amount, false), x + 1.0D, y + 0.5D, z + 0.5D, -0.22D, 0.0D, 0.0D);
            case UP -> InventoryHelper.dropItemStackWithVel(getLevel(), itemStackHandler.extractItem(index, amount, false), x + 0.5D, y + 1.0D, z + 0.5D, 0.0D, 0.22D, 0.0D);
            case DOWN -> InventoryHelper.dropItemStackWithVel(getLevel(), itemStackHandler.extractItem(index, amount, false), x + 0.5D, y - 0.1D, z + 0.5D, 0.0D, -0.22D - 0.2D, 0.0D);
            default -> throw new IllegalStateException(NotEnoughMachines.MOD_ID + ":ItemConduitBlockEntity is in an unknown state");

        }
    }

    /**
     * Adds a new Item Stack Handler to this item conduit
     * @param numberOfSlots The number of item stack slots
     * @param stackLimit The item stack limit for each item slot
     */
    protected void addItemStackHandler(int numberOfSlots, int stackLimit)
    {
        this.itemStackHandlers.add(makeItemHandler(numberOfSlots, stackLimit));
    }

    /**
     * Adds a new item stack handler to this item conduit with an item stack limit of 64
     * @param numberOfSlots The number of item stack slots
     */
    protected void addItemStackHandler(int numberOfSlots)
    {
        addItemStackHandler(numberOfSlots, 64);
    }

    @Override
    public void load(CompoundTag nbt)
    {
        super.load(nbt);

        for (int i = 0; i < this.itemStackHandlers.size(); i++)
        {
            this.itemStackHandlers.get(i).deserializeNBT(nbt.getCompound("inventory." + Integer.toString(i)));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag nbt)
    {
        super.saveAdditional(nbt);

        for (int i = 0; i < this.itemStackHandlers.size(); i++)
        {
            nbt.put("inventory." + Integer.toString(i), this.itemStackHandlers.get(i).serializeNBT());
        }
    }
}
