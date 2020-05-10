package com.kilroy790.notenoughmachines.tiles.machines;

import java.util.ArrayList;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.api.power.IMechanicalPower;
import com.kilroy790.notenoughmachines.api.power.MechanicalPower;
import com.kilroy790.notenoughmachines.api.power.MechanicalType;
import com.kilroy790.notenoughmachines.power.MechanicalInputOutputWrapper;
import com.kilroy790.notenoughmachines.tiles.AbstractBaseTile;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.util.LazyOptional;




abstract public class MechanicalTile extends AbstractBaseTile implements ITickableTileEntity {

	protected MechanicalPower mechPower;
	protected LazyOptional<IMechanicalPower> mechPowerCap = LazyOptional.of(() -> mechPower);
	
	protected long networkID = 0;
	
	protected int powerCapacity;
	protected int powerLoad;
	protected MechanicalType mechType;
	
	protected int validationTimer;
	protected final static int VALIDATE_TICK = 20;
	
	
	
	public MechanicalTile(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	
	
	//TODO
	protected void initialize(int powerCapacityIn, int powerLoadIn, int maxPowerReceivedIn, int maxPowerSentIn, MechanicalType mechTypeIn) {
		this.powerCapacity = powerCapacityIn;
		this.powerLoad = powerLoadIn;
		this.mechPower = new MechanicalPower(powerCapacityIn, maxPowerReceivedIn, maxPowerSentIn, mechTypeIn);
		
		this.networkID = NotEnoughMachines.AETHER.joinPowerNetwork(this);
	}
	
	
	
	@Override
	//TODO
	public void tick() {
		if(!world.isRemote()) {
			if(this.validationTimer == VALIDATE_TICK) {
				validatePowerNetwork();
				this.validationTimer = 0;
			}
			else this.validationTimer++;
		}
	}
	
	
	
	//TODO
	protected void  validatePowerNetwork() {

	}
	
	
	
	public void networkUpdate(int currentCapacity, int currentLoad) {
		this.powerCapacity = currentCapacity;
		this.powerLoad = currentLoad;
	}
	
	
	
	abstract public ArrayList<MechanicalInputOutputWrapper> getMechIO();
	
	
	
	public MechanicalType getMechType() {
		return this.mechPower.getMechType();
	}
	
	
	
	/*
	 * @return This mechanical tile's power network ID or 0 if it does not have a network
	 */
	public long getNetworkID() {
		return this.networkID;
	}
	
	
	
	public int getLoad() {
		return this.powerLoad;
	}
	
	
	
	public int getCapacity() {
		return this.mechPower.getMaxCapacity();
	}
}
