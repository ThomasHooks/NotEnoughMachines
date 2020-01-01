package com.kilroy790.notenoughmachines.tiles;

import com.kilroy790.notenoughmachines.api.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.api.power.CapabilityMechanical;
import com.kilroy790.notenoughmachines.api.power.IMechanicalPower;
import com.kilroy790.notenoughmachines.api.power.MechanicalPowerConduit;
import com.kilroy790.notenoughmachines.blocks.machines.AxleBlock;
import com.kilroy790.notenoughmachines.blocks.machines.GearboxBlock;
import com.kilroy790.notenoughmachines.lists.TileEntityList;

import net.minecraft.block.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;




public class GearboxTile extends TileEntity implements ITickableTileEntity {
	

	private MechanicalPowerConduit powerChannel;
	private LazyOptional<IMechanicalPower> powerChannelHandler = LazyOptional.of(() -> powerChannel);
	//there are ~20 tick per second
	private static final int POWERCAPACITY = 60;
	private static final int MAXPOWERRECEIVED = 60;
	private static final int MAXPOWERSENT = 60;
	
	
	public GearboxTile() {
		super(TileEntityList.GEARBOX);
		
		powerChannel = makeMechanicalPowerHandler(POWERCAPACITY, MAXPOWERRECEIVED, MAXPOWERSENT);
	}
	
	
	@Override
	public void tick() {
		
		if(world.isRemote) return;
		
		if(powerChannel.isPowered()) {
			
			//Loop through each side and push power to all axles that are connected to its outputs
			sendPowerToNextMachine();
		}
	}
	
	
	protected void sendPowerToNextMachine() {
		
		Direction gearboxInputSide = this.getBlockState().get(BlockStateProperties.FACING);
		int numOfAxles = 0;
		
		for(int i = 0; i < 6; i++) {
			//loop over all sides and determine the number of axles that are connected to the gearbox
			Direction direction = GearboxBlock.GEARBOX_SIDE[i];
			BlockPos nextPos = pos.offset(direction);
			Block nextBlock = world.getBlockState(nextPos).getBlock();
			
			if(direction == gearboxInputSide) {
				//The gearbox can't send power through its input side
			}
			
			else if(nextBlock instanceof AxleBlock) {
				
				int axleDir = world.getBlockState(nextPos).get(NEMBlockStateProperties.AXLE_DIRECTION);
				if(direction == AxleBlock.axisAlignment[axleDir][0] || direction == AxleBlock.axisAlignment[axleDir][1]) {
					
					numOfAxles++;
				}
			}
		}

		if(numOfAxles == 0) return;
		
		final int connectedAxles = numOfAxles;
		for(int i = 0; i < 6; i++) {
			//loop over all sides and push power to the axles that are connected to the gearbox
			Direction direction = GearboxBlock.GEARBOX_SIDE[i];
			BlockPos nextPos = pos.offset(direction);
			Block nextBlock = world.getBlockState(nextPos).getBlock();
			
			if(direction == gearboxInputSide) {
				//The gearbox can't send power through its input side
			}
			
			else if(nextBlock instanceof AxleBlock) {
				int axleDir = world.getBlockState(nextPos).get(NEMBlockStateProperties.AXLE_DIRECTION);
				if(direction == AxleBlock.axisAlignment[axleDir][0] || direction == AxleBlock.axisAlignment[axleDir][1]) {
					
					LazyOptional<IMechanicalPower> nextMachine = world.getTileEntity(nextPos).getCapability(CapabilityMechanical.MECHANICAL, direction);
					nextMachine.ifPresent(h -> {
						if(h.canReceive()) {
							h.receivePower(powerChannel.sendPower(MAXPOWERSENT/connectedAxles, false), false);
						}
					});
				}
			}
		}
	}
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		
		if(cap == CapabilityMechanical.MECHANICAL) {
			if(side == this.getBlockState().get(BlockStateProperties.FACING)) {
				
				//the gearbox can only receive power from one side
				return powerChannelHandler.cast();
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
			compound.putInt("storedpower", h.getEnergyStored());
		});
		
		return super.write(compound);
	}
	
	
	@Override
	public void remove() {
		super.remove();
		this.powerChannelHandler.invalidate();
	}
	
	
	protected MechanicalPowerConduit makeMechanicalPowerHandler(int capacity, int maxPowerReceived, int maxPowerSent) {
		return new MechanicalPowerConduit(capacity, maxPowerReceived, maxPowerSent);
	}
}




