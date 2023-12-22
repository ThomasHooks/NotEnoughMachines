package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.world.block.state.properties.AllBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class MetalScaffoldBlock extends Block implements SimpleWaterloggedBlock
{
    protected static final int TICK_DELAY = 1;
    protected static final VoxelShape STABLE_SHAPE;
    protected static final VoxelShape UNSTABLE_SHAPE;
    protected static final VoxelShape UNSTABLE_SHAPE_BOTTOM = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    protected static final VoxelShape BELOW_BLOCK = Shapes.block().move(0.0D, -1.0D, 0.0D);
    protected final int maxStabilityDistance;
    public static final int MAX_STABILITY_DISTANCE = 25;
    public static final IntegerProperty DISTANCE = AllBlockStateProperties.STABILITY_DISTANCE_25;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty UNSTABLE = AllBlockStateProperties.UNSTABLE;

    public MetalScaffoldBlock(int maxStabilityDistanceIn, BlockBehaviour.Properties properties)
    {
        super(properties);
        this.maxStabilityDistance = Math.min(maxStabilityDistanceIn, MAX_STABILITY_DISTANCE);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(DISTANCE, Integer.valueOf(this.maxStabilityDistance))
                .setValue(WATERLOGGED, Boolean.valueOf(false))
                .setValue(UNSTABLE, Boolean.valueOf(false))
        );
    }

    @Override
    public boolean isScaffolding(BlockState state, LevelReader level, BlockPos pos, LivingEntity entity)
    {
        return state.getBlock() instanceof MetalScaffoldBlock;
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos, CollisionContext collisionContext)
    {
        if (!collisionContext.isHoldingItem(state.getBlock().asItem()))
            return state.getValue(UNSTABLE) ? UNSTABLE_SHAPE : STABLE_SHAPE;
        else
            return Shapes.block();
    }

    @Override
    public @NotNull VoxelShape getInteractionShape(@NotNull BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos)
    {
        return Shapes.block();
    }

    @Override
    public boolean canBeReplaced(@NotNull BlockState state, BlockPlaceContext blockPlaceContext)
    {
        return blockPlaceContext.getItemInHand().is(this.asItem());
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext)
    {
        BlockPos blockpos = blockPlaceContext.getClickedPos();
        Level level = blockPlaceContext.getLevel();
        int distance = getDistance(level, blockpos, this.maxStabilityDistance);
        return this.defaultBlockState()
                .setValue(WATERLOGGED, Boolean.valueOf(level.getFluidState(blockpos).getType() == Fluids.WATER))
                .setValue(DISTANCE, Integer.valueOf(distance))
                .setValue(UNSTABLE, Boolean.valueOf(this.isUnstable(level, blockpos, distance)));
    }

    @Override
    public void onPlace(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean isMoving)
    {
        if (!world.isClientSide)
            world.scheduleTick(pos, this, TICK_DELAY);
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor levelAccessor, @NotNull BlockPos pos, @NotNull BlockPos facingPos)
    {
        if (state.getValue(WATERLOGGED))
            levelAccessor.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor));

        if (!levelAccessor.isClientSide())
            levelAccessor.scheduleTick(pos, this, TICK_DELAY);

        return state;
    }

    @Override
    public void tick(BlockState state, @NotNull ServerLevel world, @NotNull BlockPos pos, @NotNull RandomSource randomSource)
    {
        int distance = getDistance(world, pos, this.maxStabilityDistance);
        BlockState blockstate = state.setValue(DISTANCE, Integer.valueOf(distance)).setValue(UNSTABLE, Boolean.valueOf(this.isUnstable(world, pos, distance)));
        if (blockstate.getValue(DISTANCE) >= this.maxStabilityDistance)
        {
            if (state.getValue(DISTANCE) >= this.maxStabilityDistance || state.getValue(UNSTABLE))
                FallingBlockEntity.fall(world, pos, blockstate);
            else
                world.destroyBlock(pos, true);
        }
        else if (state != blockstate)
            world.setBlock(pos, blockstate, 3);
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader levelReader, @NotNull BlockPos pos)
    {
        return getDistance(levelReader, pos, this.maxStabilityDistance) < this.maxStabilityDistance;
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos, CollisionContext collisionContext)
    {
        if (state.getValue(UNSTABLE))
            return UNSTABLE_SHAPE;
        else if (collisionContext.isAbove(Shapes.block(), pos, true) && !collisionContext.isDescending())
            return STABLE_SHAPE;
        else
            return state.getValue(DISTANCE) != 0 && state.getValue(UNSTABLE) && collisionContext.isAbove(BELOW_BLOCK, pos, true) ? UNSTABLE_SHAPE_BOTTOM : Shapes.empty();
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state)
    {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    protected boolean isUnstable(BlockGetter blockGetter, BlockPos pos, int distance)
    {
        return distance > 0 && !(blockGetter.getBlockState(pos.below()).getBlock() instanceof MetalScaffoldBlock);
    }

    public static int getDistance(BlockGetter blockGetter, BlockPos pos, int maxStabilityDistance)
    {
        BlockPos.MutableBlockPos posDown = pos.mutable().move(Direction.DOWN);
        BlockState stateDown = blockGetter.getBlockState(posDown);
        int distance = maxStabilityDistance;
        if (stateDown.getBlock() instanceof MetalScaffoldBlock)
            distance = stateDown.getValue(DISTANCE);
        else if (stateDown.isFaceSturdy(blockGetter, posDown, Direction.UP))
            return 0;

        for(Direction direction : Direction.Plane.HORIZONTAL)
        {
            BlockState stateOffset = blockGetter.getBlockState(posDown.setWithOffset(pos, direction));
            if (stateOffset.getBlock() instanceof MetalScaffoldBlock)
            {
                distance = Math.min(distance, stateOffset.getValue(DISTANCE) + 1);
                if (distance == 1)
                    break;
            }
        }
        return distance;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(DISTANCE, WATERLOGGED, UNSTABLE);
    }

    static
    {
        VoxelShape voxelshape = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        VoxelShape voxelshape1 = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 2.0D);
        VoxelShape voxelshape2 = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
        VoxelShape voxelshape3 = Block.box(0.0D, 0.0D, 14.0D, 2.0D, 16.0D, 16.0D);
        VoxelShape voxelshape4 = Block.box(14.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
        STABLE_SHAPE = Shapes.or(voxelshape, voxelshape1, voxelshape2, voxelshape3, voxelshape4);

        VoxelShape voxelshape5 = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 2.0D, 16.0D);
        VoxelShape voxelshape6 = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
        VoxelShape voxelshape7 = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 2.0D, 16.0D);
        VoxelShape voxelshape8 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 2.0D);
        UNSTABLE_SHAPE = Shapes.or(UNSTABLE_SHAPE_BOTTOM, STABLE_SHAPE, voxelshape6, voxelshape5, voxelshape8, voxelshape7);
    }
}
