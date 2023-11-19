package com.github.thomashooks.notenoughmachines.world.block.base;

import com.github.thomashooks.notenoughmachines.util.InventoryHelper;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public abstract class ItemConduitBlock extends Block implements EntityBlock
{
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
    public static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP = Util.make(Maps.newEnumMap(Direction.class), (directions) ->
    {
        directions.put(Direction.NORTH, NORTH);
        directions.put(Direction.EAST, EAST);
        directions.put(Direction.SOUTH, SOUTH);
        directions.put(Direction.WEST, WEST);
        directions.put(Direction.UP, UP);
        directions.put(Direction.DOWN, DOWN);
    });

    protected static final VoxelShape CONDUIT_SHAPE = Block.box(4.0D, 4.0D, 4.0D, 12.0D, 12.0D, 12.0D);
    protected static final VoxelShape CONDUIT_SHAPE_NORTH = Shapes.or(CONDUIT_SHAPE, Block.box(5.0D, 5.0D, 3.0D, 11.0D, 11.0D, 4.0D));
    protected static final VoxelShape CONDUIT_SHAPE_EAST = Shapes.or(CONDUIT_SHAPE, Block.box(12.0D, 5.0D, 5.0D, 13.0D, 11.0D, 11.0D));
    protected static final VoxelShape CONDUIT_SHAPE_SOUTH = Shapes.or(CONDUIT_SHAPE, Block.box(5.0D, 5.0D, 12.0D, 11.0D, 11.0D, 13.0D));
    protected static final VoxelShape CONDUIT_SHAPE_WEST = Shapes.or(CONDUIT_SHAPE, Block.box(3.0D, 5.0D, 5.0D, 4.0D, 11.0D, 11.0D));
    protected static final VoxelShape CONDUIT_SHAPE_UP = Shapes.or(CONDUIT_SHAPE, Block.box(5.0D, 12.0D, 5.0D, 11.0D, 13.0D, 11.0D));
    protected static final VoxelShape CONDUIT_SHAPE_DOWN = Shapes.or(CONDUIT_SHAPE, Block.box(5.0D, 3.0D, 5.0D, 11.0D, 4.0D, 11.0D));
    protected static final VoxelShape TUBE_NORTH_SHAPE = Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 6.0D);
    protected static final VoxelShape TUBE_EAST_SHAPE = Block.box(10.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
    protected static final VoxelShape TUBE_SOUTH_SHAPE = Block.box(6.0D, 6.0D, 10.0D, 10.0D, 10.0D, 16.0D);
    protected static final VoxelShape TUBE_WEST_SHAPE = Block.box(0.0D, 6.0D, 6.0D, 6.0D, 10.0D, 10.0D);
    protected static final VoxelShape TUBE_UP_SHAPE = Block.box(6.0D, 10.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    protected static final VoxelShape TUBE_DOWN_SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);

    public ItemConduitBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
                .setValue(UP, false)
                .setValue(DOWN, false));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> entityType)
    {
        return EntityBlock.super.getTicker(world, state, entityType);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction facing = context.getPlayer().isCrouching() ? context.getClickedFace() : context.getClickedFace().getOpposite();
        return this.defaultBlockState()
                .setValue(FACING, facing)
                .setValue(NORTH, this.canConnectTo(world, pos.north(), Direction.NORTH))
                .setValue(EAST, this.canConnectTo(world, pos.east(), Direction.EAST))
                .setValue(SOUTH, this.canConnectTo(world, pos.south(), Direction.SOUTH))
                .setValue(WEST, this.canConnectTo(world, pos.west(), Direction.WEST))
                .setValue(UP, this.canConnectTo(world, pos.above(), Direction.UP))
                .setValue(DOWN, this.canConnectTo(world, pos.below(), Direction.DOWN));
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) // was updatePostPlacement
    {
        return stateIn.setValue(FACING_TO_PROPERTY_MAP.get(facing), this.canConnectTo((Level) worldIn, facingPos, facing));
    }

    protected boolean canConnectTo(Level world, BlockPos pos, Direction side)
    {
        BlockEntity entity = world.getBlockEntity(pos);
        if (entity != null)
            return entity.getCapability(ForgeCapabilities.ITEM_HANDLER, side.getOpposite()).isPresent();

        return false;
    }

    @Override
    public void onRemove(BlockState oldState, Level world, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (!world.isClientSide() && newState.isAir())
        {
            BlockEntity entity = world.getBlockEntity(pos);
            InventoryHelper.dropItemHandler(entity.getCapability(ForgeCapabilities.ITEM_HANDLER, null).orElse(null), world, pos);
        }
        super.onRemove(oldState, world, pos, newState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context)
    {
        switch (state.getValue(FACING))
        {
            case EAST -> { return Shapes.or(CONDUIT_SHAPE_EAST, getTubeShapes(state)); }
            case SOUTH -> { return Shapes.or(CONDUIT_SHAPE_SOUTH, getTubeShapes(state)); }
            case WEST -> { return Shapes.or(CONDUIT_SHAPE_WEST, getTubeShapes(state)); }
            case UP -> { return Shapes.or(CONDUIT_SHAPE_UP, getTubeShapes(state)); }
            case DOWN -> { return Shapes.or(CONDUIT_SHAPE_DOWN, getTubeShapes(state)); }
            default -> { return Shapes.or(CONDUIT_SHAPE_NORTH, getTubeShapes(state)); }
        }
    }

    /**
     * Gets the item conduit's bounding box
     * @param state Block state of the Item Conduit
     * @return The bounding box for the given Item Conduit Block
     */
    protected VoxelShape getTubeShapes(BlockState state)
    {
        VoxelShape shape = Shapes.empty();
        if (state.getValue(NORTH))
            shape = Shapes.or(shape, TUBE_NORTH_SHAPE);
        if (state.getValue(EAST))
            shape = Shapes.or(shape, TUBE_EAST_SHAPE);
        if (state.getValue(SOUTH))
            shape = Shapes.or(shape, TUBE_SOUTH_SHAPE);
        if (state.getValue(WEST))
            shape = Shapes.or(shape, TUBE_WEST_SHAPE);
        if (state.getValue(UP))
            shape = Shapes.or(shape, TUBE_UP_SHAPE);
        if (state.getValue(DOWN))
            shape = Shapes.or(shape, TUBE_DOWN_SHAPE);
        return shape;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) //Was fillStateContainer
    {
        builder.add(FACING, NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    @Override
    public RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Override
    public abstract BlockEntity newBlockEntity(BlockPos pos, BlockState state);
}
