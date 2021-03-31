package com.kilroy790.notenoughmachines.data;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.data.loottables.LootTableDataGen;
import com.kilroy790.notenoughmachines.data.recipes.RecipesDataGen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;




@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerated 
{	
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) 
    {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(new RecipesDataGen(generator));
        generator.addProvider(new LanguageDataGen(generator, NotEnoughMachines.MODID, "en_us"));
        generator.addProvider(new LootTableDataGen(generator));
    }
}



