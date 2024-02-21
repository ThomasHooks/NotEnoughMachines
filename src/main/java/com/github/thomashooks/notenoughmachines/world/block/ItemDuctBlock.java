package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.client.KeyboardInputHelper;
import com.github.thomashooks.notenoughmachines.util.TooltipKeys;
import com.github.thomashooks.notenoughmachines.world.block.entity.AllBlockEntities;
import com.github.thomashooks.notenoughmachines.world.block.entity.ItemDuctBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemDuctBlock extends ItemConduitBlock
{
    protected static final VoxelShape CONDUIT_CENTER_SHAPE = Block.box(6.0D, 6.0D, 6.0D, 10.0D, 10.0D, 10.0D);

    public ItemDuctBlock(Properties properties) { super(properties); }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction facing = context.getPlayer().isCrouching() ? context.getClickedFace() : context.getClickedFace().getOpposite();
        if (facing == Direction.UP)
            facing = Direction.DOWN;

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
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context)
    {
        return Shapes.or(CONDUIT_CENTER_SHAPE, getTubeShapes(state));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable BlockGetter blockGetter, @NotNull List<Component> toolTips, @NotNull TooltipFlag flags)
    {
        if (KeyboardInputHelper.getInstance().isPressingShift())
        {
            toolTips.add(Component.literal(""));
            toolTips.add(Component.translatable(TooltipKeys.ITEM_DUCT.getTranslation()).withStyle(ChatFormatting.GREEN));
            toolTips.add(Component.translatable(TooltipKeys.CANT_MOVE_ITEMS_UP.getTranslation()).withStyle(ChatFormatting.DARK_RED));
        }
        else
            toolTips.add(Component.translatable(TooltipKeys.MORE_INFO_PRESS_SHIFT.getTranslation()).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> entityType)
    {
        return ItemDuctBlockEntity.getTicker(world);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return AllBlockEntities.ITEM_DUCT.get().create(pos, state);
    }
}
