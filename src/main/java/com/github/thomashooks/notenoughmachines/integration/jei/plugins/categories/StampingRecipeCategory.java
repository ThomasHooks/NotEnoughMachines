package com.github.thomashooks.notenoughmachines.integration.jei.plugins.categories;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import com.github.thomashooks.notenoughmachines.world.item.crafting.StampingRecipe;
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

public class StampingRecipeCategory implements IRecipeCategory<StampingRecipe>
{
    public static final ResourceLocation UID = new ResourceLocation(NotEnoughMachines.MOD_ID, "stamping");
    public static final ResourceLocation TEXTURE = new ResourceLocation(NotEnoughMachines.MOD_ID, "textures/gui/jei_recipes.png");
    public static final RecipeType<StampingRecipe> RECIPE_TYPE = new RecipeType<>(UID, StampingRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated progressBar;

    public StampingRecipeCategory(IGuiHelper helper)
    {
        this.background = helper.createDrawable(TEXTURE, 0, 58, 103, 27);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AllItems.TRIP_HAMMER.get()));
        this.progressBar = helper.createAnimatedDrawable(helper.createDrawable(TEXTURE, 105, 58, 15, 15), 150, IDrawableAnimated.StartDirection.TOP, false);
    }

    @Override
    public RecipeType<StampingRecipe> getRecipeType()  { return RECIPE_TYPE; }

    @Override
    public Component getTitle()
    {
        return Component.translatable("jei.gui.category." + NotEnoughMachines.MOD_ID + "." + AllBlocks.getName(AllBlocks.TRIP_HAMMER.get()));
    }

    @Override
    public IDrawable getBackground() { return this.background; }

    @Override
    public IDrawable getIcon() { return this.icon; }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, StampingRecipe recipe, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 6).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 56, 6).addItemStack(recipe.getResultItem(null));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 82, 6).addItemStack(recipe.getSecondaryResultItem());
    }

    @Override
    public void draw(StampingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY)
    {
        this.progressBar.draw(guiGraphics, 27, 6);
    }
}
