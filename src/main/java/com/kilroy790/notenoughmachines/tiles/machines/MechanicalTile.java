package com.kilroy790.notenoughmachines.tiles.machines;

import java.util.ArrayList;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.api.power.MechanicalType;
import com.kilroy790.notenoughmachines.power.IHaveMechanicalPower;
import com.kilroy790.notenoughmachines.power.MechanicalInputOutput;
import com.kilroy790.notenoughmachines.tiles.AbstractBaseTile;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;




abstract public class MechanicalTile extends AbstractBaseTile implements ITickableTileEntity, IHaveMechanicalPower {

	protected long networkID = 0;
	protected int powerCapacity = 0;
	protected int powerLoad = 0;
	protected MechanicalType mechType = MechanicalType.CHANNEL;
	protected int networkCapacity = 0;
	protected int networkLoad = 0;
	
	private int validationTimer = 0;
	private final static int VALIDATE_TICK = 20;
	
	
	
	public MechanicalTile(int powerCapacity, int powerLoad, MechanicalType type, TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
		this.powerCapacity = powerCapacity;
		this.powerLoad = powerLoad;
		this.mechType = type;
	}
	
	
    /*
     * Called when this is first added to the world (by {@link World#addTileEntity(TileEntity)}).
     * Override instead of adding {@code if (firstTick)} stuff in update.
     * 
     * Note: super must be called at the end of the method
     */
	@Override
	public void onLoad() {
		
		if(!world.isRemote) {
			this.networkID = NotEnoughMachines.AETHER.joinPowerNetwork(this);
		}
		
		super.onLoad();
	}
	
	
	
	//TODO
	@Override
	public void tick() {
		
		if(!world.isRemote()) {
			if(this.validationTimer >= VALIDATE_TICK) validatePowerNetwork();
			else this.validationTimer++;
		}
		
		tickCustom();
	}
	
	
	
	/*
	 * There are ~20 tick per second
	 */
	abstract protected void tickCustom();
	
	
	
	//TODO
	protected void  validatePowerNetwork() {
		this.validationTimer = 0;
	}
	
	
	
	public void networkUpdate(int currentCapacity, int currentLoad) {
		this.networkCapacity = currentCapacity;
		this.networkLoad = currentLoad;
	}
	
	
	
	/*
	 * @return Gets a list of this machine's Inputs/Outputs
	 */
	abstract public ArrayList<MechanicalInputOutput> getMechIO();
	
	
	
	@Override
	protected void readCustom(CompoundNBT compound) {
		this.networkID = compound.getLong("netid");
		this.powerCapacity = compound.getInt("capacity");
		this.networkCapacity = compound.getInt("netcapacity");
		this.powerLoad = compound.getInt("load");
		this.networkLoad = compound.getInt("netload");
		this.mechType = MechanicalType.byName(compound.getString("mechtype"));
	}
	
	
	
	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) {
		compound.putLong("netid", this.networkID);
		compound.putInt("capacity", this.powerCapacity);
		compound.putInt("netcapacity", this.networkCapacity);
		compound.putInt("load", this.powerLoad);
		compound.putInt("netload", this.networkLoad);
		compound.putString("mechtype", this.mechType.getName());
		return compound;
	}
	
	
	
	/*
	 * @return This mechanical tile's power network ID or 0 if it does not have a network
	 */
	public long getNetworkID() {
		return this.networkID;
	}
	
	
	
	@Override
	public int getLoad() {
		return this.powerLoad;
	}
	
	
	
	@Override
	public int getCapacity() {
		return this.mechType == MechanicalType.SOURCE ? this.powerCapacity : 0;
	}
	
	
	
	@Override
	public boolean isPowered() {
		return this.networkCapacity - this.networkLoad > 0;
	}
	
	
	
	@Override
	public MechanicalType getMechType() {
		return this.mechType;
	}
}







