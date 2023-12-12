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

    public static final RegistryObject<CreativeModeTab> NEM_TAB =TABS.register("nem_tab",
            ()-> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + NotEnoughMachines.MOD_ID + ".nem_tab"))
                    .icon(AllItems.GEAR.get()::getDefaultInstance)
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        //Building Blocks - full
                        output.accept(AllItems.BRONZE_BLOCK.get());
                        output.accept(AllItems.TIN_BLOCK.get());
                        output.accept(AllItems.COPPER_PLATE_BLOCK.get());
                        output.accept(AllItems.IRON_PLATE_BLOCK.get());
                        output.accept(AllItems.COKE_BLOCK.get());
                        output.accept(AllItems.LINEN_BLOCK.get());
                        output.accept(AllItems.FIRE_BRICKS.get());
                        output.accept(AllItems.FLUXSTONE.get());
                        output.accept(AllItems.POLISHED_FLUXSTONE.get());
                        output.accept(AllItems.WOODEN_FRAME.get());
                        //Building Blocks - slabs
                        output.accept(AllItems.FIRE_BRICKS_SLAB.get());
                        output.accept(AllItems.FLUXSTONE_SLAB.get());
                        output.accept(AllItems.POLISHED_FLUXSTONE_SLAB.get());
                        //Building Blocks - stairs
                        output.accept(AllItems.FIRE_BRICKS_STAIRS.get());
                        output.accept(AllItems.FLUXSTONE_STAIRS.get());
                        output.accept(AllItems.POLISHED_FLUXSTONE_STAIRS.get());
                        //Building Blocks - fences

                        //Building Blocks - walls
                        output.accept(AllItems.FIRE_BRICKS_WALL.get());
                        output.accept(AllItems.FLUXSTONE_WALL.get());
                        output.accept(AllItems.POLISHED_FLUXSTONE_WALL.get());
                        //Ore Blocks
                        output.accept(AllItems.TIN_ORE.get());
                        //Machine Blocks - power transfer
                        output.accept(AllItems.AXLE.get());
                        output.accept(AllItems.COGWHEEL_SMALL.get());
                        output.accept(AllItems.COGWHEEL_LARGE.get());
                        output.accept(AllItems.ENCLOSED_AXLE.get());
                        output.accept(AllItems.GEARBOX.get());
                        //Machine Blocks - power generators
                        output.accept(AllItems.WATER_WHEEL.get());
                        output.accept(AllItems.WIND_WHEEL.get());
                        //Machine Blocks - mills/processors
                        output.accept(AllItems.COKE_OVEN.get());
                        output.accept(AllItems.MILLSTONE.get());
                        output.accept(AllItems.ROLLING_MILL.get());
                        output.accept(AllItems.TRIP_HAMMER.get());
                        //Machine Blocks - item transfer
                        output.accept(AllItems.FILTER.get());
                        //Mine Cart Rails
                        output.accept(AllItems.CROSSOVER_RAIL.get());
                        output.accept(AllItems.ONE_WAY_RAIL.get());
                        //Redstone Blocks
                        output.accept(AllItems.CONJUNCTIONER.get());
                        //Crafting Items - Raw Ore
                        output.accept(AllItems.COKE.get());
                        output.accept(AllItems.RAW_TIN.get());
                        //Crafting Items - Dust
                        output.accept(AllItems.CRUSHED_BRONZE.get());
                        output.accept(AllItems.CRUSHED_COPPER_ORE.get());
                        output.accept(AllItems.CRUSHED_GOLD_ORE.get());
                        output.accept(AllItems.CRUSHED_IRON_ORE.get());
                        output.accept(AllItems.CRUSHED_TIN_ORE.get());
                        output.accept(AllItems.FLUX.get());
                        output.accept(AllItems.FLOUR.get());
                        output.accept(AllItems.KAOLIN.get());
                        //Crafting Items - Ingot/Brick
                        output.accept(AllItems.FIRE_BRICK.get());
                        output.accept(AllItems.BRONZE_INGOT.get());
                        output.accept(AllItems.TIN_INGOT.get());
                        //Crafting Items - Metal
                        output.accept(AllItems.BRONZE_PLATE.get());
                        output.accept(AllItems.COPPER_PLATE.get());
                        output.accept(AllItems.GOLD_PLATE.get());
                        output.accept(AllItems.IRON_PLATE.get());
                        output.accept(AllItems.TIN_PLATE.get());
                        output.accept(AllItems.BRONZE_ROD.get());
                        output.accept(AllItems.COPPER_ROD.get());
                        output.accept(AllItems.GOLD_ROD.get());
                        output.accept(AllItems.IRON_ROD.get());
                        output.accept(AllItems.TIN_ROD.get());
                        output.accept(AllItems.IRON_SCREW.get());
                        //Crafting Items - Misc
                        output.accept(AllItems.BOOSTER_ROD.get());
                        output.accept(AllItems.FLAXSEED.get());
                        output.accept(AllItems.FLAX.get());
                        output.accept(AllItems.FLAX_STRING.get());
                        output.accept(AllItems.LINEN.get());
                        output.accept(AllItems.LINSEED_OIL.get());
                        output.accept(AllItems.GEAR.get());
                        output.accept(AllItems.RAILROAD_TIE.get());
                        output.accept(AllItems.WIND_WHEEL_BLADE.get());
                        output.accept(AllItems.WIND_WHEEL_SAIL.get());
                        output.accept(AllItems.HEAVY_BRONZE_STAMP.get());
                        output.accept(AllItems.ROLLERS.get());
                        output.accept(AllItems.REDSTONE_COLLECTOR.get());
                        output.accept(AllItems.REDSTONE_EMITTER.get());
                    })
                    .build()
    );

    public static void registerAll(IEventBus modEventBus)
    {
        TABS.register(modEventBus);
    }
}
