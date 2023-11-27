package com.github.thomashooks.notenoughmachines.world.item.crafting;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.Nullable;

public class StampingRecipe extends AbstractMachineRecipe
{
    protected final ItemStack resultSecondary;

    public StampingRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientsIn, ItemStack firstResultIn, ItemStack secondResultIn, int processingTimeIn)
    {
        super(idIn, groupIn, ingredientsIn, firstResultIn, processingTimeIn);
        this.resultSecondary = secondResultIn;
    }

    public ItemStack getSecondaryResultItem()
    {
        return this.resultSecondary.copy();
    }

    @Override
    public ItemStack getToastSymbol() { return new ItemStack(AllItems.TRIP_HAMMER.get()); }

    @Override
    public RecipeSerializer<?> getSerializer() { return Serializer.STAMPING; }

    @Override
    public RecipeType<?> getType() { return Type.STAMPING; }

    public static class Type implements RecipeType<StampingRecipe>
    {
        public static final StampingRecipe.Type STAMPING = new StampingRecipe.Type();
        public static final String ID = "stamping";
    }

    public static class Serializer implements RecipeSerializer<StampingRecipe>
    {
        public static final StampingRecipe.Serializer STAMPING = new StampingRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(NotEnoughMachines.MOD_ID, "stamping");

        @Override
        public StampingRecipe fromJson(ResourceLocation recipeId, JsonObject serializedRecipe)
        {
            String group = GsonHelper.getAsString(serializedRecipe, "group", "");

            if (!serializedRecipe.has("ingredient"))
                throw new com.google.gson.JsonSyntaxException("Missing ingredient, expected to find a string or object");
            JsonElement jsonelement = (JsonElement)(GsonHelper.isArrayNode(serializedRecipe, "ingredient") ? GsonHelper.getAsJsonArray(serializedRecipe, "ingredient") : GsonHelper.getAsJsonObject(serializedRecipe, "ingredient"));
            Ingredient ingredient = Ingredient.fromJson(jsonelement);

            if (!serializedRecipe.has("result_primary"))
                throw new com.google.gson.JsonSyntaxException("Missing primary result, expected to find a string or object");
            ItemStack resultPrimary = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(serializedRecipe, "result_primary"));

            ItemStack resultSecondary = serializedRecipe.has("result_secondary") ? ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(serializedRecipe, "result_secondary")) : new ItemStack(Items.AIR);

            int processTime = GsonHelper.getAsInt(serializedRecipe, "process_time", 200);

            return new StampingRecipe(recipeId, group, ingredient, resultPrimary, resultSecondary, processTime);
        }

        @Override
        public @Nullable StampingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer)
        {
            String group = buffer.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack resultPrimary = buffer.readItem();
            ItemStack resultSecondary = buffer.readItem();
            int processTime = buffer.readVarInt();
            return new StampingRecipe(recipeId, group, ingredient, resultPrimary, resultSecondary, processTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, StampingRecipe recipe)
        {
            buffer.writeUtf(recipe.group);
            recipe.ingredients.toNetwork(buffer);
            buffer.writeItem(recipe.results);
            buffer.writeItem(recipe.resultSecondary);
            buffer.writeVarInt(recipe.processingTime);
        }
    }
}
