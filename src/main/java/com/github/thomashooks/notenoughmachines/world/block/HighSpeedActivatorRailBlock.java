package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.util.TooltipKeys;
import com.github.thomashooks.notenoughmachines.integration.config.CommonConfigs;
import com.github.thomashooks.notenoughmachines.client.KeyboardInputHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HighSpeedActivatorRailBlock extends RedstoneRailBlock
{
    public HighSpeedActivatorRailBlock(Properties properties)
    {
        super(true, true, properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(SHAPE, RailShape.NORTH_SOUTH)
                .setValue(POWERED, false)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    public void onMinecartPass(BlockState state, Level level, BlockPos pos, AbstractMinecart cart)
    {
        int k = Mth.floor(cart.getX());
        int i = Mth.floor(cart.getY());
        int j = Mth.floor(cart.getZ());
        cart.activateMinecart(k, i, j, state.getValue(POWERED));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> toolTips, TooltipFlag flag)
    {
        if (KeyboardInputHelper.getInstance().isPressingShift())
        {
            toolTips.add(Component.literal(""));
            toolTips.add(Component.translatable(TooltipKeys.ACTIVATOR_RAIL.getTranslation()).withStyle(ChatFormatting.GREEN));
            toolTips.add(Component.translatable(TooltipKeys.MINECARTS_MOVE_FASTER.getTranslation()).withStyle(ChatFormatting.GRAY));
        }
        else
            toolTips.add(Component.translatable(TooltipKeys.MORE_INFO_PRESS_SHIFT.getTranslation()).withStyle(ChatFormatting.GRAY));
    }
}
