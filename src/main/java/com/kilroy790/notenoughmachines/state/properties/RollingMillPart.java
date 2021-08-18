package com.kilroy790.notenoughmachines.state.properties;

import net.minecraft.util.IStringSerializable;




public enum RollingMillPart implements IStringSerializable 
{
	BASE("base"),
	TOP("top");
	
	private final String name;

	
	
	private RollingMillPart(String name) 
	{
		this.name = name;
	}
	
	

	@Override
	public String getString() 
	{
		return name;
	}

}



