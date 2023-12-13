package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.common.config.CommonConfigs;
import com.github.thomashooks.notenoughmachines.common.util.KeyboardInputHelper;
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

public class HighSpeedCrossoverRailBlock extends CrossoverRailBlock
{
    public HighSpeedCrossoverRailBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> toolTips, TooltipFlag flag)
    {
        if (KeyboardInputHelper.isPressingShift())
        {
            toolTips.add(Component.literal(""));
            toolTips.add(Component.literal("Allows two sets of tracks to cross over each other").withStyle(ChatFormatting.GREEN));
            toolTips.add(Component.literal("Minecarts will move 200% faster!").withStyle(ChatFormatting.GRAY));
        }
        else
            toolTips.add(Component.literal(KeyboardInputHelper.MORE_INFO_PRESS_SHIFT).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public float getRailMaxSpeed(BlockState state, Level level, BlockPos pos, AbstractMinecart cart)
    {
        if (cart instanceof MinecartFurnace) return cart.isInWater() ? CommonConfigs.HIGH_SPEED_RAIL_MAX_SPEED_MINECART_FURNACE_WATERLOGGED.get() : CommonConfigs.HIGH_SPEED_RAIL_MAX_SPEED_MINECART_FURNACE.get();
        else return cart.isInWater() ? CommonConfigs.HIGH_SPEED_RAIL_MAX_SPEED_WATERLOGGED.get() : CommonConfigs.HIGH_SPEED_RAIL_MAX_SPEED.get();
    }
}
