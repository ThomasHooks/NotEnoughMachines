package com.kilroy790.notenoughmachines.tiles.machines;

import java.util.ArrayList;

import com.kilroy790.notenoughmachines.blocks.machines.logistic.ItemPusherBlock;
import com.kilroy790.notenoughmachines.tiles.NEMBaseTile;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;




public abstract class ItemConduitTile extends NEMBaseTile implements ITickableTileEntity {
	
	protected ArrayList<ItemStackHandler> inventory = new ArrayList<ItemStackHandler>();
	
	protected int transferTimer = 0;
	public static final int MAX_ITEM_TRANSFER = 1;
	public static final int ITEM_TRANSFER_RATE = 8;
	
	
	
	public ItemConduitTile(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	
	
	@Override
	public void tick() {
		if (!this.world.isRemote) {
			if (canTransferItem()) {
				for (int i = 0; i < this.inventory.size(); i++) {
					ItemStackHandler inv = this.inventory.get(i);
					if (canPullItems()) pullItems(inv, MAX_ITEM_TRANSFER);
					if (canPushItems()) pushItems(inv, MAX_ITEM_TRANSFER);
				}
				this.transferTimer = 0;
				markDirty();
			}
			this.transferTimer++;
			if (this.transferTimer > ITEM_TRANSFER_RATE) this.transferTimer = ITEM_TRANSFER_RATE;
		}
	}
	
	
	
	public boolean canTransferItem() {
		return this.transferTimer >= ITEM_TRANSFER_RATE;
	}
	
	
	
	/**
	 * @return If this Item Conduit can pull Items from other inventories
	 */
	public boolean canPullItems() {
		return false;
	}
	
	
	
	/**
	 * @return If this Item Conduit can push Items into other inventories
	 */
	public boolean canPushItems() {
		return false;
	}
	
	
	
	/**
	 * Will try to pull Items from the Container in front of this Item Conduit
	 * 
	 * @param itemHandler The item handler for this tile
	 * @param amount Number of items in the stack that will be pushed to the next Container
	 */
	protected void pullItems(ItemStackHandler itemHandler, int amount) {
		Direction facing = this.getBlockState().get(ItemPusherBlock.FACING);
		BlockPos nextPos = pos.offset(facing);
		NEMItemHelper.pullFromContainer(world, nextPos, facing.getOpposite(), itemHandler, 0, amount);
	}
	
	
	
	/**
	 * Will try to push Items to the Container or drop Items behind this Item Conduit
	 * 
	 * @param itemHandler The item handler for this tile
	 * @param amount Number of items in the stack that will be pushed to the next Container
	 */
	protected void pushItems(ItemStackHandler itemHandler, int amount) {

		Direction facing = this.getBlockState().get(ItemPusherBlock.FACING).getOpposite();
		BlockPos nextPos = pos.offset(facing);
		if (this.world.getBlockState(nextPos).isAir(this.getWorld(), nextPos)) {
			NEMItemHelper.dropItemStack(world, nextPos, itemHandler.extractItem(0, amount, false));
		}
		else {
			for (int slot = 0; slot < itemHandler.getSlots(); slot++) {

				if (itemHandler.getStackInSlot(slot).isEmpty()) continue;
				else {
					NEMItemHelper.pushToContainer(world, pos.offset(facing), facing.getOpposite(), itemHandler, slot, amount);
					break;
				}
			}
		}
	}
	
	
	
	/**
	 * Adds a new Item Stack Handler to this Item Conduit
	 * 
	 * @param numOfSlots The number of Item Stack slots
	 * @param stackLimit The Item Stack limit for each Item slot
	 */
	protected void addItemStackHandler(int numOfSlots, int stackLimit) {
		this.inventory.add(makeItemHandler(numOfSlots, stackLimit));
	}
	
	
	
	/**
	 * Adds a new Item Stack Handler to this Item Conduit with a Item Stack limit of 64
	 * 
	 * @param numOfSlots The number of Item Stack slots
	 */
	protected void addItemStackHandler(int numOfSlots) {
		addItemStackHandler(numOfSlots, 64);
	}
	
	
	
	@Override
	protected void readCustom(CompoundNBT compound) {
		
		for (int i = 0; i < this.inventory.size(); i++) {
			this.inventory.get(i).deserializeNBT(compound.getCompound("inv" + Integer.toString(i)));
		}
		this.transferTimer = compound.getInt("transfertime");
	}

	
	
	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) {
		
		for (int i = 0; i < this.inventory.size(); i++) {
			compound.put("inv" + Integer.toString(i), this.inventory.get(i).serializeNBT());
		}
		compound.putInt("transfertime", this.transferTimer);
		return compound;
	}
}







