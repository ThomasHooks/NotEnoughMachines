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
        createSimpleBlockItemModel(AllBlocks.AXLE);
        createSimpleItemModel(AllItems.BOOSTER_ROD);
        createSimpleItemModel(AllItems.BRONZE_BOOSTER_ROD);
        createSimpleItemModel(AllItems.BRONZE_INGOT);
        createSimpleFlatItemModel(AllItems.BRONZE_LADDER);
        createSimpleItemModel(AllItems.BRONZE_PLATE);
        createSimpleBlockItemModel(AllBlocks.BRONZE_PLATE_BLOCK);
        createSimpleBlockItemModel(AllBlocks.BRONZE_PLATE_SLAB);
        createSimpleBlockItemModel(AllBlocks.BRONZE_PLATE_STAIRS);
        createSimpleItemModel(AllItems.BRONZE_ROD);
        createSimpleBlockItemModel(AllBlocks.BRONZE_SCAFFOLDING);
        createHandheldItemModel(AllItems.BRONZE_MACE);
        createSimpleItemModel(AllItems.BUFFER_STOP_RAIL);
        createSimpleFlatItemModel(AllItems.CHIME_RAIL);
        createSimpleBlockItemModel(AllBlocks.COGWHEEL_LARGE);
        createSimpleBlockItemModel(AllBlocks.COGWHEEL_SMALL);
        createSimpleItemModel(AllItems.COKE);
        createSimpleBlockItemModel(AllBlocks.COKE_OVEN);
        createSimpleItemModel(AllItems.CONJUNCTIONER);
        createSimpleItemModel(AllItems.COPPER_PLATE);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_BLOCK);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_BLOCK_EXPOSED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_BLOCK_WEATHERED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_BLOCK_OXIDIZED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_BLOCK_WAXED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_BLOCK_EXPOSED_WAXED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_BLOCK_WEATHERED_WAXED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_BLOCK_OXIDIZED_WAXED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_SLAB);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_SLAB_EXPOSED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_SLAB_WEATHERED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_SLAB_OXIDIZED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_SLAB_WAXED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_SLAB_EXPOSED_WAXED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_SLAB_WEATHERED_WAXED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_SLAB_OXIDIZED_WAXED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_STAIRS);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_STAIRS_EXPOSED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_STAIRS_WEATHERED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_STAIRS_OXIDIZED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_STAIRS_WAXED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_STAIRS_EXPOSED_WAXED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_STAIRS_WEATHERED_WAXED);
        createSimpleBlockItemModel(AllBlocks.COPPER_PLATE_STAIRS_OXIDIZED_WAXED);
        createSimpleItemModel(AllItems.COPPER_ROD);
        createSimpleFlatItemModel(AllItems.CROSSOVER_RAIL);
        createSimpleItemModel(AllItems.CRUSHED_BRONZE);
        createSimpleItemModel(AllItems.CRUSHED_COPPER_ORE);
        createSimpleItemModel(AllItems.CRUSHED_GOLD_ORE);
        createSimpleItemModel(AllItems.CRUSHED_IRON_ORE);
        createSimpleItemModel(AllItems.CRUSHED_TIN_ORE);
        createSimpleItemModel(AllItems.CRUSHED_VERMILION);
        createSimpleItemModel(AllItems.FIRE_BRICK);
        createSimpleBlockItemModel(AllBlocks.FIRE_BRICKS_SLAB);
        createSimpleBlockItemModel(AllBlocks.FIRE_BRICKS_STAIRS);
        createWallItemModel(AllBlocks.FIRE_BRICKS_WALL, AllBlocks.FIRE_BRICKS);
        createSimpleItemModel(AllItems.FILTER, "filter_item");
        createSimpleItemModel(AllItems.FLUX);
        createSimpleBlockItemModel(AllBlocks.FLUXSTONE_SLAB);
        createSimpleBlockItemModel(AllBlocks.FLUXSTONE_STAIRS);
        createWallItemModel(AllBlocks.FLUXSTONE_WALL, AllBlocks.FLUXSTONE);
        createSimpleItemModel(AllItems.FLOUR);
        createSimpleItemModel(AllItems.FLAXSEED);
        createSimpleItemModel(AllItems.FLAX);
        createSimpleItemModel(AllItems.FLAX_STRING);
        createSimpleItemModel(AllItems.GEAR);
        createSimpleItemModel(AllItems.GOLD_PLATE);
        createSimpleBlockItemModel(AllBlocks.GOLD_PLATE_BLOCK);
        createSimpleBlockItemModel(AllBlocks.GOLD_PLATE_SLAB);
        createSimpleBlockItemModel(AllBlocks.GOLD_PLATE_STAIRS);
        createSimpleItemModel(AllItems.GOLD_ROD);
        createSimpleFlatItemModel(AllItems.HIGH_SPEED_ACTIVATOR_RAIL);
        createSimpleItemModel(AllItems.HIGH_SPEED_BUFFER_STOP_RAIL);
        createSimpleFlatItemModel(AllItems.HIGH_SPEED_CHIME_RAIL);
        createSimpleFlatItemModel(AllItems.HIGH_SPEED_CROSSOVER_RAIL);
        createSimpleFlatItemModel(AllItems.HIGH_SPEED_DETECTOR_RAIL);
        createSimpleFlatItemModel(AllItems.HIGH_SPEED_ONE_WAY_RAIL);
        createSimpleFlatItemModel(AllItems.HIGH_SPEED_POWERED_RAIL);
        createSimpleFlatItemModel(AllItems.HIGH_SPEED_LIMITER_RAIL);
        createSimpleFlatItemModel(AllItems.HIGH_SPEED_LOCKING_RAIL);
        createSimpleFlatItemModel(AllItems.HIGH_SPEED_RAIL);
        createSimpleFlatItemModel(AllItems.LIMITER_RAIL);
        createSimpleFlatItemModel(AllItems.LOCKING_RAIL);
        createSimpleFlatItemModel(AllItems.IRON_LADDER);
        createSimpleItemModel(AllItems.IRON_PLATE);
        createSimpleBlockItemModel(AllBlocks.IRON_PLATE_BLOCK);
        createSimpleBlockItemModel(AllBlocks.IRON_PLATE_SLAB);
        createSimpleBlockItemModel(AllBlocks.IRON_PLATE_STAIRS);
        createSimpleBlockItemModel(AllBlocks.IRON_SCAFFOLDING);
        createSimpleItemModel(AllItems.IRON_ROD);
        createSimpleItemModel(AllItems.IRON_SCREW);
        createSimpleItemModel(AllItems.ITEM_POUCH, "item_pouch", "item_pouch_overlay");
        createSimpleItemModel(AllItems.KAOLIN);
        createSimpleItemModel(AllItems.LINEN);
        createSimpleItemModel(AllItems.LINSEED_OIL);
        createSimpleFlatItemModel(AllItems.ONE_WAY_RAIL);
        createSimpleItemModel(AllItems.PADDED_BOOTS);
        createSimpleItemModel(AllItems.PADDED_CHESTPLATE);
        createSimpleItemModel(AllItems.PADDED_HELMET, "padded_helmet", "padded_helmet_overlay");
        createSimpleItemModel(AllItems.PADDED_LEGGINGS, "padded_leggings", "padded_leggings_overlay");
        createSimpleBlockItemModel(AllBlocks.POLISHED_FLUXSTONE);
        createSimpleBlockItemModel(AllBlocks.POLISHED_FLUXSTONE_SLAB);
        createSimpleBlockItemModel(AllBlocks.POLISHED_FLUXSTONE_STAIRS);
        createWallItemModel(AllBlocks.POLISHED_FLUXSTONE_WALL, AllBlocks.POLISHED_FLUXSTONE);
        createHandheldItemModel(AllItems.PROSPECTORS_PICK);
        createSimpleItemModel(AllItems.RAILROAD_TIE);
        createSimpleItemModel(AllItems.RAW_TIN);
        createSimpleItemModel(AllItems.REDSTONE_EMITTER);
        createSimpleItemModel(AllItems.REDSTONE_VALVE);
        createSimpleItemModel(AllItems.IRON_ROLLS);
        createSimpleItemModel(AllItems.TIN_INGOT);
        createSimpleItemModel(AllItems.TIN_PLATE);
        createSimpleBlockItemModel(AllBlocks.TIN_PLATE_BLOCK);
        createSimpleBlockItemModel(AllBlocks.TIN_PLATE_SLAB);
        createSimpleBlockItemModel(AllBlocks.TIN_PLATE_STAIRS);
        createSimpleItemModel(AllItems.TIN_ROD);
        createSimpleItemModel(AllItems.VERMILION_INGOT);
        createSimpleItemModel(AllItems.VERMILION_PLATE);
        createSimpleBlockItemModel(AllBlocks.VERMILION_PRESSURE_PLATE);
        createSimpleItemModel(AllItems.VERMILION_ROD);
        createSimpleBlockItemModel(AllBlocks.WATER_WHEEL);
        createSimpleItemModel(AllItems.WIND_WHEEL);
        createSimpleItemModel(AllItems.WIND_WHEEL_BLADE);
        createSimpleItemModel(AllItems.WIND_WHEEL_SAIL);
        createSimpleBlockItemModel(AllBlocks.WOODEN_FRAME_SLAB);
        createSimpleBlockItemModel(AllBlocks.WOODEN_FRAME_STAIRS);
    }

    public ItemModelBuilder createSimpleItemModel(RegistryObject<? extends Item> itemIn)
    {
        return withExistingParent(itemIn.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(NotEnoughMachines.MOD_ID, "item/" + itemIn.getId().getPath()));
    }

    public ItemModelBuilder createSimpleItemModel(RegistryObject<? extends Item> itemIn, String layer0TextureName)
    {
        return withExistingParent(itemIn.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(NotEnoughMachines.MOD_ID, "item/" + layer0TextureName));
    }

    public ItemModelBuilder createSimpleItemModel(RegistryObject<? extends Item> itemIn, String layer0TextureName, String layer1TextureName)
    {
        return withExistingParent(itemIn.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(NotEnoughMachines.MOD_ID, "item/" + layer0TextureName))
                .texture("layer1", new ResourceLocation(NotEnoughMachines.MOD_ID, "item/" + layer1TextureName));
    }

    public ItemModelBuilder createHandheldItemModel(RegistryObject<? extends Item> itemIn)
    {
        return withExistingParent(itemIn.getId().getPath(), new ResourceLocation("item/handheld"))
                .texture("layer0", new ResourceLocation(NotEnoughMachines.MOD_ID, "item/" + itemIn.getId().getPath()));
    }

    public ItemModelBuilder createSimpleFlatItemModel(RegistryObject<? extends Item> itemIn)
    {
        return withExistingParent(itemIn.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(NotEnoughMachines.MOD_ID, "block/" + itemIn.getId().getPath()));
    }

    public void createWallItemModel(RegistryObject<? extends Block> blockIn, RegistryObject<? extends Block> baseBlock)
    {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", new ResourceLocation(NotEnoughMachines.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void createSimpleBlockItemModel(RegistryObject<? extends Block> blockIn)
    {
        this.withExistingParent(NotEnoughMachines.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(blockIn.get()).getPath()));
    }
}
