package com.kilroy790.notenoughmachines.state.properties;

import net.minecraft.util.IStringSerializable;




public enum TripHammerPart implements IStringSerializable 
{
	BASE("base"),
	LOWERFRAME("lowerframe"),
	CAM("cam"),
	UPPERFRAME("upperframe");

	private final String name;

	
	
	private TripHammerPart(String name) 
	{
		this.name = name;
	}

	
	
	public String toString() 
	{
		return this.name;
	}

	
	
	@Override
	public String getString() 
	{
		return this.name;
	}
}



