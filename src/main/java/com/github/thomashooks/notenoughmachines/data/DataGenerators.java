package com.github.thomashooks.notenoughmachines.data;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.data.locale.EnglishLanguageGenerator;
import com.github.thomashooks.notenoughmachines.data.loot.modifier.NEMGlobalLootModifiersProvider;
import com.github.thomashooks.notenoughmachines.data.loot.table.LootTableGenerator;
import com.github.thomashooks.notenoughmachines.data.models.BlockStateGenerator;
import com.github.thomashooks.notenoughmachines.data.models.ItemModelGenerator;
import com.github.thomashooks.notenoughmachines.data.recipes.RecipeGenerator;
import com.github.thomashooks.notenoughmachines.data.tags.BlockTagGenerator;
import com.github.thomashooks.notenoughmachines.data.tags.ItemTagGenerator;
import com.github.thomashooks.notenoughmachines.data.worldgen.NEMWorldGenProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = NotEnoughMachines.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new RecipeGenerator(packOutput));
        generator.addProvider(event.includeServer(), LootTableGenerator.create(packOutput));
        generator.addProvider(event.includeServer(), new NEMGlobalLootModifiersProvider(packOutput));
        BlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(), new BlockTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new NEMWorldGenProvider(packOutput, lookupProvider));

        generator.addProvider(event.includeClient(), new EnglishLanguageGenerator(packOutput));
        generator.addProvider(event.includeClient(), new BlockStateGenerator(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ItemModelGenerator(packOutput, existingFileHelper));
    }
}
