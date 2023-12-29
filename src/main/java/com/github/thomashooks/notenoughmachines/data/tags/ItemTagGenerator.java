package com.github.thomashooks.notenoughmachines.data.tags;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.common.tags.AllTags;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider
{

    public ItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> tagLookup, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(packOutput, lookupProvider, tagLookup, NotEnoughMachines.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
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
