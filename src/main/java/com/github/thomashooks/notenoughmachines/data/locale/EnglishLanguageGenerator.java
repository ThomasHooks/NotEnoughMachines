package com.github.thomashooks.notenoughmachines.data.locale;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.checkerframework.checker.units.qual.A;

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
        addAllItems();
        addAllBlocks();
        addAllMenus();
        addAllJEICategories();
    }

    private void addAllItems()
    {
        addItem(AllItems.BOOSTER_ROD, "Booster Rod");
        addItem(AllItems.BRONZE_BOOSTER_ROD, "Bronze Booster Rod");
        addItem(AllItems.BRONZE_INGOT, "Bronze Ingot");
        addItem(AllItems.BRONZE_PLATE, "Bronze Plate");
        addItem(AllItems.BRONZE_ROD, "Bronze Rod");
        addItem(AllItems.COKE, "Coke");
        addItem(AllItems.COPPER_PLATE, "Copper Plate");
        addItem(AllItems.COPPER_ROD, "Copper Rod");
        addItem(AllItems.CRUSHED_BRONZE, "Crushed Bronze");
        addItem(AllItems.CRUSHED_COPPER_ORE, "Crushed Raw Copper");
        addItem(AllItems.CRUSHED_GOLD_ORE, "Crushed Raw Gold");
        addItem(AllItems.CRUSHED_IRON_ORE, "Crushed Raw Iron");
        addItem(AllItems.CRUSHED_TIN_ORE, "Crushed Raw Tin");
        addItem(AllItems.FIRE_BRICK, "Fire Brick");
        addItem(AllItems.FLAX, "Flax");
        addItem(AllItems.FLAXSEED, "Flax Seeds");
        addItem(AllItems.FLAX_STRING, "Flax String");
        addItem(AllItems.FLOUR, "Flour");
        addItem(AllItems.FLUX, "Flux");
        addItem(AllItems.GEAR, "Gear");
        addItem(AllItems.GOLD_PLATE, "Gold Plate");
        addItem(AllItems.GOLD_ROD, "Gold Rod");
        addItem(AllItems.HEAVY_BRONZE_STAMP, "Heavy Bronze Stamp");
        addItem(AllItems.IRON_PLATE, "Iron Plate");
        addItem(AllItems.IRON_ROD, "Iron Rod");
        addItem(AllItems.IRON_SCREW, "Iron Screw");
        addItem(AllItems.KAOLIN, "Kaolin");
        addItem(AllItems.LINEN, "Linen");
        addItem(AllItems.LINSEED_OIL, "Linseed Oil");
        addItem(AllItems.RAILROAD_TIE, "Railroad Tie");
        addItem(AllItems.RAW_TIN, "Raw Tin");
        addItem(AllItems.REDSTONE_COLLECTOR, "Redstone Collector");
        addItem(AllItems.REDSTONE_EMITTER, "Redstone Emitter");
        addItem(AllItems.ROLLERS, "Rollers");
        addItem(AllItems.TIN_INGOT, "Tin Ingot");
        addItem(AllItems.TIN_PLATE, "Tin Plate");
        addItem(AllItems.TIN_ROD, "Tin Rod");
        addItem(AllItems.WIND_WHEEL_BLADE, "Wind Wheel Blade");
        addItem(AllItems.WIND_WHEEL_SAIL, "Wind Wheel Sail");

        addItemGroup("nem_tab", "Not Enough Machines");
    }

    private void addAllBlocks()
    {
        addBlock(AllBlocks.AXLE, "Axle");
        addBlock(AllBlocks.BRONZE_BLOCK, "Block of Bronze");
        addBlock(AllBlocks.COGWHEEL_LARGE, "Large Cogwheel");
        addBlock(AllBlocks.COGWHEEL_SMALL, "Small Cogwheel");
        addBlock(AllBlocks.COKE_BLOCK, "Block of Coke");
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
        addBlock(AllBlocks.FILTER, "Item Filter");
        addBlock(AllBlocks.FLUXSTONE, "Fluxstone");
        addBlock(AllBlocks.FLUXSTONE_SLAB, "Fluxstone Slab");
        addBlock(AllBlocks.FLUXSTONE_STAIRS, "Fluxstone Stairs");
        addBlock(AllBlocks.FLUXSTONE_WALL, "Fluxstone Wall");
        addBlock(AllBlocks.HIGH_SPEED_POWERED_RAIL, "High-Speed Powered Rail");
        addBlock(AllBlocks.HIGH_SPEED_RAIL, "High-Speed Rail");
        addBlock(AllBlocks.IRON_PLATE_BLOCK, "Block of Iron Plates");
        addBlock(AllBlocks.LINEN_BLOCK, "Block of Linen");
        addBlock(AllBlocks.MILLSTONE, "Millstone");
        addBlock(AllBlocks.ONE_WAY_RAIL, "One-Way Rail");
        addBlock(AllBlocks.POLISHED_FLUXSTONE, "Polished Fluxstone");
        addBlock(AllBlocks.POLISHED_FLUXSTONE_SLAB, "Polished Fluxstone Slab");
        addBlock(AllBlocks.POLISHED_FLUXSTONE_STAIRS, "Polished Fluxstone Stairs");
        addBlock(AllBlocks.POLISHED_FLUXSTONE_WALL, "Polished Fluxstone Wall");
        addBlock(AllBlocks.ROLLING_MILL, "Rolling Mill");
        addBlock(AllBlocks.TIN_BLOCK, "Block of Tin");
        addBlock(AllBlocks.TRIP_HAMMER, "Trip Hammer");
        addBlock(AllBlocks.TIN_ORE, "Tin Ore");
        addBlock(AllBlocks.WATER_WHEEL, "Water Wheel");
        addBlock(AllBlocks.WIND_WHEEL, "Wind Wheel");
        addBlock(AllBlocks.WOODEN_FRAME, "Wooden Frame");
    }

    private void addAllMenus()
    {
        addMenu(AllBlocks.COKE_OVEN, "Coke Oven");
        addMenu(AllBlocks.FILTER, "Item Filter");
        addMenu(AllBlocks.MILLSTONE, "Millstone");
        addMenu(AllBlocks.ROLLING_MILL, "Rolling Mill");
        addMenu(AllBlocks.TRIP_HAMMER, "Trip Hammer");
    }

    private void addAllJEICategories()
    {
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

    public void addItemGroup(String key, String name)
    {
        add("itemGroup." + NotEnoughMachines.MOD_ID + "." + key, name);
    }
}
