package com.github.thomashooks.notenoughmachines.world.block.state;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class NEMBlockStateProperties
{
    public static final BooleanProperty NEGATED = BooleanProperty.create("negated");
    public static final EnumProperty<InputDualType> DUAL_INPUT = EnumProperty.create("input", InputDualType.class);
}
