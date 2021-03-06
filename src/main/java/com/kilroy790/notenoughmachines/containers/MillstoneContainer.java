package com.kilroy790.notenoughmachines.containers;

import com.kilroy790.notenoughmachines.blocks.NEMBlocks;
import com.kilroy790.notenoughmachines.tiles.MillstoneTile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;




public class MillstoneContainer extends Container 
{
	public MillstoneTile tile;
	private PlayerEntity player;
	private IItemHandler playerInv;
	
	private final int INPUT_SLOT = 0;
	private final int OUTPUT_SLOT = 1;
	private final int PLAYER_INV_END = 38;
	private final int PLAYER_INV_START = 2;
	
	public MillstoneContainer(int id, World world, BlockPos pos, PlayerInventory playerInvIn, PlayerEntity playerIn) 
	{
		super(NEMContainers.MILLSTONE.get(), id);
		tile = (MillstoneTile) world.getTileEntity(pos);
		this.player = playerIn;
		this.playerInv = new InvWrapper(playerInvIn);
		
		//add the millstone item slots
		tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
			//add input slot
			addSlot(new SlotItemHandler(h, 0, 61, 17));
			//add output slot
			addSlot(new SlotItemHandler(h, 1, 99, 17));
		});
				
		//add the player inventory and quick bar
		addPlayerInventorySlots(7,48);
		
		//track the millstone's process time
		trackInt(new IntReferenceHolder() 
		{
			@Override
			public void set(int p_221494_1_) 
			{
				tile.setProcessTime(p_221494_1_);
			}
			
			@Override
			public int get() 
			{
				return getProgress();
			}
		});
	}
	

	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) 
	{
		return isWithinUsableDistance(IWorldPosCallable.of(tile.getWorld(), tile.getPos()), player, NEMBlocks.MILLSTONE.get());
	}
	
	
	private void addItemSlot(IItemHandler itemHandler, int index, int x, int y, int horSlotNumber, int xOffset, int verSlotNumber, int yOffset) 
	{
		
		for (int i = 0; i < verSlotNumber; i++) 
		{
			int tempX = x;
			for (int j = 0; j < horSlotNumber; j++) 
			{
				addSlot(new SlotItemHandler(itemHandler, index, tempX, y));
				tempX += xOffset;
				index++;
			}
			y += yOffset;
		}
	}
	
	
	private void addPlayerInventorySlots(int topLeftSlotX, int topLeftSlotY) 
	{
		//Player inventory
		addItemSlot(playerInv, 9, topLeftSlotX, topLeftSlotY, 9, 18, 3, 18);
		
		//Player quick bar
		topLeftSlotY += 58;
		addItemSlot(playerInv, 0, topLeftSlotX, topLeftSlotY, 9, 18, 1, 0);
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

			if (index == INPUT_SLOT) 
			{
				//input slot to player inventory
				if(!this.mergeItemStack(itemstack1, PLAYER_INV_START, PLAYER_INV_END, false)) 
				{
					return ItemStack.EMPTY;
				}
			}
			
			else if (index == OUTPUT_SLOT) 
			{
				//output slot to player inventory
				if(!this.mergeItemStack(itemstack1, PLAYER_INV_START, PLAYER_INV_END, false)) 
				{
					return ItemStack.EMPTY;
				}
			}
			
			if (index < PLAYER_INV_END && index >= PLAYER_INV_START) 
			{
				//player inventory to the input slot
				if (!this.mergeItemStack(itemstack1, INPUT_SLOT, OUTPUT_SLOT, true)) 
				{
					return ItemStack.EMPTY;
				}
			}

			if (itemstack1.getCount() == 0) 
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else 
			{
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}
	
	
	public int getProgress() 
	{
		return tile.getProcessTime();
	}
}







