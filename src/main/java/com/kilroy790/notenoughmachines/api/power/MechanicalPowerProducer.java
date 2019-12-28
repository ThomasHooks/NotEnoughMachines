package com.kilroy790.notenoughmachines.api.power;




public class MechanicalPowerProducer extends MechanicalPower {

	
	public MechanicalPowerProducer(int powerCapacity, int maxPowerReceived, int maxPowerSent) {
		
		super(powerCapacity, 0, maxPowerSent);
	}

	
	@Override
	public boolean isProducer() {
		
		return true;
	}
}
