package com.kilroy790.notenoughmachines.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;




public class PanelBlock extends Block implements IWaterLoggable 
{
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	protected static final VoxelShape DOWN_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
	protected static final VoxelShape UP_SHAPE = Block.makeCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape NORTH_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
	protected static final VoxelShape SOUTH_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape EAST_SHAPE = Block.makeCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape WEST_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);
	
	public PanelBlock(Properties properties) 
	{
		super(properties);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.DOWN).with(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		BlockPos pos = context.getPos();
		Direction facing = context.getFace().getOpposite();
		FluidState fluid = context.getWorld().getFluidState(pos);
		if (context.getWorld().getBlockState(pos.offset(facing)).getBlock() == this) 
			return this.getDefaultState().with(FACING, context.getWorld().getBlockState(pos.offset(facing)).get(FACING)).with(WATERLOGGED, Boolean.valueOf(fluid.getFluid() == Fluids.WATER));
		else 
			return this.getDefaultState().with(FACING, facing).with(WATERLOGGED, Boolean.valueOf(fluid.getFluid() == Fluids.WATER));
	}
	
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		switch (state.get(FACING)) 
		{
		case DOWN:
			return DOWN_SHAPE;
			
		case EAST:
			return EAST_SHAPE;
			
		case NORTH:
			return NORTH_SHAPE;
			
		case SOUTH:
			return SOUTH_SHAPE;
			
		case UP:
			return UP_SHAPE;
			
		case WEST:
			return WEST_SHAPE;
			
		default:
			return UP_SHAPE;
		
		}
	}
	
	
	
	@Override
	public boolean isTransparent(BlockState state) 
	{
		return true;
	}
	
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		builder.add(FACING, WATERLOGGED);
	}
}



