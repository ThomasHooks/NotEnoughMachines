package com.kilroy790.notenoughmachines.containers;

import com.kilroy790.notenoughmachines.tiles.NEMBaseTile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;




public abstract class NEMBaseContainer<T extends NEMBaseTile> extends Container 
{
	protected IItemHandler playerInv;
	protected T tile;
	
	

	protected NEMBaseContainer(ContainerType<?> type, int id, PlayerInventory inventory, T tile) 
	{
		super(type, id);
		this.playerInv = new InvWrapper(inventory);
		this.tile = tile;
	}
	
	
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) 
	{
		return isWithinUsableDistance(IWorldPosCallable.of(tile.getWorld(), tile.getPos()), playerIn, tile.getBlockState().getBlock());
	}
	
	
	
	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slotIn) 
	{
		if(slotIn.getStack().isEmpty()) return true;
		if(!slotIn.getStack().getItem().equals(stack.getItem())) return false;
		if(slotIn.getStack().getCount() + stack.getCount() > slotIn.getSlotStackLimit()) return false;
		else return true;
	}
	
	
	
	/**
	 * Adds the player's inventory and quick bar slots to this container
	 * 
	 * @param slotX The X coordinate of the top-left inventory slot in the player's inventory
	 * @param slotY The Y coordinate of the top-left inventory slot in the player's inventory
	 */
	protected void addPlayerInventorySlots(int slotX, int slotY) 
	{
		//Player's inventory
		addItemSlot(playerInv, slotX, slotY, 18, 18, 9, 9, 3);

		//Player's quick bar
		slotY += 58;
		addItemSlot(playerInv, slotX, slotY, 18, 18, 0, 9, 1);
	}
	


	/**
	 * 
	 * @param itemHandler
	 * @param x
	 * @param y
	 * @param xOffset
	 * @param yOffset
	 * @param slotOffset
	 * @param numberOfColumns
	 * @param numberOfRows
	 */
	protected void addItemSlot(IItemHandler itemHandler, int x, int y, int xOffset, int yOffset, int slotOffset, int numberOfColumns, int numberOfRows) 
	{
		for(int i = 0; i < numberOfRows; i++) 
		{
			int tempX = x;
			for(int j = 0; j < numberOfColumns; j++) 
			{
				addSlot(new SlotItemHandler(itemHandler, slotOffset, tempX, y));
				tempX += xOffset;
				slotOffset++;
			}
			y += yOffset;
		}
	}
}







