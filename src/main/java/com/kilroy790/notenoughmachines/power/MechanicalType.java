package com.kilroy790.notenoughmachines.power;

import net.minecraft.util.IStringSerializable;




public enum MechanicalType implements IStringSerializable {
	
	SOURCE("source"),
	CHANNEL("channel"),
	SINK("sink");
	
	private final String tag;
	
	
	
	private MechanicalType(String tagIn) {
		this.tag = tagIn;
	}

	
	
	@Override
	public String getName() {
		return this.tag;
	}
	
	
	
	@Override
	public String toString() {
		return this.tag;
	}
	
	
	
	static public MechanicalType byName(String tagIn) {
		switch(tagIn) {
		
		case "source":
			return MechanicalType.SOURCE;
			
		case "channel":
			return MechanicalType.CHANNEL;
			
		case "sink":
			return MechanicalType.SINK;
			
		default:
			return MechanicalType.CHANNEL;
		}
	}
}
