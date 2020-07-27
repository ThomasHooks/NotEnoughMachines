package com.kilroy790.notenoughmachines.containers;

import com.kilroy790.notenoughmachines.setup.NEMBlocks;
import com.kilroy790.notenoughmachines.setup.NEMContainers;
import com.kilroy790.notenoughmachines.tiles.machines.processing.TripHammerTile;

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




public class TripHammerContainer extends Container {

	private TripHammerTile tile;
	private IItemHandler playerInv;

	private final int INPUTSLOTSTART = 0;
	private final int OUTPUTSLOTSTART = 1;
	private final int PLAYER_INV_START = 3;
	private final int PLAYER_INV_END = 39;

	public TripHammerContainer(int id, World world, BlockPos pos, PlayerInventory playerInv, PlayerEntity player) {
		super(NEMContainers.TRIPHAMMER.get(), id);
		this.tile = (TripHammerTile) world.getTileEntity(pos);
		this.playerInv = new InvWrapper(playerInv);

		//add the machines' inventory slots
		tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
			
			for(int slot = 0; slot < tile.getNumberOfInventorySlots(); slot++) {
				//add the input slot
				if(slot == 0) addSlot(new SlotItemHandler(h, slot, 51, 20));
				
				//add the output slots
				else addSlot(new SlotItemHandler(h, slot, 71 + (slot * 18), 20));
			}
		});

		//add the player's inventory and quick bar
		addPlayerInventorySlots(7,51);
		
		trackInt(new IntReferenceHolder() {
			
			@Override
			public int get() {
				return tile.getMaxProcessTime();
			}
			
			@Override
			public void set(int value) {
			}
		});
		
		trackInt(new IntReferenceHolder() {

			@Override
			public int get() {
				return tile.getProcessTime();
			}

			@Override
			public void set(int value) {
			}
			
		});
	}

	

	@Override
	public ItemStack transferStackInSlot(PlayerEntity player, int index) {
		
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {

			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			//input slot to player inventory
			if (index == INPUTSLOTSTART) {
				if(!this.mergeItemStack(itemstack1, PLAYER_INV_START, PLAYER_INV_END, true)) return ItemStack.EMPTY;
			}
			//output slots to player inventory
			else if (index >= OUTPUTSLOTSTART && index < PLAYER_INV_START) {
				if(!this.mergeItemStack(itemstack1, PLAYER_INV_START, PLAYER_INV_END, true)) return ItemStack.EMPTY;
			}
			//player inventory to input slot
			if (index < PLAYER_INV_END && index >= PLAYER_INV_START) {
				if (!this.mergeItemStack(itemstack1, INPUTSLOTSTART, OUTPUTSLOTSTART, false)) return ItemStack.EMPTY;
			}

			if (itemstack1.getCount() == 0) slot.putStack(ItemStack.EMPTY);

			else slot.onSlotChanged();
		}

		return itemstack;
	}


	
	@Override
	public boolean canInteractWith(PlayerEntity player) {
		return isWithinUsableDistance(IWorldPosCallable.of(tile.getWorld(), tile.getPos()), player, NEMBlocks.TRIPHAMMER.get());
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
	
	
	
	public int getProgress() {
		int progress = tile.getProcessTime() * 16 / tile.getMaxProcessTime();
		return progress > 16 ? 16 : progress;
	}
}







