package com.kilroy790.notenoughmachines.api;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;

public class NEMBlockStateProperties {

	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	
	public static final IntegerProperty AXLE_DIRECTION = IntegerProperty.create("dir", 0, 2);
	public static final IntegerProperty POWER_DISTANCE_3_15 = IntegerProperty.create("powerdist", 3, 15);
	public static final IntegerProperty POWER_DISTANCE_3_13 = IntegerProperty.create("powerdist", 3, 13);
	public static final IntegerProperty POWERED_14_15 = IntegerProperty.create("powered", 14, 15);
}
