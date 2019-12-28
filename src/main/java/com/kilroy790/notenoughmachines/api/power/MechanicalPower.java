package com.kilroy790.notenoughmachines.api.power;




public class MechanicalPower implements IMechanicalPower {
	
	
    protected int storedPower;
    protected int capacity;
    protected int maxReceive;
    protected int maxSent;
    protected boolean powered;
    protected boolean load;
    
    
    public MechanicalPower(int powerCapacity, int maxPowerReceived, int maxPowerSent) {
		
    	this.storedPower = 0;
    	this.capacity = powerCapacity;
    	this.maxReceive = maxPowerReceived;
    	this.maxSent = maxPowerSent;
    	this.powered = false;
    	this.load = false;
	}
    
    
	@Override
	public boolean hasLoad() {
		
		return load;
	}
	
	
	@Override
	public void setLoaded(boolean loaded) {
		
		this.load = loaded;
	}

	
	@Override
	public boolean isPowered() {
		
		return storedPower > 0;
	}

	
	@Override
	public boolean isProducer() {
		
		return false;
	}

	
	@Override
	public boolean isConsumer() {
		
		return false;
	}

	
	@Override
	public boolean canReceive() {
		
		return this.maxReceive > 0;
	}

	
	@Override
	public boolean canSend() {
		
		return this.maxSent > 0;
	}

	
	@Override
	public int sendPower(int maxPowerRemoved, boolean simulate) {
		
        if (!canSend()) return 0;

        int powerSent = Math.min(storedPower, Math.min(this.maxSent, maxPowerRemoved));
        if (!simulate) storedPower -= powerSent;
        
        return powerSent;
	}

	
	@Override
	public int receivePower(int maxPowerAdded, boolean simulate) {
		
        if (!canReceive()) return 0;

        int powerReceived = Math.min(capacity - storedPower, Math.min(this.maxReceive, maxPowerAdded));
        if (!simulate) storedPower += powerReceived;
        
        return powerReceived;
	}
	
	
	@Override
	public int consumePower(int maxPowerRemoved, boolean simulate) {
		
		if(!isConsumer()) return 0;
		
		int powerConsumed = Math.min(storedPower, maxPowerRemoved);
		if (!simulate) storedPower -= powerConsumed;
		
		return powerConsumed;
	}
	
	
	@Override
	public int producePower(int maxPowerAdded, boolean simulate) {
		
		if(!isProducer()) return 0;
		
		int powerProduced = Math.min(capacity - storedPower, maxPowerAdded);
		if(!simulate) storedPower += powerProduced;
		
		return powerProduced;
	}

	
	@Override
	public int getMaxCapacity() {
		
		return capacity;
	}

	
	@Override
	public int getEnergyStored() {
		
		return storedPower;
	}
}
