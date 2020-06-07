package com.kilroy790.notenoughmachines.state.properties;

import net.minecraft.util.IStringSerializable;




public enum ChuteType implements IStringSerializable {

	ANCHORED("anchored"),
	HOPPER("hopper"),
	HANGING("hanging");
	
	private final String name;

	
	private ChuteType(String name) {
		this.name = name;
	}

	
	public String toString() {
		return this.name;
	}

	
	@Override
	public String getName() {
		return this.name;
	}
}
