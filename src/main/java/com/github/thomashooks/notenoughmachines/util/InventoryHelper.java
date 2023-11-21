package com.github.thomashooks.notenoughmachines.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class InventoryHelper extends Containers
{
    public static void dropItemStackWithVel(Level world, ItemStack stack, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
    {
        if (stack == null)
            return;

        while (!stack.isEmpty())
        {
            ItemEntity entity = new ItemEntity(world, x, y, z, stack.split(world.random.nextInt(21) + 10));
            entity.setDeltaMovement(world.random.nextDouble() * 0.05F + xSpeed,
                    world.random.nextDouble() * 0.05D + 0.2D + ySpeed, //The extra 0.2D getting added with the y speed component is to add an arch. without it items will be thrown down.
                    world.random.nextDouble() * 0.05D + zSpeed);
            world.addFreshEntity(entity);
        }
    }

    public static void dropItemHandler(final @Nullable IItemHandler handler, Level world, BlockPos pos)
    {
        if (handler == null)
            return;

        for (int i = 0; i < handler.getSlots(); i++)
        {
            dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), handler.getStackInSlot(i));
        }
    }

    /**
     * Will try to push an item stack size of the given amount to the block entity at the given block position
     * @param world the current world
     * @param nextPos position of the block entity to push the item stack into
     * @param facing the direction that the block entity is pushing items into
     * @param itemHandler the item stack handler for this block entity, i.e. the one that is being pulled from
     * @param slot the slot index of the block entity that is being pulled from
     * @param amount the number of items in the item stack that is being pushed
     */
    public static void pushToContainer(Level world, BlockPos nextPos, Direction facing, ItemStackHandler itemHandler, int slot, int amount)
    {
        BlockEntity nextEntity = world.getBlockEntity(nextPos);
        if (nextEntity != null && !itemHandler.getStackInSlot(slot).isEmpty())
        {
            ItemStack stackIn = itemHandler.getStackInSlot(slot).copy();
            LazyOptional<IItemHandler> nextContainer = nextEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, facing);
            nextContainer.ifPresent(nextItemHandler ->
            {
                stackIn.setCount(amount);
                int numOfSlots = nextItemHandler.getSlots();
                for (int i = 0; i < numOfSlots; i++)
                {
                    if (nextItemHandler.isItemValid(i, stackIn))
                    {
                        ItemStack stackRemainder = nextItemHandler.insertItem(i, stackIn, true);
                        if (stackRemainder.isEmpty())
                        {
                            nextItemHandler.insertItem(i, stackIn, false);
                            itemHandler.extractItem(slot, amount, false);
                            break;
                        }
                        else
                            continue;
                    }
                }
            });
        }
    }

    /**
     * Will try to push an item stack size of the given amount to the block entity at the given block position
     * @param world the current world
     * @param nextPos position of the block entity to pull the item stack from
     * @param facing the direction that the block entity is pulling the item stack from
     * @param itemHandler the item handler for this block entity
     * @param slot the slot of the block entity to pull into
     * @param amount the number of items in the item stack to pull
     */
    public static void pullFromContainer(Level world, BlockPos nextPos, Direction facing, ItemStackHandler itemHandler, int slot, int amount)
    {
        BlockEntity nextEntity = world.getBlockEntity(nextPos);
        if (nextEntity != null)
        {
            LazyOptional<IItemHandler> nextContainer = nextEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, facing);
            nextContainer.ifPresent(nextItemHandler ->
            {
                int numOfSlots = nextItemHandler.getSlots();
                for (int i = 0; i < numOfSlots; i++)
                {
                    ItemStack stackIn = nextItemHandler.getStackInSlot(i).copy();
                    if (!stackIn.isEmpty() && itemHandler.isItemValid(slot, stackIn))
                    {
                        stackIn.setCount(amount);
                        ItemStack stackRemainder = itemHandler.insertItem(slot, stackIn, true);
                        ItemStack stackExtracted = nextItemHandler.extractItem(i, amount, true);
                        if (stackRemainder.isEmpty() && !stackExtracted.isEmpty())
                        {
                            itemHandler.insertItem(slot, stackIn, false);
                            nextItemHandler.extractItem(i, amount, false);
                            break;
                        }
                    }
                    else
                        continue;
                }
            });
        }
    }

    /**
     * Will try to move an item stack size of the given amount from one item handler to another
     * @param itemStackHandler1 the item handler where the item stack is moving from
     * @param itemStackHandler2 the item handler where the item stack is moving to
     * @param slot the slot of the block entity to pull from
     * @param amount the number of items in the item stack to move
     */
    public static void moveItemsInternally(ItemStackHandler itemStackHandler1, ItemStackHandler itemStackHandler2, int slot, int amount)
    {
        ItemStack stackIn = itemStackHandler1.getStackInSlot(slot).copy();
        if (!stackIn.isEmpty() && stackIn.getCount() > 1)
        {
            stackIn.setCount(amount);
            for (int i = 0; i < itemStackHandler2.getSlots(); i++)
            {
                if (itemStackHandler2.isItemValid(i, stackIn))
                {
                    ItemStack stackRemainder = itemStackHandler2.insertItem(i, stackIn, true);
                    if (stackRemainder.isEmpty())
                    {
                        itemStackHandler2.insertItem(i, stackIn, false);
                        itemStackHandler2.extractItem(slot, amount, false);
                        break;
                    }
                    else
                        continue;
                }
            }
        }
    }
}
