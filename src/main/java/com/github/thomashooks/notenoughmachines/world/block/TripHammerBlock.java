package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.util.InventoryHelper;
import com.github.thomashooks.notenoughmachines.util.VoxelShapeHelper;
import com.github.thomashooks.notenoughmachines.world.block.base.HorizontalMechanicalBlock;
import com.github.thomashooks.notenoughmachines.world.block.base.IMultiBlockPart;
import com.github.thomashooks.notenoughmachines.world.block.entity.AllBlockEntities;
import com.github.thomashooks.notenoughmachines.world.block.entity.TripHammerBlockEntity;
import com.github.thomashooks.notenoughmachines.world.block.entity.base.MechanicalBlockEntity;
import com.github.thomashooks.notenoughmachines.world.block.state.AllBlockStateProperties;
import com.github.thomashooks.notenoughmachines.world.block.state.MultiBlockPart1x1x4;
import com.github.thomashooks.notenoughmachines.world.power.MechanicalConnectionHelper;
import com.github.thomashooks.notenoughmachines.world.power.MechanicalContext;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TripHammerBlock extends HorizontalMechanicalBlock implements IMultiBlockPart
{
    public static final EnumProperty<MultiBlockPart1x1x4> PART = AllBlockStateProperties.TRIP_HAMMER_PART;

    public TripHammerBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(PART, MultiBlockPart1x1x4.BOTTOM));
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult hitResult)
    {
        if (!world.isClientSide())
        {
            if (world.getBlockEntity(getCoreBlockPos(state, pos)) instanceof TripHammerBlockEntity tripHammerEntity)
                NetworkHooks.openScreen((ServerPlayer) player, tripHammerEntity, tripHammerEntity.getBlockPos());
            else
                throw new IllegalStateException(NotEnoughMachines.MOD_ID + ": TripHammerBlock menu provider is missing!");
        }
        return InteractionResult.sidedSuccess(world.isClientSide());
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
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = super.getStateForPlacement(context);
        return isValidForPlacement(world, pos, state) ? state.setValue(PART, MultiBlockPart1x1x4.BOTTOM) : null;
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
    {
        super.setPlacedBy(world, pos, state, placer, stack);
        BlockState shiftedState = world.getBlockState(pos);
        world.setBlock(pos.above(1), state.setValue(PART, MultiBlockPart1x1x4.LOWER_MID).setValue(SHIFTED, shiftedState.getValue(SHIFTED)), 1 | 2);
        world.setBlock(pos.above(2), state.setValue(PART, MultiBlockPart1x1x4.UPPER_MID).setValue(SHIFTED, shiftedState.getValue(SHIFTED)), 1 | 2);
        world.setBlock(pos.above(3), state.setValue(PART, MultiBlockPart1x1x4.TOP).setValue(SHIFTED, shiftedState.getValue(SHIFTED)), 1 | 2);
    }

    @Override
    public void onRemove(BlockState oldState, Level world, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (!world.isClientSide() && newState.isAir())
        {
            MultiBlockPart1x1x4 part = oldState.getValue(PART);
            BlockPos corePos = getCoreBlockPos(oldState, pos);
            if (part == MultiBlockPart1x1x4.BOTTOM)
            {
                InventoryHelper.dropItemHandler(world.getBlockEntity(corePos).getCapability(ForgeCapabilities.ITEM_HANDLER, null).orElse(null), world, corePos);
                NotEnoughMachines.AETHER.removeFromPowerNetwork(getMechanicalEntity(world, corePos, oldState));
            }
            setMultiBlockState(world, corePos, oldState, newState, 1 | 2 | 32);
        }
        super.onRemove(oldState, world, pos, newState, isMoving);
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player)
    {
        if (!world.isClientSide() && !player.isCreative() && player.hasCorrectToolForDrops(state))
            dropResources(state.setValue(PART, MultiBlockPart1x1x4.BOTTOM), world, pos, null, player, player.getMainHandItem());
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
        dropResources(state.setValue(PART, MultiBlockPart1x1x4.BOTTOM), level, pos);
        InventoryHelper.dropItemHandler(level.getBlockEntity(getCoreBlockPos(state, pos)).getCapability(ForgeCapabilities.ITEM_HANDLER, null).orElse(null), level, pos);
        super.onBlockExploded(state, level, pos, explosion);
    }

    protected void setMultiBlockState(Level world, BlockPos centerPos, BlockState currentState, BlockState newState, int flags)
    {
        for (int y = 0; y < 4; y++)
        {
            world.setBlock(centerPos.above(y), newState, flags);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext)
    {
        Direction facing = state.getValue(FACING);
        return switch (state.getValue(PART))
        {
            case BOTTOM ->                    facing == Direction.EAST || facing == Direction.WEST ? VoxelShapeHelper.TRIP_HAMMER_BASE[0] : VoxelShapeHelper.TRIP_HAMMER_BASE[1];
            case LOWER_MID, UPPER_MID, TOP -> facing == Direction.EAST || facing == Direction.WEST ? VoxelShapeHelper.TRIP_HAMMER_FRAME[0] : VoxelShapeHelper.TRIP_HAMMER_FRAME[1];
            default ->                        VoxelShapeHelper.FULL_BLOCK;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(PART);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> blockEntityType)
    {
        return TripHammerBlockEntity.getTicker(world);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        MultiBlockPart1x1x4 part = state.getValue(PART);
        return part == MultiBlockPart1x1x4.BOTTOM ? AllBlockEntities.TRIP_HAMMER.get().create(pos, state) : null;
    }

    @Override
    public ArrayList<MechanicalContext> getMechanicalConnections(Level world, BlockPos pos, BlockState state)
    {
        BlockPos corePos = getCoreBlockPos(state, pos);
        return MechanicalConnectionHelper.monoAxle(corePos.above(2), state.getValue(FACING).getClockWise().getAxis());
    }

    @Override
    public MechanicalBlockEntity getMechanicalEntity(LevelAccessor world, BlockPos pos, BlockState state)
    {
        BlockPos corePos = getCoreBlockPos(state, pos);
        MechanicalBlockEntity machine =  (MechanicalBlockEntity) world.getBlockEntity(corePos);
        return Objects.requireNonNull(machine, NotEnoughMachines.MOD_ID + ":MechanicalBlock '" + state.getBlock().getDescriptionId() + "' block entity must be an instance of MechanicalBlockEntity!");
    }

    @Override
    public BlockPos getCoreBlockPos(BlockState state, BlockPos pos)
    {
        return switch (state.getValue(PART))
        {
            case BOTTOM ->    pos;
            case LOWER_MID -> pos.below(1);
            case UPPER_MID -> pos.below(2);
            case TOP ->       pos.below(3);
            default -> throw new IllegalStateException(":getCoreBlockPos(): TripHammerBlock is in an unknown state!");
        };
    }

    @Override
    public boolean isValidForPlacement(Level world, BlockPos corePos, BlockState state)
    {
        return corePos.getY() < world.getMaxBuildHeight() - 1
                && world.getBlockState(corePos.above(1)).canBeReplaced()
                && world.getBlockState(corePos.above(2)).canBeReplaced()
                && world.getBlockState(corePos.above(3)).canBeReplaced();
    }

    @Override
    public boolean isValid(Level world, BlockPos pos, BlockState state)
    {
        BlockPos corePos = getCoreBlockPos(state, pos);
        return corePos.getY() < world.getMaxBuildHeight() - 1
                && world.getBlockState(corePos.above(1)).is(this)
                && world.getBlockState(corePos.above(2)).is(this)
                && world.getBlockState(corePos.above(3)).is(this);
    }
}
