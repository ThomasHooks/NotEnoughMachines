package com.kilroy790.notenoughmachines.blocks.machines;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;




public class ChuteBlock extends HorizontalBlock {

	
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D);
	
	
	public ChuteBlock(Properties properties, String name) {
		super(properties);
		this.setRegistryName(name);
		this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
	}
	
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		
		Direction dir = placer.getHorizontalFacing();
		if(placer.isSneaking()) worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir), 1|2);
		else worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir.getOpposite()), 1|2);
	}
	
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		//This will prevent transparent block faces
		return BlockRenderLayer.CUTOUT_MIPPED;
		
	}
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos position, ISelectionContext context) {
		//Set the bounding box based on the direction that the block is facing
		return SHAPE;
	}

	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING);
	}
}
