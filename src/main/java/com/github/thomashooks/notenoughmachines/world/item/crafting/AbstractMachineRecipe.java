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

public abstract class AbstractMachineRecipe implements Recipe<SimpleContainer>
{
    protected final ResourceLocation id;
    protected final String group;
    protected final NonNullList<Ingredient> ingredients;
    protected final NonNullList<ItemStack> results;
    protected final int processingTime;

    protected AbstractMachineRecipe(ResourceLocation idIn, String groupIn, NonNullList<Ingredient> ingredientsIn, NonNullList<ItemStack> resultsIn, int processingTimeIn)
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
        int inputIngredient = 0;
        int inputSlotIndex = 0;
        return this.ingredients.get(inputIngredient).test(container.getItem(inputSlotIndex));
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess)
    {
        //By default, we assume there is only 1 output slot
        return this.results.get(0).copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) { return true; }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess)
    {
        //By default, we assume there is only 1 output slot
        return this.results.get(0).copy();
    }

    public int getProcessingTime() { return this.processingTime; }

    @Override
    public ResourceLocation getId() { return this.id; }

    @Override
    public String getGroup() { return this.group; }

    @Override
    public abstract ItemStack getToastSymbol();

    @Override
    public abstract RecipeSerializer<?> getSerializer();

    @Override
    public abstract RecipeType<?> getType();
}
