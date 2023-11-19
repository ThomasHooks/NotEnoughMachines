package com.github.thomashooks.notenoughmachines.data.loot.modifier;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class NEMGlobalLootModifiersProvider extends GlobalLootModifierProvider
{

    public NEMGlobalLootModifiersProvider(PackOutput output)
    {
        super(output, NotEnoughMachines.MOD_ID);
    }

    @Override
    protected void start()
    {
        add("flaxseed_from_grass", new AddItemLootModifier(new LootItemCondition[]
                {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.15F).build()
                }, AllItems.FLAXSEED.get()));
        add("flaxseed_from_tall_grass", new AddItemLootModifier(new LootItemCondition[]
                {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.TALL_GRASS).build(),
                        LootItemRandomChanceCondition.randomChance(0.2F).build()
                }, AllItems.FLAXSEED.get()));
        add("flaxseed_from_fern", new AddItemLootModifier(new LootItemCondition[]
                {
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.FERN).build(),
                        LootItemRandomChanceCondition.randomChance(0.125F).build()
                }, AllItems.FLAXSEED.get()));
    }
}
