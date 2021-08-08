package com.kilroy790.notenoughmachines.world;

import com.kilroy790.notenoughmachines.blocks.NEMBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;




public class NEMFeatures 
{
	public static void addFeatures(final BiomeLoadingEvent event)
	{
		if (!event.getCategory().equals(Biome.Category.NETHER))
		{			
			addOre(event.getGeneration(), NEMBlocks.COPPERORE.get().getDefaultState(), 9, 32, 128, 20);
			addOre(event.getGeneration(), NEMBlocks.FLUXSTONE.get().getDefaultState(), 33, 0, 196, 10);
		}
	}



	/**
	 * Adds new ore blocks to the given biome 
	 * 
	 * @param biome The biome's generation settings builder
	 * 
	 * @param oreDefaultState The ore's default BlockState
	 * 
	 * @param veinSize The average number of ore blocks in a vein
	 * 
	 * @param minHeight The lowest height that this ore can generate
	 * 
	 * @param maxHeight The highest height that this ore can generate
	 * 
	 * @param maxPerChunk The maximum amount of veins that can generate in any given chunk
	 */
	protected static void addOre(BiomeGenerationSettingsBuilder biome, BlockState oreDefaultState, int veinSize, int minHeight, int maxHeight, int maxPerChunk)
	{
		biome.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(
				new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, oreDefaultState, veinSize))
				.withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight))
						.square()
						.count(maxPerChunk))); //was func_242731_b 
	}
}



