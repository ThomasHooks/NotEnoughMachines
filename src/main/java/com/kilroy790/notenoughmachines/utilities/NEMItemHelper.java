package com.kilroy790.notenoughmachines.utilities;

import com.kilroy790.notenoughmachines.blocks.machines.AxleBlock;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;




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
	
	
	public static void removeAxleBlock(World world, BlockPos pos, Boolean destroy) {
		/*
		 * Removes	the given axle and will either drop the axle or destroy it
		 * 			if the axle is destroyed it will drop between 3 - 7 sticks
		 * 
		 * @param world		the current world
		 * 
		 * @param pos		the position of the axle
		 * 
		 * @param destroy	if true the axle is to be destroyed
		 */
		
		if(world.isRemote) return;
		
		Block block = world.getBlockState(pos).getBlock();
		if(block instanceof AxleBlock) {
			
			if(destroy) {
				int rand = (int)(Math.random() * 4.0D);
				NEMItemHelper.dropItemStack(world, pos, new ItemStack(Items.STICK, 3 + rand));
				world.destroyBlock(pos, false);
			}
			else {
				world.destroyBlock(pos, true);
			}
		}
	}
}
