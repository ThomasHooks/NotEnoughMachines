package com.github.thomashooks.notenoughmachines.data.worldgen;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.data.worldgen.biome.AllBiomeModifiers;
import com.github.thomashooks.notenoughmachines.data.worldgen.features.CustomOreFeatures;
import com.github.thomashooks.notenoughmachines.data.worldgen.placement.CustomOrePlacement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class NEMWorldGenProvider extends DatapackBuiltinEntriesProvider
{
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, CustomOreFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, CustomOrePlacement::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, AllBiomeModifiers::bootstrap);

    public NEMWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, BUILDER, Set.of(NotEnoughMachines.MOD_ID));
    }
}
