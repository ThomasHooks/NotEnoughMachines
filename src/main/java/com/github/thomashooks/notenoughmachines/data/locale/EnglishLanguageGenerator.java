package com.github.thomashooks.notenoughmachines.data.locale;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.util.TooltipKeys;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.function.Supplier;

public class EnglishLanguageGenerator extends LanguageProvider
{
    public EnglishLanguageGenerator(PackOutput output)
    {
        super(output, NotEnoughMachines.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations()
    {
        addCreativeTab("main_tab", "Not Enough Machines");

        addBlock(AllBlocks.AXLE, "Axle");
        addBlock(AllBlocks.BRONZE_BLOCK, "Block of Bronze");
        addBlock(AllBlocks.BRONZE_GRATE, "Bronze Grate");
        addBlock(AllBlocks.BRONZE_LADDER, "Bronze Ladder");
        addBlock(AllBlocks.BRONZE_PLATE_BLOCK, "Block of Bronze Plates");
        addBlock(AllBlocks.BRONZE_PLATE_SLAB, "Slab of Bronze Plates");
        addBlock(AllBlocks.BRONZE_PLATE_STAIRS, "Stairs of Bronze Plates");
        addBlock(AllBlocks.BRONZE_SCAFFOLDING, "Bronze Scaffold");
        addBlock(AllBlocks.COGWHEEL_LARGE, "Large Cogwheel");
        addBlock(AllBlocks.COGWHEEL_SMALL, "Small Cogwheel");
        addBlock(AllBlocks.COKE_BLOCK, "Block of Coke");
        addBlock(AllBlocks.BUFFER_STOP_RAIL, "Buffer Stop Rail");
        addBlock(AllBlocks.CHIME_RAIL, "Chime Rail");
        addBlock(AllBlocks.COKE_OVEN, "Coke Oven");
        addBlock(AllBlocks.CROSSOVER_RAIL, "Crossover Rail");
        addBlock(AllBlocks.ENCLOSED_AXLE, "Enclosed Axle");
        addBlock(AllBlocks.FIRE_BRICKS, "Fire Bricks");
        addBlock(AllBlocks.FIRE_BRICKS_SLAB, "Fire Brick Slab");
        addBlock(AllBlocks.FIRE_BRICKS_STAIRS, "Fire Brick Stairs");
        addBlock(AllBlocks.FIRE_BRICKS_WALL, "Fire Brick Wall");
        addBlock(AllBlocks.GEARBOX, "Gearbox");
        addBlock(AllBlocks.CONJUNCTIONER, "Redstone Conjunctioner");
        addBlock(AllBlocks.COPPER_PLATE_BLOCK, "Block of Copper Plates");
        addBlock(AllBlocks.COPPER_PLATE_BLOCK_EXPOSED, "Exposed Copper Plates");
        addBlock(AllBlocks.COPPER_PLATE_BLOCK_WEATHERED, "Weathered Copper Plates");
        addBlock(AllBlocks.COPPER_PLATE_BLOCK_OXIDIZED, "Oxidized Copper Plates");
        addBlock(AllBlocks.COPPER_PLATE_BLOCK_WAXED, "Waxed Block of Copper Plates");
        addBlock(AllBlocks.COPPER_PLATE_BLOCK_EXPOSED_WAXED, "Waxed Exposed Copper Plates");
        addBlock(AllBlocks.COPPER_PLATE_BLOCK_WEATHERED_WAXED, "Waxed Weathered Copper Plates");
        addBlock(AllBlocks.COPPER_PLATE_BLOCK_OXIDIZED_WAXED, "Waxed Oxidized Copper Plates");
        addBlock(AllBlocks.COPPER_PLATE_SLAB, "Slab of Copper Plates");
        addBlock(AllBlocks.COPPER_PLATE_SLAB_EXPOSED, "Exposed Copper Plate Slab");
        addBlock(AllBlocks.COPPER_PLATE_SLAB_WEATHERED, "Weathered Copper Plate Slab");
        addBlock(AllBlocks.COPPER_PLATE_SLAB_OXIDIZED, "Oxidized Copper Plate Slab");
        addBlock(AllBlocks.COPPER_PLATE_SLAB_WAXED, "Waxed Slab of Copper Plates");
        addBlock(AllBlocks.COPPER_PLATE_SLAB_EXPOSED_WAXED, "Waxed Exposed Copper Plate Slab");
        addBlock(AllBlocks.COPPER_PLATE_SLAB_WEATHERED_WAXED, "Waxed Weathered Copper Plate Slab");
        addBlock(AllBlocks.COPPER_PLATE_SLAB_OXIDIZED_WAXED, "Waxed Oxidized Copper Plate Slab");
        addBlock(AllBlocks.COPPER_PLATE_STAIRS, "Stairs of Copper Plates");
        addBlock(AllBlocks.COPPER_PLATE_STAIRS_EXPOSED, "Exposed Copper Plate Stairs");
        addBlock(AllBlocks.COPPER_PLATE_STAIRS_WEATHERED, "Weathered Copper Plate Stairs");
        addBlock(AllBlocks.COPPER_PLATE_STAIRS_OXIDIZED, "Oxidized Copper Plate Stairs");
        addBlock(AllBlocks.COPPER_PLATE_STAIRS_WAXED, "Waxed Stairs of Copper Plates");
        addBlock(AllBlocks.COPPER_PLATE_STAIRS_EXPOSED_WAXED, "Waxed Exposed Copper Plate Stairs");
        addBlock(AllBlocks.COPPER_PLATE_STAIRS_WEATHERED_WAXED, "Waxed Weathered Copper Plate Stairs");
        addBlock(AllBlocks.COPPER_PLATE_STAIRS_OXIDIZED_WAXED, "Waxed Oxidized Copper Plate Stairs");
        addBlock(AllBlocks.FILTER, "Item Filter");
        addBlock(AllBlocks.FLUXSTONE, "Fluxstone");
        addBlock(AllBlocks.FLUXSTONE_SLAB, "Fluxstone Slab");
        addBlock(AllBlocks.FLUXSTONE_STAIRS, "Fluxstone Stairs");
        addBlock(AllBlocks.FLUXSTONE_WALL, "Fluxstone Wall");
        addBlock(AllBlocks.GOLD_PLATE_BLOCK, "Block of Gold Plates");
        addBlock(AllBlocks.GOLD_PLATE_SLAB, "Slab of Gold Plates");
        addBlock(AllBlocks.GOLD_PLATE_STAIRS, "Stairs of Gold Plates");
        addBlock(AllBlocks.HIGH_SPEED_ACTIVATOR_RAIL, "Bronze Activator Rail");
        addBlock(AllBlocks.HIGH_SPEED_BUFFER_STOP_RAIL, "Bronze Buffer Stop Rail");
        addBlock(AllBlocks.HIGH_SPEED_CHIME_RAIL, "Bronze Chime Rail");
        addBlock(AllBlocks.HIGH_SPEED_CROSSOVER_RAIL, "Bronze Crossover Rail");
        addBlock(AllBlocks.HIGH_SPEED_DETECTOR_RAIL, "Bronze Detector Rail");
        addBlock(AllBlocks.HIGH_SPEED_ONE_WAY_RAIL, "Bronze One-Way Rail");
        addBlock(AllBlocks.HIGH_SPEED_POWERED_RAIL, "Bronze Powered Rail");
        addBlock(AllBlocks.HIGH_SPEED_LIMITER_RAIL, "Bronze Limiter Rail");
        addBlock(AllBlocks.HIGH_SPEED_LOCKING_RAIL, "Bronze Locking Rail");
        addBlock(AllBlocks.HIGH_SPEED_RAIL, "Bronze Rail");
        addBlock(AllBlocks.IRON_LADDER, "Iron Ladder");
        addBlock(AllBlocks.IRON_PLATE_BLOCK, "Block of Iron Plates");
        addBlock(AllBlocks.IRON_PLATE_SLAB, "Slab of Iron Plates");
        addBlock(AllBlocks.IRON_PLATE_STAIRS, "Stairs of Iron Plates");
        addBlock(AllBlocks.IRON_SCAFFOLDING, "Iron Scaffold");
        addBlock(AllBlocks.ITEM_DUCT, "Item Duct");
        addBlock(AllBlocks.LINEN_BLOCK, "Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_WHITE, "White Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_ORANGE, "Orange Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_MAGENTA, "Magenta Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_LIGHT_BLUE, "Light Blue Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_YELLOW, "Yellow Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_LIME, "Lime Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_PINK, "Pink Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_GRAY, "Gray Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_LIGHT_GRAY, "Light Gray Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_CYAN, "Cyan Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_PURPLE, "Purple Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_BLUE, "Blue Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_BROWN, "Brown Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_GREEN, "Green Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_RED, "Red Block of Linen");
        addBlock(AllBlocks.LINEN_BLOCK_BLACK, "Black Block of Linen");
        addBlock(AllBlocks.LAUNCHING_RAIL, "Launching Rail");
        addBlock(AllBlocks.LIMITER_RAIL, "Limiter Rail");
        addBlock(AllBlocks.LOCKING_RAIL, "Locking Rail");
        addBlock(AllBlocks.MILLSTONE, "Millstone");
        addBlock(AllBlocks.ONE_WAY_RAIL, "One-Way Rail");
        addBlock(AllBlocks.POLISHED_FLUXSTONE, "Polished Fluxstone");
        addBlock(AllBlocks.POLISHED_FLUXSTONE_SLAB, "Polished Fluxstone Slab");
        addBlock(AllBlocks.POLISHED_FLUXSTONE_STAIRS, "Polished Fluxstone Stairs");
        addBlock(AllBlocks.POLISHED_FLUXSTONE_WALL, "Polished Fluxstone Wall");
        addBlock(AllBlocks.RAW_TIN_BLOCK, "Block of Raw Tin");
        addBlock(AllBlocks.ROLLING_MILL, "Rolling Mill");
        addBlock(AllBlocks.SACK, "Sack");
        addBlock(AllBlocks.SACK_WHITE, "White Sack");
        addBlock(AllBlocks.SACK_ORANGE, "Orange Sack");
        addBlock(AllBlocks.SACK_MAGENTA, "Magenta Sack");
        addBlock(AllBlocks.SACK_LIGHT_BLUE, "Light Blue Sack");
        addBlock(AllBlocks.SACK_YELLOW, "Yellow Sack");
        addBlock(AllBlocks.SACK_LIME, "Lime Sack");
        addBlock(AllBlocks.SACK_PINK, "Pink Sack");
        addBlock(AllBlocks.SACK_GRAY, "Gray Sack");
        addBlock(AllBlocks.SACK_LIGHT_GRAY, "Light Gray Sack");
        addBlock(AllBlocks.SACK_CYAN, "Cyan Sack");
        addBlock(AllBlocks.SACK_PURPLE, "Purple Sack");
        addBlock(AllBlocks.SACK_BLUE, "Blue Sack");
        addBlock(AllBlocks.SACK_BROWN, "Brown Sack");
        addBlock(AllBlocks.SACK_GREEN, "Green Sack");
        addBlock(AllBlocks.SACK_RED, "Red Sack");
        addBlock(AllBlocks.SACK_BLACK, "Black Sack");
        addBlock(AllBlocks.TIN_BLOCK, "Block of Tin");
        addBlock(AllBlocks.TIN_PLATE_BLOCK, "Block of Tin Plates");
        addBlock(AllBlocks.TIN_PLATE_SLAB, "Slab of Tin Plates");
        addBlock(AllBlocks.TIN_PLATE_STAIRS, "Stairs of Tin Plates");
        addBlock(AllBlocks.TRIP_HAMMER, "Trip Hammer");
        addBlock(AllBlocks.TIN_ORE, "Tin Ore");
        addBlock(AllBlocks.VERMILION_BLOCK, "Block of Vermilion");
        addBlock(AllBlocks.VERMILION_PRESSURE_PLATE, "Vermilion Pressure Plate");
        addBlock(AllBlocks.WATER_WHEEL, "Water Wheel");
        addBlock(AllBlocks.WIND_WHEEL, "Wind Wheel");
        addBlock(AllBlocks.WOODEN_FRAME, "Wooden Frame");
        addBlock(AllBlocks.WOODEN_FRAME_SLAB, "Wooden Frame Slab");
        addBlock(AllBlocks.WOODEN_FRAME_STAIRS, "Wooden Frame Stairs");

        addItem(AllItems.BOOSTER_ROD, "Booster Rod");
        addItem(AllItems.BRONZE_BOOSTER_ROD, "Bronze Booster Rod");
        addItem(AllItems.BRONZE_INGOT, "Bronze Ingot");
        addItem(AllItems.BRONZE_PLATE, "Bronze Plate");
        addItem(AllItems.BRONZE_ROD, "Bronze Rod");
        addItem(AllItems.BRONZE_MACE, "Bronze Mace");
        addItem(AllItems.COKE, "Coke");
        addItem(AllItems.COPPER_PLATE, "Copper Plate");
        addItem(AllItems.COPPER_ROD, "Copper Rod");
        addItem(AllItems.CRUSHED_BRONZE, "Crushed Bronze");
        addItem(AllItems.CRUSHED_COPPER_ORE, "Crushed Raw Copper");
        addItem(AllItems.CRUSHED_GOLD_ORE, "Crushed Raw Gold");
        addItem(AllItems.CRUSHED_IRON_ORE, "Crushed Raw Iron");
        addItem(AllItems.CRUSHED_TIN_ORE, "Crushed Raw Tin");
        addItem(AllItems.CRUSHED_VERMILION, "Crushed Vermilion");
        addItem(AllItems.FIRE_BRICK, "Fire Brick");
        addItem(AllItems.FLAX, "Flax");
        addItem(AllItems.FLAXSEED, "Flax Seeds");
        addItem(AllItems.FLAX_STRING, "Flax String");
        addItem(AllItems.FLOUR, "Flour");
        addItem(AllItems.FLUX, "Flux");
        addItem(AllItems.GEAR, "Gear");
        addItem(AllItems.GOLD_PLATE, "Gold Plate");
        addItem(AllItems.GOLD_ROD, "Gold Rod");
        addItem(AllItems.IRON_PLATE, "Iron Plate");
        addItem(AllItems.IRON_ROD, "Iron Rod");
        addItem(AllItems.IRON_ROLLS, "Iron Rolls");
        addItem(AllItems.IRON_SCREW, "Iron Screw");
        addItem(AllItems.ITEM_POUCH, "Item Pouch");
        addItem(AllItems.KAOLIN, "Kaolin");
        addItem(AllItems.LINEN, "Linen");
        addItem(AllItems.LINSEED_OIL, "Linseed Oil");
        addItem(AllItems.PADDED_BOOTS, "Padded Boots");
        addItem(AllItems.PADDED_CHESTPLATE, "Padded Chestplate");
        addItem(AllItems.PADDED_HELMET, "Padded Helmet");
        addItem(AllItems.PADDED_LEGGINGS, "Padded Leggings");
        addItem(AllItems.PROSPECTORS_PICK, "Prospector's Pick");
        addItem(AllItems.RAILROAD_TIE, "Railroad Tie");
        addItem(AllItems.RAW_TIN, "Raw Tin");
        addItem(AllItems.REDSTONE_EMITTER, "Redstone Emitter");
        addItem(AllItems.REDSTONE_VALVE, "Redstone Valve");
        addItem(AllItems.TIN_INGOT, "Tin Ingot");
        addItem(AllItems.TIN_PLATE, "Tin Plate");
        addItem(AllItems.TIN_ROD, "Tin Rod");
        addItem(AllItems.VERMILION_INGOT, "Vermilion Ingot");
        addItem(AllItems.VERMILION_PLATE, "Vermilion Plate");
        addItem(AllItems.VERMILION_ROD, "Vermilion Rod");
        addItem(AllItems.WIND_WHEEL_BLADE, "Wind Wheel Blade");
        addItem(AllItems.WIND_WHEEL_SAIL, "Wind Wheel Sail");

        addToolTip(TooltipKeys.ACTIVATOR_RAIL, "Activates minecarts when powered with redstone");
        addToolTip(TooltipKeys.BUFFER_STOP_RAIL, "Endcap that stops minecarts from rolling off the track");
        addToolTip(TooltipKeys.CANT_MOVE_ITEMS_UP, "Items cannot be transported upwards!");
        addToolTip(TooltipKeys.CHIME_RAIL, "Plays a sound when a minecart passes over");
        addToolTip(TooltipKeys.COKE_OVEN, "Processes coal into coke");
        addToolTip(TooltipKeys.CROSSOVER_RAIL1, "Allows two sets of tracks to cross over each other");
        addToolTip(TooltipKeys.CROSSOVER_RAIL2, "Minecarts traveling through will continue straight");
        addToolTip(TooltipKeys.DETECTOR_RAIL, "Produces a redstone signal when a minecart passes over");
        addToolTip(TooltipKeys.GEARBOX, "Transfers mechanical power to different different axes");
        addToolTip(TooltipKeys.IS_DYEABLE, "Dyeable");
        addToolTip(TooltipKeys.ITEM_DUCT, "Transports a single item in the direction the duct is facing");
        addToolTip(TooltipKeys.ITEM_FILTER, "Routes items in different directions depending upon which color the item has been sorted into");
        addToolTip(TooltipKeys.ITEM_POUCH, "Stores up to four stacks worth of mixed items");
        addToolTip(TooltipKeys.ITEM_POUCH_FULLNESS, "%s/%s");
        addToolTip(TooltipKeys.ITEM_POUCH_INSERT, "To put items inside, either (1) pick up the item pouch and right-click on the item(s), or (2) pick up the item(s) and right-click on the item pouch");
        addToolTip(TooltipKeys.ITEM_POUCH_REMOVE, "To take items out, either (1) pick up the item pouch and right-click on an empty slot, or (2) right-click on the item pouch");
        addToolTip(TooltipKeys.ITEM_POUCH_SLOT, "To select which item is taken out press " + "\u00A72" + "W (forward)" + "\u00A77" + " and " + "\u00A72" + "S (backwards)" + "\u00A77");
        addToolTip(TooltipKeys.LAUNCHING_RAIL, "Boosts minecarts by 200% when powered with redstone");
        addToolTip(TooltipKeys.LIMITER_RAIL1, "Limits the speed of minecarts when receiving a redstone signal");
        addToolTip(TooltipKeys.LIMITER_RAIL2, "Right click to change the speed limit");
        addToolTip(TooltipKeys.LOCKING_RAIL1, "Prevents minecarts from moving when not receiving a redstone signal");
        addToolTip(TooltipKeys.LOCKING_RAIL2, "When powered with redstone locked minecarts are boosted in the direction it was traveling in");
        addToolTip(TooltipKeys.MECHANICAL_POWER_SIDES, "Transfers mechanical power to the sides");
        addToolTip(TooltipKeys.MECHANICAL_POWER_STRAIGHT, "Transfers mechanical power in a straight line");
        addToolTip(TooltipKeys.MILLSTONE, "Processes materials by grinding them into powder");
        addToolTip(TooltipKeys.MINECARTS_MOVE_FASTER, "Minecarts will move 200% faster!");
        addToolTip(TooltipKeys.MORE_INFO_PRESS_SHIFT, "\u00A77" + "Press " + "\u00A72" + "SHIFT" + "\u00A77" + " for more information");
        addToolTip(TooltipKeys.ONE_WAY_RAIL1, "Acts like a one-way powered rail");
        addToolTip(TooltipKeys.ONE_WAY_RAIL2, "Minecarts traveling against the arrows will be reversed");
        addToolTip(TooltipKeys.POWERED_RAIL, "Boosts minecarts when powered with redstone");
        addToolTip(TooltipKeys.PROSPECTOR_PICK, "Pickaxe that prospects for nearby ores");
        addToolTip(TooltipKeys.PROSPECTOR_PICK_SCAN, "Right-click on a block to prospect for ores within %s blocks");
        addToolTip(TooltipKeys.PROSPECTOR_PICK_TRACE_AMOUNTS, "Found trace amounts of %s");
        addToolTip(TooltipKeys.PROSPECTOR_PICK_NOTHING, "Found nothing of value");
        addToolTip(TooltipKeys.REDUCES_FALL_DAMAGE, "Reduces fall damage when on Feet");
        addToolTip(TooltipKeys.ROLLING_MILL, "Processes materials by flatting and drawing them");
        addToolTip(TooltipKeys.SACK_IS_EMPTY, "empty...");
        addToolTip(TooltipKeys.SACK_OVERFLOW, "and %s more...");
        addToolTip(TooltipKeys.TRIP_HAMMER1, "Processes materials by pulverizing them into dust");
        addToolTip(TooltipKeys.TRIP_HAMMER2, "\u00A77" + "Needs a " + "\u00A72" + "1x1x4 area" + "\u00A77" + " of free space");
        addToolTip(TooltipKeys.WATER_WHEEL, "Creates mechanical power from flowing water");
        addToolTip(TooltipKeys.WIND_WHEEL1, "Creates mechanical power from the wind");
        addToolTip(TooltipKeys.WIND_WHEEL2, "\u00A77" + "Must be placed " + "\u00A72" + "Above Ground " + "\u00A77" + "and needs a " + "\u00A72" + "16x1x16 area" + "\u00A77" + " of free space");

        addMenu(AllBlocks.COKE_OVEN, "Coke Oven");
        addMenu(AllBlocks.FILTER, "Item Filter");
        addMenu(AllBlocks.MILLSTONE, "Millstone");
        addMenu(AllBlocks.ROLLING_MILL, "Rolling Mill");
        addMenu(AllBlocks.SACK, "Sack");
        addMenu(AllBlocks.TRIP_HAMMER, "Trip Hammer");

        addJEICategory(AllBlocks.COKE_OVEN, "Coking");
        addJEICategory(AllBlocks.MILLSTONE, "Milling");
        addJEICategory(AllBlocks.ROLLING_MILL, "Rolling");
        addJEICategory(AllBlocks.TRIP_HAMMER, "Stamping");
    }

    public void addMenu(Supplier<? extends Block> key, String name)
    {
        add("container." + NotEnoughMachines.MOD_ID + "." + AllBlocks.getName(key.get()), name);
    }

    public void addJEICategory(Supplier<? extends Block> key, String name)
    {
        add("jei.gui.category." + NotEnoughMachines.MOD_ID + "." + AllBlocks.getName(key.get()), name);
    }

    public void addCreativeTab(String key, String name)
    {
        add("itemGroup." + NotEnoughMachines.MOD_ID + "." + key, name);
    }

    public void addToolTip(TooltipKeys key, String text)
    {
        add(key.getTranslation(), text);
    }
}
