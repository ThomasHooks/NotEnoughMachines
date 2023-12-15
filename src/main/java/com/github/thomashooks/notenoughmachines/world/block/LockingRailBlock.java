package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.common.util.KeyboardInputHelper;
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
    public LockingRailBlock(Properties properties)
    {
        super(properties, true);
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
    protected void updateState(BlockState state, Level world, BlockPos pos, Block block)
    {
        super.updateState(state, world, pos, block);

        BlockEntity entity = world.getBlockEntity(pos);
        if (state.getValue(POWERED) && entity instanceof LockingRailBlockEntity lockingRailBlockEntity)
            lockingRailBlockEntity.unlockMinecart();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> toolTips, TooltipFlag flag)
    {
        if (KeyboardInputHelper.isPressingShift())
        {
            toolTips.add(Component.literal(""));
            toolTips.add(Component.literal("Prevents minecarts from moving when not receiving a redstone signal").withStyle(ChatFormatting.GREEN));
            toolTips.add(Component.literal("Locked minecarts are boosted in the direction it was traveling in When powered with a redstone signal").withStyle(ChatFormatting.GRAY));
        }
        else
            toolTips.add(Component.literal(KeyboardInputHelper.MORE_INFO_PRESS_SHIFT).withStyle(ChatFormatting.GRAY));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(SHAPE, POWERED, WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state)
    {
        return AllBlockEntities.LOCKING_RAIL.get().create(pos, state);
    }
}
