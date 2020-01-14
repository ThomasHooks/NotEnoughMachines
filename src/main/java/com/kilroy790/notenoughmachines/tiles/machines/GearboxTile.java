package com.kilroy790.notenoughmachines.tiles.machines;

import com.kilroy790.notenoughmachines.api.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.api.NEMConfigurableSides;
import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.api.power.CapabilityMechanical;
import com.kilroy790.notenoughmachines.api.power.IMechanicalPower;
import com.kilroy790.notenoughmachines.api.power.MechanicalPowerConduit;
import com.kilroy790.notenoughmachines.blocks.machines.AxleBlock;
import com.kilroy790.notenoughmachines.blocks.machines.GearboxBlock;
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
	
	private static final int POWERCAPACITY = 72;
	private static final int MAXPOWERRECEIVED = 72;
	private static final int MAXPOWERSENT = 72;
	
	protected NEMConfigurableSides[] gearboxSide = {NEMConfigurableSides.OUTPUT, NEMConfigurableSides.INPUT, NEMConfigurableSides.OUTPUT, NEMConfigurableSides.OUTPUT, NEMConfigurableSides.OUTPUT, NEMConfigurableSides.OUTPUT};
	protected int numberOfOutputs = 5;
	
	public GearboxTile() {
		super(TileEntityList.GEARBOX);
		
		powerChannel = makeMechanicalPowerHandler(POWERCAPACITY, MAXPOWERRECEIVED, MAXPOWERSENT);
	}
	
	
	@Override
	public void tick() {
		//there are ~20 tick per second
		
		if(world.isRemote) return;
		
		//TODO implement configurable sides 
		//if(world.getGameTime()%10 == 1) updateSides();
		
		if(powerChannel.isPowered()) {
			
			//Loop through each side and push power to all axles that are connected to its outputs
			sendPowerToNextMachine();
			
			updateBlockStatePowered(true);
		}
		
		else updateBlockStatePowered(false);
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

		if(numOfAxles == 0) {
			//if there are no outputs dump the stored power
			powerChannel.consumePower(MAXPOWERSENT, false);
			return;
		}
		
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
					
					TileEntity nextTile = world.getTileEntity(nextPos);
					if(nextTile == null) continue;
					
					LazyOptional<IMechanicalPower> nextMachine = nextTile.getCapability(CapabilityMechanical.MECHANICAL, direction);
					nextMachine.ifPresent(h -> {
						if(h.canReceive()) {
							h.receivePower(powerChannel.sendPower(MAXPOWERSENT/connectedAxles, false), false);
						}
					});
				}
			}
		}
	}
	
	
	protected void updateSides() {
		
		Direction gearboxInputSide = this.getBlockState().get(BlockStateProperties.FACING);
		this.numberOfOutputs = 0;
		
		for(int i = 0; i < 6; i++) {
			//loop over all sides and determine the number of axles that are connected to the gearbox
			Direction direction = GearboxBlock.GEARBOX_SIDE[i];
			BlockPos nextPos = pos.offset(direction);
			Block nextBlock = world.getBlockState(nextPos).getBlock();
			
			if(direction == gearboxInputSide) gearboxSide[i] = NEMConfigurableSides.INPUT;
			
			else if(nextBlock instanceof AxleBlock) {
				
				int axleDir = world.getBlockState(nextPos).get(NEMBlockStateProperties.AXLE_DIRECTION);
				if(direction == AxleBlock.axisAlignment[axleDir][0] || direction == AxleBlock.axisAlignment[axleDir][1]) {
					
					gearboxSide[i] = NEMConfigurableSides.OUTPUT;
					this.numberOfOutputs++;
				}
			}
			else gearboxSide[i] = NEMConfigurableSides.NONE;
		}
	}
	
	
	protected void updateBlockStatePowered(boolean isPowered) {
		Direction facing = this.world.getBlockState(pos).get(BlockStateProperties.FACING);
		this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(BlockStateProperties.FACING, facing).with(GearboxBlock.getPowered(), isPowered), 1|2);
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




