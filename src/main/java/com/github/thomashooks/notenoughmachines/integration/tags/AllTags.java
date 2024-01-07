package com.github.thomashooks.notenoughmachines.integration.tags;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AllTags
{
    public static final class Items
    {
        public static final TagKey<Item> BRONZE_DUST = makeForgeTag("dusts/bronze");
        public static final TagKey<Item> BRONZE_INGOTS = makeForgeTag("ingots/bronze");
        public static final TagKey<Item> BRONZE_PLATES = makeForgeTag("plates/bronze");
        public static final TagKey<Item> BRONZE_RODS = makeForgeTag("rods/bronze");
        public static final TagKey<Item> COPPER_DUST = makeForgeTag("dusts/copper");
        public static final TagKey<Item> COPPER_PLATES = makeForgeTag("plates/copper");
        public static final TagKey<Item> COPPER_PLATE_BLOCKS = makeTag("copper_plate_blocks");
        public static final TagKey<Item> COPPER_PLATE_SLABS = makeTag("copper_plate_slabs");
        public static final TagKey<Item> COPPER_PLATE_STAIRS = makeTag("copper_plate_stairs");
        public static final TagKey<Item> COPPER_RODS = makeForgeTag("rods/copper");
        public static final TagKey<Item> GOLD_DUST = makeForgeTag("dusts/gold");
        public static final TagKey<Item> GOLD_PLATES = makeForgeTag("plates/gold");
        public static final TagKey<Item> GOLD_RODS = makeForgeTag("rods/gold");
        public static final TagKey<Item> IRON_DUST = makeForgeTag("dusts/iron");
        public static final TagKey<Item> IRON_PLATES = makeForgeTag("plates/iron");
        public static final TagKey<Item> IRON_RODS = makeForgeTag("rods/iron");
        public static final TagKey<Item> LINEN_BLOCKS = makeTag("linen_blocks");
        public static final TagKey<Item> STRIPPED_LOGS = makeTag("stripped_logs");
        public static final TagKey<Item> TIN_DUST = makeForgeTag("dusts/tin");
        public static final TagKey<Item> TIN_INGOTS = makeForgeTag("ingots/tin");
        public static final TagKey<Item> TIN_ORES = makeForgeTag("ores/tin");
        public static final TagKey<Item> TIN_PLATES = makeForgeTag("plates/tin");
        public static final TagKey<Item> TIN_RODS = makeForgeTag("rods/tin");


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

        public static final TagKey<Block> COPPER_PLATE_BLOCKS = makeTag("copper_plate_blocks");
        public static final TagKey<Block> COPPER_PLATE_SLABS = makeTag("copper_plate_slabs");
        public static final TagKey<Block> COPPER_PLATE_STAIRS = makeTag("copper_plate_stairs");
        public static final TagKey<Block> STRIPPED_LOGS = makeTag("stripped_logs");
        public static final TagKey<Block> LINEN_BLOCKS = makeTag("linen_blocks");
        public static final TagKey<Block> ORES_IN_DEEPSLATE = makeForgeTag("ores_in_ground/deepslate");
        public static final TagKey<Block> ORES_IN_NETHERRACK = makeForgeTag("ores_in_ground/netherrack");
        public static final TagKey<Block> ORES_IN_STONE = makeForgeTag("ores_in_ground/stone");

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
