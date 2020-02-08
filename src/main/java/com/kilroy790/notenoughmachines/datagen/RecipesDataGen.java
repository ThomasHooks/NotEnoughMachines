package com.kilroy790.notenoughmachines.datagen;

import java.util.function.Consumer;

import com.kilroy790.notenoughmachines.api.lists.BlockList;
import com.kilroy790.notenoughmachines.api.lists.ItemList;
import com.kilroy790.notenoughmachines.api.lists.NEMItemTags;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;




public class RecipesDataGen extends RecipeProvider {

	
	public RecipesDataGen(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		
		
		//Crafting Items
		ShapedRecipeBuilder.shapedRecipe(ItemList.FLAXSTRING, 3)
		.patternLine("#")
		.patternLine("#")
		.patternLine("#")
		.key('#', ItemList.FLAX)
		.setGroup("flaxstring")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(ItemList.FLAX))
		.build(consumer);
		
		
		ShapedRecipeBuilder.shapedRecipe(ItemList.LINEN, 1)
		.patternLine("###")
		.patternLine("###")
		.patternLine("###")
		.key('#', ItemList.FLAXSTRING)
		.setGroup("linen")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(ItemList.FLAX))
		.build(consumer);


		ShapedRecipeBuilder.shapedRecipe(BlockList.LINENBLOCK, 1)
		.patternLine("###")
		.patternLine("###")
		.patternLine("###")
		.key('#', ItemList.LINEN)
		.setGroup("linen")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(ItemList.FLAX))
		.build(consumer);


		
		  /*ShapelessRecipeBuilder.shapelessRecipe(ItemList.LINEN, 9)
		  .addIngredient(BlockList.LINENBLOCK) 
		  .setGroup("linen") 
		  .addCriterion("flax",InventoryChangeTrigger.Instance.forItems(ItemList.FLAX))
		  .build(consumer);*/
		 
		
		
		ShapedRecipeBuilder.shapedRecipe(ItemList.GEAR, 1)
		.patternLine(" # ")
		.patternLine("#x#")
		.patternLine(" # ")
		.key('#', Items.STICK)
		.key('x', Items.IRON_NUGGET)
		.setGroup("gear")
		.addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		ShapedRecipeBuilder.shapedRecipe(ItemList.WINDBLADE, 1)
		.patternLine("xx#")
		.patternLine("xx#")
		.patternLine("  #")
		.key('#', BlockList.AXLE)
		.key('x', Items.STICK)
		.setGroup("windblade")
		.addCriterion("logs", InventoryChangeTrigger.Instance.forItems(Items.OAK_LOG, Items.BIRCH_LOG, Items.ACACIA_LOG, Items.DARK_OAK_LOG, Items.JUNGLE_LOG, Items.SPRUCE_LOG))
		.build(consumer);
		
		
		ShapedRecipeBuilder.shapedRecipe(ItemList.WINDSAIL_ITEM, 1)
		.patternLine("###")
		.patternLine("#x#")
		.patternLine("###")
		.key('#', ItemList.LINEN)
		.key('x', ItemList.WINDBLADE)
		.setGroup("windblade")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(ItemList.FLAX))
		.build(consumer);
		
		
		ShapedRecipeBuilder.shapedRecipe(ItemList.REDSTONE_COLLECTOR, 1)
		.patternLine("x")
		.patternLine("=")
		.key('=', Items.SMOOTH_STONE_SLAB)
		.key('x', Items.REDSTONE)
		.setGroup("redstone_collector")
		.addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE))
		.build(consumer);
		
		
		ShapedRecipeBuilder.shapedRecipe(ItemList.REDSTONE_EMITTER, 1)
		.patternLine("t")
		.patternLine("=")
		.key('=', Items.SMOOTH_STONE_SLAB)
		.key('t', Items.REDSTONE_TORCH)
		.setGroup("redstone_emitter")
		.addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE))
		.build(consumer);
		
		
		//Transport Machines
		//TODO change the Chute recipe
		ShapedRecipeBuilder.shapedRecipe(BlockList.CHUTE, 1)
		.patternLine("=c=")
		.patternLine(" = ")
		.key('c', Items.CHEST)
		.key('=', ItemTags.WOODEN_SLABS)
		.setGroup("chute")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(ItemList.FLAX))
		.build(consumer);
		
		
		ShapedRecipeBuilder.shapedRecipe(BlockList.CLOSEDCHUTE, 2)
		.patternLine(" = ")
		.patternLine("xcx")
		.patternLine(" = ")
		.key('c', Items.CHEST)
		.key('x', Items.IRON_INGOT)
		.key('=', ItemTags.WOODEN_SLABS)
		.setGroup("closedchute")
		.addCriterion("iron_ingot", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		ShapelessRecipeBuilder.shapelessRecipe(BlockList.ITEMPUSHER, 1)
		.addIngredient(BlockList.CLOSEDCHUTE)
		.addIngredient(Items.HOPPER) 
		.setGroup("itempusher") 
		.addCriterion("iron_ingot",InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		ShapedRecipeBuilder.shapedRecipe(BlockList.FILTER, 1)
		.patternLine("=#=")
		.patternLine("=c=")
		.patternLine(" v ")
		.key('c', Items.COMPARATOR)
		.key('v', Items.HOPPER)
		.key('#', ItemTags.WOODEN_TRAPDOORS)
		.key('=', ItemTags.WOODEN_SLABS)
		.setGroup("filter")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(ItemList.FLAX))
		.build(consumer);
		
		
		ShapedRecipeBuilder.shapedRecipe(BlockList.AXLE, 3)
		.patternLine("#")
		.patternLine("#")
		.patternLine("#")
		.key('#', NEMItemTags.STRIPPED_LOG)
		.setGroup("axle")
		.addCriterion("logs", InventoryChangeTrigger.Instance.forItems(Items.OAK_LOG, Items.BIRCH_LOG, Items.ACACIA_LOG, Items.DARK_OAK_LOG, Items.JUNGLE_LOG, Items.SPRUCE_LOG))
		.build(consumer);
		
		
		ShapedRecipeBuilder.shapedRecipe(BlockList.GEARBOX, 1)
		.patternLine("#x#")
		.patternLine("xxx")
		.patternLine("#x#")
		.key('#', ItemTags.PLANKS)
		.key('x', ItemList.GEAR)
		.setGroup("gearbox")
		.addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		//Generators
		ShapedRecipeBuilder.shapedRecipe(BlockList.SMALLWINDWHEEL, 1)
		.patternLine(" # ")
		.patternLine("#x#")
		.patternLine(" # ")
		.key('#', ItemList.WINDSAIL_ITEM)
		.key('x', BlockList.AXLE)
		.setGroup("smallwindwheel")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(ItemList.FLAX))
		.build(consumer);
		
		
		//Processing Machines
		ShapedRecipeBuilder.shapedRecipe(BlockList.MILLSTONE, 1)
		.patternLine("=x=")
		.patternLine("===")
		.patternLine("#v#")
		.key('#', ItemTags.PLANKS)
		.key('x', ItemList.GEAR)
		.key('=', Items.SMOOTH_STONE_SLAB)
		.key('v', Items.HOPPER)
		.setGroup("millstone")
		.addCriterion("iron", InventoryChangeTrigger.Instance.forItems(Items.IRON_INGOT))
		.build(consumer);
		
		
		//Logic Gates
		ShapedRecipeBuilder.shapedRecipe(BlockList.ANDGATE, 1)
		.patternLine(" e ")
		.patternLine("ece")
		.key('c', ItemList.REDSTONE_COLLECTOR)
		.key('e', ItemList.REDSTONE_EMITTER)
		.setGroup("andgate")
		.addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE))
		.build(consumer);
		
		
		ShapedRecipeBuilder.shapedRecipe(BlockList.ORGATE, 1)
		.patternLine(" c ")
		.patternLine("cec")
		.key('c', ItemList.REDSTONE_COLLECTOR)
		.key('e', ItemList.REDSTONE_EMITTER)
		.setGroup("orgate")
		.addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE))
		.build(consumer);
	}
}
