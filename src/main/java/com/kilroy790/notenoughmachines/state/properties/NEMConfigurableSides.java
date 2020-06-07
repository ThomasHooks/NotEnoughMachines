package com.kilroy790.notenoughmachines.state.properties;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;




public enum NEMConfigurableSides implements IStringSerializable{

	
	NONE("none"),
	INPUT("input"),
	OUTPUT("output");
	
	final String type;
	
	
	NEMConfigurableSides(String typeIn){
		this.type = typeIn;
	}

	@Override
	public String getName() {
		
		return this.toString().toLowerCase(Locale.ENGLISH);
	}
}
