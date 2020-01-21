package com.kilroy790.notenoughmachines.api.lists;

import com.kilroy790.notenoughmachines.items.FlaxSeedItem;

import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;




public class ItemList {

	//Crafting Items
	@ObjectHolder("notenoughtmachines:linen")
	public static Item LINEN;
	
	@ObjectHolder("notenoughtmachines:flaxstring")
	public static Item FLAXSTRING;
	
	@ObjectHolder("notenoughtmachines:flour")
	public static Item FLOUR;
	
	@ObjectHolder("notenoughtmachines:flax")
	public static Item FLAX;
	
	@ObjectHolder("notenoughtmachines:gear")
	public static Item GEAR;
	
	@ObjectHolder("notenoughtmachines:windblade")
	public static Item WINDBLADE;
	
	@ObjectHolder("notenoughtmachines:windsail_item")
	public static Item WINDSAIL_ITEM;
	
	@ObjectHolder("notenoughtmachines:redstone_collector")
	public static Item REDSTONE_COLLECTOR;
	
	@ObjectHolder("notenoughtmachines:redstone_emitter")
	public static Item REDSTONE_EMITTER;
	
	//Seed Items
	@ObjectHolder("notenoughtmachines:flaxseed")
	public static FlaxSeedItem FLAXSEED;
	
	//Item Blocks
	public static Item LINENBLOCK;
}
