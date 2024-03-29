package com.github.thomashooks.notenoughmachines.world.item.crafting;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractMachineRecipe implements Recipe<SimpleContainer>
{
    protected final ResourceLocation id;
    protected final String group;
    protected final Ingredient ingredients;
    protected final ItemStack results;
    protected final int processingTime;

    protected AbstractMachineRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientsIn, ItemStack resultsIn, int processingTimeIn)
    {
        this.id = idIn;
        this.group = groupIn;
        this.ingredients = ingredientsIn;
        this.results = resultsIn;
        this.processingTime = processingTimeIn;
    }

    @Override
    public boolean matches(SimpleContainer container, Level world)
    {
        if (world.isClientSide())
            return false;
        //By default, we assume there is only 1 input slot and its index is 0
        int inputSlotIndex = 0;
        return this.ingredients.test(container.getItem(inputSlotIndex));
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients()
    {
        return NonNullList.withSize(1, this.ingredients);
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SimpleContainer container, @NotNull RegistryAccess registryAccess) { return this.results.copy(); }

    @Override
    public boolean canCraftInDimensions(int width, int height) { return true; }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess registryAccess) { return this.results.copy(); }

    public int getProcessingTime() { return this.processingTime; }

    @Override
    public @NotNull ResourceLocation getId() { return this.id; }

    @Override
    public @NotNull String getGroup() { return this.group; }

    @Override
    public abstract @NotNull ItemStack getToastSymbol();

    @Override
    public abstract @NotNull RecipeSerializer<?> getSerializer();

    @Override
    public abstract @NotNull RecipeType<?> getType();
}
