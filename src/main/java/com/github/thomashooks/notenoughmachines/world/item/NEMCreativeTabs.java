package com.github.thomashooks.notenoughmachines.world.item;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class NEMCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NotEnoughMachines.MOD_ID);

    public static final RegistryObject<CreativeModeTab> NEM_TAB =TABS.register("nem_tab",
            ()-> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + NotEnoughMachines.MOD_ID + ".nem_tab"))
                    .icon(NEMItems.GEAR.get()::getDefaultInstance)
                    .displayItems((itemDisplayParameters, output) ->
                    {
                        //Building Blocks - full
                        output.accept(NEMItems.COPPER_PLATE_BLOCK.get());
                        output.accept(NEMItems.IRON_PLATE_BLOCK.get());
                        output.accept(NEMItems.LINEN_BLOCK.get());
                        output.accept(NEMItems.FLUXSTONE.get());
                        output.accept(NEMItems.POLISHED_FLUXSTONE.get());
                        output.accept(NEMItems.WOODEN_FRAME.get());
                        //Building Blocks - slabs
                        output.accept(NEMItems.FLUXSTONE_SLAB.get());
                        output.accept(NEMItems.POLISHED_FLUXSTONE_SLAB.get());
                        //Building Blocks - stairs
                        output.accept(NEMItems.FLUXSTONE_STAIRS.get());
                        output.accept(NEMItems.POLISHED_FLUXSTONE_STAIRS.get());
                        //Building Blocks - fences

                        //Building Blocks - walls
                        output.accept(NEMItems.FLUXSTONE_WALL.get());
                        output.accept(NEMItems.POLISHED_FLUXSTONE_WALL.get());
                        //Machine Blocks - power transfer

                        //Machine Blocks - power generators

                        //Machine Blocks - mills/processors

                        //Machine Blocks - item transfer

                        //Redstone Blocks
                        output.accept(NEMItems.CONJUNCTIONER.get());
                        //Dust Items
                        output.accept(NEMItems.CRUSHED_COPPER_ORE.get());
                        output.accept(NEMItems.CRUSHED_IRON_ORE.get());
                        output.accept(NEMItems.CRUSHED_GOLD_ORE.get());
                        output.accept(NEMItems.FLUX.get());
                        output.accept(NEMItems.FLOUR.get());
                        //Seed Items
                        output.accept(NEMItems.FLAXSEED.get());
                        //Crafting Items
                        output.accept(NEMItems.FLAX.get());
                        output.accept(NEMItems.FLAX_STRING.get());
                        output.accept(NEMItems.LINEN.get());
                        output.accept(NEMItems.LINSEED_OIL.get());
                        output.accept(NEMItems.COPPER_PLATE.get());
                        output.accept(NEMItems.IRON_PLATE.get());
                        output.accept(NEMItems.COPPER_ROD.get());
                        output.accept(NEMItems.IRON_ROD.get());
                        output.accept(NEMItems.IRON_SCREW.get());
                        output.accept(NEMItems.GEAR.get());
                        output.accept(NEMItems.WIND_WHEEL_BLADE.get());
                        output.accept(NEMItems.WIND_WHEEL_SAIL.get());
                        output.accept(NEMItems.HAMMER_AND_ANVIL.get());
                        output.accept(NEMItems.ROLLERS.get());
                        output.accept(NEMItems.REDSTONE_COLLECTOR.get());
                        output.accept(NEMItems.REDSTONE_EMITTER.get());
                    })
                    .build()
    );

    public static void registerAll(IEventBus modEventBus)
    {
        TABS.register(modEventBus);
    }
}
