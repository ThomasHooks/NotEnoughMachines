package com.kilroy790.notenoughmachines.tiles.machines;

import com.kilroy790.notenoughmachines.api.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.api.power.CapabilityMechanical;
import com.kilroy790.notenoughmachines.api.power.IMechanicalPower;
import com.kilroy790.notenoughmachines.api.power.MechanicalPowerConduit;
import com.kilroy790.notenoughmachines.blocks.machines.AxleBlock;
import com.kilroy790.notenoughmachines.blocks.machines.CreativePowerBoxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.GearboxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.MillstoneBlock;

import net.minecraft.block.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;




public class AxleTile extends TileEntity implements ITickableTileEntity {

	
	private MechanicalPowerConduit powerChannel;
	private LazyOptional<IMechanicalPower> powerChannelHandler = LazyOptional.of(() -> powerChannel);
	//there are ~20 tick per second
	private static final int POWERCAPACITY = 60;
	private static final int MAXPOWERRECEIVED = 60;
	private static final int MAXPOWERSENT = 60;
	
	
	public AxleTile() {
		super(TileEntityList.AXLE_TILE);
		
		this.powerChannel = makeMechanicalPowerHandler(POWERCAPACITY, MAXPOWERRECEIVED, MAXPOWERSENT);
	}

	
	@Override
	public void tick() {
		
		if(world.isRemote) return;
		
		if(powerChannel.isPowered()) {
			
			sendPowerToNextMachine();
		}
	}
	
	
	private void sendPowerToNextMachine() {

		int axleDir = this.getBlockState().get(NEMBlockStateProperties.AXLE_DIRECTION);
		int axleDist = this.getBlockState().get(NEMBlockStateProperties.POWER_DISTANCE_3_15);
		
		int powerLevel[] = new int[2];
		powerLevel[0] = AxleBlock.MINPOWERDISTANCE;
		powerLevel[1] = AxleBlock.MINPOWERDISTANCE;
		
		//loop over all machine that are bordering the axle and get their power distance level
		for(int i = 0; i < 2; i++) {
			
			Direction direction = AxleBlock.axisAlignment[axleDir][i];
			BlockPos nextPos = pos.offset(direction);
			Block nextBlock = world.getBlockState(nextPos).getBlock();
			
			if(nextBlock instanceof AxleBlock) {
				powerLevel[i] = world.getBlockState(nextPos).get(NEMBlockStateProperties.POWER_DISTANCE_3_15);
			}
			
			else if(nextBlock instanceof GearboxBlock) {
				Direction gearboxInput = world.getBlockState(nextPos).get(BlockStateProperties.FACING);
				//gearboxInput == AxleBlock.axisAlignment[axleDir][i].getOpposite() || gearboxInput == AxleBlock.axisAlignment[axleDir][i]
				if(gearboxInput != AxleBlock.axisAlignment[axleDir][i].getOpposite()) {
					powerLevel[i] = axleDist + 1;
				}
				else powerLevel[i] = axleDist - 1;
			}
			
			else if(nextBlock instanceof MillstoneBlock) {
				//TODO use AbstractMachineBlock instead
				powerLevel[i] = axleDist - 1;
			}
			
			else if(nextBlock instanceof CreativePowerBoxBlock) {
				//TODO use AbstractPowerSourceBlock instead
				powerLevel[i] = axleDist + 1;
			}
		}
		
		//Check which machine is furthest form the source
		//and then push power to that machine
		if(powerLevel[0] == axleDist - 1) {
			
			Direction direction = AxleBlock.axisAlignment[axleDir][0];
			BlockPos nextPos = pos.offset(direction);
			
			LazyOptional<IMechanicalPower> nextMachine = world.getTileEntity(nextPos).getCapability(CapabilityMechanical.MECHANICAL, direction.getOpposite());
			nextMachine.ifPresent(h -> {
				
				if(h.canReceive()) {
					
					h.receivePower(powerChannel.sendPower(MAXPOWERSENT, false), false);
					}
			});
		}
		else if(powerLevel[1] == axleDist - 1) {
			
			Direction direction = AxleBlock.axisAlignment[axleDir][1];
			BlockPos nextPos = pos.offset(direction);
			
			LazyOptional<IMechanicalPower> nextMachine = world.getTileEntity(nextPos).getCapability(CapabilityMechanical.MECHANICAL, direction.getOpposite());
			nextMachine.ifPresent(h -> {
				
				if(h.canReceive()) {
					
					h.receivePower(powerChannel.sendPower(MAXPOWERSENT, false), false);
					}
			});
		}
	}
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		
		if(cap == CapabilityMechanical.MECHANICAL) {
			
			int axelDir = this.getBlockState().get(NEMBlockStateProperties.AXLE_DIRECTION);
			
			switch(axelDir) {
			
			case AxleBlock.AXELAXISY :
				if(side == Direction.UP || side == Direction.DOWN) return powerChannelHandler.cast();
				
			case AxleBlock.AXELAXISZ :
				if(side == Direction.NORTH || side == Direction.SOUTH) return powerChannelHandler.cast();
				
			case AxleBlock.AXELAXISX :
				if(side == Direction.EAST || side == Direction.WEST) return powerChannelHandler.cast();
				
			default :
				break;
			}
		}
		
		return super.getCapability(cap, side);
	}
	
	
	@Override
	public void read(CompoundNBT compound) {
		
		if(compound.contains("storedpower")) {
			powerChannelHandler.ifPresent(h -> {
				h.setEnergyStored(compound.getInt("storedpower"));
			});
		}
		
		super.read(compound);
	}
	
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {

		powerChannelHandler.ifPresent(h -> {
			compound.putInt("storedpower", h.getEnergyStored());//was "process"
		});
		
		return super.write(compound);
	}
	
	
	@Override
	public void remove() {
		super.remove();
		this.powerChannelHandler.invalidate();
	}
	
	
	private MechanicalPowerConduit makeMechanicalPowerHandler(int capacity, int maxPowerReceived, int maxPowerSent) {
		return new MechanicalPowerConduit(capacity, maxPowerReceived, maxPowerSent);
	}
}




