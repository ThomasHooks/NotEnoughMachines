package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.integration.config.CommonConfigs;
import com.github.thomashooks.notenoughmachines.common.util.KeyboardInputHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HighSpeedOneWayRailBlock extends OneWayRailBlock
{
    public HighSpeedOneWayRailBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(SHAPE, RailShape.NORTH_SOUTH)
                .setValue(POWERED, false)
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    public void onMinecartPass(BlockState state, Level level, BlockPos pos, AbstractMinecart cart)
    {
        if (!state.getValue(POWERED))
        {
            this.stopMinecart(cart);
            return;
        }

        Direction railFacing = state.getValue(FACING);
        Direction cartMovementDirection = getMinecartMovementDirection(cart);
        double boostFactor = railFacing != cartMovementDirection && cartMovementDirection != null ? -CommonConfigs.HIGH_SPEED_POWERED_RAIL_BOOST_FACTOR.get() : CommonConfigs.HIGH_SPEED_POWERED_RAIL_BOOST_FACTOR.get();
        if (cart.getDeltaMovement().horizontalDistance() > 0.01D)
            this.boostMinecart(cart, boostFactor);
        else
        {
            if (this.isRedstoneConductor(level, pos.relative(railFacing.getOpposite())))
                this.launchMinecart(state, level, pos, cart, CommonConfigs.HIGH_SPEED_POWERED_RAIL_LAUNCH_FACTOR.get());
            else
                this.launchMinecart(cart, railFacing, CommonConfigs.HIGH_SPEED_POWERED_RAIL_LAUNCH_FACTOR.get());
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> toolTips, TooltipFlag flag)
    {
        if (KeyboardInputHelper.isPressingShift())
        {
            toolTips.add(Component.literal(""));
            toolTips.add(Component.literal("Acts like a one-way powered rail").withStyle(ChatFormatting.GREEN));
            toolTips.add(Component.literal("Minecarts traveling against the arrows will be reversed").withStyle(ChatFormatting.GRAY));
            toolTips.add(Component.literal("Minecarts will move 200% faster!").withStyle(ChatFormatting.GRAY));
        }
        else
            toolTips.add(Component.literal(KeyboardInputHelper.MORE_INFO_PRESS_SHIFT).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public float getRailMaxSpeed(BlockState state, Level level, BlockPos pos, AbstractMinecart cart)
    {
        if (cart instanceof MinecartFurnace)
            return cart.isInWater() ? CommonConfigs.HIGH_SPEED_RAIL_MAX_SPEED_MINECART_FURNACE_WATERLOGGED.get() : CommonConfigs.HIGH_SPEED_RAIL_MAX_SPEED_MINECART_FURNACE.get();
        else
            return cart.isInWater() ? CommonConfigs.HIGH_SPEED_RAIL_MAX_SPEED_WATERLOGGED.get() : CommonConfigs.HIGH_SPEED_RAIL_MAX_SPEED.get();
    }
}
