package com.kilroy790.notenoughmachines.api.power;




public interface IMechanicalPower {

	
	/*
	 * @return		if the given machine is attached to a Consumer machine ie. a load
	 * 
	 * *Note* this method is mostly used by Conduits.  Consumer should always return true
	 */
	boolean hasLoad();
	
	
	/*
	 * set if the given machine is loaded
	 * 
	 * *Note* this method is mostly used by Conduits.  Consumer should always return true
	 */
	void setLoaded(boolean loaded);
	
	
	/*
	 * @return		if the machine is currently powered
	 * 
	 * *Note* this method is mostly used by Conduits.  Producers should always return true
	 */
	boolean isPowered();
	
	
	/*
	 * @return		if the machine is a produce of power
	 * 
	 * *Note* Producers should always return true, while Conduits and Consumers should always return false
	 */
	boolean isProducer();
	
	
	/*
	 * @return		if the machine is a consumes of power
	 * 
	 * *Note* Consumers should always return true, while Conduits and Producers should always return false
	 */
	boolean isConsumer();
	
	
	/*
	 * @return		if the machine can receive power
	 */
	boolean canReceive();
	
	
	/*
	 * @return		if the machine can send power
	 */
	boolean canSend();
	
	
	/*
	 * Removes power from the machine and returns quantity of power that was sent
	 * 
	 * @param maxPowerRemoved		the amount of power to be sent and removed
	 * 
	 * @param simulate				sets if the power transmission is a simulation, if false power will be removed
	 * 
	 * @return						the amount of power that was sent or would have been sent if it was a simulation
	 */
	int sendPower(int maxPowerRemoved, boolean simulate);
	
	
	/*
	 * Adds power to the machine and returns quantity of power that was added
	 * 
	 * @param maxPowerAdded		the amount of power to be received and added
	 * 
	 * @param simulate			sets if the power transmission is a simulation, if false power will be added
	 * 
	 * @return					the amount of power that was received or would have been received if it was a simulation
	 */
	int receivePower(int maxPowerAdded, boolean simulate);
	
	
	/*
	 * Removes power from the machine and returns quantity of power that was removed
	 * 
	 * @param maxPowerRemoved		the amount of power to be removed
	 * 
	 * @param simulate				sets if the power transmission is a simulation, if false power will be removed
	 * 
	 * @return						the amount of power that was consumed or would have been consumed if it was a simulation
	 */
	int consumePower(int maxPowerRemoved, boolean simulate);
	
	
	/*
	 * Adds power to the machine and returns quantity of power that was added
	 * 
	 * @param maxPowerAdded		the amount of power to be removed
	 * 
	 * @param simulate			sets if the power transmission is a simulation, if false power will be removed
	 * 
	 * @return					the amount of power that was produced or would have been produced if it was a simulation
	 */
	int producePower(int maxPowerAdded, boolean simulate);
	
	
	/*
	 * @return		the maximum amount of energy that can be stored by the machine
	 */
	int getMaxCapacity();
	
	
	/*
	 * @return		the amount of energy that is currently stored by the machine
	 */
	int getEnergyStored();
	
	
	/*
	 * @param storedPowerIn		the amount of energy to be stored by the machine
	 */
	void setEnergyStored(int storedPowerIn);
}
