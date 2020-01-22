package com.kilroy790.notenoughmachines;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kilroy790.notenoughmachines.api.lists.BlockList;
import com.kilroy790.notenoughmachines.api.lists.ItemGroupList;
import com.kilroy790.notenoughmachines.api.lists.ItemList;
import com.kilroy790.notenoughmachines.api.lists.MachineRecipeList;
import com.kilroy790.notenoughmachines.blocks.building.LinenBlock;
import com.kilroy790.notenoughmachines.blocks.crops.FlaxPlantBlock;
import com.kilroy790.notenoughmachines.blocks.logicgates.ANDGateBlock;
import com.kilroy790.notenoughmachines.blocks.machines.AxleBlock;
import com.kilroy790.notenoughmachines.blocks.machines.ChuteBlock;
import com.kilroy790.notenoughmachines.blocks.machines.CreativePowerBoxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.GearboxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.MillstoneBlock;
import com.kilroy790.notenoughmachines.blocks.machines.SmallWindWheelBlock;
import com.kilroy790.notenoughmachines.client.renderers.SmallWindWheelRenderers;
import com.kilroy790.notenoughmachines.containers.MillstoneContainer;
import com.kilroy790.notenoughmachines.items.FlaxSeedItem;
import com.kilroy790.notenoughmachines.recipes.MillingRecipeSerializer;
import com.kilroy790.notenoughmachines.recipes.MillingRecipe;
import com.kilroy790.notenoughmachines.setup.ModSetup;
import com.kilroy790.notenoughmachines.tiles.machines.AxleTile;
import com.kilroy790.notenoughmachines.tiles.machines.ChuteTile;
import com.kilroy790.notenoughmachines.tiles.machines.CreativePowerBoxTile;
import com.kilroy790.notenoughmachines.tiles.machines.GearboxTile;
import com.kilroy790.notenoughmachines.tiles.machines.MillstoneTile;
import com.kilroy790.notenoughmachines.tiles.machines.SmallWindWheelTile;
import com.kilroy790.notenoughmachines.utilities.ClientProxy;
import com.kilroy790.notenoughmachines.utilities.IProxy;
import com.kilroy790.notenoughmachines.utilities.ServerProxy;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("notenoughtmachines")
public class NotEnoughMachines {
	
	public static NotEnoughMachines instance;
	
	public static final String modid = "notenoughtmachines";
	
	public static final Logger logger = LogManager.getLogger(modid);
	
	public static IProxy proxy = DistExecutor.runForDist(()-> ()-> new ClientProxy(), ()-> ()-> new ServerProxy());
	
	public static ModSetup setup = new ModSetup();
	
	
	public NotEnoughMachines() {
		
		instance = this;
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		
		// Register the clientRegistries method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	
	private void setup(final FMLCommonSetupEvent event) {
		// some preinit code
		setup.init();
		proxy.init();
		
		logger.info("Preinit registered");
	}

	
	private void clientRegistries(final FMLClientSetupEvent event) {
		// do something that can only be done on the client
		
		logger.info("Registering SmallWindWheelTESR");
		ClientRegistry.bindTileEntitySpecialRenderer(SmallWindWheelTile.class, new SmallWindWheelRenderers());
		
		logger.info("clientRegistries registered");
	}
	
	
	// this is subscribing to the MOD Event bus for receiving Registry Events
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class registryEvents {
		
		
		//Register Blocks
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			logger.info("Registering all blocks");
			
			//Building Blocks
			logger.info("Registering LinenBlock");
			event.getRegistry().register(BlockList.LINENBLOCK = new LinenBlock());
			
			//Machine Blocks
			logger.info("Registering CreativePowerBoxBlock");
			event.getRegistry().register(BlockList.CREATIVEPOWERBOX = new CreativePowerBoxBlock("creativepowerbox"));
			
			logger.info("Registering SmallWindWheelBlock");
			event.getRegistry().register(BlockList.SMALLWINDWHEEL = new SmallWindWheelBlock("smallwindwheel"));
			
			logger.info("Registering GearboxBlock");
			event.getRegistry().register(BlockList.GEARBOX = new GearboxBlock("gearbox"));
			
			logger.info("Registering AxleBlock");
			event.getRegistry().register(BlockList.AXLE = new AxleBlock("axle"));
			
			logger.info("Registering MillstoneBlock");
			event.getRegistry().register(BlockList.MILLSTONE = new MillstoneBlock("millstone"));
			
			//Logic Gate Blocks
			logger.info("Registering ANDGateBlock");
			event.getRegistry().register(BlockList.ANDGATE = new ANDGateBlock(Block.Properties
					.create(Material.MISCELLANEOUS)
					.hardnessAndResistance(0.0f)
					.sound(SoundType.WOOD), "andgate"));
			
			//Transport Blocks
			logger.info("Registering ChuteBlock");
			event.getRegistry().register(BlockList.CHUTE = new ChuteBlock(Block.Properties
					.create(Material.MISCELLANEOUS)
					.hardnessAndResistance(1.8f, 2.0f)
					.sound(SoundType.WOOD)
					.harvestTool(ToolType.AXE)
					.harvestLevel(0), "chute"));
			
			//Crops Blocks
			logger.info("Registering FlaxPlantBlock");
			event.getRegistry().register(BlockList.FLAXPLANT = new FlaxPlantBlock("flaxplant"));
			
			logger.info("All blocks registered");
		}
		
		
		//Register Items
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			logger.info("Registering all items");
			
			//Crafting Items
			logger.info("Registering LinenItem");
			event.getRegistry().register(ItemList.LINEN = new Item(new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName("linen"));
			
			logger.info("Registering FlaxItem");
			event.getRegistry().register(ItemList.FLAX = new Item(new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName("flax"));
			
			logger.info("Registering FlourItem");
			event.getRegistry().register(ItemList.FLOUR = new Item(new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName("flour"));
			
			logger.info("Registering FlaxStringItem");
			event.getRegistry().register(ItemList.FLAXSTRING = new Item(new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName("flaxstring"));
			
			logger.info("Registering WindBladeItem");
			event.getRegistry().register(ItemList.WINDBLADE = new Item(new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName("windblade"));
			
			logger.info("Registering WindSailItem");
			event.getRegistry().register(ItemList.WINDSAIL_ITEM = new Item(new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName("windsail_item"));
			
			logger.info("Registering GearItem");
			event.getRegistry().register(ItemList.GEAR = new Item(new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName("gear"));
			
			logger.info("Registering RedstoneCollectorItem");
			event.getRegistry().register(ItemList.REDSTONE_COLLECTOR = new Item(new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName("redstone_collector"));
			
			logger.info("Registering RedstoneEmitterItem");
			event.getRegistry().register(ItemList.REDSTONE_EMITTER = new Item(new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName("redstone_emitter"));
			
			//Seed Items
			logger.info("Registering FlaxSeedItem");
			event.getRegistry().register(ItemList.FLAXSEED = new FlaxSeedItem(new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP), "flaxseed"));
			
			//Block Items
			logger.info("Registering LinenBlockItem");
			event.getRegistry().register(new BlockItem(BlockList.LINENBLOCK, new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName(BlockList.LINENBLOCK.getRegistryName()));
			
			logger.info("Registering CreativePowerBoxBlockItem");
			event.getRegistry().register(new BlockItem(BlockList.CREATIVEPOWERBOX, new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName(BlockList.CREATIVEPOWERBOX.getRegistryName()));
			
			logger.info("Registering SmallWindWheelBlockItem");
			event.getRegistry().register(new BlockItem(BlockList.SMALLWINDWHEEL, new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName(BlockList.SMALLWINDWHEEL.getRegistryName()));
			
			logger.info("Registering GearboxBlockItem");
			event.getRegistry().register(new BlockItem(BlockList.GEARBOX, new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName(BlockList.GEARBOX.getRegistryName()));
			
			logger.info("Registering AxleBlockItem");
			event.getRegistry().register(new BlockItem(BlockList.AXLE, new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName(BlockList.AXLE.getRegistryName()));
			
			logger.info("Registering MillstoneBlockItem");
			event.getRegistry().register(new BlockItem(BlockList.MILLSTONE, new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName(BlockList.MILLSTONE.getRegistryName()));
			
			logger.info("Registering ANDGateBlockItem");
			event.getRegistry().register(new BlockItem(BlockList.ANDGATE, new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName(BlockList.ANDGATE.getRegistryName()));
			
			logger.info("Registering ChuteBlockItem");
			event.getRegistry().register(new BlockItem(BlockList.CHUTE, new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName(BlockList.CHUTE.getRegistryName()));
			
			logger.info("Items registered");
		} 
		
		
		//Register Tile Entities
		@SubscribeEvent
		public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
			logger.info("Registering all tile entities");
			
			logger.info("Registering CreativePowerBoxTileEntity");
			event.getRegistry().register(TileEntityType.Builder.create(CreativePowerBoxTile::new, BlockList.CREATIVEPOWERBOX).build(null).setRegistryName("creativepowerbox"));
			
			logger.info("Registering SmallWindWheelTileEntity");
			event.getRegistry().register(TileEntityType.Builder.create(SmallWindWheelTile::new, BlockList.SMALLWINDWHEEL).build(null).setRegistryName("smallwindwheel"));
			
			logger.info("Registering MillstoneTileEntity");
			event.getRegistry().register(TileEntityType.Builder.create(MillstoneTile::new, BlockList.MILLSTONE).build(null).setRegistryName("millstone"));
			
			logger.info("Registering AxelTileEntity");
			event.getRegistry().register(TileEntityType.Builder.create(AxleTile::new, BlockList.AXLE).build(null).setRegistryName("axle"));
			
			logger.info("Registering GearboxTileEntity");
			event.getRegistry().register(TileEntityType.Builder.create(GearboxTile::new, BlockList.GEARBOX).build(null).setRegistryName("gearbox"));
			
			logger.info("Registering ChuteTileEntity");
			event.getRegistry().register(TileEntityType.Builder.create(ChuteTile::new, BlockList.CHUTE).build(null).setRegistryName("chute"));
			
			logger.info("Tile entities registered");
		}
		
		
		//Register Containers
		@SubscribeEvent
		public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
			logger.info("Registering all containers");
			
			logger.info("Registering MillstoneContainer");
			event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new MillstoneContainer(windowId, NotEnoughMachines.proxy.getClientWorld(), pos, inv, NotEnoughMachines.proxy.getClientPlayer());
            }).setRegistryName("millstone"));
			
			logger.info("Containers registered");
		}
		
		
		//Register recipes
		@SubscribeEvent
		public static void registerRecipes(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
			logger.info("Registering all recipes");
			
			logger.info("Registering MillingRecipe");
			event.getRegistry().register(MachineRecipeList.MILLING = new MillingRecipeSerializer<>(MillingRecipe::new, 200));
			
			logger.info("Recipes registered");
		}
	}
}







