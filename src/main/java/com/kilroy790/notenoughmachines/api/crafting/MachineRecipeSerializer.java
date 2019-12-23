package com.kilroy790.notenoughmachines.api.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kilroy790.notenoughmachines.NotEnoughMachines;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class MachineRecipeSerializer<T extends AbstractMachineRecipe> extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T>{

	private final int processTime;
	private final MachineRecipeSerializer.IFactory<T> IAbstractMachineRecipeFactory;
	   
	   
	public MachineRecipeSerializer(MachineRecipeSerializer.IFactory<T> factoryIn, int processTimeIn) {
		this.processTime = processTimeIn;
	    this.IAbstractMachineRecipeFactory = factoryIn;
	    this.setRegistryName(NotEnoughMachines.modid, "milling");
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public T read(ResourceLocation recipeId, JsonObject json) {
		
		String s = JSONUtils.getString(json, "group", "");
	    JsonElement jsonelement = (JsonElement)(JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
	    Ingredient ingredient = Ingredient.deserialize(jsonelement);
	    
	    //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
	    if (!json.has("result")) throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
	    
	    ItemStack itemstack;
	    
	    if (json.get("result").isJsonObject()) itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
	    else {
	    	String s1 = JSONUtils.getString(json, "result");
	    	ResourceLocation resourcelocation = new ResourceLocation(s1);
	    	itemstack = new ItemStack(Registry.ITEM.getValue(resourcelocation).orElseThrow(() -> {
	    		return new IllegalStateException("Item: " + s1 + " does not exist");
	    	}));
	    }
	    
	    float f = JSONUtils.getFloat(json, "experience", 0.0F);
	    int i = JSONUtils.getInt(json, "processtime", this.processTime);
	    return this.IAbstractMachineRecipeFactory.create(recipeId, s, ingredient, itemstack, f, i);
	}

	
	@Override
	public T read(ResourceLocation recipeId, PacketBuffer buffer) {
		
		String s = buffer.readString(32767);
	    Ingredient ingredient = Ingredient.read(buffer);
	    ItemStack itemstack = buffer.readItemStack();
	    float f = buffer.readFloat();
	    int i = buffer.readVarInt();
	    
	    return this.IAbstractMachineRecipeFactory.create(recipeId, s, ingredient, itemstack, f, i);
	}

	
	@Override
	public void write(PacketBuffer buffer, T recipe) {
		
	    buffer.writeString(recipe.group);
	    recipe.ingredient.write(buffer);
	    buffer.writeItemStack(recipe.result);
	    buffer.writeFloat(recipe.experience);
	    buffer.writeVarInt(recipe.processTime);
	}

	
	public interface IFactory<T extends AbstractMachineRecipe> {
		
		T create(ResourceLocation resourceLocation, String s, Ingredient ingredient, ItemStack itemStack, float experienceIn, int processTimeIn);
	}
}
