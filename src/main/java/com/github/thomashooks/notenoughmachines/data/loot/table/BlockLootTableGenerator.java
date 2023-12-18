package com.github.thomashooks.notenoughmachines.data.loot.table;

import com.github.thomashooks.notenoughmachines.world.block.CogwheelLargeBlock;
import com.github.thomashooks.notenoughmachines.world.block.FlaxPlantBlock;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.block.TripHammerBlock;
import com.github.thomashooks.notenoughmachines.world.block.state.MultiBlockPart1x1x4;
import com.github.thomashooks.notenoughmachines.world.block.state.MultiBlockPart3x1x3;
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
        dropSelf(AllBlocks.BUFFER_STOP_RAIL.get());
        dropSelf(AllBlocks.CHIME_RAIL.get());
        add(AllBlocks.COGWHEEL_LARGE.get(), (block) -> { return this.createSinglePropConditionTable(block, CogwheelLargeBlock.COGWHEEL_PART, MultiBlockPart3x1x3.CENTER); });
        dropSelf(AllBlocks.COGWHEEL_SMALL.get());
        dropSelf(AllBlocks.COKE_BLOCK.get());
        dropSelf(AllBlocks.COKE_OVEN.get());
        dropSelf(AllBlocks.CONJUNCTIONER.get());
        dropSelf(AllBlocks.COPPER_PLATE_BLOCK.get());
        dropSelf(AllBlocks.CROSSOVER_RAIL.get());
        dropSelf(AllBlocks.ENCLOSED_AXLE.get());
        dropSelf(AllBlocks.GEARBOX.get());
        dropSelf(AllBlocks.FIRE_BRICKS.get());
        add(AllBlocks.FIRE_BRICKS_SLAB.get(), block -> createSlabItemTable(AllBlocks.FIRE_BRICKS_SLAB.get()));
        dropSelf(AllBlocks.FIRE_BRICKS_STAIRS.get());
        dropSelf(AllBlocks.FIRE_BRICKS_WALL.get());
        dropSelf(AllBlocks.FILTER.get());
        dropCropAndSeed(AllBlocks.FLAXPLANT.get(), AllItems.FLAX.get(), AllItems.FLAXSEED.get(), FlaxPlantBlock.AGE, FlaxPlantBlock.MAX_AGE);
        dropSelf(AllBlocks.FLUXSTONE.get());
        add(AllBlocks.FLUXSTONE_SLAB.get(), block -> createSlabItemTable(AllBlocks.FLUXSTONE_SLAB.get()));
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
        dropSelf(AllBlocks.HIGH_SPEED_LOCKING_RAIL.get());
        dropSelf(AllBlocks.IRON_PLATE_BLOCK.get());
        dropSelf(AllBlocks.LINEN_BLOCK.get());
        dropSelf(AllBlocks.LOCKING_RAIL.get());
        dropSelf(AllBlocks.MILLSTONE.get());
        dropSelf(AllBlocks.ONE_WAY_RAIL.get());
        dropSelf(AllBlocks.POLISHED_FLUXSTONE.get());
        add(AllBlocks.POLISHED_FLUXSTONE_SLAB.get(), block -> createSlabItemTable(AllBlocks.POLISHED_FLUXSTONE_SLAB.get()));
        dropSelf(AllBlocks.POLISHED_FLUXSTONE_STAIRS.get());
        dropSelf(AllBlocks.POLISHED_FLUXSTONE_WALL.get());
        dropSelf(AllBlocks.ROLLING_MILL.get());
        add(AllBlocks.TRIP_HAMMER.get(), (block) -> { return this.createSinglePropConditionTable(block, TripHammerBlock.PART, MultiBlockPart1x1x4.BOTTOM); });
        dropSelf(AllBlocks.TIN_BLOCK.get());
        this.add(AllBlocks.TIN_ORE.get(), (block) -> { return this.createOreDrop(block, AllItems.RAW_TIN.get()); });
        dropSelf(AllBlocks.VERMILION_BLOCK.get());
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

    private void dropCropAndSeed(Block cropBlock, Item crop, Item seed, IntegerProperty age, int maxAge)
    {
        LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(cropBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(age, maxAge));
        add(cropBlock, createCropDrops(cropBlock, crop, seed, lootitemcondition$builder1));
    }
}
