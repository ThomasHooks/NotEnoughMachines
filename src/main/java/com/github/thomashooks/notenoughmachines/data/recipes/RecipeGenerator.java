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
        buildCokeOvenRecipes(consumer);
    }

    protected void buildCraftingTableRecipes(Consumer<FinishedRecipe> consumer)
    {
        //Axle
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.AXLE.get(), 2)
                .pattern("# ")
                .pattern("#b")
                .define('#', NEMTags.Items.STRIPPED_LOGS)
                .define('b', AllItems.LINSEED_OIL.get())
                .group(NotEnoughMachines.MOD_ID + ":axle")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Large Cogwheel
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.COGWHEEL_LARGE.get(), 1)
                .pattern("=#=")
                .pattern("#x#")
                .pattern("=#=")
                .define('=', ItemTags.WOODEN_SLABS)
                .define('#', AllItems.WOODEN_FRAME.get())
                .define('x', AllItems.COGWHEEL_SMALL.get())
                .group(NotEnoughMachines.MOD_ID + ":large_cogwheel")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Small Cogwheel
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.COGWHEEL_SMALL.get(), 1)
                .requires(AllItems.AXLE.get())
                .requires(AllItems.GEAR.get())
                .group(NotEnoughMachines.MOD_ID + ":small_cogwheel")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Enclosed Axle
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.ENCLOSED_AXLE.get(), 3)
                .pattern("###")
                .pattern("xxx")
                .pattern("###")
                .define('#', AllItems.WOODEN_FRAME.get())
                .define('x', AllItems.AXLE.get())
                .group(NotEnoughMachines.MOD_ID + ":enclosed_axle")
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

        //Gearbox
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.GEARBOX.get(), 1)
                .pattern("#x#")
                .pattern("xbx")
                .pattern("#x#")
                .define('#', AllItems.WOODEN_FRAME.get())
                .define('x', AllItems.GEAR.get())
                .define('b', AllItems.LINSEED_OIL.get())
                .group(NotEnoughMachines.MOD_ID + ":gearbox")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Gunpowder
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 1)
                .requires(AllItems.FLUX.get(), 2)
                .requires(Items.CHARCOAL, 1)
                .group(NotEnoughMachines.MOD_ID + ":gunpowder")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":gunpowder_from_flux_and_charcoal");

        //Iron Screw
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.IRON_SCREW.get(), 1)
                .pattern("-l ")
                .pattern(" l-")
                .pattern("-l ")
                .define('l', AllItems.IRON_ROD.get())
                .define('-', AllItems.IRON_PLATE.get())
                .group(NotEnoughMachines.MOD_ID + ":iron_screw")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

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

        //Block of Linen
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.LINEN_BLOCK.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.LINEN.get())
                .group(NotEnoughMachines.MOD_ID + ":linen_block")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Linseed Oil
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.LINSEED_OIL.get(), 1)
                .requires(AllItems.FLAXSEED.get(), 6)
                .requires(Items.GLASS_BOTTLE, 1)
                .group(NotEnoughMachines.MOD_ID + ":linseed_oil")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //String from Flax String
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STRING, 3)
                .pattern("# ")
                .pattern("##")
                .define('#', AllItems.FLAX_STRING.get())
                .group(NotEnoughMachines.MOD_ID + ":string")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":string_from_flax_string");

        //Millstone
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.MILLSTONE.get(), 1)
                .pattern("=x=")
                .pattern("===")
                .pattern("#v#")
                .define('#', AllItems.WOODEN_FRAME.get())
                .define('x', AllItems.GEAR.get())
                .define('=', Items.SMOOTH_STONE_SLAB)
                .define('v', Items.HOPPER)
                .group(NotEnoughMachines.MOD_ID + ":millstone")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Block of Tin
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.TIN_BLOCK.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.TIN_INGOT.get())
                .group(NotEnoughMachines.MOD_ID + ":tin_block")
                .unlockedBy("has_" + getHasName(AllItems.RAW_TIN.get()), has(AllItems.RAW_TIN.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.TIN_INGOT.get(), 9)
                .requires(AllBlocks.TIN_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":tin_ingot")
                .unlockedBy("has_" + getHasName(AllItems.RAW_TIN.get()), has(AllItems.RAW_TIN.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":tin_ingot_from_tin_block");

        //Water Wheel
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.WATER_WHEEL.get(), 1)
                .pattern("===")
                .pattern("=x=")
                .pattern("===")
                .define('=', ItemTags.WOODEN_SLABS)
                .define('x', AllItems.COGWHEEL_SMALL.get())
                .group(NotEnoughMachines.MOD_ID + ":water_wheel")
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
        //Building Blocks
        simpleOreSmelting(consumer, "polished_fluxstone", AllItems.FLUXSTONE.get(), AllItems.POLISHED_FLUXSTONE.get(), AllItems.FLUXSTONE.get(), RecipeCategory.BUILDING_BLOCKS, 0.15F, 200);

        //Food
        simpleOreSmelting(consumer, "flour", AllItems.FLOUR.get(), Items.BREAD, Items.WHEAT, RecipeCategory.FOOD, 0.35F, 200);
        simpleOreSmoking(consumer, "flour", AllItems.FLOUR.get(), Items.BREAD, Items.WHEAT, RecipeCategory.FOOD, 0.35F, 100);

        //Misc - Dust
        simpleOreSmelting(consumer, "copper_dust", AllItems.CRUSHED_COPPER_ORE.get(), Items.COPPER_INGOT, Items.RAW_COPPER, RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(consumer, "copper_dust", AllItems.CRUSHED_COPPER_ORE.get(), Items.COPPER_INGOT, Items.RAW_COPPER, RecipeCategory.MISC, 0.7F, 100);
        simpleOreSmelting(consumer, "gold_dust", AllItems.CRUSHED_GOLD_ORE.get(), Items.GOLD_INGOT, Items.RAW_GOLD, RecipeCategory.MISC, 1.0F, 200);
        simpleOreBlasting(consumer, "gold_dust", AllItems.CRUSHED_GOLD_ORE.get(), Items.GOLD_INGOT, Items.RAW_GOLD, RecipeCategory.MISC, 1.0F, 100);
        simpleOreSmelting(consumer, "iron_dust", AllItems.CRUSHED_IRON_ORE.get(), Items.IRON_INGOT, Items.RAW_IRON, RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(consumer, "iron_dust", AllItems.CRUSHED_IRON_ORE.get(), Items.IRON_INGOT, Items.RAW_IRON, RecipeCategory.MISC, 0.7F, 100);
        simpleOreSmelting(consumer, "tin_dust", AllItems.CRUSHED_TIN_ORE.get(), AllItems.TIN_INGOT.get(), AllItems.RAW_TIN.get(), RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(consumer, "tin_dust", AllItems.CRUSHED_TIN_ORE.get(), AllItems.TIN_INGOT.get(), AllItems.RAW_TIN.get(), RecipeCategory.MISC, 0.7F, 100);

        //Misc - Other Minerals
        simpleOreSmelting(consumer, "raw_tin", AllItems.RAW_TIN.get(), AllItems.TIN_INGOT.get(), AllItems.RAW_TIN.get(), RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(consumer, "raw_tin", AllItems.RAW_TIN.get(), AllItems.TIN_INGOT.get(), AllItems.RAW_TIN.get(), RecipeCategory.MISC, 0.7F, 100);
    }

    protected void buildMillingRecipes(Consumer<FinishedRecipe> consumer)
    {
        simpleMilling(consumer, "flax_string", AllItems.FLAX.get(), AllItems.FLAX_STRING.get(), 3, 200);
        simpleMilling(consumer, "flint", Items.GRAVEL, Items.FLINT, 1, 500);

        //Dust
        simpleMilling(consumer, "blaze_powder", Items.BLAZE_ROD, Items.BLAZE_POWDER, 4, 300);
        simpleMilling(consumer, "bone_meal", Items.BONE, Items.BONE_MEAL, 6, 300);
        simpleMilling(consumer, "copper_dust", Items.RAW_COPPER, AllItems.CRUSHED_COPPER_ORE.get(), 1, 600);
        simpleMilling(consumer, "flour", Items.WHEAT, AllItems.FLOUR.get(), 1, 200);
        simpleMilling(consumer, "flux", AllItems.FLUXSTONE.get(), AllItems.FLUX.get(), 1, 500);
        simpleMilling(consumer, "gold_dust", Items.RAW_GOLD, AllItems.CRUSHED_GOLD_ORE.get(), 1, 600);
        simpleMilling(consumer, "iron_dust", Items.RAW_IRON, AllItems.CRUSHED_IRON_ORE.get(), 1, 600);
        simpleMilling(consumer, "sugar", Items.SUGAR_CANE, Items.SUGAR, 2, 200);
        simpleMilling(consumer, "tin_dust", AllItems.RAW_TIN.get(), AllItems.CRUSHED_TIN_ORE.get(), 1, 600);

        //Dye
        simpleMilling(consumer, "black_dye", Ingredient.of(Items.INK_SAC, Items.WITHER_ROSE), Items.BLACK_DYE, 2, 200);
        simpleMilling(consumer, "blue_dye", Items.LAPIS_LAZULI, Items.BLUE_DYE, 3, 400);
        simpleMilling(consumer, "blue_dye", Items.CORNFLOWER, Items.BLUE_DYE, 2, 200);
        simpleMilling(consumer, "brown_dye", Items.COCOA_BEANS, Items.BROWN_DYE, 2, 200);
        simpleMilling(consumer, "green_dye", Items.CACTUS, Items.GREEN_DYE, 2, 200);
        simpleMilling(consumer, "light_blue_dye", Items.BLUE_ORCHID, Items.LIGHT_BLUE_DYE, 2, 200);
        simpleMilling(consumer, "light_gray_dye", Ingredient.of(Items.AZURE_BLUET, Items.OXEYE_DAISY, Items.WHITE_TULIP), Items.LIGHT_GRAY_DYE, 2, 200);
        simpleMilling(consumer, "magenta_dye", Ingredient.of(Items.LILAC, Items.ALLIUM), Items.MAGENTA_DYE, 2, 200);
        simpleMilling(consumer, "orange_dye", Items.ORANGE_TULIP, Items.ORANGE_DYE, 2, 200);
        simpleMilling(consumer, "pink_dye", Ingredient.of(Items.PINK_TULIP, Items.PEONY), Items.PINK_DYE, 2, 200);
        simpleMilling(consumer, "red_dye", Ingredient.of(Items.POPPY, Items.RED_TULIP, Items.BEETROOT, Items.ROSE_BUSH), Items.RED_DYE, 2, 200);
        simpleMilling(consumer, "white_dye", Ingredient.of(Items.BONE_MEAL, Items.LILY_OF_THE_VALLEY), Items.WHITE_DYE, 2, 200);
        simpleMilling(consumer, "yellow_dye", Ingredient.of(Items.DANDELION, Items.SUNFLOWER), Items.YELLOW_DYE, 2, 200);
    }

    protected void buildStampingRecipes(Consumer<FinishedRecipe> consumer)
    {
        simpleStamping(consumer, "clay_ball", Ingredient.of(Items.SAND, Items.RED_SAND), Items.CLAY_BALL, 6, 200);
        simpleStamping(consumer, "cobblestone", Ingredient.of(Items.STONE, Items.CRACKED_STONE_BRICKS, Items.SMOOTH_STONE), Items.COBBLESTONE, 1, 200);
        simpleStamping(consumer, "cracked_stone_bricks", Ingredient.of(Items.STONE_BRICKS, Items.CHISELED_STONE_BRICKS), Items.CRACKED_STONE_BRICKS, 1, 200);
        simpleStamping(consumer, "glowstone_dust", Items.REDSTONE_LAMP, Items.GLOWSTONE_DUST, 4, Items.REDSTONE, 4, 200);
        simpleStamping(consumer, "gravel", Ingredient.of(Items.COBBLESTONE), Items.GRAVEL, 1, Items.FLINT, 2, 200);
        simpleStamping(consumer, "mossy_cobblestone", Ingredient.of(Items.MOSSY_STONE_BRICKS), Items.MOSSY_COBBLESTONE, 1, 200);
        simpleStamping(consumer, "red_sand", Ingredient.of(Items.RED_SANDSTONE, Items.CHISELED_RED_SANDSTONE, Items.CUT_RED_SANDSTONE, Items.SMOOTH_RED_SANDSTONE), Items.RED_SAND, 1, 200);
        simpleStamping(consumer, "sand", Ingredient.of(Items.SANDSTONE, Items.CHISELED_SANDSTONE, Items.SMOOTH_SANDSTONE, Items.CUT_SANDSTONE, Items.GRAVEL), Items.SAND, 1, 200);

        //Dust
        simpleStamping(consumer, "copper_dust", Items.RAW_COPPER, AllItems.CRUSHED_COPPER_ORE.get(), 2, 300);
        simpleStamping(consumer, "glowstone_dust", Items.GLOWSTONE, Items.GLOWSTONE_DUST, 4, 200);
        simpleStamping(consumer, "gold_dust", Items.RAW_GOLD, AllItems.CRUSHED_GOLD_ORE.get(), 2, 300);
        simpleStamping(consumer, "iron_dust", Items.RAW_IRON, AllItems.CRUSHED_IRON_ORE.get(), 2, 300);
        simpleStamping(consumer, "tin_dust", AllItems.RAW_TIN.get(), AllItems.CRUSHED_TIN_ORE.get(), 2, 300);

        //Raw Ore
        simpleStamping(consumer, "raw_copper", Ingredient.of(Items.COPPER_ORE, Items.DEEPSLATE_COPPER_ORE), Items.RAW_COPPER, 6, 300);
        simpleStamping(consumer, "raw_gold", Ingredient.of(Items.GOLD_ORE, Items.DEEPSLATE_GOLD_ORE), Items.RAW_GOLD, 2, 300);
        simpleStamping(consumer, "raw_iron", Ingredient.of(Items.IRON_ORE, Items.DEEPSLATE_IRON_ORE), Items.RAW_IRON, 2, 300);
        simpleStamping(consumer, "raw_tin", Ingredient.of(AllItems.TIN_ORE.get(), Items.DEEPSLATE_IRON_ORE), AllItems.RAW_TIN.get(), 2, 300);

        //Other Minerals
        simpleStamping(consumer, "amethyst_shard", Ingredient.of(Items.AMETHYST_CLUSTER), Items.AMETHYST_SHARD, 7, 300);
        simpleStamping(consumer, "coal", Ingredient.of(Items.COAL_ORE, Items.DEEPSLATE_COAL_ORE), Items.COAL, 3, 600);
        simpleStamping(consumer, "diamond", Ingredient.of(Items.DIAMOND_ORE, Items.DEEPSLATE_DIAMOND_ORE), Items.DIAMOND, 3, 600);
        simpleStamping(consumer, "emerald", Ingredient.of(Items.EMERALD_ORE, Items.DEEPSLATE_EMERALD_ORE), Items.EMERALD, 3, 600);
        simpleStamping(consumer, "flux", AllItems.FLUXSTONE.get(), AllItems.FLUX.get(), 3, 300);
        simpleStamping(consumer, "gold_nugget", Items.NETHER_GOLD_ORE, Items.GOLD_NUGGET, 7, 600);
        simpleStamping(consumer, "lapis_lazuli", Ingredient.of(Items.LAPIS_ORE, Items.DEEPSLATE_LAPIS_ORE), Items.LAPIS_LAZULI, 11, 600);
        simpleStamping(consumer, "quartz", Ingredient.of(Items.NETHER_QUARTZ_ORE), Items.QUARTZ, 2, 300);
        simpleStamping(consumer, "redstone", Ingredient.of(Items.REDSTONE_ORE, Items.DEEPSLATE_REDSTONE_ORE), Items.REDSTONE, 6, 600);
    }

    protected void buildRollingRecipes(Consumer<FinishedRecipe> consumer)
    {
    }

    protected void buildCokeOvenRecipes(Consumer<FinishedRecipe> consumer)
    {
    }

    protected void simpleOreBlasting(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":cooking/" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_blasting");
    }

    protected void simpleOreSmelting(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":cooking/" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_smelting");
    }

    protected void simpleOreSmoking(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":cooking/" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_smoking");
    }

    protected void simpleMilling(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike result, int count, int processingTime)
    {
        SingleResultMachineRecipeBuilder.millingRecipe(Ingredient.of(ingredient), result, count, processingTime)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":milling/" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_milling");
    }

    protected void simpleMilling(Consumer<FinishedRecipe> consumer, String group, Ingredient ingredient, ItemLike result, int count, int processingTime)
    {
        SingleResultMachineRecipeBuilder.millingRecipe(ingredient, result, count, processingTime)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":milling/" + getItemName(result) + "_from_milling");
    }

    protected void simpleStamping(Consumer<FinishedRecipe> consumer, String group, Ingredient ingredient, ItemLike resultPrimary, int countPrimary, int processingTime)
    {
        DoubleResultMachineRecipeBuilder.stampingRecipe(ingredient, resultPrimary, countPrimary, processingTime)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":stamping/" + getItemName(resultPrimary) + "_from_stamping");
    }

    protected void simpleStamping(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike resultPrimary, int countPrimary, int processingTime)
    {
        DoubleResultMachineRecipeBuilder.stampingRecipe(Ingredient.of(ingredient), resultPrimary, countPrimary, processingTime)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":stamping/" + getItemName(resultPrimary) + "_from_" + getItemName(ingredient) + "_from_stamping");
    }

    protected void simpleStamping(Consumer<FinishedRecipe> consumer, String group, Ingredient ingredient, ItemLike resultPrimary, int countPrimary, ItemLike resultSecondary, int countSecondary, int processingTime)
    {
        DoubleResultMachineRecipeBuilder.stampingRecipe(ingredient, resultPrimary, countPrimary, processingTime)
                .addSecondaryResult(resultSecondary, countSecondary)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":stamping/" + getItemName(resultPrimary) + "_from_stamping");
    }

    protected void simpleStamping(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike resultPrimary, int countPrimary, ItemLike resultSecondary, int countSecondary, int processingTime)
    {
        DoubleResultMachineRecipeBuilder.stampingRecipe(Ingredient.of(ingredient), resultPrimary, countPrimary, processingTime)
                .addSecondaryResult(resultSecondary, countSecondary)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":stamping/" + getItemName(resultPrimary) + "_from_" + getItemName(ingredient)  + "_from_stamping");
    }
}
