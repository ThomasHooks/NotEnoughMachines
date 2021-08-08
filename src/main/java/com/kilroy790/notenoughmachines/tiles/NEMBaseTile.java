package com.kilroy790.notenoughmachines.tiles;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.items.ItemStackHandler;




public abstract class NEMBaseTile extends TileEntity 
{
	public NEMBaseTile(TileEntityType<?> tileEntityTypeIn) 
	{
		super(tileEntityTypeIn);
	}
	
	
	
	@Override
	public void read(BlockState state, CompoundNBT compound) 
	{
		super.read(state, compound);
		this.readCustom(compound);
	}
	
	
	
	protected abstract void readCustom(CompoundNBT compound);
	
	
	
	@Override
	public CompoundNBT write(CompoundNBT compound) 
	{
		compound = super.write(compound);
		compound = writeCustom(compound);
		return compound;
	}
	
	
	
	protected abstract CompoundNBT writeCustom(CompoundNBT compound);
	
	
	
	@Override
	public CompoundNBT getUpdateTag() 
	{
		return write(new CompoundNBT());
	}
	
	
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() 
	{
        return new SUpdateTileEntityPacket(this.getPos(), 0, getUpdateTag());
	}
	
	
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) 
	{
		read(this.getBlockState() ,pkt.getNbtCompound());
	}
	
	
	
	/**
	 * Causes the Client to sync with the Server
	 */
	public void syncClient() 
	{
		if(!this.world.isRemote) this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 2 | 16);
	}
	
	
	
	public void triggerBlockUpdate() 
	{
		if(!this.world.isRemote) this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 1);
	}

	
	
	/**
	 * Creates a new Item Stack Handler with the given parameters
	 * 
	 * @param numSlots The number of slots this Item Stack Handler has
	 * @param stackLimit The Item Stack limit for each Item slot
	 * 
	 * @return A new Item Stack Handler with the given parameters
	 */
	protected ItemStackHandler makeItemHandler(int numSlots, int stackLimit) 
	{
		return new ItemStackHandler(numSlots) 
		{
			@Override
			public int getSlotLimit(int slot) 
			{
				int limit;
				if(Math.abs(stackLimit) < 1) limit = 1;
				else if(Math.abs(stackLimit) > 64) limit = 64;
				else limit = Math.abs(stackLimit);
				return limit;
			}
			
			
			
			@Override
			protected void onContentsChanged(int slot) 
			{
				markDirty();
			}
		};
	}
}







