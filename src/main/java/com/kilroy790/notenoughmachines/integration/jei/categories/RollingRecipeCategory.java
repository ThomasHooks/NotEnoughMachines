package com.kilroy790.notenoughmachines.integration.jei.categories;

import com.kilroy790.notenoughmachines.client.NEMTextures;
import com.kilroy790.notenoughmachines.integration.jei.NEMPlugin;
import com.kilroy790.notenoughmachines.items.NEMItems;
import com.kilroy790.notenoughmachines.recipes.RollingRecipe;
import com.mojang.blaze3d.matrix.MatrixStack;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;




public class RollingRecipeCategory extends NEMRecipeCategory<RollingRecipe>
{
	private final int INPUT_SLOT = 0;
	private final int OUPUT_SLOT = 1;
	private final IDrawableAnimated gear;
	
	
	
	public RollingRecipeCategory(IGuiHelper guiHelper) 
	{
		super(NEMPlugin.ROLLING_CATEGORY_UID, guiHelper.drawableBuilder(NEMTextures.JEI_RECIPES_GUI, 0, 0, 57, 19).build(), guiHelper.createDrawableIngredient(new ItemStack(NEMItems.ROLLING_MILL.get())));
		this.gear = guiHelper.createAnimatedDrawable(guiHelper.createDrawable(NEMTextures.JEI_RECIPES_GUI, 80, 0, 15, 15), 150, IDrawableAnimated.StartDirection.TOP, false);
	}

	
	
	@Override
	public void setIngredients(RollingRecipe recipe, IIngredients ingredients) 
	{
		ingredients.setInputIngredients(recipe.getIngredients());
		
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
	}

	
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, RollingRecipe recipe, IIngredients ingredients) 
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(INPUT_SLOT, true, 1, 1);
		guiItemStacks.init(OUPUT_SLOT, false, 39, 1);
		guiItemStacks.set(ingredients);
	}
	
	
	
	@Override
	public void draw(RollingRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) 
	{
		this.gear.draw(matrixStack, 22, 2);
	}

	
	
	@Override
	public Class<? extends RollingRecipe> getRecipeClass() 
	{
		return RollingRecipe.class;
	}
}



