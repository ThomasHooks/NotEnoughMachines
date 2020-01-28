package com.kilroy790.notenoughmachines.tiles;

import com.kilroy790.notenoughmachines.api.power.MechanicalPowerProducer;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;





public abstract class AbstractNEMBaseTile extends TileEntity {

	public AbstractNEMBaseTile(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.readCustom(compound);
	}
	
	
	protected abstract void readCustom(CompoundNBT compound);
	
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound = super.write(compound);
		compound = this.writeCustom(compound);
		return compound;
	}
	
	
	protected abstract CompoundNBT writeCustom(CompoundNBT compound);
	
	
	@Override
	public CompoundNBT getUpdateTag() {
		return this.write(new CompoundNBT());
	}
	
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getPos(), 0, this.getUpdateTag());
	}
	
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		this.read(pkt.getNbtCompound());
	}
	
	
	protected void syncClient() {
		//Sync the tile on the client with the server
		if(!this.world.isRemote) this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 1|2);
	}
	
	
	protected void pushToContainer(BlockPos nextPos, Direction facing, ItemStackHandler itemHandler, int index, int amount) {
		/*
		 * Will try to push an item stack of size given by amount to the tile at the given block position
		 * 
		 * @param	nextPos			position of the tile to push the item stack into
		 * 
		 * @param	facing			the direction that the tile is pushing items into
		 * 
		 * @param	itemHandler		the item handler for this tile
		 * 
		 * @param	index			the slot of the tile to pull from
		 * 
		 * @param	amount			the number of items in the item stack to push
		 */

		TileEntity nextTile = world.getTileEntity(nextPos);
		if(nextTile != null && !itemHandler.getStackInSlot(index).isEmpty()) {

			ItemStack stackIn = itemHandler.getStackInSlot(index).copy();
			LazyOptional<IItemHandler> nextContainer = nextTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
			nextContainer.ifPresent(h -> {

				stackIn.setCount(amount);
				int numOfSlots = h.getSlots();
				for(int slot = 0; slot < numOfSlots; slot++) {

					if(h.isItemValid(slot, stackIn)) {

						ItemStack stackRemander = h.insertItem(slot, stackIn, true);
						if(stackRemander.isEmpty()) {

							h.insertItem(slot, stackIn, false);
							itemHandler.extractItem(index, amount, false);
							break;
						}

						else continue;
					}
				}
			});
		}
	}

	
	protected ItemStackHandler makeItemHandler(int numSlots) {
		return new ItemStackHandler(numSlots) {
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
			}
		};
	}
	
	
	protected MechanicalPowerProducer makeMechanicalPowerHandler(int capacity, int maxReceived, int maxSent) {
		return new MechanicalPowerProducer(capacity, maxReceived, maxSent);
	}
}
