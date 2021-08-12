package com.kilroy790.notenoughmachines.tiles;

import com.kilroy790.notenoughmachines.power.MechanicalType;

import net.minecraft.tileentity.TileEntityType;




public abstract class MechanicalGeneratorTile extends MechanicalTile 
{
	protected float speedModifier = 0.0f;
	
	
	
	public MechanicalGeneratorTile(TileEntityType<?> tileEntityTypeIn) 
	{
		super(0, 0, MechanicalType.SOURCE, tileEntityTypeIn);
	}

	
	
	@Override
	public void tick()
	{
		if (!this.world.isRemote()) 
		{
			if (world.getGameTime() % 40 == 1) 
			{
				this.updateSpeed();
				setCapacity((int)(getBasePowerCapacity() * Math.abs(this.speedModifier)));
			}
			if (this.isPowered()) 
				changeSpeed(this, this.getBaseSpeed() * (float)this.speedModifier);
		}
		super.tick();
	}
	
	
	
	/**
	 * 
	 */
	abstract protected void updateSpeed();
	
	
	
	@Override
	public boolean isPowered() 
	{
		return this.networkCapacity - this.networkLoad > 0;
	}
	
	
	
	abstract public float getBaseSpeed();
	
	
	
	abstract public int getBasePowerCapacity();
}



