package com.github.thomashooks.notenoughmachines.world.block.state;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.util.StringRepresentable;

public enum LargeCogwheelPart implements StringRepresentable
{
    TOP_LEFT("top_left"),
    TOP("top"),
    TOP_RIGHT("top_right"),
    MID_LEFT("mid_left"),
    MID("mid"),
    MID_RIGHT("mid_right"),
    BOTTOM_LEFT("bottom_left"),
    BOTTOM("bottom"),
    BOTTOM_RIGHT("bottom_right");

    private final String part;

    private LargeCogwheelPart(String partIn) { this.part = partIn; }

    @Override
    public String getSerializedName() { return part; }

    @Override
    public String toString()
    {
        return NotEnoughMachines.MOD_ID +  ":LargeCogwheelPart{" + "part='" + part + '\'' + '}';
    }
}
