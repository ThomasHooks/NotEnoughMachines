package com.kilroy790.notenoughmachines.tiles.machines;

import java.util.ArrayList;
import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.api.power.MechanicalType;
import com.kilroy790.notenoughmachines.power.IHaveMechanicalPower;
import com.kilroy790.notenoughmachines.power.MechanicalInputOutput;
import com.kilroy790.notenoughmachines.tiles.NEMBaseTile;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;




abstract public class MechanicalTile extends NEMBaseTile implements ITickableTileEntity, IHaveMechanicalPower {

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
	
	
	
    /**
     * Called when this is first added to the world (by {@link World#addTileEntity(TileEntity)}).
     * Override instead of adding {@code if (firstTick)} stuff in update.
     * <p>
     * Note: super must be called at the end of the method
     */
	@Override
	public void onLoad() {
		
		if(!world.isRemote) {
			NotEnoughMachines.AETHER.joinPowerNetwork(this);
		}
		
		super.onLoad();
	}
	
	
	
	@Override
	public void remove() {
		
		if(!world.isRemote) {
			NotEnoughMachines.AETHER.removeFromPowerNetwork(this);
		}
		
		super.remove();
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
	
	
	
	/**
	 * This method will be called by both the server and the client once per tick. There are about 20 tick per second.
	 */
	abstract protected void tickCustom();
	
	
	
	//TODO
	protected void  validatePowerNetwork() {
		this.validationTimer = 0;
	}
	
	
	
	/**
	 * Gets an array of this machine's Mechanical Inputs/Outputs
	 * 
	 * @return An array of this machine's I/O
	 */
	abstract public ArrayList<MechanicalInputOutput> getMechIO();
	
	
	
	/**
	 * Gets an array of neighboring machines that are aligned to this machine.
	 * It is possible that the returned array is empty.
	 * 
	 * @return An array of neighboring machines to this machine
	 */
	public ArrayList<MechanicalTile> getNeighbors() {
		
		ArrayList<MechanicalTile> neighbors = new ArrayList<MechanicalTile>();
		for(MechanicalInputOutput io : getMechIO()) {
			
			TileEntity tile = world.getTileEntity(io.getPos());
			MechanicalTile mechTile = tile instanceof MechanicalTile ? (MechanicalTile)tile : null;
			if(mechTile != null) {
				if(mechTile.isAlignedWith(io.getFacing(), io.isAxle())) neighbors.add(mechTile);
			}
		}
		return neighbors;
	}
	
	
	
	/**
	 * Checks if the given direction and Input/Output type is aligned with this machine.
	 * 
	 * @param facing The direction that is being checked for alignment with this machine
	 * @param isAxle If the Mechanical I/O is not an axle it is assumed to be a cog I/O
	 * 
	 * @return True if the given direction is aligned with this machine
	 */
	public boolean isAlignedWith(Direction facing, boolean isAxle) {
		
		for(MechanicalInputOutput io : getMechIO()) {
			if(io.getFacing() == facing.getOpposite() && io.isAxle() == isAxle) return true;
		}
		return false;
	}
	
	
	
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
	
	
	
	/**
	 * Updates this machine with the power networks new state.
	 * This method should only be called by this machine's power network.
	 * 
	 * @param currentCapacity The new power capacity of the power network
	 * @param currentLoad The new power load of the power network
	 */
	public void networkUpdate(int currentCapacity, int currentLoad) {
		this.networkCapacity = currentCapacity;
		this.networkLoad = currentLoad;
		markDirty();
	}
	
	
	
	/**
	 * @return This mechanical tile's power network ID or 0 if it does not have a network.
	 */
	public long getNetworkID() {
		return this.networkID;
	}
	
	
	
	/**
	 * Attaches this tile to a new power network.
	 * This method should only be called by this machine's power network or a power network that is adding this machine.
	 * 
	 * @param id The new power network ID for this tile
	 */
	public void setNetworkID(long id) {
		
		if(id > 0 && this.networkID != id) {
			this.networkID = id;
			markDirty();
		}
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







