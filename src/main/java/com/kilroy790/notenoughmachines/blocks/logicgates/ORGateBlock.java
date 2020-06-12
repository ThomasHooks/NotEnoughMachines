package com.kilroy790.notenoughmachines.blocks.logicgates;

import com.kilroy790.notenoughmachines.state.properties.InputDualType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;




public class ORGateBlock extends RedstoneTriodeBlock {

	public ORGateBlock(Properties builder) {
		super(builder);
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(HORIZONTAL_FACING, Direction.NORTH)
				.with(INPUT, InputDualType.IN00)
				.with(NEGATED, Boolean.valueOf(false))
				.with(POWERED, Boolean.valueOf(false)));
	}

	@Override
	protected boolean getLogicFunction(World worldIn, BlockPos pos, BlockState state) {
		
		Direction sideCW = worldIn.getBlockState(pos).get(HORIZONTAL_FACING).rotateY();
		Direction sideCCW = worldIn.getBlockState(pos).get(HORIZONTAL_FACING).rotateYCCW();
		
		if(state.get(NEGATED)) {
			if(this.getInputStrengthOnSide(worldIn, pos, state, sideCW) > 0 || this.getInputStrengthOnSide(worldIn, pos, state, sideCCW) > 0) return false;
			else return true;
		}
		else {
			if(this.getInputStrengthOnSide(worldIn, pos, state, sideCW) > 0 || this.getInputStrengthOnSide(worldIn, pos, state, sideCCW) > 0) return true;
			else return false;
		}
	}
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING, INPUT, NEGATED, POWERED);
	}
}
