package com.kilroy790.notenoughmachines;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kilroy790.notenoughmachines.client.gui.screen.FilterScreen;
import com.kilroy790.notenoughmachines.client.gui.screen.MillstoneScreen;
import com.kilroy790.notenoughmachines.client.gui.screen.TripHammerScreen;
import com.kilroy790.notenoughmachines.client.renderers.tiles.AxleRenderer;
import com.kilroy790.notenoughmachines.client.renderers.tiles.GearboxRenderer;
import com.kilroy790.notenoughmachines.client.renderers.tiles.LargeCogwheelRenderer;
import com.kilroy790.notenoughmachines.client.renderers.tiles.MillstoneRenderer;
import com.kilroy790.notenoughmachines.client.renderers.tiles.SmallCogRenderer;
import com.kilroy790.notenoughmachines.client.renderers.tiles.SmallWindWheelRenderer;
import com.kilroy790.notenoughmachines.client.renderers.tiles.TripHammerRenderer;
import com.kilroy790.notenoughmachines.client.renderers.tiles.TubWheelRenderer;
import com.kilroy790.notenoughmachines.power.PowerNetworkStack;
import com.kilroy790.notenoughmachines.recipes.MillingRecipeSerializer;
import com.kilroy790.notenoughmachines.recipes.MillingRecipe;
import com.kilroy790.notenoughmachines.setup.ModSetup;
import com.kilroy790.notenoughmachines.setup.NEMBlocks;
import com.kilroy790.notenoughmachines.setup.NEMContainers;
import com.kilroy790.notenoughmachines.setup.NEMItems;
import com.kilroy790.notenoughmachines.setup.NEMMachineRecipes;
import com.kilroy790.notenoughmachines.setup.NEMRecipes;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.setup.lootmodifiers.GrassLootModifier;
import com.kilroy790.notenoughmachines.utilities.ClientTimer;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;




@Mod("notenoughtmachines")
@Mod.EventBusSubscriber(modid = NotEnoughMachines.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NotEnoughMachines 
{	
	public static NotEnoughMachines INSTANCE;
	public static final String MODID = "notenoughtmachines";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	public static PowerNetworkStack AETHER = new PowerNetworkStack();
	public static ClientTimer CLIENTTIMER = new ClientTimer();
	
	public static ModSetup setup = new ModSetup();
	
	public NotEnoughMachines() 
	{
		INSTANCE = this;
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::clientRegistries);
		MinecraftForge.EVENT_BUS.register(this);
		NEMBlocks.registerAll(modEventBus);
		NEMItems.registerAll(modEventBus);
		NEMTiles.registerAll(modEventBus);
		NEMContainers.registerAll(modEventBus);
		NEMRecipes.registerAll(modEventBus);
	}
	
	
	
	private void setup(final FMLCommonSetupEvent event) 
	{
		LOGGER.info("Starting NEM preinit");
		setup.init();
		LOGGER.info("NEM preinit done");
	}

	
	
	private void clientRegistries(final FMLClientSetupEvent event) 
	{
		LOGGER.info("registering NEM Client side entries");
		
		LOGGER.info("Setting NEM Block render types");
		RenderTypeLookup.setRenderLayer(NEMBlocks.FLAXPLANT.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(NEMBlocks.ANDGATE.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(NEMBlocks.ORGATE.get(), RenderType.getCutout());
		LOGGER.info("All NEM Block render types set");
		
		LOGGER.info("Registering all NEM Tile Entity Renderers");
		AxleRenderer.register();
		SmallCogRenderer.register();
		LargeCogwheelRenderer.register();
		GearboxRenderer.register();
		MillstoneRenderer.register();
		TubWheelRenderer.register();
		SmallWindWheelRenderer.register();
		TripHammerRenderer.register();
		LOGGER.info("All NEM Tile Entity Renderers registered");
		
		LOGGER.info("Registering all NEM Containers");
		ScreenManager.registerFactory(NEMContainers.MILLSTONE.get(), MillstoneScreen::new);
		ScreenManager.registerFactory(NEMContainers.TRIPHAMMER.get(), TripHammerScreen::new);
		ScreenManager.registerFactory(NEMContainers.FILTER.get(), FilterScreen::new);
		LOGGER.info("All NEM Containers registered");
		
		LOGGER.info("All NEM Client side entries registered");
	}
	
	
	
	@SubscribeEvent
	public static void worldGenEvent(FMLLoadCompleteEvent event) 
	{
		for (Biome biome : ForgeRegistries.BIOMES) 
		{
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(
					OreFeatureConfig.FillerBlockType.NATURAL_STONE, NEMBlocks.COPPERORE.get().getDefaultState(), 12))
					.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 32, 0, 196))));
			
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(
					OreFeatureConfig.FillerBlockType.NATURAL_STONE, NEMBlocks.FLUXSTONE.get().getDefaultState(), 33))
					.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(10, 0, 0, 196))));
		}
	}
	
	
	
	@SubscribeEvent
	public static void registerGlobalLootModifiers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event)
	{
		event.getRegistry().register(new GrassLootModifier.Serializer().setRegistryName(new ResourceLocation(MODID, "grass")));
	}
	
	
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class registryEvents 
	{			
		//Register recipes
		@SubscribeEvent
		public static void registerRecipes(final RegistryEvent.Register<IRecipeSerializer<?>> event) 
		{
			LOGGER.info("Registering all recipes");
			
			LOGGER.info("Registering MillingRecipe");
			event.getRegistry().register(NEMMachineRecipes.MILLING = new MillingRecipeSerializer<>(MillingRecipe::new, 200));
			
			LOGGER.info("Recipes registered");
		}
	}
}







