package com.github.thomashooks.notenoughmachines.data.locale;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.NEMBlocks;
import com.github.thomashooks.notenoughmachines.world.item.NEMItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class LanguageGenerator extends LanguageProvider
{
    protected final String locale;

    public LanguageGenerator(PackOutput output, String locale)
    {
        super(output, NotEnoughMachines.MOD_ID, locale);
        this.locale = locale;
    }

    @Override
    protected void addTranslations()
    {
        this.items();
        this.blocks();
        this.containers();
    }

    private void items()
    {
        addItem(NEMItems.COPPER_PLATE, "Copper Plate");
        addItem(NEMItems.COPPER_ROD, "Copper Rod");
        addItem(NEMItems.CRUSHED_COPPER_ORE, "Crushed Raw Copper");
        addItem(NEMItems.CRUSHED_GOLD_ORE, "Crushed Raw Gold");
        addItem(NEMItems.CRUSHED_IRON_ORE, "Crushed Raw Iron");
        addItem(NEMItems.IRON_PLATE, "Iron Plate");
        addItem(NEMItems.IRON_ROD, "Iron Rod");
        addItem(NEMItems.IRON_SCREW, "Iron Screw");
        addItem(NEMItems.FLAX, "Flax");
        addItem(NEMItems.FLAXSEED, "Flax Seeds");
        addItem(NEMItems.FLAX_STRING, "Flax String");
        addItem(NEMItems.FLOUR, "Flour");
        addItem(NEMItems.FLUX, "Flux");
        addItem(NEMItems.GEAR, "Gear");
        addItem(NEMItems.HAMMER_AND_ANVIL, "Hammer and Anvil");
        addItem(NEMItems.ROLLERS, "Rollers");
        addItem(NEMItems.LINEN, "Linen");
        addItem(NEMItems.LINSEED_OIL, "Linseed Oil");
        addItem(NEMItems.REDSTONE_COLLECTOR, "Redstone Collector");
        addItem(NEMItems.REDSTONE_EMITTER, "Redstone Emitter");
        addItem(NEMItems.WIND_WHEEL_BLADE, "Wind Wheel Blade");
        addItem(NEMItems.WIND_WHEEL_SAIL, "Wind Wheel Sail");

        addItemGroup("nem_tab", "Not Enough Machines");
    }

    private void blocks()
    {
        addBlock(NEMBlocks.CONJUNCTIONER, "Redstone Conjunctioner");
        addBlock(NEMBlocks.COPPER_PLATE_BLOCK, "Block of Copper Plates");
        addBlock(NEMBlocks.FLUXSTONE, "Fluxstone");
        addBlock(NEMBlocks.FLUXSTONE_SLAB, "Fluxstone Slab");
        addBlock(NEMBlocks.FLUXSTONE_STAIRS, "Fluxstone Stairs");
        addBlock(NEMBlocks.FLUXSTONE_WALL, "Fluxstone Wall");
        addBlock(NEMBlocks.IRON_PLATE_BLOCK, "Block of Iron Plates");
        addBlock(NEMBlocks.LINEN_BLOCK, "Block of Linen");
        addBlock(NEMBlocks.POLISHED_FLUXSTONE, "Polished Fluxstone");
        addBlock(NEMBlocks.POLISHED_FLUXSTONE_SLAB, "Polished Fluxstone Slab");
        addBlock(NEMBlocks.POLISHED_FLUXSTONE_STAIRS, "Polished Fluxstone Stairs");
        addBlock(NEMBlocks.POLISHED_FLUXSTONE_WALL, "Polished Fluxstone Wall");
        addBlock(NEMBlocks.WOODEN_FRAME, "Wooden Frame");
    }

    private void containers()
    {

    }

    public void addContainer(String key, String name)
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
