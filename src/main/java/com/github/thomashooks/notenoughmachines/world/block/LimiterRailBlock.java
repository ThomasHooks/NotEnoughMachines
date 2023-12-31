package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.client.KeyboardInputHelper;
import com.github.thomashooks.notenoughmachines.util.TooltipKeys;
import com.github.thomashooks.notenoughmachines.world.block.state.properties.AllBlockStateProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.MinecartFurnace;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LimiterRailBlock extends RedstoneRailBlock
{
    public static final IntegerProperty SPEED = AllBlockStateProperties.SPEED;
    public LimiterRailBlock(Properties properties)
    {
        super(properties, true);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(SHAPE, RailShape.NORTH_SOUTH)
                .setValue(POWERED, false)
                .setValue(SPEED, Integer.valueOf(1))
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult hitResult)
    {
        if (!player.getAbilities().mayBuild)
            return InteractionResult.PASS;
        else
        {
            float pitch = 0.4F + 0.05F * state.getValue(SPEED);
            world.playSound(player, pos, SoundEvents.COMPARATOR_CLICK, SoundSource.BLOCKS, 0.3F,pitch);
            world.setBlock(pos, state.cycle(SPEED), 3);
            return InteractionResult.sidedSuccess(world.isClientSide);
        }
    }

    @Override
    public float getRailMaxSpeed(BlockState state, Level level, BlockPos pos, AbstractMinecart cart)
    {
        float limit = state.getValue(POWERED) ? 0.25F * state.getValue(SPEED) : 1.0F;
        if (cart instanceof MinecartFurnace)
            return (cart.isInWater() ? 0.15F : 0.2F) * limit;
        else
            return (cart.isInWater() ? 0.2F : 0.4F) * limit;
    }

    @Override
    public void onMinecartPass(BlockState state, Level level, BlockPos pos, AbstractMinecart cart)
    {
        if (state.getValue(POWERED))
        {
            Vec3 cartDeltaMovement = cart.getDeltaMovement();
            double railMaxSpeed = cart.getMaxSpeedWithRail();
            cart.setDeltaMovement(new Vec3(Mth.clamp(cartDeltaMovement.x, -railMaxSpeed, railMaxSpeed), 0.0D, Mth.clamp(cartDeltaMovement.z, -railMaxSpeed, railMaxSpeed)));
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> toolTips, TooltipFlag flag)
    {
        if (KeyboardInputHelper.getInstance().isPressingShift())
        {
            toolTips.add(Component.literal(""));
            toolTips.add(Component.translatable(TooltipKeys.LIMITER_RAIL1.getTranslation()).withStyle(ChatFormatting.GREEN));
            toolTips.add(Component.translatable(TooltipKeys.LIMITER_RAIL2.getTranslation()).withStyle(ChatFormatting.GRAY));
        }
        else
            toolTips.add(Component.translatable(TooltipKeys.MORE_INFO_PRESS_SHIFT.getTranslation()).withStyle(ChatFormatting.GRAY));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(SHAPE, POWERED, SPEED, WATERLOGGED);
    }
}
