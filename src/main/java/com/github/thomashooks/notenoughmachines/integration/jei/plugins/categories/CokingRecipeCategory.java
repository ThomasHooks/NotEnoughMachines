package com.github.thomashooks.notenoughmachines.integration.jei.plugins.categories;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import com.github.thomashooks.notenoughmachines.world.item.crafting.CokingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class CokingRecipeCategory implements IRecipeCategory<CokingRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(NotEnoughMachines.MOD_ID, "coking");
    public static final ResourceLocation TEXTURE = new ResourceLocation(NotEnoughMachines.MOD_ID, "textures/gui/jei_recipes.png");
    public static final RecipeType<CokingRecipe> RECIPE_TYPE = new RecipeType<>(UID, CokingRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated progressBar;

    public CokingRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 0, 29, 76, 27);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AllItems.COKE_OVEN.get()));
        this.progressBar = helper.createAnimatedDrawable(helper.createDrawable(TEXTURE, 78, 29, 13, 13), 300, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public RecipeType<CokingRecipe> getRecipeType() { return RECIPE_TYPE; }

    @Override
    public Component getTitle()
    {
        return Component.translatable("jei.gui.category." + NotEnoughMachines.MOD_ID + "." + AllBlocks.getName(AllBlocks.COKE_OVEN.get()));
    }

    @Override
    public IDrawable getBackground() { return this.background; }

    @Override
    public IDrawable getIcon() { return this.icon; }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CokingRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 6).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 55, 6).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public void draw(CokingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY)
    {
        this.progressBar.draw(guiGraphics, 27, 7);
    }
}
