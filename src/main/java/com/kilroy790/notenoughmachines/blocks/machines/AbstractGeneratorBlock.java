package com.kilroy790.notenoughmachines.blocks.machines;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;




public abstract class AbstractGeneratorBlock extends Block {

	
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	
	public AbstractGeneratorBlock(Properties properties, String name) {
		super(properties);
		this.setRegistryName(name);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	
	
	public DirectionProperty getPowerBlockFacing() {
		return FACING;
	}
	
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		
		return true;
	}
}
