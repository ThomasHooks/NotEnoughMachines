package com.github.thomashooks.notenoughmachines.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class RedstoneBoosterRailBlock extends BaseRailBlock
{
    /**
     * This class is mostly just a copy of PoweredRailBlock and only exist so that we can handle our own booster logic
     * in onMinecartPass() since powered rail movement is not handled inside PoweredRailBlock but instead in AbstractMinecart's moveAlongTrack()
     * @see PoweredRailBlock
     * @see AbstractMinecart#moveAlongTrack(BlockPos, BlockState)
     * @see net.minecraftforge.common.extensions.IForgeBaseRailBlock#onMinecartPass(BlockState, Level, BlockPos, AbstractMinecart)
     */

    public static final EnumProperty<RailShape> SHAPE = BlockStateProperties.RAIL_SHAPE_STRAIGHT;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final double DEFAULT_BOOST_FACTOR = 0.06D;
    public static final double DEFAULT_LAUNCH_FACTOR = 0.02D;

    protected RedstoneBoosterRailBlock(Properties properties, boolean isStraight)
    {
        super(isStraight, properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(SHAPE, RailShape.NORTH_SOUTH)
                .setValue(POWERED, false)
                .setValue(WATERLOGGED, false)
        );
    }

    protected boolean findPoweredRailSignal(Level world, BlockPos pos, BlockState state, boolean searchForward, int recursionCount)
    {
        if (recursionCount >= 8)
            return false;
        else
        {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            boolean flag = true;
            RailShape railshape = state.getValue(getShapeProperty());
            switch (railshape) {
                case NORTH_SOUTH:
                    if (searchForward) {
                        ++k;
                    } else {
                        --k;
                    }
                    break;
                case EAST_WEST:
                    if (searchForward) {
                        --i;
                    } else {
                        ++i;
                    }
                    break;
                case ASCENDING_EAST:
                    if (searchForward) {
                        --i;
                    } else {
                        ++i;
                        ++j;
                        flag = false;
                    }

                    railshape = RailShape.EAST_WEST;
                    break;
                case ASCENDING_WEST:
                    if (searchForward) {
                        --i;
                        ++j;
                        flag = false;
                    } else {
                        ++i;
                    }

                    railshape = RailShape.EAST_WEST;
                    break;
                case ASCENDING_NORTH:
                    if (searchForward) {
                        ++k;
                    } else {
                        --k;
                        ++j;
                        flag = false;
                    }

                    railshape = RailShape.NORTH_SOUTH;
                    break;
                case ASCENDING_SOUTH:
                    if (searchForward) {
                        ++k;
                        ++j;
                        flag = false;
                    } else {
                        --k;
                    }

                    railshape = RailShape.NORTH_SOUTH;
            }

            if (this.isSameRailWithPower(world, new BlockPos(i, j, k), searchForward, recursionCount, railshape)) {
                return true;
            } else {
                return flag && this.isSameRailWithPower(world, new BlockPos(i, j - 1, k), searchForward, recursionCount, railshape);
            }
        }
    }

    protected boolean isSameRailWithPower(Level world, BlockPos pos, boolean searchForward, int recursionCount, RailShape shape)
    {
        BlockState blockstate = world.getBlockState(pos);
        if (!(blockstate.getBlock() instanceof RedstoneBoosterRailBlock other))
            return false;
        else
        {
            RailShape railshape = other.getRailDirection(blockstate, world, pos, null);
            if (shape != RailShape.EAST_WEST || railshape != RailShape.NORTH_SOUTH && railshape != RailShape.ASCENDING_NORTH && railshape != RailShape.ASCENDING_SOUTH)
            {
                if (shape != RailShape.NORTH_SOUTH || railshape != RailShape.EAST_WEST && railshape != RailShape.ASCENDING_EAST && railshape != RailShape.ASCENDING_WEST)
                    return world.hasNeighborSignal(pos) ? true : other.findPoweredRailSignal(world, pos, blockstate, searchForward, recursionCount + 1);
                else
                    return false;
            }
            else
                return false;
        }
    }

    @Override
    protected void updateState(BlockState state, Level world, BlockPos pos, Block block)
    {
        boolean flag = state.getValue(POWERED);
        boolean flag1 = world.hasNeighborSignal(pos) || this.findPoweredRailSignal(world, pos, state, true, 0) || this.findPoweredRailSignal(world, pos, state, false, 0);
        if (flag1 != flag)
        {
            world.setBlock(pos, state.setValue(POWERED, Boolean.valueOf(flag1)), 3);
            world.updateNeighborsAt(pos.below(), this);
            if (state.getValue(getShapeProperty()).isAscending())
                world.updateNeighborsAt(pos.above(), this);
        }

    }

    abstract public void onMinecartPass(BlockState state, Level level, BlockPos pos, AbstractMinecart cart);

    protected void stopMinecart(BlockState state, Level world, BlockPos pos, AbstractMinecart cart)
    {
        Vec3 cartDeltaMovement = cart.getDeltaMovement();
        double cartHorizontalDistance = cartDeltaMovement.horizontalDistance();
        if (cartHorizontalDistance < 0.03D)
            cart.setDeltaMovement(Vec3.ZERO);
        else
            cart.setDeltaMovement(cart.getDeltaMovement().multiply(0.5D, 0.0D, 0.5D));
    }

    protected void boostMinecart(BlockState state, Level world, BlockPos pos, AbstractMinecart cart)
    {
        this.boostMinecart(state, world, pos, cart, DEFAULT_BOOST_FACTOR, DEFAULT_LAUNCH_FACTOR);
    }

    protected void boostMinecart(BlockState state, Level world, BlockPos pos, AbstractMinecart cart, double boostFactor, double launchFactor)
    {
        RailShape railshape = ((BaseRailBlock)state.getBlock()).getRailDirection(state, world, pos, cart);
        Vec3 cartDeltaMovement = cart.getDeltaMovement();
        double cartHorizontalDistance = cartDeltaMovement.horizontalDistance();
        if (cartHorizontalDistance > 0.01D)
            cart.setDeltaMovement(cartDeltaMovement.add(cartDeltaMovement.x / cartHorizontalDistance * boostFactor, 0.0D, cartDeltaMovement.z / cartHorizontalDistance * boostFactor));
        else
        {
            double cartDeltaX = cartDeltaMovement.x;
            double cartDeltaZ = cartDeltaMovement.z;
            if (railshape == RailShape.EAST_WEST)
            {
                if (this.isRedstoneConductor(world, pos.west()))
                    cartDeltaX = launchFactor;
                else if (this.isRedstoneConductor(world, pos.east()))
                    cartDeltaX = -launchFactor;
            }
            else
            {
                if (railshape != RailShape.NORTH_SOUTH)
                    return;

                if (isRedstoneConductor(world, pos.north()))
                    cartDeltaZ = launchFactor;
                else if (isRedstoneConductor(world, pos.south()))
                    cartDeltaZ = -launchFactor;
            }
            cart.setDeltaMovement(cartDeltaX, cartDeltaMovement.y, cartDeltaZ);
        }
    }

    protected boolean isRedstoneConductor(Level world, BlockPos pos)
    {
        return world.getBlockState(pos).isRedstoneConductor(world, pos);
    }

    public static @Nullable Direction getMinecartMovementDirection(AbstractMinecart cart)
    {
        double xVel = cart.getDeltaMovement().x;
        double zVel = cart.getDeltaMovement().z;
        if (xVel > 0.0D)
            return Direction.EAST;
        else if (xVel < 0.0D)
            return Direction.WEST;
        if (zVel > 0.0D)
            return Direction.SOUTH;
        else if (zVel < 0.0D)
            return Direction.NORTH;
        return null;
    }

    @Override
    public Property<RailShape> getShapeProperty() { return SHAPE; }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation)
    {
        return switch (rotation)
        {
            case CLOCKWISE_180 -> switch ((RailShape) state.getValue(SHAPE))
            {
                case ASCENDING_EAST -> state.setValue(SHAPE, RailShape.ASCENDING_WEST);
                case ASCENDING_WEST -> state.setValue(SHAPE, RailShape.ASCENDING_EAST);
                case ASCENDING_NORTH -> state.setValue(SHAPE, RailShape.ASCENDING_SOUTH);
                case ASCENDING_SOUTH -> state.setValue(SHAPE, RailShape.ASCENDING_NORTH);
                case SOUTH_EAST -> state.setValue(SHAPE, RailShape.NORTH_WEST);
                case SOUTH_WEST -> state.setValue(SHAPE, RailShape.NORTH_EAST);
                case NORTH_WEST -> state.setValue(SHAPE, RailShape.SOUTH_EAST);
                case NORTH_EAST -> state.setValue(SHAPE, RailShape.SOUTH_WEST);
                case NORTH_SOUTH, EAST_WEST -> state;
            };
            case COUNTERCLOCKWISE_90 -> switch ((RailShape) state.getValue(SHAPE))
            {
                case NORTH_SOUTH -> state.setValue(SHAPE, RailShape.EAST_WEST);
                case EAST_WEST -> state.setValue(SHAPE, RailShape.NORTH_SOUTH);
                case ASCENDING_EAST -> state.setValue(SHAPE, RailShape.ASCENDING_NORTH);
                case ASCENDING_WEST -> state.setValue(SHAPE, RailShape.ASCENDING_SOUTH);
                case ASCENDING_NORTH -> state.setValue(SHAPE, RailShape.ASCENDING_WEST);
                case ASCENDING_SOUTH -> state.setValue(SHAPE, RailShape.ASCENDING_EAST);
                case SOUTH_EAST -> state.setValue(SHAPE, RailShape.NORTH_EAST);
                case SOUTH_WEST -> state.setValue(SHAPE, RailShape.SOUTH_EAST);
                case NORTH_WEST -> state.setValue(SHAPE, RailShape.SOUTH_WEST);
                case NORTH_EAST -> state.setValue(SHAPE, RailShape.NORTH_WEST);
            };
            case CLOCKWISE_90 -> switch ((RailShape) state.getValue(SHAPE))
            {
                case NORTH_SOUTH -> state.setValue(SHAPE, RailShape.EAST_WEST);
                case EAST_WEST -> state.setValue(SHAPE, RailShape.NORTH_SOUTH);
                case ASCENDING_EAST -> state.setValue(SHAPE, RailShape.ASCENDING_SOUTH);
                case ASCENDING_WEST -> state.setValue(SHAPE, RailShape.ASCENDING_NORTH);
                case ASCENDING_NORTH -> state.setValue(SHAPE, RailShape.ASCENDING_EAST);
                case ASCENDING_SOUTH -> state.setValue(SHAPE, RailShape.ASCENDING_WEST);
                case SOUTH_EAST -> state.setValue(SHAPE, RailShape.SOUTH_WEST);
                case SOUTH_WEST -> state.setValue(SHAPE, RailShape.NORTH_WEST);
                case NORTH_WEST -> state.setValue(SHAPE, RailShape.NORTH_EAST);
                case NORTH_EAST -> state.setValue(SHAPE, RailShape.SOUTH_EAST);
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
                    case ASCENDING_NORTH -> state.setValue(SHAPE, RailShape.ASCENDING_SOUTH);
                    case ASCENDING_SOUTH -> state.setValue(SHAPE, RailShape.ASCENDING_NORTH);
                    case SOUTH_EAST -> state.setValue(SHAPE, RailShape.NORTH_EAST);
                    case SOUTH_WEST -> state.setValue(SHAPE, RailShape.NORTH_WEST);
                    case NORTH_WEST -> state.setValue(SHAPE, RailShape.SOUTH_WEST);
                    case NORTH_EAST -> state.setValue(SHAPE, RailShape.SOUTH_EAST);
                    default -> super.mirror(state, mirror);
                };
            case FRONT_BACK:
                switch (railshape)
                {
                    case ASCENDING_EAST:
                        return state.setValue(SHAPE, RailShape.ASCENDING_WEST);
                    case ASCENDING_WEST:
                        return state.setValue(SHAPE, RailShape.ASCENDING_EAST);
                    case ASCENDING_NORTH:
                    case ASCENDING_SOUTH:
                    default:
                        break;
                    case SOUTH_EAST:
                        return state.setValue(SHAPE, RailShape.SOUTH_WEST);
                    case SOUTH_WEST:
                        return state.setValue(SHAPE, RailShape.SOUTH_EAST);
                    case NORTH_WEST:
                        return state.setValue(SHAPE, RailShape.NORTH_EAST);
                    case NORTH_EAST:
                        return state.setValue(SHAPE, RailShape.NORTH_WEST);
                }
        }
        return super.mirror(state, mirror);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { builder.add(SHAPE, POWERED, WATERLOGGED); }
}
