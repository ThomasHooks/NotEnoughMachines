package com.github.thomashooks.notenoughmachines.world.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum MultiBlockPart1x1x4 implements StringRepresentable
{
    BOTTOM("bottom"),
    LOWER_MID("lower_mid"),
    UPPER_MID("upper_mid"),
    TOP("top");

    private final String part;

    private MultiBlockPart1x1x4(String partName)
    {
        this.part = partName;
    }

    @Override
    public String toString() { return this.part; }

    @Override
    public String getSerializedName() { return this.part; }
}
