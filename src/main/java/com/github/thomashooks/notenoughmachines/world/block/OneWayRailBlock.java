package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.common.util.KeyboardInputHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OneWayRailBlock extends RedstoneBoosterRailBlock
{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public OneWayRailBlock(Properties properties)
    {
        super(properties, true);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(SHAPE, RailShape.NORTH_SOUTH)
                .setValue(POWERED, false)
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public void onMinecartPass(BlockState state, Level level, BlockPos pos, AbstractMinecart cart)
    {
        boolean railIsNotPowered = !state.getValue(POWERED);
        Direction railFacing = state.getValue(FACING);
        Direction cartMovementDirection = getMinecartMovementDirection(cart);
        if (railIsNotPowered)
            this.stopMinecart(state, level, pos, cart);
        else if (railFacing != cartMovementDirection && cartMovementDirection != null)
            this.boostMinecart(state, level, pos, cart, -DEFAULT_BOOST_FACTOR, 0.0D);
        else
            this.boostMinecart(state, level, pos, cart);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> toolTips, TooltipFlag flag)
    {
        if (KeyboardInputHelper.isPressingShift())
        {
            toolTips.add(Component.literal(""));
            toolTips.add(Component.literal("Acts like a one-way powered rail").withStyle(ChatFormatting.GREEN));
            toolTips.add(Component.literal("Minecarts traveling against the arrows will be reversed").withStyle(ChatFormatting.GRAY));
        }
        else
            toolTips.add(Component.literal(KeyboardInputHelper.MORE_INFO_PRESS_SHIFT).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void onRemove(BlockState oldState, Level world, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (!world.isClientSide && !newState.isAir())
        {
            //Need to update its facing direction as the rail shape is changed inside RailState and not in BaseRailBlock
            if (oldState.getValue(SHAPE) != newState.getValue(SHAPE)
                    && !((oldState.getValue(SHAPE) == RailShape.NORTH_SOUTH && newState.getValue(SHAPE) == RailShape.ASCENDING_NORTH)
                        || (oldState.getValue(SHAPE) == RailShape.NORTH_SOUTH && newState.getValue(SHAPE) == RailShape.ASCENDING_SOUTH)
                        || (oldState.getValue(SHAPE) == RailShape.EAST_WEST && newState.getValue(SHAPE) == RailShape.ASCENDING_EAST)
                        || (oldState.getValue(SHAPE) == RailShape.EAST_WEST && newState.getValue(SHAPE) == RailShape.ASCENDING_WEST)))
                updateFacing(oldState, world, pos, newState);
        }
        super.onRemove(oldState, world, pos, newState, isMoving);
    }

    protected void updateFacing(BlockState oldState, Level world, BlockPos pos, BlockState newState)
    {
        boolean connectedToNorth = BaseRailBlock.isRail(world, pos.north());
        boolean connectedToNorthAscending = BaseRailBlock.isRail(world, pos.north().above());
        boolean connectedToEast = BaseRailBlock.isRail(world, pos.east());
        boolean connectedToEastAscending = BaseRailBlock.isRail(world, pos.east().above());
        boolean connectedToSouth = BaseRailBlock.isRail(world, pos.south());
        boolean connectedToSouthAscending = BaseRailBlock.isRail(world, pos.south().above());
        boolean connectedToWest = BaseRailBlock.isRail(world, pos.west());
        boolean connectedToWestAscending = BaseRailBlock.isRail(world, pos.west().above());
        switch (oldState.getValue(FACING))
        {
            case NORTH ->
            {
                if (connectedToEast || connectedToEastAscending)
                    world.setBlock(pos, newState.setValue(FACING, oldState.getValue(FACING).getCounterClockWise()), 3);
                else if (connectedToWest || connectedToWestAscending)
                    world.setBlock(pos, newState.setValue(FACING, oldState.getValue(FACING).getClockWise()), 3);
            }
            case EAST ->
            {
                if (connectedToNorth || connectedToNorthAscending)
                    world.setBlock(pos, newState.setValue(FACING, oldState.getValue(FACING).getClockWise()), 3);
                else if (connectedToSouth || connectedToSouthAscending)
                    world.setBlock(pos, newState.setValue(FACING, oldState.getValue(FACING).getCounterClockWise()), 3);
            }
            case SOUTH ->
            {
                if (connectedToEast || connectedToEastAscending)
                    world.setBlock(pos, newState.setValue(FACING, oldState.getValue(FACING).getClockWise()), 3);
                else if (connectedToWest || connectedToWestAscending)
                    world.setBlock(pos, newState.setValue(FACING, oldState.getValue(FACING).getCounterClockWise()), 3);
            }
            case WEST ->
            {
                if (connectedToNorth || connectedToNorthAscending)
                    world.setBlock(pos, newState.setValue(FACING, oldState.getValue(FACING).getCounterClockWise()), 3);
                else if (connectedToSouth || connectedToSouthAscending)
                    world.setBlock(pos, newState.setValue(FACING, oldState.getValue(FACING).getClockWise()), 3);
            }
        }
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation)
    {
        return switch (rotation)
        {
            case CLOCKWISE_180 -> switch ((RailShape) state.getValue(SHAPE))
            {
                case ASCENDING_EAST ->
                        state.setValue(SHAPE, RailShape.ASCENDING_WEST).setValue(FACING, state.getValue(FACING).getOpposite());
                case ASCENDING_WEST ->
                        state.setValue(SHAPE, RailShape.ASCENDING_EAST).setValue(FACING, state.getValue(FACING).getOpposite());
                case ASCENDING_NORTH ->
                        state.setValue(SHAPE, RailShape.ASCENDING_SOUTH).setValue(FACING, state.getValue(FACING).getOpposite());
                case ASCENDING_SOUTH ->
                        state.setValue(SHAPE, RailShape.ASCENDING_NORTH).setValue(FACING, state.getValue(FACING).getOpposite());
                case SOUTH_EAST ->
                        state.setValue(SHAPE, RailShape.NORTH_WEST).setValue(FACING, state.getValue(FACING).getOpposite());
                case SOUTH_WEST ->
                        state.setValue(SHAPE, RailShape.NORTH_EAST).setValue(FACING, state.getValue(FACING).getOpposite());
                case NORTH_WEST ->
                        state.setValue(SHAPE, RailShape.SOUTH_EAST).setValue(FACING, state.getValue(FACING).getOpposite());
                case NORTH_EAST ->
                        state.setValue(SHAPE, RailShape.SOUTH_WEST).setValue(FACING, state.getValue(FACING).getOpposite());
                case NORTH_SOUTH, EAST_WEST -> state;
            };
            case COUNTERCLOCKWISE_90 -> switch ((RailShape) state.getValue(SHAPE))
            {
                case NORTH_SOUTH ->
                        state.setValue(SHAPE, RailShape.EAST_WEST).setValue(FACING, state.getValue(FACING).getCounterClockWise());
                case EAST_WEST ->
                        state.setValue(SHAPE, RailShape.NORTH_SOUTH).setValue(FACING, state.getValue(FACING).getCounterClockWise());
                case ASCENDING_EAST ->
                        state.setValue(SHAPE, RailShape.ASCENDING_NORTH).setValue(FACING, state.getValue(FACING).getCounterClockWise());
                case ASCENDING_WEST ->
                        state.setValue(SHAPE, RailShape.ASCENDING_SOUTH).setValue(FACING, state.getValue(FACING).getCounterClockWise());
                case ASCENDING_NORTH ->
                        state.setValue(SHAPE, RailShape.ASCENDING_WEST).setValue(FACING, state.getValue(FACING).getCounterClockWise());
                case ASCENDING_SOUTH ->
                        state.setValue(SHAPE, RailShape.ASCENDING_EAST).setValue(FACING, state.getValue(FACING).getCounterClockWise());
                case SOUTH_EAST ->
                        state.setValue(SHAPE, RailShape.NORTH_EAST).setValue(FACING, state.getValue(FACING).getCounterClockWise());
                case SOUTH_WEST ->
                        state.setValue(SHAPE, RailShape.SOUTH_EAST).setValue(FACING, state.getValue(FACING).getCounterClockWise());
                case NORTH_WEST ->
                        state.setValue(SHAPE, RailShape.SOUTH_WEST).setValue(FACING, state.getValue(FACING).getCounterClockWise());
                case NORTH_EAST ->
                        state.setValue(SHAPE, RailShape.NORTH_WEST).setValue(FACING, state.getValue(FACING).getCounterClockWise());
            };
            case CLOCKWISE_90 -> switch ((RailShape) state.getValue(SHAPE))
            {
                case NORTH_SOUTH ->
                        state.setValue(SHAPE, RailShape.EAST_WEST).setValue(FACING, state.getValue(FACING).getClockWise());
                case EAST_WEST ->
                        state.setValue(SHAPE, RailShape.NORTH_SOUTH).setValue(FACING, state.getValue(FACING).getClockWise());
                case ASCENDING_EAST ->
                        state.setValue(SHAPE, RailShape.ASCENDING_SOUTH).setValue(FACING, state.getValue(FACING).getClockWise());
                case ASCENDING_WEST ->
                        state.setValue(SHAPE, RailShape.ASCENDING_NORTH).setValue(FACING, state.getValue(FACING).getClockWise());
                case ASCENDING_NORTH ->
                        state.setValue(SHAPE, RailShape.ASCENDING_EAST).setValue(FACING, state.getValue(FACING).getClockWise());
                case ASCENDING_SOUTH ->
                        state.setValue(SHAPE, RailShape.ASCENDING_WEST).setValue(FACING, state.getValue(FACING).getClockWise());
                case SOUTH_EAST ->
                        state.setValue(SHAPE, RailShape.SOUTH_WEST).setValue(FACING, state.getValue(FACING).getClockWise());
                case SOUTH_WEST ->
                        state.setValue(SHAPE, RailShape.NORTH_WEST).setValue(FACING, state.getValue(FACING).getClockWise());
                case NORTH_WEST ->
                        state.setValue(SHAPE, RailShape.NORTH_EAST).setValue(FACING, state.getValue(FACING).getClockWise());
                case NORTH_EAST ->
                        state.setValue(SHAPE, RailShape.SOUTH_EAST).setValue(FACING, state.getValue(FACING).getClockWise());
            };
            default -> state;
        };
    }

    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirror)
    {
        RailShape railshape = state.getValue(SHAPE);
        switch (mirror)
        {
            case LEFT_RIGHT:
                return switch (railshape)
                {
                    case ASCENDING_NORTH ->
                            state.setValue(SHAPE, RailShape.ASCENDING_SOUTH).setValue(FACING, state.getValue(FACING).getOpposite());
                    case ASCENDING_SOUTH ->
                            state.setValue(SHAPE, RailShape.ASCENDING_NORTH).setValue(FACING, state.getValue(FACING).getOpposite());
                    case SOUTH_EAST ->
                            state.setValue(SHAPE, RailShape.NORTH_EAST).setValue(FACING, state.getValue(FACING).getOpposite());
                    case SOUTH_WEST ->
                            state.setValue(SHAPE, RailShape.NORTH_WEST).setValue(FACING, state.getValue(FACING).getOpposite());
                    case NORTH_WEST ->
                            state.setValue(SHAPE, RailShape.SOUTH_WEST).setValue(FACING, state.getValue(FACING).getOpposite());
                    case NORTH_EAST ->
                            state.setValue(SHAPE, RailShape.SOUTH_EAST).setValue(FACING, state.getValue(FACING).getOpposite());
                    default -> super.mirror(state, mirror);
                };
            case FRONT_BACK:
                switch (railshape)
                {
                    case ASCENDING_EAST:
                        return state.setValue(SHAPE, RailShape.ASCENDING_WEST).setValue(FACING, state.getValue(FACING).getOpposite());
                    case ASCENDING_WEST:
                        return state.setValue(SHAPE, RailShape.ASCENDING_EAST).setValue(FACING, state.getValue(FACING).getOpposite());
                    case ASCENDING_NORTH:
                    case ASCENDING_SOUTH:
                    default:
                        break;
                    case SOUTH_EAST:
                        return state.setValue(SHAPE, RailShape.SOUTH_WEST).setValue(FACING, state.getValue(FACING).getOpposite());
                    case SOUTH_WEST:
                        return state.setValue(SHAPE, RailShape.SOUTH_EAST).setValue(FACING, state.getValue(FACING).getOpposite());
                    case NORTH_WEST:
                        return state.setValue(SHAPE, RailShape.NORTH_EAST).setValue(FACING, state.getValue(FACING).getOpposite());
                    case NORTH_EAST:
                        return state.setValue(SHAPE, RailShape.NORTH_WEST).setValue(FACING, state.getValue(FACING).getOpposite());
                }
        }
        return super.mirror(state, mirror);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { builder.add(SHAPE, POWERED, FACING, WATERLOGGED); }
}
