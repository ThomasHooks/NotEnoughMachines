package com.github.thomashooks.notenoughmachines.data.recipes;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import com.github.thomashooks.notenoughmachines.util.NEMTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider implements IConditionBuilder
{
    public RecipeGenerator(PackOutput packOutput)
    {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> output)
    {
        buildCraftingTableRecipes(output);
        buildCookingRecipes(output);
        buildMillingRecipes(output);
        buildStampingRecipes(output);
        buildRollingRecipes(output);
    }

    protected void buildCraftingTableRecipes(Consumer<FinishedRecipe> output)
    {
        //Gear
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.GEAR.get(), 1)
                .pattern(" # ")
                .pattern("#b#")
                .pattern(" # ")
                .define('#', Items.STICK)
                .define('b', AllItems.LINSEED_OIL.get())
                .group(NotEnoughMachines.MOD_ID + ":gear")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(output);

        //Block of Linen
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.LINEN_BLOCK.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.LINEN.get())
                .group(NotEnoughMachines.MOD_ID + ":linen_block")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(output);

        //Flax String
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.FLAX_STRING.get(), 3)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', AllItems.FLAX.get())
                .group(NotEnoughMachines.MOD_ID + ":flax_string")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(output);

        //Gunpowder
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GUNPOWDER, 2)
                .pattern("fc")
                .pattern("cf")
                .define('f', AllItems.FLUX.get())
                .define('c', Items.CHARCOAL)
                .group(NotEnoughMachines.MOD_ID + ":gunpowder")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(output, NotEnoughMachines.MOD_ID + ":gunpowder_from_flux_and_charcoal");

        //Linen
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.LINEN.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.FLAX_STRING.get())
                .group(NotEnoughMachines.MOD_ID + ":linen")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.LINEN.get(), 9)
                .requires(AllBlocks.LINEN_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":linen")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(output, NotEnoughMachines.MOD_ID + ":linen_from_linen_block");

        //Linseed Oil
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.LINSEED_OIL.get(), 1)
                .requires(AllItems.FLAXSEED.get(), 6)
                .requires(Items.GLASS_BOTTLE, 1)
                .group(NotEnoughMachines.MOD_ID + ":linseed_oil")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(output);

        //Wooden Frame
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllBlocks.WOODEN_FRAME.get(), 4)
                .pattern("x#x")
                .pattern("#o#")
                .pattern("x#x")
                .define('#', NEMTags.Items.STRIPPED_LOGS)
                .define('x', ItemTags.PLANKS)
                .define('o', AllItems.LINSEED_OIL.get())
                .group(NotEnoughMachines.MOD_ID + ":wooden_frame")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(output);
    }

    protected void buildCookingRecipes(Consumer<FinishedRecipe> output)
    {
        simpleOreSmelting(output, AllItems.FLOUR.get(), Items.BREAD, Items.WHEAT, "flour", RecipeCategory.FOOD, 0.35F, 200);
        simpleOreSmoking(output, AllItems.FLOUR.get(), Items.BREAD, Items.WHEAT, "flour", RecipeCategory.FOOD, 0.35F, 100);

        simpleOreSmelting(output, AllItems.CRUSHED_COPPER_ORE.get(), Items.COPPER_INGOT, Items.RAW_COPPER, "copper_dust", RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(output, AllItems.CRUSHED_COPPER_ORE.get(), Items.COPPER_INGOT, Items.RAW_COPPER, "copper_dust", RecipeCategory.MISC, 0.7F, 100);

        simpleOreSmelting(output, AllItems.CRUSHED_GOLD_ORE.get(), Items.GOLD_INGOT, Items.RAW_GOLD, "gold_dust", RecipeCategory.MISC, 1.0F, 200);
        simpleOreBlasting(output, AllItems.CRUSHED_GOLD_ORE.get(), Items.GOLD_INGOT, Items.RAW_GOLD, "gold_dust", RecipeCategory.MISC, 1.0F, 100);

        simpleOreSmelting(output, AllItems.CRUSHED_IRON_ORE.get(), Items.IRON_INGOT, Items.RAW_IRON, "iron_dust", RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(output, AllItems.CRUSHED_IRON_ORE.get(), Items.IRON_INGOT, Items.RAW_IRON, "iron_dust", RecipeCategory.MISC, 0.7F, 100);
    }

    private void simpleOreBlasting(Consumer<FinishedRecipe> output, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, String group, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(output, NotEnoughMachines.MOD_ID + ":" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_blasting");
    }

    private void simpleOreSmelting(Consumer<FinishedRecipe> output, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, String group, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(output, NotEnoughMachines.MOD_ID + ":" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_smelting");
    }

    private void simpleOreSmoking(Consumer<FinishedRecipe> output, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, String group, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(output, NotEnoughMachines.MOD_ID + ":" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_smoking");
    }

    protected void buildMillingRecipes(Consumer<FinishedRecipe> output)
    {

    }

    protected void buildStampingRecipes(Consumer<FinishedRecipe> output)
    {

    }

    protected void buildRollingRecipes(Consumer<FinishedRecipe> output)
    {

    }
}