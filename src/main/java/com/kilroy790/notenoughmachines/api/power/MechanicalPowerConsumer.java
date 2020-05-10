package com.kilroy790.notenoughmachines.api.power;




public class MechanicalPowerConsumer extends MechanicalPower {

	
	public MechanicalPowerConsumer(int powerCapacity, int maxPowerReceived, int maxPowerSent) {
		
		super(powerCapacity, maxPowerReceived, 0, MechanicalType.SINK);
	}
}
