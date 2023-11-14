package com.github.thomashooks.notenoughmachines.world.power;

import net.minecraft.util.StringRepresentable;

public enum MechanicalType implements StringRepresentable
{
    SOURCE("source"),
    SHAFT("shaft"),
    COG("cog"),
    SINK("sink");

    private final String tag;

    MechanicalType(String tagIn)
    {
        this.tag = tagIn;
    }

    @Override
    public String getSerializedName()
    {
        return this.tag;
    }

    @Override
    public String toString()
    {
        return this.tag;
    }

    static public MechanicalType byName(String tagIn)
    {
        return switch (tagIn)
        {
            case "source" -> MechanicalType.SOURCE;
            case "cog" -> MechanicalType.COG;
            case "sink" -> MechanicalType.SINK;
            default -> MechanicalType.SHAFT;
        };
    }
}
