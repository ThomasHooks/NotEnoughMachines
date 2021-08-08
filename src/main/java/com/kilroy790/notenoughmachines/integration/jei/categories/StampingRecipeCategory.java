package com.kilroy790.notenoughmachines.integration.jei.categories;

import java.util.ArrayList;
import java.util.List;

import com.kilroy790.notenoughmachines.client.NEMTextures;
import com.kilroy790.notenoughmachines.integration.jei.NEMPlugin;
import com.kilroy790.notenoughmachines.items.NEMItems;
import com.kilroy790.notenoughmachines.recipes.StampingRecipe;
import com.mojang.blaze3d.matrix.MatrixStack;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;




public class StampingRecipeCategory extends NEMRecipeCategory<StampingRecipe> 
{
	private final int INPUT_SLOT = 0;
	private final int OUPUT_SLOT_PRIMARY = 1;
	private final int OUPUT_SLOT_SECONDARY = 2;
	private final IDrawableAnimated gear;
	
	public StampingRecipeCategory(IGuiHelper guiHelper) 
	{
		super(NEMPlugin.STAMPING_CATEGORY_UID, guiHelper.drawableBuilder(NEMTextures.JEI_RECIPES_GUI, 0, 21, 75, 19).build(), guiHelper.createDrawableIngredient(new ItemStack(NEMItems.TRIPHAMMER.get())));
		this.gear = guiHelper.createAnimatedDrawable(guiHelper.createDrawable(NEMTextures.JEI_RECIPES_GUI, 80, 0, 15, 15), 150, IDrawableAnimated.StartDirection.TOP, false);
	}

	
	
	@Override
	public Class<? extends StampingRecipe> getRecipeClass() 
	{
		return StampingRecipe.class;
	}

	
	
	@Override
	public void setIngredients(StampingRecipe recipe, IIngredients ingredients) 
	{
		ingredients.setInputIngredients(recipe.getIngredients());
		
		List<ItemStack> outputs = new ArrayList<ItemStack>();
		outputs.add(recipe.getRecipeOutput());
		
		if (!recipe.getSecondaryCraftingResult().isEmpty())
			outputs.add(recipe.getSecondaryCraftingResult());
		
		ingredients.setOutputs(VanillaTypes.ITEM, outputs);
	}

	
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, StampingRecipe recipe, IIngredients ingredients) 
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(INPUT_SLOT, true, 1, 1);
		guiItemStacks.init(OUPUT_SLOT_PRIMARY, false, 39, 1);
		guiItemStacks.init(OUPUT_SLOT_SECONDARY, false, 57, 1);
		guiItemStacks.set(ingredients);
	}
	
	
	
	@Override
	public void draw(StampingRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) 
	{
		this.gear.draw(matrixStack, 22, 2);
	}
}



