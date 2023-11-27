package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.util.VoxelShapeHelper;
import com.github.thomashooks.notenoughmachines.world.block.base.IMultiBlockPart;
import com.github.thomashooks.notenoughmachines.world.block.base.RotatingShaftBlock;
import com.github.thomashooks.notenoughmachines.world.block.entity.AllBlockEntities;
import com.github.thomashooks.notenoughmachines.world.block.entity.CogwheelLargeBlockEntity;
import com.github.thomashooks.notenoughmachines.world.block.entity.base.MechanicalBlockEntity;
import com.github.thomashooks.notenoughmachines.world.block.state.AllBlockStateProperties;
import com.github.thomashooks.notenoughmachines.world.block.state.MultiBlockPart3x1x3;
import com.github.thomashooks.notenoughmachines.world.power.MechanicalConnectionHelper;
import com.github.thomashooks.notenoughmachines.world.power.MechanicalContext;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CogwheelLargeBlock extends RotatingShaftBlock implements IMultiBlockPart
{
    public static final EnumProperty<MultiBlockPart3x1x3> COGWHEEL_PART = AllBlockStateProperties.LARGE_COGWHEEL_PART;

    public CogwheelLargeBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(COGWHEEL_PART, MultiBlockPart3x1x3.CENTER));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> toolTips, TooltipFlag flags)
    {
        toolTips.add(Component.literal("\u00A77" + "Press " + "\u00A72" + "SHIFT" + "\u00A77" + " for more information").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = super.getStateForPlacement(context).setValue(AXIS, context.getClickedFace().getAxis());
        return isValidForPlacement(world, pos, state) ? state.setValue(COGWHEEL_PART, MultiBlockPart3x1x3.CENTER) : null;
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) //was onBlockPlacedBy()
    {
        super.setPlacedBy(world, pos, state, placer, stack);
        BlockState shiftedState = world.getBlockState(pos);
        int partIndex = 0;
        switch (shiftedState.getValue(AXIS))
        {
            case X ->
            {
                for (int y = 1; y >= -1; y--)
                {
                    for (int z = -1; z <= 1; z++)
                    {
                        if (z == 0 && y == 0)
                        {
                            //Need to skip the center as we have already placed it
                            partIndex++;
                            continue;
                        }
                        world.setBlock(pos.offset(0, y, z), state.setValue(COGWHEEL_PART, MultiBlockPart3x1x3.fromIndex(partIndex)).setValue(SHIFTED, shiftedState.getValue(SHIFTED)), 1 | 2);
                        partIndex++;
                    }
                }
            }
            case Y ->
            {
                for (int z = -1; z <= 1; z++)
                {
                    for (int x = -1; x <= 1; x++)
                    {
                        if (x == 0 && z == 0)
                        {
                            partIndex++;
                            continue;
                        }
                        world.setBlock(pos.offset(x, 0, z), state.setValue(COGWHEEL_PART, MultiBlockPart3x1x3.fromIndex(partIndex)).setValue(SHIFTED, shiftedState.getValue(SHIFTED)), 1 | 2);
                        partIndex++;
                    }
                }
            }
            case Z ->
            {
                for (int y = 1; y >= -1; y--)
                {
                    for (int x = -1; x <= 1; x++)
                    {
                        if (x == 0 && y == 0)
                        {
                            partIndex++;
                            continue;
                        }
                        world.setBlock(pos.offset(x, y, 0), state.setValue(COGWHEEL_PART, MultiBlockPart3x1x3.fromIndex(partIndex)).setValue(SHIFTED, shiftedState.getValue(SHIFTED)), 1 | 2);
                        partIndex++;
                    }
                }
            }
        }
    }

    @Override
    public void onRemove(BlockState oldState, Level world, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (!world.isClientSide() && newState.isAir())
        {
            MultiBlockPart3x1x3 part = oldState.getValue(COGWHEEL_PART);
            Direction.Axis axis = oldState.getValue(AXIS);
            BlockPos centerPos = getCoreBlockPos(oldState, pos);
            if (part == MultiBlockPart3x1x3.CENTER)
                NotEnoughMachines.AETHER.removeFromPowerNetwork(getMechanicalEntity(world, centerPos, oldState));
            setMultiBlockState(world, centerPos, oldState, newState, 1 | 2 | 32);
        }
        super.onRemove(oldState, world, pos, newState, isMoving);
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player)
    {
        if (!world.isClientSide() && !player.isCreative() && player.hasCorrectToolForDrops(state))
            dropResources(state.setValue(COGWHEEL_PART, MultiBlockPart3x1x3.CENTER), world, pos, null, player, player.getMainHandItem());
        super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack itemStack)
    {
        super.playerDestroy(world, player, pos, Blocks.AIR.defaultBlockState(), blockEntity, itemStack);
    }

    @Override
    public void onBlockExploded(BlockState state, Level level, BlockPos pos, Explosion explosion)
    {
        dropResources(state.setValue(COGWHEEL_PART, MultiBlockPart3x1x3.CENTER), level, pos);
        super.onBlockExploded(state, level, pos, explosion);
    }

    private void setMultiBlockState(Level world, BlockPos centerPos, BlockState currentState, BlockState newState, int flags)
    {
        switch (currentState.getValue(AXIS))
        {
            case X -> //Axis is East/West
            {
                for (int y = 1; y >= -1; y--)
                {
                    for (int z = -1; z <= 1; z++)
                    {
                        world.setBlock(centerPos.offset(0, y, z), newState, flags);
                    }
                }
            }
            case Y -> //Axis is Up/Down
            {
                for (int z = -1; z <= 1; z++)
                {
                    for (int x = -1; x <= 1; x++)
                    {
                        world.setBlock(centerPos.offset(x, 0, z), newState, flags);
                    }
                }
            }
            case Z -> //Axis is South/North
            {
                for (int y = 1; y >= -1; y--)
                {
                    for (int x = -1; x <= 1; x++)
                    {
                        world.setBlock(centerPos.offset(x, y, 0), newState, flags);
                    }
                }
            }
            default -> throw new IllegalStateException(NotEnoughMachines.MOD_ID + ":CogwheelLargeBlock is in an unknown state!");
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext)
    {
        return switch (state.getValue(COGWHEEL_PART))
        {
            case BOTTOM, BOTTOM_LEFT, BOTTOM_RIGHT,
                    LEFT, RIGHT,
                    TOP, TOP_LEFT, TOP_RIGHT -> VoxelShapeHelper.COGWHEEL_SIDE[VoxelShapeHelper.AXIS_LOOKUP.get(state.getValue(AXIS))];
            case CENTER -> VoxelShapeHelper.COGWHEEL_CENTER[VoxelShapeHelper.AXIS_LOOKUP.get(state.getValue(AXIS))];
            default -> throw new IllegalStateException(NotEnoughMachines.MOD_ID + ":CogwheelLargeBlock is in an unknown state!");
        };
    }

    @Override
    public ArrayList<MechanicalContext> getMechanicalConnections(Level world, BlockPos pos, BlockState state)
    {
        return MechanicalConnectionHelper.largeCogwheel(getCoreBlockEntity(world, pos, state).getBlockPos(), state.getValue(AXIS));
    }

    @Override
    public MechanicalBlockEntity getMechanicalEntity(LevelAccessor world, BlockPos pos, BlockState state)
    {
        MechanicalBlockEntity machine = getCoreBlockEntity((Level) world, pos, state) instanceof MechanicalBlockEntity ? (MechanicalBlockEntity) getCoreBlockEntity((Level) world, pos, state) : null;
        return Objects.requireNonNull(machine, NotEnoughMachines.MOD_ID + ":MechanicalBlock '" + state.getBlock().getDescriptionId() + "' block entity must be an instance of MechanicalBlockEntity!");
    }

    public BlockEntity getCoreBlockEntity(Level world, BlockPos pos, BlockState state)
    {
        MultiBlockPart3x1x3 part = state.getValue(COGWHEEL_PART);
        Direction.Axis axis = state.getValue(AXIS);
        return world.getBlockEntity(getCoreBlockPos(state, pos));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(COGWHEEL_PART);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> blockEntityType)
    {
        return CogwheelLargeBlockEntity.getTicker(world);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        if (state.getValue(COGWHEEL_PART) == MultiBlockPart3x1x3.CENTER)
            return AllBlockEntities.COGWHEEL_LARGE.get().create(pos, state);
        else
            return null;
    }

    @Override
    public BlockPos getCoreBlockPos(BlockState state, BlockPos pos)
    {
        MultiBlockPart3x1x3 part = state.getValue(COGWHEEL_PART);
        Direction.Axis axis = state.getValue(AXIS);
        return switch (part)
        {
            case TOP_LEFT -> switch (axis)
            {
                case X -> pos.offset(0, -1, 1);
                case Y -> pos.offset(1, 0, 1);
                case Z -> pos.offset(1, -1, 0);
            };
            case TOP -> switch (axis)
            {
                case X -> pos.offset(0, -1, 0);
                case Y -> pos.offset(0, 0, 1);
                case Z -> pos.offset(0, -1, 0);
            };
            case TOP_RIGHT -> switch (axis)
            {
                case X -> pos.offset(0, -1, -1);
                case Y -> pos.offset(-1, 0, 1);
                case Z -> pos.offset(-1, -1, 0);
            };
            case LEFT -> switch (axis)
            {
                case X -> pos.offset(0, 0, 1);
                case Y -> pos.offset(1, 0, 0);
                case Z -> pos.offset(1, 0, 0);
            };
            case CENTER -> pos;

            case RIGHT -> switch (axis)
            {
                case X -> pos.offset(0, 0, -1);
                case Y -> pos.offset(-1, 0, 0);
                case Z -> pos.offset(-1, 0, 0);
            };
            case BOTTOM_LEFT -> switch (axis)
            {
                case X -> pos.offset(0, 1, 1);
                case Y -> pos.offset(1, 0, -1);
                case Z -> pos.offset(1, 1, 0);
            };
            case BOTTOM -> switch (axis)
            {
                case X -> pos.offset(0, 1, 0);
                case Y -> pos.offset(0, 0, -1);
                case Z -> pos.offset(0, 1, 0);
            };
            case BOTTOM_RIGHT -> switch (axis)
            {
                case X -> pos.offset(0, 1, -1);
                case Y -> pos.offset(-1, 0, -1);
                case Z -> pos.offset(-1, 1, 0);
            };
            default -> throw new IllegalStateException(":getCoreBlockPos(): CogwheelLargeBlock is in an unknown state!");
        };
    }

    @Override
    public boolean isValidForPlacement(Level world, BlockPos corePos, BlockState state)
    {
        return switch (state.getValue(AXIS))
        {
            case X -> corePos.getY() < world.getMaxBuildHeight() - 1
                    && world.getBlockState(corePos.offset(0, 1, -1)).canBeReplaced()
                    && world.getBlockState(corePos.offset(0, 1, 0)).canBeReplaced()
                    && world.getBlockState(corePos.offset(0, 1, 1)).canBeReplaced()
                    && world.getBlockState(corePos.offset(0, 0, -1)).canBeReplaced()
                    && world.getBlockState(corePos.offset(0, 0, 1)).canBeReplaced()
                    && world.getBlockState(corePos.offset(0, -1, -1)).canBeReplaced()
                    && world.getBlockState(corePos.offset(0, -1, 0)).canBeReplaced()
                    && world.getBlockState(corePos.offset(0, -1, 1)).canBeReplaced();

            case Y -> corePos.getY() < world.getMaxBuildHeight() - 1
                    &&world.getBlockState(corePos.offset(-1, 0, -1)).canBeReplaced()
                    && world.getBlockState(corePos.offset(0, 0, -1)).canBeReplaced()
                    && world.getBlockState(corePos.offset(1, 0, -1)).canBeReplaced()
                    && world.getBlockState(corePos.offset(-1, 0, 0)).canBeReplaced()
                    && world.getBlockState(corePos.offset(1, 0, 0)).canBeReplaced()
                    && world.getBlockState(corePos.offset(-1, 0, 1)).canBeReplaced()
                    && world.getBlockState(corePos.offset(0, 0, 1)).canBeReplaced()
                    && world.getBlockState(corePos.offset(1, 0, 1)).canBeReplaced();

            case Z -> corePos.getY() < world.getMaxBuildHeight() - 1
                    && world.getBlockState(corePos.offset(-1, 1, 0)).canBeReplaced()
                    && world.getBlockState(corePos.offset(0, 1, 0)).canBeReplaced()
                    && world.getBlockState(corePos.offset(1, 1, 0)).canBeReplaced()
                    && world.getBlockState(corePos.offset(-1, 0, 0)).canBeReplaced()
                    && world.getBlockState(corePos.offset(1, 0, 0)).canBeReplaced()
                    && world.getBlockState(corePos.offset(-1, -1, 0)).canBeReplaced()
                    && world.getBlockState(corePos.offset(0, -1, 0)).canBeReplaced()
                    && world.getBlockState(corePos.offset(1, -1, 0)).canBeReplaced();

            default -> throw new IllegalStateException(NotEnoughMachines.MOD_ID + ":isValidForPlacement():CogwheelLargeBlock is in an unknown state!");
        };
    }

    @Override
    public boolean isValid(Level world, BlockPos pos, BlockState state)
    {
        BlockPos corePos = getCoreBlockPos(state, pos);
        return switch (state.getValue(AXIS))
        {
            case X -> corePos.getY() < world.getMaxBuildHeight() - 1
                    && world.getBlockState(corePos.offset(0, 1, -1)).is(this)
                    && world.getBlockState(corePos.offset(0, 1, 0)).is(this)
                    && world.getBlockState(corePos.offset(0, 1, 1)).is(this)
                    && world.getBlockState(corePos.offset(0, 0, -1)).is(this)
                    && world.getBlockState(corePos.offset(0, 0, 1)).is(this)
                    && world.getBlockState(corePos.offset(0, -1, -1)).is(this)
                    && world.getBlockState(corePos.offset(0, -1, 0)).is(this)
                    && world.getBlockState(corePos.offset(0, -1, 1)).is(this);

            case Y -> corePos.getY() < world.getMaxBuildHeight() - 1
                    && world.getBlockState(corePos.offset(-1, 0, -1)).is(this)
                    && world.getBlockState(corePos.offset(0, 0, -1)).is(this)
                    && world.getBlockState(corePos.offset(1, 0, -1)).is(this)
                    && world.getBlockState(corePos.offset(-1, 0, 0)).is(this)
                    && world.getBlockState(corePos.offset(1, 0, 0)).is(this)
                    && world.getBlockState(corePos.offset(-1, 0, 1)).is(this)
                    && world.getBlockState(corePos.offset(0, 0, 1)).is(this)
                    && world.getBlockState(corePos.offset(1, 0, 1)).is(this);

            case Z -> corePos.getY() < world.getMaxBuildHeight() - 1
                    && world.getBlockState(corePos.offset(-1, 1, 0)).is(this)
                    && world.getBlockState(corePos.offset(0, 1, 0)).is(this)
                    && world.getBlockState(corePos.offset(1, 1, 0)).is(this)
                    && world.getBlockState(corePos.offset(-1, 0, 0)).is(this)
                    && world.getBlockState(corePos.offset(1, 0, 0)).is(this)
                    && world.getBlockState(corePos.offset(-1, -1, 0)).is(this)
                    && world.getBlockState(corePos.offset(0, -1, 0)).is(this)
                    && world.getBlockState(corePos.offset(1, -1, 0)).is(this);

            default -> throw new IllegalStateException(NotEnoughMachines.MOD_ID + ":isValid():CogwheelLargeBlock is in an unknown state!");
        };
    }
}
