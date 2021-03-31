package com.kilroy790.notenoughmachines.data.recipes;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import com.kilroy790.notenoughmachines.recipes.MillingRecipe;
import com.kilroy790.notenoughmachines.recipes.NEMRecipes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;




public class MillingRecipeBuilder 
{
	private final Ingredient ingredient;
	private final Item result;
	private final int count;
	private final int processingTime;
	private String group;
	private final IRecipeSerializer<MillingRecipe> serializer;
	
	private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();
	
	private MillingRecipeBuilder(Ingredient ingredient, IItemProvider result, int count, int processingTime, IRecipeSerializer<MillingRecipe> serializer) 
	{
		this.ingredient = ingredient;
		this.result = result.asItem();
		this.count = count;
		this.processingTime = processingTime;
		this.serializer = serializer;
	}
	
	
	
	public static MillingRecipeBuilder millingRecipe(Ingredient ingredient, IItemProvider result, int count, int processingTime) 
	{
		return new MillingRecipeBuilder(ingredient, result, count, processingTime, NEMRecipes.MILLING.get());
	}
	
	
	
	public static MillingRecipeBuilder millingRecipe(Ingredient ingredient, IItemProvider resultPrimary, int processingTime) 
	{
		return new MillingRecipeBuilder(ingredient, resultPrimary, 1, processingTime, NEMRecipes.MILLING.get());
	}
	
	
	
	/**
	 * Sets the group of this recipe
	 */
	public MillingRecipeBuilder setGroup(String groupIn) 
	{
		this.group = groupIn;
		return this;
	}
	
	
	
	/**
	 * Adds a criterion needed to unlock the recipe
	 */
	public MillingRecipeBuilder addCriterion(String name, ICriterionInstance criterionIn) 
	{
		this.advancementBuilder.withCriterion(name, criterionIn);
		return this;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void build(Consumer<IFinishedRecipe> consumer) 
	{
		this.build(consumer, Registry.ITEM.getKey(this.result));
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void build(Consumer<IFinishedRecipe> consumer, String save) 
	{
		ResourceLocation location = Registry.ITEM.getKey(this.result);
		ResourceLocation saveLocation = new ResourceLocation(save);
		if (saveLocation.equals(location)) 
			throw new IllegalStateException("Milling Recipe '" + saveLocation + "' should remove its 'save' argument");
		else 
			this.build(consumer, saveLocation);
	}



	public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation id) 
	{
		validate(id);
//		this.advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", new RecipeUnlockedTrigger.Instance(id)).withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);
		consumer.accept(new MillingRecipeBuilder.Result(id, group == null ? "nem" : group, ingredient, result, count, processingTime, advancementBuilder, new ResourceLocation(id.getNamespace(), "recipes/" + this.result.getGroup().getPath() + "/" + id.getPath()), serializer));
	}
	
	

	private void validate(ResourceLocation id) 
	{
//		if (this.advancementBuilder.getCriteria().isEmpty()) 
//			throw new IllegalStateException("No way of obtaining recipe " + id);
	}

	
	
	public class Result implements IFinishedRecipe 
	{
        private final ResourceLocation id;
        private final String group;
        private final Ingredient ingredient;
        private final Item result;
        private final int count;
        private final int processingTime;
        private final IRecipeSerializer<MillingRecipe> serializer;
        
        public Result(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, Item resultIn, int countIn, int processingTimeIn, Advancement.Builder advancementBuilderIn, ResourceLocation advancementIdIn, IRecipeSerializer<MillingRecipe> serializerIn) 
        {
        	this.id = idIn;
        	this.group = groupIn;
        	this.ingredient = ingredientIn;
        	this.result = resultIn;
        	this.count = countIn;
        	this.processingTime = processingTimeIn;
        	this.serializer = serializerIn;
        }
        
        
        
		@SuppressWarnings("deprecation")
		@Override
		public void serialize(JsonObject json) 
		{
			
			if (!this.group.isEmpty()) 
				json.addProperty("group", this.group);
			
			json.add("ingredient", this.ingredient.serialize());
			
			JsonObject jsonResult = new JsonObject();
			jsonResult.addProperty("item", Registry.ITEM.getKey(this.result).toString());
	         if (this.count > 1) 
	        	 jsonResult.addProperty("count", this.count);
	         json.add("result", jsonResult);
	         
	         json.addProperty("processtime", this.processingTime);
		}

		
		
		@Override
		public ResourceLocation getID() 
		{
			return this.id;
		}

		
		
		@Override
		public IRecipeSerializer<?> getSerializer() 
		{
			return this.serializer;
		}

		
		
		@Override
		public JsonObject getAdvancementJson() 
		{
			return null;
		}

		
		
		@Override
		public ResourceLocation getAdvancementID() 
		{
			return null;
		}
	}
}



