package com.kilroy790.notenoughmachines.tiles.machines.logistic;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.blocks.machines.logistic.ItemPusherBlock;
import com.kilroy790.notenoughmachines.tiles.AbstractBaseTile;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;




public class ItemPusherTile extends AbstractBaseTile implements ITickableTileEntity {

	
	protected ItemStackHandler itemInv;
	protected LazyOptional<ItemStackHandler> itemInvHandler = LazyOptional.of(() -> itemInv);
	
	protected int itemTransfer = 0;
	public static final int MAX_ITEM_TRANSFER = 1;
	public static final int ITEM_TRANSFER_RATE = 8;
	
	
	public ItemPusherTile() {
		super(TileEntityList.ITEMPUSHER);
		itemInv = this.makeItemHandler(1);
	}

	
	@Override
	public void tick() {
		//there are ~20 tick per second
		
		if(!this.world.isRemote) {
			
			if(this.canTransferItem()) {
				this.pullItems(this.itemInv, MAX_ITEM_TRANSFER);
				this.pushItems(this.itemInv, MAX_ITEM_TRANSFER);
				this.setItemTransfer(0);
			}
			this.itemTransfer++;
			if(this.itemTransfer > ITEM_TRANSFER_RATE * 2) this.setItemTransfer(ITEM_TRANSFER_RATE * 2);
		}
	}
	
	
	protected void pullItems(ItemStackHandler itemHandler, int amount) {
		/*
		 * Will try to pull the item stack from the Container
		 * 
		 * @param	itemHandler		the item handler for this tile
		 * 
		 * @param	amount			number of items in the stack that will be pushed to the next Container
		 */
		
		Direction facing = this.getBlockState().get(ItemPusherBlock.FACING);
		BlockPos nextPos = pos.offset(facing);
		
		NEMItemHelper.pullFromContainer(world, nextPos, facing.getOpposite(), itemHandler, 0, amount);
	}
	
	
	protected void pushItems(ItemStackHandler itemHandler, int amount) {
		/*
		 * Will try to drop the item stack in front of itself or push to the Container in front of itself
		 * 
		 * @param	itemHandler		the item handler for this tile
		 * 
		 * @param	amount			number of items in the stack that will be pushed to the next Container
		 */

		Direction facing = this.getBlockState().get(ItemPusherBlock.FACING).getOpposite();
		BlockPos nextPos = pos.offset(facing);
		
		if(this.world.getBlockState(nextPos).isAir(this.getWorld(), nextPos)) NEMItemHelper.dropItemStack(world, nextPos, itemInv.extractItem(0, amount, false));
		
		else {
			for(int slot = 0; slot < itemHandler.getSlots(); slot++) {

				if(itemHandler.getStackInSlot(slot).isEmpty()) continue;
				
				else {
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
		return this.itemTransfer > ITEM_TRANSFER_RATE && this.world.getBlockState(pos).get(ItemPusherBlock.ENABLED);
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
