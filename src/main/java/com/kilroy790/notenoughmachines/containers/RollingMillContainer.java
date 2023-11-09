package com.kilroy790.notenoughmachines.containers;

import com.kilroy790.notenoughmachines.tiles.RollingMillTile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IntReferenceHolder;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;




public class RollingMillContainer extends NEMBaseContainer<RollingMillTile> 
{
	private final int INPUT_SLOT = 0;
	private final int OUTPUT_SLOT = 1;
	private final int PLAYER_INV_END = 38;
	private final int PLAYER_INV_START = 2;



	public RollingMillContainer(int id, PlayerInventory inventory, RollingMillTile tile) 
	{
		super(NEMContainers.ROLLING_MILL.get(), id, inventory, tile);

		tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent( h -> 
		{
			//input slot
			addSlot(new SlotItemHandler(h, 0, 61, 17));

			//output slot
			addSlot(new SlotItemHandler(h, 1, 99, 17));
		});

		addPlayerInventorySlots(7, 48);

		//track the rolling mill's max process time
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
		
		//track the rolling mill's process time
		trackInt(new IntReferenceHolder() 
		{
			@Override
			public int get() 
			{
				return getProgress();
			}
			
			
			
			@Override
			public void set(int value) 
			{
				
			}
		});
	}



	public RollingMillContainer(final int id, final PlayerInventory inventory, final PacketBuffer data) 
	{
		this(id, inventory, (RollingMillTile)inventory.player.world.getTileEntity(data.readBlockPos()));
	}



	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) 
	{	
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) 
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index == INPUT_SLOT) 
			{
				//input slot to player inventory
				if (!this.mergeItemStack(itemstack1, PLAYER_INV_START, PLAYER_INV_END, false)) 
					return ItemStack.EMPTY;
			}

			else if (index == OUTPUT_SLOT) 
			{
				//output slot to player inventory
				if (!this.mergeItemStack(itemstack1, PLAYER_INV_START, PLAYER_INV_END, false)) 
					return ItemStack.EMPTY;
			}

			if (index < PLAYER_INV_END && index >= PLAYER_INV_START) 
			{
				//player inventory to the input slot
				if (!this.mergeItemStack(itemstack1, INPUT_SLOT, OUTPUT_SLOT, true)) 
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



