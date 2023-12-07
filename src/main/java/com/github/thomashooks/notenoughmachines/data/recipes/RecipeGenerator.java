package com.github.thomashooks.notenoughmachines.data.recipes;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.util.NEMTags;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
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
        buildStoneCuttingRecipes(consumer);
        buildMillingRecipes(consumer);
        buildStampingRecipes(consumer);
        buildRollingRecipes(consumer);
        buildCokeOvenRecipes(consumer);
    }

    protected void buildCraftingTableRecipes(Consumer<FinishedRecipe> consumer)
    {
        //Axle
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.AXLE.get(), 3)
                .pattern("###")
                .pattern("b  ")
                .define('#', NEMTags.Items.STRIPPED_LOGS)
                .define('b', AllItems.LINSEED_OIL.get())
                .group(NotEnoughMachines.MOD_ID + ":axle")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Block of Bronze
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.BRONZE_BLOCK.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.BRONZE_INGOT.get())
                .group(NotEnoughMachines.MOD_ID + ":bronze_block")
                .unlockedBy("has_" + getHasName(AllItems.CRUSHED_BRONZE.get()), has(AllItems.CRUSHED_BRONZE.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.BRONZE_INGOT.get(), 9)
                .requires(AllItems.BRONZE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":bronze_ingot")
                .unlockedBy("has_" + getHasName(AllItems.CRUSHED_BRONZE.get()), has(AllItems.CRUSHED_BRONZE.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":bronze_ingot_from_bronze_block");

        //Block of Coke
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.COKE_BLOCK.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.COKE.get())
                .group(NotEnoughMachines.MOD_ID + ":coke_block")
                .unlockedBy("has_" + getHasName(AllItems.COKE.get()), has(AllItems.COKE.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.COKE.get(), 9)
                .requires(AllItems.COKE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":coke")
                .unlockedBy("has_" + getHasName(AllItems.COKE.get()), has(AllItems.COKE.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":coke_from_coke_block");

        //Block of Linen
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.LINEN_BLOCK.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.LINEN.get())
                .group(NotEnoughMachines.MOD_ID + ":linen_block")
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

        //Campfire From Coke
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.CAMPFIRE, 1)
                .pattern(" s ")
                .pattern("sos")
                .pattern("###")
                .define('o', AllItems.COKE.get())
                .define('s', Items.STICK)
                .define('#', ItemTags.LOGS)
                .group(NotEnoughMachines.MOD_ID + ":campfire")
                .unlockedBy("has_" + getHasName(AllItems.COKE.get()), has(AllItems.COKE.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":campfire_from_coke");

        //Coke Oven
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.COKE_OVEN.get(), 1)
                .pattern("===")
                .pattern("=x=")
                .pattern("===")
                .define('=', AllItems.FIRE_BRICKS.get())
                .define('x', Items.FURNACE)
                .group(NotEnoughMachines.MOD_ID + ":coke_oven")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Crushed Bronze
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.CRUSHED_BRONZE.get(), 2)
                .requires(AllItems.CRUSHED_COPPER_ORE.get(), 3)
                .requires(AllItems.CRUSHED_TIN_ORE.get(), 1)
                .requires(AllItems.FLUX.get(), 2)
                .group(NotEnoughMachines.MOD_ID + ":crushed_bronze")
                .unlockedBy("has_" + getHasName(AllItems.RAW_TIN.get()), has(AllItems.RAW_TIN.get()))
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

        //Fire Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.FIRE_BRICKS.get(), 1)
                .pattern("##")
                .pattern("##")
                .define('#', AllItems.FIRE_BRICK.get())
                .group(NotEnoughMachines.MOD_ID + ":fire_bricks")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Fire Bricks Slab
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.FIRE_BRICKS_SLAB.get(), 6)
                .pattern("===")
                .define('=', AllItems.FIRE_BRICKS.get())
                .group(NotEnoughMachines.MOD_ID + ":fire_bricks_slab")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Fire Bricks Stairs
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.FIRE_BRICKS_STAIRS.get(), 4)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.FIRE_BRICKS.get())
                .group(NotEnoughMachines.MOD_ID + ":fire_bricks_stairs")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Fire Bricks Wall
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AllItems.FIRE_BRICKS_WALL.get(), 6)
                .pattern("===")
                .pattern("===")
                .define('=', AllItems.FIRE_BRICKS.get())
                .group(NotEnoughMachines.MOD_ID + ":fire_bricks_wall")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Fire Charge from Coke
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.FIRE_CHARGE, 3)
                .pattern("fo")
                .pattern(" g")
                .define('f', Items.BLAZE_POWDER)
                .define('o', AllItems.COKE.get())
                .define('g', Items.GUNPOWDER)
                .group(NotEnoughMachines.MOD_ID + ":fire_charge")
                .unlockedBy("has_" + getHasName(AllItems.COKE.get()), has(AllItems.COKE.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":fire_charge_from_coke");

        //Flax String
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.FLAX_STRING.get(), 3)
                .pattern("#")
                .pattern("#")
                .pattern("#")
                .define('#', AllItems.FLAX.get())
                .group(NotEnoughMachines.MOD_ID + ":flax_string")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Fluxstone Slab
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE_SLAB.get(), 6)
                .pattern("===")
                .define('=', AllItems.FLUXSTONE.get())
                .group(NotEnoughMachines.MOD_ID + ":fluxstone_slab")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Fluxstone Stairs
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE_STAIRS.get(), 4)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.FLUXSTONE.get())
                .group(NotEnoughMachines.MOD_ID + ":fluxstone_stairs")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Fluxstone Wall
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AllItems.FLUXSTONE_WALL.get(), 6)
                .pattern("===")
                .pattern("===")
                .define('=', AllItems.FLUXSTONE.get())
                .group(NotEnoughMachines.MOD_ID + ":fluxstone_wall")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
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
                .pattern("x#x")
                .pattern("#x#")
                .define('#', AllItems.WOODEN_FRAME.get())
                .define('x', AllItems.COGWHEEL_SMALL.get())
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

        //Heavy Bronze Stamp
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.HEAVY_BRONZE_STAMP.get(), 1)
                .pattern("I")
                .pattern("#")
                .pattern("#")
                .define('I', AllItems.AXLE.get())
                .define('#', AllItems.BRONZE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":heavy_bronze_stamp")
                .unlockedBy("has_" + getHasName(AllItems.CRUSHED_BRONZE.get()), has(AllItems.CRUSHED_BRONZE.get()))
                .save(consumer);

        //Iron Screw
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.IRON_SCREW.get(), 1)
                .pattern("-l ")
                .pattern(" l-")
                .pattern("-l ")
                .define('l', AllItems.IRON_ROD.get())
                .define('-', Items.IRON_INGOT)
                .group(NotEnoughMachines.MOD_ID + ":iron_screw")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

        //Kaolin
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.KAOLIN.get(), 1)
                .requires(AllItems.FLUX.get(), 3)
                .requires(Items.CLAY_BALL, 1)
                .group(NotEnoughMachines.MOD_ID + ":kaolin")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
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

        //Linseed Oil
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.LINSEED_OIL.get(), 1)
                .requires(AllItems.FLAXSEED.get(), 6)
                .requires(Items.GLASS_BOTTLE, 1)
                .group(NotEnoughMachines.MOD_ID + ":linseed_oil")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

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

        //Polished Fluxstone Slab
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.POLISHED_FLUXSTONE_SLAB.get(), 6)
                .pattern("===")
                .define('=', AllItems.POLISHED_FLUXSTONE.get())
                .group(NotEnoughMachines.MOD_ID + ":polished_fluxstone_slab")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Polished Fluxstone Stairs
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.POLISHED_FLUXSTONE_STAIRS.get(), 4)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.POLISHED_FLUXSTONE.get())
                .group(NotEnoughMachines.MOD_ID + ":polished_fluxstone_stairs")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Polished Fluxstone Wall
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AllItems.POLISHED_FLUXSTONE_WALL.get(), 6)
                .pattern("===")
                .pattern("===")
                .define('=', AllItems.POLISHED_FLUXSTONE.get())
                .group(NotEnoughMachines.MOD_ID + ":polished_fluxstone_wall")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Soul Torch From Coke
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.SOUL_TORCH, 4)
                .pattern("o")
                .pattern("|")
                .pattern("#")
                .define('o', AllItems.COKE.get())
                .define('|', Items.STICK)
                .define('#', Ingredient.of(Items.SOUL_SAND, Items.SOUL_SOIL))
                .group(NotEnoughMachines.MOD_ID + ":soul_torch")
                .unlockedBy("has_" + getHasName(AllItems.COKE.get()), has(AllItems.COKE.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":soul_torch_from_coke");

        //String from Flax String
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STRING, 3)
                .pattern("# ")
                .pattern("##")
                .define('#', AllItems.FLAX_STRING.get())
                .group(NotEnoughMachines.MOD_ID + ":string")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":string_from_flax_string");

        //Trip Hammer
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.TRIP_HAMMER.get(), 1)
                .pattern("#I#")
                .pattern("#X#")
                .pattern("#=#")
                .define('#', AllItems.WOODEN_FRAME.get())
                .define('I', AllItems.AXLE.get())
                .define('X', AllItems.HEAVY_BRONZE_STAMP.get())
                .define('=', AllItems.BRONZE_PLATE.get())
                .group(NotEnoughMachines.MOD_ID + ":trip_hammer")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Torch From Coke
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.TORCH, 4)
                .pattern("o")
                .pattern("|")
                .define('o', AllItems.COKE.get())
                .define('|', Items.STICK)
                .group(NotEnoughMachines.MOD_ID + ":torch")
                .unlockedBy("has_" + getHasName(AllItems.COKE.get()), has(AllItems.COKE.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":torch_from_coke");

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
        simpleOreSmelting(consumer, "bread", AllItems.FLOUR.get(), Items.BREAD, Items.WHEAT, RecipeCategory.FOOD, 0.35F, 200);
        simpleOreSmoking(consumer, "bread", AllItems.FLOUR.get(), Items.BREAD, Items.WHEAT, RecipeCategory.FOOD, 0.35F, 100);

        //Ingots
        simpleOreSmelting(consumer, "bronze_ingot", AllItems.CRUSHED_BRONZE.get(), AllItems.BRONZE_INGOT.get(), AllItems.RAW_TIN.get(), RecipeCategory.MISC, 1.0F, 200);
        simpleOreBlasting(consumer, "bronze_ingot", AllItems.CRUSHED_BRONZE.get(), AllItems.BRONZE_INGOT.get(), AllItems.RAW_TIN.get(), RecipeCategory.MISC, 1.0F, 100);
        simpleOreSmelting(consumer, "copper_ingot", AllItems.CRUSHED_COPPER_ORE.get(), Items.COPPER_INGOT, Items.RAW_COPPER, RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(consumer, "copper_ingot", AllItems.CRUSHED_COPPER_ORE.get(), Items.COPPER_INGOT, Items.RAW_COPPER, RecipeCategory.MISC, 0.7F, 100);
        simpleOreSmelting(consumer, "fire_brick", AllItems.KAOLIN.get(), AllItems.FIRE_BRICK.get(), AllItems.FLUXSTONE.get(), RecipeCategory.BUILDING_BLOCKS, 0.15F, 200);
        simpleOreSmelting(consumer, "gold_ingot", AllItems.CRUSHED_GOLD_ORE.get(), Items.GOLD_INGOT, Items.RAW_GOLD, RecipeCategory.MISC, 1.0F, 200);
        simpleOreBlasting(consumer, "gold_ingot", AllItems.CRUSHED_GOLD_ORE.get(), Items.GOLD_INGOT, Items.RAW_GOLD, RecipeCategory.MISC, 1.0F, 100);
        simpleOreSmelting(consumer, "iron_ingot", AllItems.CRUSHED_IRON_ORE.get(), Items.IRON_INGOT, Items.RAW_IRON, RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(consumer, "iron_ingot", AllItems.CRUSHED_IRON_ORE.get(), Items.IRON_INGOT, Items.RAW_IRON, RecipeCategory.MISC, 0.7F, 100);
        simpleOreSmelting(consumer, "tin_ingot", AllItems.CRUSHED_TIN_ORE.get(), AllItems.TIN_INGOT.get(), AllItems.RAW_TIN.get(), RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(consumer, "tin_ingot", AllItems.CRUSHED_TIN_ORE.get(), AllItems.TIN_INGOT.get(), AllItems.RAW_TIN.get(), RecipeCategory.MISC, 0.7F, 100);
        simpleOreSmelting(consumer, "tin_ingot", AllItems.RAW_TIN.get(), AllItems.TIN_INGOT.get(), AllItems.RAW_TIN.get(), RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(consumer, "tin_ingot", AllItems.RAW_TIN.get(), AllItems.TIN_INGOT.get(), AllItems.RAW_TIN.get(), RecipeCategory.MISC, 0.7F, 100);
    }

    protected void buildStoneCuttingRecipes(Consumer<FinishedRecipe> consumer)
    {
        simpleStoneCutting(consumer, "fire_bricks_slab", Ingredient.of(AllItems.FIRE_BRICKS.get()), AllItems.FIRE_BRICKS_SLAB.get(), 2, RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE.get());
        simpleStoneCutting(consumer, "fire_bricks_stairs", Ingredient.of(AllItems.FIRE_BRICKS.get()), AllItems.FIRE_BRICKS_STAIRS.get(), 1, RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE.get());
        simpleStoneCutting(consumer, "fire_bricks_wall", Ingredient.of(AllItems.FIRE_BRICKS.get()), AllItems.FIRE_BRICKS_WALL.get(), 1, RecipeCategory.DECORATIONS, AllItems.FLUXSTONE.get());
        simpleStoneCutting(consumer, "fluxstone_slab", Ingredient.of(AllItems.FLUXSTONE.get()), AllItems.FLUXSTONE_SLAB.get(), 2, RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE.get());
        simpleStoneCutting(consumer, "fluxstone_stairs", Ingredient.of(AllItems.FLUXSTONE.get()), AllItems.FLUXSTONE_STAIRS.get(), 1, RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE.get());
        simpleStoneCutting(consumer, "fluxstone_wall", Ingredient.of(AllItems.FLUXSTONE.get()), AllItems.FLUXSTONE_WALL.get(), 1, RecipeCategory.DECORATIONS, AllItems.FLUXSTONE.get());
        simpleStoneCutting(consumer, "polished_fluxstone_slab", Ingredient.of(AllItems.POLISHED_FLUXSTONE.get()), AllItems.POLISHED_FLUXSTONE_SLAB.get(), 2, RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE.get());
        simpleStoneCutting(consumer, "polished_fluxstone_stairs", Ingredient.of(AllItems.POLISHED_FLUXSTONE.get()), AllItems.POLISHED_FLUXSTONE_STAIRS.get(), 1, RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE.get());
        simpleStoneCutting(consumer, "polished_fluxstone_wall", Ingredient.of(AllItems.POLISHED_FLUXSTONE.get()), AllItems.POLISHED_FLUXSTONE_WALL.get(), 1, RecipeCategory.DECORATIONS, AllItems.FLUXSTONE.get());
    }

    protected void buildMillingRecipes(Consumer<FinishedRecipe> consumer)
    {
        //Dust
        simpleMilling(consumer, "blaze_powder", Items.BLAZE_ROD, Items.BLAZE_POWDER, 4, 200);
        simpleMilling(consumer, "bone_meal", Items.BONE, Items.BONE_MEAL, 6, 200);
        simpleMilling(consumer, "copper_dust", Items.RAW_COPPER, AllItems.CRUSHED_COPPER_ORE.get(), 1, 600);
        simpleMilling(consumer, "flour", Items.WHEAT, AllItems.FLOUR.get(), 1, 200);
        simpleMilling(consumer, "flux", AllItems.FLUXSTONE.get(), AllItems.FLUX.get(), 1, 400);
        simpleMilling(consumer, "glowstone_dust", Items.GLOWSTONE, Items.GLOWSTONE_DUST, 2, 400);
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

        //Misc
        simpleMilling(consumer, "flax_string", AllItems.FLAX.get(), AllItems.FLAX_STRING.get(), 3, 200);
        simpleMilling(consumer, "flint", Items.GRAVEL, Items.FLINT, 1, 400);
    }

    protected void buildStampingRecipes(Consumer<FinishedRecipe> consumer)
    {
        //Building Blocks
        simpleStamping(consumer, "clay_ball", Ingredient.of(Items.SAND, Items.RED_SAND), Items.CLAY_BALL, 6, 200);
        simpleStamping(consumer, "cobblestone", Ingredient.of(Items.STONE, Items.CRACKED_STONE_BRICKS, Items.SMOOTH_STONE), Items.COBBLESTONE, 1, 200);
        simpleStamping(consumer, "cracked_stone_bricks", Ingredient.of(Items.STONE_BRICKS, Items.CHISELED_STONE_BRICKS), Items.CRACKED_STONE_BRICKS, 1, 200);
        simpleStamping(consumer, "gravel", Ingredient.of(Items.COBBLESTONE), Items.GRAVEL, 1, 200);
        simpleStamping(consumer, "mossy_cobblestone", Ingredient.of(Items.MOSSY_STONE_BRICKS), Items.MOSSY_COBBLESTONE, 1, 200);
        simpleStamping(consumer, "red_sand", Ingredient.of(Items.RED_SANDSTONE, Items.CHISELED_RED_SANDSTONE, Items.CUT_RED_SANDSTONE, Items.SMOOTH_RED_SANDSTONE), Items.RED_SAND, 1, 200);
        simpleStamping(consumer, "sand", Ingredient.of(Items.SANDSTONE, Items.CHISELED_SANDSTONE, Items.SMOOTH_SANDSTONE, Items.CUT_SANDSTONE), Items.SAND, 1, 200);
        simpleStamping(consumer, "sand", Items.GRAVEL, Items.SAND, 1, Items.FLINT, 2, 200);

        //Dust
        simpleStamping(consumer, "copper_dust", Items.RAW_COPPER, AllItems.CRUSHED_COPPER_ORE.get(), 2, 400);
        simpleStamping(consumer, "gold_dust", Items.RAW_GOLD, AllItems.CRUSHED_GOLD_ORE.get(), 2, 400);
        simpleStamping(consumer, "iron_dust", Items.RAW_IRON, AllItems.CRUSHED_IRON_ORE.get(), 2, 400);
        simpleStamping(consumer, "tin_dust", AllItems.RAW_TIN.get(), AllItems.CRUSHED_TIN_ORE.get(), 2, 400);

        //Raw Ore
        simpleStamping(consumer, "raw_copper", Ingredient.of(Items.COPPER_ORE, Items.DEEPSLATE_COPPER_ORE), Items.RAW_COPPER, 6, 400);
        simpleStamping(consumer, "raw_gold", Ingredient.of(Items.GOLD_ORE, Items.DEEPSLATE_GOLD_ORE), Items.RAW_GOLD, 2, 400);
        simpleStamping(consumer, "raw_iron", Ingredient.of(Items.IRON_ORE, Items.DEEPSLATE_IRON_ORE), Items.RAW_IRON, 2, 400);
        simpleStamping(consumer, "raw_tin", Ingredient.of(AllItems.TIN_ORE.get(), Items.DEEPSLATE_IRON_ORE), AllItems.RAW_TIN.get(), 2, 400);

        //Other Minerals
        simpleStamping(consumer, "amethyst_shard", Ingredient.of(Items.AMETHYST_CLUSTER), Items.AMETHYST_SHARD, 7, 200);
        simpleStamping(consumer, "coal", Ingredient.of(Items.COAL_ORE, Items.DEEPSLATE_COAL_ORE), Items.COAL, 3, 400);
        simpleStamping(consumer, "diamond", Ingredient.of(Items.DIAMOND_ORE, Items.DEEPSLATE_DIAMOND_ORE), Items.DIAMOND, 3, 600);
        simpleStamping(consumer, "emerald", Ingredient.of(Items.EMERALD_ORE, Items.DEEPSLATE_EMERALD_ORE), Items.EMERALD, 3, 600);
        simpleStamping(consumer, "flux", AllItems.FLUXSTONE.get(), AllItems.FLUX.get(), 3, 200);
        simpleStamping(consumer, "glowstone_dust", Items.GLOWSTONE, Items.GLOWSTONE_DUST, 4, 200);
        simpleStamping(consumer, "glowstone_dust", Items.REDSTONE_LAMP, Items.GLOWSTONE_DUST, 4, Items.REDSTONE, 4, 200);
        simpleStamping(consumer, "gold_nugget", Items.NETHER_GOLD_ORE, Items.GOLD_NUGGET, 7, 400);
        simpleStamping(consumer, "lapis_lazuli", Ingredient.of(Items.LAPIS_ORE, Items.DEEPSLATE_LAPIS_ORE), Items.LAPIS_LAZULI, 11, 400);
        simpleStamping(consumer, "quartz", Ingredient.of(Items.NETHER_QUARTZ_ORE), Items.QUARTZ, 2, 200);
        simpleStamping(consumer, "quartz", Items.DAYLIGHT_DETECTOR, Items.QUARTZ, 3, Items.SAND, 3, 200);
        simpleStamping(consumer, "redstone", Ingredient.of(Items.REDSTONE_ORE, Items.DEEPSLATE_REDSTONE_ORE), Items.REDSTONE, 6, 400);
    }

    protected void buildRollingRecipes(Consumer<FinishedRecipe> consumer)
    {
        simpleRolling(consumer, "bronze_plate", Ingredient.of(AllItems.BRONZE_INGOT.get()), AllItems.BRONZE_PLATE.get(), 1, 300);
        simpleRolling(consumer, "bronze_rod", Ingredient.of(AllItems.BRONZE_PLATE.get()), AllItems.BRONZE_ROD.get(), 3, 300);
        simpleRolling(consumer, "copper_plate", Ingredient.of(Items.COPPER_INGOT), AllItems.COPPER_PLATE.get(), 1, 200);
        simpleRolling(consumer, "copper_rod", Ingredient.of(AllItems.COPPER_PLATE.get()), AllItems.COPPER_ROD.get(), 3, 200);
        simpleRolling(consumer, "iron_plate", Ingredient.of(Items.IRON_INGOT), AllItems.IRON_PLATE.get(), 1, 300);
        simpleRolling(consumer, "iron_rod", Ingredient.of(AllItems.IRON_PLATE.get()), AllItems.IRON_ROD.get(), 3, 300);
        simpleRolling(consumer, "sponge_dry", Ingredient.of(Items.WET_SPONGE), Items.SPONGE, 1, 200);
    }

    public static void buildCokeOvenRecipes(Consumer<FinishedRecipe> consumer)
    {
        simpleCoking(consumer, "charcoal", Ingredient.of(ItemTags.LOGS), Items.CHARCOAL, 1, 600);
        simpleCoking(consumer, "coke", Ingredient.of(Items.COAL), AllItems.COKE.get(), 1, 1800);
        simpleCoking(consumer, "coke_block", Ingredient.of(Items.COAL_BLOCK), AllItems.COKE_BLOCK.get(), 1, 16200);
    }

    public static void simpleCoking(Consumer<FinishedRecipe> consumer, String group, Ingredient ingredient, ItemLike result, int count, int processingTime)
    {
        SingleResultMachineRecipeBuilder.coking(ingredient, result, count, processingTime)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":coking/" + getItemName(result) + "_from_coking");
    }

    public static void simpleMilling(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike result, int count, int processingTime)
    {
        SingleResultMachineRecipeBuilder.milling(Ingredient.of(ingredient), result, count, processingTime)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":milling/" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_milling");
    }

    public static void simpleMilling(Consumer<FinishedRecipe> consumer, String group, Ingredient ingredient, ItemLike result, int count, int processingTime)
    {
        SingleResultMachineRecipeBuilder.milling(ingredient, result, count, processingTime)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":milling/" + getItemName(result) + "_from_milling");
    }

    public static void simpleOreBlasting(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":cooking/" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_blasting");
    }

    public static void simpleOreSmelting(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":cooking/" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_smelting");
    }

    public static void simpleOreSmoking(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike result, ItemLike unlockedBy, RecipeCategory category, float experience, int cookingTime)
    {
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), category, result, experience, cookingTime)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":cooking/" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_smoking");
    }

    public static void simpleRolling(Consumer<FinishedRecipe> consumer, String group, Ingredient ingredient, ItemLike result, int count, int processingTime)
    {
        SingleResultMachineRecipeBuilder.rolling(ingredient, result, count, processingTime)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":rolling/" + getItemName(result) + "_from_rolling");
    }

    public static void simpleStamping(Consumer<FinishedRecipe> consumer, String group, Ingredient ingredient, ItemLike resultPrimary, int countPrimary, int processingTime)
    {
        DoubleResultMachineRecipeBuilder.stamping(ingredient, resultPrimary, countPrimary, processingTime)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":stamping/" + getItemName(resultPrimary) + "_from_stamping");
    }

    public static void simpleStamping(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike resultPrimary, int countPrimary, int processingTime)
    {
        DoubleResultMachineRecipeBuilder.stamping(Ingredient.of(ingredient), resultPrimary, countPrimary, processingTime)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":stamping/" + getItemName(resultPrimary) + "_from_" + getItemName(ingredient) + "_from_stamping");
    }

    public static void simpleStamping(Consumer<FinishedRecipe> consumer, String group, Ingredient ingredient, ItemLike resultPrimary, int countPrimary, ItemLike resultSecondary, int countSecondary, int processingTime)
    {
        DoubleResultMachineRecipeBuilder.stamping(ingredient, resultPrimary, countPrimary, processingTime)
                .addSecondaryResult(resultSecondary, countSecondary)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":stamping/" + getItemName(resultPrimary) + "_from_stamping");
    }

    public static void simpleStamping(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike resultPrimary, int countPrimary, ItemLike resultSecondary, int countSecondary, int processingTime)
    {
        DoubleResultMachineRecipeBuilder.stamping(Ingredient.of(ingredient), resultPrimary, countPrimary, processingTime)
                .addSecondaryResult(resultSecondary, countSecondary)
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":stamping/" + getItemName(resultPrimary) + "_from_" + getItemName(ingredient)  + "_from_stamping");
    }

    public static void simpleStoneCutting(Consumer<FinishedRecipe> consumer, String group, Ingredient ingredient, ItemLike result, int count, RecipeCategory category, ItemLike unlockedBy)
    {
        SingleItemRecipeBuilder.stonecutting(ingredient, category, result, count)
                .unlockedBy("has_" + getItemName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":cutting/" + getItemName(result) + "_from_cutting");
    }
}
