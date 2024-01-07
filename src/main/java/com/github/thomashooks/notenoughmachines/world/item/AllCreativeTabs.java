package com.github.thomashooks.notenoughmachines.world.item;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AllCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NotEnoughMachines.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN_TAB =TABS.register("main_tab",
            ()-> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + NotEnoughMachines.MOD_ID + ".main_tab"))
                    .icon(AllItems.GEAR.get()::getDefaultInstance)
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        //Wood Blocks
                        //Order: full block -> stairs -> slab -> wall -> fence -> fence gate -> door -> trapdoor -> pressure plate -> button
                        output.accept(AllItems.WOODEN_FRAME.get());
                        output.accept(AllItems.WOODEN_FRAME_STAIRS.get());
                        output.accept(AllItems.WOODEN_FRAME_SLAB.get());

                        //Stone Blocks
                        output.accept(AllItems.FLUXSTONE.get());
                        output.accept(AllItems.FLUXSTONE_STAIRS.get());
                        output.accept(AllItems.FLUXSTONE_SLAB.get());
                        output.accept(AllItems.FLUXSTONE_WALL.get());

                        output.accept(AllItems.POLISHED_FLUXSTONE.get());
                        output.accept(AllItems.POLISHED_FLUXSTONE_STAIRS.get());
                        output.accept(AllItems.POLISHED_FLUXSTONE_SLAB.get());
                        output.accept(AllItems.POLISHED_FLUXSTONE_WALL.get());

                        output.accept(AllItems.FIRE_BRICKS.get());
                        output.accept(AllItems.FIRE_BRICKS_STAIRS.get());
                        output.accept(AllItems.FIRE_BRICKS_SLAB.get());
                        output.accept(AllItems.FIRE_BRICKS_WALL.get());

                        //Fuel Blocks
                        output.accept(AllItems.COKE_BLOCK.get());

                        //Metal Blocks
                        output.accept(AllItems.IRON_PLATE_BLOCK.get());
                        output.accept(AllItems.IRON_PLATE_STAIRS.get());
                        output.accept(AllItems.IRON_PLATE_SLAB.get());

                        output.accept(AllItems.COPPER_PLATE_BLOCK.get());
                        output.accept(AllItems.COPPER_PLATE_STAIRS.get());
                        output.accept(AllItems.COPPER_PLATE_SLAB.get());

                        output.accept(AllItems.COPPER_PLATE_BLOCK_EXPOSED.get());
                        output.accept(AllItems.COPPER_PLATE_STAIRS_EXPOSED.get());
                        output.accept(AllItems.COPPER_PLATE_SLAB_EXPOSED.get());

                        output.accept(AllItems.COPPER_PLATE_BLOCK_WEATHERED.get());
                        output.accept(AllItems.COPPER_PLATE_STAIRS_WEATHERED.get());
                        output.accept(AllItems.COPPER_PLATE_SLAB_WEATHERED.get());

                        output.accept(AllItems.COPPER_PLATE_BLOCK_OXIDIZED.get());
                        output.accept(AllItems.COPPER_PLATE_STAIRS_OXIDIZED.get());
                        output.accept(AllItems.COPPER_PLATE_SLAB_OXIDIZED.get());

                        output.accept(AllItems.COPPER_PLATE_BLOCK_WAXED.get());
                        output.accept(AllItems.COPPER_PLATE_STAIRS_WAXED.get());
                        output.accept(AllItems.COPPER_PLATE_SLAB_WAXED.get());

                        output.accept(AllItems.COPPER_PLATE_BLOCK_EXPOSED_WAXED.get());
                        output.accept(AllItems.COPPER_PLATE_STAIRS_EXPOSED_WAXED.get());
                        output.accept(AllItems.COPPER_PLATE_SLAB_EXPOSED_WAXED.get());

                        output.accept(AllItems.COPPER_PLATE_BLOCK_WEATHERED_WAXED.get());
                        output.accept(AllItems.COPPER_PLATE_STAIRS_WEATHERED_WAXED.get());
                        output.accept(AllItems.COPPER_PLATE_SLAB_WEATHERED_WAXED.get());

                        output.accept(AllItems.COPPER_PLATE_BLOCK_OXIDIZED_WAXED.get());
                        output.accept(AllItems.COPPER_PLATE_STAIRS_OXIDIZED_WAXED.get());
                        output.accept(AllItems.COPPER_PLATE_SLAB_OXIDIZED_WAXED.get());

                        output.accept(AllItems.TIN_BLOCK.get());
                        output.accept(AllItems.TIN_PLATE_BLOCK.get());
                        output.accept(AllItems.TIN_PLATE_STAIRS.get());
                        output.accept(AllItems.TIN_PLATE_SLAB.get());

                        output.accept(AllItems.BRONZE_BLOCK.get());
                        output.accept(AllItems.BRONZE_PLATE_BLOCK.get());
                        output.accept(AllItems.BRONZE_PLATE_STAIRS.get());
                        output.accept(AllItems.BRONZE_PLATE_SLAB.get());

                        output.accept(AllItems.GOLD_PLATE_BLOCK.get());
                        output.accept(AllItems.GOLD_PLATE_STAIRS.get());
                        output.accept(AllItems.GOLD_PLATE_SLAB.get());

                        output.accept(AllItems.VERMILION_BLOCK.get());
                        output.accept(AllItems.VERMILION_PRESSURE_PLATE.get());

                        //Cloth Blocks
                        output.accept(AllItems.LINEN_BLOCK.get());
                        output.accept(AllItems.LINEN_BLOCK_WHITE.get());
                        output.accept(AllItems.LINEN_BLOCK_ORANGE.get());
                        output.accept(AllItems.LINEN_BLOCK_MAGENTA.get());
                        output.accept(AllItems.LINEN_BLOCK_LIGHT_BLUE.get());
                        output.accept(AllItems.LINEN_BLOCK_YELLOW.get());
                        output.accept(AllItems.LINEN_BLOCK_LIME.get());
                        output.accept(AllItems.LINEN_BLOCK_PINK.get());
                        output.accept(AllItems.LINEN_BLOCK_GRAY.get());
                        output.accept(AllItems.LINEN_BLOCK_LIGHT_GRAY.get());
                        output.accept(AllItems.LINEN_BLOCK_CYAN.get());
                        output.accept(AllItems.LINEN_BLOCK_PURPLE.get());
                        output.accept(AllItems.LINEN_BLOCK_BLUE.get());
                        output.accept(AllItems.LINEN_BLOCK_BROWN.get());
                        output.accept(AllItems.LINEN_BLOCK_GREEN.get());
                        output.accept(AllItems.LINEN_BLOCK_RED.get());
                        output.accept(AllItems.LINEN_BLOCK_BLACK.get());

                        //Ore Blocks
                        output.accept(AllItems.TIN_ORE.get());
                        output.accept(AllItems.RAW_TIN_BLOCK.get());

                        //Machine Blocks - Power Transfer
                        output.accept(AllItems.AXLE.get());
                        output.accept(AllItems.COGWHEEL_SMALL.get());
                        output.accept(AllItems.COGWHEEL_LARGE.get());
                        output.accept(AllItems.ENCLOSED_AXLE.get());
                        output.accept(AllItems.GEARBOX.get());

                        //Machine Blocks - Power Generators
                        output.accept(AllItems.WATER_WHEEL.get());
                        output.accept(AllItems.WIND_WHEEL.get());

                        //Machine Blocks - Mills/Processors
                        output.accept(AllItems.MILLSTONE.get());
                        output.accept(AllItems.ROLLING_MILL.get());
                        output.accept(AllItems.TRIP_HAMMER.get());

                        //Furnaces
                        output.accept(AllItems.COKE_OVEN.get());

                        //Ladders/Scaffolding
                        output.accept(AllItems.IRON_LADDER.get());
                        output.accept(AllItems.IRON_SCAFFOLDING.get());

                        output.accept(AllItems.BRONZE_LADDER.get());
                        output.accept(AllItems.BRONZE_SCAFFOLDING.get());

                        //Redstone Blocks
                        output.accept(AllItems.CONJUNCTIONER.get());

                        //Item Transfer Blocks
                        output.accept(AllItems.FILTER.get());

                        //Minecart Rails
                        output.accept(AllItems.CROSSOVER_RAIL.get());
                        output.accept(AllItems.BUFFER_STOP_RAIL.get());
                        output.accept(AllItems.LIMITER_RAIL.get());
                        output.accept(AllItems.ONE_WAY_RAIL.get());
                        output.accept(AllItems.LOCKING_RAIL.get());
                        output.accept(AllItems.CHIME_RAIL.get());

                        output.accept(AllItems.HIGH_SPEED_RAIL.get());
                        output.accept(AllItems.HIGH_SPEED_CROSSOVER_RAIL.get());
                        output.accept(AllItems.HIGH_SPEED_BUFFER_STOP_RAIL.get());
                        output.accept(AllItems.HIGH_SPEED_LIMITER_RAIL.get());
                        output.accept(AllItems.HIGH_SPEED_POWERED_RAIL.get());
                        output.accept(AllItems.HIGH_SPEED_ONE_WAY_RAIL.get());
                        output.accept(AllItems.HIGH_SPEED_LOCKING_RAIL.get());
                        output.accept(AllItems.HIGH_SPEED_DETECTOR_RAIL.get());
                        output.accept(AllItems.HIGH_SPEED_CHIME_RAIL.get());
                        output.accept(AllItems.HIGH_SPEED_ACTIVATOR_RAIL.get());

                        //Minecarts

                        //Tools
                        output.accept(AllItems.ITEM_POUCH.get());
                        output.accept(AllItems.PROSPECTORS_PICK.get());

                        //Weapons
                        output.accept(AllItems.BRONZE_MACE.get());

                        //Armor
                        output.accept(AllItems.PADDED_HELMET.get());
                        output.accept(AllItems.PADDED_CHESTPLATE.get());
                        output.accept(AllItems.PADDED_LEGGINGS.get());
                        output.accept(AllItems.PADDED_BOOTS.get());

                        //Food
                        output.accept(AllItems.FLOUR.get());

                        //Raw Ore
                        output.accept(AllItems.COKE.get());
                        output.accept(AllItems.RAW_TIN.get());

                        //Raw Crushed Ore
                        output.accept(AllItems.CRUSHED_IRON_ORE.get());
                        output.accept(AllItems.CRUSHED_COPPER_ORE.get());
                        output.accept(AllItems.CRUSHED_TIN_ORE.get());
                        output.accept(AllItems.CRUSHED_BRONZE.get());
                        output.accept(AllItems.CRUSHED_GOLD_ORE.get());
                        output.accept(AllItems.CRUSHED_VERMILION.get());

                        //Metal Ingots
                        output.accept(AllItems.TIN_INGOT.get());
                        output.accept(AllItems.BRONZE_INGOT.get());
                        output.accept(AllItems.VERMILION_INGOT.get());

                        //Metal Plates
                        output.accept(AllItems.IRON_PLATE.get());
                        output.accept(AllItems.COPPER_PLATE.get());
                        output.accept(AllItems.TIN_PLATE.get());
                        output.accept(AllItems.BRONZE_PLATE.get());
                        output.accept(AllItems.GOLD_PLATE.get());
                        output.accept(AllItems.VERMILION_PLATE.get());

                        //Metal Rods
                        output.accept(AllItems.IRON_ROD.get());
                        output.accept(AllItems.IRON_SCREW.get());
                        output.accept(AllItems.COPPER_ROD.get());
                        output.accept(AllItems.TIN_ROD.get());
                        output.accept(AllItems.BRONZE_ROD.get());
                        output.accept(AllItems.GOLD_ROD.get());
                        output.accept(AllItems.VERMILION_ROD.get());
                        output.accept(AllItems.BOOSTER_ROD.get());
                        output.accept(AllItems.BRONZE_BOOSTER_ROD.get());

                        //Raw Dust Items
                        output.accept(AllItems.FLUX.get());
                        output.accept(AllItems.KAOLIN.get());

                        //Bricks
                        output.accept(AllItems.FIRE_BRICK.get());

                        //Crop Items
                        output.accept(AllItems.FLAXSEED.get());
                        output.accept(AllItems.FLAX.get());
                        output.accept(AllItems.FLAX_STRING.get());
                        output.accept(AllItems.LINEN.get());
                        output.accept(AllItems.LINSEED_OIL.get());

                        //Misc Crafting Items
                        output.accept(AllItems.GEAR.get());
                        output.accept(AllItems.IRON_ROLLS.get());
                        output.accept(AllItems.RAILROAD_TIE.get());
                        output.accept(AllItems.WIND_WHEEL_BLADE.get());
                        output.accept(AllItems.WIND_WHEEL_SAIL.get());
                        output.accept(AllItems.REDSTONE_EMITTER.get());
                        output.accept(AllItems.REDSTONE_VALVE.get());
                    })
                    .build()
    );

    public static void registerAll(IEventBus modEventBus) { TABS.register(modEventBus); }
}
