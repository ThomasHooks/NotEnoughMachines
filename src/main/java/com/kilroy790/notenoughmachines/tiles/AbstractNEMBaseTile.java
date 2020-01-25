package com.kilroy790.notenoughmachines.tiles;

import com.kilroy790.notenoughmachines.api.power.MechanicalPowerProducer;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
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
