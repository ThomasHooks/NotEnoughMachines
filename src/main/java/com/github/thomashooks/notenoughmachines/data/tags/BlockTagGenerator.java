package com.github.thomashooks.notenoughmachines.data.tags;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.util.NEMTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider
{
    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, NotEnoughMachines.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider providerIn)
    {
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                AllBlocks.AXLE.get(),
                AllBlocks.COGWHEEL_LARGE.get(),
                AllBlocks.COGWHEEL_SMALL.get(),
                AllBlocks.ENCLOSED_AXLE.get(),
                AllBlocks.FILTER.get(),
                AllBlocks.GEARBOX.get(),
                AllBlocks.MILLSTONE.get(),
                AllBlocks.ROLLING_MILL.get(),
                AllBlocks.TRIP_HAMMER.get(),
                AllBlocks.WATER_WHEEL.get(),
                AllBlocks.WIND_WHEEL.get(),
                AllBlocks.WOODEN_FRAME.get()
        );
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                AllBlocks.AXLE.get(),
                AllBlocks.BRONZE_BLOCK.get(),
                AllBlocks.COGWHEEL_LARGE.get(),
                AllBlocks.COGWHEEL_SMALL.get(),
                AllBlocks.COKE_BLOCK.get(),
                AllBlocks.COKE_OVEN.get(),
                AllBlocks.COPPER_PLATE_BLOCK.get(),
                AllBlocks.CROSSOVER_RAIL.get(),
                AllBlocks.ENCLOSED_AXLE.get(),
                AllBlocks.FIRE_BRICKS.get(),
                AllBlocks.FIRE_BRICKS_SLAB.get(),
                AllBlocks.FIRE_BRICKS_STAIRS.get(),
                AllBlocks.FIRE_BRICKS_WALL.get(),
                AllBlocks.FILTER.get(),
                AllBlocks.FLUXSTONE.get(),
                AllBlocks.FLUXSTONE_SLAB.get(),
                AllBlocks.FLUXSTONE_STAIRS.get(),
                AllBlocks.FLUXSTONE_WALL.get(),
                AllBlocks.GEARBOX.get(),
                AllBlocks.HIGH_SPEED_RAIL.get(),
                AllBlocks.IRON_PLATE_BLOCK.get(),
                AllBlocks.MILLSTONE.get(),
                AllBlocks.ONE_WAY_RAIL.get(),
                AllBlocks.POLISHED_FLUXSTONE.get(),
                AllBlocks.POLISHED_FLUXSTONE_SLAB.get(),
                AllBlocks.POLISHED_FLUXSTONE_STAIRS.get(),
                AllBlocks.POLISHED_FLUXSTONE_WALL.get(),
                AllBlocks.ROLLING_MILL.get(),
                AllBlocks.TRIP_HAMMER.get(),
                AllBlocks.TIN_ORE.get(),
                AllBlocks.TIN_BLOCK.get(),
                AllBlocks.WATER_WHEEL.get(),
                AllBlocks.WIND_WHEEL.get(),
                AllBlocks.WOODEN_FRAME.get()
        );
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(
                AllBlocks.BRONZE_BLOCK.get(),
                AllBlocks.COKE_BLOCK.get(),
                AllBlocks.COKE_OVEN.get(),
                AllBlocks.COPPER_PLATE_BLOCK.get(),
                AllBlocks.FIRE_BRICKS.get(),
                AllBlocks.FIRE_BRICKS_SLAB.get(),
                AllBlocks.FIRE_BRICKS_STAIRS.get(),
                AllBlocks.FIRE_BRICKS_WALL.get(),
                AllBlocks.FLUXSTONE.get(),
                AllBlocks.FLUXSTONE_SLAB.get(),
                AllBlocks.FLUXSTONE_STAIRS.get(),
                AllBlocks.FLUXSTONE_WALL.get(),
                AllBlocks.IRON_PLATE_BLOCK.get(),
                AllBlocks.POLISHED_FLUXSTONE.get(),
                AllBlocks.POLISHED_FLUXSTONE_SLAB.get(),
                AllBlocks.POLISHED_FLUXSTONE_STAIRS.get(),
                AllBlocks.POLISHED_FLUXSTONE_WALL.get(),
                AllBlocks.TIN_BLOCK.get(),
                AllBlocks.TIN_ORE.get()
        );
        this.tag(BlockTags.WALLS).add(
                        AllBlocks.FIRE_BRICKS_WALL.get(),
                        AllBlocks.FLUXSTONE_WALL.get(),
                        AllBlocks.POLISHED_FLUXSTONE_WALL.get()
        );
        this.tag(BlockTags.RAILS).add(
                AllBlocks.CROSSOVER_RAIL.get(),
                AllBlocks.HIGH_SPEED_RAIL.get(),
                AllBlocks.ONE_WAY_RAIL.get()
        );
        this.tag(BlockTags.PREVENT_MOB_SPAWNING_INSIDE).add(
                AllBlocks.CROSSOVER_RAIL.get(),
                AllBlocks.HIGH_SPEED_RAIL.get(),
                AllBlocks.ONE_WAY_RAIL.get()
        );
        this.tag(NEMTags.Blocks.STRIPPED_LOGS).add(
                Blocks.STRIPPED_OAK_LOG,
                Blocks.STRIPPED_SPRUCE_LOG,
                Blocks.STRIPPED_BIRCH_LOG,
                Blocks.STRIPPED_JUNGLE_LOG,
                Blocks.STRIPPED_ACACIA_LOG,
                Blocks.STRIPPED_CHERRY_LOG,
                Blocks.STRIPPED_DARK_OAK_LOG,
                Blocks.STRIPPED_MANGROVE_LOG,
                Blocks.STRIPPED_CRIMSON_STEM,
                Blocks.STRIPPED_WARPED_STEM,
                Blocks.STRIPPED_OAK_WOOD,
                Blocks.STRIPPED_SPRUCE_WOOD,
                Blocks.STRIPPED_BIRCH_WOOD,
                Blocks.STRIPPED_JUNGLE_WOOD,
                Blocks.STRIPPED_ACACIA_WOOD,
                Blocks.STRIPPED_CHERRY_WOOD,
                Blocks.STRIPPED_DARK_OAK_WOOD,
                Blocks.STRIPPED_MANGROVE_WOOD,
                Blocks.STRIPPED_CRIMSON_HYPHAE,
                Blocks.STRIPPED_WARPED_HYPHAE,
                Blocks.STRIPPED_BAMBOO_BLOCK
        );
    }
}
