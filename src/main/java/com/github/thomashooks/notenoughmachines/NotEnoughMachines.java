package com.github.thomashooks.notenoughmachines;

import com.github.thomashooks.notenoughmachines.client.ClientTimer;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.block.entity.AllBlockEntities;
import com.github.thomashooks.notenoughmachines.data.loot.modifier.NEMLootModifiers;
import com.github.thomashooks.notenoughmachines.world.inventory.AllMenus;
import com.github.thomashooks.notenoughmachines.world.item.NEMCreativeTabs;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import com.github.thomashooks.notenoughmachines.world.power.PowerNetworkStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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
        //modEventBus.addListener(this::onClientSetup);

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Blocks");
        AllBlocks.registerAll(modEventBus);

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Block Entities");
        AllBlockEntities.registerAll(modEventBus);

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Items");
        AllItems.registerAll(modEventBus);

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Menus");
        AllMenus.registerAll(modEventBus);

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Creative Tabs");
        NEMCreativeTabs.registerAll(modEventBus);

        //LOGGER.debug("registering all NEM Recipes");

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Loot Modifiers");
        NEMLootModifiers.registerAll(modEventBus);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.debug(NotEnoughMachines.MOD_ID + ":starting pre-initiation");

        LOGGER.debug(NotEnoughMachines.MOD_ID + ":pre-initiation done");
    }

   /* public void onClientSetup(final FMLClientSetupEvent event)
    {
        //LNotEnoughMachines.LOGGER.debug(NotEnoughMachines.MOD_ID + ":setting Block render types");
        //ItemBlockRenderTypes.setRenderLayer(NEMBlocks.FLAXPLANT.get(), RenderType.cutout());

        NotEnoughMachines.LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Screens");
        event.enqueueWork(()->
        {
            MenuScreens.register(NEMMenus.FILTER.get(), FilterScreen::new);
        });
    }*/
}
