package com.kilroy790.notenoughmachines.data;

import com.kilroy790.notenoughmachines.NotEnoughMachines;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;




public class NEMTags 
{	
	public static final class Items
	{
		public static final ITag.INamedTag<Item> STRIPPED_LOGS = makeForgeTag("stripped_logs");
		public static final ITag.INamedTag<Item> COBBLESTONE = makeForgeTag("cobblestone");
		
		
		
		protected static ITag.INamedTag<Item> makeForgeTag(String path)
		{
			return ItemTags.makeWrapperTag(new ResourceLocation("forge", path).toString());
		}
		
		
		
		protected static ITag.INamedTag<Item> makeTag(String path)
		{
			return ItemTags.makeWrapperTag(new ResourceLocation(NotEnoughMachines.MODID, path).toString());
		}
	}
	
	
	
	public static final class Blocks
	{
		public static final ITag.INamedTag<Block> STRIPPED_LOGS = makeForgeTag("stripped_logs");
		
		
		
		protected static ITag.INamedTag<Block> makeForgeTag(String path)
		{
			return BlockTags.makeWrapperTag(new ResourceLocation("forge", path).toString());
		}
		
		
		
		protected static ITag.INamedTag<Block> makeTag(String path)
		{
			return BlockTags.makeWrapperTag(new ResourceLocation(NotEnoughMachines.MODID, path).toString());
		}
	}
}







