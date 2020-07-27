package com.kilroy790.notenoughmachines.blocks.machines;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;




abstract public class MechanicalHorizontalBlock extends MechanicalBlock {

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	public MechanicalHorizontalBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
	}
	
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(FACING);
	}
}







