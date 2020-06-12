package com.kilroy790.notenoughmachines.containers;

import com.kilroy790.notenoughmachines.tiles.NEMBaseTile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;




public abstract class NEMBaseContainer<T extends NEMBaseTile> extends Container {

	protected IItemHandler playerInv;
	protected T tile;
	
	

	protected NEMBaseContainer(ContainerType<?> type, int id, PlayerInventory playerInvIn, T tileIn) {
		super(type, id);
		this.playerInv = new InvWrapper(playerInvIn);
		this.tile = tileIn;
	}
	
	
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(IWorldPosCallable.of(tile.getWorld(), tile.getPos()), playerIn, tile.getBlockState().getBlock());
	}
	
	
	
	/**
	 * Adds the player's inventory and quick bar slots to this container
	 * 
	 * @param slotX The X coordinate of the top-left inventory slot in the player's inventory
	 * @param slotY The Y coordinate of the top-left inventory slot in the player's inventory
	 */
	protected void addPlayerInventorySlots(int slotX, int slotY) {
		//Player inventory
		addItemSlot(playerInv, 9, slotX, slotY, 9, 18, 3, 18);

		//Player quick bar
		slotY += 58;
		addItemSlot(playerInv, 0, slotX, slotY, 9, 18, 1, 0);
	}
	


	/**
	 * 
	 * @param itemHandler
	 * @param x
	 * @param y
	 * @param xOffset
	 * @param yOffset
	 * @param slotOffset
	 * @param numberOfHorizontalSlots
	 * @param numberOfVerticalSlots
	 */
	protected void addItemSlot(IItemHandler itemHandler, int x, int y, int xOffset, int yOffset, int slotOffset, int numberOfHorizontalSlots, int numberOfVerticalSlots) {

		for(int i = 0; i < numberOfVerticalSlots; i++) {
			int tempX = x;
			for(int j = 0; j < numberOfHorizontalSlots; j++) {
				addSlot(new SlotItemHandler(itemHandler, slotOffset, tempX, y));
				tempX += xOffset;
				slotOffset++;
			}
			y += yOffset;
		}
	}
	
	
	
	/**
	 * @return Gets the slot index where the player's inventory starts
	 */
	abstract protected int getPlayerInventoryStatringSlot();
	
	
	
	/**
	 * @return Gets the slot index where the player's inventory ends
	 */
	abstract protected int getPlayerInventoryEndingSlot();
}







