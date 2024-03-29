package com.github.thomashooks.notenoughmachines.data.tags;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.integration.tags.AllTags;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider
{

    public ItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> tagLookup, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(packOutput, lookupProvider, tagLookup, NotEnoughMachines.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider)
    {
        this.tag(AllTags.Items.STRIPPED_LOGS).add(
                Items.STRIPPED_OAK_LOG,
                Items.STRIPPED_SPRUCE_LOG,
                Items.STRIPPED_BIRCH_LOG,
                Items.STRIPPED_JUNGLE_LOG,
                Items.STRIPPED_ACACIA_LOG,
                Items.STRIPPED_CHERRY_LOG,
                Items.STRIPPED_DARK_OAK_LOG,
                Items.STRIPPED_MANGROVE_LOG,
                Items.STRIPPED_CRIMSON_STEM,
                Items.STRIPPED_WARPED_STEM,
                Items.STRIPPED_OAK_WOOD,
                Items.STRIPPED_SPRUCE_WOOD,
                Items.STRIPPED_BIRCH_WOOD,
                Items.STRIPPED_JUNGLE_WOOD,
                Items.STRIPPED_ACACIA_WOOD,
                Items.STRIPPED_CHERRY_WOOD,
                Items.STRIPPED_DARK_OAK_WOOD,
                Items.STRIPPED_MANGROVE_WOOD,
                Items.STRIPPED_CRIMSON_HYPHAE,
                Items.STRIPPED_WARPED_HYPHAE,
                Items.STRIPPED_BAMBOO_BLOCK
        );
        this.tag(AllTags.Items.LINEN_BLOCKS).add(
                AllItems.LINEN_BLOCK.get(),
                AllItems.LINEN_BLOCK_WHITE.get(),
                AllItems.LINEN_BLOCK_ORANGE.get(),
                AllItems.LINEN_BLOCK_MAGENTA.get(),
                AllItems.LINEN_BLOCK_LIGHT_BLUE.get(),
                AllItems.LINEN_BLOCK_YELLOW.get(),
                AllItems.LINEN_BLOCK_LIME.get(),
                AllItems.LINEN_BLOCK_PINK.get(),
                AllItems.LINEN_BLOCK_GRAY.get(),
                AllItems.LINEN_BLOCK_LIGHT_GRAY.get(),
                AllItems.LINEN_BLOCK_CYAN.get(),
                AllItems.LINEN_BLOCK_PURPLE.get(),
                AllItems.LINEN_BLOCK_BLUE.get(),
                AllItems.LINEN_BLOCK_BROWN.get(),
                AllItems.LINEN_BLOCK_GREEN.get(),
                AllItems.LINEN_BLOCK_RED.get(),
                AllItems.LINEN_BLOCK_BLACK.get()
        );
        this.tag(AllTags.Items.COPPER_PLATE_BLOCKS).add(
                AllItems.COPPER_PLATE_BLOCK.get(),
                AllItems.COPPER_PLATE_BLOCK_EXPOSED.get(),
                AllItems.COPPER_PLATE_BLOCK_WEATHERED.get(),
                AllItems.COPPER_PLATE_BLOCK_OXIDIZED.get(),
                AllItems.COPPER_PLATE_BLOCK_WAXED.get(),
                AllItems.COPPER_PLATE_BLOCK_EXPOSED_WAXED.get(),
                AllItems.COPPER_PLATE_BLOCK_WEATHERED_WAXED.get(),
                AllItems.COPPER_PLATE_BLOCK_OXIDIZED_WAXED.get()
        );
        this.tag(AllTags.Items.COPPER_PLATE_SLABS).add(
                AllItems.COPPER_PLATE_SLAB.get(),
                AllItems.COPPER_PLATE_SLAB_EXPOSED.get(),
                AllItems.COPPER_PLATE_SLAB_WEATHERED.get(),
                AllItems.COPPER_PLATE_SLAB_OXIDIZED.get(),
                AllItems.COPPER_PLATE_SLAB_WAXED.get(),
                AllItems.COPPER_PLATE_SLAB_EXPOSED_WAXED.get(),
                AllItems.COPPER_PLATE_SLAB_WEATHERED_WAXED.get(),
                AllItems.COPPER_PLATE_SLAB_OXIDIZED_WAXED.get()
        );
        this.tag(AllTags.Items.COPPER_PLATE_STAIRS).add(
                AllItems.COPPER_PLATE_STAIRS.get(),
                AllItems.COPPER_PLATE_STAIRS_EXPOSED.get(),
                AllItems.COPPER_PLATE_STAIRS_WEATHERED.get(),
                AllItems.COPPER_PLATE_STAIRS_OXIDIZED.get(),
                AllItems.COPPER_PLATE_STAIRS_WAXED.get(),
                AllItems.COPPER_PLATE_STAIRS_EXPOSED_WAXED.get(),
                AllItems.COPPER_PLATE_STAIRS_WEATHERED_WAXED.get(),
                AllItems.COPPER_PLATE_STAIRS_OXIDIZED_WAXED.get()
        );
        this.tag(AllTags.Items.BRONZE_DUST).add(
                AllItems.CRUSHED_BRONZE.get()
        );
        this.tag(AllTags.Items.BRONZE_INGOTS).add(
                AllItems.BRONZE_INGOT.get()
        );
        this.tag(AllTags.Items.BRONZE_PLATES).add(
                AllItems.BRONZE_PLATE.get()
        );
        this.tag(AllTags.Items.BRONZE_RODS).add(
                AllItems.BRONZE_ROD.get()
        );
        this.tag(AllTags.Items.COPPER_DUST).add(
                AllItems.CRUSHED_COPPER_ORE.get()
        );
        this.tag(AllTags.Items.COPPER_PLATES).add(
                AllItems.COPPER_PLATE.get()
        );
        this.tag(AllTags.Items.COPPER_RODS).add(
                AllItems.COPPER_ROD.get()
        );
        this.tag(AllTags.Items.GOLD_DUST).add(
                AllItems.CRUSHED_GOLD_ORE.get()
        );
        this.tag(AllTags.Items.GOLD_PLATES).add(
                AllItems.GOLD_PLATE.get()
        );
        this.tag(AllTags.Items.GOLD_RODS).add(
                AllItems.GOLD_ROD.get()
        );
        this.tag(AllTags.Items.IRON_DUST).add(
                AllItems.CRUSHED_IRON_ORE.get()
        );
        this.tag(AllTags.Items.IRON_PLATES).add(
                AllItems.IRON_PLATE.get()
        );
        this.tag(AllTags.Items.IRON_RODS).add(
                AllItems.IRON_ROD.get()
        );
        this.tag(AllTags.Items.TIN_DUST).add(
                AllItems.CRUSHED_TIN_ORE.get()
        );
        this.tag(AllTags.Items.TIN_INGOTS).add(
                AllItems.TIN_INGOT.get()
        );
        this.tag(AllTags.Items.TIN_ORES).add(
                AllItems.RAW_TIN.get()
        );
        this.tag(AllTags.Items.TIN_PLATES).add(
                AllItems.TIN_PLATE.get()
        );
        this.tag(AllTags.Items.TIN_RODS).add(
                AllItems.TIN_ROD.get()
        );
    }
}
