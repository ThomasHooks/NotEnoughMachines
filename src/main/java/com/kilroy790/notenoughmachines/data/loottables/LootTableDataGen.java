package com.kilroy790.notenoughmachines.data.loottables;

import com.kilroy790.notenoughmachines.blocks.NEMBlocks;

import net.minecraft.data.DataGenerator;




public class LootTableDataGen extends BaseLootTableProvider 
{
	public LootTableDataGen(DataGenerator dataGeneratorIn) 
	{
		super(dataGeneratorIn);
	}
	
	
	
	@Override
	protected void addTables() 
	{
		lootTables.put(NEMBlocks.ANDGATE.get(), createBasicTable("andgate", NEMBlocks.ANDGATE.get()));
		lootTables.put(NEMBlocks.AXLE.get(), createBasicTable("axle", NEMBlocks.AXLE.get()));
		lootTables.put(NEMBlocks.CHUTE.get(), createBasicTable("chute", NEMBlocks.CHUTE.get()));
		lootTables.put(NEMBlocks.COPPERORE.get(), createBasicTable("copper_ore", NEMBlocks.COPPERORE.get()));
		lootTables.put(NEMBlocks.CUT_PANLE.get(), createBasicTable("cut_panle", NEMBlocks.CUT_PANLE.get()));
		lootTables.put(NEMBlocks.ENCLOSED_AXLE.get(), createBasicTable("enclosedaxle", NEMBlocks.ENCLOSED_AXLE.get()));
		lootTables.put(NEMBlocks.FILTER.get(), createBasicTable("filter", NEMBlocks.FILTER.get()));
		lootTables.put(NEMBlocks.FLUXSTONE.get(), createBasicTable("fluxstone", NEMBlocks.FLUXSTONE.get()));
		lootTables.put(NEMBlocks.FLUXSTONE_BUTTON.get(), createBasicTable("fluxstone_button", NEMBlocks.FLUXSTONE_BUTTON.get()));
		lootTables.put(NEMBlocks.FLUXSTONE_PRESSURE_PLATE.get(), createBasicTable("fluxstone_pressure_plate", NEMBlocks.FLUXSTONE_PRESSURE_PLATE.get()));
		lootTables.put(NEMBlocks.FLUXSTONE_STAIRS.get(), createBasicTable("fluxstone_stairs", NEMBlocks.FLUXSTONE_STAIRS.get()));
		lootTables.put(NEMBlocks.FLUXSTONE_WALL.get(), createBasicTable("fluxstone_wall", NEMBlocks.FLUXSTONE_WALL.get()));
		lootTables.put(NEMBlocks.GEARBOX.get(), createBasicTable("gearbox", NEMBlocks.GEARBOX.get()));
		lootTables.put(NEMBlocks.ITEMPUSHER.get(), createBasicTable("itempusher", NEMBlocks.ITEMPUSHER.get()));
		lootTables.put(NEMBlocks.LINENBLOCK.get(), createBasicTable("linenblock", NEMBlocks.LINENBLOCK.get()));
		lootTables.put(NEMBlocks.MILLSTONE.get(), createBasicTable("millstone", NEMBlocks.MILLSTONE.get()));
		lootTables.put(NEMBlocks.ORGATE.get(), createBasicTable("orgate", NEMBlocks.ORGATE.get()));
		lootTables.put(NEMBlocks.POLISHED_FLUXSTONE.get(), createBasicTable("polished_fluxstone", NEMBlocks.POLISHED_FLUXSTONE.get()));
		lootTables.put(NEMBlocks.POLISHED_FLUXSTONE_BUTTON.get(), createBasicTable("polished_fluxstone_button", NEMBlocks.POLISHED_FLUXSTONE_BUTTON.get()));
		lootTables.put(NEMBlocks.POLISHED_FLUXSTONE_PRESSURE_PLATE.get(), createBasicTable("polished_fluxstone_pressure_plate", NEMBlocks.POLISHED_FLUXSTONE_PRESSURE_PLATE.get()));
		lootTables.put(NEMBlocks.POLISHED_FLUXSTONE_STAIRS.get(), createBasicTable("polished_fluxstone_stairs", NEMBlocks.POLISHED_FLUXSTONE_STAIRS.get()));
		lootTables.put(NEMBlocks.POLISHED_FLUXSTONE_WALL.get(), createBasicTable("polished_fluxstone_wall", NEMBlocks.POLISHED_FLUXSTONE_WALL.get()));
		lootTables.put(NEMBlocks.SMALLCOG.get(), createBasicTable("smallcog", NEMBlocks.SMALLCOG.get()));
		lootTables.put(NEMBlocks.SMALLWINDWHEEL.get(), createBasicTable("smallwindwheel", NEMBlocks.SMALLWINDWHEEL.get()));
		lootTables.put(NEMBlocks.TUBWHEEL.get(), createBasicTable("tubwheel", NEMBlocks.TUBWHEEL.get()));
		lootTables.put(NEMBlocks.WOODEN_FRAME.get(), createBasicTable("wooden_frame", NEMBlocks.WOODEN_FRAME.get()));
	}
}



