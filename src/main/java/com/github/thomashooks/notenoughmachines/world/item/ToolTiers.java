package com.github.thomashooks.notenoughmachines.world.item;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.integration.tags.AllTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ToolTiers
{
    public static final Tier BRONZE = TierSortingRegistry.registerTier(
            new ForgeTier(2, 906, 5.0F, 2.0F, 15, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(AllTags.Items.BRONZE_INGOTS)),
            new ResourceLocation(NotEnoughMachines.MOD_ID, "bronze"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND)
    );
}
