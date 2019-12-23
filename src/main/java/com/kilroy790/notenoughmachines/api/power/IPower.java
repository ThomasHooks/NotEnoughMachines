package com.kilroy790.notenoughmachines.api.power;

public interface IPower {

	boolean hasLoad();
	
	boolean isPowered();
	
	boolean isProducer();
	
	boolean isConsumer();
	
	boolean canReceive();
	
	boolean canSend();
}
