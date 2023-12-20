package com.github.thomashooks.notenoughmachines.world.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum InputDualType implements StringRepresentable
{
    IN00("in00"),
    IN01("in01"),
    IN10("in10"),
    IN11("in11");

    private final String name;

    private InputDualType(String name)
    {
        this.name = name;
    }

    @Override
    public String getSerializedName()
    {
        return this.name;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
