package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.util.KeyboardInputHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HighSpeedRailBlock extends RailBlock
{
    public HighSpeedRailBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(SHAPE, RailShape.NORTH_SOUTH)
                .setValue(WATERLOGGED, false));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> toolTips, TooltipFlag flag)
    {
        if (KeyboardInputHelper.isPressingShift())
        {
            toolTips.add(Component.literal(""));
            toolTips.add(Component.literal("Allows minecrafts to travel 200% faster than standard rails").withStyle(ChatFormatting.GREEN));
        }
        else
            toolTips.add(Component.literal(KeyboardInputHelper.MORE_INFO_PRESS_SHIFT).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public float getRailMaxSpeed(BlockState state, Level level, BlockPos pos, AbstractMinecart cart)
    {
        if (cart instanceof MinecartFurnace) return cart.isInWater() ? 0.3F : 0.4F;
        else return cart.isInWater() ? 0.4F : 0.8F;
    }
}
