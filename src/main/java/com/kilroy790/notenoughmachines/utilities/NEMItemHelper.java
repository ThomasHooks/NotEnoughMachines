package com.kilroy790.notenoughmachines.utilities;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.item.ItemEntity;
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




public class NEMItemHelper 
{	
	private static final Random randomNum = new Random();

	public static void spawnItemStackWithVel(World worldIn, ItemStack stack, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
	{
		while(!stack.isEmpty()) 
		{
			ItemEntity itementity = new ItemEntity(worldIn, x, y, z, stack.split(randomNum.nextInt(21) + 10));
			//The extra 0.2 getting added with the y speed component is to add an arch. without it items will be thrown down
			itementity.setMotion(randomNum.nextGaussian() * 0.05D + xSpeed, randomNum.nextGaussian() * 0.05D + 0.2D + ySpeed, randomNum.nextGaussian() * 0.05D + zSpeed);
			worldIn.addEntity(itementity);
		}
	}



	public static void dropItemHandlerInventory(final IItemHandler handler, World world, BlockPos pos) 
	{
		if(handler == null) 
			return;
		for (int i = 0; i < handler.getSlots(); ++i) 
		{
			InventoryHelper.spawnItemStack(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), handler.getStackInSlot(i));
		}
	}
	
	
	
	public static void dropItemStack(World world, BlockPos pos, @Nullable ItemStack stack) 
	{
		if(stack == null) 
			return;
		else 
			InventoryHelper.spawnItemStack(world, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), stack);
	}
	
	
	
	public static void dropItemStack(World world, @Nullable ItemStack stack, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
	{
		if (stack != null)
			spawnItemStackWithVel(world, stack, x, y, z, xSpeed, ySpeed, zSpeed);
	}
	
	
	
	/**
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
	public static void pushToContainer(World world, BlockPos nextPos, Direction facing, ItemStackHandler itemHandler, int index, int amount) {

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
	
	
	
	/**
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
	public static void pullFromContainer(World world, BlockPos nextPos, Direction facing, ItemStackHandler itemHandler, int index, int amount) {

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
						ItemStack stackExtracted = h.extractItem(slot, amount, true);
						if(stackRemander.isEmpty() && !stackExtracted.isEmpty()) {
							
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
	
	
	
	/**
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
	public static void moveItemsInternally(ItemStackHandler itemHandler1, int index, int amount, ItemStackHandler itemHandler2) {
		
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
