package com.github.thomashooks.notenoughmachines.data.models;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.NEMBlocks;
import com.github.thomashooks.notenoughmachines.world.item.NEMItems;
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
        simpleItemModel(NEMItems.CONJUNCTIONER);
        simpleItemModel(NEMItems.COPPER_PLATE);
        simpleBlockItemModel(NEMBlocks.COPPER_PLATE_BLOCK);
        simpleItemModel(NEMItems.COPPER_ROD);
        simpleItemModel(NEMItems.CRUSHED_COPPER_ORE);
        simpleItemModel(NEMItems.CRUSHED_GOLD_ORE);
        simpleItemModel(NEMItems.CRUSHED_IRON_ORE);
        simpleItemModel(NEMItems.FLUX);
        simpleBlockItemModel(NEMBlocks.FLUXSTONE_SLAB);
        simpleBlockItemModel(NEMBlocks.FLUXSTONE_STAIRS);
        wallItemModel(NEMBlocks.FLUXSTONE_WALL, NEMBlocks.FLUXSTONE);
        simpleItemModel(NEMItems.FLOUR);
        simpleItemModel(NEMItems.FLAXSEED);
        simpleItemModel(NEMItems.FLAX);
        simpleItemModel(NEMItems.FLAX_STRING);
        simpleItemModel(NEMItems.GEAR);
        simpleItemModel(NEMItems.HAMMER_AND_ANVIL);
        simpleItemModel(NEMItems.IRON_PLATE);
        simpleBlockItemModel(NEMBlocks.IRON_PLATE_BLOCK);
        simpleItemModel(NEMItems.IRON_ROD);
        simpleItemModel(NEMItems.IRON_SCREW);
        simpleItemModel(NEMItems.LINEN);
        simpleItemModel(NEMItems.LINSEED_OIL);
        simpleBlockItemModel(NEMBlocks.POLISHED_FLUXSTONE_SLAB);
        simpleBlockItemModel(NEMBlocks.POLISHED_FLUXSTONE_STAIRS);
        wallItemModel(NEMBlocks.POLISHED_FLUXSTONE_WALL, NEMBlocks.POLISHED_FLUXSTONE);
        simpleItemModel(NEMItems.REDSTONE_COLLECTOR);
        simpleItemModel(NEMItems.REDSTONE_EMITTER);
        simpleItemModel(NEMItems.ROLLERS);
        simpleItemModel(NEMItems.WIND_WHEEL_BLADE);
        simpleItemModel(NEMItems.WIND_WHEEL_SAIL);
    }

    public ItemModelBuilder simpleItemModel(RegistryObject<? extends Item> itemIn)
    {
        return withExistingParent(itemIn.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(NotEnoughMachines.MOD_ID, "item/" + itemIn.getId().getPath()));
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
