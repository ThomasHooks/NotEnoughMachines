package com.kilroy790.notenoughmachines.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;




public abstract class AbstractMachineRecipe implements IRecipe<RecipeWrapper> 
{
	protected final ResourceLocation id;
	protected final String group;
	protected final Ingredient ingredient;
	protected final ItemStack result;
	protected final int processingTime;
	
	public AbstractMachineRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, int processingTimeIn)
	{
		this.id = idIn;
		this.group = groupIn;
		this.ingredient = ingredientIn;
		this.result = resultIn;
		this.processingTime = processingTimeIn;
	}

	
	
	@Override
	public boolean matches(RecipeWrapper inv, World worldIn) 
	{
		return this.ingredient.test(inv.getStackInSlot(0));
	}

	
	
	@Override
	public ItemStack getCraftingResult(RecipeWrapper inv) 
	{
		return this.result.copy();
	}

	
	
	@Override
	public boolean canFit(int width, int height) 
	{
		return true;
	}

	
	
	@Override
	public ItemStack getRecipeOutput() 
	{
		return this.result;
	}

	
	
	@Override
	public ResourceLocation getId() 
	{
		return this.id;
	}
	
	
	
	@Override
	public String getGroup() 
	{
		return this.group;
	}
	
	
	
	public int getProcessingTime() 
	{
		return this.processingTime;
	}
	
	
	
	@Override
	public NonNullList<Ingredient> getIngredients() 
	{
		return NonNullList.withSize(1, this.ingredient);
	}
	
	
	
	@Override
	abstract public ItemStack getIcon();

	
	
	@Override
	abstract public IRecipeSerializer<?> getSerializer();

	
	
	@Override
	abstract public IRecipeType<?> getType();
}



