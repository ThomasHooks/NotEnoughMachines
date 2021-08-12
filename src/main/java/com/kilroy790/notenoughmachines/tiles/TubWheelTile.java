package com.kilroy790.notenoughmachines.tiles;

import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class TubWheelTile extends MechanicalGeneratorTile 
{
	public TubWheelTile() 
	{
		super(NEMTiles.TUBWHEEL.get());
		this.speedModifier = 0.0f;
	}



	@Override
	protected void updateSpeed() 
	{
		this.speedModifier = 0;
		
		for (Direction dir : Direction.Plane.HORIZONTAL) 
		{
			BlockPos fluidPos = pos.offset(dir);
			FluidState fluid = world.getFluidState(fluidPos);
			Vector3d vec = fluid.getFlow(world, fluidPos);
			
			/*
			 * 				North (-Z)
			 * 
			 * West (-X)					East (+X)
			 * 
			 * 				South (+Z)
			 * 
			 * Because Counter Clockwise is positive rotation, the flow for both South and West must be negated
			 */
			switch (dir) 
			{
			case EAST:
				this.speedModifier += Math.signum(vec.getZ());
				break;
				
			case NORTH:
				this.speedModifier += Math.signum(vec.getX());
				break;
				
			case SOUTH:
				this.speedModifier += -Math.signum(vec.getX());
				break;
				
			case WEST:
				this.speedModifier += -Math.signum(vec.getZ());
				break;
				
			default:
				break;
			}
		}
	}
	
	
	
	@Override
	protected void readCustom(CompoundNBT compound) 
	{
		super.readCustom(compound);
		this.speedModifier = compound.getFloat("speedModifier");
	}
	
	
	
	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) 
	{
		compound.putFloat("speedModifier", this.speedModifier);
		return super.writeCustom(compound);
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() 
	{
		return new AxisAlignedBB(getPos()).grow(1.0D);
	}



	@Override
	public float getBaseSpeed() 
	{
		return 11.1f;
	}



	@Override
	public int getBasePowerCapacity() 
	{
		return 20;
	}
}



