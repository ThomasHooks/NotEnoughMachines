package com.kilroy790.notenoughmachines.power;

import net.minecraft.util.IStringSerializable;




public enum MechanicalType implements IStringSerializable 
{	
	SOURCE("source"),
	SHAFT("shaft"),
	COG("cog"),
	SINK("sink");
	
	private final String tag;
	
	
	
	private MechanicalType(String tagIn) 
	{
		this.tag = tagIn;
	}

	
	
	@Override
	public String getString() 
	{
		return this.tag;
	}
	
	
	
	@Override
	public String toString() 
	{
		return this.tag;
	}
	
	
	
	static public MechanicalType byName(String tagIn) 
	{
		switch(tagIn) {
		
		case "source":
			return MechanicalType.SOURCE;
			
		case "shaft":
			return MechanicalType.SHAFT;
			
		case "cog":
			return MechanicalType.COG;
			
		case "sink":
			return MechanicalType.SINK;
			
		default:
			return MechanicalType.SHAFT;
		}
	}
}
