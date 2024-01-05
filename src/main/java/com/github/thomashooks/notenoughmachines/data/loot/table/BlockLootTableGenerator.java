package com.github.thomashooks.notenoughmachines.data.loot.table;

import com.github.thomashooks.notenoughmachines.world.block.CogwheelLargeBlock;
import com.github.thomashooks.notenoughmachines.world.block.FlaxPlantBlock;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.block.TripHammerBlock;
import com.github.thomashooks.notenoughmachines.world.block.state.properties.MultiBlockPart1x1x4;
import com.github.thomashooks.notenoughmachines.world.block.state.properties.MultiBlockPart3x1x3;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class BlockLootTableGenerator extends BlockLootSubProvider
{
    protected BlockLootTableGenerator()
    {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate()
    {
        dropSelf(AllBlocks.AXLE.get());
        dropSelf(AllBlocks.BRONZE_BLOCK.get());
        dropSelf(AllBlocks.BRONZE_LADDER.get());
        dropSelf(AllBlocks.BRONZE_PLATE_BLOCK.get());
        dropSlab(AllBlocks.BRONZE_PLATE_SLAB.get());
        dropSelf(AllBlocks.BRONZE_PLATE_STAIRS.get());
        dropSelf(AllBlocks.BRONZE_SCAFFOLDING.get());
        dropSelf(AllBlocks.BUFFER_STOP_RAIL.get());
        dropSelf(AllBlocks.CHIME_RAIL.get());
        add(AllBlocks.COGWHEEL_LARGE.get(), (block) -> { return this.createSinglePropConditionTable(block, CogwheelLargeBlock.COGWHEEL_PART, MultiBlockPart3x1x3.CENTER); });
        dropSelf(AllBlocks.COGWHEEL_SMALL.get());
        dropSelf(AllBlocks.COKE_BLOCK.get());
        dropSelf(AllBlocks.COKE_OVEN.get());
        dropSelf(AllBlocks.CONJUNCTIONER.get());
        dropSelf(AllBlocks.COPPER_PLATE_BLOCK.get());
        dropSlab(AllBlocks.COPPER_PLATE_SLAB.get());
        dropSelf(AllBlocks.COPPER_PLATE_STAIRS.get());
        dropSelf(AllBlocks.CROSSOVER_RAIL.get());
        dropSelf(AllBlocks.ENCLOSED_AXLE.get());
        dropSelf(AllBlocks.GEARBOX.get());
        dropSelf(AllBlocks.GOLD_PLATE_BLOCK.get());
        dropSlab(AllBlocks.GOLD_PLATE_SLAB.get());
        dropSelf(AllBlocks.GOLD_PLATE_STAIRS.get());
        dropSelf(AllBlocks.FIRE_BRICKS.get());
        dropSlab(AllBlocks.FIRE_BRICKS_SLAB.get());
        dropSelf(AllBlocks.FIRE_BRICKS_STAIRS.get());
        dropSelf(AllBlocks.FIRE_BRICKS_WALL.get());
        dropSelf(AllBlocks.FILTER.get());
        dropCropAndSeed(AllBlocks.FLAXPLANT.get(), AllItems.FLAX.get(), AllItems.FLAXSEED.get(), FlaxPlantBlock.AGE, FlaxPlantBlock.MAX_AGE);
        dropSelf(AllBlocks.FLUXSTONE.get());
        dropSlab(AllBlocks.FLUXSTONE_SLAB.get());
        dropSelf(AllBlocks.FLUXSTONE_STAIRS.get());
        dropSelf(AllBlocks.FLUXSTONE_WALL.get());
        dropSelf(AllBlocks.HIGH_SPEED_RAIL.get());
        dropSelf(AllBlocks.HIGH_SPEED_ACTIVATOR_RAIL.get());
        dropSelf(AllBlocks.HIGH_SPEED_BUFFER_STOP_RAIL.get());
        dropSelf(AllBlocks.HIGH_SPEED_CHIME_RAIL.get());
        dropSelf(AllBlocks.HIGH_SPEED_CROSSOVER_RAIL.get());
        dropSelf(AllBlocks.HIGH_SPEED_DETECTOR_RAIL.get());
        dropSelf(AllBlocks.HIGH_SPEED_ONE_WAY_RAIL.get());
        dropSelf(AllBlocks.HIGH_SPEED_POWERED_RAIL.get());
        dropSelf(AllBlocks.HIGH_SPEED_LIMITER_RAIL.get());
        dropSelf(AllBlocks.HIGH_SPEED_LOCKING_RAIL.get());
        dropSelf(AllBlocks.IRON_LADDER.get());
        dropSelf(AllBlocks.IRON_PLATE_BLOCK.get());
        dropSlab(AllBlocks.IRON_PLATE_SLAB.get());
        dropSelf(AllBlocks.IRON_PLATE_STAIRS.get());
        dropSelf(AllBlocks.IRON_SCAFFOLDING.get());
        dropSelf(AllBlocks.LINEN_BLOCK.get());
        dropSelf(AllBlocks.LINEN_BLOCK_WHITE.get());
        dropSelf(AllBlocks.LINEN_BLOCK_ORANGE.get());
        dropSelf(AllBlocks.LINEN_BLOCK_MAGENTA.get());
        dropSelf(AllBlocks.LINEN_BLOCK_LIGHT_BLUE.get());
        dropSelf(AllBlocks.LINEN_BLOCK_YELLOW.get());
        dropSelf(AllBlocks.LINEN_BLOCK_LIME.get());
        dropSelf(AllBlocks.LINEN_BLOCK_PINK.get());
        dropSelf(AllBlocks.LINEN_BLOCK_GRAY.get());
        dropSelf(AllBlocks.LINEN_BLOCK_LIGHT_GRAY.get());
        dropSelf(AllBlocks.LINEN_BLOCK_CYAN.get());
        dropSelf(AllBlocks.LINEN_BLOCK_PURPLE.get());
        dropSelf(AllBlocks.LINEN_BLOCK_BLUE.get());
        dropSelf(AllBlocks.LINEN_BLOCK_BROWN.get());
        dropSelf(AllBlocks.LINEN_BLOCK_GREEN.get());
        dropSelf(AllBlocks.LINEN_BLOCK_RED.get());
        dropSelf(AllBlocks.LINEN_BLOCK_BLACK.get());
        dropSelf(AllBlocks.LIMITER_RAIL.get());
        dropSelf(AllBlocks.LOCKING_RAIL.get());
        dropSelf(AllBlocks.MILLSTONE.get());
        dropSelf(AllBlocks.ONE_WAY_RAIL.get());
        dropSelf(AllBlocks.POLISHED_FLUXSTONE.get());
        dropSlab(AllBlocks.POLISHED_FLUXSTONE_SLAB.get());
        dropSelf(AllBlocks.POLISHED_FLUXSTONE_STAIRS.get());
        dropSelf(AllBlocks.POLISHED_FLUXSTONE_WALL.get());
        dropSelf(AllBlocks.RAW_TIN_BLOCK.get());
        dropSelf(AllBlocks.ROLLING_MILL.get());
        add(AllBlocks.TRIP_HAMMER.get(), (block) -> { return this.createSinglePropConditionTable(block, TripHammerBlock.PART, MultiBlockPart1x1x4.BOTTOM); });
        dropSelf(AllBlocks.TIN_BLOCK.get());
        dropSelf(AllBlocks.TIN_PLATE_BLOCK.get());
        dropSlab(AllBlocks.TIN_PLATE_SLAB.get());
        dropSelf(AllBlocks.TIN_PLATE_STAIRS.get());
        this.add(AllBlocks.TIN_ORE.get(), (block) -> { return this.createOreDrop(block, AllItems.RAW_TIN.get()); });
        dropSelf(AllBlocks.VERMILION_BLOCK.get());
        dropSelf(AllBlocks.VERMILION_PRESSURE_PLATE.get());
        dropSelf(AllBlocks.WATER_WHEEL.get());
        dropSelf(AllBlocks.WIND_WHEEL.get());
        dropSelf(AllBlocks.WOODEN_FRAME.get());
        dropSelf(AllBlocks.WOODEN_FRAME_SLAB.get());
        dropSelf(AllBlocks.WOODEN_FRAME_STAIRS.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return AllBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    protected void dropCropAndSeed(Block cropBlock, Item crop, Item seed, IntegerProperty age, int maxAge)
    {
        LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(cropBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(age, maxAge));
        add(cropBlock, createCropDrops(cropBlock, crop, seed, lootitemcondition$builder1));
    }

    protected void dropSlab(Block slab)
    {
        add(slab, block -> createSlabItemTable(slab));
    }
}
