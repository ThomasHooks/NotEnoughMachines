package com.kilroy790.notenoughmachines.tiles.machines.logistic;

import com.kilroy790.notenoughmachines.blocks.machines.logistic.ClosedChuteBlock;
import com.kilroy790.notenoughmachines.blocks.machines.logistic.FilterBlock;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.NEMBaseTile;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;




public class ClosedChuteTile extends NEMBaseTile implements ITickableTileEntity {

	
	protected ItemStackHandler itemInv;
	protected LazyOptional<ItemStackHandler> itemInvHandler = LazyOptional.of(() -> itemInv);
	
	protected int itemTransfer = 0;
	public static final int MAX_ITEM_TRANSFER = 1;
	public static final int ITEM_TRANSFER_RATE = 8;
	
	
	public ClosedChuteTile() {
		super(NEMTiles.CLOSEDCHUTE.get());
		itemInv = this.makeItemHandler(1);
	}

	
	@Override
	public void tick() {
		
		if(!this.world.isRemote) {
			
			if(this.canTransferItem()) {
				this.pushItems(this.itemInv, MAX_ITEM_TRANSFER);
				this.setItemTransfer(0);
			}
			this.itemTransfer++;
		}
	}
	
	
	protected void pushItems(ItemStackHandler itemHandler, int amount) {
		/*
		 * Will try to push the item stack into the next Container or drop the item stack in front of itself
		 * 
		 * @param	itemHandler		the item handler for this tile
		 * 
		 * @param	amount			number of items in the stack that will be pushed to the next Container
		 */

		Direction facing = this.getBlockState().get(ClosedChuteBlock.FACING);
		BlockPos nextPos = pos.offset(facing);
		
		if(this.world.getBlockState(nextPos).isAir(this.getWorld(), nextPos)) NEMItemHelper.dropItemStack(world, nextPos, itemInv.extractItem(0, amount, false));

		else {
			for(int slot = 0; slot < itemHandler.getSlots(); slot++) {

				if(itemHandler.getStackInSlot(slot).isEmpty()) continue;
				
				else {

					if(this.world.getBlockState(pos.down()).getBlock() instanceof FilterBlock) NEMItemHelper.pushToContainer(world, pos.down(), Direction.UP, itemHandler, slot, amount);
					//This is done so that if the chute can't push an item into the Filter it will try to push it to the next Container
					NEMItemHelper.pushToContainer(world, pos.offset(facing), facing.getOpposite(), itemHandler, slot, amount);
					break;
				}

			}
		}
	}
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return itemInvHandler.cast();
		
		return super.getCapability(cap, side);
	}

	
	@Override
	protected void readCustom(CompoundNBT compound) {
		itemInv.deserializeNBT(compound.getCompound("inv"));
		this.itemTransfer = compound.getInt("transfertime");
	}

	
	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) {
		compound.put("inv", itemInv.serializeNBT());
		compound.putInt("transfertime", this.itemTransfer);
		return compound;
	}
	
	
	public boolean canTransferItem() {
		return this.itemTransfer > ITEM_TRANSFER_RATE;
	}
	
	
	protected void setItemTransfer(int tick) {
		this.itemTransfer = tick;
		markDirty();
	}

	
	@Override
	public void remove() {
		super.remove();
		this.itemInvHandler.invalidate();
	}
}
