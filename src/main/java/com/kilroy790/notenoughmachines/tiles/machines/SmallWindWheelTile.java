package com.kilroy790.notenoughmachines.tiles.machines;

import com.kilroy790.notenoughmachines.api.lists.BlockList;
import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.api.power.CapabilityMechanical;
import com.kilroy790.notenoughmachines.api.power.IMechanicalPower;
import com.kilroy790.notenoughmachines.api.power.MechanicalPowerProducer;
import com.kilroy790.notenoughmachines.blocks.machines.SmallWindWheelBlock;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;




public class SmallWindWheelTile extends TileEntity implements ITickableTileEntity{

	
	protected float angle = 0.0f;
	
	protected float speedModifier = 1.0f;
	
	protected boolean isRotating = true;
	
	private static final int POWERCAPACITY = 11520;
	private static final int MAXPOWERRECEIVED = 0;
	private static final int MAXPOWERSENT = 288;
	private static final int POWERPRODUCED = 144;
	private MechanicalPowerProducer powerOutput;
	private LazyOptional<IMechanicalPower> powerOutputHandler = LazyOptional.of(() -> powerOutput);
	
	public static final int WINDWHEEL_RADIUS = 7;
	
	
	public SmallWindWheelTile() {
		super(TileEntityList.SMALLWINDWHEEL);
		
		powerOutput = makeMechanicalPowerHandler(POWERCAPACITY, MAXPOWERRECEIVED, MAXPOWERSENT);
	}
	
	
	@Override
	public void tick() {
		//there are ~20 tick per second
		
		if(world.getGameTime()%40 == 1) {
			
			//if(world.isRemote) return;
			
			isRotating = validateArea();
		}
		
		if(isRotating) {
			
			updateWindWheelAngle();
			
			generatePower();
		}
	}
	
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		//TODO make the aabb determined on the wind wheel's direction
		//this works for the moment but it could be made more efficient as the aabb is a large cube around where the wind wheel could be
		AxisAlignedBB boundingBox = new AxisAlignedBB((double)pos.getX() - (7.0f * 16.0f), (double)pos.getY() - (7.0f * 16.0f), (double)pos.getZ() - (7.0f * 16.0f), 
													  (double)pos.getX() + (7.0f * 16.0f), (double)pos.getY() + (7.0f * 16.0f), (double)pos.getZ() + (7.0f * 16.0f));
		return boundingBox;
	}
	
	
	protected void updateWindWheelAngle() {
		
		//TODO only update on the client side
		//if(world.isRemote) return;
		
		//Modify wind wheel speed based on the weather
		if(world.isRaining()) speedModifier = 1.0f + world.getThunderStrength(1.0f);
		else speedModifier = 1.0f;
		
		this.angle += 4.0f * speedModifier;
		
		if(this.angle > 360.0f)this.angle = 0.0f;
	}
	
	
	protected void generatePower() {
		
		//only do on the server side
		if(world.isRemote) return;
		
		powerOutput.producePower(POWERPRODUCED * (int)speedModifier, false);
		
		//check if the block behind has a tile entity that has a mechanical power capability
		//then send that block power if it can also receive power
		Direction facing = this.getBlockState().get(SmallWindWheelBlock.FACING).getOpposite();
		if(world.getBlockState(pos.offset(facing)).hasTileEntity()){
			LazyOptional<IMechanicalPower> cap = world.getTileEntity(pos.offset(facing)).getCapability(CapabilityMechanical.MECHANICAL, facing.getOpposite());
			
			cap.ifPresent(h -> {
				if(h.canReceive()) {
					//send power to the receiver
					int overFlow = MAXPOWERSENT - h.receivePower(powerOutput.sendPower(POWERPRODUCED * (int)speedModifier, false), false);
					
					//add the overflow power back into the power source
					powerOutput.producePower(overFlow, false);
				}
			});
		}
	}
	
	
	public boolean validateArea() {
		//@return true if the area is valid, false otherwise
		
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
	
	
	public float getWindSailAngle() {
		//returns the angle of the wind sail in radians
		return ((float)Math.PI * this.angle)/180.0f;
	}
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		
		if(cap == CapabilityMechanical.MECHANICAL) {
			
			//return powerOutputHandler.cast();
		}
		
		return super.getCapability(cap, side);
	}
	
	
	@Override
	public void read(CompoundNBT compound) {
		
		if(compound.contains("storedpower")) {
			powerOutputHandler.ifPresent(h -> {
				h.setEnergyStored(compound.getInt("storedpower"));
			});
		}
		
		super.read(compound);
	}
	
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		
		powerOutputHandler.ifPresent(h -> {
			compound.putInt("storedpower", h.getEnergyStored());
		});
		
		return super.write(compound);
	}
	
	
	@Override
	public void remove() {
		super.remove();
		this.powerOutputHandler.invalidate();
	}
	
	
	private MechanicalPowerProducer makeMechanicalPowerHandler(int capacity, int maxReceived, int maxSent) {
		
		return new MechanicalPowerProducer(capacity, maxReceived, maxSent);
	}
}
