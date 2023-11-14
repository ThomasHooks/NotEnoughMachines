package com.github.thomashooks.notenoughmachines.data.loot.table;

import com.github.thomashooks.notenoughmachines.world.block.FlaxPlantBlock;
import com.github.thomashooks.notenoughmachines.world.block.NEMBlocks;
import com.github.thomashooks.notenoughmachines.world.item.NEMItems;
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
        dropSelf(NEMBlocks.CONJUNCTIONER.get());
        dropSelf(NEMBlocks.COPPER_PLATE_BLOCK.get());
        dropCropAndSeed(NEMBlocks.FLAXPLANT.get(), NEMItems.FLAX.get(), NEMItems.FLAXSEED.get(), FlaxPlantBlock.AGE, FlaxPlantBlock.MAX_AGE);
        dropSelf(NEMBlocks.FLUXSTONE.get());
        add(NEMBlocks.FLUXSTONE_SLAB.get(), block -> createSlabItemTable(NEMBlocks.FLUXSTONE_SLAB.get()));
        dropSelf(NEMBlocks.FLUXSTONE_STAIRS.get());
        dropSelf(NEMBlocks.FLUXSTONE_WALL.get());
        dropSelf(NEMBlocks.IRON_PLATE_BLOCK.get());
        dropSelf(NEMBlocks.LINEN_BLOCK.get());
        dropSelf(NEMBlocks.POLISHED_FLUXSTONE.get());
        add(NEMBlocks.POLISHED_FLUXSTONE_SLAB.get(), block -> createSlabItemTable(NEMBlocks.POLISHED_FLUXSTONE_SLAB.get()));
        dropSelf(NEMBlocks.POLISHED_FLUXSTONE_STAIRS.get());
        dropSelf(NEMBlocks.POLISHED_FLUXSTONE_WALL.get());
        dropSelf(NEMBlocks.WOODEN_FRAME.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks()
    {
        return NEMBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    private void dropCropAndSeed(Block cropBlock, Item crop, Item seed, IntegerProperty age, int maxAge)
    {
        LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(cropBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(age, maxAge));
        add(cropBlock, createCropDrops(cropBlock, crop, seed, lootitemcondition$builder1));
    }
}
