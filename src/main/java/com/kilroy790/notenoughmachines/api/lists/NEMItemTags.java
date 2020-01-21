package com.kilroy790.notenoughmachines.api.lists;

import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class NEMItemTags {

	
	public static final Tag<Item> STRIPPED_LOG = makeWrapperTag("stripped_logs");
	
	
	private static Tag<Item> makeWrapperTag(String p_199901_0_) {
		return new ItemTags.Wrapper(new ResourceLocation(p_199901_0_));
	}
}
