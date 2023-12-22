package com.github.thomashooks.notenoughmachines.world.item;

import com.github.thomashooks.notenoughmachines.world.block.MetalScaffoldBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class MetalScaffoldBlockItem extends BlockItem
{
    protected final int maxStabilityDistance;
    public static final int MAX_STABILITY_DISTANCE = MetalScaffoldBlock.MAX_STABILITY_DISTANCE;

    public MetalScaffoldBlockItem(int maxStabilityDistanceIn, Block block, Item.Properties properties)
    {
        super(block, properties);
        this.maxStabilityDistance= Math.min(maxStabilityDistanceIn, MAX_STABILITY_DISTANCE);
    }

    @Nullable
    public BlockPlaceContext updatePlacementContext(BlockPlaceContext context)
    {
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();
        BlockState blockstate = level.getBlockState(blockpos);
        Block block = this.getBlock();
        if (!blockstate.is(block))
            return MetalScaffoldBlock.getDistance(level, blockpos, this.maxStabilityDistance) == this.maxStabilityDistance ? null : context;
        else
        {
            Direction direction;
            if (context.isSecondaryUseActive())
                direction = context.isInside() ? context.getClickedFace().getOpposite() : context.getClickedFace();
            else
                direction = context.getClickedFace() == Direction.UP ? context.getHorizontalDirection() : Direction.UP;

            int i = 0;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos.mutable().move(direction);

            while(i < this.maxStabilityDistance)
            {
                if (!level.isClientSide && !level.isInWorldBounds(blockpos$mutableblockpos))
                {
                    Player player = context.getPlayer();
                    int j = level.getMaxBuildHeight();
                    if (player instanceof ServerPlayer && blockpos$mutableblockpos.getY() >= j)
                        ((ServerPlayer)player).sendSystemMessage(Component.translatable("build.tooHigh", j - 1).withStyle(ChatFormatting.RED), true);
                    break;
                }
                blockstate = level.getBlockState(blockpos$mutableblockpos);
                if (!blockstate.is(this.getBlock()))
                {
                    if (blockstate.canBeReplaced(context))
                        return BlockPlaceContext.at(context, blockpos$mutableblockpos, direction);
                    break;
                }

                blockpos$mutableblockpos.move(direction);
                if (direction.getAxis().isHorizontal())
                    ++i;
            }
            return null;
        }
    }

    protected boolean mustSurvive() { return false; }
}
