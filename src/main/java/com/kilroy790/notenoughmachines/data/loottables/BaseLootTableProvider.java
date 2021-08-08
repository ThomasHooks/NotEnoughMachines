package com.kilroy790.notenoughmachines.data.loottables;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kilroy790.notenoughmachines.NotEnoughMachines;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.SurvivesExplosion;
import net.minecraft.loot.functions.CopyName;
import net.minecraft.loot.functions.CopyNbt;
import net.minecraft.loot.functions.SetContents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;




//By McJTY
public abstract class BaseLootTableProvider extends LootTableProvider
{
	private static final Logger LOGGER = LogManager.getLogger();
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

	protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
	private final DataGenerator generator;

	public BaseLootTableProvider(DataGenerator dataGeneratorIn)
	{
		super(dataGeneratorIn);
		this.generator = dataGeneratorIn;
	}



	protected abstract void addTables();



	protected LootTable.Builder createInventoryTable(String name, Block block)
	{
		LootPool.Builder builder = LootPool.builder()
				.name(name)
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(block)
					.acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
					.acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY).addOperation(NotEnoughMachines.MODID + ":inventory", "BlockEntityTag."  + NotEnoughMachines.MODID + ":inventory", CopyNbt.Action.REPLACE))
					.acceptFunction(SetContents.builderIn().addLootEntry(DynamicLootEntry.func_216162_a(new ResourceLocation("minecraft", "contents")))));
		return LootTable.builder().addLootPool(builder);
	}



	protected LootTable.Builder createBasicTable(String name, Block lootDrop)
	{
		LootPool.Builder builder = LootPool.builder()
				.name(name)
				.rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(lootDrop))
				.acceptCondition(SurvivesExplosion.builder());
		return LootTable.builder().addLootPool(builder);
	}



	@Override
	public void act(DirectoryCache cache)
	{
		addTables();

		Map<ResourceLocation, LootTable> tables = new HashMap<>();
		for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet())
		{
			tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
		}
		writeTables(cache, tables);
	}
	
	
	
	private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables)
	{
		Path outputFolder = this.generator.getOutputFolder();
		tables.forEach((key, lootTable) ->
		{
			Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
			try
			{
				IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
			} 
			catch (IOException e)
			{
				LOGGER.error("Couldn't write loot table {}", path, e);
			}
		});
	}
	
	
	
	@Override
	public String getName()
	{
		return NotEnoughMachines.MODID + " LootTables";
	}
}



