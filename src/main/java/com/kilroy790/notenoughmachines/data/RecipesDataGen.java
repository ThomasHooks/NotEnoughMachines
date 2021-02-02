package com.kilroy790.notenoughmachines.data;

import java.util.function.Consumer;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.setup.NEMBlocks;
import com.kilroy790.notenoughmachines.setup.NEMItems;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;




public class RecipesDataGen extends RecipeProvider 
{
	public RecipesDataGen(DataGenerator generatorIn) 
	{
		super(generatorIn);
	}
	
	
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) 
	{
		//Crafting Items
		ShapedRecipeBuilder.shapedRecipe(NEMItems.FLAXSTRING.get(), 3)
		.patternLine("#")
		.patternLine("#")
		.patternLine("#")
		.key('#', NEMItems.FLAX.get())
		.setGroup("nem:flaxstring")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMItems.LINEN.get(), 1)
		.patternLine("###")
		.patternLine("###")
		.patternLine("###")
		.key('#', NEMItems.FLAXSTRING.get())
		.setGroup("nem:linen")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);

		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.LINENBLOCK.get(), 1)
		.patternLine("###")
		.patternLine("###")
		.patternLine("###")
		.key('#', NEMItems.LINEN.get())
		.setGroup("nem:linenblock")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);

		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.POLISHED_FLUXSTONE.get(), 4)
		.patternLine("##")
		.patternLine("##")
		.key('#', NEMItems.FLUXSTONE.get())
		.setGroup("nem:polished_fluxstone")
		.addCriterion("fluxstone", InventoryChangeTrigger.Instance.forItems(NEMItems.FLUXSTONE.get()))
		.build(consumer);
		
		
		
		ShapelessRecipeBuilder.shapelessRecipe(NEMItems.LINSEEDOIL.get(), 1)
		.addIngredient(NEMItems.FLAXSEED.get(), 6)
		.addIngredient(Items.GLASS_BOTTLE, 1)
		.setGroup("nem:linseed_oil")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);

		
		
		ShapelessRecipeBuilder.shapelessRecipe(NEMItems.LINEN.get(), 9)
		.addIngredient(NEMBlocks.LINENBLOCK.get()) 
		.setGroup("nem:linenblock") 
		.addCriterion("flax",InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer, NotEnoughMachines.MODID + ":linen_from_linen_block");

		
		
		ShapedRecipeBuilder.shapedRecipe(NEMItems.GEAR.get(), 1)
		.patternLine(" # ")
		.patternLine("#b#")
		.patternLine(" # ")
		.key('#', Items.STICK)
		.key('b', NEMItems.LINSEEDOIL.get())
		.setGroup("nem:gear")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMItems.WINDBLADE.get(), 1)
		.patternLine("xx#")
		.patternLine("xx#")
		.patternLine("  #")
		.key('#', NEMBlocks.AXLE.get())
		.key('x', Items.STICK)
		.setGroup("nem:windblade")
		.addCriterion("logs", InventoryChangeTrigger.Instance.forItems(Items.OAK_LOG, Items.BIRCH_LOG, Items.ACACIA_LOG, Items.DARK_OAK_LOG, Items.JUNGLE_LOG, Items.SPRUCE_LOG))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMItems.WINDSAIL_ITEM.get(), 1)
		.patternLine("###")
		.patternLine("#x#")
		.patternLine("###")
		.key('#', NEMItems.LINEN.get())
		.key('x', NEMItems.WINDBLADE.get())
		.setGroup("nem:windsail")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMItems.HAMMER_AND_ANVIL.get())
		.patternLine("l")
		.patternLine("#")
		.patternLine("=")
		.key('l', NEMBlocks.AXLE.get())
		.key('#', Blocks.IRON_BLOCK)
		.key('=', Blocks.ANVIL)
		.setGroup("nem:hammer_and_anvil")
		.addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMItems.REDSTONE_COLLECTOR.get(), 1)
		.patternLine("x")
		.patternLine("=")
		.key('=', Items.SMOOTH_STONE_SLAB)
		.key('x', Items.REDSTONE)
		.setGroup("nem:redstone_collector")
		.addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMItems.REDSTONE_EMITTER.get(), 1)
		.patternLine("t")
		.patternLine("=")
		.key('=', Items.SMOOTH_STONE_SLAB)
		.key('t', Items.REDSTONE_TORCH)
		.setGroup("nem:redstone_emitter")
		.addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(Items.GUNPOWDER, 2)
		.patternLine("fc")
		.patternLine("cf")
		.key('f', NEMItems.FLUX.get())
		.key('c', Items.COAL)
		.setGroup("nem:gunpowder")
		.addCriterion("fluxstone", InventoryChangeTrigger.Instance.forItems(NEMItems.FLUXSTONE.get()))
		.build(consumer, NotEnoughMachines.MODID + ":gunpowder_from_flux_and_coal");
		
		
		
		ShapedRecipeBuilder.shapedRecipe(Items.GUNPOWDER, 2)
		.patternLine("fc")
		.patternLine("cf")
		.key('f', NEMItems.FLUX.get())
		.key('c', Items.CHARCOAL)
		.setGroup("nem:gunpowder")
		.addCriterion("fluxstone", InventoryChangeTrigger.Instance.forItems(NEMItems.FLUXSTONE.get()))
		.build(consumer, NotEnoughMachines.MODID + ":gunpowder_from_flux_and_charcoal");
		
		
		
		//***********************************************************************************************************************************************************************
		//Transport Machines
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.CHUTE.get(), 2)
		.patternLine("xc=")
		.key('x', Items.IRON_INGOT)
		.key('c', Items.CHEST)
		.key('=', ItemTags.WOODEN_SLABS)
		.setGroup("nem:chute")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.FILTER.get(), 2)
		.patternLine("=#=")
		.patternLine("xcx")
		.patternLine("=r=")
		.key('c', Items.CHEST)
		.key('x', NEMBlocks.CHUTE.get())
		.key('=', ItemTags.WOODEN_SLABS)
		.key('#', ItemTags.WOODEN_TRAPDOORS)
		.key('r', Items.COMPARATOR)
		.setGroup("nem:filter")
		.addCriterion("iron_ingot", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.ITEMPUSHER.get(), 2)
		.patternLine("icx")
		.key('i', Items.IRON_INGOT)
		.key('c', Items.CHEST)
		.key('x', Items.REDSTONE)
		.setGroup("nem:itempusher")
		.addCriterion("iron_ingot", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.AXLE.get(), 2)
		.patternLine("#")
		.patternLine("#")
		.patternLine("b")
		.key('#', NEMItemTags.STRIPPED_LOGS)
		.key('b', NEMItems.LINSEEDOIL.get())
		.setGroup("nem:axle")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);
		
		
		
		ShapelessRecipeBuilder.shapelessRecipe(NEMItems.SMALLCOG.get(), 1)
		.addIngredient(NEMBlocks.AXLE.get())
		.addIngredient(NEMItems.GEAR.get())
		.setGroup("nem:small_cog") 
		.addCriterion("flax",InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.LARGECOG.get(), 1)
		.patternLine("###")
		.patternLine("#x#")
		.patternLine("###")
		.key('#', ItemTags.WOODEN_SLABS)
		.key('x', NEMItems.SMALLCOG.get())
		.setGroup("nem:large_cog")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.GEARBOX.get(), 1)
		.patternLine("#x#")
		.patternLine("xbx")
		.patternLine("#x#")
		.key('#', ItemTags.PLANKS)
		.key('x', NEMItems.GEAR.get())
		.key('b', NEMItems.LINSEEDOIL.get())
		.setGroup("nem:gearbox")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);
		
		
		
		//***********************************************************************************************************************************************************************
		//Generators
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.SMALLWINDWHEEL.get(), 1)
		.patternLine(" # ")
		.patternLine("#x#")
		.patternLine(" # ")
		.key('#', NEMItems.WINDSAIL_ITEM.get())
		.key('x', NEMBlocks.AXLE.get())
		.setGroup("nem:smallwindwheel")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.TUBWHEEL.get(), 1)
		.patternLine("===")
		.patternLine("=x=")
		.patternLine("===")
		.key('=', ItemTags.WOODEN_SLABS)
		.key('x', NEMBlocks.SMALLCOG.get())
		.setGroup("nem:tubwheel")
		.addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		
		//***********************************************************************************************************************************************************************
		//Processing Machines
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.MILLSTONE.get(), 1)
		.patternLine("=x=")
		.patternLine("===")
		.patternLine("#v#")
		.key('#', ItemTags.PLANKS)
		.key('x', NEMItems.GEAR.get())
		.key('=', Items.SMOOTH_STONE_SLAB)
		.key('v', Items.HOPPER)
		.setGroup("nem:millstone")
		.addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.TRIPHAMMER.get())
		.patternLine("#l#")
		.patternLine("#x#")
		.patternLine("#=#")
		.key('l', NEMBlocks.AXLE.get())
		.key('#', NEMItemTags.STRIPPED_LOGS)
		.key('x', NEMBlocks.GEARBOX.get())
		.key('=', NEMItems.HAMMER_AND_ANVIL.get())
		.setGroup("nem:triphammer")
		.addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		
		//***********************************************************************************************************************************************************************
		//Logic Gates
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.ANDGATE.get(), 1)
		.patternLine(" e ")
		.patternLine("ece")
		.key('c', NEMItems.REDSTONE_COLLECTOR.get())
		.key('e', NEMItems.REDSTONE_EMITTER.get())
		.setGroup("nem:andgate")
		.addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.ORGATE.get(), 1)
		.patternLine(" c ")
		.patternLine("cec")
		.key('c', NEMItems.REDSTONE_COLLECTOR.get())
		.key('e', NEMItems.REDSTONE_EMITTER.get())
		.setGroup("nem:orgate")
		.addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE))
		.build(consumer);
		
		
		
		//***********************************************************************************************************************************************************************
		//Furnace Recipes
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(NEMItems.FLOUR.get()), Items.BREAD, 0.35f, 200)
		.addCriterion("wheat", InventoryChangeTrigger.Instance.forItems(Items.WHEAT))
		.build(consumer, NotEnoughMachines.MODID + ":furnace/bread_from_flour");
		
		
		
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(NEMBlocks.COPPERORE.get()), NEMItems.COPPER_INGOT.get(), 0.35f, 200)
		.addCriterion("copper_ore", InventoryChangeTrigger.Instance.forItems(NEMBlocks.COPPERORE.get()))
		.build(consumer, NotEnoughMachines.MODID + ":furnace/copper_ingot_from_copper_ore");
		
		
		
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(NEMBlocks.COPPERORE.get()), NEMItems.COPPER_INGOT.get(), 0.35f, 100)
		.addCriterion("copper_ore", InventoryChangeTrigger.Instance.forItems(NEMBlocks.COPPERORE.get()))
		.build(consumer, NotEnoughMachines.MODID + ":furnace/copper_ingot_from_copper_ore_and_blasting");
		
		
		
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(NEMItems.CRUSHED_IRON_ORE.get()), Items.IRON_INGOT, 0.35f, 200)
		.addCriterion("iron_ore", InventoryChangeTrigger.Instance.forItems(Blocks.IRON_ORE))
		.build(consumer, NotEnoughMachines.MODID + ":furnace/iron_from_crushed_iron_ore");
		
		
		
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(NEMItems.CRUSHED_IRON_ORE.get()), Items.IRON_INGOT, 0.35f, 100)
		.addCriterion("iron_ore", InventoryChangeTrigger.Instance.forItems(Blocks.IRON_ORE))
		.build(consumer, NotEnoughMachines.MODID + ":furnace/iron_from_crushed_iron_ore_and_blasting");
		
		
		
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(NEMItems.CRUSHED_GOLD_ORE.get()), Items.GOLD_INGOT, 0.35f, 200)
		.addCriterion("gold_ore", InventoryChangeTrigger.Instance.forItems(Blocks.GOLD_ORE))
		.build(consumer, NotEnoughMachines.MODID + ":furnace/gold_from_crushed_gold_ore");
		
		
		
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(NEMItems.CRUSHED_GOLD_ORE.get()), Items.GOLD_INGOT, 0.35f, 100)
		.addCriterion("gold_ore", InventoryChangeTrigger.Instance.forItems(Blocks.GOLD_ORE))
		.build(consumer, NotEnoughMachines.MODID + ":furnace/gold_from_crushed_gold_ore_and_blasting");
		
		
		
		//***********************************************************************************************************************************************************************
		//Trip Hammer Recipes
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.STONE), Items.COBBLESTONE, 300)
		.setGroup("nem:cobblestone")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/cobblestone");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.SMOOTH_STONE), Items.COBBLESTONE, 300)
		.setGroup("nem:cobblestone")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/cobblestone_from_smooth_stone");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.STONE_BRICKS), Items.COBBLESTONE, 300)
		.setGroup("nem:cobblestone")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/cobblestone_from_stone_bricks");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.CHISELED_STONE_BRICKS), Items.COBBLESTONE, 300)
		.setGroup("nem:cobblestone")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/cobblestone_from_chiseled_stone_bricks");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.MOSSY_STONE_BRICKS), Items.COBBLESTONE, 300)
		.setGroup("nem:cobblestone")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/cobblestone_from_mossy_stone_bricks");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.CRACKED_STONE_BRICKS), Items.COBBLESTONE, 300)
		.setGroup("nem:cobblestone")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/cobblestone_from_cracked_stone_bricks");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.COBBLESTONE), Items.GRAVEL, 300)
		.setGroup("nem:gravel")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/gravel");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.ANDESITE), Items.GRAVEL, 300)
		.setGroup("nem:gravel")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/gravel_from_andesite");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.POLISHED_ANDESITE), Items.GRAVEL, 300)
		.setGroup("nem:gravel")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/gravel_from_polished_andesite");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.DIORITE), Items.GRAVEL, 300)
		.setGroup("nem:gravel")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/gravel_from_diorite");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.POLISHED_DIORITE), Items.GRAVEL, 300)
		.setGroup("nem:gravel")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/gravel_from_polished_diorite");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.GRANITE), Items.GRAVEL, 300)
		.setGroup("nem:gravel")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/gravel_from_granite");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.POLISHED_GRANITE), Items.GRAVEL, 300)
		.setGroup("nem:gravel")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/gravel_from_polished_granite");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.GRAVEL), Items.FLINT, 300)
		.setGroup("nem:flint")
		.addSecondaryResult(Items.SAND)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/flint");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.SANDSTONE), Items.SAND, 4, 300)
		.setGroup("nem:sand")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/sand");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.CHISELED_SANDSTONE), Items.SAND, 4, 300)
		.setGroup("nem:sand")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/sand_from_chiseled_sandstone");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.SMOOTH_SANDSTONE), Items.SAND, 4, 300)
		.setGroup("nem:sand")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/sand_from_smooth_sandstone");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.CUT_SANDSTONE), Items.SAND, 4, 300)
		.setGroup("nem:sand")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/sand_from_cut_sandstone");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.RED_SANDSTONE), Items.RED_SAND, 4, 300)
		.setGroup("nem:red_sand")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/red_sand");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.CHISELED_RED_SANDSTONE), Items.RED_SAND, 4, 300)
		.setGroup("nem:red_sand")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/red_sand_from_chiseled_red_sandstone");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.CUT_RED_SANDSTONE), Items.RED_SAND, 4, 300)
		.setGroup("nem:red_sand")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/red_sand_from_cut_red_sandstone");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.SMOOTH_RED_SANDSTONE), Items.RED_SAND, 4, 300)
		.setGroup("nem:red_sand")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/red_sand_from_smooth_red_sandstone");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.IRON_ORE), NEMItems.CRUSHED_IRON_ORE.get(), 2, 600)
		.setGroup("nem:crushed_iron_ore")
		.addSecondaryResult(Items.GRAVEL)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/crushed_iron_ore");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.GOLD_ORE), NEMItems.CRUSHED_GOLD_ORE.get(), 2, 600)
		.setGroup("nem:crushed_gold_ore")
		.addSecondaryResult(Items.GRAVEL)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/crushed_gold_ore");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(NEMBlocks.FLUXSTONE.get()), NEMItems.FLUX.get(), 4, 300)
		.setGroup("nem:flux")
		.addSecondaryResult(Items.GRAVEL)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/flux");
	}
}







