package com.github.thomashooks.notenoughmachines.util;

import net.minecraft.core.Direction;

import java.util.Objects;

public class DirectionHelper
{
    static public Direction rotateY(final Direction direction, final boolean counterClockWise, final int times)
    {
        Objects.requireNonNull(direction, "Direction cannot be null!");
        Direction facing = direction;
        for (int i = 0; i < times; i++)
        {
            if (counterClockWise)
                facing = facing.getCounterClockWise();
            else
                facing = facing.getClockWise();
        }
        return facing;
    }

    static public Direction rotateX(final Direction direction, final boolean counterClockWise, final int times)
    {
        Objects.requireNonNull(direction, "Direction cannot be null!");
        Direction facing = direction;
        for (int i = 0; i < times; i++)
        {
            if(counterClockWise)
                facing = getCounterClockWiseX(facing);
            else
                facing = getClockWiseX(facing);
        }
        return facing;
    }

    static public Direction getClockWiseX(final Direction dir)
    {
        return switch (dir)
        {
            case DOWN -> Direction.SOUTH;
            case UP -> Direction.NORTH;
            case NORTH -> Direction.DOWN;
            case SOUTH -> Direction.UP;
            default -> throw new IllegalStateException("Unable to get X-rotated clock wise facing of " + dir);
        };
    }

    static public Direction getCounterClockWiseX(final Direction dir)
    {
        return switch (dir)
        {
            case DOWN -> Direction.NORTH;
            case UP -> Direction.SOUTH;
            case NORTH -> Direction.UP;
            case SOUTH -> Direction.DOWN;
            default -> throw new IllegalStateException("Unable to get X-rotated counter clock wise facing of " + dir);
        };
    }
}
