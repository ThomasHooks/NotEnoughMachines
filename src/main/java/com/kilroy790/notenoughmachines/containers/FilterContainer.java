package com.kilroy790.notenoughmachines.containers;

import com.kilroy790.notenoughmachines.api.lists.BlockList;
import com.kilroy790.notenoughmachines.api.lists.ContainerList;
import com.kilroy790.notenoughmachines.tiles.machines.logistic.FilterTile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;




public class FilterContainer extends Container {

	
	private FilterTile tile;
	
	private IItemHandler playerInv;
	
	private final int FILTERSLOT = 0;
	private final int FILTER_INV = 1;
	private final int PLAYER_INV_START = 5;
	private final int PLAYER_INV_END = 41;
	
	
	public FilterContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
		super(ContainerList.FILTER_CONTAINER, id);
		this.tile = (FilterTile) world.getTileEntity(pos);
		this.playerInv = new InvWrapper(playerInv);
		
		//add the filter's inventory and filter slot
		tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
			
			//add the filter slot
			addSlot(new SlotItemHandler(h, 0, 34, 20));
			
			//add the Filter's inventory slots
			for(int i = 0; i < tile.getNumberOfInventorySlots(); i++) {
				addSlot(new SlotItemHandler(h, i + 1, 61 + (i * 18), 20));
			}
		});
		
		//add the player inventory and quick bar
		addPlayerInventorySlots(7,51);
	}
	
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) {
		//method for shift clicking into an inventory 
		
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index == FILTERSLOT) {
				//Filter slot to player inventory
				if(!this.mergeItemStack(itemstack1, PLAYER_INV_START, PLAYER_INV_END, true)) return ItemStack.EMPTY;
			}
			
			else if (index >= FILTER_INV && index < PLAYER_INV_START) {
				//Filter inventory slot to player inventory
				if(!this.mergeItemStack(itemstack1, PLAYER_INV_START, PLAYER_INV_END, true)) return ItemStack.EMPTY;
			}
			
			if (index < PLAYER_INV_END && index >= PLAYER_INV_START) {
				//player inventory to Filter inventory
				if (!this.mergeItemStack(itemstack1, FILTER_INV, PLAYER_INV_START, false)) return ItemStack.EMPTY;
			}

			if (itemstack1.getCount() == 0) slot.putStack(ItemStack.EMPTY);
			
			else slot.onSlotChanged();
		}

		return itemstack;
	}

	
	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return isWithinUsableDistance(IWorldPosCallable.of(tile.getWorld(), tile.getPos()), player, BlockList.FILTER);
	}
	
	
	protected void addPlayerInventorySlots(int topLeftSlotX, int topLeftSlotY) {
		//Player inventory
		addItemSlot(playerInv, 9, topLeftSlotX, topLeftSlotY, 9, 18, 3, 18);
		
		//Player quick bar
		topLeftSlotY += 58;
		addItemSlot(playerInv, 0, topLeftSlotX, topLeftSlotY, 9, 18, 1, 0);
	}
	
	
	protected void addItemSlot(IItemHandler itemHandler, int index, int x, int y, int horSlotNumber, int xOffset, int verSlotNumber, int yOffset) {

		for(int i = 0; i < verSlotNumber; i++) {
			int tempX = x;
			for(int j = 0; j < horSlotNumber; j++) {
				addSlot(new SlotItemHandler(itemHandler, index, tempX, y));
				tempX += xOffset;
				index++;
			}
			y += yOffset;
		}
	}
}
