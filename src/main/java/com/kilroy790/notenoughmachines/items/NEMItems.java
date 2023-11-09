package com.kilroy790.notenoughmachines.items;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.NEMBlocks;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;




public class NEMItems 
{
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NotEnoughMachines.MODID);
	
	
	
	//Building Blocks - full
	public static final RegistryObject<Item> COPPER_ORE = ITEMS.register("copper_ore", 
			()-> new BlockItem(NEMBlocks.COPPERORE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> FLUXSTONE = ITEMS.register("fluxstone", 
			()-> new BlockItem(NEMBlocks.FLUXSTONE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> POLISHED_FLUXSTONE = ITEMS.register("polished_fluxstone", 
			()-> new BlockItem(NEMBlocks.POLISHED_FLUXSTONE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> LINENBLOCK = ITEMS.register("linenblock", 
			()-> new BlockItem(NEMBlocks.LINENBLOCK.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> WOODEN_FRAME = ITEMS.register("wooden_frame", 
			()-> new BlockItem(NEMBlocks.WOODEN_FRAME.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> IRON_PLATE_BLOCK = ITEMS.register("iron_plate_block", 
			()-> new BlockItem(NEMBlocks.IRON_PLATE_BLOCK.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> COPPER_PLATE_BLOCK = ITEMS.register("copper_plate_block", 
			()-> new BlockItem(NEMBlocks.COPPER_PLATE_BLOCK.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	//Building Blocks - slabs
	public static final RegistryObject<Item> FLUXSTONE_SLAB = ITEMS.register("fluxstone_slab", 
			()-> new BlockItem(NEMBlocks.FLUXSTONE_SLAB.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> POLISHED_FLUXSTONE_SLAB = ITEMS.register("polished_fluxstone_slab", 
			()-> new BlockItem(NEMBlocks.POLISHED_FLUXSTONE_SLAB.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> CUT_PANLE = ITEMS.register("cut_panle", 
			()-> new BlockItem(NEMBlocks.CUT_PANLE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	//Building Blocks - stairs
	public static final RegistryObject<Item> FLUXSTONE_STAIRS = ITEMS.register("fluxstone_stairs", 
			()-> new BlockItem(NEMBlocks.FLUXSTONE_STAIRS.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> POLISHED_FLUXSTONE_STAIRS = ITEMS.register("polished_fluxstone_stairs", 
			()-> new BlockItem(NEMBlocks.POLISHED_FLUXSTONE_STAIRS.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	//Building Blocks - walls/fences
	public static final RegistryObject<Item> FLUXSTONE_WALL = ITEMS.register("fluxstone_wall", 
			()-> new BlockItem(NEMBlocks.FLUXSTONE_WALL.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> POLISHED_FLUXSTONE_WALL = ITEMS.register("polished_fluxstone_wall", 
			()-> new BlockItem(NEMBlocks.POLISHED_FLUXSTONE_WALL.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	//Miscellaneous - dust items
	public static final RegistryObject<Item> CRUSHED_IRON_ORE = ITEMS.register("crushedironore", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> CRUSHED_GOLD_ORE = ITEMS.register("crushedgoldore", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> CRUSHED_COPPER_ORE = ITEMS.register("crushed_copper_ore", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> FLUX = ITEMS.register("flux", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> FLOUR = ITEMS.register("flour", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	//Miscellaneous - ingot items
	public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	//Miscellaneous - crafting items	
	public static final RegistryObject<FlaxSeedItem> FLAXSEED = ITEMS.register("flaxseed", 
			()-> new FlaxSeedItem( new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> FLAX = ITEMS.register("flax", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> FLAXSTRING = ITEMS.register("flaxstring", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> LINEN = ITEMS.register("linen", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<LinseedOilItem> LINSEEDOIL = ITEMS.register("linseed_oil", 
			()-> new LinseedOilItem(new Item.Properties()
					.containerItem(Items.GLASS_BOTTLE)
					.group(NEMItemGroup.NEM_ITEMGROUP)
					.food(new Food.Builder().hunger(2).saturation(0.3f).build())
					.maxStackSize(32)));
	
	
	
	public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("ironplate", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> COPPER_PLATE = ITEMS.register("copper_plate", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> IRON_ROD = ITEMS.register("iron_rod", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> COPPER_ROD = ITEMS.register("copper_rod", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> IRON_SCREW = ITEMS.register("iron_screw", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> GEAR = ITEMS.register("gear", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> WINDBLADE = ITEMS.register("windblade", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> WINDSAIL_ITEM = ITEMS.register("windsail_item", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> HAMMER_AND_ANVIL = ITEMS.register("hammer_and_anvil", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> ROLLERS = ITEMS.register("rollers", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> REDSTONE_COLLECTOR = ITEMS.register("redstone_collector", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> REDSTONE_EMITTER = ITEMS.register("redstone_emitter", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	//Machine Blocks - power transfer
	public static final RegistryObject<Item> AXLE = ITEMS.register("axle", 
			()-> new BlockItem(NEMBlocks.AXLE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> ENCLOSED_AXLE = ITEMS.register("enclosedaxle", 
			()-> new BlockItem(NEMBlocks.ENCLOSED_AXLE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> SMALLCOG = ITEMS.register("smallcog", 
			()-> new BlockItem(NEMBlocks.SMALLCOG.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> LARGECOG = ITEMS.register("largecog", 
			()-> new BlockItem(NEMBlocks.LARGECOG.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> GEARBOX = ITEMS.register("gearbox", 
			()-> new BlockItem(NEMBlocks.GEARBOX.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	//Machine Blocks - power generators
	public static final RegistryObject<Item> CREATIVEPOWERBOX = ITEMS.register("creativepowerbox", 
			()-> new BlockItem(NEMBlocks.CREATIVEPOWERBOX.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> TUBWHEEL = ITEMS.register("tubwheel", 
			()-> new BlockItem(NEMBlocks.TUBWHEEL.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> SMALLWINDWHEEL = ITEMS.register("smallwindwheel", 
			()-> new BlockItem(NEMBlocks.SMALLWINDWHEEL.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	//Machine Blocks - processors 
	public static final RegistryObject<Item> MILLSTONE = ITEMS.register("millstone", 
			()-> new BlockItem(NEMBlocks.MILLSTONE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> ROLLING_MILL = ITEMS.register("rollingmill", 
			()-> new BlockItem(NEMBlocks.ROLLING_MILL.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> TRIPHAMMER = ITEMS.register("triphammer", 
			()-> new BlockItem(NEMBlocks.TRIPHAMMER.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	//Machine Blocks - item transfer
	public static final RegistryObject<Item> CHUTE = ITEMS.register("chute", 
			()-> new BlockItem(NEMBlocks.CHUTE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> FILTER = ITEMS.register("filter", 
			()-> new BlockItem(NEMBlocks.FILTER.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> ITEMPUSHER = ITEMS.register("itempusher", 
			()-> new BlockItem(NEMBlocks.ITEMPUSHER.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	//Redstone Blocks
	public static final RegistryObject<Item> FLUXSTONE_BUTTON = ITEMS.register("fluxstone_button", 
			()-> new BlockItem(NEMBlocks.FLUXSTONE_BUTTON.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> FLUXSTONE_PRESSURE_PLATE = ITEMS.register("fluxstone_pressure_plate", 
			()-> new BlockItem(NEMBlocks.FLUXSTONE_PRESSURE_PLATE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> POLISHED_FLUXSTONE_BUTTON = ITEMS.register("polished_fluxstone_button", 
			()-> new BlockItem(NEMBlocks.POLISHED_FLUXSTONE_BUTTON.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> POLISHED_FLUXSTONE_PRESSURE_PLATE = ITEMS.register("polished_fluxstone_pressure_plate", 
			()-> new BlockItem(NEMBlocks.POLISHED_FLUXSTONE_PRESSURE_PLATE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> ORGATE = ITEMS.register("orgate", 
			()-> new BlockItem(NEMBlocks.ORGATE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	
	
	public static final RegistryObject<Item> ANDGATE = ITEMS.register("andgate", 
			()-> new BlockItem(NEMBlocks.ANDGATE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));

	

	public static void registerAll(IEventBus modEventBus) 
	{
		ITEMS.register(modEventBus);
	}
}



