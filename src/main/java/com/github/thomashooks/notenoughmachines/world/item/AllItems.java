package com.github.thomashooks.notenoughmachines.world.item;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllItems
{
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NotEnoughMachines.MOD_ID);

    public static final RegistryObject<Item> AXLE = ITEMS.register("axle", ()-> new BlockItem(AllBlocks.AXLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BOOSTER_ROD = ITEMS.register("booster_rod", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_BOOSTER_ROD = ITEMS.register("bronze_booster_rod", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_BLOCK = ITEMS.register("bronze_block", ()-> new BlockItem(AllBlocks.BRONZE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_GRATE = ITEMS.register("bronze_grate", ()-> new BlockItem(AllBlocks.BRONZE_GRATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_LADDER = ITEMS.register("bronze_ladder", ()-> new BlockItem(AllBlocks.BRONZE_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_PLATE_BLOCK = ITEMS.register("bronze_plate_block", ()-> new BlockItem(AllBlocks.BRONZE_PLATE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_PLATE_SLAB = ITEMS.register("bronze_plate_slab", ()-> new BlockItem(AllBlocks.BRONZE_PLATE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_PLATE_STAIRS = ITEMS.register("bronze_plate_stairs", ()-> new BlockItem(AllBlocks.BRONZE_PLATE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_MACE = ITEMS.register("bronze_mace", ()-> new MaceItem(ToolTiers.BRONZE, 3.0F, -3.2F, 1.8F, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_PLATE = ITEMS.register("bronze_plate", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_ROD = ITEMS.register("bronze_rod", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_SCAFFOLDING = ITEMS.register("bronze_scaffolding", ()-> new MetalScaffoldBlockItem(19, AllBlocks.BRONZE_SCAFFOLDING.get(), new Item.Properties()));
    public static final RegistryObject<Item> BUFFER_STOP_RAIL = ITEMS.register("buffer_stop_rail", ()-> new BlockItem(AllBlocks.BUFFER_STOP_RAIL.get(), new Item.Properties()));

    public static final RegistryObject<Item> CHIME_RAIL = ITEMS.register("chime_rail", ()-> new BlockItem(AllBlocks.CHIME_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> COKE = ITEMS.register("coke", ()-> new FuelItem(3200, new Item.Properties()));
    public static final RegistryObject<Item> COKE_BLOCK = ITEMS.register("coke_block", ()-> new FuelBlockItem(AllBlocks.COKE_BLOCK.get(), 32000, new Item.Properties()));
    public static final RegistryObject<Item> COKE_OVEN = ITEMS.register("coke_oven", ()-> new BlockItem(AllBlocks.COKE_OVEN.get(), new Item.Properties()));
    public static final RegistryObject<Item> CONJUNCTIONER = ITEMS.register("conjunctioner", ()-> new BlockItem(AllBlocks.CONJUNCTIONER.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE = ITEMS.register("copper_plate", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_BLOCK = ITEMS.register("copper_plate_block", ()-> new BlockItem(AllBlocks.COPPER_PLATE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_BLOCK_EXPOSED = ITEMS.register("copper_plate_block_exposed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_BLOCK_EXPOSED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_BLOCK_WEATHERED = ITEMS.register("copper_plate_block_weathered", ()-> new BlockItem(AllBlocks.COPPER_PLATE_BLOCK_WEATHERED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_BLOCK_OXIDIZED = ITEMS.register("copper_plate_block_oxidized", ()-> new BlockItem(AllBlocks.COPPER_PLATE_BLOCK_OXIDIZED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_BLOCK_WAXED = ITEMS.register("copper_plate_block_waxed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_BLOCK_WAXED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_BLOCK_EXPOSED_WAXED = ITEMS.register("copper_plate_block_exposed_waxed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_BLOCK_EXPOSED_WAXED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_BLOCK_WEATHERED_WAXED = ITEMS.register("copper_plate_block_weathered_waxed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_BLOCK_WEATHERED_WAXED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_BLOCK_OXIDIZED_WAXED = ITEMS.register("copper_plate_block_oxidized_waxed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_BLOCK_OXIDIZED_WAXED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_SLAB = ITEMS.register("copper_plate_slab", ()-> new BlockItem(AllBlocks.COPPER_PLATE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_SLAB_EXPOSED = ITEMS.register("copper_plate_slab_exposed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_SLAB_EXPOSED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_SLAB_WEATHERED = ITEMS.register("copper_plate_slab_weathered", ()-> new BlockItem(AllBlocks.COPPER_PLATE_SLAB_WEATHERED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_SLAB_OXIDIZED = ITEMS.register("copper_plate_slab_oxidized", ()-> new BlockItem(AllBlocks.COPPER_PLATE_SLAB_OXIDIZED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_SLAB_WAXED = ITEMS.register("copper_plate_slab_waxed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_SLAB_WAXED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_SLAB_EXPOSED_WAXED = ITEMS.register("copper_plate_slab_exposed_waxed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_SLAB_EXPOSED_WAXED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_SLAB_WEATHERED_WAXED = ITEMS.register("copper_plate_slab_weathered_waxed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_SLAB_WEATHERED_WAXED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_SLAB_OXIDIZED_WAXED = ITEMS.register("copper_plate_slab_oxidized_waxed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_SLAB_OXIDIZED_WAXED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_STAIRS = ITEMS.register("copper_plate_stairs", ()-> new BlockItem(AllBlocks.COPPER_PLATE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_STAIRS_EXPOSED = ITEMS.register("copper_plate_stairs_exposed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_STAIRS_EXPOSED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_STAIRS_WEATHERED = ITEMS.register("copper_plate_stairs_weathered", ()-> new BlockItem(AllBlocks.COPPER_PLATE_STAIRS_WEATHERED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_STAIRS_OXIDIZED = ITEMS.register("copper_plate_stairs_oxidized", ()-> new BlockItem(AllBlocks.COPPER_PLATE_STAIRS_OXIDIZED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_STAIRS_WAXED = ITEMS.register("copper_plate_stairs_waxed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_STAIRS_WAXED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_STAIRS_EXPOSED_WAXED = ITEMS.register("copper_plate_stairs_exposed_waxed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_STAIRS_EXPOSED_WAXED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_STAIRS_WEATHERED_WAXED = ITEMS.register("copper_plate_stairs_weathered_waxed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_STAIRS_WEATHERED_WAXED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_STAIRS_OXIDIZED_WAXED = ITEMS.register("copper_plate_stairs_oxidized_waxed", ()-> new BlockItem(AllBlocks.COPPER_PLATE_STAIRS_OXIDIZED_WAXED.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_ROD = ITEMS.register("copper_rod", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CROSSOVER_RAIL = ITEMS.register("crossover_rail", ()-> new BlockItem(AllBlocks.CROSSOVER_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_BRONZE = ITEMS.register("crushed_bronze", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_COPPER_ORE = ITEMS.register("crushed_copper_ore", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_GOLD_ORE = ITEMS.register("crushed_gold_ore", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_IRON_ORE = ITEMS.register("crushed_iron_ore", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_TIN_ORE = ITEMS.register("crushed_tin_ore", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_VERMILION = ITEMS.register("crushed_vermilion", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ENCLOSED_AXLE = ITEMS.register("enclosed_axle", ()-> new BlockItem(AllBlocks.ENCLOSED_AXLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> FIRE_BRICK = ITEMS.register("fire_brick", ()-> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> FIRE_BRICKS = ITEMS.register("fire_bricks", ()-> new BlockItem(AllBlocks.FIRE_BRICKS.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> FIRE_BRICKS_SLAB = ITEMS.register("fire_bricks_slab", ()-> new BlockItem(AllBlocks.FIRE_BRICKS_SLAB.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> FIRE_BRICKS_STAIRS = ITEMS.register("fire_bricks_stairs", ()-> new BlockItem(AllBlocks.FIRE_BRICKS_STAIRS.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> FIRE_BRICKS_WALL = ITEMS.register("fire_bricks_wall", ()-> new BlockItem(AllBlocks.FIRE_BRICKS_WALL.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> FILTER = ITEMS.register("filter", ()-> new BlockItem(AllBlocks.FILTER.get(), new Item.Properties()));
    public static final RegistryObject<Item> FLAX = ITEMS.register("flax", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLAXSEED = ITEMS.register("flaxseed", ()-> new ItemNameBlockItem(AllBlocks.FLAXPLANT.get(), new Item.Properties()));
    public static final RegistryObject<Item> FLAX_STRING = ITEMS.register("flax_string", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLOUR = ITEMS.register("flour", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLUX = ITEMS.register("flux", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLUXSTONE = ITEMS.register("fluxstone", ()-> new BlockItem(AllBlocks.FLUXSTONE.get(), new Item.Properties()));
    public static final RegistryObject<Item> FLUXSTONE_SLAB = ITEMS.register("fluxstone_slab", ()-> new BlockItem(AllBlocks.FLUXSTONE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> FLUXSTONE_STAIRS = ITEMS.register("fluxstone_stairs", ()-> new BlockItem(AllBlocks.FLUXSTONE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> FLUXSTONE_WALL = ITEMS.register("fluxstone_wall", ()-> new BlockItem(AllBlocks.FLUXSTONE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> GEAR = ITEMS.register("gear", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEARBOX = ITEMS.register("gearbox", ()-> new BlockItem(AllBlocks.GEARBOX.get(), new Item.Properties()));
    public static final RegistryObject<Item> GOLD_PLATE = ITEMS.register("gold_plate", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLD_PLATE_BLOCK = ITEMS.register("gold_plate_block", ()-> new BlockItem(AllBlocks.GOLD_PLATE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> GOLD_PLATE_SLAB = ITEMS.register("gold_plate_slab", ()-> new BlockItem(AllBlocks.GOLD_PLATE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> GOLD_PLATE_STAIRS = ITEMS.register("gold_plate_stairs", ()-> new BlockItem(AllBlocks.GOLD_PLATE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> GOLD_ROD = ITEMS.register("gold_rod", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HIGH_SPEED_ACTIVATOR_RAIL = ITEMS.register("high_speed_activator_rail", ()-> new BlockItem(AllBlocks.HIGH_SPEED_ACTIVATOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> HIGH_SPEED_BUFFER_STOP_RAIL = ITEMS.register("high_speed_buffer_stop_rail", ()-> new BlockItem(AllBlocks.HIGH_SPEED_BUFFER_STOP_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> HIGH_SPEED_CHIME_RAIL = ITEMS.register("high_speed_chime_rail", ()-> new BlockItem(AllBlocks.HIGH_SPEED_CHIME_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> HIGH_SPEED_CROSSOVER_RAIL = ITEMS.register("high_speed_crossover_rail", ()-> new BlockItem(AllBlocks.HIGH_SPEED_CROSSOVER_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> HIGH_SPEED_DETECTOR_RAIL = ITEMS.register("high_speed_detector_rail", ()-> new BlockItem(AllBlocks.HIGH_SPEED_DETECTOR_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> HIGH_SPEED_ONE_WAY_RAIL = ITEMS.register("high_speed_one_way_rail", ()-> new BlockItem(AllBlocks.HIGH_SPEED_ONE_WAY_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> HIGH_SPEED_POWERED_RAIL = ITEMS.register("high_speed_powered_rail", ()-> new BlockItem(AllBlocks.HIGH_SPEED_POWERED_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> HIGH_SPEED_LIMITER_RAIL = ITEMS.register("high_speed_limiter_rail", ()-> new BlockItem(AllBlocks.HIGH_SPEED_LIMITER_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> HIGH_SPEED_LOCKING_RAIL = ITEMS.register("high_speed_locking_rail", ()-> new BlockItem(AllBlocks.HIGH_SPEED_LOCKING_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> HIGH_SPEED_RAIL = ITEMS.register("high_speed_rail", ()-> new BlockItem(AllBlocks.HIGH_SPEED_RAIL.get(), new Item.Properties()));

    public static final RegistryObject<Item> IRON_LADDER = ITEMS.register("iron_ladder", ()-> new BlockItem(AllBlocks.IRON_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_PLATE_BLOCK = ITEMS.register("iron_plate_block", ()-> new BlockItem(AllBlocks.IRON_PLATE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> IRON_PLATE_SLAB = ITEMS.register("iron_plate_slab", ()-> new BlockItem(AllBlocks.IRON_PLATE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> IRON_PLATE_STAIRS = ITEMS.register("iron_plate_stairs", ()-> new BlockItem(AllBlocks.IRON_PLATE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> IRON_ROD = ITEMS.register("iron_rod", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_ROLLS = ITEMS.register("iron_rolls", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_SCAFFOLDING = ITEMS.register("iron_scaffolding", ()-> new MetalScaffoldBlockItem(13, AllBlocks.IRON_SCAFFOLDING.get(), new Item.Properties()));
    public static final RegistryObject<Item> IRON_SCREW = ITEMS.register("iron_screw", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ITEM_POUCH = ITEMS.register("item_pouch", ()-> new PouchItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> KAOLIN = ITEMS.register("kaolin", ()-> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> COGWHEEL_LARGE = ITEMS.register("large_cogwheel", ()-> new BlockItem(AllBlocks.COGWHEEL_LARGE.get(), new Item.Properties()));
    public static final RegistryObject<Item> LINEN = ITEMS.register("linen", ()-> new FuelItem(50, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK = ITEMS.register("linen_block", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_WHITE = ITEMS.register("linen_block_white", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_WHITE.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_ORANGE = ITEMS.register("linen_block_orange", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_ORANGE.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_MAGENTA = ITEMS.register("linen_block_magenta", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_MAGENTA.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_LIGHT_BLUE = ITEMS.register("linen_block_light_blue", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_LIGHT_BLUE.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_YELLOW = ITEMS.register("linen_block_yellow", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_YELLOW.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_LIME = ITEMS.register("linen_block_lime", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_LIME.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_PINK = ITEMS.register("linen_block_pink", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_PINK.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_GRAY = ITEMS.register("linen_block_gray", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_GRAY.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_LIGHT_GRAY = ITEMS.register("linen_block_light_gray", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_LIGHT_GRAY.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_CYAN = ITEMS.register("linen_block_cyan", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_CYAN.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_PURPLE = ITEMS.register("linen_block_purple", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_PURPLE.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_BLUE = ITEMS.register("linen_block_blue", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_BLUE.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_BROWN = ITEMS.register("linen_block_brown", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_BROWN.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_GREEN = ITEMS.register("linen_block_green", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_GREEN.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_RED = ITEMS.register("linen_block_red", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_RED.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK_BLACK = ITEMS.register("linen_block_black", ()-> new FuelBlockItem(AllBlocks.LINEN_BLOCK_BLACK.get(), 200, new Item.Properties()));
    public static final RegistryObject<Item> LINSEED_OIL = ITEMS.register("linseed_oil",
            ()-> new LinseedOilItem(new Item.Properties()
                    .craftRemainder(Items.GLASS_BOTTLE)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3f).build())
            ));
    public static final RegistryObject<Item> LIMITER_RAIL = ITEMS.register("limiter_rail", ()-> new BlockItem(AllBlocks.LIMITER_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> LOCKING_RAIL = ITEMS.register("locking_rail", ()-> new BlockItem(AllBlocks.LOCKING_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> MILLSTONE = ITEMS.register("millstone", ()-> new BlockItem(AllBlocks.MILLSTONE.get(), new Item.Properties()));
    public static final RegistryObject<Item> ONE_WAY_RAIL = ITEMS.register("one_way_rail", ()-> new BlockItem(AllBlocks.ONE_WAY_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> PADDED_BOOTS = ITEMS.register("padded_boots", ()-> new PaddedArmorItem(AllArmorMaterials.LINEN, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> PADDED_CHESTPLATE = ITEMS.register("padded_chestplate", ()-> new PaddedArmorItem(AllArmorMaterials.LINEN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> PADDED_HELMET = ITEMS.register("padded_helmet", ()-> new PaddedArmorItem(AllArmorMaterials.LINEN, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> PADDED_LEGGINGS = ITEMS.register("padded_leggings", ()-> new PaddedArmorItem(AllArmorMaterials.LINEN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_FLUXSTONE = ITEMS.register("polished_fluxstone", ()-> new BlockItem(AllBlocks.POLISHED_FLUXSTONE.get(), new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_FLUXSTONE_SLAB = ITEMS.register("polished_fluxstone_slab", ()-> new BlockItem(AllBlocks.POLISHED_FLUXSTONE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_FLUXSTONE_STAIRS = ITEMS.register("polished_fluxstone_stairs", ()-> new BlockItem(AllBlocks.POLISHED_FLUXSTONE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_FLUXSTONE_WALL = ITEMS.register("polished_fluxstone_wall", ()-> new BlockItem(AllBlocks.POLISHED_FLUXSTONE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> PROSPECTORS_PICK = ITEMS.register("prospectors_pick", ()-> new ProspectorPickItem(ToolTiers.BRONZE, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> RAILROAD_TIE = ITEMS.register("railroad_tie", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REDSTONE_EMITTER = ITEMS.register("redstone_emitter", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TIN_BLOCK = ITEMS.register("raw_tin_block", ()-> new BlockItem(AllBlocks.RAW_TIN_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> REDSTONE_VALVE = ITEMS.register("redstone_valve", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROLLING_MILL = ITEMS.register("rolling_mill", ()-> new BlockItem(AllBlocks.ROLLING_MILL.get(), new Item.Properties()));
    public static final RegistryObject<Item> COGWHEEL_SMALL = ITEMS.register("small_cogwheel", ()-> new BlockItem(AllBlocks.COGWHEEL_SMALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> SACK = ITEMS.register("sack", ()-> new SackItem(AllBlocks.SACK.get(), new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SACK_WHITE = ITEMS.register("sack_white", ()-> new SackItem(AllBlocks.SACK_WHITE.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_ORANGE = ITEMS.register("sack_orange", ()-> new SackItem(AllBlocks.SACK_ORANGE.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_MAGENTA = ITEMS.register("sack_magenta", ()-> new SackItem(AllBlocks.SACK_MAGENTA.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_LIGHT_BLUE = ITEMS.register("sack_light_blue", ()-> new SackItem(AllBlocks.SACK_LIGHT_BLUE.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_YELLOW = ITEMS.register("sack_yellow", ()-> new SackItem(AllBlocks.SACK_YELLOW.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_LIME = ITEMS.register("sack_lime", ()-> new SackItem(AllBlocks.SACK_LIME.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_PINK = ITEMS.register("sack_pink", ()-> new SackItem(AllBlocks.SACK_PINK.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_GRAY = ITEMS.register("sack_gray", ()-> new SackItem(AllBlocks.SACK_GRAY.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_LIGHT_GRAY = ITEMS.register("sack_light_gray", ()-> new SackItem(AllBlocks.SACK_LIGHT_GRAY.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_CYAN = ITEMS.register("sack_cyan", ()-> new SackItem(AllBlocks.SACK_CYAN.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_PURPLE = ITEMS.register("sack_purple", ()-> new SackItem(AllBlocks.SACK_PURPLE.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_BLUE = ITEMS.register("sack_blue", ()-> new SackItem(AllBlocks.SACK_BLUE.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_BROWN = ITEMS.register("sack_brown", ()-> new SackItem(AllBlocks.SACK_BROWN.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_GREEN = ITEMS.register("sack_green", ()-> new SackItem(AllBlocks.SACK_GREEN.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_RED = ITEMS.register("sack_red", ()-> new SackItem(AllBlocks.SACK_RED.get(), new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> SACK_BLACK = ITEMS.register("sack_black", ()-> new SackItem(AllBlocks.SACK_BLACK.get(), new Item.Properties().stacksTo(1)));



    public static final RegistryObject<Item> TIN_BLOCK = ITEMS.register("tin_block", ()-> new BlockItem(AllBlocks.TIN_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> TIN_PLATE_BLOCK = ITEMS.register("tin_plate_block", ()-> new BlockItem(AllBlocks.TIN_PLATE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> TIN_PLATE_SLAB = ITEMS.register("tin_plate_slab", ()-> new BlockItem(AllBlocks.TIN_PLATE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> TIN_PLATE_STAIRS = ITEMS.register("tin_plate_stairs", ()-> new BlockItem(AllBlocks.TIN_PLATE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN_ORE = ITEMS.register("tin_ore", ()-> new BlockItem(AllBlocks.TIN_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> TIN_PLATE = ITEMS.register("tin_plate", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TIN_ROD = ITEMS.register("tin_rod", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TRIP_HAMMER = ITEMS.register("trip_hammer", ()-> new BlockItem(AllBlocks.TRIP_HAMMER.get(), new Item.Properties()));
    public static final RegistryObject<Item> VERMILION_BLOCK = ITEMS.register("vermilion_block", ()-> new BlockItem(AllBlocks.VERMILION_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> VERMILION_PRESSURE_PLATE = ITEMS.register("vermilion_pressure_plate", ()-> new BlockItem(AllBlocks.VERMILION_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> VERMILION_INGOT = ITEMS.register("vermilion_ingot", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VERMILION_PLATE = ITEMS.register("vermilion_plate", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> VERMILION_ROD = ITEMS.register("vermilion_rod", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WATER_WHEEL = ITEMS.register("water_wheel", ()-> new BlockItem(AllBlocks.WATER_WHEEL.get(), new Item.Properties()));
    public static final RegistryObject<Item> WIND_WHEEL = ITEMS.register("wind_wheel", ()-> new BlockItem(AllBlocks.WIND_WHEEL.get(), new Item.Properties()));
    public static final RegistryObject<Item> WIND_WHEEL_BLADE = ITEMS.register("wind_wheel_blade", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WIND_WHEEL_SAIL = ITEMS.register("wind_wheel_sail", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WOODEN_FRAME = ITEMS.register("wooden_frame", ()-> new BlockItem(AllBlocks.WOODEN_FRAME.get(), new Item.Properties()));
    public static final RegistryObject<Item> WOODEN_FRAME_SLAB = ITEMS.register("wooden_frame_slab", ()-> new BlockItem(AllBlocks.WOODEN_FRAME_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> WOODEN_FRAME_STAIRS = ITEMS.register("wooden_frame_stairs", ()-> new BlockItem(AllBlocks.WOODEN_FRAME_STAIRS.get(), new Item.Properties()));

    public static void registerAll(IEventBus modEventBus)
    {
        ITEMS.register(modEventBus);
    }

    public static String getName(Item item) { return ForgeRegistries.ITEMS.getKey(item).getPath(); }
}
