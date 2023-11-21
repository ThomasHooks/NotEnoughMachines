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

    //Building Blocks - full
    public static final RegistryObject<Item> COPPER_PLATE_BLOCK = ITEMS.register("copper_plate_block",
            ()-> new BlockItem(AllBlocks.COPPER_PLATE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> FILTER = ITEMS.register("filter",
            ()-> new BlockItem(AllBlocks.FILTER.get(), new Item.Properties()));
    public static final RegistryObject<Item> FLUXSTONE = ITEMS.register("fluxstone",
            ()-> new BlockItem(AllBlocks.FLUXSTONE.get(), new Item.Properties()));
    public static final RegistryObject<Item> IRON_PLATE_BLOCK = ITEMS.register("iron_plate_block",
            ()-> new BlockItem(AllBlocks.IRON_PLATE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_FLUXSTONE = ITEMS.register("polished_fluxstone",
            ()-> new BlockItem(AllBlocks.POLISHED_FLUXSTONE.get(), new Item.Properties()));
    public static final RegistryObject<Item> LINEN_BLOCK = ITEMS.register("linen_block",
            ()-> new BlockItem(AllBlocks.LINEN_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> WOODEN_FRAME = ITEMS.register("wooden_frame",
            ()-> new BlockItem(AllBlocks.WOODEN_FRAME.get(), new Item.Properties()));
    //Building Blocks - slabs
    public static final RegistryObject<Item> FLUXSTONE_SLAB = ITEMS.register("fluxstone_slab",
            ()-> new BlockItem(AllBlocks.FLUXSTONE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_FLUXSTONE_SLAB = ITEMS.register("polished_fluxstone_slab",
            ()-> new BlockItem(AllBlocks.POLISHED_FLUXSTONE_SLAB.get(), new Item.Properties()));
    //Building Blocks - stairs
    public static final RegistryObject<Item> FLUXSTONE_STAIRS = ITEMS.register("fluxstone_stairs",
            ()-> new BlockItem(AllBlocks.FLUXSTONE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_FLUXSTONE_STAIRS = ITEMS.register("polished_fluxstone_stairs",
            ()-> new BlockItem(AllBlocks.POLISHED_FLUXSTONE_STAIRS.get(), new Item.Properties()));
    //Building Blocks - walls/fences
    public static final RegistryObject<Item> FLUXSTONE_WALL = ITEMS.register("fluxstone_wall",
            ()-> new BlockItem(AllBlocks.FLUXSTONE_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> POLISHED_FLUXSTONE_WALL = ITEMS.register("polished_fluxstone_wall",
            ()-> new BlockItem(AllBlocks.POLISHED_FLUXSTONE_WALL.get(), new Item.Properties()));
    //Miscellaneous - dust items
    public static final RegistryObject<Item> CRUSHED_IRON_ORE = ITEMS.register("crushed_iron_ore",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_GOLD_ORE = ITEMS.register("crushed_gold_ore",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_COPPER_ORE = ITEMS.register("crushed_copper_ore",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLUX = ITEMS.register("flux",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLOUR = ITEMS.register("flour",
            ()-> new Item(new Item.Properties()));
    //Seed Items
    public static final RegistryObject<Item> FLAXSEED = ITEMS.register("flaxseed",
            ()-> new ItemNameBlockItem(AllBlocks.FLAXPLANT.get(), new Item.Properties()));
    //Crafting Items
    public static final RegistryObject<Item> FLAX = ITEMS.register("flax",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLAX_STRING = ITEMS.register("flax_string",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LINEN = ITEMS.register("linen",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LINSEED_OIL = ITEMS.register("linseed_oil",
            ()-> new LinseedOilItem(new Item.Properties()
                    .craftRemainder(Items.GLASS_BOTTLE)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3f).build())
                    .stacksTo(16)));
    public static final RegistryObject<Item> COPPER_PLATE = ITEMS.register("copper_plate",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_ROD = ITEMS.register("copper_rod",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_ROD = ITEMS.register("iron_rod",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_SCREW = ITEMS.register("iron_screw",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEAR = ITEMS.register("gear",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WIND_WHEEL_BLADE = ITEMS.register("wind_wheel_blade",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WIND_WHEEL_SAIL = ITEMS.register("wind_wheel_sail",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HAMMER_AND_ANVIL = ITEMS.register("hammer_and_anvil",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROLLERS = ITEMS.register("rollers",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REDSTONE_COLLECTOR = ITEMS.register("redstone_collector",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REDSTONE_EMITTER = ITEMS.register("redstone_emitter",
            ()-> new Item(new Item.Properties()));
    //Machine Blocks - power transfer
    public static final RegistryObject<Item> AXLE = ITEMS.register("axle",
            ()-> new BlockItem(AllBlocks.AXLE.get(), new Item.Properties()));

    //Machine Blocks - power generators
    public static final RegistryObject<Item> WATER_WHEEL = ITEMS.register("water_wheel",
            ()-> new BlockItem(AllBlocks.WATER_WHEEL.get(), new Item.Properties()));
    //Machine Blocks - mills/processors
    public static final RegistryObject<Item> MILLSTONE = ITEMS.register("millstone",
            ()-> new BlockItem(AllBlocks.MILLSTONE.get(), new Item.Properties()));
    //Machine Blocks - item transfer

    //Redstone Blocks
    public static final RegistryObject<Item> CONJUNCTIONER = ITEMS.register("conjunctioner",
            ()-> new BlockItem(AllBlocks.CONJUNCTIONER.get(), new Item.Properties()));

    public static void registerAll(IEventBus modEventBus)
    {
        ITEMS.register(modEventBus);
    }
}
