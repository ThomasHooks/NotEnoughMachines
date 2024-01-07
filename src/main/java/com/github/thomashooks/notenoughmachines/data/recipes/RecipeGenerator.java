package com.github.thomashooks.notenoughmachines.data.recipes;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.data.recipes.builders.DoubleResultMachineRecipeBuilder;
import com.github.thomashooks.notenoughmachines.data.recipes.builders.SingleResultMachineRecipeBuilder;
import com.github.thomashooks.notenoughmachines.integration.tags.AllTags;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider implements IConditionBuilder
{
    public RecipeGenerator(PackOutput packOutput)
    {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer)
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
        LOGGER.debug(NotEnoughMachines.MOD_ID + ":building all shaped and shapeless recipes");

        //Items
        //--------------------------------------------------------------------------------------------------------------

        //Bronze Ingot
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.BRONZE_INGOT.get(), 9)
                .requires(AllItems.BRONZE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":bronze_ingot")
                .unlockedBy("has_" + getHasName(AllItems.CRUSHED_BRONZE.get()), has(AllItems.CRUSHED_BRONZE.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":bronze_ingot_from_bronze_block");

        //Bronze Plate
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.BRONZE_PLATE.get(), 4)
                .requires(AllItems.BRONZE_PLATE_BLOCK.get(), 1)
                .group(NotEnoughMachines.MOD_ID + ":bronze_plate")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":bronze_plate_from_bronze_plate_block");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.BRONZE_PLATE.get(), 2)
                .requires(AllItems.BRONZE_PLATE_SLAB.get(), 1)
                .group(NotEnoughMachines.MOD_ID + ":bronze_plate")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":bronze_plate_from_bronze_plate_slab");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.BRONZE_PLATE.get(), 4)
                .requires(AllItems.BRONZE_PLATE_STAIRS.get(), 1)
                .group(NotEnoughMachines.MOD_ID + ":bronze_plate")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":bronze_plate_from_bronze_plate_stairs");

        //Coke
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.COKE.get(), 9)
                .requires(AllItems.COKE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":coke")
                .unlockedBy("has_" + getHasName(AllItems.COKE.get()), has(AllItems.COKE.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":coke_from_coke_block");

        //Copper Plate
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.COPPER_PLATE.get(), 4)
                .requires(Ingredient.of(AllTags.Items.COPPER_PLATE_BLOCKS), 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":copper_plate_from_copper_plate_block");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.COPPER_PLATE.get(), 2)
                .requires(Ingredient.of(AllTags.Items.COPPER_PLATE_SLABS), 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":copper_plate_from_copper_plate_slab");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.COPPER_PLATE.get(), 4)
                .requires(Ingredient.of(AllTags.Items.COPPER_PLATE_STAIRS), 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":copper_plate_from_copper_plate_stairs");

        //Crushed Bronze
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.CRUSHED_BRONZE.get(), 2)
                .requires(Ingredient.of(AllTags.Items.COPPER_DUST), 3)
                .requires(Ingredient.of(AllTags.Items.TIN_DUST), 1)
                .requires(AllItems.FLUX.get(), 2)
                .group(NotEnoughMachines.MOD_ID + ":crushed_bronze")
                .unlockedBy("has_" + getHasName(AllItems.RAW_TIN.get()), has(AllItems.RAW_TIN.get()))
                .save(consumer);

        //Crushed Vermilion
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.CRUSHED_VERMILION.get(), 1)
                .requires(Ingredient.of(AllTags.Items.COPPER_DUST), 1)
                .requires(Items.REDSTONE, 4)
                .requires(AllItems.FLUX.get(), 1)
                .group(NotEnoughMachines.MOD_ID + ":crushed_vermilion")
                .unlockedBy("has_" + getHasName(Items.REDSTONE), has(Items.REDSTONE))
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
                .pattern("# ")
                .pattern("##")
                .define('#', AllItems.FLAX.get())
                .group(NotEnoughMachines.MOD_ID + ":flax_string")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Gear
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.GEAR.get(), 4)
                .pattern(" # ")
                .pattern("#b#")
                .pattern(" # ")
                .define('#', Items.STICK)
                .define('b', AllItems.LINSEED_OIL.get())
                .group(NotEnoughMachines.MOD_ID + ":gear")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Gunpowder
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 1)
                .requires(AllItems.FLUX.get(), 2)
                .requires(Items.CHARCOAL, 1)
                .group(NotEnoughMachines.MOD_ID + ":gunpowder")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":gunpowder_from_flux_and_charcoal");

        //Gold Plate
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.GOLD_PLATE.get(), 4)
                .requires(AllItems.GOLD_PLATE_BLOCK.get(), 1)
                .group(NotEnoughMachines.MOD_ID + ":gold_plate")
                .unlockedBy("has_" + getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":gold_plate_from_gold_plate_block");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.GOLD_PLATE.get(), 2)
                .requires(AllItems.GOLD_PLATE_SLAB.get(), 1)
                .group(NotEnoughMachines.MOD_ID + ":gold_plate")
                .unlockedBy("has_" + getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":gold_plate_from_gold_plate_slab");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.GOLD_PLATE.get(), 4)
                .requires(AllItems.GOLD_PLATE_STAIRS.get(), 1)
                .group(NotEnoughMachines.MOD_ID + ":gold_plate")
                .unlockedBy("has_" + getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":gold_plate_from_gold_plate_stairs");

        //Iron Plate
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.IRON_PLATE.get(), 4)
                .requires(AllItems.IRON_PLATE_BLOCK.get(), 1)
                .group(NotEnoughMachines.MOD_ID + ":iron_plate")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":iron_plate_from_iron_plate_block");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.IRON_PLATE.get(), 2)
                .requires(AllItems.IRON_PLATE_SLAB.get(), 1)
                .group(NotEnoughMachines.MOD_ID + ":iron_plate")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":iron_plate_from_iron_plate_slab");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.IRON_PLATE.get(), 4)
                .requires(AllItems.IRON_PLATE_STAIRS.get(), 1)
                .group(NotEnoughMachines.MOD_ID + ":iron_plate")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":iron_plate_from_iron_plate_stairs");

        //Iron Rolls
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.IRON_ROLLS.get(), 1)
                .pattern("x#x")
                .pattern("x#x")
                .define('#', Items.IRON_BLOCK)
                .define('x', AllItems.GEAR.get())
                .group(NotEnoughMachines.MOD_ID + ":iron_rolls")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

        //Iron Screw
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.IRON_SCREW.get(), 1)
                .pattern("-l ")
                .pattern(" l-")
                .pattern("-l ")
                .define('l', AllTags.Items.IRON_RODS)
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.LINEN.get(), 4)
                .requires(AllTags.Items.LINEN_BLOCKS)
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

        //Raw Tin
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.RAW_TIN.get(), 9)
                .requires(AllItems.RAW_TIN_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":raw_tin")
                .unlockedBy("has_" + getHasName(AllItems.RAW_TIN.get()), has(AllItems.RAW_TIN.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":raw_tin_from_raw_tin_block");

        //Redstone Valve
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.REDSTONE_VALVE.get(), 1)
                .pattern("|p|")
                .pattern("|p|")
                .pattern("#e#")
                .define('|', Items.GLASS)
                .define('p', AllItems.VERMILION_PLATE.get())
                .define('#', AllTags.Items.TIN_PLATES)
                .define('e', Items.REDSTONE_TORCH)
                .group(NotEnoughMachines.MOD_ID + ":redstone_valve")
                .unlockedBy("has_" + getHasName(Items.REDSTONE), has(Items.REDSTONE))
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

        //Tin Ingot
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.TIN_INGOT.get(), 9)
                .requires(AllBlocks.TIN_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":tin_ingot")
                .unlockedBy("has_" + getHasName(AllItems.RAW_TIN.get()), has(AllItems.RAW_TIN.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":tin_ingot_from_tin_block");

        //Tin Plate
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.TIN_PLATE.get(), 4)
                .requires(AllItems.TIN_PLATE_BLOCK.get(), 1)
                .group(NotEnoughMachines.MOD_ID + ":tin_plate")
                .unlockedBy("has_" + getHasName(AllItems.TIN_INGOT.get()), has(AllItems.TIN_INGOT.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":tin_plate_from_tin_plate_block");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.TIN_PLATE.get(), 2)
                .requires(AllItems.TIN_PLATE_SLAB.get(), 1)
                .group(NotEnoughMachines.MOD_ID + ":tin_plate")
                .unlockedBy("has_" + getHasName(AllItems.TIN_INGOT.get()), has(AllItems.TIN_INGOT.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":tin_plate_from_tin_plate_slab");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.TIN_PLATE.get(), 4)
                .requires(AllItems.TIN_PLATE_STAIRS.get(), 1)
                .group(NotEnoughMachines.MOD_ID + ":tin_plate")
                .unlockedBy("has_" + getHasName(AllItems.TIN_INGOT.get()), has(AllItems.TIN_INGOT.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":tin_plate_from_tin_plate_stairs");

        //Torch From Coke
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.TORCH, 4)
                .pattern("o")
                .pattern("|")
                .define('o', AllItems.COKE.get())
                .define('|', Items.STICK)
                .group(NotEnoughMachines.MOD_ID + ":torch")
                .unlockedBy("has_" + getHasName(AllItems.COKE.get()), has(AllItems.COKE.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":torch_from_coke");

        //Vermilion Ingot
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.VERMILION_INGOT.get(), 9)
                .requires(AllItems.VERMILION_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":vermilion_ingot")
                .unlockedBy("has_" + getHasName(AllItems.CRUSHED_VERMILION.get()), has(AllItems.CRUSHED_VERMILION.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":vermilion_ingot_from_vermilion_block");

        //Wind Wheel Blade
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.WIND_WHEEL_BLADE.get(), 1)
                .pattern("xx#")
                .pattern("xx#")
                .pattern("xx#")
                .define('#', AllItems.AXLE.get())
                .define('x', Items.STICK)
                .group(NotEnoughMachines.MOD_ID + ":wind_wheel_blade")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Wind Wheel Sail
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.WIND_WHEEL_SAIL.get(), 1)
                .pattern("###")
                .pattern("#x#")
                .pattern("###")
                .define('x', AllItems.WIND_WHEEL_BLADE.get())
                .define('#', AllItems.LINEN.get())
                .group(NotEnoughMachines.MOD_ID + ":wind_wheel_sail")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Armor
        //--------------------------------------------------------------------------------------------------------------

        //Padded Boots
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AllItems.PADDED_BOOTS.get(), 1)
                .pattern("# #")
                .pattern("# #")
                .define('#', AllItems.LINEN.get())
                .group(NotEnoughMachines.MOD_ID + ":padded_boots")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Padded Chestplate
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AllItems.PADDED_CHESTPLATE.get(), 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.LINEN.get())
                .group(NotEnoughMachines.MOD_ID + ":padded_chestplate")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Padded Helmet
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AllItems.PADDED_HELMET.get(), 1)
                .pattern("###")
                .pattern("# #")
                .define('#', AllItems.LINEN.get())
                .group(NotEnoughMachines.MOD_ID + ":padded_helmet")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Padded Leggings
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AllItems.PADDED_LEGGINGS.get(), 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', AllItems.LINEN.get())
                .group(NotEnoughMachines.MOD_ID + ":padded_leggings")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Tools
        //--------------------------------------------------------------------------------------------------------------

        //Item Pouch
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AllItems.ITEM_POUCH.get(), 1)
                .pattern("s s")
                .pattern("# #")
                .pattern("###")
                .define('#', AllItems.LINEN.get())
                .define('s', AllItems.FLAX_STRING.get())
                .group(NotEnoughMachines.MOD_ID + ":item_pouch")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Prospector's Pick
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AllItems.PROSPECTORS_PICK.get(), 1)
                .pattern("bbb")
                .pattern(" sb")
                .pattern(" s ")
                .define('b', AllTags.Items.BRONZE_INGOTS)
                .define('s', Items.STICK)
                .group(NotEnoughMachines.MOD_ID + ":prospectors_pick")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);

        //Weapons
        //--------------------------------------------------------------------------------------------------------------

        //Bronze Mace
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AllItems.BRONZE_MACE.get(), 1)
                .pattern("b")
                .pattern("s")
                .pattern("s")
                .define('b', AllItems.BRONZE_BLOCK.get())
                .define('s', Items.STICK)
                .group(NotEnoughMachines.MOD_ID + ":bronze_mace")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);

        //Full Blocks
        //--------------------------------------------------------------------------------------------------------------

        //Bronze Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.BRONZE_BLOCK.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.BRONZE_INGOT.get())
                .group(NotEnoughMachines.MOD_ID + ":bronze_block")
                .unlockedBy("has_" + getHasName(AllItems.CRUSHED_BRONZE.get()), has(AllItems.CRUSHED_BRONZE.get()))
                .save(consumer);

        //Bronze Plate Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.BRONZE_PLATE_BLOCK.get(), 1)
                .pattern("##")
                .pattern("##")
                .define('#', AllTags.Items.BRONZE_PLATES)
                .group(NotEnoughMachines.MOD_ID + ":bronze_plate_block")
                .unlockedBy("has_" + getHasName(AllItems.CRUSHED_BRONZE.get()), has(AllItems.CRUSHED_BRONZE.get()))
                .save(consumer);

        //Coke Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.COKE_BLOCK.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.COKE.get())
                .group(NotEnoughMachines.MOD_ID + ":coke_block")
                .unlockedBy("has_" + getHasName(AllItems.COKE.get()), has(AllItems.COKE.get()))
                .save(consumer);

        //Copper Plate Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_BLOCK.get(), 1)
                .pattern("##")
                .pattern("##")
                .define('#', AllTags.Items.COPPER_PLATES)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_block")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Waxed Copper Plate Block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_BLOCK_WAXED.get(), 1)
                .requires(AllItems.COPPER_PLATE_BLOCK.get(), 1)
                .requires(Items.HONEYCOMB, 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_block_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Exposed Waxed Copper Plate Block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_BLOCK_EXPOSED_WAXED.get(), 1)
                .requires(AllItems.COPPER_PLATE_BLOCK_EXPOSED.get(), 1)
                .requires(Items.HONEYCOMB, 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_block_exposed_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Weathered Waxed Copper Plate Block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_BLOCK_WEATHERED_WAXED.get(), 1)
                .requires(AllItems.COPPER_PLATE_BLOCK_WEATHERED.get(), 1)
                .requires(Items.HONEYCOMB, 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_block_weathered_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Oxidized Waxed Copper Plate Block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_BLOCK_OXIDIZED_WAXED.get(), 1)
                .requires(AllItems.COPPER_PLATE_BLOCK_OXIDIZED.get(), 1)
                .requires(Items.HONEYCOMB, 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_block_oxidized_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Fire Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.FIRE_BRICKS.get(), 1)
                .pattern("##")
                .pattern("##")
                .define('#', AllItems.FIRE_BRICK.get())
                .group(NotEnoughMachines.MOD_ID + ":fire_bricks")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Gold Plate Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.GOLD_PLATE_BLOCK.get(), 1)
                .pattern("##")
                .pattern("##")
                .define('#', AllTags.Items.GOLD_PLATES)
                .group(NotEnoughMachines.MOD_ID + ":gold_plate_block")
                .unlockedBy("has_" + getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer);

        //Linen Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.LINEN_BLOCK.get(), 1)
                .pattern("##")
                .pattern("##")
                .define('#', AllItems.LINEN.get())
                .group(NotEnoughMachines.MOD_ID + ":linen_block")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_WHITE.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.WHITE_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_white")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_ORANGE.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.ORANGE_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_orange")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_MAGENTA.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.MAGENTA_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_magenta")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_LIGHT_BLUE.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.LIGHT_BLUE_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_light_blue")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_YELLOW.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.YELLOW_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_yellow")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_LIME.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.LIME_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_lime")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_PINK.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.PINK_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_pink")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_GRAY.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.GRAY_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_gray")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_LIGHT_GRAY.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.LIGHT_GRAY_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_light_gray")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_CYAN.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.CYAN_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_cyan")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_PURPLE.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.PURPLE_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_purple")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_BLUE.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.BLUE_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_blue")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_BROWN.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.BROWN_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_brown")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_GREEN.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.GREEN_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_green")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_RED.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.RED_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_red")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.LINEN_BLOCK_BLACK.get(), 1)
                .requires(AllTags.Items.LINEN_BLOCKS)
                .requires(Items.BLACK_DYE)
                .group(NotEnoughMachines.MOD_ID + ":linen_block_black")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Iron Plate Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.IRON_PLATE_BLOCK.get(), 1)
                .pattern("##")
                .pattern("##")
                .define('#', AllTags.Items.IRON_PLATES)
                .group(NotEnoughMachines.MOD_ID + ":iron_plate_block")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

        //Raw Tin Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.RAW_TIN_BLOCK.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.RAW_TIN.get())
                .group(NotEnoughMachines.MOD_ID + ":raw_tin_block")
                .unlockedBy("has_" + getHasName(AllItems.RAW_TIN.get()), has(AllItems.RAW_TIN.get()))
                .save(consumer);

        //Tin Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.TIN_BLOCK.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.TIN_INGOT.get())
                .group(NotEnoughMachines.MOD_ID + ":tin_block")
                .unlockedBy("has_" + getHasName(AllItems.RAW_TIN.get()), has(AllItems.RAW_TIN.get()))
                .save(consumer);

        //Tin Plate Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.TIN_PLATE_BLOCK.get(), 1)
                .pattern("##")
                .pattern("##")
                .define('#', AllTags.Items.TIN_PLATES)
                .group(NotEnoughMachines.MOD_ID + ":tin_plate_block")
                .unlockedBy("has_" + getHasName(AllItems.RAW_TIN.get()), has(AllItems.RAW_TIN.get()))
                .save(consumer);

        //Vermilion Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.VERMILION_BLOCK.get(), 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', AllItems.VERMILION_INGOT.get())
                .group(NotEnoughMachines.MOD_ID + ":vermilion_block")
                .unlockedBy("has_" + getHasName(AllItems.CRUSHED_VERMILION.get()), has(AllItems.CRUSHED_VERMILION.get()))
                .save(consumer);

        //Wooden Frame
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllBlocks.WOODEN_FRAME.get(), 4)
                .pattern("x#x")
                .pattern("#o#")
                .pattern("x#x")
                .define('#', AllTags.Items.STRIPPED_LOGS)
                .define('x', ItemTags.PLANKS)
                .define('o', AllItems.LINSEED_OIL.get())
                .group(NotEnoughMachines.MOD_ID + ":wooden_frame")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Slabs
        //--------------------------------------------------------------------------------------------------------------

        //Bronze Plate Slab
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.BRONZE_PLATE_SLAB.get(), 6)
                .pattern("===")
                .define('=', AllItems.BRONZE_PLATE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":bronze_plate_slab")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);

        //Copper Plate Slab
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_SLAB.get(), 6)
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_slab")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Copper Plate Slab Exposed
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_SLAB_EXPOSED.get(), 6)
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK_EXPOSED.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_slab_exposed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Copper Plate Slab Weathered
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_SLAB_WEATHERED.get(), 6)
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK_WEATHERED.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_slab_weathered")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Copper Plate Slab Oxidized
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_SLAB_OXIDIZED.get(), 6)
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK_OXIDIZED.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_slab_oxidized")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Copper Plate Slab Waxed
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_SLAB_WAXED.get(), 6)
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK_WAXED.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_slab_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Copper Plate Slab Exposed Waxed
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_SLAB_EXPOSED_WAXED.get(), 6)
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK_EXPOSED_WAXED.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_slab_exposed_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Copper Plate Slab Weathered Waxed
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_SLAB_WEATHERED_WAXED.get(), 6)
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK_WEATHERED_WAXED.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_slab_weathered_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Copper Plate Slab Oxidized Waxed
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_SLAB_OXIDIZED_WAXED.get(), 6)
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK_OXIDIZED_WAXED.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_slab_oxidized_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Waxed Copper Plate Slab
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_SLAB_WAXED.get(), 1)
                .requires(AllItems.COPPER_PLATE_SLAB.get(), 1)
                .requires(Items.HONEYCOMB, 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_slab_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":copper_plate_slab_waxed_from_honeycomb");

        //Exposed Waxed Copper Plate Slab
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_SLAB_EXPOSED_WAXED.get(), 1)
                .requires(AllItems.COPPER_PLATE_SLAB_EXPOSED.get(), 1)
                .requires(Items.HONEYCOMB, 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_slab_exposed_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":copper_plate_slab_exposed_waxed_from_honeycomb");

        //Weathered Waxed Copper Plate Slab
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_SLAB_WEATHERED_WAXED.get(), 1)
                .requires(AllItems.COPPER_PLATE_SLAB_WEATHERED.get(), 1)
                .requires(Items.HONEYCOMB, 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_slab_weathered_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":copper_plate_slab_weathered_waxed_from_honeycomb");

        //Oxidized Waxed Copper Plate Slab
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_SLAB_OXIDIZED_WAXED.get(), 1)
                .requires(AllItems.COPPER_PLATE_SLAB_OXIDIZED.get(), 1)
                .requires(Items.HONEYCOMB, 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_slab_oxidized_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":copper_plate_slab_oxidized_waxed_from_honeycomb");

        //Fire Bricks Slab
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.FIRE_BRICKS_SLAB.get(), 6)
                .pattern("===")
                .define('=', AllItems.FIRE_BRICKS.get())
                .group(NotEnoughMachines.MOD_ID + ":fire_bricks_slab")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Fluxstone Slab
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE_SLAB.get(), 6)
                .pattern("===")
                .define('=', AllItems.FLUXSTONE.get())
                .group(NotEnoughMachines.MOD_ID + ":fluxstone_slab")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Gold Plate Slab
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.GOLD_PLATE_SLAB.get(), 6)
                .pattern("===")
                .define('=', AllItems.GOLD_PLATE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":gold_plate_slab")
                .unlockedBy("has_" + getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer);

        //Iron Plate Slab
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.IRON_PLATE_SLAB.get(), 6)
                .pattern("===")
                .define('=', AllItems.IRON_PLATE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":iron_plate_slab")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

        //Polished Fluxstone Slab
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.POLISHED_FLUXSTONE_SLAB.get(), 6)
                .pattern("===")
                .define('=', AllItems.POLISHED_FLUXSTONE.get())
                .group(NotEnoughMachines.MOD_ID + ":polished_fluxstone_slab")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Tin Plate Slab
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.TIN_PLATE_SLAB.get(), 6)
                .pattern("===")
                .define('=', AllItems.TIN_PLATE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":tin_plate_slab")
                .unlockedBy("has_" + getHasName(AllItems.TIN_INGOT.get()), has(AllItems.TIN_INGOT.get()))
                .save(consumer);

        //Wooden Frame Slab
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.WOODEN_FRAME_SLAB.get(), 6)
                .pattern("===")
                .define('=', AllItems.WOODEN_FRAME.get())
                .group(NotEnoughMachines.MOD_ID + ":wooden_frame_slab")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Stairs
        //--------------------------------------------------------------------------------------------------------------

        //Bronze Plates Stairs
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.BRONZE_PLATE_STAIRS.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.BRONZE_PLATE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":bronze_plate_stairs")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);

        //Copper Plates Stairs
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_STAIRS.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_stairs")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Copper Plates Stairs Exposed
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_STAIRS_EXPOSED.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK_EXPOSED.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_stairs_exposed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Copper Plates Stairs Weathered
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_STAIRS_WEATHERED.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK_WEATHERED.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_stairs_weathered")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Copper Plates Stairs Oxidized
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_STAIRS_OXIDIZED.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK_OXIDIZED.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_stairs_oxidized")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Copper Plates Stairs Waxed
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_STAIRS_WAXED.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK_WAXED.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_stairs_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Copper Plates Stairs Exposed Waxed
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_STAIRS_EXPOSED_WAXED.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK_EXPOSED_WAXED.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_stairs_exposed_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Copper Plates Stairs Weathered Waxed
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_STAIRS_WEATHERED_WAXED.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK_WEATHERED_WAXED.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_stairs_weathered_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Copper Plates Stairs Oxidized Waxed
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_STAIRS_OXIDIZED_WAXED.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.COPPER_PLATE_BLOCK_OXIDIZED_WAXED.get())
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_stairs_oxidized_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer);

        //Waxed Copper Plate Stairs
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_STAIRS_WAXED.get(), 1)
                .requires(AllItems.COPPER_PLATE_STAIRS.get(), 1)
                .requires(Items.HONEYCOMB, 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_stairs_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":copper_plate_stairs_waxed_from_honeycomb");

        //Exposed Waxed Copper Plate Stairs
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_STAIRS_EXPOSED_WAXED.get(), 1)
                .requires(AllItems.COPPER_PLATE_STAIRS_EXPOSED.get(), 1)
                .requires(Items.HONEYCOMB, 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_stairs_exposed_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":copper_plate_stairs_exposed_waxed_from_honeycomb");

        //Weathered Waxed Copper Plate Stairs
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_STAIRS_WEATHERED_WAXED.get(), 1)
                .requires(AllItems.COPPER_PLATE_STAIRS_WEATHERED.get(), 1)
                .requires(Items.HONEYCOMB, 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_stairs_weathered_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":copper_plate_stairs_weathered_waxed_from_honeycomb");

        //Oxidized Waxed Copper Plate Stairs
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, AllItems.COPPER_PLATE_STAIRS_OXIDIZED_WAXED.get(), 1)
                .requires(AllItems.COPPER_PLATE_STAIRS_OXIDIZED.get(), 1)
                .requires(Items.HONEYCOMB, 1)
                .group(NotEnoughMachines.MOD_ID + ":copper_plate_stairs_oxidized_waxed")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":copper_plate_stairs_oxidized_waxed_from_honeycomb");

        //Fire Bricks Stairs
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.FIRE_BRICKS_STAIRS.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.FIRE_BRICKS.get())
                .group(NotEnoughMachines.MOD_ID + ":fire_bricks_stairs")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Fluxstone Stairs
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE_STAIRS.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.FLUXSTONE.get())
                .group(NotEnoughMachines.MOD_ID + ":fluxstone_stairs")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Gold Plates Stairs
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.GOLD_PLATE_STAIRS.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.GOLD_PLATE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":gold_plate_stairs")
                .unlockedBy("has_" + getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer);

        //Iron Bars
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.IRON_BARS, 16)
                .pattern("rrr")
                .pattern("rrr")
                .define('r', AllTags.Items.IRON_RODS)
                .group(NotEnoughMachines.MOD_ID + ":iron_bars")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":iron_bars_from_iron_rods");

        //Iron Plates Stairs
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.IRON_PLATE_STAIRS.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.IRON_PLATE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":iron_plate_stairs")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

        //Polished Fluxstone Stairs
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.POLISHED_FLUXSTONE_STAIRS.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.POLISHED_FLUXSTONE.get())
                .group(NotEnoughMachines.MOD_ID + ":polished_fluxstone_stairs")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Tin Plates Stairs
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.TIN_PLATE_STAIRS.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.TIN_PLATE_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":tin_plate_stairs")
                .unlockedBy("has_" + getHasName(AllItems.TIN_INGOT.get()), has(AllItems.TIN_INGOT.get()))
                .save(consumer);

        //Wooden Frame Stairs
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AllItems.WOODEN_FRAME_STAIRS.get(), 6)
                .pattern("=  ")
                .pattern("== ")
                .pattern("===")
                .define('=', AllItems.WOODEN_FRAME.get())
                .group(NotEnoughMachines.MOD_ID + ":wooden_frame_stairs")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Walls
        //--------------------------------------------------------------------------------------------------------------

        //Fire Bricks Wall
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AllItems.FIRE_BRICKS_WALL.get(), 6)
                .pattern("===")
                .pattern("===")
                .define('=', AllItems.FIRE_BRICKS.get())
                .group(NotEnoughMachines.MOD_ID + ":fire_bricks_wall")
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

        //Polished Fluxstone Wall
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AllItems.POLISHED_FLUXSTONE_WALL.get(), 6)
                .pattern("===")
                .pattern("===")
                .define('=', AllItems.POLISHED_FLUXSTONE.get())
                .group(NotEnoughMachines.MOD_ID + ":polished_fluxstone_wall")
                .unlockedBy("has_" + getHasName(AllItems.FLUXSTONE.get()), has(AllItems.FLUXSTONE.get()))
                .save(consumer);

        //Ladders
        //--------------------------------------------------------------------------------------------------------------

        //Bronze Ladder
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.BRONZE_LADDER.get(), 3)
                .pattern("l l")
                .pattern("lll")
                .pattern("l l")
                .define('l', AllTags.Items.BRONZE_RODS)
                .group(NotEnoughMachines.MOD_ID + ":bronze_ladder")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);

        //Bronze Scaffolding
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.BRONZE_SCAFFOLDING.get(), 6)
                .pattern("lpl")
                .pattern("l l")
                .pattern("lpl")
                .define('p', AllTags.Items.BRONZE_PLATES)
                .define('l', AllTags.Items.BRONZE_RODS)
                .group(NotEnoughMachines.MOD_ID + ":bronze_scaffolding")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);

        //Iron Ladder
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.IRON_LADDER.get(), 3)
                .pattern("l l")
                .pattern("lll")
                .pattern("l l")
                .define('l', AllTags.Items.IRON_RODS)
                .group(NotEnoughMachines.MOD_ID + ":iron_ladder")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

        //Iron Scaffolding
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.IRON_SCAFFOLDING.get(), 6)
                .pattern("lpl")
                .pattern("l l")
                .pattern("lpl")
                .define('p', AllTags.Items.IRON_PLATES)
                .define('l', AllTags.Items.IRON_RODS)
                .group(NotEnoughMachines.MOD_ID + ":iron_scaffolding")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);

        //Redstone
        //--------------------------------------------------------------------------------------------------------------

        //Vermilion Pressure Plate
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, AllItems.VERMILION_PRESSURE_PLATE.get(), 1)
                .pattern("==")
                .define('=', AllItems.VERMILION_PLATE.get())
                .group(NotEnoughMachines.MOD_ID + ":vermilion_pressure_plate")
                .unlockedBy("has_" + getHasName(AllItems.CRUSHED_VERMILION.get()), has(AllItems.CRUSHED_VERMILION.get()))
                .save(consumer);

        //Lightning Rod
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, Items.LIGHTNING_ROD, 1)
                .pattern("i")
                .pattern("i")
                .pattern("i")
                .define('i', AllTags.Items.COPPER_RODS)
                .group(NotEnoughMachines.MOD_ID + ":lightning_rod")
                .unlockedBy("has_" + getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID  + ":lightning_rod_from_copper_rods");

        //Power Connectors
        //--------------------------------------------------------------------------------------------------------------

        //Axle
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.AXLE.get(), 3)
                .pattern("###")
                .pattern("b  ")
                .define('#', AllTags.Items.STRIPPED_LOGS)
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

        //Power Generators
        //--------------------------------------------------------------------------------------------------------------

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

        //Wind Wheel
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.WIND_WHEEL.get(), 1)
                .pattern("# #")
                .pattern(" x ")
                .pattern("# #")
                .define('#', AllItems.WIND_WHEEL_SAIL.get())
                .define('x', AllItems.AXLE.get())
                .group(NotEnoughMachines.MOD_ID + ":wind_wheel")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Furnaces and Mills
        //--------------------------------------------------------------------------------------------------------------

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

        //Rolling Mill
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.ROLLING_MILL.get(), 1)
                .pattern("# #")
                .pattern("-=-")
                .pattern("###")
                .define('#', AllItems.WOODEN_FRAME.get())
                .define('-', AllItems.AXLE.get())
                .define('=', AllItems.IRON_ROLLS.get())
                .group(NotEnoughMachines.MOD_ID + ":rolling_mill")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Trip Hammer
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.TRIP_HAMMER.get(), 1)
                .pattern("#i#")
                .pattern("#x#")
                .pattern("#=#")
                .define('#', AllItems.WOODEN_FRAME.get())
                .define('i', AllItems.AXLE.get())
                .define('x', AllItems.BRONZE_MACE.get())
                .define('=', AllTags.Items.BRONZE_PLATES)
                .group(NotEnoughMachines.MOD_ID + ":trip_hammer")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Rails and related rail items
        //--------------------------------------------------------------------------------------------------------------

        //Booster Rod
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.BOOSTER_ROD.get(), 3)
                .pattern("ig ")
                .pattern("igv")
                .pattern("ig ")
                .define('i', AllTags.Items.IRON_RODS)
                .define('g', AllTags.Items.GOLD_RODS)
                .define('v', AllItems.VERMILION_ROD.get())
                .group(NotEnoughMachines.MOD_ID + ":booster_rod")
                .unlockedBy("has_" + getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.BOOSTER_ROD.get(), 3)
                .pattern("tg ")
                .pattern("tgv")
                .pattern("tg ")
                .define('t', AllTags.Items.TIN_RODS)
                .define('g', AllTags.Items.GOLD_RODS)
                .define('v', AllItems.VERMILION_ROD.get())
                .group(NotEnoughMachines.MOD_ID + ":booster_rod")
                .unlockedBy("has_" + getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":booster_rod_from_tin_rods");

        //Bronze Booster Rod
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.BRONZE_BOOSTER_ROD.get(), 3)
                .pattern("bg ")
                .pattern("bgv")
                .pattern("bg ")
                .define('b', AllTags.Items.BRONZE_RODS)
                .define('g', AllTags.Items.GOLD_RODS)
                .define('v', AllItems.VERMILION_ROD.get())
                .group(NotEnoughMachines.MOD_ID + ":bronze_booster_rod")
                .unlockedBy("has_" + getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer);

        //Railroad Tie
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.RAILROAD_TIE.get(), 6)
                .pattern(" b ")
                .pattern("###")
                .define('#', ItemTags.WOODEN_SLABS)
                .define('b', AllItems.LINSEED_OIL.get())
                .group(NotEnoughMachines.MOD_ID + ":railroad_tie")
                .unlockedBy("has_" + getHasName(AllItems.FLAX.get()), has(AllItems.FLAX.get()))
                .save(consumer);

        //Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, Items.RAIL, 16)
                .pattern("| |")
                .pattern("|-|")
                .pattern("| |")
                .define('|', AllTags.Items.IRON_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .group(NotEnoughMachines.MOD_ID + ":rail")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":rail");
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, Items.RAIL, 16)
                .pattern("| |")
                .pattern("|-|")
                .pattern("| |")
                .define('|', AllTags.Items.TIN_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .group(NotEnoughMachines.MOD_ID + ":rail")
                .unlockedBy("has_" + getHasName(AllItems.TIN_INGOT.get()), has(AllItems.TIN_INGOT.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":rail_from_tin_rods");

        //Buffer Stop Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AllItems.BUFFER_STOP_RAIL.get(), 8)
                .pattern("|-|")
                .pattern("|x|")
                .pattern("|-|")
                .define('|', AllTags.Items.IRON_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('x', Items.IRON_BLOCK)
                .group(NotEnoughMachines.MOD_ID + ":buffer_stop_rail")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AllItems.BUFFER_STOP_RAIL.get(), 8)
                .pattern("|-|")
                .pattern("|x|")
                .pattern("|-|")
                .define('|', AllTags.Items.TIN_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('x', AllItems.TIN_BLOCK.get())
                .group(NotEnoughMachines.MOD_ID + ":buffer_stop_rail")
                .unlockedBy("has_" + getHasName(AllItems.TIN_INGOT.get()), has(AllItems.TIN_INGOT.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":buffer_stop_rail_from_tin");

        //Chime Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.CHIME_RAIL.get(), 6)
                .pattern("|p|")
                .pattern("|-|")
                .pattern("|n|")
                .define('|', AllTags.Items.IRON_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('n', Items.NOTE_BLOCK)
                .group(NotEnoughMachines.MOD_ID + ":chime_rail")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.CHIME_RAIL.get(), 6)
                .pattern("|p|")
                .pattern("|-|")
                .pattern("|n|")
                .define('|', AllTags.Items.TIN_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('n', Items.NOTE_BLOCK)
                .group(NotEnoughMachines.MOD_ID + ":chime_rail")
                .unlockedBy("has_" + getHasName(AllItems.TIN_INGOT.get()), has(AllItems.TIN_INGOT.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":chime_rail_from_tin_rods");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, AllItems.CHIME_RAIL.get(), 1)
                .requires(Items.DETECTOR_RAIL)
                .requires(Items.NOTE_BLOCK)
                .group(NotEnoughMachines.MOD_ID + ":chime_rail")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":chime_rail_from_detector_rail");

        //Crossover Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.CROSSOVER_RAIL.get(), 6)
                .pattern("|||")
                .pattern("|-|")
                .pattern("|||")
                .define('|', AllTags.Items.IRON_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .group(NotEnoughMachines.MOD_ID + ":crossover_rail")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.CROSSOVER_RAIL.get(), 6)
                .pattern("|||")
                .pattern("|-|")
                .pattern("|||")
                .define('|', AllTags.Items.TIN_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .group(NotEnoughMachines.MOD_ID + ":crossover_rail")
                .unlockedBy("has_" + getHasName(AllItems.TIN_INGOT.get()), has(AllItems.TIN_INGOT.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":crossover_rail_from_tin_rods");

        //Detector Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, Items.DETECTOR_RAIL, 6)
                .pattern("|p|")
                .pattern("|-|")
                .pattern("|r|")
                .define('|', AllTags.Items.IRON_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', Items.REDSTONE)
                .group(NotEnoughMachines.MOD_ID + ":detector_rail")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":detector_rail");
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, Items.DETECTOR_RAIL, 6)
                .pattern("|p|")
                .pattern("|-|")
                .pattern("|r|")
                .define('|', AllTags.Items.TIN_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', Items.REDSTONE)
                .group(NotEnoughMachines.MOD_ID + ":detector_rail")
                .unlockedBy("has_" + getHasName(AllItems.TIN_INGOT.get()), has(AllItems.TIN_INGOT.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":detector_rail_from_tin_rods");

        //Powered Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, Items.POWERED_RAIL, 6)
                .pattern("| |")
                .pattern("|-|")
                .pattern("| |")
                .define('|', AllItems.BOOSTER_ROD.get())
                .define('-', AllItems.RAILROAD_TIE.get())
                .group(NotEnoughMachines.MOD_ID + ":powered_rail")
                .unlockedBy("has_" + getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":powered_rail");

        //Activator Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, Items.ACTIVATOR_RAIL, 6)
                .pattern("|-|")
                .pattern("|r|")
                .pattern("|-|")
                .define('|', AllTags.Items.IRON_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('r', Items.REDSTONE_TORCH)
                .group(NotEnoughMachines.MOD_ID + ":activator_rail")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer, NotEnoughMachines.MOD_ID + ":activator_rail");
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, Items.ACTIVATOR_RAIL, 6)
                .pattern("|-|")
                .pattern("|r|")
                .pattern("|-|")
                .define('|', AllTags.Items.TIN_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('r', Items.REDSTONE_TORCH)
                .group(NotEnoughMachines.MOD_ID + ":activator_rail")
                .unlockedBy("has_" + getHasName(AllItems.TIN_INGOT.get()), has(AllItems.TIN_INGOT.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":activator_rail_from_tin_rods");

        //One-Way Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.ONE_WAY_RAIL.get(), 6)
                .pattern("|r|")
                .pattern("|-|")
                .pattern("|r|")
                .define('|', AllItems.BOOSTER_ROD.get())
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('r', Items.REPEATER)
                .group(NotEnoughMachines.MOD_ID + ":one_way_rail")
                .unlockedBy("has_" + getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer);

        //Limiter Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.LIMITER_RAIL.get(), 6)
                .pattern("|-|")
                .pattern("|r|")
                .pattern("|-|")
                .define('|', AllTags.Items.IRON_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('r', Items.REPEATER)
                .group(NotEnoughMachines.MOD_ID + ":limiter_rail")
                .unlockedBy("has_" + getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.LIMITER_RAIL.get(), 6)
                .pattern("|-|")
                .pattern("|r|")
                .pattern("|-|")
                .define('|', AllTags.Items.TIN_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('r', Items.REPEATER)
                .group(NotEnoughMachines.MOD_ID + ":limiter_rail")
                .unlockedBy("has_" + getHasName(AllItems.TIN_INGOT.get()), has(AllItems.TIN_INGOT.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":limiter_rail_from_tin_rods");

        //Locking Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.LOCKING_RAIL.get(), 6)
                .pattern("|s|")
                .pattern("|-|")
                .pattern("|p|")
                .define('|', AllItems.BOOSTER_ROD.get())
                .define('s', Items.STONE_PRESSURE_PLATE)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('p', Items.PISTON)
                .group(NotEnoughMachines.MOD_ID + ":locking_rail")
                .unlockedBy("has_" + getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(consumer);

        //High-Speed Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.HIGH_SPEED_RAIL.get(), 16)
                .pattern("| |")
                .pattern("|-|")
                .pattern("| |")
                .define('|', AllTags.Items.BRONZE_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .group(NotEnoughMachines.MOD_ID + ":high_speed_rail")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);

        //High-Speed Activator Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.HIGH_SPEED_ACTIVATOR_RAIL.get(), 6)
                .pattern("|-|")
                .pattern("|r|")
                .pattern("|-|")
                .define('|', AllTags.Items.BRONZE_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('r', Items.REDSTONE_TORCH)
                .group(NotEnoughMachines.MOD_ID + ":high_speed_activator_rail")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);

        //High-Speed Buffer Stop Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AllItems.HIGH_SPEED_BUFFER_STOP_RAIL.get(), 8)
                .pattern("|-|")
                .pattern("|x|")
                .pattern("|-|")
                .define('|', AllTags.Items.BRONZE_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('x', Items.IRON_BLOCK)
                .group(NotEnoughMachines.MOD_ID + ":buffer_stop_rail")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);

        //High-Speed Buffer Chime Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.HIGH_SPEED_CHIME_RAIL.get(), 6)
                .pattern("|p|")
                .pattern("|-|")
                .pattern("|n|")
                .define('|', AllTags.Items.BRONZE_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('n', Items.NOTE_BLOCK)
                .group(NotEnoughMachines.MOD_ID + ":high_speed_chime_rail")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, AllItems.HIGH_SPEED_CHIME_RAIL.get(), 1)
                .requires(AllItems.HIGH_SPEED_DETECTOR_RAIL.get())
                .requires(Items.NOTE_BLOCK)
                .group(NotEnoughMachines.MOD_ID + ":high_speed_chime_rail")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer, NotEnoughMachines.MOD_ID + ":high_speed_chime_rail_from_detector_rail");

        //High-Speed Crossover Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.HIGH_SPEED_CROSSOVER_RAIL.get(), 6)
                .pattern("|||")
                .pattern("|-|")
                .pattern("|||")
                .define('|', AllTags.Items.BRONZE_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .group(NotEnoughMachines.MOD_ID + ":high_speed_crossover_rail")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);

        //High-Speed Detector Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.HIGH_SPEED_DETECTOR_RAIL.get(), 6)
                .pattern("|p|")
                .pattern("|-|")
                .pattern("|r|")
                .define('|', AllTags.Items.BRONZE_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('p', Items.STONE_PRESSURE_PLATE)
                .define('r', Items.REDSTONE)
                .group(NotEnoughMachines.MOD_ID + ":high_speed_detector_rail")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);

        //High-Speed Limiter Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.HIGH_SPEED_LIMITER_RAIL.get(), 6)
                .pattern("|-|")
                .pattern("|r|")
                .pattern("|-|")
                .define('|', AllTags.Items.BRONZE_RODS)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('r', Items.REPEATER)
                .group(NotEnoughMachines.MOD_ID + ":high_speed_limiter_rail")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);

        //High-Speed Locking Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.HIGH_SPEED_LOCKING_RAIL.get(), 6)
                .pattern("|s|")
                .pattern("|-|")
                .pattern("|p|")
                .define('|', AllItems.BRONZE_BOOSTER_ROD.get())
                .define('s', Items.STONE_PRESSURE_PLATE)
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('p', Items.PISTON)
                .group(NotEnoughMachines.MOD_ID + ":high_speed_locking_rail")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);

        //High-Speed One-Way Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.HIGH_SPEED_ONE_WAY_RAIL.get(), 6)
                .pattern("|r|")
                .pattern("|-|")
                .pattern("|r|")
                .define('|', AllItems.BRONZE_BOOSTER_ROD.get())
                .define('-', AllItems.RAILROAD_TIE.get())
                .define('r', Items.REPEATER)
                .group(NotEnoughMachines.MOD_ID + ":high_speed_one_way_rail")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);

        //High-Speed Powered Rail
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, AllItems.HIGH_SPEED_POWERED_RAIL.get(), 6)
                .pattern("| |")
                .pattern("|-|")
                .pattern("| |")
                .define('|', AllItems.BRONZE_BOOSTER_ROD.get())
                .define('-', AllItems.RAILROAD_TIE.get())
                .group(NotEnoughMachines.MOD_ID + ":high_speed_powered_rail")
                .unlockedBy("has_" + getHasName(AllItems.BRONZE_INGOT.get()), has(AllItems.BRONZE_INGOT.get()))
                .save(consumer);
    }

    protected void buildCookingRecipes(Consumer<FinishedRecipe> consumer)
    {
        LOGGER.debug(NotEnoughMachines.MOD_ID + ":building all cooking recipes");

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
        simpleOreSmelting(consumer, "vermilion_ingot", AllItems.CRUSHED_VERMILION.get(), AllItems.VERMILION_INGOT.get(), Items.REDSTONE, RecipeCategory.MISC, 0.7F, 200);
        simpleOreBlasting(consumer, "vermilion_ingot", AllItems.CRUSHED_VERMILION.get(), AllItems.VERMILION_INGOT.get(), Items.REDSTONE, RecipeCategory.MISC, 0.7F, 100);
    }

    protected void buildStoneCuttingRecipes(Consumer<FinishedRecipe> consumer)
    {
        LOGGER.debug(NotEnoughMachines.MOD_ID + ":building all stone cutting recipes");

        //Bronze Plate Block
        simpleStoneCutting(consumer, "bronze_plate_slab", Ingredient.of(AllItems.BRONZE_PLATE_BLOCK.get()), AllItems.BRONZE_PLATE_SLAB.get(), 2, RecipeCategory.BUILDING_BLOCKS, AllItems.BRONZE_INGOT.get());
        simpleStoneCutting(consumer, "bronze_plate_stairs", Ingredient.of(AllItems.BRONZE_PLATE_BLOCK.get()), AllItems.BRONZE_PLATE_STAIRS.get(), 1, RecipeCategory.BUILDING_BLOCKS, AllItems.BRONZE_INGOT.get());

        //Copper Plate Block
        simpleStoneCutting(consumer, "copper_plate_slab", Ingredient.of(AllItems.COPPER_PLATE_BLOCK.get()), AllItems.COPPER_PLATE_SLAB.get(), 2, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_stairs", Ingredient.of(AllItems.COPPER_PLATE_BLOCK.get()), AllItems.COPPER_PLATE_STAIRS.get(), 1, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_slab_exposed", Ingredient.of(AllItems.COPPER_PLATE_BLOCK_EXPOSED.get()), AllItems.COPPER_PLATE_SLAB_EXPOSED.get(), 2, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_stairs_exposed", Ingredient.of(AllItems.COPPER_PLATE_BLOCK_EXPOSED.get()), AllItems.COPPER_PLATE_STAIRS_EXPOSED.get(), 1, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_slab_weathered", Ingredient.of(AllItems.COPPER_PLATE_BLOCK_WEATHERED.get()), AllItems.COPPER_PLATE_SLAB_WEATHERED.get(), 2, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_stairs_weathered", Ingredient.of(AllItems.COPPER_PLATE_BLOCK_WEATHERED.get()), AllItems.COPPER_PLATE_STAIRS_WEATHERED.get(), 1, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_slab_oxidized", Ingredient.of(AllItems.COPPER_PLATE_BLOCK_OXIDIZED.get()), AllItems.COPPER_PLATE_SLAB_OXIDIZED.get(), 2, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_stairs_oxidized", Ingredient.of(AllItems.COPPER_PLATE_BLOCK_OXIDIZED.get()), AllItems.COPPER_PLATE_STAIRS_OXIDIZED.get(), 1, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_slab_waxed", Ingredient.of(AllItems.COPPER_PLATE_BLOCK_WAXED.get()), AllItems.COPPER_PLATE_SLAB_WAXED.get(), 2, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_stairs_waxed", Ingredient.of(AllItems.COPPER_PLATE_BLOCK_WAXED.get()), AllItems.COPPER_PLATE_STAIRS_WAXED.get(), 1, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_slab_exposed_waxed", Ingredient.of(AllItems.COPPER_PLATE_BLOCK_EXPOSED_WAXED.get()), AllItems.COPPER_PLATE_SLAB_EXPOSED_WAXED.get(), 2, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_stairs_exposed_waxed", Ingredient.of(AllItems.COPPER_PLATE_BLOCK_EXPOSED_WAXED.get()), AllItems.COPPER_PLATE_STAIRS_EXPOSED_WAXED.get(), 1, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_slab_weathered_waxed", Ingredient.of(AllItems.COPPER_PLATE_BLOCK_WEATHERED_WAXED.get()), AllItems.COPPER_PLATE_SLAB_WEATHERED_WAXED.get(), 2, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_stairs_weathered_waxed", Ingredient.of(AllItems.COPPER_PLATE_BLOCK_WEATHERED_WAXED.get()), AllItems.COPPER_PLATE_STAIRS_WEATHERED_WAXED.get(), 1, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_slab_oxidized_waxed", Ingredient.of(AllItems.COPPER_PLATE_BLOCK_OXIDIZED_WAXED.get()), AllItems.COPPER_PLATE_SLAB_OXIDIZED_WAXED.get(), 2, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);
        simpleStoneCutting(consumer, "copper_plate_stairs_oxidized_waxed", Ingredient.of(AllItems.COPPER_PLATE_BLOCK_OXIDIZED_WAXED.get()), AllItems.COPPER_PLATE_STAIRS_OXIDIZED_WAXED.get(), 1, RecipeCategory.BUILDING_BLOCKS, Items.COPPER_INGOT);

        //Fire Bricks
        simpleStoneCutting(consumer, "fire_bricks_slab", Ingredient.of(AllItems.FIRE_BRICKS.get()), AllItems.FIRE_BRICKS_SLAB.get(), 2, RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE.get());
        simpleStoneCutting(consumer, "fire_bricks_stairs", Ingredient.of(AllItems.FIRE_BRICKS.get()), AllItems.FIRE_BRICKS_STAIRS.get(), 1, RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE.get());
        simpleStoneCutting(consumer, "fire_bricks_wall", Ingredient.of(AllItems.FIRE_BRICKS.get()), AllItems.FIRE_BRICKS_WALL.get(), 1, RecipeCategory.DECORATIONS, AllItems.FLUXSTONE.get());

        //Fluxstone
        simpleStoneCutting(consumer, "fluxstone_slab", Ingredient.of(AllItems.FLUXSTONE.get()), AllItems.FLUXSTONE_SLAB.get(), 2, RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE.get());
        simpleStoneCutting(consumer, "fluxstone_stairs", Ingredient.of(AllItems.FLUXSTONE.get()), AllItems.FLUXSTONE_STAIRS.get(), 1, RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE.get());
        simpleStoneCutting(consumer, "fluxstone_wall", Ingredient.of(AllItems.FLUXSTONE.get()), AllItems.FLUXSTONE_WALL.get(), 1, RecipeCategory.DECORATIONS, AllItems.FLUXSTONE.get());

        //Gold Plate Block
        simpleStoneCutting(consumer, "gold_plate_slab", Ingredient.of(AllItems.GOLD_PLATE_BLOCK.get()), AllItems.GOLD_PLATE_SLAB.get(), 2, RecipeCategory.BUILDING_BLOCKS, Items.GOLD_INGOT);
        simpleStoneCutting(consumer, "gold_plate_stairs", Ingredient.of(AllItems.GOLD_PLATE_BLOCK.get()), AllItems.GOLD_PLATE_STAIRS.get(), 1, RecipeCategory.BUILDING_BLOCKS, Items.GOLD_INGOT);

        //Iron Plate Block
        simpleStoneCutting(consumer, "iron_plate_slab", Ingredient.of(AllItems.IRON_PLATE_BLOCK.get()), AllItems.IRON_PLATE_SLAB.get(), 2, RecipeCategory.BUILDING_BLOCKS, Items.IRON_INGOT);
        simpleStoneCutting(consumer, "iron_plate_stairs", Ingredient.of(AllItems.IRON_PLATE_BLOCK.get()), AllItems.IRON_PLATE_STAIRS.get(), 1, RecipeCategory.BUILDING_BLOCKS, Items.IRON_INGOT);

        //Polished Fluxstone
        simpleStoneCutting(consumer, "polished_fluxstone_slab", Ingredient.of(AllItems.POLISHED_FLUXSTONE.get()), AllItems.POLISHED_FLUXSTONE_SLAB.get(), 2, RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE.get());
        simpleStoneCutting(consumer, "polished_fluxstone_stairs", Ingredient.of(AllItems.POLISHED_FLUXSTONE.get()), AllItems.POLISHED_FLUXSTONE_STAIRS.get(), 1, RecipeCategory.BUILDING_BLOCKS, AllItems.FLUXSTONE.get());
        simpleStoneCutting(consumer, "polished_fluxstone_wall", Ingredient.of(AllItems.POLISHED_FLUXSTONE.get()), AllItems.POLISHED_FLUXSTONE_WALL.get(), 1, RecipeCategory.DECORATIONS, AllItems.FLUXSTONE.get());

        //Tin Plate Block
        simpleStoneCutting(consumer, "tin_plate_slab", Ingredient.of(AllItems.TIN_PLATE_BLOCK.get()), AllItems.TIN_PLATE_SLAB.get(), 2, RecipeCategory.BUILDING_BLOCKS, AllItems.TIN_INGOT.get());
        simpleStoneCutting(consumer, "tin_plate_stairs", Ingredient.of(AllItems.TIN_PLATE_BLOCK.get()), AllItems.TIN_PLATE_STAIRS.get(), 1, RecipeCategory.BUILDING_BLOCKS, AllItems.TIN_INGOT.get());
    }

    protected void buildMillingRecipes(Consumer<FinishedRecipe> consumer)
    {
        LOGGER.debug(NotEnoughMachines.MOD_ID + ":building all milling recipes");

        //Dust
        simpleMilling(consumer, "blaze_powder", Items.BLAZE_ROD, Items.BLAZE_POWDER, 4, Items.BLAZE_ROD, 200);
        simpleMilling(consumer, "bone_meal", Items.BONE, Items.BONE_MEAL, 6, Items.BONE, 200);
        simpleMilling(consumer, "copper_dust", Items.RAW_COPPER, AllItems.CRUSHED_COPPER_ORE.get(), 1, Items.RAW_COPPER, 400);
        simpleMilling(consumer, "flour", Items.WHEAT, AllItems.FLOUR.get(), 1, Items.WHEAT, 200);
        simpleMilling(consumer, "flux", AllItems.FLUXSTONE.get(), AllItems.FLUX.get(), 1, AllItems.FLUXSTONE.get(), 400);
        simpleMilling(consumer, "glowstone_dust", Items.GLOWSTONE, Items.GLOWSTONE_DUST, 2, Items.GLOWSTONE, 400);
        simpleMilling(consumer, "gold_dust", Items.RAW_GOLD, AllItems.CRUSHED_GOLD_ORE.get(), 1, Items.RAW_GOLD, 400);
        simpleMilling(consumer, "iron_dust", Items.RAW_IRON, AllItems.CRUSHED_IRON_ORE.get(), 1, Items.RAW_IRON, 400);
        simpleMilling(consumer, "sugar", Items.SUGAR_CANE, Items.SUGAR, 2, Items.SUGAR_CANE, 200);
        simpleMilling(consumer, "tin_dust", AllItems.RAW_TIN.get(), AllItems.CRUSHED_TIN_ORE.get(), 1, AllItems.RAW_TIN.get(), 400);

        //Dye
        simpleMilling(consumer, "black_dye", Ingredient.of(Items.INK_SAC, Items.WITHER_ROSE), Items.BLACK_DYE, 2, Items.BLACK_DYE, 200);
        simpleMilling(consumer, "blue_dye", Items.LAPIS_LAZULI, Items.BLUE_DYE, 3, Items.LAPIS_LAZULI, 400);
        simpleMilling(consumer, "blue_dye", Items.CORNFLOWER, Items.BLUE_DYE, 2, Items.CORNFLOWER, 200);
        simpleMilling(consumer, "brown_dye", Items.COCOA_BEANS, Items.BROWN_DYE, 2, Items.COCOA_BEANS, 200);
        simpleMilling(consumer, "green_dye", Items.CACTUS, Items.GREEN_DYE, 2, Items.CACTUS, 200);
        simpleMilling(consumer, "light_blue_dye", Items.BLUE_ORCHID, Items.LIGHT_BLUE_DYE, 2, Items.BLUE_ORCHID, 200);
        simpleMilling(consumer, "light_gray_dye", Ingredient.of(Items.AZURE_BLUET, Items.OXEYE_DAISY, Items.WHITE_TULIP), Items.LIGHT_GRAY_DYE, 2, Items.LIGHT_GRAY_DYE, 200);
        simpleMilling(consumer, "magenta_dye", Ingredient.of(Items.LILAC, Items.ALLIUM), Items.MAGENTA_DYE, 2, Items.MAGENTA_DYE, 200);
        simpleMilling(consumer, "orange_dye", Items.ORANGE_TULIP, Items.ORANGE_DYE, 2, Items.ORANGE_TULIP, 200);
        simpleMilling(consumer, "pink_dye", Ingredient.of(Items.PINK_TULIP, Items.PEONY), Items.PINK_DYE, 2, Items.PINK_DYE, 200);
        simpleMilling(consumer, "red_dye", Ingredient.of(Items.POPPY, Items.RED_TULIP, Items.BEETROOT, Items.ROSE_BUSH), Items.RED_DYE, 2, Items.RED_DYE, 200);
        simpleMilling(consumer, "white_dye", Ingredient.of(Items.BONE_MEAL, Items.LILY_OF_THE_VALLEY), Items.WHITE_DYE, 2, Items.WHITE_DYE, 200);
        simpleMilling(consumer, "yellow_dye", Ingredient.of(Items.DANDELION, Items.SUNFLOWER), Items.YELLOW_DYE, 2, Items.YELLOW_DYE, 200);

        //Misc
        simpleMilling(consumer, "flax_string", AllItems.FLAX.get(), AllItems.FLAX_STRING.get(), 3, AllItems.FLAX.get(), 200);
        simpleMilling(consumer, "flint", Items.GRAVEL, Items.FLINT, 1, Items.GRAVEL, 400);
    }

    protected void buildStampingRecipes(Consumer<FinishedRecipe> consumer)
    {
        LOGGER.debug(NotEnoughMachines.MOD_ID + ":building all stamping recipes");

        //Building Blocks
        simpleStamping(consumer, "clay_ball", Ingredient.of(Items.SAND, Items.RED_SAND), Items.CLAY_BALL, 6, Items.SAND, 200);
        simpleStamping(consumer, "cobblestone", Ingredient.of(Items.STONE, Items.CRACKED_STONE_BRICKS, Items.SMOOTH_STONE), Items.COBBLESTONE, 1, Items.COBBLESTONE, 200);
        simpleStamping(consumer, "cracked_stone_bricks", Ingredient.of(Items.STONE_BRICKS, Items.CHISELED_STONE_BRICKS), Items.CRACKED_STONE_BRICKS, 1, Items.STONE_BRICKS, 200);
        simpleStamping(consumer, "gravel", Ingredient.of(Items.COBBLESTONE), Items.GRAVEL, 1, Items.COBBLESTONE, 200);
        simpleStamping(consumer, "mossy_cobblestone", Ingredient.of(Items.MOSSY_STONE_BRICKS), Items.MOSSY_COBBLESTONE, 1, Items.MOSSY_STONE_BRICKS, 200);
        simpleStamping(consumer, "red_sand", Ingredient.of(Items.RED_SANDSTONE, Items.CHISELED_RED_SANDSTONE, Items.CUT_RED_SANDSTONE, Items.SMOOTH_RED_SANDSTONE), Items.RED_SAND, 1, Items.RED_SANDSTONE, 200);
        simpleStamping(consumer, "sand", Ingredient.of(Items.SANDSTONE, Items.CHISELED_SANDSTONE, Items.SMOOTH_SANDSTONE, Items.CUT_SANDSTONE), Items.SAND, 1, Items.SAND, 200);
        simpleStamping(consumer, "sand", Items.GRAVEL, Items.SAND, 1, Items.FLINT, 2, Items.GRAVEL, 200);

        //Dust
        simpleStamping(consumer, "copper_dust", Items.RAW_COPPER, AllItems.CRUSHED_COPPER_ORE.get(), 2, Items.RAW_COPPER, 400);
        simpleStamping(consumer, "gold_dust", Items.RAW_GOLD, AllItems.CRUSHED_GOLD_ORE.get(), 2, Items.RAW_GOLD, 400);
        simpleStamping(consumer, "iron_dust", Items.RAW_IRON, AllItems.CRUSHED_IRON_ORE.get(), 2, Items.RAW_IRON, 400);
        simpleStamping(consumer, "tin_dust", AllItems.RAW_TIN.get(), AllItems.CRUSHED_TIN_ORE.get(), 2, AllItems.RAW_TIN.get(), 400);

        //Raw Ore
        simpleStamping(consumer, "raw_copper", Ingredient.of(Items.COPPER_ORE, Items.DEEPSLATE_COPPER_ORE), Items.RAW_COPPER, 6, Items.COPPER_ORE, 400);
        simpleStamping(consumer, "raw_gold", Ingredient.of(Items.GOLD_ORE, Items.DEEPSLATE_GOLD_ORE), Items.RAW_GOLD, 2, Items.GOLD_ORE, 400);
        simpleStamping(consumer, "raw_iron", Ingredient.of(Items.IRON_ORE, Items.DEEPSLATE_IRON_ORE), Items.RAW_IRON, 2, Items.IRON_ORE, 400);
        simpleStamping(consumer, "raw_tin", Ingredient.of(AllItems.TIN_ORE.get()), AllItems.RAW_TIN.get(), 2, AllItems.TIN_ORE.get(), 400);

        //Other Minerals
        simpleStamping(consumer, "amethyst_shard", Ingredient.of(Items.AMETHYST_CLUSTER), Items.AMETHYST_SHARD, 7, Items.AMETHYST_CLUSTER, 200);
        simpleStamping(consumer, "coal", Ingredient.of(Items.COAL_ORE, Items.DEEPSLATE_COAL_ORE), Items.COAL, 3, Items.COAL_ORE, 400);
        simpleStamping(consumer, "diamond", Ingredient.of(Items.DIAMOND_ORE, Items.DEEPSLATE_DIAMOND_ORE), Items.DIAMOND, 3, Items.DIAMOND_ORE, 600);
        simpleStamping(consumer, "emerald", Ingredient.of(Items.EMERALD_ORE, Items.DEEPSLATE_EMERALD_ORE), Items.EMERALD, 3, Items.EMERALD_ORE, 600);
        simpleStamping(consumer, "flux", AllItems.FLUXSTONE.get(), AllItems.FLUX.get(), 3, AllItems.FLUXSTONE.get(), 200);
        simpleStamping(consumer, "glowstone_dust", Items.GLOWSTONE, Items.GLOWSTONE_DUST, 4, Items.GLOWSTONE, 200);
        simpleStamping(consumer, "glowstone_dust", Items.REDSTONE_LAMP, Items.GLOWSTONE_DUST, 4, Items.REDSTONE, 4, Items.GLOWSTONE, 200);
        simpleStamping(consumer, "gold_nugget", Items.NETHER_GOLD_ORE, Items.GOLD_NUGGET, 7, Items.NETHER_GOLD_ORE, 400);
        simpleStamping(consumer, "lapis_lazuli", Ingredient.of(Items.LAPIS_ORE, Items.DEEPSLATE_LAPIS_ORE), Items.LAPIS_LAZULI, 11, Items.LAPIS_ORE, 400);
        simpleStamping(consumer, "quartz", Ingredient.of(Items.NETHER_QUARTZ_ORE), Items.QUARTZ, 2, Items.NETHER_QUARTZ_ORE, 200);
        simpleStamping(consumer, "quartz", Items.DAYLIGHT_DETECTOR, Items.QUARTZ, 3, Items.SAND, 3, Items.DAYLIGHT_DETECTOR, 200);
        simpleStamping(consumer, "redstone", Ingredient.of(Items.REDSTONE_ORE, Items.DEEPSLATE_REDSTONE_ORE), Items.REDSTONE, 6, Items.REDSTONE_ORE, 400);
    }

    protected void buildRollingRecipes(Consumer<FinishedRecipe> consumer)
    {
        LOGGER.debug(NotEnoughMachines.MOD_ID + ":building all rolling recipes");

        simpleRolling(consumer, "bronze_plate", Ingredient.of(AllTags.Items.BRONZE_INGOTS), AllItems.BRONZE_PLATE.get(), 1, AllItems.BRONZE_INGOT.get(), 300);
        simpleRolling(consumer, "bronze_rod", Ingredient.of(AllTags.Items.BRONZE_PLATES), AllItems.BRONZE_ROD.get(), 3, AllItems.BRONZE_INGOT.get(), 300);
        simpleRolling(consumer, "copper_plate", Ingredient.of(Items.COPPER_INGOT), AllItems.COPPER_PLATE.get(), 1, Items.COPPER_INGOT, 200);
        simpleRolling(consumer, "copper_rod", Ingredient.of(AllItems.COPPER_PLATE.get()), AllItems.COPPER_ROD.get(), 3, Items.COPPER_INGOT, 200);
        simpleRolling(consumer, "gold_plate", Ingredient.of(Items.GOLD_INGOT), AllItems.GOLD_PLATE.get(), 1, Items.GOLD_INGOT, 300);
        simpleRolling(consumer, "gold_rod", Ingredient.of(AllItems.GOLD_PLATE.get()), AllItems.GOLD_ROD.get(), 3, Items.GOLD_INGOT, 300);
        simpleRolling(consumer, "iron_plate", Ingredient.of(Items.IRON_INGOT), AllItems.IRON_PLATE.get(), 1, Items.IRON_INGOT, 300);
        simpleRolling(consumer, "iron_rod", Ingredient.of(AllItems.IRON_PLATE.get()), AllItems.IRON_ROD.get(), 3, Items.IRON_INGOT, 300);
        simpleRolling(consumer, "sponge_dry", Ingredient.of(Items.WET_SPONGE), Items.SPONGE, 1, Items.WET_SPONGE, 200);
        simpleRolling(consumer, "tin_plate", Ingredient.of(AllTags.Items.TIN_INGOTS), AllItems.TIN_PLATE.get(), 1, AllItems.TIN_INGOT.get(), 300);
        simpleRolling(consumer, "tin_rod", Ingredient.of(AllTags.Items.TIN_PLATES), AllItems.TIN_ROD.get(), 3, AllItems.TIN_INGOT.get(), 300);
        simpleRolling(consumer, "vermilion_plate", Ingredient.of(AllItems.VERMILION_INGOT.get()), AllItems.VERMILION_PLATE.get(), 1, AllItems.VERMILION_INGOT.get(), 200);
        simpleRolling(consumer, "vermilion_rod", Ingredient.of(AllItems.VERMILION_PLATE.get()), AllItems.VERMILION_ROD.get(), 3, AllItems.VERMILION_INGOT.get(), 200);
    }

    public static void buildCokeOvenRecipes(Consumer<FinishedRecipe> consumer)
    {
        LOGGER.debug(NotEnoughMachines.MOD_ID + ":building all coking recipes");

        simpleCoking(consumer, "charcoal", Ingredient.of(ItemTags.LOGS), Items.CHARCOAL, 1, Items.CHARCOAL, 600);
        simpleCoking(consumer, "coke", Ingredient.of(Items.COAL), AllItems.COKE.get(), 1, Items.COAL, 1800);
        simpleCoking(consumer, "coke_block", Ingredient.of(Items.COAL_BLOCK), AllItems.COKE_BLOCK.get(), 1, Items.COAL, 16200);
    }

    public static void simpleCoking(Consumer<FinishedRecipe> consumer, String group, Ingredient ingredient, ItemLike result, int count, ItemLike unlockedBy, int processingTime)
    {
        SingleResultMachineRecipeBuilder.coking(ingredient, result, count, processingTime)
                .unlockedBy("has_" + getHasName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":coking/" + getItemName(result) + "_from_coking");
    }

    public static void simpleMilling(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike result, int count, ItemLike unlockedBy, int processingTime)
    {
        SingleResultMachineRecipeBuilder.milling(Ingredient.of(ingredient), result, count, processingTime)
                .unlockedBy("has_" + getHasName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":milling/" + getItemName(result) + "_from_" + getItemName(ingredient) + "_from_milling");
    }

    public static void simpleMilling(Consumer<FinishedRecipe> consumer, String group, Ingredient ingredient, ItemLike result, int count, ItemLike unlockedBy, int processingTime)
    {
        SingleResultMachineRecipeBuilder.milling(ingredient, result, count, processingTime)
                .unlockedBy("has_" + getHasName(unlockedBy), has(unlockedBy))
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

    public static void simpleRolling(Consumer<FinishedRecipe> consumer, String group, Ingredient ingredient, ItemLike result, int count, ItemLike unlockedBy, int processingTime)
    {
        SingleResultMachineRecipeBuilder.rolling(ingredient, result, count, processingTime)
                .unlockedBy("has_" + getHasName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":rolling/" + getItemName(result) + "_from_rolling");
    }

    public static void simpleStamping(Consumer<FinishedRecipe> consumer, String group, Ingredient ingredient, ItemLike resultPrimary, int countPrimary, ItemLike unlockedBy, int processingTime)
    {
        DoubleResultMachineRecipeBuilder.stamping(ingredient, resultPrimary, countPrimary, processingTime)
                .unlockedBy("has_" + getHasName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":stamping/" + getItemName(resultPrimary) + "_from_stamping");
    }

    public static void simpleStamping(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike resultPrimary, int countPrimary, ItemLike unlockedBy, int processingTime)
    {
        DoubleResultMachineRecipeBuilder.stamping(Ingredient.of(ingredient), resultPrimary, countPrimary, processingTime)
                .unlockedBy("has_" + getHasName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":stamping/" + getItemName(resultPrimary) + "_from_" + getItemName(ingredient) + "_from_stamping");
    }

    public static void simpleStamping(Consumer<FinishedRecipe> consumer, String group, Ingredient ingredient, ItemLike resultPrimary, int countPrimary, ItemLike resultSecondary, int countSecondary, ItemLike unlockedBy, int processingTime)
    {
        DoubleResultMachineRecipeBuilder.stamping(ingredient, resultPrimary, countPrimary, processingTime)
                .addSecondaryResult(resultSecondary, countSecondary)
                .unlockedBy("has_" + getHasName(unlockedBy), has(unlockedBy))
                .group(NotEnoughMachines.MOD_ID + ":" + group)
                .save(consumer, NotEnoughMachines.MOD_ID + ":stamping/" + getItemName(resultPrimary) + "_from_stamping");
    }

    public static void simpleStamping(Consumer<FinishedRecipe> consumer, String group, ItemLike ingredient, ItemLike resultPrimary, int countPrimary, ItemLike resultSecondary, int countSecondary, ItemLike unlockedBy, int processingTime)
    {
        DoubleResultMachineRecipeBuilder.stamping(Ingredient.of(ingredient), resultPrimary, countPrimary, processingTime)
                .addSecondaryResult(resultSecondary, countSecondary)
                .unlockedBy("has_" + getHasName(unlockedBy), has(unlockedBy))
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
