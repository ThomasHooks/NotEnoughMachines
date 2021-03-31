package com.kilroy790.notenoughmachines.tiles;

import java.util.ArrayList;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalBlock;
import com.kilroy790.notenoughmachines.power.IHaveMechanicalPower;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.power.MechanicalType;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;




abstract public class MechanicalTile extends NEMBaseTile implements ITickableTileEntity, IHaveMechanicalPower 
{
	protected long networkID = 0;
	private int powerCapacity = 0;
	private int powerLoad = 0;
	protected int networkCapacity = 0;
	protected int networkLoad = 0;
	protected MechanicalType mechType = MechanicalType.SHAFT;
	private float speed = 0.0f;
	protected BlockPos driverPos = null;
	private int powerNetworkTimer = 0;
	protected final static int VALIDATE_TICK = 20;
	private boolean updateNetwork = false;
	private boolean speedChanged = false;
	
	
	
	public MechanicalTile(int powerCapacity, int powerLoad, MechanicalType type, TileEntityType<?> tileEntityTypeIn) 
	{
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
	public void onLoad() 
	{
		if (!world.isRemote) 
		{
			NotEnoughMachines.AETHER.joinPowerNetwork(this);
		}
		super.onLoad();
	}
	
	
	
	@Override
	public void remove() 
	{
//		if (!world.isRemote) 
//		{
//			NotEnoughMachines.AETHER.removeFromPowerNetwork(this);
//		}
		super.remove();
	}
	
	
	
	/**
	 * This method will be called by both the server and all clients once per tick. There are about 20 tick per second.
	 * <p> 
	 * If overridden the super must be called.
	 */
	@Override
	public void tick() 
	{		
		if (!world.isRemote()) 
		{
			if (this.powerNetworkTimer > VALIDATE_TICK) 
			{
				updatePowerNetwork();
				this.powerNetworkTimer = 0;
			}
			else 
				this.powerNetworkTimer++;
			
			if (!isPowered()) 
			{
				this.speed = 0;
				markDirty();
				syncClient();
			}
			propagateSpeed();
		}
	}
	
	
	
	/**
	 * Updates this machine's power network if its power state has changed
	 */
	private void  updatePowerNetwork() 
	{
		if (this.updateNetwork) 
		{
			NotEnoughMachines.AETHER.updatePowerNetwork(this);
			this.updateNetwork = false;
			markDirty();
		}
	}
	
	
	
	/**
	 * Attempts to drive the next machine that is attached to this machine
	 */
	private void propagateSpeed() 
	{
		if (speedChanged) 
		{
			MechanicalBlock block = (MechanicalBlock)getBlockState().getBlock();
			for (MechanicalContext io : block.getIO(world, pos, getBlockState())) 
			{
				MechanicalBlock neighborBlock = MechanicalBlock.getMechanicalBlock(world, io.getPos());
				if (neighborBlock != null) 
				{
					MechanicalTile neighbor = neighborBlock.getTile(world, io.getPos(), world.getBlockState(io.getPos()));
					if (neighbor.getPos().equals(this.driverPos)) 
						continue;
					else if (neighborBlock.isAlignedWith(neighbor.world, neighbor.pos, neighbor.getBlockState(), io)) 
					{
						if (io.isAxle()) 
						{
							neighbor.changeSpeed(this, this.speed);
						}
						else 
						{
							float gearRatio = this.numberOfTeeth() / neighbor.numberOfTeeth();
							neighbor.changeSpeed(this, this.speed * -gearRatio);
						}
					}
				}
			}
		}
	}
	
	
	
	/**
	 * Sets the number of teeth for this machine
	 * <p>
	 * Note: This method is only used by machines with cog mechanical connections
	 * 
	 * @return The number of teeth of this machine, by default it is 1
	 */
	protected float numberOfTeeth()
	{
		return 1.0f;
	}
	
	
	
	/**
	 * Increases the power capacity of this machine by the given amount
	 * 
	 * @param capacity The amount to increase by
	 */
	protected void increaseCapacity(int capacity) 
	{
		if (capacity == 0) 
			return;
		
		this.powerCapacity += Math.abs(capacity);
		this.updateNetwork = true;
	}
	
	
	
	/**
	 * Decreases the power capacity of this machine by the given amount
	 * 
	 * @param capacity The amount to decrease by
	 */
	protected void decreaseCapacity(int capacity) 
	{
		if (capacity == 0) 
			return;
		
		this.powerCapacity -= Math.abs(capacity);
		if (this.powerCapacity < 0) this.powerCapacity = 0;
		this.updateNetwork = true;
	}
	
	
	
	/**
	 * Sets the power capacity of this machine to the given amount
	 * 
	 * @param capacity This machine's new power capacity
	 */
	protected void setCapacity(int capacity) 
	{
		if (capacity == this.powerCapacity) 
			return;
		
		this.powerCapacity = Math.abs(capacity);
		this.updateNetwork = true;
	}
	
	
	
	/**
	 * Increases the power load of this machine by the given amount
	 * 
	 * @param load The amount to increase by
	 */
	protected void increaseLoad(int load) 
	{
		if (load == 0) 
			return;
		
		this.powerLoad += Math.abs(load);
		this.updateNetwork = true;
	}
	
	
	
	/**
	 * Decreases the power load of this machine by the given amount
	 * 
	 * @param load The amount to decrease by
	 */
	protected void decreaseLoad(int load) 
	{
		if (load == 0) 
			return;
		
		this.powerLoad -= Math.abs(load);
		if (this.powerLoad < 0) 
			this.powerLoad = 0;
		
		this.updateNetwork = true;
	}
	
	
	
	/**
	 * Sets the power load of this machine to the given amount
	 * 
	 * @param load This machine's new power load
	 */
	protected void setLoad(int load) 
	{
		if (load == this.powerLoad) 
			return;
		
		this.powerLoad = Math.abs(load);
		this.updateNetwork = true;
	}
	
	
	
	/**
	 * Gets an array of neighboring machines that are attached to this machine.
	 * It is possible that the returned array is empty if there are not neighboring machines. 
	 * <p>
	 * Use {@link MechanicalBlock#getNeighbors(World, BlockPos, BlockState)} instead
	 * 
	 * @return An array of neighboring machines to this machine
	 */
	@Deprecated
	public ArrayList<MechanicalTile> getNeighbors() 
	{
		ArrayList<MechanicalTile> neighbors = new ArrayList<MechanicalTile>();
		MechanicalBlock block = (MechanicalBlock)getBlockState().getBlock();
		for (MechanicalContext io : block.getIO(world, pos, getBlockState())) 
		{
			TileEntity tile = world.getTileEntity(io.getPos());
			MechanicalTile mechTile = tile instanceof MechanicalTile ? (MechanicalTile)tile : null;
			if (mechTile != null) 
			{
				if (mechTile.isAlignedWith(io.getFacing(), io.isAxle())) 
					neighbors.add(mechTile);
			}
		}
		return neighbors;
	}
	
	
	
	/**
	 * Checks if the given direction and Input/Output type is aligned with this machine.
	 * <p>
	 * Use {@link MechanicalBlock#isAlignedWith(World, BlockPos, BlockState, MechanicalContext)} instead
	 * 
	 * @param facing The direction that is being checked for alignment with this machine
	 * @param isAxle If the Mechanical I/O is not an axle it is assumed to be a cog I/O
	 * 
	 * @return True if the given direction is aligned with this machine
	 */
	@Deprecated
	public boolean isAlignedWith(Direction facing, boolean isAxle) 
	{
		MechanicalBlock block = (MechanicalBlock)getBlockState().getBlock();
		for (MechanicalContext io : block.getIO(world, pos, getBlockState())) 
		{
			if (io.getFacing() == facing.getOpposite() && io.isAxle() == isAxle) 
				return true;
		}
		return false;
	}
	
	
	
	@Override
	protected void readCustom(CompoundNBT compound) 
	{
		this.networkID = compound.getLong("netid");
		this.powerCapacity = compound.getInt("capacity");
		this.networkCapacity = compound.getInt("netcapacity");
		this.powerLoad = compound.getInt("load");
		this.networkLoad = compound.getInt("netload");
		this.speed = compound.getFloat("speed");
		this.mechType = MechanicalType.byName(compound.getString("mechtype"));
		if (compound.contains("driverpos")) 
			this.driverPos = NBTUtil.readBlockPos(compound.getCompound("driverpos"));
	}
	
	
	
	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) 
	{
		compound.putLong("netid", this.networkID);
		compound.putInt("capacity", this.powerCapacity);
		compound.putInt("netcapacity", this.networkCapacity);
		compound.putInt("load", this.powerLoad);
		compound.putInt("netload", this.networkLoad);
		compound.putFloat("speed", this.speed);
		compound.putString("mechtype", this.mechType.getName());
		if (this.driverPos != null) 
			compound.put("driverpos", NBTUtil.writeBlockPos(this.driverPos));
		
		return compound;
	}
	
	
	
	/**
	 * Updates this machine with the power networks new state.
	 * This method should only be called by this machine's power network.
	 * 
	 * @param currentCapacity The new power capacity of the power network
	 * @param currentLoad The new power load of the power network
	 */
	public void networkUpdate(int currentCapacity, int currentLoad) 
	{
		this.networkCapacity = currentCapacity;
		this.networkLoad = currentLoad;
		markDirty();
		syncClient();
	}
	
	
	
	/**
	 * @return This mechanical tile's power network ID or 0 if it does not have a network.
	 */
	public long getNetworkID() 
	{
		return this.networkID;
	}
	
	
	
	/**
	 * Attaches this tile to a new power network.
	 * This method should only be called by this machine's power network or a power network that is adding this machine.
	 * 
	 * @param id The new power network ID for this tile
	 */
	public void setNetworkID(long id) 
	{
		if (id > 0 && this.networkID != id) 
		{
			this.networkID = id;
			markDirty();
		}
	}
	
	
	
	@Override
	public int getLoad() 
	{
		return this.powerLoad;
	}
	
	
	
	@Override
	public int getCapacity() 
	{
		return this.mechType == MechanicalType.SOURCE ? this.powerCapacity : 0;
	}
	
	
	
	@Override
	public float getSpeed() 
	{
		return this.speed;
	}
	
	
	
	@Override
	public void changeSpeed(MechanicalTile driver, float speedIn) 
	{
		if (speedIn == this.speed && world.getGameTime() % 10 != 1)  
			return;
		
		this.speed = speedIn;
		this.driverPos = driver.getPos();
		this.speedChanged = true;
		markDirty();
		syncClient();
	}
	
	
	
	@Override
	public float getTorque() 
	{
		return getSpeed() != 0.0f ? 9.5488f * this.networkCapacity/getSpeed() : 0;
	}
	
	
	
	@Override
	public boolean isPowered() 
	{
		return this.networkCapacity - this.networkLoad > 0;
	}
	
	
	
	@Override
	public MechanicalType getMachineType() 
	{
		return this.mechType;
	}
}







