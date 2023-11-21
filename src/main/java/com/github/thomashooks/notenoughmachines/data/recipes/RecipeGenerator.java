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
    protected void buildRecipes(Consumer<FinishedRecipe> consumer)
    {
        buildCraftingTableRecipes(consumer);
        buildCookingRecipes(consumer);
        buildMillingRecipes(consumer);
        buildStampingRecipes(consumer);
        buildRollingRecipes(consumer);
    }

    protected void buildCraftingTableRecipes(Consumer<FinishedRecipe> consumer)
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
                .save(consumer);
        //Block of Linen
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.LINEN_BLOCK.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.LINEN.get())
                .group(NotEnoughMachines.MOD_ID + ":linen_block")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        //Flax String
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.FLAX_STRING.get(), 3)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', AllItems.FLAX.get())
                .group(NotEnoughMachines.MOD_ID + ":flax_string")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        //Gunpowder
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GUNPOWDER, 2)
                .pattern("fc")
                .pattern("cf")
                .define('f', AllItems.FLUX.get())
                .define('c', Items.CHARCOAL)
                .group(NotEnoughMachines.MOD_ID + ":gunpowder")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":gunpowder_from_flux_and_charcoal");
        //Linen
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.LINEN.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.FLAX_STRING.get())
                .group(NotEnoughMachines.MOD_ID + ":linen")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.LINEN.get(), 9)
                .requires(AllBlocks.LINEN_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":linen")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":linen_from_linen_block");
        //Linseed Oil
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.LINSEED_OIL.get(), 1)
                .requires(AllItems.FLAXSEED.get(), 6)
                .requires(Items.GLASS_BOTTLE, 1)
                .group(NotEnoughMachines.MOD_ID + ":linseed_oil")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
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
                .save(consumer);
    }

    protected void buildCookingRecipes(Consumer<FinishedRecipe> consumer)
    {
        //Flour
        simpleOreSmelting(consumer, AllItems.FLOUR.get(), Items.BREAD, Items.WHEAT, "flour", RecipeCategory.FOOD, 0.35F, 200);
        simpleOreSmoking(consumer, AllItems.FLOUR.get(), Items.BREAD, Items.WHEAT, "flour", RecipeCategory.FOOD, 0.35F, 100);
        //Copper
        simpleOreSmelting(consumer, AllItems.CRUSHED_COPPER_ORE.get(), Items.COPPER_INGOT, Items.RAW_COPPER, "copper_dust", RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(consumer, AllItems.CRUSHED_COPPER_ORE.get(), Items.COPPER_INGOT, Items.RAW_COPPER, "copper_dust", RecipeCategory.MISC, 0.7F, 100);
        //Gold
        simpleOreSmelting(consumer, AllItems.CRUSHED_GOLD_ORE.get(), Items.GOLD_INGOT, Items.RAW_GOLD, "gold_dust", RecipeCategory.MISC, 1.0F, 200);
        simpleOreBlasting(consumer, AllItems.CRUSHED_GOLD_ORE.get(), Items.GOLD_INGOT, Items.RAW_GOLD, "gold_dust", RecipeCategory.MISC, 1.0F, 100);
        //Iron
        simpleOreSmelting(consumer, AllItems.CRUSHED_IRON_ORE.get(), Items.IRON_INGOT, Items.RAW_IRON, "iron_dust", RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(consumer, AllItems.CRUSHED_IRON_ORE.get(), Items.IRON_INGOT, Items.RAW_IRON, "iron_dust", RecipeCategory.MISC, 0.7F, 100);
    }

    protected void simpleOreBlasting(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, String group, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_blasting");
    }

    protected void simpleOreSmelting(Consumer<FinishedRecipe> output, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, String group, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(output, NotEnoughMachines.MOD_ID + ":" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_smelting");
    }

    protected void simpleOreSmoking(Consumer<FinishedRecipe> output, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, String group, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(output, NotEnoughMachines.MOD_ID + ":" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_smoking");
    }

    protected void buildMillingRecipes(Consumer<FinishedRecipe> consumer)
    {
        //Organics
        simpleMilling(consumer, AllItems.FLAX.get(), AllItems.FLAX_STRING.get(), "flax_string", 3, 300);
        //Powders
        simpleMilling(consumer, Items.BLAZE_ROD, Items.BLAZE_POWDER, "blaze_powder", 4, 300);
        simpleMilling(consumer, Items.BONE, Items.BONE_MEAL, "bone_meal", 6, 300);
        simpleMilling(consumer, Items.WHEAT, AllItems.FLOUR.get(), "flour", 1, 200);
        simpleMilling(consumer, Items.SUGAR_CANE, Items.SUGAR, "sugar", 2, 200);
        //Dyes
        simpleMilling(consumer, Items.INK_SAC, Items.BLACK_DYE, "black_dye", 2, 200);
        simpleMilling(consumer, Items.WITHER_ROSE, Items.BLACK_DYE, "black_dye", 2, 200);
        simpleMilling(consumer, Items.LAPIS_LAZULI, Items.BLUE_DYE, "blue_dye", 2, 300);
        simpleMilling(consumer, Items.CORNFLOWER, Items.BLUE_DYE, "blue_dye", 2, 200);
        simpleMilling(consumer, Items.BLUE_ORCHID, Items.LIGHT_BLUE_DYE, "light_blue_dye", 2, 200);
        simpleMilling(consumer, Items.COCOA_BEANS, Items.BROWN_DYE, "brown_dye", 2, 200);
        simpleMilling(consumer, Items.CACTUS, Items.GREEN_DYE, "green_dye", 2, 200);
        simpleMilling(consumer, Items.POPPY, Items.RED_DYE, "red_dye", 2, 200);
        simpleMilling(consumer, Items.RED_TULIP, Items.RED_DYE, "red_dye", 2, 200);
        simpleMilling(consumer, Items.BEETROOT, Items.RED_DYE, "red_dye", 2, 200);
        simpleMilling(consumer, Items.ROSE_BUSH, Items.RED_DYE, "red_dye", 2, 200);
        simpleMilling(consumer, Items.BONE_MEAL, Items.WHITE_DYE, "white_dye", 2, 200);
        simpleMilling(consumer, Items.LILY_OF_THE_VALLEY, Items.WHITE_DYE, "white_dye", 2, 200);
        simpleMilling(consumer, Items.AZURE_BLUET, Items.LIGHT_GRAY_DYE, "light_gray_dye", 2, 200);
        simpleMilling(consumer, Items.OXEYE_DAISY, Items.LIGHT_GRAY_DYE, "light_gray_dye", 2, 200);
        simpleMilling(consumer, Items.WHITE_TULIP, Items.LIGHT_GRAY_DYE, "light_gray_dye", 2, 200);
        simpleMilling(consumer, Items.LILAC, Items.MAGENTA_DYE, "magenta_dye", 2, 200);
        simpleMilling(consumer, Items.ALLIUM, Items.MAGENTA_DYE, "magenta_dye", 2, 200);
        simpleMilling(consumer, Items.ORANGE_TULIP, Items.ORANGE_DYE, "orange_dye", 2, 200);
        simpleMilling(consumer, Items.PINK_TULIP, Items.PINK_DYE, "pink_dye", 2, 200);
        simpleMilling(consumer, Items.PEONY, Items.PINK_DYE, "pink_dye", 2, 200);
        simpleMilling(consumer, Items.DANDELION, Items.YELLOW_DYE, "yellow_dye", 2, 200);
        simpleMilling(consumer, Items.SUNFLOWER, Items.YELLOW_DYE, "yellow_dye", 2, 200);
    }

    protected void simpleMilling(Consumer<FinishedRecipe> consumer, ItemLike ingredient, ItemLike result, String group, int count, int processingTime)
    {
        SingleIngredientRecipeBuilder.millingRecipe(Ingredient.of(ingredient), result, count, processingTime)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":milling/" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_milling");
    }

    protected void buildStampingRecipes(Consumer<FinishedRecipe> consumer)
    {
    }

    protected void buildRollingRecipes(Consumer<FinishedRecipe> consumer)
    {
    }
}
