package com.kilroy790.notenoughmachines.blocks.logicgates;

import com.kilroy790.notenoughmachines.api.stateproperties.InputDualType;
import com.kilroy790.notenoughmachines.api.stateproperties.NEMBlockStateProperties;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneDiodeBlock;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
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
		return this.getLogicFunction(worldIn, pos, state);
	}
	
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		
		this.updateState(worldIn, pos, state);
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
	
	
	@Override
	protected void updateState(World worldIn, BlockPos pos, BlockState state) {
		
		//update the Triode's input state
		Direction sideCW = state.get(HORIZONTAL_FACING).rotateY();
		Direction sideCCW = state.get(HORIZONTAL_FACING).rotateYCCW();
		int inputStrengthCW = this.getInputStrengthOnSide(worldIn, pos, state, sideCW);
		int inputStrengthCCW = this.getInputStrengthOnSide(worldIn, pos, state, sideCCW);
		
		if(inputStrengthCW > 0 && inputStrengthCCW > 0) worldIn.setBlockState(pos, state.with(INPUT, InputDualType.IN11), 1|2);
		else if(inputStrengthCW > 0 && !(inputStrengthCCW > 0)) worldIn.setBlockState(pos, state.with(INPUT, InputDualType.IN10), 1|2);
		else if(!(inputStrengthCW > 0) && inputStrengthCCW > 0) worldIn.setBlockState(pos, state.with(INPUT, InputDualType.IN01), 1|2);
		else worldIn.setBlockState(pos, state.with(INPUT, InputDualType.IN00), 1|2);
		
		//update the Triode's powered state
		super.updateState(worldIn, pos, state);
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
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		
		if (!player.abilities.allowEdit) {
			return false;
		} 
		else {
			state = state.cycle(NEGATED);
			float f = state.get(NEGATED) == true ? 0.55f : 0.5f;
			worldIn.playSound(player, pos, SoundEvents.BLOCK_COMPARATOR_CLICK, SoundCategory.BLOCKS, 0.3f, f);
			worldIn.setBlockState(pos, state, 2);
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 1);
			return true;
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
