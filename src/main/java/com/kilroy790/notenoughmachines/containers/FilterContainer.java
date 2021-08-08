package com.kilroy790.notenoughmachines.containers;

import com.kilroy790.notenoughmachines.tiles.FilterTile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;




public class FilterContainer extends NEMBaseContainer<FilterTile> 
{
	private static final int BUFFERSLOT = 0;
	private static final int FILTERSLOT = 1;
	private static final int PLAYER_INV_START = 29;
	private static final int PLAYER_INV_END = 65;
	
	
	
	public FilterContainer(final int id, final PlayerInventory inventory, final FilterTile tile) 
	{
		super(NEMContainers.FILTER.get(), id, inventory, tile);
		
		tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent( h -> 
		{
			//Buffer slot
			addSlot(new SlotItemHandler(h, 0, 15, 47));
			
			//Filter slots
			addItemSlot(h, 43, 20, 18, 18, 1, 7, 4);
		});
		
		addPlayerInventorySlots(7, 105);
	}
	
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) 
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			//buffer slot to player inventory
			if (index == BUFFERSLOT) 
			{
				if(!this.mergeItemStack(itemstack1, PLAYER_INV_START, PLAYER_INV_END, false)) 
					return ItemStack.EMPTY;
			}
			
			//Filter slots to player inventory
			if (index >= FILTERSLOT && index < PLAYER_INV_START) 
			{
				if(!this.mergeItemStack(itemstack1, PLAYER_INV_START, PLAYER_INV_END, false)) 
					return ItemStack.EMPTY;
			}

			//player inventory to buffer slot or back into the player's inventory
			if (index < PLAYER_INV_END && index >= PLAYER_INV_START) 
			{
				if(canMergeSlot(itemstack1, getSlot(BUFFERSLOT))) 
				{
					if (!this.mergeItemStack(itemstack1, BUFFERSLOT, FILTERSLOT, false)) 
						return ItemStack.EMPTY; 
				}
				else 
				{
					if(index < PLAYER_INV_END - 9) 
					{
						if(!this.mergeItemStack(itemstack1, PLAYER_INV_END - 9, PLAYER_INV_END, true)) 
							return ItemStack.EMPTY;
					}
					else 
					{
						if(!this.mergeItemStack(itemstack1, PLAYER_INV_START, PLAYER_INV_END - 9, false)) 
							return ItemStack.EMPTY;
					}
				}
			}

			if(itemstack1.isEmpty()) 
				slot.putStack(ItemStack.EMPTY);
			else 
				slot.onSlotChanged();
			
			if (itemstack1.getCount() == itemstack.getCount()) 
				return ItemStack.EMPTY;
			
			slot.onTake(player, itemstack1);
		}
		return itemstack;
	}
	
	
	
	public FilterContainer(final int id, final PlayerInventory inventory, final PacketBuffer data) 
	{
		this(id, inventory, (FilterTile)inventory.player.world.getTileEntity(data.readBlockPos()));
	}
}







