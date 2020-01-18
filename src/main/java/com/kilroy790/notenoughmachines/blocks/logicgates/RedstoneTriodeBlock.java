package com.kilroy790.notenoughmachines.blocks.logicgates;

import com.kilroy790.notenoughmachines.api.stateproperties.InputDualType;
import com.kilroy790.notenoughmachines.api.stateproperties.NEMBlockStateProperties;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneDiodeBlock;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;




public abstract class RedstoneTriodeBlock extends RedstoneDiodeBlock {
	
	
	public static final EnumProperty<InputDualType> INPUT = NEMBlockStateProperties.DUAL_INPUT;
	public static final BooleanProperty NEGATED = NEMBlockStateProperties.NEGATED;
	
	
	protected RedstoneTriodeBlock(Properties builder) {
		super(builder);
	}
	
	
	protected abstract boolean getLogicFunction(World worldIn, BlockPos pos, BlockState state);
	
	
	@Override
	protected boolean shouldBePowered(World worldIn, BlockPos pos, BlockState state) {
		//return this.calculateInputStrength(worldIn, pos, state) > 0;
		return this.getLogicFunction(worldIn, pos, state);
	}
	
	
	@Override
	protected int calculateInputStrength(World worldIn, BlockPos pos, BlockState state) {
		Direction direction = state.get(HORIZONTAL_FACING).rotateY();
		BlockPos blockpos = pos.offset(direction);
		int in1 = worldIn.getRedstonePower(blockpos, direction);
		if (in1 >= 15) {
			return in1;
		} else {
			BlockState blockstate = worldIn.getBlockState(blockpos);
			return Math.max(in1, blockstate.getBlock() == Blocks.REDSTONE_WIRE ? blockstate.get(RedstoneWireBlock.POWER) : 0);
		}
	}
	
	
	protected int getInputStrengthOnSide(World worldIn, BlockPos pos, BlockState state, Direction side) {
		BlockPos blockpos = pos.offset(side);
		int in = worldIn.getRedstonePower(blockpos, side);
		if (in >= 15) {
			return in;
		} else {
			BlockState blockstate = worldIn.getBlockState(blockpos);
			return Math.max(in, blockstate.getBlock() == Blocks.REDSTONE_WIRE ? blockstate.get(RedstoneWireBlock.POWER) : 0);
		}
	}
	
	
	@Override
	protected int getActiveSignal(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return 15;
	}
	
	
	protected int getDelay(BlockState state) {
		return 2;
	}

	
	public Direction getFacing(IWorldReader worldIn, BlockPos pos, BlockState state) {
		return worldIn.getBlockState(pos).get(HORIZONTAL_FACING);
	}

	
	public InputDualType getInputs(IWorldReader worldIn, BlockPos pos, BlockState state) {
		return worldIn.getBlockState(pos).get(INPUT);
	}
	
	
	public boolean isNegated(IWorldReader worldIn, BlockPos pos, BlockState state) {
		return worldIn.getBlockState(pos).get(NEGATED);
	}
}
