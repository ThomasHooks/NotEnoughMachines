package com.github.thomashooks.notenoughmachines.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class WeatheringStairBlock extends StairBlock implements IWeatheringBlock
{
    private final IWeatheringBlock.State weatherAge;

    public WeatheringStairBlock(IWeatheringBlock.State weatherState, Supplier<BlockState> state, Properties properties)
    {
        super(state, properties);
        this.weatherAge = weatherState;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult)
    {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getItem() == Items.HONEYCOMB && !IWeatheringBlock.isWaxed(state))
            return IWeatheringBlock.applyWax(state, world, pos, player, stack);

        return InteractionResult.PASS;
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel serverWorld, @NotNull BlockPos pos, @NotNull RandomSource randomSource)
    {
        this.onRandomTick(state, serverWorld, pos, randomSource);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) { return IWeatheringBlock.getNext(state.getBlock()).isPresent(); }

    @Override
    public @NotNull State getAge() { return this.weatherAge; }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate)
    {
        if (!context.getItemInHand().canPerformAction(toolAction))
            return null;

        if (ToolActions.AXE_SCRAPE == toolAction && !IWeatheringBlock.isWaxed(state))
            return IWeatheringBlock.scrapeOffOxidation(state, context, toolAction);
        else if (ToolActions.AXE_WAX_OFF == toolAction)
            return IWeatheringBlock.scrapeOffWax(state, context, toolAction);

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
