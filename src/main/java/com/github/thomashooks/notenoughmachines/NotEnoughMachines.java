/*
Copyright (c) 2024 Thomas Hooks

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package com.github.thomashooks.notenoughmachines;

import com.github.thomashooks.notenoughmachines.client.ClientTimer;
import com.github.thomashooks.notenoughmachines.data.loot.modifier.AllLootModifiers;
import com.github.thomashooks.notenoughmachines.integration.config.CommonConfigs;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.block.entity.AllBlockEntities;
import com.github.thomashooks.notenoughmachines.world.inventory.AllMenus;
import com.github.thomashooks.notenoughmachines.world.item.AllCreativeTabs;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import com.github.thomashooks.notenoughmachines.world.item.PaddedArmorItem;
import com.github.thomashooks.notenoughmachines.world.item.crafting.AllRecipes;
import com.github.thomashooks.notenoughmachines.world.power.PowerNetworkStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(NotEnoughMachines.MOD_ID)
@Mod.EventBusSubscriber(modid = NotEnoughMachines.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NotEnoughMachines
{
    public static NotEnoughMachines MOD_INSTANCE;
    public static final String MOD_ID = "notenoughmachines";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static PowerNetworkStack AETHER = new PowerNetworkStack();
    public static ClientTimer CLIENT_TIMER = new ClientTimer();

    public NotEnoughMachines()
    {
        MOD_INSTANCE = this;

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onCommonSetup);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOW, PaddedArmorItem::onLivingEntityFall);

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Blocks");
        AllBlocks.registerAll(modEventBus);

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Block Entities");
        AllBlockEntities.registerAll(modEventBus);

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Items");
        AllItems.registerAll(modEventBus);

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Menus");
        AllMenus.registerAll(modEventBus);

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Creative Tabs");
        AllCreativeTabs.registerAll(modEventBus);

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Recipes");
        AllRecipes.registerAll(modEventBus);

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Loot Modifiers");
        AllLootModifiers.registerAll(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfigs.SPEC,MOD_ID + "-common-configs.toml");
    }

    private void onCommonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.debug(NotEnoughMachines.MOD_ID + ":starting pre-initiation");

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":pre-initiation done");
    }
}
