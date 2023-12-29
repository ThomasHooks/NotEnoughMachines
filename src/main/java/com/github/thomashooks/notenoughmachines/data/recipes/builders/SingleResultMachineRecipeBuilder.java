package com.github.thomashooks.notenoughmachines.data.recipes.builders;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.item.crafting.AllRecipes;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class SingleResultMachineRecipeBuilder implements RecipeBuilder
{
    private final Ingredient ingredient;
    private final Item result;
    private final int count;
    private final int processingTime;
    private String group;
    private final RecipeSerializer<?> serializer;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    protected SingleResultMachineRecipeBuilder(Ingredient ingredient, ItemLike result, int count, int processingTime, RecipeSerializer<?> serializerIn)
    {
        this.ingredient = ingredient;
        this.result = result.asItem();
        this.count = count;
        this.processingTime = processingTime;
        this.serializer = serializerIn;
    }

    /**
     * Creates a new coking recipe
     * @param ingredient specifies the item that the recipe crafts with
     * @param result specifies the item that is produced from this recipe
     * @param count specifies the number of items that are produced from this recipe
     * @param processingTime specifies the amount of time needed to craft this recipe
     */
    public static SingleResultMachineRecipeBuilder coking(Ingredient ingredient, ItemLike result, int count, int processingTime)
    {
        return new SingleResultMachineRecipeBuilder(ingredient, result, count, processingTime, AllRecipes.COKING.get());
    }

    /**
     * Creates a new milling recipe
     * @param ingredient specifies the item that the recipe crafts with
     * @param result specifies the item that is produced from this recipe
     * @param count specifies the number of items that are produced from this recipe
     * @param processingTime specifies the amount of time needed to craft this recipe
     */
    public static SingleResultMachineRecipeBuilder milling(Ingredient ingredient, ItemLike result, int count, int processingTime)
    {
        return new SingleResultMachineRecipeBuilder(ingredient, result, count, processingTime, AllRecipes.MILLING.get());
    }

    /**
     * Creates a new rolling recipe
     * @param ingredient specifies the item that the recipe crafts with
     * @param result specifies the item that is produced from this recipe
     * @param count specifies the number of items that are produced from this recipe
     * @param processingTime specifies the amount of time needed to craft this recipe
     */
    public static SingleResultMachineRecipeBuilder rolling(Ingredient ingredient, ItemLike result, int count, int processingTime)
    {
        return new SingleResultMachineRecipeBuilder(ingredient, result, count, processingTime, AllRecipes.ROLLING.get());
    }

    @Override
    public @NotNull RecipeBuilder unlockedBy(@NotNull String name, @NotNull CriterionTriggerInstance criterion)
    {
        this.advancement.addCriterion(name, criterion);
        return this;
    }

    @Override
    public @NotNull RecipeBuilder group(@Nullable String groupIn)
    {
        this.group = groupIn;
        return this;
    }

    @Override
    public @NotNull Item getResult() { return this.result; }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, @NotNull ResourceLocation recipeID)
    {
        this.ensureValid(recipeID);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeID)).rewards(AdvancementRewards.Builder.recipe(recipeID)).requirements(RequirementsStrategy.OR);
        consumer.accept(new SingleResultMachineRecipeBuilder.Result(recipeID, this.group == null ? NotEnoughMachines.MOD_ID + ":" : this.group, this.ingredient, this.result, this.count, this.processingTime, this.serializer, this.advancement, recipeID.withPrefix("recipes/")));
    }

    private void ensureValid(ResourceLocation recipeID)
    {
        if (this.advancement.getCriteria().isEmpty())
            throw new IllegalStateException("No way of obtaining recipe " + recipeID);
    }

    public static class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final String group;
        private final Ingredient ingredient;
        private final Item result;
        private final int count;
        private final int processingTime;
        private final RecipeSerializer<?> serializer;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementID;

        public Result(ResourceLocation id, String group, Ingredient ingredient, Item result, int count, int processingTime, RecipeSerializer<?> serializer, Advancement.Builder advancement, ResourceLocation advancementID)
        {
            this.id = id;
            this.group = group;
            this.ingredient = ingredient;
            this.result = result;
            this.count = count;
            this.processingTime = processingTime;
            this.serializer = serializer;
            this.advancement = advancement;
            this.advancementID = advancementID;
        }

        @Override
        public void serializeRecipeData(@NotNull JsonObject jsonObject)
        {
            if (!this.group.isEmpty())
                jsonObject.addProperty("group", this.group);

            jsonObject.add("ingredient", this.ingredient.toJson());

            JsonObject jsonResult = new JsonObject();
            jsonResult.addProperty("item", ForgeRegistries.ITEMS.getKey(this.result).toString());
            if (this.count > 1)
                jsonResult.addProperty("count", this.count);
            jsonObject.add("result", jsonResult);

            jsonObject.addProperty("processtime", this.processingTime);
        }

        @Override
        public @NotNull ResourceLocation getId() { return this.id; }

        @Override
        public @NotNull RecipeSerializer<?> getType() { return this.serializer; }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() { return this.advancement.serializeToJson(); }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() { return this.advancementID; }
    }
}
