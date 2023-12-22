package com.github.thomashooks.notenoughmachines.world.block.state.properties;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class AllBlockStateProperties
{
    public static final BooleanProperty NEGATED = BooleanProperty.create("negated");
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final BooleanProperty SHIFTED = BooleanProperty.create("shifted");
    public static final BooleanProperty UNSTABLE = BooleanProperty.create("unstable");

    public static final IntegerProperty SPEED = IntegerProperty.create("speed", 1, 4);
    public static final IntegerProperty STABILITY_DISTANCE_25 = IntegerProperty.create("distance", 0, 25);
    public static final EnumProperty<InputDualType> DUAL_INPUT = EnumProperty.create("input", InputDualType.class);
    public static final EnumProperty<MultiBlockPart3x1x3> LARGE_COGWHEEL_PART = EnumProperty.create("cogwheel_part", MultiBlockPart3x1x3.class);
    public static final EnumProperty<MultiBlockPart1x1x4> TRIP_HAMMER_PART = EnumProperty.create("trip_hammer_part", MultiBlockPart1x1x4.class);
}
