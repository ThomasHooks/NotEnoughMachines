package com.github.thomashooks.notenoughmachines.events;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.client.gui.screens.*;
import com.github.thomashooks.notenoughmachines.client.render.blockentity.*;
import com.github.thomashooks.notenoughmachines.client.render.blockentity.model.*;
import com.github.thomashooks.notenoughmachines.world.block.entity.AllBlockEntities;
import com.github.thomashooks.notenoughmachines.world.inventory.AllMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = NotEnoughMachines.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents
{
    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event)
    {
        //NotEnoughMachines.LOGGER.debug(NotEnoughMachines.MOD_ID + ":setting Block render types");
        //ItemBlockRenderTypes.setRenderLayer(NEMBlocks.FLAXPLANT.get(), RenderType.cutout());

        event.enqueueWork(()->
        {
            NotEnoughMachines.LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Screens");
            MenuScreens.register(AllMenus.COKE_OVEN.get(), CokeOvenScreen::new);
            MenuScreens.register(AllMenus.FILTER.get(), FilterScreen::new);
            MenuScreens.register(AllMenus.MILLSTONE.get(), MillstoneScreen::new);
            MenuScreens.register(AllMenus.ROLLING_MILL.get(), RollingMillScreen::new);
            MenuScreens.register(AllMenus.TRIP_HAMMER.get(), TripHammerScreen::new);
        });
    }

    @SubscribeEvent
    public static void registerLayersDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        NotEnoughMachines.LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Layer Definitions");
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
        NotEnoughMachines.LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Block Entity Renderers");
        event.registerBlockEntityRenderer(AllBlockEntities.AXLE.get(), AxleRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.COGWHEEL_SMALL.get(), CogwheelSmallRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.COGWHEEL_LARGE.get(), CogwheelLargeRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.ENCLOSED_AXLE.get(), EnclosedAxleRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.GEARBOX.get(), GearboxRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.MILLSTONE.get(), MillstoneRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.ROLLING_MILL.get(), RollingMillRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.TRIP_HAMMER.get(), TripHammerRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.WATER_WHEEL.get(), WaterWheelRenderer::new);
        event.registerBlockEntityRenderer(AllBlockEntities.WIND_WHEEL.get(), WindWheelRenderer::new);
    }

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event)
    {
        //NotEnoughMachines.LOGGER.debug(NotEnoughMachines.MOD_ID + ":registering all Key Mappings");
    }
}
