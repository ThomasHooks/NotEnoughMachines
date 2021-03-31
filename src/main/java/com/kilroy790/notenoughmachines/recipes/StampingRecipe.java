package com.kilroy790.notenoughmachines.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kilroy790.notenoughmachines.items.NEMItems;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.NBTIngredient;
import net.minecraftforge.registries.ForgeRegistryEntry;




public class StampingRecipe extends AbstractMachineRecipe
{
	protected final ItemStack resultSecondary;	
	
	public StampingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, ItemStack resultSecondary, int processingTime) 
	{
		super(id, group, ingredient, result, processingTime);
		this.resultSecondary = resultSecondary;
	}
	
	
	
	@Override
	public ItemStack getIcon() 
	{
		return new ItemStack(NEMItems.TRIPHAMMER.get());
	}

	
	
	@Override
	public IRecipeSerializer<?> getSerializer() 
	{
		return NEMRecipes.STAMPING.get();
	}

	
	
	@Override
	public IRecipeType<?> getType() 
	{
		return NEMRecipes.Types.STAMPING;
	}
	
	
	
	public ItemStack getSecondaryCraftingResult() 
	{
		return this.resultSecondary.copy();
	}
	
	
	
	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<StampingRecipe> 
	{
		@Override
		public StampingRecipe read(ResourceLocation recipeId, JsonObject json) 
		{
			String group = JSONUtils.getString(json, "group", "");
			
			if (!json.has("ingredient")) 
				throw new com.google.gson.JsonSyntaxException("Missing ingredient, expected to find a string or object");
			JsonElement jsonelement = (JsonElement)(JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
			Ingredient ingredient = Ingredient.deserialize(jsonelement);
		    
		    if (!json.has("result")) 
		    	throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
		    ItemStack result = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
		    
		    ItemStack result2 = json.has("resultsecond") ? ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "resultsecond")) : new ItemStack(Items.AIR);
		    
		    int time = JSONUtils.getInt(json, "processtime", 300);
		    
		    return new StampingRecipe(recipeId, group, ingredient, result, result2, time);
		}

		
		
		@Override
		public StampingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) 
		{
			String group = buffer.readString(32767);
			NBTIngredient ingredient = NBTIngredient.Serializer.INSTANCE.parse(buffer);
			ItemStack result = buffer.readItemStack();
			ItemStack result2 = buffer.readItemStack();
			int time = buffer.readVarInt();
			return new StampingRecipe(recipeId, group, ingredient, result, result2, time);
		}

		
		
		@Override
		public void write(PacketBuffer buffer, StampingRecipe recipe) 
		{
		    buffer.writeString(recipe.group);
		    recipe.ingredient.write(buffer);
		    buffer.writeItemStack(recipe.result);
		    buffer.writeItemStack(recipe.resultSecondary);
		    buffer.writeVarInt(recipe.processingTime);
		}
	}
}







