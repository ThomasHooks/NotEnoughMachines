package com.github.thomashooks.notenoughmachines.world.block.state;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class AllBlockStateProperties
{
    public static final BooleanProperty NEGATED = BooleanProperty.create("negated");
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final BooleanProperty SHIFTED = BooleanProperty.create("shifted");
    public static final EnumProperty<InputDualType> DUAL_INPUT = EnumProperty.create("input", InputDualType.class);
    public static final EnumProperty<MultiBlockPart3x1x3> LARGE_COGWHEEL_PART = EnumProperty.create("cogwheel_part", MultiBlockPart3x1x3.class);
}
