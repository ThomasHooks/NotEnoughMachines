package com.github.thomashooks.notenoughmachines.integration.jei.plugins.categories;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import com.github.thomashooks.notenoughmachines.world.item.crafting.RollingRecipe;
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

public class RollingRecipeCategory implements IRecipeCategory<RollingRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(NotEnoughMachines.MOD_ID, "rolling");
    public static final ResourceLocation TEXTURE = new ResourceLocation(NotEnoughMachines.MOD_ID, "textures/gui/jei_recipes.png");
    public static final RecipeType<RollingRecipe> RECIPE_TYPE = new RecipeType<>(UID, RollingRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated progressBar;

    public RollingRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 77, 27);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AllItems.ROLLING_MILL.get()));
        this.progressBar = helper.createAnimatedDrawable(helper.createDrawable(TEXTURE, 79, 0, 15, 15), 150, IDrawableAnimated.StartDirection.TOP, false);
    }

    @Override
    public RecipeType<RollingRecipe> getRecipeType() { return RECIPE_TYPE; }

    @Override
    public Component getTitle()
    {
        return Component.translatable("jei.gui.category." + NotEnoughMachines.MOD_ID + "." + AllBlocks.getName(AllBlocks.ROLLING_MILL.get()));
    }

    @Override
    public IDrawable getBackground() { return this.background; }

    @Override
    public IDrawable getIcon() { return this.icon; }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RollingRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 6).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 56, 6).addItemStack(recipe.getResultItem(null));
    }

    @Override
    public void draw(RollingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY)
    {
        this.progressBar.draw(guiGraphics, 27, 6);
    }
}
