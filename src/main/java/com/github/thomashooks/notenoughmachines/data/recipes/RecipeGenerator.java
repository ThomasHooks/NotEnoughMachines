package com.github.thomashooks.notenoughmachines.data.recipes;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.NEMBlocks;
import com.github.thomashooks.notenoughmachines.world.item.NEMItems;
import com.github.thomashooks.notenoughmachines.util.NEMTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class RecipeGenerator extends RecipeProvider implements IConditionBuilder
{
    public RecipeGenerator(PackOutput packOutput)
    {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(RecipeOutput output)
    {
        buildCraftingTableRecipes(output);
        buildCookingRecipes(output);
        buildMillingRecipes(output);
        buildStampingRecipes(output);
        buildRollingRecipes(output);
    }

    protected void buildCraftingTableRecipes(RecipeOutput output)
    {
        //Gear
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NEMItems.GEAR.get(), 1)
                .pattern(" # ")
                .pattern("#b#")
                .pattern(" # ")
                .define('#', Items.STICK)
                .define('b', NEMItems.LINSEED_OIL.get())
                .group(NotEnoughMachines.MOD_ID + ":gear")
                .unlockedBy("has_" + getHasName(NEMItems.FLAX.get()), has(NEMItems.FLAX.get()))
                .save(output);

        //Block of Linen
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NEMBlocks.LINEN_BLOCK.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', NEMItems.LINEN.get())
                .group(NotEnoughMachines.MOD_ID + ":linen_block")
                .unlockedBy("has_" + getHasName(NEMItems.FLAX.get()), has(NEMItems.FLAX.get()))
                .save(output);

        //Flax String
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NEMItems.FLAX_STRING.get(), 3)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', NEMItems.FLAX.get())
                .group(NotEnoughMachines.MOD_ID + ":flax_string")
                .unlockedBy("has_" + getHasName(NEMItems.FLAX.get()), has(NEMItems.FLAX.get()))
                .save(output);

        //Gunpowder
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.GUNPOWDER, 2)
                .pattern("fc")
                .pattern("cf")
                .define('f', NEMItems.FLUX.get())
                .define('c', Items.CHARCOAL)
                .group(NotEnoughMachines.MOD_ID + ":gunpowder")
                .unlockedBy("has_" + getHasName(NEMItems.FLUXSTONE.get()), has(NEMItems.FLUXSTONE.get()))
                .save(output, NotEnoughMachines.MOD_ID + ":gunpowder_from_flux_and_charcoal");

        //Linen
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NEMItems.LINEN.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', NEMItems.FLAX_STRING.get())
                .group(NotEnoughMachines.MOD_ID + ":linen")
                .unlockedBy("has_" + getHasName(NEMItems.FLAX.get()), has(NEMItems.FLAX.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, NEMItems.LINEN.get(), 9)
                .requires(NEMBlocks.LINEN_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":linen")
                .unlockedBy("has_" + getHasName(NEMItems.FLAX.get()), has(NEMItems.FLAX.get()))
                .save(output, NotEnoughMachines.MOD_ID + ":linen_from_linen_block");

        //Linseed Oil
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, NEMItems.LINSEED_OIL.get(), 1)
                .requires(NEMItems.FLAXSEED.get(), 6)
                .requires(Items.GLASS_BOTTLE, 1)
                .group(NotEnoughMachines.MOD_ID + ":linseed_oil")
                .unlockedBy("has_" + getHasName(NEMItems.FLAX.get()), has(NEMItems.FLAX.get()))
                .save(output);

        //Wooden Frame
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, NEMBlocks.WOODEN_FRAME.get(), 4)
                .pattern("x#x")
                .pattern("#o#")
                .pattern("x#x")
                .define('#', NEMTags.Items.STRIPPED_LOGS)
                .define('x', ItemTags.PLANKS)
                .define('o', NEMItems.LINSEED_OIL.get())
                .group(NotEnoughMachines.MOD_ID + ":wooden_frame")
                .unlockedBy("has_" + getHasName(NEMItems.FLAX.get()), has(NEMItems.FLAX.get()))
                .save(output);
    }

    protected void buildCookingRecipes(RecipeOutput output)
    {
        simpleOreSmelting(output, NEMItems.FLOUR.get(), Items.BREAD, Items.WHEAT, "flour", RecipeCategory.FOOD, 0.35F, 200);
        simpleOreSmoking(output, NEMItems.FLOUR.get(), Items.BREAD, Items.WHEAT, "flour", RecipeCategory.FOOD, 0.35F, 100);

        simpleOreSmelting(output, NEMItems.CRUSHED_COPPER_ORE.get(), Items.COPPER_INGOT, Items.RAW_COPPER, "copper_dust", RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(output, NEMItems.CRUSHED_COPPER_ORE.get(), Items.COPPER_INGOT, Items.RAW_COPPER, "copper_dust", RecipeCategory.MISC, 0.7F, 100);

        simpleOreSmelting(output, NEMItems.CRUSHED_GOLD_ORE.get(), Items.GOLD_INGOT, Items.RAW_GOLD, "gold_dust", RecipeCategory.MISC, 1.0F, 200);
        simpleOreBlasting(output, NEMItems.CRUSHED_GOLD_ORE.get(), Items.GOLD_INGOT, Items.RAW_GOLD, "gold_dust", RecipeCategory.MISC, 1.0F, 100);

        simpleOreSmelting(output, NEMItems.CRUSHED_IRON_ORE.get(), Items.IRON_INGOT, Items.RAW_IRON, "iron_dust", RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(output, NEMItems.CRUSHED_IRON_ORE.get(), Items.IRON_INGOT, Items.RAW_IRON, "iron_dust", RecipeCategory.MISC, 0.7F, 100);
    }

    private void simpleOreBlasting(RecipeOutput output, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, String group, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(output, NotEnoughMachines.MOD_ID + ":" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_blasting");
    }

    private void simpleOreSmelting(RecipeOutput output, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, String group, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(output, NotEnoughMachines.MOD_ID + ":" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_smelting");
    }

    private void simpleOreSmoking(RecipeOutput output, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, String group, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(output, NotEnoughMachines.MOD_ID + ":" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_smoking");
    }

    protected void buildMillingRecipes(RecipeOutput output)
    {

    }

    protected void buildStampingRecipes(RecipeOutput output)
    {

    }

    protected void buildRollingRecipes(RecipeOutput output)
    {

    }
}
