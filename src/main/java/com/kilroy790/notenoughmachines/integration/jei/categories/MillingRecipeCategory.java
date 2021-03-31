package com.kilroy790.notenoughmachines.integration.jei.categories;

import com.kilroy790.notenoughmachines.client.NEMTextures;
import com.kilroy790.notenoughmachines.integration.jei.NEMPlugin;
import com.kilroy790.notenoughmachines.items.NEMItems;
import com.kilroy790.notenoughmachines.recipes.MillingRecipe;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;




public class MillingRecipeCategory extends NEMRecipeCategory<MillingRecipe> 
{
	private final int INPUT_SLOT = 0;
	private final int OUPUT_SLOT = 1;
	private final IDrawableAnimated gear;
	
	public MillingRecipeCategory(IGuiHelper guiHelper) 
	{
		super(NEMPlugin.MILLING_CATEGORY_UID, guiHelper.drawableBuilder(NEMTextures.JEI_RECIPES_GUI, 0, 0, 57, 19).build(), guiHelper.createDrawableIngredient(new ItemStack(NEMItems.MILLSTONE.get())));
		this.gear = guiHelper.createAnimatedDrawable(guiHelper.createDrawable(NEMTextures.JEI_RECIPES_GUI, 80, 0, 15, 15), 150, IDrawableAnimated.StartDirection.TOP, false);
	}

	
	
	@Override
	public void setIngredients(MillingRecipe recipe, IIngredients ingredients) 
	{
		ingredients.setInputIngredients(recipe.getIngredients());
		
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
	}

	
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, MillingRecipe recipe, IIngredients ingredients) 
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(INPUT_SLOT, true, 1, 1);
		guiItemStacks.init(OUPUT_SLOT, false, 39, 1);
		guiItemStacks.set(ingredients);
	}
	
	
	
	@Override
	public void draw(MillingRecipe recipe, double mouseX, double mouseY) 
	{
		this.gear.draw(22, 2);
	}

	
	
	@Override
	public Class<? extends MillingRecipe> getRecipeClass() 
	{
		return MillingRecipe.class;
	}
}



