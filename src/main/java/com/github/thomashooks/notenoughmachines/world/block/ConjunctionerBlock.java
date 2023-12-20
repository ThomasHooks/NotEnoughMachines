package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.world.block.state.properties.InputDualType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class ConjunctionerBlock extends TriodeBlock
{
    protected ConjunctionerBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(DUAL_INPUT, InputDualType.IN00)
                .setValue(NEGATED, Boolean.FALSE)
                .setValue(POWERED, Boolean.FALSE));
    }

    @Override
    protected boolean getLogicFunction(Level world, BlockPos pos, BlockState state)
    {
        Direction sideCW = state.getValue(FACING).getClockWise();
        Direction sideCCW = state.getValue(FACING).getCounterClockWise();
        if (state.getValue(NEGATED))
            return !(this.getInputStrengthOnSide(world, pos, state, sideCW) > 0 && this.getInputStrengthOnSide(world, pos, state, sideCCW) > 0);
        else
            return this.getInputStrengthOnSide(world, pos, state, sideCW) > 0 && this.getInputStrengthOnSide(world, pos, state, sideCCW) > 0;
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource randomSource)
    {
        if (state.getValue(POWERED))
        {
            Direction direction = state.getValue(FACING);
            double d0 = (double)pos.getX() + 0.5D + (randomSource.nextDouble() - 0.5D) * 0.2D;
            double d1 = (double)pos.getY() + 0.4D + (randomSource.nextDouble() - 0.5D) * 0.2D;
            double d2 = (double)pos.getZ() + 0.5D + (randomSource.nextDouble() - 0.5D) * 0.2D;
            float f = -5.0F;
            if (randomSource.nextBoolean())
            {
                f = 4.0F;
            }

            f /= 16.0F;
            double d3 = (double)(f * (float)direction.getStepX());
            double d4 = (double)(f * (float)direction.getStepZ());
            world.addParticle(DustParticleOptions.REDSTONE, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder)
    {
        stateBuilder.add(FACING, DUAL_INPUT, NEGATED, POWERED);
    }
}
