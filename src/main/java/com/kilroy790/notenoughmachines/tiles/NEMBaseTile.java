package com.kilroy790.notenoughmachines.tiles;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;




public abstract class NEMBaseTile extends TileEntity {

	public NEMBaseTile(TileEntityType<?> tileEntityTypeIn) {
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
		compound = writeCustom(compound);
		return compound;
	}
	
	
	
	protected abstract CompoundNBT writeCustom(CompoundNBT compound);
	
	
	
	@Override
	public CompoundNBT getUpdateTag() {
		return write(new CompoundNBT());
	}
	
	
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getPos(), 0, getUpdateTag());
	}
	
	
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		read(pkt.getNbtCompound());
	}
	
	
	
	/*
	 * Causes the Client to sync with the Server
	 * 
	 * 2 will send the change to clients.
	 * 4 will prevent the block from being re-rendered.
	 * 16 will prevent neighbor reactions (e.g. fences connecting, observers pulsing).
	 */
	public void syncClient() {
		if(!this.world.isRemote) this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2 | 16);
	}
	
	
	
	public void triggerBlockUpdate() {
		if(!this.world.isRemote) this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 1);
	}

	
	
	protected ItemStackHandler makeItemHandler(int numSlots) {
		return new ItemStackHandler(numSlots) {
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
			}
		};
	}
}







