package com.kilroy790.notenoughmachines.blocks.building;

import com.kilroy790.notenoughmachines.api.stateproperties.NEMBlockStateProperties;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;




public class HammerHeadBlock extends Block {

	
	private static final VoxelShape FACE_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);
	private static final VoxelShape NECK_SHAPE = Block.makeCuboidShape(4.0D, 3.0D, 4.0D, 12.0D, 6.0D, 12.0D);
	private static final VoxelShape HEAD_SHAPE = Block.makeCuboidShape(3.0D, 6.0D, 3.0D, 13.0D, 13.0D, 13.0D);
	private static final VoxelShape EYE_SHAPE = Block.makeCuboidShape(5.0D, 13.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	private static final VoxelShape HAMMER_SHAPE = VoxelShapes.or(FACE_SHAPE, NECK_SHAPE, HEAD_SHAPE, EYE_SHAPE);
	
	public static final BooleanProperty FORMED = NEMBlockStateProperties.FORMED;
	
	
	public HammerHeadBlock(Properties properties, String name) {
		super(properties);
		this.setRegistryName(name);
		this.setDefaultState(this.stateContainer.getBaseState().with(FORMED, false));
	}
	
	/*
	protected void onStartFalling(FallingBlockEntity fallingEntity) {
		fallingEntity.setHurtEntities(true);
	}
	
	
	public void onEndFalling(World worldIn, BlockPos pos, BlockState fallingState, BlockState hitState) {
		worldIn.playEvent(1031, pos, 0);
	}*/
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return HAMMER_SHAPE;
	}
	
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FORMED);
	}
}
