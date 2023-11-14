package com.github.thomashooks.notenoughmachines.util;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class NEMTags
{
    public static final class Items
    {
        public static final TagKey<Item> STRIPPED_LOGS = makeTag("stripped_logs");
        public static final TagKey<Item> COBBLESTONE = makeTag("cobblestone");


        private static TagKey<Item> makeForgeTag(String path)
        {
            return ItemTags.create(new ResourceLocation("forge", path));
        }

        private static TagKey<Item> makeTag(String path)
        {
            return ItemTags.create(new ResourceLocation(NotEnoughMachines.MOD_ID, path));
        }
    }



    public static final class Blocks
    {
        public static final TagKey<Block> STRIPPED_LOGS = makeTag("stripped_logs");

        private static TagKey<Block> makeForgeTag(String path)
        {
            return BlockTags.create(new ResourceLocation("forge", path));
        }

        private static TagKey<Block> makeTag(String path)
        {
            return BlockTags.create(new ResourceLocation(NotEnoughMachines.MOD_ID, path));
        }
    }
}
