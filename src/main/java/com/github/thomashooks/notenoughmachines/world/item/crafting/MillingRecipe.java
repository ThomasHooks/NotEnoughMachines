package com.github.thomashooks.notenoughmachines.world.item.crafting;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MillingRecipe extends AbstractMachineRecipe
{
    public MillingRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientsIn, ItemStack resultsIn, int processingTimeIn)
    {
        super(idIn, groupIn, ingredientsIn, resultsIn, processingTimeIn);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() { return new ItemStack(AllItems.MILLSTONE.get()); }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() { return Serializer.MILLING; }

    @Override
    public @NotNull RecipeType<?> getType() { return Type.MILLING; }

    public static class Type implements RecipeType<MillingRecipe>
    {
        public static final Type MILLING = new Type();
        public static final String ID = "milling";
    }

    public static class Serializer implements RecipeSerializer<MillingRecipe>
    {
        //The Millstone has only one input slot, and one output slot
        public static final Serializer MILLING = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(NotEnoughMachines.MOD_ID, "milling");

        @Override
        public @NotNull MillingRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject serializedRecipe)
        {
            String group = GsonHelper.getAsString(serializedRecipe, "group", "");

            if (!serializedRecipe.has("ingredient"))
                throw new com.google.gson.JsonSyntaxException("Missing ingredient, expected to find a string or object");
            JsonElement jsonelement = (JsonElement)(GsonHelper.isArrayNode(serializedRecipe, "ingredient") ? GsonHelper.getAsJsonArray(serializedRecipe, "ingredient") : GsonHelper.getAsJsonObject(serializedRecipe, "ingredient"));
            Ingredient ingredient = Ingredient.fromJson(jsonelement);

            if (!serializedRecipe.has("result"))
                throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(serializedRecipe, "result"));

            int processTime = GsonHelper.getAsInt(serializedRecipe, "processtime", 200);

            return new MillingRecipe(recipeId, group, ingredient, result, processTime);
        }

        @Override
        public @Nullable MillingRecipe fromNetwork(@NotNull ResourceLocation recipeId, FriendlyByteBuf buffer)
        {
            String group = buffer.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            int processTime = buffer.readVarInt();
            return new MillingRecipe(recipeId, group, ingredient, result, processTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MillingRecipe recipe)
        {
            buffer.writeUtf(recipe.group);
            recipe.ingredients.toNetwork(buffer);
            buffer.writeItem(recipe.results);
            buffer.writeVarInt(recipe.processingTime);
        }
    }
}
