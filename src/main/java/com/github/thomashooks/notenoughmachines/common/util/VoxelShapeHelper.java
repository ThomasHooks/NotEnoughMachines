package com.github.thomashooks.notenoughmachines.common.util;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.HashMap;
import java.util.Map;

public class VoxelShapeHelper
{
    public static final Map<Direction.Axis, Integer> AXIS_LOOKUP;
    static
    {
        AXIS_LOOKUP = new HashMap<Direction.Axis, Integer>();
        AXIS_LOOKUP.put(Direction.Axis.X, 0);
        AXIS_LOOKUP.put(Direction.Axis.Y, 1);
        AXIS_LOOKUP.put(Direction.Axis.Z, 2);
    }

    public static final VoxelShape BASE_16X2X16 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);

    public static final VoxelShape BASE_16X4X16 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);

    public static final VoxelShape BASE_16X6X16 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);

    public static final VoxelShape HALF_BLOCK = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    public static final VoxelShape BASE_16X10X16 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);

    public static final VoxelShape BASE_16X12X16 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

    public static final VoxelShape BASE_16X14X16 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public static final VoxelShape FULL_BLOCK = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public static final VoxelShape[] AXLE = new VoxelShape[]
            {
                    Block.box(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D),
                    Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D),
                    Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D)
            };

    public static final VoxelShape[] COGWHEEL_CENTER = new VoxelShape[]
            {
                    Shapes.or(AXLE[0], Block.box(5.0D, 0.0D, 0.0D, 11.0D, 16.0D, 16.0D)),
                    Shapes.or(AXLE[1], Block.box(0.0D, 5.0D, 0.0D, 16.0D, 11.0D, 16.0D)),
                    Shapes.or(AXLE[2], Block.box(0.0D, 0.0D, 5.0D, 16.0D, 16.0D, 11.0D))
            };

    public static final VoxelShape[] COGWHEEL_SIDE = new VoxelShape[]
            {
                    Block.box(5.0D, 0.0D, 0.0D, 11.0D, 16.0D, 16.0D),
                    Block.box(0.0D, 5.0D, 0.0D, 16.0D, 11.0D, 16.0D),
                    Block.box(0.0D, 0.0D, 5.0D, 16.0D, 16.0D, 11.0D)
            };

    private static final VoxelShape RUNNER_STONE = Block.box(1.0D, 6.0D, 1.0D, 15.D, 10.0D, 15.0D);
    private static final VoxelShape BED_STONE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
    public static final VoxelShape MILLSTONE = Shapes.or(AXLE[1], RUNNER_STONE, BED_STONE);

    private static final VoxelShape WATER_WHEEL_MID = Block.box(5.0D, 1.0D, 5.0D, 11.0D, 13.0D, 11.0D);
    private static final VoxelShape WATER_WHEEL_TRIL = Block.box(0.0D, 2.0D, 0.0D, 16.0D, 12.0D, 16.0D);
    public static final VoxelShape WATER_WHEEL = Shapes.or(AXLE[1], WATER_WHEEL_MID, WATER_WHEEL_TRIL);

    private static final VoxelShape GEARBOX_INNER = Block.box(2.0D, 2.0D, 2.0D, 14.0D, 14.0D, 14.0D);
    private static final VoxelShape GEARBOX_BOWL_X = Shapes.or(Block.box(2.0D, 2.0D, 0.0D, 14.D, 14.0D, 2.0D),
            Block.box(2.0D, 14.0D, 2.0D, 14.0D, 16.0D, 14.0D),
            Block.box(2.0D, 2.0D, 14.0D, 14.0D, 14.0D, 16.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D));
    private static final VoxelShape GEARBOX_OUTER_X = Shapes.join(FULL_BLOCK, GEARBOX_BOWL_X, BooleanOp.ONLY_FIRST);
    private static final VoxelShape GEARBOX_BOWL_Y = Shapes.or(Block.box(2.0D, 2.0D, 0.0D, 14.D, 14.0D, 2.0D),
            Block.box(0.0D, 2.0D, 2.0D, 2.0D, 14.0D, 14.0D),
            Block.box(2.0D, 2.0D, 14.0D, 14.0D, 14.0D, 16.0D),
            Block.box(14.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D));
    private static final VoxelShape GEARBOX_OUTER_Y = Shapes.join(FULL_BLOCK, GEARBOX_BOWL_Y, BooleanOp.ONLY_FIRST);
    private static final VoxelShape GEARBOX_BOWL_Z = Shapes.or(Block.box(2.0D, 0.0D, 2.0D, 14.D, 2.0D, 14.0D),
            Block.box(14.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D),
            Block.box(2.0D, 14.0D, 2.0D, 14.0D, 16.0D, 14.0D),
            Block.box(0.0D, 2.0D, 2.0D, 20.D, 14.0D, 14.0D));
    private static final VoxelShape GEARBOX_OUTER_Z = Shapes.join(FULL_BLOCK, GEARBOX_BOWL_Z, BooleanOp.ONLY_FIRST);
    public static final VoxelShape[] GEARBOX = new VoxelShape[]
            {
                    Shapes.or(GEARBOX_OUTER_X, GEARBOX_INNER),
                    Shapes.or(GEARBOX_OUTER_Y, GEARBOX_INNER),
                    Shapes.or(GEARBOX_OUTER_Z, GEARBOX_INNER)
            };

    public static final VoxelShape[] ENCLOSED_AXLE = new VoxelShape[]
            {
                    Shapes.or(GEARBOX_OUTER_Y, GEARBOX_OUTER_Z, GEARBOX_INNER),
                    Shapes.or(GEARBOX_OUTER_X, GEARBOX_OUTER_Z, GEARBOX_INNER),
                    Shapes.or(GEARBOX_OUTER_X, GEARBOX_OUTER_Y, GEARBOX_INNER)
            };

    private static final VoxelShape TRIP_HAMMER_FRAME_X = Block.box(2.0D, 0.0D, 0.0D, 14.0D, 16.0D, 16.0D);
    private static final VoxelShape TRIP_HAMMER_FRAME_Z = Block.box(0.0D, 0.0D, 2.0D, 16.0D, 16.0D, 14.0D);
    public static final VoxelShape[] TRIP_HAMMER_BASE = new VoxelShape[]
            {
                    Shapes.or(BASE_16X2X16, TRIP_HAMMER_FRAME_X),
                    Shapes.or(BASE_16X2X16, TRIP_HAMMER_FRAME_Z)
            };
    public static final VoxelShape[] TRIP_HAMMER_FRAME = new VoxelShape[]
            {
                    TRIP_HAMMER_FRAME_X,
                    TRIP_HAMMER_FRAME_Z
            };

    private static final VoxelShape ROLLING_MILL_FRAMEX = Block.box(2.0D, 0.0D, 0.0D, 14.0D, 16.0D, 16.0D);
    private static final VoxelShape ROLLING_MILL_FRAMEZ = Block.box(0.0D, 0.0D, 2.0D, 16.0D, 16.0D, 14.0D);
    public static final VoxelShape[] ROLLING_MILL_BASE = new VoxelShape[]
            {
                    Shapes.or(BASE_16X4X16, ROLLING_MILL_FRAMEX),
                    FULL_BLOCK,
                    Shapes.or(BASE_16X4X16, ROLLING_MILL_FRAMEZ)
            };
    public static final VoxelShape[] ROLLING_MILL_FRAME = new VoxelShape[]
            {
                    ROLLING_MILL_FRAMEX,
                    FULL_BLOCK,
                    ROLLING_MILL_FRAMEZ
            };
}
