package com.kilroy790.notenoughmachines.data;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.NEMBlocks;
import com.kilroy790.notenoughmachines.items.NEMItems;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;




public class LanguageDataGen extends LanguageProvider 
{
	private static final String CONTAINER_KEY_BASE = "container." + NotEnoughMachines.MODID + ".";
	private static final String JEI_CATEGORY_KEY_BASE = "jei.gui.category." + NotEnoughMachines.MODID + ".";
	
	public LanguageDataGen(DataGenerator gen, String modid, String locale) 
	{
		super(gen, modid, locale);
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
		addItem(NEMItems.COPPER_INGOT, "Copper Ingot");
		addItem(NEMItems.CRUSHED_COPPER_ORE, "Crushed Copper Ore");
		addItem(NEMItems.CRUSHED_GOLD_ORE, "Crushed Gold Ore");
		addItem(NEMItems.CRUSHED_IRON_ORE, "Crushed Iron Ore");
		addItem(NEMItems.FLAX, "Flax");
		addItem(NEMItems.FLAXSEED, "Flax Seeds");
		addItem(NEMItems.FLAXSTRING, "Flax String");
		addItem(NEMItems.FLOUR, "Flour");
		addItem(NEMItems.FLUX, "Flux");
		addItem(NEMItems.GEAR, "Gear");
		addItem(NEMItems.HAMMER_AND_ANVIL, "Hammer and Anvil");
		addItem(NEMItems.LINEN, "Linen");
		addItem(NEMItems.LINSEEDOIL, "Linseed Oil");
		addItem(NEMItems.REDSTONE_COLLECTOR, "Redstone Collector");
		addItem(NEMItems.REDSTONE_EMITTER, "Redstone Emitter");
		addItem(NEMItems.WINDBLADE, "Wind Wheel Blade");
		addItem(NEMItems.WINDSAIL_ITEM, "Wind Wheel Sail");
		
		add("itemGroup." + NotEnoughMachines.MODID, "Not Enough Machines");
	}
	
	
	
	private void blocks()
	{
		addBlock(NEMBlocks.ANDGATE, "AND Gate");
		addBlock(NEMBlocks.AXLE, "Axle");
		addBlock(NEMBlocks.CHUTE, "Chute");
		addBlock(NEMBlocks.COPPERORE, "Copper Ore");
		addBlock(NEMBlocks.CREATIVEPOWERBOX, "Creative Power Box");
		addBlock(NEMBlocks.CUT_PANLE, "Cut Panle");
		addBlock(NEMBlocks.ENCLOSED_AXLE, "Enclosed Axle");
		addBlock(NEMBlocks.FILTER, "Filter");
		addBlock(NEMBlocks.FLUXSTONE, "Fluxstone");
		addBlock(NEMBlocks.FLUXSTONE_BUTTON, "Fluxstone Button");
		addBlock(NEMBlocks.FLUXSTONE_PRESSURE_PLATE, "Fluxstone Pressure Plate");
		addBlock(NEMBlocks.FLUXSTONE_SLAB, "Fluxstone Slab");
		addBlock(NEMBlocks.FLUXSTONE_STAIRS, "Fluxstone Stairs");
		addBlock(NEMBlocks.FLUXSTONE_WALL, "Fluxstone Wall");
		addBlock(NEMBlocks.GEARBOX, "Gearbox");
		addBlock(NEMBlocks.ITEMPUSHER, "Pusher");
		addBlock(NEMBlocks.LARGECOG, "Large Cogwheel");
		addBlock(NEMBlocks.LINENBLOCK, "Block of Linen");
		addBlock(NEMBlocks.MILLSTONE, "Grist Mill");
		addBlock(NEMBlocks.ORGATE, "OR Gate");
		addBlock(NEMBlocks.POLISHED_FLUXSTONE, "Polished Fluxstone");
		addBlock(NEMBlocks.POLISHED_FLUXSTONE_BUTTON, "Polished Fluxstone Button");
		addBlock(NEMBlocks.POLISHED_FLUXSTONE_PRESSURE_PLATE, "Polished Fluxstone Pressure Plate");
		addBlock(NEMBlocks.POLISHED_FLUXSTONE_SLAB, "Polished Fluxstone Slab");
		addBlock(NEMBlocks.POLISHED_FLUXSTONE_STAIRS, "Polished Fluxstone Stairs");
		addBlock(NEMBlocks.POLISHED_FLUXSTONE_WALL, "Polished Fluxstone Wall");
		addBlock(NEMBlocks.SMALLCOG, "Small Cogwheel");
		addBlock(NEMBlocks.SMALLWINDWHEEL, "Wind Wheel");
		addBlock(NEMBlocks.TRIPHAMMER, "Stamp Mill");
		addBlock(NEMBlocks.TUBWHEEL, "Tub Wheel");
	}
	
	
	
	private void containers()
	{
		addContainerNEM("filter", "Filter");
		addContainerNEM("filter2", "Buffer");
		addContainerNEM("millstone", "Grist Mill");
		addContainerNEM("triphammer", "Stamp Mill");
		
		addCategoryNEM("stamping", "Stamping");
		addCategoryNEM("milling", "Milling");
	}
	
	
	
	private void addContainerNEM(String key, String name)
	{
		add(CONTAINER_KEY_BASE + key, name);
	}
	
	
	
	private void addCategoryNEM(String key, String name)
	{
		add(JEI_CATEGORY_KEY_BASE + key, name);
	}
}



