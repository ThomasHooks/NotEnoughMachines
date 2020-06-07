package com.kilroy790.notenoughmachines;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kilroy790.notenoughmachines.client.gui.screen.FilterScreen;
import com.kilroy790.notenoughmachines.client.gui.screen.MillstoneScreen;
import com.kilroy790.notenoughmachines.client.gui.screen.TripHammerScreen;
import com.kilroy790.notenoughmachines.client.renderers.tiles.AxleRenderer;
import com.kilroy790.notenoughmachines.client.renderers.tiles.MillstoneRenderer;
import com.kilroy790.notenoughmachines.client.renderers.tiles.TubWheelRenderer;
import com.kilroy790.notenoughmachines.containers.FilterContainer;
import com.kilroy790.notenoughmachines.containers.MillstoneContainer;
import com.kilroy790.notenoughmachines.containers.TripHammerContainer;
import com.kilroy790.notenoughmachines.power.PowerNetworkStack;
import com.kilroy790.notenoughmachines.recipes.MillingRecipeSerializer;
import com.kilroy790.notenoughmachines.recipes.MillingRecipe;
import com.kilroy790.notenoughmachines.setup.ContainerList;
import com.kilroy790.notenoughmachines.setup.ModSetup;
import com.kilroy790.notenoughmachines.setup.NEMBlocks;
import com.kilroy790.notenoughmachines.setup.NEMItems;
import com.kilroy790.notenoughmachines.setup.NEMMachineRecipes;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.utilities.ClientProxy;
import com.kilroy790.notenoughmachines.utilities.IProxy;
import com.kilroy790.notenoughmachines.utilities.ServerProxy;

import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;




@Mod("notenoughtmachines")
@Mod.EventBusSubscriber(modid = NotEnoughMachines.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NotEnoughMachines {
	
	public static NotEnoughMachines instance;
	public static final String MODID = "notenoughtmachines";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	public static PowerNetworkStack AETHER = new PowerNetworkStack();
	
	public static IProxy proxy = DistExecutor.runForDist(()-> ()-> new ClientProxy(), ()-> ()-> new ServerProxy());
	public static ModSetup setup = new ModSetup();
	
	
	
	public NotEnoughMachines() {
		
		instance = this;
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		MinecraftForge.EVENT_BUS.register(this);
		
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		NEMBlocks.registerAll(modEventBus);
		NEMItems.registerAll(modEventBus);
		NEMTiles.registerAll(modEventBus);
	}
	
	
	private void setup(final FMLCommonSetupEvent event) {
		LOGGER.info("Starting NEM preinit");
		setup.init();
		proxy.init();
		LOGGER.info("NEM preinit done");
	}

	
	private void clientRegistries(final FMLClientSetupEvent event) {
		LOGGER.info("registering NEM Client side entries");
		
		LOGGER.info("Setting NEM Block render types");
		RenderTypeLookup.setRenderLayer(NEMBlocks.FLAXPLANT.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(NEMBlocks.ANDGATE.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(NEMBlocks.ORGATE.get(), RenderType.getCutout());
		LOGGER.info("All NEM Block render types set");
		
		LOGGER.info("Registering all NEM Tile Entity Renderers");
		AxleRenderer.register();
		MillstoneRenderer.register();
		TubWheelRenderer.register();
		LOGGER.info("All NEM Tile Entity Renderers registered");
		
		LOGGER.info("Registering all NEM Containers");
		ScreenManager.registerFactory(ContainerList.MILLSTONE_CONTAINER, MillstoneScreen::new);
		ScreenManager.registerFactory(ContainerList.TRIPHAMMER, TripHammerScreen::new);
		ScreenManager.registerFactory(ContainerList.FILTER_CONTAINER, FilterScreen::new);
		LOGGER.info("All NEM Containers registered");
		
		LOGGER.info("All NEM Client side entries registered");
	}
	
	
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class registryEvents {
		
		//Register Containers
		@SubscribeEvent
		public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
			LOGGER.info("Registering all containers");
			
			LOGGER.info("Registering Millstone Container");
			event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new MillstoneContainer(windowId, NotEnoughMachines.proxy.getClientWorld(), pos, inv, NotEnoughMachines.proxy.getClientPlayer());
            }).setRegistryName("millstone"));
			
			LOGGER.info("Registering Trip Hammer Container");
			event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new TripHammerContainer(windowId, NotEnoughMachines.proxy.getClientWorld(), pos, inv, NotEnoughMachines.proxy.getClientPlayer());
            }).setRegistryName("triphammer"));
			
			LOGGER.info("Registering Filter Container");
			event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new FilterContainer(windowId, NotEnoughMachines.proxy.getClientWorld(), pos, inv, NotEnoughMachines.proxy.getClientPlayer());
            }).setRegistryName("filter"));
			
			LOGGER.info("Containers registered");
		}
		
		
		
		//Register recipes
		@SubscribeEvent
		public static void registerRecipes(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
			LOGGER.info("Registering all recipes");
			
			LOGGER.info("Registering MillingRecipe");
			event.getRegistry().register(NEMMachineRecipes.MILLING = new MillingRecipeSerializer<>(MillingRecipe::new, 200));
			
			LOGGER.info("Recipes registered");
		}
	}
}







