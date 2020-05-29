package com.kilroy790.notenoughmachines.api.lists;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;




public class ShapeList {

	public static final VoxelShape[] AXLE = new VoxelShape[]{
			Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D), 
			Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D), 
			Block.makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D)
	};
	
	
	private static final VoxelShape RUNNERSTONE = Block.makeCuboidShape(1.0D, 6.0D, 1.0D, 15.D, 10.0D, 15.0D);
	private static final VoxelShape BEDSTONE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
	public static final VoxelShape MILLSTONE = VoxelShapes.or(AXLE[1], RUNNERSTONE, BEDSTONE);
}







