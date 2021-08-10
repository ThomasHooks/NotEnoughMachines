package com.kilroy790.notenoughmachines.data.recipes;

import java.util.function.Consumer;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.NEMBlocks;
import com.kilroy790.notenoughmachines.data.NEMTags;
import com.kilroy790.notenoughmachines.items.NEMItems;

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

		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.FLUXSTONE_SLAB.get(), 3)
		.patternLine("###")
		.key('#', NEMItems.FLUXSTONE.get())
		.setGroup("nem:fluxstone_slab")
		.addCriterion("fluxstone", InventoryChangeTrigger.Instance.forItems(NEMItems.FLUXSTONE.get()))
		.build(consumer);

		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.FLUXSTONE_STAIRS.get(), 4)
		.patternLine("#  ")
		.patternLine("## ")
		.patternLine("###")
		.key('#', NEMItems.FLUXSTONE.get())
		.setGroup("nem:fluxstone_stairs")
		.addCriterion("fluxstone", InventoryChangeTrigger.Instance.forItems(NEMItems.FLUXSTONE.get()))
		.build(consumer);

		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.FLUXSTONE_WALL.get(), 6)
		.patternLine("###")
		.patternLine("###")
		.key('#', NEMItems.FLUXSTONE.get())
		.setGroup("nem:fluxstone_wall")
		.addCriterion("fluxstone", InventoryChangeTrigger.Instance.forItems(NEMItems.FLUXSTONE.get()))
		.build(consumer);

		
		
		ShapelessRecipeBuilder.shapelessRecipe(NEMBlocks.FLUXSTONE_BUTTON.get(), 1)
		.addIngredient(NEMItems.FLUXSTONE.get(), 1)
		.setGroup("nem:fluxstone_button")
		.addCriterion("fluxstone", InventoryChangeTrigger.Instance.forItems(NEMItems.FLUXSTONE.get()))
		.build(consumer);

		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.FLUXSTONE_PRESSURE_PLATE.get(), 1)
		.patternLine("##")
		.key('#', NEMItems.FLUXSTONE.get())
		.setGroup("nem:fluxstone_pressure_plate")
		.addCriterion("fluxstone", InventoryChangeTrigger.Instance.forItems(NEMItems.FLUXSTONE.get()))
		.build(consumer);

		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.POLISHED_FLUXSTONE.get(), 4)
		.patternLine("##")
		.patternLine("##")
		.key('#', NEMItems.FLUXSTONE.get())
		.setGroup("nem:polished_fluxstone")
		.addCriterion("fluxstone", InventoryChangeTrigger.Instance.forItems(NEMItems.FLUXSTONE.get()))
		.build(consumer);

		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.POLISHED_FLUXSTONE_SLAB.get(), 3)
		.patternLine("###")
		.key('#', NEMItems.POLISHED_FLUXSTONE.get())
		.setGroup("nem:polished_fluxstone_slab")
		.addCriterion("polished_fluxstone", InventoryChangeTrigger.Instance.forItems(NEMItems.POLISHED_FLUXSTONE.get()))
		.build(consumer);

		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.POLISHED_FLUXSTONE_STAIRS.get(), 4)
		.patternLine("#  ")
		.patternLine("## ")
		.patternLine("###")
		.key('#', NEMItems.POLISHED_FLUXSTONE.get())
		.setGroup("nem:polished_fluxstone_stairs")
		.addCriterion("polished_fluxstone", InventoryChangeTrigger.Instance.forItems(NEMItems.POLISHED_FLUXSTONE.get()))
		.build(consumer);

		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.POLISHED_FLUXSTONE_WALL.get(), 6)
		.patternLine("###")
		.patternLine("###")
		.key('#', NEMItems.POLISHED_FLUXSTONE.get())
		.setGroup("nem:polished_fluxstone_wall")
		.addCriterion("polished_fluxstone", InventoryChangeTrigger.Instance.forItems(NEMItems.POLISHED_FLUXSTONE.get()))
		.build(consumer);

		
		
		ShapelessRecipeBuilder.shapelessRecipe(NEMBlocks.POLISHED_FLUXSTONE_BUTTON.get(), 1)
		.addIngredient(NEMItems.POLISHED_FLUXSTONE.get(), 1)
		.setGroup("nem:polished_fluxstone_button")
		.addCriterion("polished_fluxstone", InventoryChangeTrigger.Instance.forItems(NEMItems.POLISHED_FLUXSTONE.get()))
		.build(consumer);

		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.POLISHED_FLUXSTONE_PRESSURE_PLATE.get(), 1)
		.patternLine("##")
		.key('#', NEMItems.POLISHED_FLUXSTONE.get())
		.setGroup("nem:polished_fluxstone_pressure_plate")
		.addCriterion("polished_fluxstone", InventoryChangeTrigger.Instance.forItems(NEMItems.POLISHED_FLUXSTONE.get()))
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
		.build(consumer, NotEnoughMachines.MODID + ":linen_from_linenblock");

		
		
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
		.key('#', NEMTags.Items.STRIPPED_LOGS)
		.key('b', NEMItems.LINSEEDOIL.get())
		.setGroup("nem:axle")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.ENCLOSED_AXLE.get(), 3)
		.patternLine("###")
		.patternLine("xxx")
		.patternLine("###")
		.key('#', ItemTags.WOODEN_SLABS)
		.key('x', NEMItems.AXLE.get())
		.setGroup("nem:enclosedaxle")
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
		
		
		
//		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.TRIPHAMMER.get())
//		.patternLine("#l#")
//		.patternLine("#x#")
//		.patternLine("#=#")
//		.key('l', NEMBlocks.AXLE.get())
//		.key('#', NEMItemTags.STRIPPED_LOGS)
//		.key('x', NEMBlocks.GEARBOX.get())
//		.key('=', NEMItems.HAMMER_AND_ANVIL.get())
//		.setGroup("nem:triphammer")
//		.addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
//		.build(consumer);
		
		
		
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
		
		
		
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(NEMItems.CRUSHED_COPPER_ORE.get()), NEMItems.COPPER_INGOT.get(), 0.35f, 200)
		.addCriterion("copper_ore", InventoryChangeTrigger.Instance.forItems(NEMBlocks.COPPERORE.get()))
		.build(consumer, NotEnoughMachines.MODID + ":furnace/copper_from_crushed_copper_ore");
		
		
		
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(NEMItems.CRUSHED_COPPER_ORE.get()), NEMItems.COPPER_INGOT.get(), 0.35f, 100)
		.addCriterion("copper_ore", InventoryChangeTrigger.Instance.forItems(NEMBlocks.COPPERORE.get()))
		.build(consumer, NotEnoughMachines.MODID + ":furnace/copper_from_crushed_copper_ore_and_blasting");
		
		
		
		//***********************************************************************************************************************************************************************
		//Stamping Recipes
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.STONE, Blocks.CRACKED_STONE_BRICKS, Blocks.SMOOTH_STONE), Items.COBBLESTONE, 300)
		.setGroup("nem:cobblestone")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/cobblestone");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.STONE_BRICKS, Blocks.CHISELED_STONE_BRICKS), Items.CRACKED_STONE_BRICKS, 300)
		.setGroup("nem:cracked_stone_bricks")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/crackedstone_bricks");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.MOSSY_STONE_BRICKS), Items.MOSSY_COBBLESTONE, 300)
		.setGroup("nem:mossy_cobblestone")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/mossy_cobblestone");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.COBBLESTONE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.GRANITE, Blocks.POLISHED_GRANITE), Items.GRAVEL, 300)
		.setGroup("nem:gravel")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/gravel");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.GRAVEL), Items.FLINT, 300)
		.setGroup("nem:flint")
		.addSecondaryResult(Items.SAND)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/flint");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.CUT_SANDSTONE), Items.SAND, 4, 300)
		.setGroup("nem:sand")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/sand");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.RED_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE), Items.RED_SAND, 4, 300)
		.setGroup("nem:red_sand")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/red_sand");

		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.NETHERRACK), Items.SOUL_SAND, 300)
		.setGroup("nem:soul_sand")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/netherrack");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.IRON_ORE), NEMItems.CRUSHED_IRON_ORE.get(), 2, 600)
		.setGroup("nem:crushed_iron_ore")
		.addSecondaryResult(Items.GRAVEL)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/crushed_iron_ore");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.GOLD_ORE), NEMItems.CRUSHED_GOLD_ORE.get(), 2, 600)
		.setGroup("nem:crushed_gold_ore")
		.addSecondaryResult(Items.GRAVEL)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/crushed_gold_ore");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(NEMBlocks.COPPERORE.get()), NEMItems.CRUSHED_COPPER_ORE.get(), 2, 600)
		.setGroup("nem:crushed_copper_ore")
		.addSecondaryResult(Items.GRAVEL)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/crushed_copper_ore");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(NEMBlocks.FLUXSTONE.get()), NEMItems.FLUX.get(), 4, 300)
		.setGroup("nem:flux")
		.addSecondaryResult(Items.GRAVEL)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/flux");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.COAL_ORE), Items.COAL, 8, 600)
		.setGroup("nem:coal")
		.addSecondaryResult(Items.GRAVEL)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/coal");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.DIAMOND_ORE), Items.DIAMOND, 4, 600)
		.setGroup("nem:diamond")
		.addSecondaryResult(Items.GRAVEL)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/diamond");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.EMERALD_ORE), Items.EMERALD, 4, 600)
		.setGroup("nem:emerald")
		.addSecondaryResult(Items.GRAVEL)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/emerald");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.NETHER_QUARTZ_ORE), Items.QUARTZ, 4, 600)
		.setGroup("nem:quartz")
		.addSecondaryResult(Items.NETHERRACK)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/quartz");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.LAPIS_ORE), Items.LAPIS_LAZULI, 16, 600)
		.setGroup("nem:lapis_lazuli")
		.addSecondaryResult(Items.GRAVEL)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/lapis_lazuli");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.REDSTONE_ORE), Items.REDSTONE, 16, 600)
		.setGroup("nem:redstone")
		.addSecondaryResult(Items.GRAVEL)
		.build(consumer, NotEnoughMachines.MODID + ":stamping/redstone");
		
		
		
		StampingRecipeBuilder.stampingRecipe(Ingredient.fromItems(Blocks.GLOWSTONE), Items.GLOWSTONE_DUST, 6, 300)
		.setGroup("nem:glowstone")
		.build(consumer, NotEnoughMachines.MODID + ":stamping/glowstone");
		
		
		
		//***********************************************************************************************************************************************************************
		//Milling Recipes
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(NEMItems.FLAX.get()), NEMItems.FLAXSTRING.get(), 3, 200)
		.setGroup("nem:flaxstring")
		.build(consumer, NotEnoughMachines.MODID + ":milling/flaxstring");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.BONE), Items.BONE_MEAL, 6, 200)
		.setGroup("nem:bone_meal")
		.build(consumer, NotEnoughMachines.MODID + ":milling/bone_meal");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.WHEAT), NEMItems.FLOUR.get(), 200)
		.setGroup("nem:flour")
		.build(consumer, NotEnoughMachines.MODID + ":milling/flour");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.SUGAR_CANE), Items.SUGAR, 2, 200)
		.setGroup("nem:sugar")
		.build(consumer, NotEnoughMachines.MODID + ":milling/sugar");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.BLAZE_ROD), Items.BLAZE_POWDER, 4, 200)
		.setGroup("nem:blaze_powder")
		.build(consumer, NotEnoughMachines.MODID + ":milling/blaze_powder");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.INK_SAC, Items.WITHER_ROSE), Items.BLACK_DYE, 2, 200)
		.setGroup("nem:black_dye")
		.build(consumer, NotEnoughMachines.MODID + ":milling/black_dye");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.LAPIS_LAZULI, Items.CORNFLOWER), Items.BLUE_DYE, 2, 200)
		.setGroup("nem:blue_dye")
		.build(consumer, NotEnoughMachines.MODID + ":milling/blue_dye");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.BLUE_ORCHID), Items.LIGHT_BLUE_DYE, 2, 200)
		.setGroup("nem:light_blue_dye")
		.build(consumer, NotEnoughMachines.MODID + ":milling/light_blue_dye");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.COCOA_BEANS), Items.BROWN_DYE, 2, 200)
		.setGroup("nem:brown_dye")
		.build(consumer, NotEnoughMachines.MODID + ":milling/brown_dye");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.CACTUS), Items.GREEN_DYE, 2, 200)
		.setGroup("nem:green_dye")
		.build(consumer, NotEnoughMachines.MODID + ":milling/green_dye");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.POPPY, Items.RED_TULIP, Items.BEETROOT, Items.ROSE_BUSH), Items.RED_DYE, 2, 200)
		.setGroup("nem:red_dye")
		.build(consumer, NotEnoughMachines.MODID + ":milling/red_dye");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.BONE_MEAL, Items.LILY_OF_THE_VALLEY), Items.WHITE_DYE, 2, 200)
		.setGroup("nem:white_dye")
		.build(consumer, NotEnoughMachines.MODID + ":milling/white_dye");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.AZURE_BLUET, Items.OXEYE_DAISY, Items.WHITE_TULIP), Items.LIGHT_GRAY_DYE, 2, 200)
		.setGroup("nem:light_gray_dye")
		.build(consumer, NotEnoughMachines.MODID + ":milling/light_gray_dye");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.LILAC, Items.ALLIUM), Items.MAGENTA_DYE, 2, 200)
		.setGroup("nem:magenta_dye")
		.build(consumer, NotEnoughMachines.MODID + ":milling/magenta_dye");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.ORANGE_TULIP), Items.ORANGE_DYE, 2, 200)
		.setGroup("nem:orange_dye")
		.build(consumer, NotEnoughMachines.MODID + ":milling/orange_dye");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.PINK_TULIP, Items.PEONY), Items.PINK_DYE, 2, 200)
		.setGroup("nem:pink_dye")
		.build(consumer, NotEnoughMachines.MODID + ":milling/pink_dye");
		
		
		
		MillingRecipeBuilder.millingRecipe(Ingredient.fromItems(Items.DANDELION, Items.SUNFLOWER), Items.YELLOW_DYE, 2, 200)
		.setGroup("nem:yellow_dye")
		.build(consumer, NotEnoughMachines.MODID + ":milling/yellow_dye");
	}
}







