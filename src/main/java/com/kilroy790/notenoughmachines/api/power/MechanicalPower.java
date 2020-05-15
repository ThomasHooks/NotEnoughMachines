package com.kilroy790.notenoughmachines.api.power;




public class MechanicalPower implements IMechanicalPower {
	
	
    protected int storedPower;
    protected int capacity;
    protected int load;
    protected int maxTransmitted;
    
    protected MechanicalType type;
    
    
    public MechanicalPower(int powerCapacity, int powerLoad, int maxTransmitted, MechanicalType typeIn) {
		
    	this.storedPower = 0;
    	this.capacity = powerCapacity;
    	this.load = powerLoad;
    	this.maxTransmitted = maxTransmitted;
    	this.type = typeIn;
	}
	
    
	
	@Override
	public boolean isPowered() {
		return storedPower > 0;
	}



	@Override
	public MechanicalType getMechType() {
		return this.type;
	}

	
	
	@Override
	public int sendPower(int maxPowerRemoved, boolean simulate) {
		
        int powerSent = Math.min(storedPower, Math.min(this.maxTransmitted, maxPowerRemoved));
        if (!simulate) storedPower -= powerSent;
        
        return powerSent;
	}

	
	
	@Override
	public int receivePower(int maxPowerAdded, boolean simulate) {
		
        int powerReceived = Math.min(capacity - storedPower, Math.min(this.maxTransmitted, maxPowerAdded));
        if (!simulate) storedPower += powerReceived;
        
        return powerReceived;
	}
	
	
	
	@Override
	public int consumePower(int maxPowerRemoved, boolean simulate) {
		
		if(this.type == MechanicalType.SOURCE) return 0;
		
		int powerConsumed = Math.min(storedPower, maxPowerRemoved);
		if (!simulate) storedPower -= powerConsumed;
		
		return powerConsumed;
	}
	
	
	
	@Override
	public int producePower(int maxPowerAdded, boolean simulate) {
		
		if(this.type != MechanicalType.SOURCE) return 0;
		
		int powerProduced = Math.min(capacity - storedPower, maxPowerAdded);
		if(!simulate) storedPower += powerProduced;
		
		return powerProduced;
	}

	
	
	@Override
	public int getMaxCapacity() {
		return capacity;
	}

	
	
	@Override
	public int getStoredEngergy() {
		return storedPower;
	}
	
	
	
	@Override
	public void setStoredEnergy(int storedPowerIn) {
		if(storedPowerIn < 0) this.storedPower = 0;
		else this.storedPower = storedPowerIn;
	}
}
