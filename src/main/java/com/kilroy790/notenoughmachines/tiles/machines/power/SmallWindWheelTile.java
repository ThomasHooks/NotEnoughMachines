package com.kilroy790.notenoughmachines.tiles.machines.power;

import java.util.ArrayList;
import java.util.Map;

import com.kilroy790.notenoughmachines.api.lists.BlockList;
import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalHorizontalBlock;
import com.kilroy790.notenoughmachines.blocks.machines.power.SmallWindWheelBlock;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;
import com.kilroy790.notenoughmachines.utilities.MachineIOList;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;




public class SmallWindWheelTile extends MechanicalTile {

	protected float angle = 0.0f;
	protected float speedModifier = 1.0f;
	protected boolean isRotating = true;
	
	private Map<Direction.Axis, ArrayList<MechanicalContext>> io;
	
	public static final int WINDWHEEL_RADIUS = 7;
	
	
	
	public SmallWindWheelTile() {
		super(200, 0, MechanicalType.SOURCE, TileEntityList.SMALLWINDWHEEL);
	}
	
	
	
	@Override
	public void onLoad() {
		io = MachineIOList.monoAxle(pos);
		super.onLoad();
	}



	@Override
	protected void tickCustom() {
		
		if(!world.isRemote()) {
			if(world.getGameTime()%40 == 1) this.isRotating = validateArea();
			
			if(isRotating) {
				updateWindWheelAngle();
				//setCapacity((int)(200 * this.speedModifier));
				changeSpeed(this, 20.0f * this.speedModifier);
			}
			else changeSpeed(this, 0.0f);//setCapacity(0);
		}
	}
	
	
	
	//TODO: make the aabb determined on the wind wheel's direction
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB((double)pos.getX() - (7.0f * 16.0f), 
								 (double)pos.getY() - (7.0f * 16.0f), 
								 (double)pos.getZ() - (7.0f * 16.0f), 
								 (double)pos.getX() + (7.0f * 16.0f), 
								 (double)pos.getY() + (7.0f * 16.0f), 
								 (double)pos.getZ() + (7.0f * 16.0f));
	}
	
	
	
	protected void updateWindWheelAngle() {
		
		//Modify wind wheel speed based on the weather
		if(world.getGameTime()%40 == 1) {
			if(world.isThundering()) speedModifier = 2.0f;
			else if(world.isRaining()) speedModifier = 1.25f;
			else speedModifier = 1.0f;
		}
		
		this.angle += 4.0f * speedModifier;
		
		if(this.angle > 360.0f)this.angle = 0.0f;
		
		syncClient();
	}
	
	
	
	/**
	 * @return True if the area is valid
	 */
	public boolean validateArea() {
		
		
		boolean valid = false;
		if(world.canBlockSeeSky(pos) && world.getBlockState(pos).getBlock() == BlockList.SMALLWINDWHEEL) {
			
			Direction direction = this.getBlockState().get(SmallWindWheelBlock.FACING);
			for(int y = -WINDWHEEL_RADIUS; y <= WINDWHEEL_RADIUS; y++) {
				
				for(int hor = -WINDWHEEL_RADIUS; hor <= WINDWHEEL_RADIUS; hor++) {
					
					int x = 0;
					int z = 0;
					
					//Z = north & south
					//Y = up & down
					//X = east & west
					if(direction == Direction.NORTH || direction == Direction.SOUTH) x = hor;
					else z = hor;
					
					BlockPos nextPos = pos.add(x, y, z);
					
					if(x == 0 && y == 0 && z == 0) continue;
					
					valid = world.isAirBlock(nextPos);
					if(!valid) return false;
				}
			}
		}
		return valid;
	}
	
	
	
	/**
	 * @return The angle of the wind sail in radians
	 */
	public float getWindSailAngle() {
		return ((float)Math.PI * this.angle)/180.0f;
	}
	
	
	
	@Override
	protected void readCustom(CompoundNBT compound) {
		this.angle = compound.getFloat("angle");
		this.isRotating = compound.getBoolean("rotating");
		super.readCustom(compound);
	}
	
	
	
	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) {
		compound.putFloat("angle", this.angle);
		compound.putBoolean("rotating", this.isRotating);
		return super.writeCustom(compound);
	}



	@Override
	public ArrayList<MechanicalContext> getIO() {
		return io.get(getBlockState().get(MechanicalHorizontalBlock.FACING).getAxis());
	}
}







