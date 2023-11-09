package com.kilroy790.notenoughmachines.data.recipes;

import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;
import com.kilroy790.notenoughmachines.recipes.AbstractMachineRecipe;
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



//TODO: Add advancements
public class SingleResultMachineRecipeBuilder 
{
	private final Ingredient ingredient;
	private final Item result;
	private final int count;
	private final int processingTime;
	private String group;
	private final IRecipeSerializer<? extends AbstractMachineRecipe> serializer;
	private final Advancement.Builder advancementBuilder = Advancement.Builder.builder();
	
	
	
	private SingleResultMachineRecipeBuilder(Ingredient ingredient, IItemProvider result, int count, int processingTime, IRecipeSerializer<? extends AbstractMachineRecipe> serializerIn)
	{
		this.ingredient = ingredient;
		this.result = result.asItem();
		this.count = count;
		this.processingTime = processingTime;
		this.serializer = serializerIn;
	}



	/**
	 * Creates a new milling recipe
	 * 
	 * @param ingredient specifies the item that the recipe crafts with
	 * @param result specifies the item that is produced from this recipe
	 * @param count specifies the number of items that are produced from this recipe
	 * @param processingTime specifies the amount of time needed to craft this recipe
	 */
	public static SingleResultMachineRecipeBuilder millingRecipe(Ingredient ingredient, IItemProvider result, int count, int processingTime)
	{
		return new SingleResultMachineRecipeBuilder(ingredient, result, count, processingTime, NEMRecipes.MILLING.get());
	}



	/**
	 * Creates a new milling recipe that produces one item
	 * 
	 * @param ingredient specifies the item that the recipe crafts with
	 * @param result specifies the item that is produced from this recipe
	 * @param processingTime specifies the amount of time needed to craft this recipe
	 */
	public static SingleResultMachineRecipeBuilder millingRecipe(Ingredient ingredient, IItemProvider result, int processingTime)
	{
		return new SingleResultMachineRecipeBuilder(ingredient, result, 1, processingTime, NEMRecipes.MILLING.get());
	}



	/**
	 * Creates a new rolling recipe
	 * 
	 * @param ingredient specifies the item that the recipe crafts with
	 * @param result specifies the item that is produced from this recipe
	 * @param count specifies the number of items that are produced from this recipe
	 * @param processingTime specifies the amount of time needed to craft this recipe
	 */
	public static SingleResultMachineRecipeBuilder rollingRecipe(Ingredient ingredient, IItemProvider result, int count, int processingTime) 
	{
		return new SingleResultMachineRecipeBuilder(ingredient, result, count, processingTime, NEMRecipes.ROLLING.get());
	}



	/**
	 * Creates a new rolling recipe that produces one item
	 * 
	 * @param ingredient specifies the item that the recipe crafts with
	 * @param result specifies the item that is produced from this recipe
	 * @param processingTime specifies the amount of time needed to craft this recipe
	 */
	public static SingleResultMachineRecipeBuilder rollingRecipe(Ingredient ingredient, IItemProvider result, int processingTime) 
	{
		return new SingleResultMachineRecipeBuilder(ingredient, result, 1, processingTime, NEMRecipes.ROLLING.get());
	}



	/**
	 * Sets the item group of this machine crafting recipe
	 * 
	 * @param groupIn specifics the name for this 
	 */
	public SingleResultMachineRecipeBuilder setGroup(String groupIn)
	{
		this.group = groupIn;
		return this;
	}



	/**
	 * Adds a criterion needed to unlock the recipe
	 */
	public SingleResultMachineRecipeBuilder addCriterion(String name, ICriterionInstance criterionIn) 
	{
		this.advancementBuilder.withCriterion(name, criterionIn);
		return this;
	}



	protected void validate(ResourceLocation id) 
	{
		if (this.advancementBuilder.getCriteria().isEmpty()) 
			throw new IllegalStateException("No way of obtaining recipe " + id);
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
			throw new IllegalStateException("Machine recipe '" + saveLocation + "' should remove its 'save' argument as it matches the default save location");
		else 
			this.build(consumer, saveLocation);
	}
	
	
	
	public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation id) 
	{
		//validate(id);
		//this.advancementBuilder.withParentId(new ResourceLocation("recipes/root")).withCriterion("has_the_recipe", new RecipeUnlockedTrigger.Instance(id)).withRewards(AdvancementRewards.Builder.recipe(id)).withRequirementsStrategy(IRequirementsStrategy.OR);
		consumer.accept(new SingleResultMachineRecipeBuilder.Result(
				id, 
				group == null ? "nem" : group, 
				ingredient, 
				result, 
				count, 
				processingTime, 
				advancementBuilder, 
				new ResourceLocation(id.getNamespace(), "recipes/" + this.result.getGroup().getPath() + "/" + id.getPath()), serializer));
	}



	public class Result implements IFinishedRecipe 
	{
		private final ResourceLocation id;
		private final String group;
		private final Ingredient ingredient;
		private final Item result;
		private final int count;
		private final int processingTime;
		private final IRecipeSerializer<? extends AbstractMachineRecipe> serializer;



		public Result(ResourceLocation idIn, 
				String groupIn, 
				Ingredient ingredientIn, 
				Item resultIn, 
				int countIn, 
				int processingTimeIn, 
				Advancement.Builder advancementBuilderIn, 
				ResourceLocation advancementIdIn, 
				IRecipeSerializer<? extends AbstractMachineRecipe> serializerIn) 
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
		@Nullable
		public JsonObject getAdvancementJson() 
		{
			return null;
		}



		@Override
		@Nullable
		public ResourceLocation getAdvancementID() 
		{
			return null;
		}
	}
}



