package com.github.thomashooks.notenoughmachines;

import com.github.thomashooks.notenoughmachines.world.block.NEMBlocks;
import com.github.thomashooks.notenoughmachines.world.block.entity.NEMBlockEntities;
import com.github.thomashooks.notenoughmachines.data.loot.modifier.NEMLootModifiers;
import com.github.thomashooks.notenoughmachines.world.item.NEMCreativeTabs;
import com.github.thomashooks.notenoughmachines.world.item.NEMItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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

    public NotEnoughMachines()
    {
        MOD_INSTANCE = this;

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientRegistries);

        LOGGER.info("registering all NEM Blocks");
        NEMBlocks.registerAll(modEventBus);

        LOGGER.info("registering all NEM Block Entities");
        NEMBlockEntities.registerAll(modEventBus);

        LOGGER.info("registering all NEM Items");
        NEMItems.registerAll(modEventBus);

        LOGGER.info("registering all NEM Creative Tabs");
        NEMCreativeTabs.registerAll(modEventBus);

        LOGGER.info("registering all NEM Loot Modifiers");
        NEMLootModifiers.registerAll(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Starting NEM pre-initiation");

        LOGGER.info("NEM pre-initiation done");
    }

    private void clientRegistries(final FMLClientSetupEvent event)
    {
        LOGGER.info("registering NEM Client side entries");

        //LOGGER.info("Setting NEM Block render types");
        //ItemBlockRenderTypes.setRenderLayer(NEMBlocks.FLAXPLANT.get(), RenderType.cutout());

        LOGGER.info("Registering all NEM Block Entity Renderers");

        LOGGER.info("Registering all NEM Containers");

        LOGGER.info("All NEM Client side entries registered");
    }
}
