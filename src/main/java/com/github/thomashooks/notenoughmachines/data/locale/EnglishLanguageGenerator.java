package com.github.thomashooks.notenoughmachines.data.locale;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

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
    }

    private void addAllItems()
    {
        addItem(AllItems.COPPER_PLATE, "Copper Plate");
        addItem(AllItems.COPPER_ROD, "Copper Rod");
        addItem(AllItems.CRUSHED_COPPER_ORE, "Crushed Raw Copper");
        addItem(AllItems.CRUSHED_GOLD_ORE, "Crushed Raw Gold");
        addItem(AllItems.CRUSHED_IRON_ORE, "Crushed Raw Iron");
        addItem(AllItems.IRON_PLATE, "Iron Plate");
        addItem(AllItems.IRON_ROD, "Iron Rod");
        addItem(AllItems.IRON_SCREW, "Iron Screw");
        addItem(AllItems.FLAX, "Flax");
        addItem(AllItems.FLAXSEED, "Flax Seeds");
        addItem(AllItems.FLAX_STRING, "Flax String");
        addItem(AllItems.FLOUR, "Flour");
        addItem(AllItems.FLUX, "Flux");
        addItem(AllItems.GEAR, "Gear");
        addItem(AllItems.HAMMER_AND_ANVIL, "Hammer and Anvil");
        addItem(AllItems.ROLLERS, "Rollers");
        addItem(AllItems.LINEN, "Linen");
        addItem(AllItems.LINSEED_OIL, "Linseed Oil");
        addItem(AllItems.REDSTONE_COLLECTOR, "Redstone Collector");
        addItem(AllItems.REDSTONE_EMITTER, "Redstone Emitter");
        addItem(AllItems.WIND_WHEEL_BLADE, "Wind Wheel Blade");
        addItem(AllItems.WIND_WHEEL_SAIL, "Wind Wheel Sail");

        addItemGroup("nem_tab", "Not Enough Machines");
    }

    private void addAllBlocks()
    {
        addBlock(AllBlocks.AXLE, "Axle");
        addBlock(AllBlocks.CONJUNCTIONER, "Redstone Conjunctioner");
        addBlock(AllBlocks.COPPER_PLATE_BLOCK, "Block of Copper Plates");
        addBlock(AllBlocks.FILTER, "Item Filter");
        addBlock(AllBlocks.FLUXSTONE, "Fluxstone");
        addBlock(AllBlocks.FLUXSTONE_SLAB, "Fluxstone Slab");
        addBlock(AllBlocks.FLUXSTONE_STAIRS, "Fluxstone Stairs");
        addBlock(AllBlocks.FLUXSTONE_WALL, "Fluxstone Wall");
        addBlock(AllBlocks.IRON_PLATE_BLOCK, "Block of Iron Plates");
        addBlock(AllBlocks.LINEN_BLOCK, "Block of Linen");
        addBlock(AllBlocks.POLISHED_FLUXSTONE, "Polished Fluxstone");
        addBlock(AllBlocks.POLISHED_FLUXSTONE_SLAB, "Polished Fluxstone Slab");
        addBlock(AllBlocks.POLISHED_FLUXSTONE_STAIRS, "Polished Fluxstone Stairs");
        addBlock(AllBlocks.POLISHED_FLUXSTONE_WALL, "Polished Fluxstone Wall");
        addBlock(AllBlocks.WOODEN_FRAME, "Wooden Frame");
    }

    private void addAllMenus()
    {
        addMenu("filter", "Item Filter");
    }

    public void addMenu(String key, String name)
    {
        add("container." + NotEnoughMachines.MOD_ID + "." + key, name);
    }

    public void addJEICategory(String key, String name)
    {
        add("jei.gui.category." + NotEnoughMachines.MOD_ID + "." + key, name);
    }

    public void addItemGroup(String key, String name)
    {
        add("itemGroup." + NotEnoughMachines.MOD_ID + "." + key, name);
    }
}
