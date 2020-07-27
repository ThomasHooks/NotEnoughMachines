package com.kilroy790.notenoughmachines.recipes;

import com.google.gson.JsonObject;
import com.kilroy790.notenoughmachines.setup.NEMItems;
import com.kilroy790.notenoughmachines.setup.NEMRecipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.NBTIngredient;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import net.minecraftforge.registries.ForgeRegistryEntry;




public class StampingRecipe implements IRecipe<RecipeWrapper> {
	
	private final ResourceLocation id;
	private final String group;
	private final NBTIngredient ingredient;
	private final ItemStack result;
	private final ItemStack resultSecondary;
	private final int processingTime;
	
	
	
	public StampingRecipe(ResourceLocation id, String group, NBTIngredient ingredient, ItemStack result, ItemStack resultSecondary, int processingTime) {
		this.id = id;
		this.group = group;
		this.ingredient = ingredient;
		this.result = result;
		this.resultSecondary = resultSecondary;
		this.processingTime = processingTime;
	}

	
	
	@Override
	public boolean matches(RecipeWrapper inv, World worldIn) {
		return this.ingredient.test(inv.getStackInSlot(0));
	}

	
	
	@Override
	public ItemStack getCraftingResult(RecipeWrapper inv) {
		return this.result.copy();
	}

	
	
	@Override
	public boolean canFit(int width, int height) {
		return true;
	}

	
	
	@Override
	public ItemStack getRecipeOutput() {
		return this.result;
	}
	
	
	
	public ItemStack getCraftingResultSecondary() {
		return this.resultSecondary.copy();
	}

	
	
	@Override
	public ResourceLocation getId() {
		return this.id;
	}
	
	
	
	@Override
	public String getGroup() {
		return this.group;
	}
	
	
	
	public int getProcessingTime() {
		return this.processingTime;
	}
	
	
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return NonNullList.withSize(1, this.ingredient);
	}
	
	
	
	@Override
	public ItemStack getIcon() {
		return new ItemStack(NEMItems.TRIPHAMMER.get());
	}

	
	
	@Override
	public IRecipeSerializer<?> getSerializer() {
		return NEMRecipes.STAMPING.get();
	}

	
	
	@Override
	public IRecipeType<?> getType() {
		return NEMRecipes.Types.STAMPING;
	}
	
	
	
	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<StampingRecipe> {

		@Override
		public StampingRecipe read(ResourceLocation recipeId, JsonObject json) {
			
			String group = JSONUtils.getString(json, "group", "");
			
			if (!json.has("ingredient")) throw new com.google.gson.JsonSyntaxException("Missing ingredient, expected to find a string or object");
		    NBTIngredient ingredient = NBTIngredient.Serializer.INSTANCE.parse(JSONUtils.getJsonObject(json, "ingredient"));
		    
		    if (!json.has("result")) throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
		    ItemStack result = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
		    
		    ItemStack result2 = json.has("resultsecond") ? ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "resultsecond")) : new ItemStack(Items.AIR);
		    
		    int time = JSONUtils.getInt(json, "processtime", 300);
		    
		    return new StampingRecipe(recipeId, group, ingredient, result, result2, time);
		}

		
		
		@Override
		public StampingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
			String group = buffer.readString(32767);
			NBTIngredient ingredient = NBTIngredient.Serializer.INSTANCE.parse(buffer);
			ItemStack result = buffer.readItemStack();
			ItemStack result2 = buffer.readItemStack();
			int time = buffer.readVarInt();
			return new StampingRecipe(recipeId, group, ingredient, result, result2, time);
		}

		
		
		@Override
		public void write(PacketBuffer buffer, StampingRecipe recipe) {
		    buffer.writeString(recipe.group);
		    recipe.ingredient.write(buffer);
		    buffer.writeItemStack(recipe.result);
		    buffer.writeItemStack(recipe.resultSecondary);
		    buffer.writeVarInt(recipe.processingTime);
		}
	}
}







