package com.kilroy790.notenoughmachines.api.utilities;

import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;




public class InventoryHandler {

	
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
}
