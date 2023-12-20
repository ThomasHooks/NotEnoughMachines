package com.github.thomashooks.notenoughmachines.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BasePressurePlateBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;

public class PressablePlateBlock extends BasePressurePlateBlock
{
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private final PressablePlateBlock.Sensitivity sensitivity;

    public PressablePlateBlock(PressablePlateBlock.Sensitivity sensitivity, BlockSetType blockSetType, BlockBehaviour.Properties properties)
    {
        super(properties, blockSetType);
        this.sensitivity = sensitivity;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(POWERED, Boolean.FALSE)
        );
    }

    protected int getSignalForState(BlockState state) {
        return state.getValue(POWERED) ? 15 : 0;
    }

    protected @NotNull BlockState setSignalForState(BlockState state, int signalStrength)
    {
        return state.setValue(POWERED, signalStrength > 0);
    }

    protected int getSignalStrength(@NotNull Level world, @NotNull BlockPos pos)
    {
        Class<? extends Entity> entityClass = switch (this.sensitivity)
        {
            case EVERYTHING -> Entity.class;
            case ITEMS -> ItemEntity.class;
            case MOBS -> LivingEntity.class;
            case PLAYERS -> Player.class;
            default -> throw new IncompatibleClassChangeError();
        };
        return getEntityCount(world, TOUCH_AABB.move(pos), entityClass) > 0 ? 15 : 0;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(POWERED);
    }

    public static enum Sensitivity
    {
        EVERYTHING,
        ITEMS,
        MOBS,
        PLAYERS;
    }
}
