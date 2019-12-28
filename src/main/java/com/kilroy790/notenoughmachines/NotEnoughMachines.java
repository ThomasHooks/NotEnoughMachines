package com.kilroy790.notenoughmachines;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kilroy790.notenoughmachines.api.crafting.MachineRecipeSerializer;
import com.kilroy790.notenoughmachines.api.crafting.MillingRecipe;
import com.kilroy790.notenoughmachines.blocks.FlaxPlantBlock;
import com.kilroy790.notenoughmachines.blocks.LinenBlock;
import com.kilroy790.notenoughmachines.blocks.machines.AxelBlock;
import com.kilroy790.notenoughmachines.blocks.machines.CreativePowerBoxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.GearBoxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.MillstoneBlock;
import com.kilroy790.notenoughmachines.gui.MillstoneContainer;
import com.kilroy790.notenoughmachines.items.FlaxSeedItem;
import com.kilroy790.notenoughmachines.lists.BlockList;
import com.kilroy790.notenoughmachines.lists.ItemGroupList;
import com.kilroy790.notenoughmachines.lists.ItemList;
import com.kilroy790.notenoughmachines.lists.MachineRecipeList;
import com.kilroy790.notenoughmachines.setup.ClientProxy;
import com.kilroy790.notenoughmachines.setup.IProxy;
import com.kilroy790.notenoughmachines.setup.ModSetup;
import com.kilroy790.notenoughmachines.setup.ServerProxy;
import com.kilroy790.notenoughmachines.tiles.AxelTile;
import com.kilroy790.notenoughmachines.tiles.CreativePowerBoxTile;
import com.kilroy790.notenoughmachines.tiles.MillstoneTile;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("notenoughtmachines")
public class NotEnoughMachines {
	
	public static NotEnoughMachines instance;
	
	public static final String modid = "notenoughtmachines";
	
	private static final Logger logger = LogManager.getLogger(modid);
	
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
		logger.info("clientRegistries registered");
	}
	
	
	// this is subscribing to the MOD Event bus for receiving Registry Events
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class registryEvents {
		
		
		//Register Blocks
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			logger.info("Registering all blocks");
			
			//Storage Blocks
			logger.info("Registering LinenBlock");
			event.getRegistry().register(BlockList.LINENBLOCK = new LinenBlock());
			
			//Machine Blocks
			logger.info("Registering CreativePowerBoxBlock");
			event.getRegistry().register(BlockList.CREATIVEPOWERBOX = new CreativePowerBoxBlock("creativepowerbox"));
			
			logger.info("Registering GearboxBlock");
			event.getRegistry().register(BlockList.GEARBOX = new GearBoxBlock("gearbox"));
			
			logger.info("Registering AxelBlock");
			event.getRegistry().register(BlockList.AXEL = new AxelBlock("axel"));
			
			logger.info("Registering MillstoneBlock");
			event.getRegistry().register(BlockList.MILLSTONE = new MillstoneBlock("millstone"));
			
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
			
			logger.info("Registering FlaxStringItem");
			event.getRegistry().register(ItemList.FLAXSTRING = new Item(new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName("flaxstring"));
			
			logger.info("Registering GearItem");
			event.getRegistry().register(ItemList.GEAR = new Item(new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName("gear"));
			
			//Seed Items
			logger.info("Registering FlaxSeedItem");
			event.getRegistry().register(ItemList.FLAXSEED = new FlaxSeedItem(new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP), "flaxseed"));
			
			//Block Items
			logger.info("Registering LinenBlockItem");
			event.getRegistry().register(new BlockItem(BlockList.LINENBLOCK, new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName(BlockList.LINENBLOCK.getRegistryName()));
			
			logger.info("Registering CreativePowerBoxBlock");
			event.getRegistry().register(new BlockItem(BlockList.CREATIVEPOWERBOX, new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName(BlockList.CREATIVEPOWERBOX.getRegistryName()));
			
			logger.info("Registering GearboxBlockItem");
			event.getRegistry().register(new BlockItem(BlockList.GEARBOX, new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName(BlockList.GEARBOX.getRegistryName()));
			
			logger.info("Registering AxelBlockItem");
			event.getRegistry().register(new BlockItem(BlockList.AXEL, new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName(BlockList.AXEL.getRegistryName()));
			
			logger.info("Registering MillstoneBlockItem");
			event.getRegistry().register(new BlockItem(BlockList.MILLSTONE, new Item.Properties().group(ItemGroupList.NEM_ITEMGROUP)).setRegistryName(BlockList.MILLSTONE.getRegistryName()));
			
			logger.info("Items registered");
		} 
		
		
		//Register Tile Entities
		@SubscribeEvent
		public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
			logger.info("Registering all tile entities");
			
			logger.info("Registering CreativePowerBoxTileEntity");
			event.getRegistry().register(TileEntityType.Builder.create(CreativePowerBoxTile::new, BlockList.CREATIVEPOWERBOX).build(null).setRegistryName("creativepowerbox"));
			
			logger.info("Registering MillstoneTileEntity");
			event.getRegistry().register(TileEntityType.Builder.create(MillstoneTile::new, BlockList.MILLSTONE).build(null).setRegistryName("millstone"));
			
			logger.info("Registering AxelTileEntity");
			event.getRegistry().register(TileEntityType.Builder.create(AxelTile::new, BlockList.AXEL).build(null).setRegistryName("axel"));
			
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
			event.getRegistry().register(MachineRecipeList.MILLING = new MachineRecipeSerializer<>(MillingRecipe::new, 200));
			
			logger.info("Recipes registered");
		}
	}
}







