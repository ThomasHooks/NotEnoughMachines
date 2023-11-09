package com.kilroy790.notenoughmachines.containers;

import com.kilroy790.notenoughmachines.tiles.TripHammerTile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IntReferenceHolder;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;




public class TripHammerContainer extends NEMBaseContainer<TripHammerTile> 
{
	private final int INPUTSLOTSTART = 0;
	private final int OUTPUTSLOTSTART = 1;
	private final int PLAYER_INV_START = 3;
	private final int PLAYER_INV_END = 39;
	
	
	
	public TripHammerContainer(int id, PlayerInventory inventory, TripHammerTile tile)
	{
		super(NEMContainers.TRIPHAMMER.get(), id, inventory, tile);

		tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> 
		{
			for(int slot = 0; slot < tile.getNumberOfInventorySlots(); slot++) 
			{
				//add the input slot
				if(slot == 0) 
					addSlot(new SlotItemHandler(h, slot, 51, 20));

				//add the output slots
				else 
					addSlot(new SlotItemHandler(h, slot, 71 + (slot * 18), 20));
			}
		});

		//add the player's inventory and quick bar
		addPlayerInventorySlots(7,51);

		trackInt(new IntReferenceHolder() 
		{
			@Override
			public int get() 
			{
				return tile.getMaxProcessTime();
			}



			@Override
			public void set(int value) 
			{

			}
		});

		trackInt(new IntReferenceHolder() 
		{
			@Override
			public int get() 
			{
				return tile.getProcessTime();
			}

			@Override
			public void set(int value) 
			{

			}

		});
	}
	
	
	
	public TripHammerContainer(final int id, final PlayerInventory inventory, final PacketBuffer data)
	{
		this(id, inventory, (TripHammerTile)inventory.player.world.getTileEntity(data.readBlockPos()));
	}

	

	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) 
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) 
		{

			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			//input slot to player inventory
			if (index == INPUTSLOTSTART) 
			{
				if(!this.mergeItemStack(itemstack1, PLAYER_INV_START, PLAYER_INV_END, true)) 
					return ItemStack.EMPTY;
			}
			//output slots to player inventory
			else if (index >= OUTPUTSLOTSTART && index < PLAYER_INV_START) 
			{
				if(!this.mergeItemStack(itemstack1, PLAYER_INV_START, PLAYER_INV_END, true)) 
					return ItemStack.EMPTY;
			}
			//player inventory to input slot
			if (index < PLAYER_INV_END && index >= PLAYER_INV_START) 
			{
				if (!this.mergeItemStack(itemstack1, INPUTSLOTSTART, OUTPUTSLOTSTART, false)) 
					return ItemStack.EMPTY;
			}

			if (itemstack1.getCount() == 0) 
				slot.putStack(ItemStack.EMPTY);

			else 
				slot.onSlotChanged();
		}

		return itemstack;
	}
	
	
	
	public int getProgress() 
	{
		int progress = tile.getProcessTime() * 16 / tile.getMaxProcessTime();
		return progress > 16 ? 16 : progress;
	}
}







