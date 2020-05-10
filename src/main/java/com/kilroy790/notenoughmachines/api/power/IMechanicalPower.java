package com.kilroy790.notenoughmachines.api.power;




public interface IMechanicalPower {

	
	
	/*
	 * @return If the machine is currently powered
	 */
	boolean isPowered();
	
	
	
	/*
	 * @return The type of the machine
	 */
	MechanicalType getMechType();

	
	
	/*
	 * Removes power from the machine and returns quantity of power that was sent
	 * 
	 * @param maxPowerRemoved, The amount of power to be sent and removed
	 * 
	 * @param simulate, Sets if the power transmission is a simulation, if false power will be removed
	 * 
	 * @return The amount of power that was sent or would have been sent if it was a simulation
	 */
	int sendPower(int maxPowerRemoved, boolean simulate);
	
	
	
	/*
	 * Adds power to the machine and returns quantity of power that was added
	 * 
	 * @param maxPowerAdded, The amount of power to be received and added
	 * 
	 * @param simulate, Sets if the power transmission is a simulation, if false power will be added
	 * 
	 * @return The amount of power that was received or would have been received if it was a simulation
	 */
	int receivePower(int maxPowerAdded, boolean simulate);
	
	
	
	/*
	 * Removes power from the machine and returns quantity of power that was removed
	 * 
	 * @param maxPowerRemoved, The amount of power to be removed
	 * 
	 * @param simulate, Sets if the power transmission is a simulation, if false power will be removed
	 * 
	 * @return The amount of power that was consumed or would have been consumed if it was a simulation
	 */
	int consumePower(int maxPowerRemoved, boolean simulate);
	
	
	
	/*
	 * Adds power to the machine and returns quantity of power that was added
	 * 
	 * @param maxPowerAdded, The amount of power to be removed
	 * 
	 * @param simulate, Sets if the power transmission is a simulation, if false power will be removed
	 * 
	 * @return The amount of power that was produced or would have been produced if it was a simulation
	 */
	int producePower(int maxPowerAdded, boolean simulate);
	
	
	
	/*
	 * @return The maximum amount of energy that can be stored by the machine
	 */
	int getMaxCapacity();
	
	
	
	/*
	 * @return The amount of energy that is currently stored by the machine
	 */
	int getStoredEngergy();
	
	
	
	/*
	 * @param storedPowerIn, The amount of energy to be stored by the machine
	 */
	void setStoredEnergy(int storedPowerIn);
}
