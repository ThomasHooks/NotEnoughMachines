package com.github.thomashooks.notenoughmachines.world.block.state.properties;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;

public enum MultiBlockPart3x1x3 implements StringRepresentable
{
    TOP_LEFT(0, "top_left"),
    TOP(1, "top"),
    TOP_RIGHT(2, "top_right"),
    LEFT(3, "left"),
    CENTER(4, "center"),
    RIGHT(5, "right"),
    BOTTOM_LEFT(6, "bottom_left"),
    BOTTOM(7, "bottom"),
    BOTTOM_RIGHT(8, "bottom_right");

    private final String part;
    private final int index;
    private static final MultiBlockPart3x1x3[] BY_INDEX = Arrays.stream(values()).sorted(Comparator.comparingInt( (multiBlockPart) -> { return multiBlockPart.index; })).toArray(MultiBlockPart3x1x3[]::new);

    private MultiBlockPart3x1x3(int partIndex, String partName)
    {
        this.index = partIndex;
        this.part = partName;
    }

    public static MultiBlockPart3x1x3 fromIndex(int partIndex) { return BY_INDEX[Mth.abs(partIndex % BY_INDEX.length)]; }

    @Override
    public @NotNull String getSerializedName() { return part; }

    @Override
    public String toString() { return NotEnoughMachines.MOD_ID +  ":LargeCogwheelPart{" + "part='" + part + '\'' + '}'; }
}
