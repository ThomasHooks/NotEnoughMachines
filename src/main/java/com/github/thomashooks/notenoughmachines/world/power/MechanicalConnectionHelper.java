package com.github.thomashooks.notenoughmachines.world.power;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MechanicalConnectionHelper
{
    /**
     * Creates a new 2-way mechanical axle connection lookup map
     * <p>
     * Use this when the machine's axis is either unknown or can change
     * @param pos The position of the machine
     * @return New 2-way mechanical axle connection map
     */
    public static HashMap<Direction.Axis, ArrayList<MechanicalContext>> monoAxle(BlockPos pos)
    {
        HashMap<Direction.Axis, ArrayList<MechanicalContext>> connection = new HashMap<Direction.Axis, ArrayList<MechanicalContext>>();
        connection.put(Direction.Axis.X, new ArrayList<MechanicalContext>(
                Arrays.asList(
                        new MechanicalContext(pos.east(), Direction.EAST, true),
                        new MechanicalContext(pos.west(), Direction.WEST, true)
                )));

        connection.put(Direction.Axis.Y, new ArrayList<MechanicalContext>(
                Arrays.asList(
                        new MechanicalContext(pos.above(), Direction.UP, true),
                        new MechanicalContext(pos.below(), Direction.DOWN, true)
                )));

        connection.put(Direction.Axis.Z, new ArrayList<MechanicalContext>(
                Arrays.asList(
                        new MechanicalContext(pos.north(), Direction.NORTH, true),
                        new MechanicalContext(pos.south(), Direction.SOUTH, true)
                )));
        return connection;
    }



    /**
     * Creates a new 2-way mechanical axle connection array
     * <p>
     * The axis must be know ahead of time, and must never change
     * @param pos The position of the machine
     * @param axis The axis that the machine is aligned with
     * @return New 2-way mechanical axle connection array
     */
    public static ArrayList<MechanicalContext> monoAxle(BlockPos pos, Direction.Axis axis)
    {
        ArrayList<MechanicalContext> connection = switch (axis) {
            case X -> new ArrayList<MechanicalContext>(Arrays.asList(
                    new MechanicalContext(pos.east(), Direction.EAST, true),
                    new MechanicalContext(pos.west(), Direction.WEST, true)
            ));
            case Y -> new ArrayList<MechanicalContext>(Arrays.asList(
                    new MechanicalContext(pos.above(), Direction.UP, true),
                    new MechanicalContext(pos.below(), Direction.DOWN, true)
            ));
            case Z -> new ArrayList<MechanicalContext>(Arrays.asList(
                    new MechanicalContext(pos.north(), Direction.NORTH, true),
                    new MechanicalContext(pos.south(), Direction.SOUTH, true)
            ));
            default -> new ArrayList<MechanicalContext>();
        };
        return connection;
    }



    /**
     * Creates a new 4-way mechanical axle connection lookup map
     * <p>
     * Use this when the machine's axis is either unknown or can change
     * @param pos The position of the machine
     * @return New 4-way mechanical axle connection map
     */
    public static HashMap<Direction.Axis, ArrayList<MechanicalContext>> biAxle(BlockPos pos)
    {
        HashMap<Direction.Axis, ArrayList<MechanicalContext>> connection = new HashMap<Direction.Axis, ArrayList<MechanicalContext>>();
        connection.put(Direction.Axis.X, new ArrayList<MechanicalContext>(
                Arrays.asList(
                        new MechanicalContext(pos.above(), Direction.UP, true),
                        new MechanicalContext(pos.below(), Direction.DOWN, true),
                        new MechanicalContext(pos.north(), Direction.NORTH, true),
                        new MechanicalContext(pos.south(), Direction.SOUTH, true)
                )));

        connection.put(Direction.Axis.Y, new ArrayList<MechanicalContext>(
                Arrays.asList(
                        new MechanicalContext(pos.east(), Direction.EAST, true),
                        new MechanicalContext(pos.west(), Direction.WEST, true),
                        new MechanicalContext(pos.north(), Direction.NORTH, true),
                        new MechanicalContext(pos.south(), Direction.SOUTH, true)
                )));

        connection.put(Direction.Axis.Z, new ArrayList<MechanicalContext>(
                Arrays.asList(
                        new MechanicalContext(pos.east(), Direction.EAST, true),
                        new MechanicalContext(pos.west(), Direction.WEST, true),
                        new MechanicalContext(pos.above(), Direction.UP, true),
                        new MechanicalContext(pos.below(), Direction.DOWN, true)
                )));
        return connection;
    }



    /**
     * Creates a new 4-way mechanical axle connection array
     * <p>
     * The axis must be know ahead of time and must never change
     * @param pos The position of the machine
     * @param axis The axis that the machine is aligned with
     * @return New 4-way mechanical axle connection map
     */
    public static ArrayList<MechanicalContext> biAxle(BlockPos pos, Direction.Axis axis)
    {
        ArrayList<MechanicalContext> connection = switch (axis) {
            case X -> new ArrayList<MechanicalContext>(Arrays.asList(
                    new MechanicalContext(pos.above(), Direction.UP, true),
                    new MechanicalContext(pos.below(), Direction.DOWN, true),
                    new MechanicalContext(pos.north(), Direction.NORTH, true),
                    new MechanicalContext(pos.south(), Direction.SOUTH, true)
            ));
            case Y -> new ArrayList<MechanicalContext>(Arrays.asList(
                    new MechanicalContext(pos.east(), Direction.EAST, true),
                    new MechanicalContext(pos.west(), Direction.WEST, true),
                    new MechanicalContext(pos.north(), Direction.NORTH, true),
                    new MechanicalContext(pos.south(), Direction.SOUTH, true)
            ));
            case Z -> new ArrayList<MechanicalContext>(Arrays.asList(
                    new MechanicalContext(pos.east(), Direction.EAST, true),
                    new MechanicalContext(pos.west(), Direction.WEST, true),
                    new MechanicalContext(pos.above(), Direction.UP, true),
                    new MechanicalContext(pos.below(), Direction.DOWN, true)
            ));
            default -> new ArrayList<MechanicalContext>();
        };

        return connection;
    }



    /**
     * Creates a new 6-way mechanical axle connection array
     * @param pos The position of the machine
     * @return New 6-way mechanical axle connection array
     */
    public static ArrayList<MechanicalContext> triAxle(BlockPos pos)
    {
        ArrayList<MechanicalContext> connection = new ArrayList<MechanicalContext>(
                Arrays.asList(
                        new MechanicalContext(pos.east(), Direction.EAST, true),
                        new MechanicalContext(pos.west(), Direction.WEST, true),
                        new MechanicalContext(pos.above(), Direction.UP, true),
                        new MechanicalContext(pos.below(), Direction.DOWN, true),
                        new MechanicalContext(pos.south(), Direction.SOUTH, true),
                        new MechanicalContext(pos.north(), Direction.NORTH, true)
                ));
        return connection;
    }



    /**
     * Creates a new mechanical small cogwheel connection lookup map
     * <p>
     * Use this when the machine's axis is either unknown or can change
     * @param pos The position of the machine
     * @return New mechanical small cogwheel connection map
     */
    public static HashMap<Direction.Axis, ArrayList<MechanicalContext>> smallCogwheel(BlockPos pos)
    {
        HashMap<Direction.Axis, ArrayList<MechanicalContext>> connection = new HashMap<Direction.Axis, ArrayList<MechanicalContext>>();
        connection.put(Direction.Axis.X, new ArrayList<MechanicalContext>(
                Arrays.asList(
                        new MechanicalContext(pos.east(), Direction.EAST, true),
                        new MechanicalContext(pos.west(), Direction.WEST, true),
                        new MechanicalContext(pos.north(), Direction.NORTH, false),
                        new MechanicalContext(pos.south(), Direction.SOUTH, false),
                        new MechanicalContext(pos.above(), Direction.UP, false),
                        new MechanicalContext(pos.below(), Direction.DOWN, false)
                )));

        connection.put(Direction.Axis.Y, new ArrayList<MechanicalContext>(
                Arrays.asList(
                        new MechanicalContext(pos.east(), Direction.EAST, false),
                        new MechanicalContext(pos.west(), Direction.WEST, false),
                        new MechanicalContext(pos.north(), Direction.NORTH, false),
                        new MechanicalContext(pos.south(), Direction.SOUTH, false),
                        new MechanicalContext(pos.above(), Direction.UP, true),
                        new MechanicalContext(pos.below(), Direction.DOWN, true)
                )));

        connection.put(Direction.Axis.Z, new ArrayList<MechanicalContext>(
                Arrays.asList(
                        new MechanicalContext(pos.east(), Direction.EAST, false),
                        new MechanicalContext(pos.west(), Direction.WEST, false),
                        new MechanicalContext(pos.north(), Direction.NORTH, true),
                        new MechanicalContext(pos.south(), Direction.SOUTH, true),
                        new MechanicalContext(pos.above(), Direction.UP, false),
                        new MechanicalContext(pos.below(), Direction.DOWN, false)
                )));
        return connection;
    }



    /**
     * Creates a new mechanical small cogwheel connection array
     * <p>
     * The axis must be know ahead of time and must never change
     * @param pos The position of the machine
     * @param axis The axis that the machine is aligned with
     * @return New mechanical small cogwheel connection array
     */
    public static ArrayList<MechanicalContext> smallCogwheel(BlockPos pos, Direction.Axis axis)
    {
        ArrayList<MechanicalContext> connection = switch (axis) {
            case X -> new ArrayList<MechanicalContext>(Arrays.asList(
                    new MechanicalContext(pos.east(), Direction.EAST, true),
                    new MechanicalContext(pos.west(), Direction.WEST, true),
                    new MechanicalContext(pos.north(), Direction.NORTH, false),
                    new MechanicalContext(pos.south(), Direction.SOUTH, false),
                    new MechanicalContext(pos.above(), Direction.UP, false),
                    new MechanicalContext(pos.below(), Direction.DOWN, false)
            ));
            case Y -> new ArrayList<MechanicalContext>(Arrays.asList(
                    new MechanicalContext(pos.east(), Direction.EAST, false),
                    new MechanicalContext(pos.west(), Direction.WEST, false),
                    new MechanicalContext(pos.north(), Direction.NORTH, false),
                    new MechanicalContext(pos.south(), Direction.SOUTH, false),
                    new MechanicalContext(pos.above(), Direction.UP, true),
                    new MechanicalContext(pos.below(), Direction.DOWN, true)
            ));
            case Z -> new ArrayList<MechanicalContext>(Arrays.asList(
                    new MechanicalContext(pos.east(), Direction.EAST, false),
                    new MechanicalContext(pos.west(), Direction.WEST, false),
                    new MechanicalContext(pos.north(), Direction.NORTH, true),
                    new MechanicalContext(pos.south(), Direction.SOUTH, true),
                    new MechanicalContext(pos.above(), Direction.UP, false),
                    new MechanicalContext(pos.below(), Direction.DOWN, false)
            ));
            default -> new ArrayList<MechanicalContext>();
        };

        return connection;
    }



    /**
     * Creates a new mechanical large cogwheel connection array
     * <p>
     * The axis must be know ahead of time and must never change
     * @param pos The position of the machine
     * @param axis The axis that the machine is aligned with
     * @return New mechanical large cogwheel connection array
     */
    public static ArrayList<MechanicalContext> largeCogwheel(BlockPos pos, Direction.Axis axis)
    {
        ArrayList<MechanicalContext> connection = switch (axis) {
            case X -> new ArrayList<MechanicalContext>(Arrays.asList(
                    new MechanicalContext(pos.north(2).above(), Direction.NORTH, false),
                    new MechanicalContext(pos.north(2), Direction.NORTH, false),
                    new MechanicalContext(pos.north(2).below(), Direction.NORTH, false),

                    new MechanicalContext(pos.south(2).above(), Direction.SOUTH, false),
                    new MechanicalContext(pos.south(2), Direction.SOUTH, false),
                    new MechanicalContext(pos.south(2).below(), Direction.SOUTH, false),

                    new MechanicalContext(pos.above(2).north(), Direction.UP, false),
                    new MechanicalContext(pos.above(2), Direction.UP, false),
                    new MechanicalContext(pos.above(2).south(), Direction.UP, false),

                    new MechanicalContext(pos.below(2).north(), Direction.DOWN, false),
                    new MechanicalContext(pos.below(2), Direction.DOWN, false),
                    new MechanicalContext(pos.below(2).south(), Direction.DOWN, false),

                    new MechanicalContext(pos.east(), Direction.EAST, true),
                    new MechanicalContext(pos.west(), Direction.WEST, true)
            ));
            case Y -> new ArrayList<MechanicalContext>(Arrays.asList(
                    new MechanicalContext(pos.east(2).north(), Direction.EAST, false),
                    new MechanicalContext(pos.east(2), Direction.EAST, false),
                    new MechanicalContext(pos.east(2).south(), Direction.EAST, false),

                    new MechanicalContext(pos.west(2).north(), Direction.WEST, false),
                    new MechanicalContext(pos.west(2), Direction.WEST, false),
                    new MechanicalContext(pos.west(2).south(), Direction.WEST, false),

                    new MechanicalContext(pos.north(2).west(), Direction.NORTH, false),
                    new MechanicalContext(pos.north(2), Direction.NORTH, false),
                    new MechanicalContext(pos.north(2).east(), Direction.NORTH, false),

                    new MechanicalContext(pos.south(2).west(), Direction.SOUTH, false),
                    new MechanicalContext(pos.south(2), Direction.SOUTH, false),
                    new MechanicalContext(pos.south(2).east(), Direction.SOUTH, false),

                    new MechanicalContext(pos.above(), Direction.UP, true),
                    new MechanicalContext(pos.below(), Direction.DOWN, true)
            ));
            case Z -> new ArrayList<MechanicalContext>(Arrays.asList(
                    new MechanicalContext(pos.east(2).above(), Direction.EAST, false),
                    new MechanicalContext(pos.east(2), Direction.EAST, false),
                    new MechanicalContext(pos.east(2).below(), Direction.EAST, false),

                    new MechanicalContext(pos.west(2).above(), Direction.WEST, false),
                    new MechanicalContext(pos.west(2), Direction.WEST, false),
                    new MechanicalContext(pos.west(2).below(), Direction.WEST, false),

                    new MechanicalContext(pos.above(2).east(), Direction.UP, false),
                    new MechanicalContext(pos.above(2), Direction.UP, false),
                    new MechanicalContext(pos.above(2).west(), Direction.UP, false),

                    new MechanicalContext(pos.below(2).east(), Direction.DOWN, false),
                    new MechanicalContext(pos.below(2), Direction.DOWN, false),
                    new MechanicalContext(pos.below(2).west(), Direction.DOWN, false),

                    new MechanicalContext(pos.north(), Direction.NORTH, true),
                    new MechanicalContext(pos.south(), Direction.SOUTH, true)
            ));
            default -> new ArrayList<MechanicalContext>();
        };
        return connection;
    }
}
