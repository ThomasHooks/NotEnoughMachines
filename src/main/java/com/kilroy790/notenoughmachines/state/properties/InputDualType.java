package com.kilroy790.notenoughmachines.state.properties;

import net.minecraft.util.IStringSerializable;





public enum InputDualType implements IStringSerializable {
	IN00("in00"),
	IN01("in01"),
	IN10("in10"),
	IN11("in11");
	
	private final String name;

	
	private InputDualType(String name) {
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
