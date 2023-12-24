package com.github.thomashooks.notenoughmachines.world.item;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
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
    public static final RegistryObject<Item> BRONZE_LADDER = ITEMS.register("bronze_ladder", ()-> new BlockItem(AllBlocks.BRONZE_LADDER.get(), new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_PLATE_BLOCK = ITEMS.register("bronze_plate_block", ()-> new BlockItem(AllBlocks.BRONZE_PLATE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_PLATE_SLAB = ITEMS.register("bronze_plate_slab", ()-> new BlockItem(AllBlocks.BRONZE_PLATE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_PLATE_STAIRS = ITEMS.register("bronze_plate_stairs", ()-> new BlockItem(AllBlocks.BRONZE_PLATE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_PLATE = ITEMS.register("bronze_plate", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_ROD = ITEMS.register("bronze_rod", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_SCAFFOLDING = ITEMS.register("bronze_scaffolding", ()-> new MetalScaffoldBlockItem(19, AllBlocks.BRONZE_SCAFFOLDING.get(), new Item.Properties()));
    public static final RegistryObject<Item> BUFFER_STOP_RAIL = ITEMS.register("buffer_stop_rail", ()-> new BlockItem(AllBlocks.BUFFER_STOP_RAIL.get(), new Item.Properties()));

    public static final RegistryObject<Item> CHIME_RAIL = ITEMS.register("chime_rail", ()-> new BlockItem(AllBlocks.CHIME_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> COKE = ITEMS.register("coke", ()-> new FuelItem(new Item.Properties(), 3200));
    public static final RegistryObject<Item> COKE_BLOCK = ITEMS.register("coke_block", ()-> new FuelBlockItem(AllBlocks.COKE_BLOCK.get(), new Item.Properties(), 32000));
    public static final RegistryObject<Item> COKE_OVEN = ITEMS.register("coke_oven", ()-> new BlockItem(AllBlocks.COKE_OVEN.get(), new Item.Properties()));
    public static final RegistryObject<Item> CONJUNCTIONER = ITEMS.register("conjunctioner", ()-> new BlockItem(AllBlocks.CONJUNCTIONER.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE = ITEMS.register("copper_plate", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_BLOCK = ITEMS.register("copper_plate_block", ()-> new BlockItem(AllBlocks.COPPER_PLATE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_SLAB = ITEMS.register("copper_plate_slab", ()-> new BlockItem(AllBlocks.COPPER_PLATE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> COPPER_PLATE_STAIRS = ITEMS.register("copper_plate_stairs", ()-> new BlockItem(AllBlocks.COPPER_PLATE_STAIRS.get(), new Item.Properties()));
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
    public static final RegistryObject<Item> HEAVY_BRONZE_STAMP = ITEMS.register("heavy_bronze_stamp", ()-> new Item(new Item.Properties()));
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
    public static final RegistryObject<Item> KAOLIN = ITEMS.register("kaolin", ()-> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> COGWHEEL_LARGE = ITEMS.register("large_cogwheel", ()-> new BlockItem(AllBlocks.COGWHEEL_LARGE.get(), new Item.Properties()));
    public static final RegistryObject<Item> LINEN = ITEMS.register("linen", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK = ITEMS.register("linen_block", ()-> new BlockItem(AllBlocks.LINEN_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> LINSEED_OIL = ITEMS.register("linseed_oil",
            ()-> new LinseedOilItem(new Item.Properties()
                    .craftRemainder(Items.GLASS_BOTTLE)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3f).build())
                    .stacksTo(16)
            ));
    public static final RegistryObject<Item> LIMITER_RAIL = ITEMS.register("limiter_rail", ()-> new BlockItem(AllBlocks.LIMITER_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> LOCKING_RAIL = ITEMS.register("locking_rail", ()-> new BlockItem(AllBlocks.LOCKING_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> MILLSTONE = ITEMS.register("millstone", ()-> new BlockItem(AllBlocks.MILLSTONE.get(), new Item.Properties()));
    public static final RegistryObject<Item> ONE_WAY_RAIL = ITEMS.register("one_way_rail", ()-> new BlockItem(AllBlocks.ONE_WAY_RAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_FLUXSTONE = ITEMS.register("polished_fluxstone", ()-> new BlockItem(AllBlocks.POLISHED_FLUXSTONE.get(), new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_FLUXSTONE_SLAB = ITEMS.register("polished_fluxstone_slab", ()-> new BlockItem(AllBlocks.POLISHED_FLUXSTONE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_FLUXSTONE_STAIRS = ITEMS.register("polished_fluxstone_stairs", ()-> new BlockItem(AllBlocks.POLISHED_FLUXSTONE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_FLUXSTONE_WALL = ITEMS.register("polished_fluxstone_wall", ()-> new BlockItem(AllBlocks.POLISHED_FLUXSTONE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> RAILROAD_TIE = ITEMS.register("railroad_tie", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REDSTONE_EMITTER = ITEMS.register("redstone_emitter", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REDSTONE_VALVE = ITEMS.register("redstone_valve", ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROLLING_MILL = ITEMS.register("rolling_mill", ()-> new BlockItem(AllBlocks.ROLLING_MILL.get(), new Item.Properties()));
    public static final RegistryObject<Item> COGWHEEL_SMALL = ITEMS.register("small_cogwheel", ()-> new BlockItem(AllBlocks.COGWHEEL_SMALL.get(), new Item.Properties()));
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
