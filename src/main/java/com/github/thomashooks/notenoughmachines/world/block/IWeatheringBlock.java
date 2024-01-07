package com.github.thomashooks.notenoughmachines.world.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

public interface IWeatheringBlock extends ChangeOverTimeBlock<IWeatheringBlock.State>
{
    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() ->
    {
        return ImmutableBiMap.<Block, Block>builder()
                .put(AllBlocks.COPPER_PLATE_BLOCK.get(), AllBlocks.COPPER_PLATE_BLOCK_EXPOSED.get())
                .put(AllBlocks.COPPER_PLATE_BLOCK_EXPOSED.get(), AllBlocks.COPPER_PLATE_BLOCK_WEATHERED.get())
                .put(AllBlocks.COPPER_PLATE_BLOCK_WEATHERED.get(), AllBlocks.COPPER_PLATE_BLOCK_OXIDIZED.get())
                .put(AllBlocks.COPPER_PLATE_SLAB.get(), AllBlocks.COPPER_PLATE_SLAB_EXPOSED.get())
                .put(AllBlocks.COPPER_PLATE_SLAB_EXPOSED.get(), AllBlocks.COPPER_PLATE_SLAB_WEATHERED.get())
                .put(AllBlocks.COPPER_PLATE_SLAB_WEATHERED.get(), AllBlocks.COPPER_PLATE_SLAB_OXIDIZED.get())
                .put(AllBlocks.COPPER_PLATE_STAIRS.get(), AllBlocks.COPPER_PLATE_STAIRS_EXPOSED.get())
                .put(AllBlocks.COPPER_PLATE_STAIRS_EXPOSED.get(), AllBlocks.COPPER_PLATE_STAIRS_WEATHERED.get())
                .put(AllBlocks.COPPER_PLATE_STAIRS_WEATHERED.get(), AllBlocks.COPPER_PLATE_STAIRS_OXIDIZED.get())
                .build();
    });
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());
    Supplier<BiMap<Block, Block>> WAXED_BY_BLOCK = Suppliers.memoize(() ->
    {
        return ImmutableBiMap.<Block, Block>builder()
                .put(AllBlocks.COPPER_PLATE_BLOCK.get(), AllBlocks.COPPER_PLATE_BLOCK_WAXED.get())
                .put(AllBlocks.COPPER_PLATE_BLOCK_EXPOSED.get(), AllBlocks.COPPER_PLATE_BLOCK_EXPOSED_WAXED.get())
                .put(AllBlocks.COPPER_PLATE_BLOCK_WEATHERED.get(), AllBlocks.COPPER_PLATE_BLOCK_WEATHERED_WAXED.get())
                .put(AllBlocks.COPPER_PLATE_BLOCK_OXIDIZED.get(), AllBlocks.COPPER_PLATE_BLOCK_OXIDIZED_WAXED.get())
                .put(AllBlocks.COPPER_PLATE_SLAB.get(), AllBlocks.COPPER_PLATE_SLAB_WAXED.get())
                .put(AllBlocks.COPPER_PLATE_SLAB_EXPOSED.get(), AllBlocks.COPPER_PLATE_SLAB_EXPOSED_WAXED.get())
                .put(AllBlocks.COPPER_PLATE_SLAB_WEATHERED.get(), AllBlocks.COPPER_PLATE_SLAB_WEATHERED_WAXED.get())
                .put(AllBlocks.COPPER_PLATE_SLAB_OXIDIZED.get(), AllBlocks.COPPER_PLATE_SLAB_OXIDIZED_WAXED.get())
                .put(AllBlocks.COPPER_PLATE_STAIRS.get(), AllBlocks.COPPER_PLATE_STAIRS_WAXED.get())
                .put(AllBlocks.COPPER_PLATE_STAIRS_EXPOSED.get(), AllBlocks.COPPER_PLATE_STAIRS_EXPOSED_WAXED.get())
                .put(AllBlocks.COPPER_PLATE_STAIRS_WEATHERED.get(), AllBlocks.COPPER_PLATE_STAIRS_WEATHERED_WAXED.get())
                .put(AllBlocks.COPPER_PLATE_STAIRS_OXIDIZED.get(), AllBlocks.COPPER_PLATE_STAIRS_OXIDIZED_WAXED.get())
                .build();
    });
    Supplier<BiMap<Block, Block>> WAX_OFF_BY_BLOCK = Suppliers.memoize(() -> WAXED_BY_BLOCK.get().inverse());

    public static enum State
    {
        UNAFFECTED,
        EXPOSED,
        WEATHERED,
        OXIDIZED;
    }

    static Block getFirst(Block blockIn)
    {
        Block weatheringBlock = blockIn;
        for(Block block = PREVIOUS_BY_BLOCK.get().get(blockIn); block != null; block = PREVIOUS_BY_BLOCK.get().get(block))
            weatheringBlock = block;
        return weatheringBlock;
    }

    static BlockState getFirst(BlockState state) { return getFirst(state.getBlock()).withPropertiesOf(state); }

    static Optional<BlockState> getPrevious(BlockState state) { return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(state.getBlock())).map((block) -> block.withPropertiesOf(state)); }

    static Optional<Block> getNext(Block block) { return Optional.ofNullable(NEXT_BY_BLOCK.get().get(block)); }

    @Override
    default @NotNull Optional<BlockState> getNext(BlockState state) { return getNext(state.getBlock()).map((block) -> block.withPropertiesOf(state)); }

    @Override
    default float getChanceModifier() { return this.getAge() == IWeatheringBlock.State.UNAFFECTED ? 0.75F : 1.0F; }

    static Optional<BlockState> getWaxed(BlockState state) { return Optional.ofNullable(WAXED_BY_BLOCK.get().get(state.getBlock())).map(block -> block.withPropertiesOf(state)); }

    static Optional<BlockState> getWaxOff(BlockState state) { return Optional.ofNullable(WAX_OFF_BY_BLOCK.get().get(state.getBlock())).map(block -> block.withPropertiesOf(state)); }

    static boolean isWaxed(BlockState state) { return WAX_OFF_BY_BLOCK.get().containsKey(state.getBlock()); }

    @Nullable
    static BlockState scrapeOffOxidation(BlockState state, UseOnContext context, ToolAction toolAction)
    {
        if (context.getItemInHand().getItem() instanceof AxeItem && toolAction.equals(ToolActions.AXE_SCRAPE))
        {
            Optional<BlockState> previousState = getPrevious(state);
            if (previousState.isPresent())
                return previousState.get();
        }
        return null;
    }

    @Nullable
    static BlockState scrapeOffWax(BlockState state, UseOnContext context, ToolAction toolAction)
    {
        if (isWaxed(state) && context.getItemInHand().getItem() instanceof AxeItem && toolAction.equals(ToolActions.AXE_WAX_OFF))
        {
            Optional<BlockState> waxOffState = getWaxOff(state);
            if (waxOffState.isPresent())
                return waxOffState.get();
        }
        return null;
    }

    static InteractionResult applyWax(BlockState state, Level world, BlockPos pos, Player player, ItemStack honeycombItemStack)
    {
        Optional<BlockState> waxedState = getWaxed(state);
        if (waxedState.isPresent())
        {
            if (player instanceof ServerPlayer serverPlayer)
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, honeycombItemStack);

            if (!player.isCreative())
                honeycombItemStack.shrink(1);

            world.setBlock(pos, waxedState.get(), 11);
            world.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, waxedState.get()));
            world.levelEvent(player, 3003, pos, 0);
            return InteractionResult.sidedSuccess(world.isClientSide());
        }
        return InteractionResult.PASS;
    }
}
