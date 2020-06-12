package com.kilroy790.notenoughmachines.setup;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.items.FlaxSeedItem;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;




public class NEMItems {

	private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, NotEnoughMachines.MODID);

	public static final RegistryObject<FlaxSeedItem> FLAXSEED = ITEMS.register("flaxseed", 
			()-> new FlaxSeedItem( new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> FLAX = ITEMS.register("flax", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> FLAXSTRING = ITEMS.register("flaxstring", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> LINEN = ITEMS.register("linen", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> LINENBLOCK = ITEMS.register("linenblock", 
			()-> new BlockItem(NEMBlocks.LINENBLOCK.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> FLOUR = ITEMS.register("flour", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> GEAR = ITEMS.register("gear", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> CREATIVEPOWERBOX = ITEMS.register("creativepowerbox", 
			()-> new BlockItem(NEMBlocks.CREATIVEPOWERBOX.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> WINDBLADE = ITEMS.register("windblade", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> WINDSAIL_ITEM = ITEMS.register("windsail_item", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> SMALLWINDWHEEL = ITEMS.register("smallwindwheel", 
			()-> new BlockItem(NEMBlocks.SMALLWINDWHEEL.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> TUBWHEEL = ITEMS.register("tubwheel", 
			()-> new BlockItem(NEMBlocks.TUBWHEEL.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> AXLE = ITEMS.register("axle", 
			()-> new BlockItem(NEMBlocks.AXLE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> GEARBOX = ITEMS.register("gearbox", 
			()-> new BlockItem(NEMBlocks.GEARBOX.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> MILLSTONE = ITEMS.register("millstone", 
			()-> new BlockItem(NEMBlocks.MILLSTONE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> HAMMERHEAD = ITEMS.register("hammerhead", 
			()-> new BlockItem(NEMBlocks.HAMMERHEAD.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> CHUTE = ITEMS.register("chute", 
			()-> new BlockItem(NEMBlocks.CHUTE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> CLOSEDCHUTE = ITEMS.register("closedchute", 
			()-> new BlockItem(NEMBlocks.CLOSEDCHUTE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> ITEMPUSHER = ITEMS.register("itempusher", 
			()-> new BlockItem(NEMBlocks.ITEMPUSHER.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> FILTER = ITEMS.register("filter", 
			()-> new BlockItem(NEMBlocks.FILTER.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> REDSTONE_COLLECTOR = ITEMS.register("redstone_collector", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> REDSTONE_EMITTER = ITEMS.register("redstone_emitter", 
			()-> new Item(new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> ANDGATE = ITEMS.register("andgate", 
			()-> new BlockItem(NEMBlocks.ANDGATE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));
	
	public static final RegistryObject<Item> ORGATE = ITEMS.register("orgate", 
			()-> new BlockItem(NEMBlocks.ORGATE.get(), new Item.Properties().group(NEMItemGroup.NEM_ITEMGROUP)));



	public static void registerAll(IEventBus modEventBus) {
		ITEMS.register(modEventBus);
	}
}







