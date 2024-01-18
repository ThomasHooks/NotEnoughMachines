package com.github.thomashooks.notenoughmachines.events;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.client.gui.screens.*;
import com.github.thomashooks.notenoughmachines.client.gui.screens.tooltip.ClientItemPouchTooltip;
import com.github.thomashooks.notenoughmachines.client.render.blockentity.*;
import com.github.thomashooks.notenoughmachines.client.render.blockentity.model.*;
import com.github.thomashooks.notenoughmachines.world.block.entity.AllBlockEntities;
import com.github.thomashooks.notenoughmachines.world.inventory.AllMenus;
import com.github.thomashooks.notenoughmachines.world.inventory.tooltip.ItemPouchTooltip;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import com.github.thomashooks.notenoughmachines.world.item.PaddedArmorItem;
import com.github.thomashooks.notenoughmachines.world.item.PouchItem;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = NotEnoughMachines.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegistrationEvents
{
    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event)
    {
        NotEnoughMachines.LOGGER.debug(NotEnoughMachines.MOD_ID + ":setting block render types");
        //ItemBlockRenderTypes.setRenderLayer(NEMBlocks.FLAXPLANT.get(), RenderType.cutout());

        event.enqueueWork(() ->
        {
            NotEnoughMachines.LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all screens");
            MenuScreens.register(AllMenus.COKE_OVEN.orElseThrow(IllegalStateException::new), CokeOvenScreen::new);
            MenuScreens.register(AllMenus.FILTER.orElseThrow(IllegalStateException::new), FilterScreen::new);
            MenuScreens.register(AllMenus.MILLSTONE.orElseThrow(IllegalStateException::new), MillstoneScreen::new);
            MenuScreens.register(AllMenus.ROLLING_MILL.orElseThrow(IllegalStateException::new), RollingMillScreen::new);
            MenuScreens.register(AllMenus.SACK.orElseThrow(IllegalStateException::new), SackScreen::new);
            MenuScreens.register(AllMenus.TRIP_HAMMER.orElseThrow(IllegalStateException::new), TripHammerScreen::new);
        });


    }

    @SubscribeEvent
    public static void registerLayersDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        NotEnoughMachines.LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all layer definitions");
        event.registerLayerDefinition(AxleModelLayer.LOCATION, AxleModelLayer::createBody);
        event.registerLayerDefinition(CogwheelLargeModelLayer.LOCATION, CogwheelLargeModelLayer::createBody);
        event.registerLayerDefinition(CogwheelSmallModelLayer.LOCATION, CogwheelSmallModelLayer::createBody);
        event.registerLayerDefinition(MillstoneModelLayer.LOCATION, MillstoneModelLayer::createBody);
        event.registerLayerDefinition(RollingMillModelLayer.LOCATION, RollingMillModelLayer::createBody);
        event.registerLayerDefinition(TripHammerModelLayer.LOCATION, TripHammerModelLayer::createBody);
        event.registerLayerDefinition(WaterWheelModelLayer.LOCATION, WaterWheelModelLayer::createBody);
        event.registerLayerDefinition(WindWheelModelLayer.LOCATION, WindWheelModelLayer::createBody);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        NotEnoughMachines.LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all block entity renderers");
        event.registerBlockEntityRenderer(AllBlockEntities.AXLE.orElseThrow(IllegalStateException::new), AxleRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.COGWHEEL_SMALL.orElseThrow(IllegalStateException::new), CogwheelSmallRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.COGWHEEL_LARGE.orElseThrow(IllegalStateException::new), CogwheelLargeRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.ENCLOSED_AXLE.orElseThrow(IllegalStateException::new), EnclosedAxleRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.GEARBOX.orElseThrow(IllegalStateException::new), GearboxRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.MILLSTONE.orElseThrow(IllegalStateException::new), MillstoneRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.ROLLING_MILL.orElseThrow(IllegalStateException::new), RollingMillRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.TRIP_HAMMER.orElseThrow(IllegalStateException::new), TripHammerRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.WATER_WHEEL.orElseThrow(IllegalStateException::new), WaterWheelRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.WIND_WHEEL.orElseThrow(IllegalStateException::new), WindWheelRenderer::new);
    }

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event)
    {
        NotEnoughMachines.LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all key mappings");
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event)
    {
        NotEnoughMachines.LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all dyeable items");
        event.register((stack, layer) -> { return layer > 0 ? -1 : ((PaddedArmorItem)stack.getItem()).getColor(stack); },
                AllItems.PADDED_BOOTS.orElseThrow(IllegalStateException::new),
                AllItems.PADDED_CHESTPLATE.orElseThrow(IllegalStateException::new),
                AllItems.PADDED_HELMET.orElseThrow(IllegalStateException::new),
                AllItems.PADDED_LEGGINGS.orElseThrow(IllegalStateException::new)
        );
        event.register((stack, layer) -> { return layer > 0 ? -1 : ((PouchItem)stack.getItem()).getColor(stack); },
                AllItems.ITEM_POUCH.orElseThrow(IllegalStateException::new)
        );
    }

    @SubscribeEvent
    public static void registerClientTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event)
    {
        NotEnoughMachines.LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all tooltip components");
        event.register(ItemPouchTooltip.class, ClientItemPouchTooltip::new);
    }
}
