package com.kilroy790.notenoughmachines.blocks.logicgates;

import java.util.Random;

import com.kilroy790.notenoughmachines.state.properties.InputDualType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class ANDGateBlock extends RedstoneTriodeBlock {

	
	public ANDGateBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(HORIZONTAL_FACING, Direction.NORTH)
				.with(INPUT, InputDualType.IN00)
				.with(NEGATED, Boolean.valueOf(false))
				.with(POWERED, Boolean.valueOf(false)));
	}
	
	
	protected boolean getLogicFunction(World worldIn, BlockPos pos, BlockState state) {
		
		Direction sideCW = worldIn.getBlockState(pos).get(HORIZONTAL_FACING).rotateY();
		Direction sideCCW = worldIn.getBlockState(pos).get(HORIZONTAL_FACING).rotateYCCW();
		
		if(state.get(NEGATED)) {
			if(this.getInputStrengthOnSide(worldIn, pos, state, sideCW) > 0 && this.getInputStrengthOnSide(worldIn, pos, state, sideCCW) > 0) return false;
			else return true;
		}
		else {
			if(this.getInputStrengthOnSide(worldIn, pos, state, sideCW) > 0 && this.getInputStrengthOnSide(worldIn, pos, state, sideCCW) > 0) return true;
			else return false;
		}
	}
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {

		Direction direction = stateIn.get(HORIZONTAL_FACING);
	    double d0 = (double)((float)pos.getX() + 0.5f) + (double)(rand.nextFloat() - 0.5f) * 0.2D;
	    double d1 = (double)((float)pos.getY() + 0.4f) + (double)(rand.nextFloat() - 0.5f) * 0.2D;
	    double d2 = (double)((float)pos.getZ() + 0.5f) + (double)(rand.nextFloat() - 0.5f) * 0.2D;
	    float f = -5.0f;
        if (rand.nextBoolean()) {
            f = 4.0f;
         }
	        
	    f = f / 16.0f;
	    double d3 = (double)(f * (float)direction.getXOffset());
	    double d4 = (double)(f * (float)direction.getZOffset());
	    worldIn.addParticle(RedstoneParticleData.REDSTONE_DUST, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
	}
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING, INPUT, NEGATED, POWERED);
	}
}
