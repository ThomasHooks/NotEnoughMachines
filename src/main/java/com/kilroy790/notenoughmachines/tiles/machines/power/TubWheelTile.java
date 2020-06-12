package com.kilroy790.notenoughmachines.tiles.machines.power;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;
import com.kilroy790.notenoughmachines.utilities.MachineIOList;

import net.minecraft.fluid.IFluidState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class TubWheelTile extends MechanicalTile {
	
	private ArrayList<MechanicalContext> io;
	
	private static final int BASE_POWER_CAPACITY = 20;
	private static final float BASE_SPEED = 11.1f;
	private Map<Direction, Double> waterFlow = new HashMap<Direction, Double>();
	private double totalFlow = 0;
	
	

	public TubWheelTile() {
		super(BASE_POWER_CAPACITY, 0, MechanicalType.SOURCE, NEMTiles.TUBWHEEL.get());
		
		for(Direction dir : Direction.Plane.HORIZONTAL) {
			this.waterFlow.put(dir, 0.0D);
		}
	}
	
	
	
	@Override
	public void onLoad() {
		this.io = MachineIOList.monoAxle(getPos(), Direction.Axis.Y);
		super.onLoad();
	}

	
	
	@Override
	protected void tickCustom() {
		
		if(!this.world.isRemote()) {
			
			if(world.getGameTime() % 40 == 1) {
				updateWaterFlow();
				setCapacity((int)(BASE_POWER_CAPACITY * totalFlow));
			}
			
			if(this.isPowered()) changeSpeed(this, BASE_SPEED * (float)totalFlow);
		}
	}
	
	
	
	private void updateWaterFlow() {
		
		for(Direction dir : Direction.Plane.HORIZONTAL) {
			BlockPos fluidPos = pos.offset(dir);
			IFluidState fluid = world.getFluidState(fluidPos);
			Vec3d vec = fluid.getFlow(world, fluidPos);
			Vec3d flux = new Vec3d(Math.signum(vec.getX()), Math.signum(vec.getY()), Math.signum(vec.getZ()));
			
			/*
			 * 				North (-Z)
			 * 
			 * West (-X)					East (+X)
			 * 
			 * 				South (+Z)
			 * 
			 * Because Counter Clockwise is positive rotation, the flow for both South and West must be negated
			 */
			switch(dir) {
			case EAST:
				//changeWaterFlow(dir, flux.getZ());
				this.waterFlow.put(dir, flux.getZ());
				break;
				
			case NORTH:
				//changeWaterFlow(dir, flux.getX());
				this.waterFlow.put(dir, flux.getX());
				break;
				
			case SOUTH:
				//changeWaterFlow(dir, -flux.getX());
				this.waterFlow.put(dir, -flux.getX());
				break;
				
			case WEST:
				//changeWaterFlow(dir, -flux.getZ());
				this.waterFlow.put(dir, -flux.getZ());
				break;
				
			default:
				this.waterFlow.put(dir, 0.0D);
				break;
			
			}
		}
		
		this.totalFlow = 0;
		for(Direction dir : Direction.Plane.HORIZONTAL) {
			this.totalFlow += this.waterFlow.get(dir);
		}
	}
	
	
	
	/**
	 * Updates the water flow rate for the given direction facing away from the Tub Wheel.
	 * 
	 * @param dir The direction facing away from the Tub Wheel
	 * @param flux The water's flow rate
	 */
	public void changeWaterFlow(Direction dir, double flux) {
		this.waterFlow.put(dir, flux);
	}
	
	
	
	@Override
	protected void readCustom(CompoundNBT compound) {
		super.readCustom(compound);
		this.totalFlow = compound.getDouble("totalflow");
	}
	
	
	
	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) {
		compound.putDouble("totalflow", this.totalFlow);
		return super.writeCustom(compound);
	}

	
	
	@Override
	public ArrayList<MechanicalContext> getIO() {
		return io;
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos()).grow(1.0D);
	}
}







