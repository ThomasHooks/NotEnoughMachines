package com.kilroy790.notenoughmachines.utilities;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;




public class NEMBlockShapes {
	
	public static final Map<Direction.Axis, Integer> AXIS_LOOKUP;
	static {
		AXIS_LOOKUP = new HashMap<Direction.Axis, Integer>();
		AXIS_LOOKUP.put(Direction.Axis.X, 0);
		AXIS_LOOKUP.put(Direction.Axis.Y, 1);
		AXIS_LOOKUP.put(Direction.Axis.Z, 2);
	}

	
	
	public static final VoxelShape FULL_BLOCK = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	
	
	
	private static final VoxelShape BASE16X2X16 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
	
	
	
	public static final VoxelShape[] AXLE = new VoxelShape[] {
			Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D), 
			Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D), 
			Block.makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D)
	};
	
	
	
	public static final VoxelShape[] COGWHEEL_CENTER = new VoxelShape[] {
			VoxelShapes.or(AXLE[0], Block.makeCuboidShape(5.0D, 0.0D, 0.0D, 11.0D, 16.0D, 16.0D)),
			VoxelShapes.or(AXLE[1], Block.makeCuboidShape(0.0D, 5.0D, 0.0D, 16.0D, 11.0D, 16.0D)),
			VoxelShapes.or(AXLE[2], Block.makeCuboidShape(0.0D, 0.0D, 5.0D, 16.0D, 16.0D, 11.0D))
	};
	
	
	
	public static final VoxelShape[] COGWHEEL_SIDE = new VoxelShape[] {
			Block.makeCuboidShape(5.0D, 0.0D, 0.0D, 11.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 5.0D, 0.0D, 16.0D, 11.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 5.0D, 16.0D, 16.0D, 11.0D)
	};
	
	
	
	private static final VoxelShape RUNNERSTONE = Block.makeCuboidShape(1.0D, 6.0D, 1.0D, 15.D, 10.0D, 15.0D);
	private static final VoxelShape BEDSTONE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
	public static final VoxelShape MILLSTONE = VoxelShapes.or(AXLE[1], RUNNERSTONE, BEDSTONE);
	
	
	
	private static final VoxelShape TUBWHEELMID = Block.makeCuboidShape(5.0D, 1.0D, 5.0D, 11.0D, 13.0D, 11.0D);
	private static final VoxelShape TUBWHEELTRIL = Block.makeCuboidShape(0.0D, 2.0D, 0.0D, 16.0D, 12.0D, 16.0D);
	public static final VoxelShape TUBWHEEL = VoxelShapes.or(AXLE[1], TUBWHEELMID, TUBWHEELTRIL);
	
	
	
	private static final VoxelShape GEARBOX_INNER = Block.makeCuboidShape(2.0D, 2.0D, 2.0D, 14.0D, 14.0D, 14.0D);
	private static final VoxelShape GEARBOX_BOWL_X = VoxelShapes.or(Block.makeCuboidShape(2.0D, 2.0D, 0.0D, 14.D, 14.0D, 2.0D), 
			Block.makeCuboidShape(2.0D, 14.0D, 2.0D, 14.0D, 16.0D, 14.0D), 
			Block.makeCuboidShape(2.0D, 2.0D, 14.0D, 14.0D, 14.0D, 16.0D),
			Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 2.0D, 14.0D));
	private static final VoxelShape GEARBOX_OUTER_X = VoxelShapes.combineAndSimplify(FULL_BLOCK, GEARBOX_BOWL_X, IBooleanFunction.ONLY_FIRST);
	private static final VoxelShape GEARBOX_BOWL_Y = VoxelShapes.or(Block.makeCuboidShape(2.0D, 2.0D, 0.0D, 14.D, 14.0D, 2.0D), 
			Block.makeCuboidShape(0.0D, 2.0D, 2.0D, 2.0D, 14.0D, 14.0D), 
			Block.makeCuboidShape(2.0D, 2.0D, 14.0D, 14.0D, 14.0D, 16.0D),
			Block.makeCuboidShape(14.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D));
	private static final VoxelShape GEARBOX_OUTER_Y = VoxelShapes.combineAndSimplify(FULL_BLOCK, GEARBOX_BOWL_Y, IBooleanFunction.ONLY_FIRST);
	private static final VoxelShape GEARBOX_BOWL_Z = VoxelShapes.or(Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.D, 2.0D, 14.0D), 
			Block.makeCuboidShape(14.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D), 
			Block.makeCuboidShape(2.0D, 14.0D, 2.0D, 14.0D, 16.0D, 14.0D),
			Block.makeCuboidShape(0.0D, 2.0D, 2.0D, 20.D, 14.0D, 14.0D));
	private static final VoxelShape GEARBOX_OUTER_Z = VoxelShapes.combineAndSimplify(FULL_BLOCK, GEARBOX_BOWL_Z, IBooleanFunction.ONLY_FIRST);
	public static final VoxelShape[] GEARBOX = new VoxelShape[] {
			VoxelShapes.or(GEARBOX_OUTER_X, GEARBOX_INNER),
			VoxelShapes.or(GEARBOX_OUTER_Y, GEARBOX_INNER),
			VoxelShapes.or(GEARBOX_OUTER_Z, GEARBOX_INNER)
	};
	
	
	
	private static final VoxelShape TRIPHAMMER_FRAMEX = Block.makeCuboidShape(2.0D, 0.0D, 0.0D, 14.0D, 16.0D, 16.0D);
	private static final VoxelShape TRIPHAMMER_FRAMEZ = Block.makeCuboidShape(0.0D, 0.0D, 2.0D, 16.0D, 16.0D, 14.0D);
	public static final VoxelShape[] TRIPHAMMER_BASE = new VoxelShape[] {
			VoxelShapes.or(BASE16X2X16, TRIPHAMMER_FRAMEX),
			FULL_BLOCK,
			VoxelShapes.or(BASE16X2X16, TRIPHAMMER_FRAMEZ)
	};
	public static final VoxelShape[] TRIPHAMMER_FRAME = new VoxelShape[] {
			TRIPHAMMER_FRAMEX,
			FULL_BLOCK,
			TRIPHAMMER_FRAMEZ
	};
}







