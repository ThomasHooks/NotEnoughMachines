package com.github.thomashooks.notenoughmachines.data.recipes;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.item.crafting.AllRecipes;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class DoubleResultMachineRecipeBuilder implements RecipeBuilder
{
    private final Ingredient ingredient;
    private final Item resultPrimary;
    private final int countPrimary;
    private Item resultSecondary = null;
    private int countSecondary = 0;
    private final int processingTime;
    private String group;
    private final RecipeSerializer<?> serializer;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();

    protected DoubleResultMachineRecipeBuilder(Ingredient ingredient, ItemLike resultPrimary, int countPrimary, int processingTime, RecipeSerializer<?> serializerIn)
    {
        this.ingredient = ingredient;
        this.resultPrimary = resultPrimary.asItem();
        this.countPrimary = countPrimary;
        this.processingTime = processingTime;
        this.serializer = serializerIn;
    }

    /**
     * Creates a new stamping recipe
     * @param ingredient specifies the item that the recipe crafts with
     * @param resultPrimary specifies the primary item that is produced from this recipe
     * @param countPrimary specifies the number of primary items that are produced from this recipe
     * @param processingTime specifies the amount of time needed to craft this recipe
     */
    public static DoubleResultMachineRecipeBuilder stampingRecipe(Ingredient ingredient, ItemLike resultPrimary, int countPrimary, int processingTime)
    {
        return new DoubleResultMachineRecipeBuilder(ingredient, resultPrimary, countPrimary, processingTime, AllRecipes.STAMPING.get());
    }

    /**
     * Creates a new stamping recipe that produces one item
     * @param ingredient specifies the item that the recipe crafts with
     * @param resultPrimary specifies the item that is produced from this recipe
     * @param processingTime specifies the amount of time needed to craft this recipe
     */
    public static DoubleResultMachineRecipeBuilder stampingRecipe(Ingredient ingredient, ItemLike resultPrimary, int processingTime)
    {
        return new DoubleResultMachineRecipeBuilder(ingredient, resultPrimary, 1, processingTime, AllRecipes.STAMPING.get());
    }

    @Override
    public RecipeBuilder unlockedBy(String name, CriterionTriggerInstance criterion)
    {
        this.advancement.addCriterion(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String groupIn)
    {
        this.group = groupIn;
        return this;
    }

    /**
     * Adds a secondary result of this recipe
     * @param result specifies the item that is produced from this recipe
     * @param count specifies the number of items that is produced from this recipe
     */
    public RecipeBuilder addSecondaryResult(ItemLike result, int count)
    {
        this.resultSecondary = result.asItem();
        this.countSecondary = count;
        return this;
    }

    /**
     * Adds a secondary result of this recipe
     * @param result specifies the item that is produced from this recipe
     */
    public RecipeBuilder addSecondaryResult(ItemLike result)
    {
        return addSecondaryResult(result, 1);
    }

    @Override
    public Item getResult() { return this.resultPrimary; }

    public Item getResultSecondary() { return this.resultSecondary; }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation resourceLocation)
    {
        consumer.accept(new DoubleResultMachineRecipeBuilder.Result(
                resourceLocation,
                this.group == null ? NotEnoughMachines.MOD_ID + ":" : this.group,
                this.ingredient,
                this.resultPrimary,
                this.countPrimary,
                this.resultSecondary,
                this.countSecondary,
                this.processingTime,
                this.serializer)
        );
    }

    public static class Result implements FinishedRecipe
    {
        private final ResourceLocation id;
        private final String group;
        private final Ingredient ingredient;
        private final Item resultPrimary;
        private final int countPrimary;
        private final Item resultSecondary;
        private final int countSecondary;
        private final int processingTime;
        private final RecipeSerializer<?> serializer;

        public Result(ResourceLocation id, String group, Ingredient ingredient, Item resultPrimary, int countPrimary, @Nullable Item resultSecondary, int countSecondary, int processingTime, RecipeSerializer<?> serializer)
        {
            this.id = id;
            this.group = group;
            this.ingredient = ingredient;
            this.resultPrimary = resultPrimary;
            this.countPrimary = countPrimary;
            this.resultSecondary = resultSecondary;
            this.countSecondary = countSecondary;
            this.processingTime = processingTime;
            this.serializer = serializer;
        }

        @Override
        public void serializeRecipeData(JsonObject jsonObject)
        {
            if (!this.group.isEmpty())
                jsonObject.addProperty("group", this.group);

            jsonObject.add("ingredient", this.ingredient.toJson());

            JsonObject jsonResultPrimary = new JsonObject();
            jsonResultPrimary.addProperty("item", BuiltInRegistries.ITEM.getKey(this.resultPrimary).toString());
            if (this.countPrimary > 1)
                jsonResultPrimary.addProperty("count", this.countPrimary);
            jsonObject.add("result_primary", jsonResultPrimary);

            if (this.resultSecondary != null)
            {
                JsonObject jsonResultSecondary = new JsonObject();
                jsonResultSecondary.addProperty("item", BuiltInRegistries.ITEM.getKey(this.resultSecondary).toString());
                if (this.countSecondary > 1)
                    jsonResultSecondary.addProperty("count", this.countSecondary);
                jsonObject.add("result_secondary", jsonResultSecondary);
            }

            jsonObject.addProperty("process_time", this.processingTime);
        }

        @Override
        public ResourceLocation getId() { return this.id; }

        @Override
        public RecipeSerializer<?> getType() { return this.serializer; }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() { return null; }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() { return null; }
    }
}
