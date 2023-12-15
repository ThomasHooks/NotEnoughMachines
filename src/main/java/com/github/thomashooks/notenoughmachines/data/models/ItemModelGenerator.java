package com.github.thomashooks.notenoughmachines.data.models;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.item.AllItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemModelGenerator extends ItemModelProvider
{
    public ItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, NotEnoughMachines.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        simpleBlockItemModel(AllBlocks.AXLE);
        simpleItemModel(AllItems.BOOSTER_ROD);
        simpleItemModel(AllItems.BRONZE_INGOT);
        simpleItemModel(AllItems.BRONZE_PLATE);
        simpleItemModel(AllItems.BRONZE_ROD);
        simpleItemModel(AllItems.BRONZE_BOOSTER_ROD);
        simpleBlockItemModel(AllBlocks.COGWHEEL_LARGE);
        simpleBlockItemModel(AllBlocks.COGWHEEL_SMALL);
        simpleItemModel(AllItems.COKE);
        simpleBlockItemModel(AllBlocks.COKE_OVEN);
        simpleItemModel(AllItems.CONJUNCTIONER);
        simpleItemModel(AllItems.COPPER_PLATE);
        simpleBlockItemModel(AllBlocks.COPPER_PLATE_BLOCK);
        simpleItemModel(AllItems.COPPER_ROD);
        simpleItemModelFromBlockTexture(AllItems.CROSSOVER_RAIL);
        simpleItemModel(AllItems.CRUSHED_BRONZE);
        simpleItemModel(AllItems.CRUSHED_COPPER_ORE);
        simpleItemModel(AllItems.CRUSHED_GOLD_ORE);
        simpleItemModel(AllItems.CRUSHED_IRON_ORE);
        simpleItemModel(AllItems.CRUSHED_TIN_ORE);
        simpleItemModel(AllItems.FIRE_BRICK);
        simpleBlockItemModel(AllBlocks.FIRE_BRICKS_SLAB);
        simpleBlockItemModel(AllBlocks.FIRE_BRICKS_STAIRS);
        wallItemModel(AllBlocks.FIRE_BRICKS_WALL, AllBlocks.FIRE_BRICKS);
        simpleItemModel(AllItems.FILTER, "filter_item");
        simpleItemModel(AllItems.FLUX);
        simpleBlockItemModel(AllBlocks.FLUXSTONE_SLAB);
        simpleBlockItemModel(AllBlocks.FLUXSTONE_STAIRS);
        wallItemModel(AllBlocks.FLUXSTONE_WALL, AllBlocks.FLUXSTONE);
        simpleItemModel(AllItems.FLOUR);
        simpleItemModel(AllItems.FLAXSEED);
        simpleItemModel(AllItems.FLAX);
        simpleItemModel(AllItems.FLAX_STRING);
        simpleItemModel(AllItems.GEAR);
        simpleItemModel(AllItems.GOLD_PLATE);
        simpleItemModel(AllItems.GOLD_ROD);
        simpleItemModel(AllItems.HEAVY_BRONZE_STAMP);
        simpleItemModelFromBlockTexture(AllItems.HIGH_SPEED_ACTIVATOR_RAIL);
        simpleItemModelFromBlockTexture(AllItems.HIGH_SPEED_CROSSOVER_RAIL);
        simpleItemModelFromBlockTexture(AllItems.HIGH_SPEED_DETECTOR_RAIL);
        simpleItemModelFromBlockTexture(AllItems.HIGH_SPEED_ONE_WAY_RAIL);
        simpleItemModelFromBlockTexture(AllItems.HIGH_SPEED_POWERED_RAIL);
        simpleItemModelFromBlockTexture(AllItems.HIGH_SPEED_LOCKING_RAIL);
        simpleItemModelFromBlockTexture(AllItems.HIGH_SPEED_RAIL);
        simpleItemModelFromBlockTexture(AllItems.LOCKING_RAIL);
        simpleItemModel(AllItems.IRON_PLATE);
        simpleBlockItemModel(AllBlocks.IRON_PLATE_BLOCK);
        simpleItemModel(AllItems.IRON_ROD);
        simpleItemModel(AllItems.IRON_SCREW);
        simpleItemModel(AllItems.KAOLIN);
        simpleItemModel(AllItems.LINEN);
        simpleItemModel(AllItems.LINSEED_OIL);
        simpleItemModelFromBlockTexture(AllItems.ONE_WAY_RAIL);
        simpleBlockItemModel(AllBlocks.POLISHED_FLUXSTONE);
        simpleBlockItemModel(AllBlocks.POLISHED_FLUXSTONE_SLAB);
        simpleBlockItemModel(AllBlocks.POLISHED_FLUXSTONE_STAIRS);
        wallItemModel(AllBlocks.POLISHED_FLUXSTONE_WALL, AllBlocks.POLISHED_FLUXSTONE);
        simpleItemModel(AllItems.RAILROAD_TIE);
        simpleItemModel(AllItems.RAW_TIN);
        simpleItemModel(AllItems.REDSTONE_COLLECTOR);
        simpleItemModel(AllItems.REDSTONE_EMITTER);
        simpleItemModel(AllItems.ROLLERS);
        simpleItemModel(AllItems.TIN_INGOT);
        simpleItemModel(AllItems.TIN_PLATE);
        simpleItemModel(AllItems.TIN_ROD);
        simpleBlockItemModel(AllBlocks.WATER_WHEEL);
        simpleItemModel(AllItems.WIND_WHEEL);
        simpleItemModel(AllItems.WIND_WHEEL_BLADE);
        simpleItemModel(AllItems.WIND_WHEEL_SAIL);
    }

    public ItemModelBuilder simpleItemModel(RegistryObject<? extends Item> itemIn)
    {
        return withExistingParent(itemIn.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(NotEnoughMachines.MOD_ID, "item/" + itemIn.getId().getPath()));
    }

    public ItemModelBuilder simpleItemModel(RegistryObject<? extends Item> itemIn, String textureName)
    {
        return withExistingParent(itemIn.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(NotEnoughMachines.MOD_ID, "item/" + textureName));
    }

    public ItemModelBuilder simpleItemModelFromBlockTexture(RegistryObject<? extends Item> itemIn)
    {
        return withExistingParent(itemIn.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/" + itemIn.getId().getPath()));
    }

    public void wallItemModel(RegistryObject<? extends Block> blockIn, RegistryObject<? extends Block> baseBlock)
    {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", new ResourceLocation(NotEnoughMachines.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void simpleBlockItemModel(RegistryObject<? extends Block> blockIn)
    {
        this.withExistingParent(NotEnoughMachines.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath()));
    }
}
