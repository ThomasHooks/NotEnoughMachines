package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.common.util.KeyboardInputHelper;
import com.github.thomashooks.notenoughmachines.integration.config.CommonConfigs;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HighSpeedLimiterRailBlock extends LimiterRailBlock
{
    public HighSpeedLimiterRailBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public float getRailMaxSpeed(BlockState state, Level level, BlockPos pos, AbstractMinecart cart)
    {
        float limit = state.getValue(POWERED) ? 0.25F * state.getValue(SPEED) : 1.0F;
        if (cart instanceof MinecartFurnace)
            return (cart.isInWater() ? CommonConfigs.HIGH_SPEED_RAIL_MAX_SPEED_MINECART_FURNACE_WATERLOGGED.get() : CommonConfigs.HIGH_SPEED_RAIL_MAX_SPEED_MINECART_FURNACE.get()) * limit;
        else
            return (cart.isInWater() ? CommonConfigs.HIGH_SPEED_RAIL_MAX_SPEED_WATERLOGGED.get() : CommonConfigs.HIGH_SPEED_RAIL_MAX_SPEED.get()) * limit;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> toolTips, TooltipFlag flag)
    {
        if (KeyboardInputHelper.isPressingShift())
        {
            toolTips.add(Component.literal(""));
            toolTips.add(Component.literal("Limits the speed of minecarts when receiving a redstone signal").withStyle(ChatFormatting.GREEN));
            toolTips.add(Component.literal("Right click to change the speed limit").withStyle(ChatFormatting.GRAY));
            toolTips.add(Component.literal("Minecarts will move 200% faster!").withStyle(ChatFormatting.GRAY));
        }
        else
            toolTips.add(Component.literal(KeyboardInputHelper.MORE_INFO_PRESS_SHIFT).withStyle(ChatFormatting.GRAY));
    }
}
