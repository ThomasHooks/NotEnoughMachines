package com.kilroy790.notenoughmachines.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kilroy790.notenoughmachines.items.NEMItems;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.NBTIngredient;
import net.minecraftforge.registries.ForgeRegistryEntry;




public class MillingRecipe extends AbstractMachineRecipe 
{
	public MillingRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result, int processingTime)
	{
		super(id, group, ingredient, result, processingTime);
	}
	
	
	
	@Override
	public ItemStack getIcon() 
	{
		return new ItemStack(NEMItems.MILLSTONE.get());
	}

	
	
	@Override
	public IRecipeSerializer<?> getSerializer() 
	{
		return NEMRecipes.MILLING.get();
	}

	
	
	@Override
	public IRecipeType<?> getType() 
	{
		return NEMRecipes.Types.MILLING;
	}
	
	
	
	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<MillingRecipe> 
	{
		@Override
		public MillingRecipe read(ResourceLocation recipeId, JsonObject json) 
		{
			String group = JSONUtils.getString(json, "group", "");
			
			if (!json.has("ingredient")) 
				throw new com.google.gson.JsonSyntaxException("Missing ingredient, expected to find a string or object");
			JsonElement jsonelement = (JsonElement)(JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
			Ingredient ingredient = Ingredient.deserialize(jsonelement);
		    
		    if (!json.has("result")) 
		    	throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
		    ItemStack result = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
		    
		    int time = JSONUtils.getInt(json, "processtime", 200);
		    
		    return new MillingRecipe(recipeId, group, ingredient, result, time);
		}

		
		
		@Override
		public MillingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) 
		{
			String group = buffer.readString(32767);
			NBTIngredient ingredient = NBTIngredient.Serializer.INSTANCE.parse(buffer);
			ItemStack result = buffer.readItemStack();
			int time = buffer.readVarInt();
			return new MillingRecipe(recipeId, group, ingredient, result, time);
		}

		
		
		@Override
		public void write(PacketBuffer buffer, MillingRecipe recipe) 
		{
		    buffer.writeString(recipe.group);
		    recipe.ingredient.write(buffer);
		    buffer.writeItemStack(recipe.result);
		    buffer.writeVarInt(recipe.processingTime);
		}
	}
}



