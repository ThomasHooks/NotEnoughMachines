package com.kilroy790.notenoughmachines.data;

import java.util.function.Consumer;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.setup.NEMBlocks;
import com.kilroy790.notenoughmachines.setup.NEMItems;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;




public class RecipesDataGen extends RecipeProvider {

	public RecipesDataGen(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		
		//Crafting Items
		ShapedRecipeBuilder.shapedRecipe(NEMItems.FLAXSTRING.get(), 3)
		.patternLine("#")
		.patternLine("#")
		.patternLine("#")
		.key('#', NEMItems.FLAX.get())
		.setGroup("flaxstring")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMItems.LINEN.get(), 1)
		.patternLine("###")
		.patternLine("###")
		.patternLine("###")
		.key('#', NEMItems.FLAXSTRING.get())
		.setGroup("linen")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);

		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.LINENBLOCK.get(), 1)
		.patternLine("###")
		.patternLine("###")
		.patternLine("###")
		.key('#', NEMItems.LINEN.get())
		.setGroup("linen")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);
		
		
		
		ShapelessRecipeBuilder.shapelessRecipe(NEMItems.LINSEEDOIL.get(), 1)
		.addIngredient(NEMItems.FLAXSEED.get(), 6)
		.addIngredient(Items.GLASS_BOTTLE, 1)
		.setGroup("linseed_oil")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);

		
		
		ShapelessRecipeBuilder.shapelessRecipe(NEMItems.LINEN.get(), 9)
		.addIngredient(NEMBlocks.LINENBLOCK.get()) 
		.setGroup("linen") 
		.addCriterion("flax",InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer, NotEnoughMachines.MODID + ":linen_from_linen_block");

		
		
		ShapedRecipeBuilder.shapedRecipe(NEMItems.GEAR.get(), 1)
		.patternLine(" # ")
		.patternLine("#x#")
		.patternLine(" # ")
		.key('#', Items.STICK)
		.key('x', Items.IRON_NUGGET)
		.setGroup("gear")
		.addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMItems.WINDBLADE.get(), 1)
		.patternLine("xx#")
		.patternLine("xx#")
		.patternLine("  #")
		.key('#', NEMBlocks.AXLE.get())
		.key('x', Items.STICK)
		.setGroup("windblade")
		.addCriterion("logs", InventoryChangeTrigger.Instance.forItems(Items.OAK_LOG, Items.BIRCH_LOG, Items.ACACIA_LOG, Items.DARK_OAK_LOG, Items.JUNGLE_LOG, Items.SPRUCE_LOG))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMItems.WINDSAIL_ITEM.get(), 1)
		.patternLine("###")
		.patternLine("#x#")
		.patternLine("###")
		.key('#', NEMItems.LINEN.get())
		.key('x', NEMItems.WINDBLADE.get())
		.setGroup("windblade")
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
		.setGroup("redstone_collector")
		.addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMItems.REDSTONE_EMITTER.get(), 1)
		.patternLine("t")
		.patternLine("=")
		.key('=', Items.SMOOTH_STONE_SLAB)
		.key('t', Items.REDSTONE_TORCH)
		.setGroup("redstone_emitter")
		.addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE))
		.build(consumer);
		
		
		
		//***********************************************************************************************************************************************************************
		//Transport Machines
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.CHUTE.get(), 2)
		.patternLine("xc=")
		.key('x', Items.IRON_INGOT)
		.key('c', Items.CHEST)
		.key('=', ItemTags.WOODEN_SLABS)
		.setGroup("chute")
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
		.setGroup("filter")
		.addCriterion("iron_ingot", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.ITEMPUSHER.get(), 2)
		.patternLine("icx")
		.key('i', Items.IRON_INGOT)
		.key('c', Items.CHEST)
		.key('x', Items.REDSTONE)
		.setGroup("itempusher")
		.addCriterion("iron_ingot", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.AXLE.get(), 2)
		.patternLine("#")
		.patternLine("#")
		.patternLine("b")
		.key('#', NEMItemTags.STRIPPED_LOGS)
		.key('b', NEMItems.LINSEEDOIL.get())
		.setGroup("axle")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);
		
		
		
		ShapelessRecipeBuilder.shapelessRecipe(NEMItems.SMALLCOG.get(), 1)
		.addIngredient(NEMBlocks.AXLE.get())
		.addIngredient(NEMItems.GEAR.get())
		.setGroup("gear") 
		.addCriterion("iron",InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.GEARBOX.get(), 1)
		.patternLine("#x#")
		.patternLine("xxx")
		.patternLine("#x#")
		.key('#', ItemTags.PLANKS)
		.key('x', NEMItems.GEAR.get())
		.setGroup("gearbox")
		.addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		
		//***********************************************************************************************************************************************************************
		//Generators
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.SMALLWINDWHEEL.get(), 1)
		.patternLine(" # ")
		.patternLine("#x#")
		.patternLine(" # ")
		.key('#', NEMItems.WINDSAIL_ITEM.get())
		.key('x', NEMBlocks.AXLE.get())
		.setGroup("smallwindwheel")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAXSEED.get()))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.TUBWHEEL.get(), 1)
		.patternLine("===")
		.patternLine("=x=")
		.patternLine("===")
		.key('=', ItemTags.WOODEN_SLABS)
		.key('x', NEMBlocks.SMALLCOG.get())
		.setGroup("tubwheel")
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
		.setGroup("millstone")
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
		.setGroup("andgate")
		.addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE))
		.build(consumer);
		
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.ORGATE.get(), 1)
		.patternLine(" c ")
		.patternLine("cec")
		.key('c', NEMItems.REDSTONE_COLLECTOR.get())
		.key('e', NEMItems.REDSTONE_EMITTER.get())
		.setGroup("orgate")
		.addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE))
		.build(consumer);
		
		
		
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
	}
}







