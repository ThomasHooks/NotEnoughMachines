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
                        output.accept(AllItems.COPPER_PLATE_BLOCK.get());
                        output.accept(AllItems.IRON_PLATE_BLOCK.get());
                        output.accept(AllItems.LINEN_BLOCK.get());
                        output.accept(AllItems.FLUXSTONE.get());
                        output.accept(AllItems.POLISHED_FLUXSTONE.get());
                        output.accept(AllItems.WOODEN_FRAME.get());
                        //Building Blocks - slabs
                        output.accept(AllItems.FLUXSTONE_SLAB.get());
                        output.accept(AllItems.POLISHED_FLUXSTONE_SLAB.get());
                        //Building Blocks - stairs
                        output.accept(AllItems.FLUXSTONE_STAIRS.get());
                        output.accept(AllItems.POLISHED_FLUXSTONE_STAIRS.get());
                        //Building Blocks - fences

                        //Building Blocks - walls
                        output.accept(AllItems.FLUXSTONE_WALL.get());
                        output.accept(AllItems.POLISHED_FLUXSTONE_WALL.get());
                        //Machine Blocks - power transfer
                        output.accept(AllItems.AXLE.get());
                        output.accept(AllItems.COGWHEEL_SMALL.get());
                        output.accept(AllItems.ENCLOSED_AXLE.get());
                        output.accept(AllItems.GEARBOX.get());
                        //Machine Blocks - power generators
                        output.accept(AllItems.WATER_WHEEL.get());
                        //Machine Blocks - mills/processors
                        output.accept(AllItems.MILLSTONE.get());
                        //Machine Blocks - item transfer
                        output.accept(AllItems.FILTER.get());
                        //Redstone Blocks
                        output.accept(AllItems.CONJUNCTIONER.get());
                        //Dust Items
                        output.accept(AllItems.CRUSHED_COPPER_ORE.get());
                        output.accept(AllItems.CRUSHED_IRON_ORE.get());
                        output.accept(AllItems.CRUSHED_GOLD_ORE.get());
                        output.accept(AllItems.FLUX.get());
                        output.accept(AllItems.FLOUR.get());
                        //Seed Items
                        output.accept(AllItems.FLAXSEED.get());
                        //Crafting Items
                        output.accept(AllItems.FLAX.get());
                        output.accept(AllItems.FLAX_STRING.get());
                        output.accept(AllItems.LINEN.get());
                        output.accept(AllItems.LINSEED_OIL.get());
                        output.accept(AllItems.COPPER_PLATE.get());
                        output.accept(AllItems.IRON_PLATE.get());
                        output.accept(AllItems.COPPER_ROD.get());
                        output.accept(AllItems.IRON_ROD.get());
                        output.accept(AllItems.IRON_SCREW.get());
                        output.accept(AllItems.GEAR.get());
                        output.accept(AllItems.WIND_WHEEL_BLADE.get());
                        output.accept(AllItems.WIND_WHEEL_SAIL.get());
                        output.accept(AllItems.HAMMER_AND_ANVIL.get());
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
