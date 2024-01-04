package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.client.KeyboardInputHelper;
import com.github.thomashooks.notenoughmachines.util.TooltipKeys;
import com.github.thomashooks.notenoughmachines.world.block.entity.AllBlockEntities;
import com.github.thomashooks.notenoughmachines.world.block.entity.LockingRailBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LockingRailBlock extends RedstoneRailBlock implements EntityBlock
{
    public LockingRailBlock(boolean isHighSpeed, Properties properties)
    {
        super(true, isHighSpeed, properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(SHAPE, RailShape.NORTH_SOUTH)
                .setValue(POWERED, false)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    public boolean canMakeSlopes(BlockState state, BlockGetter level, BlockPos pos) { return false; }

    @Override
    public void onMinecartPass(BlockState state, Level level, BlockPos pos, AbstractMinecart cart)
    {
        if (!state.getValue(POWERED))
        {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof LockingRailBlockEntity lockingRailBlockEntity)
                lockingRailBlockEntity.lockMinecart(cart);
        }
    }

    @Override
    protected void updateState(BlockState state, Level world, @NotNull BlockPos pos, @NotNull Block block)
    {
        super.updateState(state, world, pos, block);

        BlockEntity entity = world.getBlockEntity(pos);
        if (state.getValue(POWERED) && entity instanceof LockingRailBlockEntity lockingRailBlockEntity)
            lockingRailBlockEntity.unlockMinecart();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable BlockGetter blockGetter, @NotNull List<Component> tooltips, @NotNull TooltipFlag flag)
    {
        if (KeyboardInputHelper.getInstance().isPressingShift())
        {
            tooltips.add(Component.literal(""));
            tooltips.add(Component.translatable(TooltipKeys.LOCKING_RAIL1.getTranslation()).withStyle(ChatFormatting.GREEN));
            tooltips.add(Component.translatable(TooltipKeys.LOCKING_RAIL2.getTranslation()).withStyle(ChatFormatting.GRAY));
            if (this.isHighSpeed)
                tooltips.add(Component.translatable(TooltipKeys.MINECARTS_MOVE_FASTER.getTranslation()).withStyle(ChatFormatting.GRAY));
        }
        else
            tooltips.add(Component.translatable(TooltipKeys.MORE_INFO_PRESS_SHIFT.getTranslation()).withStyle(ChatFormatting.GRAY));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state)
    {
        return AllBlockEntities.LOCKING_RAIL.get().create(pos, state);
    }
}
