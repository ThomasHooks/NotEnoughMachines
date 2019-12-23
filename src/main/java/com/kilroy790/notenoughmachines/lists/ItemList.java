package com.kilroy790.notenoughmachines.lists;

import com.kilroy790.notenoughmachines.items.FlaxSeedItem;

import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ItemList {

	//Crafting Items
	@ObjectHolder("notenoughtmachines:linen")
	public static Item LINEN;
	
	@ObjectHolder("notenoughtmachines:flaxstring")
	public static Item FLAXSTRING;
	
	@ObjectHolder("notenoughtmachines:flax")
	public static Item FLAX;
	
	@ObjectHolder("notenoughtmachines:gear")
	public static Item GEAR;
	
	//Seed Items
	@ObjectHolder("notenoughtmachines:flaxseed")
	public static FlaxSeedItem FLAXSEED;
	
	//Item Blocks
	public static Item LINENBLOCK;
}
