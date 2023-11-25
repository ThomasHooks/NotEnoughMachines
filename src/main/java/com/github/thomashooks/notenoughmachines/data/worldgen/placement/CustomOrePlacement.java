package com.github.thomashooks.notenoughmachines.data.worldgen.placement;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.data.worldgen.features.CustomOreFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class CustomOrePlacement
{
    public static final ResourceKey<PlacedFeature> FLUXSTONE_ORE = registerKey("fluxstone_ore");
    public static final ResourceKey<PlacedFeature> TIN_ORE = registerKey("tin_ore");

    public static void bootstrap(BootstapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> fluxstoneHolder = holdergetter.getOrThrow(CustomOreFeatures.FLUXSTONE_ORE);
        Holder<ConfiguredFeature<?, ?>> tinHolder = holdergetter.getOrThrow(CustomOreFeatures.TIN_ORE);

        register(context, FLUXSTONE_ORE, fluxstoneHolder, commonOrePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(192))));
        register(context, TIN_ORE, tinHolder, commonOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(182))));
    }
    protected static List<PlacementModifier> orePlacement(PlacementModifier placementModifier, PlacementModifier heightRange)
    {
        return List.of(placementModifier, InSquarePlacement.spread(), heightRange, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange)
    {
        return orePlacement(CountPlacement.of(count), heightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightRange)
    {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);
    }

    private static ResourceKey<PlacedFeature> registerKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(NotEnoughMachines.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuredFeatureHolder, List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(configuredFeatureHolder, List.copyOf(modifiers)));
    }
}
