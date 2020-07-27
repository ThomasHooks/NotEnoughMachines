package com.kilroy790.notenoughmachines.data;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;
import com.kilroy790.notenoughmachines.recipes.StampingRecipe;
import com.kilroy790.notenoughmachines.setup.NEMRecipes;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;



//TODO: Add advancements
public class StampingRecipeBuilder {

	private final Ingredient ingredient;
	private final Item resultPrimary;
	private final int countPrimary;
	private Item resultSecondary = null;
	private int countSecondary = 0;
	private final int processingTime;
	private String group;
	private final IRecipeSerializer<StampingRecipe> serializer;
	
	private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();
	
	private StampingRecipeBuilder(Ingredient ingredient, IItemProvider resultPrimary, int countPrimary, int processingTime, IRecipeSerializer<StampingRecipe> serializer) {
		this.ingredient = ingredient;
		this.resultPrimary = resultPrimary.asItem();
		this.countPrimary = countPrimary;
		this.processingTime = processingTime;
		this.serializer = serializer;
	}
	
	
	
	public static StampingRecipeBuilder stampingRecipe(Ingredient ingredient, IItemProvider resultPrimary, int countPrimary, int processingTime) {
		return new StampingRecipeBuilder(ingredient, resultPrimary, countPrimary, processingTime, NEMRecipes.STAMPING.get());
	}
	
	
	
	public static StampingRecipeBuilder stampingRecipe(Ingredient ingredient, IItemProvider resultPrimary, int processingTime) {
		return new StampingRecipeBuilder(ingredient, resultPrimary, 1, processingTime, NEMRecipes.STAMPING.get());
	}
	
	
	
	/**
	 * Sets the group of this recipe
	 */
	public StampingRecipeBuilder setGroup(String groupIn) {
		this.group = groupIn;
		return this;
	}
	
	
	
	/**
	 * Adds a secondary result of this recipe
	 */
	public StampingRecipeBuilder addSecondaryResult(IItemProvider result, int count) {
		this.resultSecondary = result.asItem();
		this.countSecondary = count;
		return this;
	}
	
	
	
	public StampingRecipeBuilder addSecondaryResult(IItemProvider result) {
		return addSecondaryResult(result, 1);
	}
	
	
	
	/**
	 * Adds a criterion needed to unlock the recipe
	 */
	public StampingRecipeBuilder addCriterion(String name, ICriterionInstance criterionIn) {
		this.advancementBuilder.withCriterion(name, criterionIn);
		return this;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void build(Consumer<IFinishedRecipe> consumer) {
		this.build(consumer, Registry.ITEM.getKey(this.resultPrimary));
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void build(Consumer<IFinishedRecipe> consumer, String save) {
		ResourceLocation location = Registry.ITEM.getKey(this.resultPrimary);
		ResourceLocation saveLocation = new ResourceLocation(save);
		if (saveLocation.equals(location)) {
			throw new IllegalStateException("Stamping Recipe '" + saveLocation + "' should remove its 'save' argument");
		}
		else {
			this.build(consumer, saveLocation);
		}
	}



	public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation id) {
		validate(id);
//		this.advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", new RecipeUnlockedTrigger.Instance(id)).withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);
		consumer.accept(new StampingRecipeBuilder.Result(id, group == null ? "nem" : group, ingredient, resultPrimary, countPrimary, resultSecondary, countSecondary, processingTime, advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + this.resultPrimary.getGroup().getPath() + "/" + id.getPath()), serializer));
	}
	
	

	private void validate(ResourceLocation id) {
//		if (this.advancementBuilder.getCriteria().isEmpty()) {
//			throw new IllegalStateException("No way of obtaining recipe " + id);
//		}
	}

	
	
	public class Result implements IFinishedRecipe {
		
        private final ResourceLocation id;
        private final String group;
        private final Ingredient ingredient;
        private final Item resultPrimary;
        private final int countPrimary;
    	private final Item resultSecondary;
    	private final int countSecondary;
        private final int processingTime;
//        private final Advancement.Builder advancementBuilder;
//        private final ResourceLocation advancementId;
        private final IRecipeSerializer<StampingRecipe> serializer;
        
        public Result(ResourceLocation id, String group, Ingredient ingredient, Item resultPrimary, int countPrimary, @Nullable Item resultSecondary, int countSecondary, int processingTime, Advancement.Builder advancementBuilder, ResourceLocation advancementId, IRecipeSerializer<StampingRecipe> serializer) {
        	this.id = id;
        	this.group = group;
        	this.ingredient = ingredient;
        	this.resultPrimary = resultPrimary;
        	this.countPrimary = countPrimary;
        	this.resultSecondary = resultSecondary;
        	this.countSecondary = countSecondary;
        	this.processingTime = processingTime;
//        	this.advancementBuilder = advancementBuilder;
//        	this.advancementId = advancementId;
        	this.serializer = serializer;
        }
        
        
        
		@SuppressWarnings("deprecation")
		@Override
		public void serialize(JsonObject json) {
			
			if (!this.group.isEmpty()) {
				json.addProperty("group", this.group);
			}
			
			json.add("ingredient", this.ingredient.serialize());
			
			JsonObject jsonResultPrimary = new JsonObject();
			jsonResultPrimary.addProperty("item", Registry.ITEM.getKey(this.resultPrimary).toString());
	         if (this.countPrimary > 1) {
	        	 jsonResultPrimary.addProperty("count", this.countPrimary);
	         }
	         json.add("result", jsonResultPrimary);
	         
	         if (this.resultSecondary != null) {
	        	 JsonObject jsonResultSecondary = new JsonObject();
	        	 jsonResultSecondary.addProperty("item", Registry.ITEM.getKey(this.resultSecondary).toString());
		         if (this.countSecondary > 1) {
		        	 jsonResultSecondary.addProperty("count", this.countSecondary);
		         }
		         json.add("resultsecond", jsonResultSecondary);
	         }
	         
	         json.addProperty("processtime", this.processingTime);
		}

		
		
		@Override
		public ResourceLocation getID() {
			return this.id;
		}

		
		
		@Override
		public IRecipeSerializer<?> getSerializer() {
			return this.serializer;
		}

		
		
		@Override
		public JsonObject getAdvancementJson() {
//			return this.advancementBuilder.serialize();
			return null;
		}

		
		
		@Override
		public ResourceLocation getAdvancementID() {
//			return this.advancementId;
			return null;
		}
	}
}







