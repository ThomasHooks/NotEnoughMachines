package com.github.thomashooks.notenoughmachines.data.loot.table;

import com.github.thomashooks.notenoughmachines.world.block.FlaxPlantBlock;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
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
        dropSelf(AllBlocks.COGWHEEL_SMALL.get());
        dropSelf(AllBlocks.CONJUNCTIONER.get());
        dropSelf(AllBlocks.COPPER_PLATE_BLOCK.get());
        dropSelf(AllBlocks.ENCLOSED_AXLE.get());
        dropSelf(AllBlocks.GEARBOX.get());
        dropSelf(AllBlocks.FILTER.get());
        dropCropAndSeed(AllBlocks.FLAXPLANT.get(), AllItems.FLAX.get(), AllItems.FLAXSEED.get(), FlaxPlantBlock.AGE, FlaxPlantBlock.MAX_AGE);
        dropSelf(AllBlocks.FLUXSTONE.get());
        add(AllBlocks.FLUXSTONE_SLAB.get(), block -> createSlabItemTable(AllBlocks.FLUXSTONE_SLAB.get()));
        dropSelf(AllBlocks.FLUXSTONE_STAIRS.get());
        dropSelf(AllBlocks.FLUXSTONE_WALL.get());
        dropSelf(AllBlocks.IRON_PLATE_BLOCK.get());
        dropSelf(AllBlocks.LINEN_BLOCK.get());
        dropSelf(AllBlocks.MILLSTONE.get());
        dropSelf(AllBlocks.POLISHED_FLUXSTONE.get());
        add(AllBlocks.POLISHED_FLUXSTONE_SLAB.get(), block -> createSlabItemTable(AllBlocks.POLISHED_FLUXSTONE_SLAB.get()));
        dropSelf(AllBlocks.POLISHED_FLUXSTONE_STAIRS.get());
        dropSelf(AllBlocks.POLISHED_FLUXSTONE_WALL.get());
        dropSelf(AllBlocks.WATER_WHEEL.get());
        dropSelf(AllBlocks.WOODEN_FRAME.get());
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
