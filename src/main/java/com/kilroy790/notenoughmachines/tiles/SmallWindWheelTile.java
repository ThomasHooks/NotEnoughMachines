package com.kilroy790.notenoughmachines.tiles;

import com.kilroy790.notenoughmachines.blocks.NEMBlocks;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalHorizontalBlock;
import com.kilroy790.notenoughmachines.blocks.machines.SmallWindWheelBlock;
import com.kilroy790.notenoughmachines.power.MechanicalType;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class SmallWindWheelTile extends MechanicalTile 
{
	protected float speedModifier = 1.0f;
	private static final int BASE_POWER_CAPACITY = 200;
	private static final float BASE_SPEED = 12.0f;
	public static final int WINDWHEEL_RADIUS = 8;
	
	
	
	public SmallWindWheelTile() 
	{
		super(0, 0, MechanicalType.SOURCE, NEMTiles.SMALLWINDWHEEL.get());
	}
	
	
	
	@Override
	public void tick() 
	{
		if (!this.world.isRemote()) 
		{
			if (world.getGameTime() % 40 == 1) 
			{
				updateWindSpeed();
				setCapacity((int)(BASE_POWER_CAPACITY * Math.abs(speedModifier)));
			}
			if (this.isPowered()) 
				changeSpeed(this, BASE_SPEED * (float)speedModifier);
		}
		super.tick();
	}
	
	
	
	protected void updateWindSpeed() {
		
		if (!isAreaValid()) 
		{
			this.stop();
			return;
		}
		switch (this.getBlockState().get(MechanicalHorizontalBlock.FACING)) 
		{
		case EAST:
		case NORTH:
			if (world.isThundering()) this.speedModifier = 2.0f;
			else if (world.isRaining()) this.speedModifier = 1.33f;
			else this.speedModifier = 1.0f;
			break;
			
		case SOUTH:
		case WEST:
			if (world.isThundering()) this.speedModifier = -2.0f;
			else if (world.isRaining()) this.speedModifier = -1.33f;
			else this.speedModifier = -1.0f;
			break;
			
		default:
			throw new IllegalStateException(this.getType().getRegistryName() + " is in an unknow BlockState for property 'FACING'");
		}
	}
	
	
	
	/**
	 * Checks if there are blocks blocking this wind wheel's path, and that the wind wheel is above ground
	 * 
	 * @return True if the wind wheel is unobstructed
	 */
	public boolean isAreaValid() 
	{
		boolean valid = false;
		if (world.canBlockSeeSky(pos) && world.getBlockState(pos).getBlock() == NEMBlocks.SMALLWINDWHEEL.get()) 
		{
			Direction direction = this.getBlockState().get(SmallWindWheelBlock.FACING);
			for (int y = -WINDWHEEL_RADIUS; y <= WINDWHEEL_RADIUS; y++) 
			{
				for (int hor = -WINDWHEEL_RADIUS; hor <= WINDWHEEL_RADIUS; hor++) 
				{
					int x = 0;
					int z = 0;
					if (direction == Direction.NORTH || direction == Direction.SOUTH) 
						x = hor;
					else 
						z = hor;
					
					BlockPos nextPos = pos.add(x, y, z);
					if (x == 0 && y == 0 && z == 0) 
						continue;
					
					valid = world.isAirBlock(nextPos);
					if (!valid) 
						return false;
				}
			}
		}
		return valid;
	}
	
	
	
	/**
	 * Causes this wind wheel to temporarily stop rotating
	 */
	public void stop()
	{
		this.speedModifier = 0.0f;
	}



	@Override
	protected void readCustom(CompoundNBT compound) 
	{
		this.speedModifier = compound.getFloat("speedfactor");
		super.readCustom(compound);
	}



	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) 
	{
		compound.putFloat("speedfactor", this.speedModifier);
		return super.writeCustom(compound);
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() 
	{
		return new AxisAlignedBB(getPos()).grow((double)WINDWHEEL_RADIUS);
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public double getMaxRenderDistanceSquared() 
	{
		return 4096.0D * 4.0D;
	}
}







