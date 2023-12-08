package com.github.thomashooks.notenoughmachines.integration.jei.plugins;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.client.gui.screens.CokeOvenScreen;
import com.github.thomashooks.notenoughmachines.client.gui.screens.MillstoneScreen;
import com.github.thomashooks.notenoughmachines.client.gui.screens.RollingMillScreen;
import com.github.thomashooks.notenoughmachines.client.gui.screens.TripHammerScreen;
import com.github.thomashooks.notenoughmachines.integration.jei.plugins.categories.CokingRecipeCategory;
import com.github.thomashooks.notenoughmachines.integration.jei.plugins.categories.MillingRecipeCategory;
import com.github.thomashooks.notenoughmachines.integration.jei.plugins.categories.RollingRecipeCategory;
import com.github.thomashooks.notenoughmachines.integration.jei.plugins.categories.StampingRecipeCategory;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import com.github.thomashooks.notenoughmachines.world.item.crafting.CokingRecipe;
import com.github.thomashooks.notenoughmachines.world.item.crafting.MillingRecipe;
import com.github.thomashooks.notenoughmachines.world.item.crafting.RollingRecipe;
import com.github.thomashooks.notenoughmachines.world.item.crafting.StampingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class NotEnoughMachinesModPlugin implements IModPlugin
{
    private static final ResourceLocation UID = new ResourceLocation(NotEnoughMachines.MOD_ID, "jei_plugin");
    @Override
    public ResourceLocation getPluginUid() { return UID; }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();

        registration.addRecipeCategories(new CokingRecipeCategory(guiHelper));
        registration.addRecipeCategories(new MillingRecipeCategory(guiHelper));
        registration.addRecipeCategories(new RollingRecipeCategory(guiHelper));
        registration.addRecipeCategories(new StampingRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();

        List<CokingRecipe> cokingRecipes = manager.getAllRecipesFor(CokingRecipe.Type.COKING);
        registration.addRecipes(CokingRecipeCategory.RECIPE_TYPE, cokingRecipes);

        List<MillingRecipe> millingRecipes = manager.getAllRecipesFor(MillingRecipe.Type.MILLING);
        registration.addRecipes(MillingRecipeCategory.RECIPE_TYPE, millingRecipes);

        List<RollingRecipe> rollingRecipes = manager.getAllRecipesFor(RollingRecipe.Type.ROLLING);
        registration.addRecipes(RollingRecipeCategory.RECIPE_TYPE, rollingRecipes);

        List<StampingRecipe> stampingRecipes = manager.getAllRecipesFor(StampingRecipe.Type.STAMPING);
        registration.addRecipes(StampingRecipeCategory.RECIPE_TYPE, stampingRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(new ItemStack(AllItems.COKE_OVEN.get()), CokingRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(AllItems.MILLSTONE.get()), MillingRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(AllItems.ROLLING_MILL.get()), RollingRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(AllItems.TRIP_HAMMER.get()), StampingRecipeCategory.RECIPE_TYPE);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration)
    {
        registration.addRecipeClickArea(CokeOvenScreen.class, 83, 19,13, 13, CokingRecipeCategory.RECIPE_TYPE);
        registration.addRecipeClickArea(MillstoneScreen.class, 80, 17,15, 15, MillingRecipeCategory.RECIPE_TYPE);
        registration.addRecipeClickArea(RollingMillScreen.class, 80, 17,15, 15, RollingRecipeCategory.RECIPE_TYPE);
        registration.addRecipeClickArea(TripHammerScreen.class, 70, 20,15, 15, StampingRecipeCategory.RECIPE_TYPE);
    }
}
