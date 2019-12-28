package com.kilroy790.notenoughmachines.api.power;




public class MechanicalPowerConsumer extends MechanicalPower {

	
	public MechanicalPowerConsumer(int powerCapacity, int maxPowerReceived, int maxPowerSent) {
		
		super(powerCapacity, maxPowerReceived, 0);
	}

	
	@Override
	public boolean isConsumer() {
		
		return true;
	}
	
	
	@Override
	public boolean hasLoad() {
		
		return true;
	}
	
	
	@Override
	public void setLoaded(boolean loaded) {
		
		super.setLoaded(true);
	}
}
