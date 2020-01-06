package com.kilroy790.notenoughmachines.tiles.machines;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.api.power.CapabilityMechanical;
import com.kilroy790.notenoughmachines.api.power.IMechanicalPower;
import com.kilroy790.notenoughmachines.api.power.MechanicalPowerProducer;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class CreativePowerBoxTile extends TileEntity implements ITickableTileEntity {

	
	//there are ~20 tick per second
	private static final int POWERCAPACITY = 1000000;
	private static final int MAXPOWERRECEIVED = 0;
	private static final int MAXPOWERSENT = 1000;
	private static final int POWERPRODUCED = 5000;
	private MechanicalPowerProducer powerOutput;
	private LazyOptional<IMechanicalPower> powerOutputHandler = LazyOptional.of(() -> powerOutput);
	
	
	public CreativePowerBoxTile() {
		
		super(TileEntityList.CREATIVEPOWERBOX);
		
		powerOutput = makeMechanicalPowerHandler();
	}

	
	@Override
	public void tick() {
		
		if(world.isRemote) return;
		
		//add power to storage
		powerOutput.producePower(POWERPRODUCED, false);
		
		//check if the block below has a tile entity that has a mechanical power capability
		//then send that block power if it can also receive power
		if(world.getBlockState(pos.down()).hasTileEntity()){
			LazyOptional<IMechanicalPower> cap = world.getTileEntity(pos.down()).getCapability(CapabilityMechanical.MECHANICAL, Direction.UP);
			
			cap.ifPresent(h -> {
				if(h.canReceive()) {
					h.receivePower(powerOutput.sendPower(MAXPOWERSENT, false), false);
				}
			});
		}
	}
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		
		if(cap == CapabilityMechanical.MECHANICAL) {
			
			return powerOutputHandler.cast();
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
			compound.putInt("process", h.getEnergyStored());
		});
		
		return super.write(compound);
	}
	
	
	@Override
	public void remove() {
		super.remove();
		this.powerOutputHandler.invalidate();
	}
	
	
	private MechanicalPowerProducer makeMechanicalPowerHandler() {
		
		return new MechanicalPowerProducer(POWERCAPACITY, MAXPOWERRECEIVED, MAXPOWERSENT);
	}
}
