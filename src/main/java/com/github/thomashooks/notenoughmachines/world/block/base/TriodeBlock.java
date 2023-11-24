package com.github.thomashooks.notenoughmachines.world.block.base;

import com.github.thomashooks.notenoughmachines.world.block.state.InputDualType;
import com.github.thomashooks.notenoughmachines.world.block.state.AllBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DiodeBlock;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

public abstract class TriodeBlock extends DiodeBlock
{
    public static final EnumProperty<InputDualType> DUAL_INPUT = AllBlockStateProperties.DUAL_INPUT;
    public static final BooleanProperty NEGATED = AllBlockStateProperties.NEGATED;

    protected TriodeBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if (!player.getAbilities().mayBuild)
        {
            return InteractionResult.PASS;
        }
        else
        {
            state = state.cycle(NEGATED);
            float pitch = state.getValue(NEGATED) ? 0.55F : 0.5F;
            world.playSound(player, pos, SoundEvents.COMPARATOR_CLICK, SoundSource.BLOCKS, 0.3F,pitch);
            world.setBlock(pos, state, 1 | 2);
            world.scheduleTick(pos, this,getDelay(state));
            return InteractionResult.sidedSuccess(world.isClientSide);
        }
    }

    @Override
    protected boolean shouldTurnOn(Level world, BlockPos pos, BlockState state)
    {
        return this.getLogicFunction(world, pos, state);
    }

    protected abstract boolean getLogicFunction(Level world, BlockPos pos, BlockState state);

    @Override
    protected void checkTickOnNeighbor(Level world, BlockPos pos, BlockState state)
    {
        Direction sideCW = state.getValue(FACING).getClockWise();
        Direction sideCCW = state.getValue(FACING).getCounterClockWise();
        int inputStrengthCW = this.getInputStrengthOnSide(world, pos, state, sideCW);
        int inputStrengthCCW = this.getInputStrengthOnSide(world, pos, state, sideCCW);

        if (inputStrengthCW > 0 && inputStrengthCCW > 0)
        {
            world.setBlock(pos, state.setValue(DUAL_INPUT, InputDualType.IN11), 1 | 2); //N both sides
        }
        else if (inputStrengthCW > 0)
        {
            world.setBlock(pos, state.setValue(DUAL_INPUT, InputDualType.IN10), 1 | 2); //E Right side
        }
        else if (inputStrengthCCW > 0)
        {
            world.setBlock(pos, state.setValue(DUAL_INPUT, InputDualType.IN01), 1 | 2); //W Left side
        }
        else
        {
            world.setBlock(pos, state.setValue(DUAL_INPUT, InputDualType.IN00), 1 | 2); // none
        }
        super.checkTickOnNeighbor(world, pos, state);
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        this.checkTickOnNeighbor(world, pos, state);
        super.setPlacedBy(world, pos, state, placer, stack);
    }

    protected int getInputStrengthOnSide(Level worldIn, BlockPos pos, BlockState state, Direction side)
    {
        BlockPos blockpos = pos.relative(side);
        int input = worldIn.getSignal(blockpos, side);
        if (input >= 15)
        {
            return input;
        }
        else
        {
            BlockState blockstate = worldIn.getBlockState(blockpos);
            return Math.max(input, blockstate.getBlock() == Blocks.REDSTONE_WIRE ? blockstate.getValue(RedStoneWireBlock.POWER) : 0);
        }
    }

    @Override
    protected int getDelay(BlockState state)
    {
        return 2;
    }
}
