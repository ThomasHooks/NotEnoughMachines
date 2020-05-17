package com.kilroy790.notenoughmachines.power;

import com.kilroy790.notenoughmachines.api.power.MechanicalType;




public interface IHaveMechanicalPower {

	/**
	 * @return The amount of energy that is required by the machine
	 */
	int getLoad();
	
	
	
	/**
	 * @return The amount of energy that can be stored by the machine
	 */
	int getCapacity();
	
	
	
	/**
	 * @return If the machine is currently powered
	 */
	boolean isPowered();
	
	
	
	/**
	 * @return The mechanical power type of this machine
	 */
	MechanicalType getMechType();
}
