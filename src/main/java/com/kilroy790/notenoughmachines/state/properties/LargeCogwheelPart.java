package com.kilroy790.notenoughmachines.state.properties;

import net.minecraft.util.IStringSerializable;




public enum LargeCogwheelPart implements IStringSerializable 
{
	TOPLEFT("topleft"),
	TOP("top"),
	TOPRIGHT("topright"),
	MIDLEFT("midleft"),
	MID("mid"),
	MIDRIGHT("midright"),
	BOTTOMLEFT("bottomleft"),
	BOTTOM("bottom"),
	BOTTOMRIGHT("bottomright");

	private final String name;

	
	
	private LargeCogwheelPart(String name) 
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



