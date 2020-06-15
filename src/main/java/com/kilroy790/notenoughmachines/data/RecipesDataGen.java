package com.kilroy790.notenoughmachines.data;

import java.util.function.Consumer;

import com.kilroy790.notenoughmachines.setup.NEMBlocks;
import com.kilroy790.notenoughmachines.setup.NEMItems;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
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
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAX.get()))
		.build(consumer);
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMItems.LINEN.get(), 1)
		.patternLine("###")
		.patternLine("###")
		.patternLine("###")
		.key('#', NEMItems.FLAXSTRING.get())
		.setGroup("linen")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAX.get()))
		.build(consumer);


		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.LINENBLOCK.get(), 1)
		.patternLine("###")
		.patternLine("###")
		.patternLine("###")
		.key('#', NEMItems.LINEN.get())
		.setGroup("linen")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAX.get()))
		.build(consumer);


		
//		  ShapelessRecipeBuilder.shapelessRecipe(ItemList.LINEN, 9)
//		  .addIngredient(BlockList.LINENBLOCK) 
//		  .setGroup("linen") 
//		  .addCriterion("flax",InventoryChangeTrigger.Instance.forItems(ItemList.FLAX))
//		  .build(consumer);
		 
		
		
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
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAX.get()))
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
		
		
		//Transport Machines
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.CHUTE.get(), 2)
		.patternLine("xc=")
		.key('x', Items.IRON_INGOT)
		.key('c', Items.CHEST)
		.key('=', ItemTags.WOODEN_SLABS)
		.setGroup("chute")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAX.get()))
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
		
		
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.AXLE.get(), 3)
		.patternLine("#")
		.patternLine("#")
		.patternLine("#")
		.key('#', NEMItemTags.STRIPPED_LOG)
		.setGroup("axle")
		.addCriterion("logs", InventoryChangeTrigger.Instance.forItems(Items.OAK_LOG, Items.BIRCH_LOG, Items.ACACIA_LOG, Items.DARK_OAK_LOG, Items.JUNGLE_LOG, Items.SPRUCE_LOG))
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
		
		
		//Generators
		ShapedRecipeBuilder.shapedRecipe(NEMBlocks.SMALLWINDWHEEL.get(), 1)
		.patternLine(" # ")
		.patternLine("#x#")
		.patternLine(" # ")
		.key('#', NEMItems.WINDSAIL_ITEM.get())
		.key('x', NEMBlocks.AXLE.get())
		.setGroup("smallwindwheel")
		.addCriterion("flax", InventoryChangeTrigger.Instance.forItems(NEMItems.FLAX.get()))
		.build(consumer);
		
		
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
	}
}







