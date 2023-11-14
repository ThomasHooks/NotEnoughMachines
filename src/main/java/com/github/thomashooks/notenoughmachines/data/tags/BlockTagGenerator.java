package com.github.thomashooks.notenoughmachines.data.tags;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.NEMBlocks;
import com.github.thomashooks.notenoughmachines.util.NEMTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider
{
    public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, NotEnoughMachines.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider providerIn)
    {
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(NEMBlocks.WOODEN_FRAME.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(NEMBlocks.COPPER_PLATE_BLOCK.get(),
                        NEMBlocks.FLUXSTONE.get(),
                        NEMBlocks.FLUXSTONE_SLAB.get(),
                        NEMBlocks.FLUXSTONE_STAIRS.get(),
                        NEMBlocks.FLUXSTONE_WALL.get(),
                        NEMBlocks.IRON_PLATE_BLOCK.get(),
                        NEMBlocks.POLISHED_FLUXSTONE.get(),
                        NEMBlocks.POLISHED_FLUXSTONE_SLAB.get(),
                        NEMBlocks.POLISHED_FLUXSTONE_STAIRS.get(),
                        NEMBlocks.POLISHED_FLUXSTONE_WALL.get(),
                        NEMBlocks.WOODEN_FRAME.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(NEMBlocks.COPPER_PLATE_BLOCK.get(),
                        NEMBlocks.FLUXSTONE.get(),
                        NEMBlocks.FLUXSTONE_SLAB.get(),
                        NEMBlocks.FLUXSTONE_STAIRS.get(),
                        NEMBlocks.FLUXSTONE_WALL.get(),
                        NEMBlocks.IRON_PLATE_BLOCK.get(),
                        NEMBlocks.POLISHED_FLUXSTONE.get(),
                        NEMBlocks.POLISHED_FLUXSTONE_SLAB.get(),
                        NEMBlocks.POLISHED_FLUXSTONE_STAIRS.get(),
                        NEMBlocks.POLISHED_FLUXSTONE_WALL.get());

        this.tag(BlockTags.WALLS)
                .add(NEMBlocks.FLUXSTONE_WALL.get(),
                        NEMBlocks.POLISHED_FLUXSTONE_WALL.get());

        this.tag(NEMTags.Blocks.STRIPPED_LOGS).add(Blocks.STRIPPED_OAK_LOG,
                Blocks.STRIPPED_SPRUCE_LOG,
                Blocks.STRIPPED_BIRCH_LOG,
                Blocks.STRIPPED_JUNGLE_LOG,
                Blocks.STRIPPED_ACACIA_LOG,
                Blocks.STRIPPED_CHERRY_LOG,
                Blocks.STRIPPED_DARK_OAK_LOG,
                Blocks.STRIPPED_MANGROVE_LOG,
                Blocks.STRIPPED_CRIMSON_STEM,
                Blocks.STRIPPED_WARPED_STEM,
                Blocks.STRIPPED_OAK_WOOD,
                Blocks.STRIPPED_SPRUCE_WOOD,
                Blocks.STRIPPED_BIRCH_WOOD,
                Blocks.STRIPPED_JUNGLE_WOOD,
                Blocks.STRIPPED_ACACIA_WOOD,
                Blocks.STRIPPED_CHERRY_WOOD,
                Blocks.STRIPPED_DARK_OAK_WOOD,
                Blocks.STRIPPED_MANGROVE_WOOD,
                Blocks.STRIPPED_CRIMSON_HYPHAE,
                Blocks.STRIPPED_WARPED_HYPHAE,
                Blocks.STRIPPED_BAMBOO_BLOCK);
    }
}
