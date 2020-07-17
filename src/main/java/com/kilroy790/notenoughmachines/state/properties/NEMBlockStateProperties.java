package com.kilroy790.notenoughmachines.state.properties;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;




public class NEMBlockStateProperties {

	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	public static final BooleanProperty SHIFTED = BooleanProperty.create("shifted");
	public static final BooleanProperty NEGATED = BooleanProperty.create("negated");
	public static final BooleanProperty FORMED = BooleanProperty.create("formed");
	
	public static final EnumProperty<InputDualType> DUAL_INPUT = EnumProperty.create("input", InputDualType.class);
	public static final EnumProperty<TripHammerPart> TRIPHAMMERPART = EnumProperty.create("hammerpart", TripHammerPart.class);
}







