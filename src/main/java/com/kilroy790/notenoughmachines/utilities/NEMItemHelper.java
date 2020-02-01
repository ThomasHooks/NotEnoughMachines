package com.kilroy790.notenoughmachines.utilities;

import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;




public class NEMItemHelper {

	
	public static void dropItemHandlerInventory(final IItemHandler handler, World world, BlockPos pos) {
		if(handler == null) return;
		for(int i = 0; i < handler.getSlots(); ++i) {
			InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), handler.getStackInSlot(i));
		}
	}
	
	
	public static void dropItemStack(World world, BlockPos pos, ItemStack stack) {
		if(stack == null) return;
		else InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack);
	}
	
	
	public static void pushToContainer(World world, BlockPos nextPos, Direction facing, ItemStackHandler itemHandler, int index, int amount) {
		/*
		 * Will try to push an item stack size of the given amount to the tile at the given block position
		 * 
		 * @param	world			the current world
		 * 
		 * @param	nextPos			position of the tile to push the item stack into
		 * 
		 * @param	facing			the direction that the tile is pushing items into
		 * 
		 * @param	itemHandler		the item handler for this tile
		 * 
		 * @param	index			the slot of the tile to pull from
		 * 
		 * @param	amount			the number of items in the item stack to push
		 */

		TileEntity nextTile = world.getTileEntity(nextPos);
		if(nextTile != null && !itemHandler.getStackInSlot(index).isEmpty()) {

			ItemStack stackIn = itemHandler.getStackInSlot(index).copy();
			LazyOptional<IItemHandler> nextContainer = nextTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
			nextContainer.ifPresent(h -> {

				stackIn.setCount(amount);
				int numOfSlots = h.getSlots();
				for(int slot = 0; slot < numOfSlots; slot++) {

					if(h.isItemValid(slot, stackIn)) {

						ItemStack stackRemander = h.insertItem(slot, stackIn, true);
						if(stackRemander.isEmpty()) {

							h.insertItem(slot, stackIn, false);
							itemHandler.extractItem(index, amount, false);
							break;
						}

						else continue;
					}
				}
			});
		}
	}
	
	
	public static void pullFromContainer(World world, BlockPos nextPos, Direction facing, ItemStackHandler itemHandler, int index, int amount) {
		/*
		 * Will try to push an item stack size of the given amount to the tile at the given block position
		 * 
		 * @param	world			the current world
		 * 
		 * @param	nextPos			position of the tile to pull the item stack from
		 * 
		 * @param	facing			the direction that the tile is pulling the item stack from
		 * 
		 * @param	itemHandler		the item handler for this tile
		 * 
		 * @param	index			the slot of the tile to pull into
		 * 
		 * @param	amount			the number of items in the item stack to pull
		 */

		TileEntity nextTile = world.getTileEntity(nextPos);
		if(nextTile != null) {

			LazyOptional<IItemHandler> nextContainer = nextTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
			nextContainer.ifPresent(h -> {

				int numOfSlots = h.getSlots();
				for(int slot = 0; slot < numOfSlots; slot++) {
					
					ItemStack stackIn = h.getStackInSlot(slot).copy();
					if(!stackIn.isEmpty() && itemHandler.isItemValid(index, stackIn)) {
						
						stackIn.setCount(amount);
						ItemStack stackRemander = itemHandler.insertItem(index, stackIn, true);
						if(stackRemander.isEmpty()) {
							
							itemHandler.insertItem(index, stackIn, false);
							h.extractItem(slot, amount, false);
							break;
						}
					}
					else continue;
				}
			});
		}
	}
	
	
	public static void moveItemsInternally(ItemStackHandler itemHandler1, int index, int amount, ItemStackHandler itemHandler2) {
		/*
		 * Will try to move an item stack size of the given amount from on item handler to another
		 * 
		 * @param	itemHandler1		the item handler where the item stack is moving from
		 * 
		 * @param	index				the slot of the tile to pull from
		 * 
		 * @param	amount				the number of items in the item stack to push
		 * 
		 * @param	itemHandler2		the item handler where the item stack is moving to
		 */
		
		ItemStack stackIn = itemHandler1.getStackInSlot(index).copy();
		if(!stackIn.isEmpty() && stackIn.getCount() > 1) {
			
			stackIn.setCount(amount);
			for(int slot = 0; slot < itemHandler2.getSlots(); slot++) {
				
				if(itemHandler2.isItemValid(slot, stackIn)) {

					ItemStack stackRemander = itemHandler2.insertItem(slot, stackIn, true);
					if(stackRemander.isEmpty()) {

						itemHandler2.insertItem(slot, stackIn, false);
						itemHandler1.extractItem(index, amount, false);
						break;
					}

					else continue;
				}
			}
		}
	}
}
