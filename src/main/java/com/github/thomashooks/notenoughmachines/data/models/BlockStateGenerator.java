package com.github.thomashooks.notenoughmachines.data.models;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.FlaxPlantBlock;
import com.github.thomashooks.notenoughmachines.world.block.NEMBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class BlockStateGenerator extends BlockStateProvider
{
    public BlockStateGenerator(PackOutput output, ExistingFileHelper exFileHelper)
    {
        super(output, NotEnoughMachines.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        axisBlock((RotatedPillarBlock) NEMBlocks.COPPER_PLATE_BLOCK.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top"));
        createFlaxPlant();
        simpleCubeBlockWithItem(NEMBlocks.FLUXSTONE);
        stairsBlock(((StairBlock) NEMBlocks.FLUXSTONE_STAIRS.get()), blockTexture(NEMBlocks.FLUXSTONE.get()));
        slabBlock(((SlabBlock) NEMBlocks.FLUXSTONE_SLAB.get()), blockTexture(NEMBlocks.FLUXSTONE.get()), blockTexture(NEMBlocks.FLUXSTONE.get()));
        wallBlock(((WallBlock) NEMBlocks.FLUXSTONE_WALL.get()), blockTexture(NEMBlocks.FLUXSTONE.get()));
        axisBlock((RotatedPillarBlock) NEMBlocks.IRON_PLATE_BLOCK.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/iron_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/iron_plate_block_top"));
        simpleCubeBlockWithItem(NEMBlocks.LINEN_BLOCK);
        simpleCubeBlockWithItem(NEMBlocks.POLISHED_FLUXSTONE);
        stairsBlock(((StairBlock) NEMBlocks.POLISHED_FLUXSTONE_STAIRS.get()), blockTexture(NEMBlocks.POLISHED_FLUXSTONE.get()));
        slabBlock(((SlabBlock) NEMBlocks.POLISHED_FLUXSTONE_SLAB.get()), blockTexture(NEMBlocks.POLISHED_FLUXSTONE.get()), blockTexture(NEMBlocks.POLISHED_FLUXSTONE.get()));
        wallBlock(((WallBlock) NEMBlocks.POLISHED_FLUXSTONE_WALL.get()), blockTexture(NEMBlocks.POLISHED_FLUXSTONE.get()));
        simpleCubeBlockWithItem(NEMBlocks.WOODEN_FRAME);

        //axisBlock(RotatedPillarBlock block, ResourceLocation side, ResourceLocation end);
    }

    public void simpleCubeBlockWithItem(RegistryObject<? extends Block> blockObject)
    {
        simpleBlockWithItem(blockObject.get(), cubeAll(blockObject.get()));
    }

    public void createFlaxPlant()
    {
        Function<BlockState, ConfiguredModel[]> function = state -> flaxCropStates(state, (CropBlock) NEMBlocks.FLAXPLANT.get(), "flaxplant_stage");
        getVariantBuilder((CropBlock) NEMBlocks.FLAXPLANT.get()).forAllStates(function);
    }

    private ConfiguredModel[] flaxCropStates(BlockState state, CropBlock block, String textureName)
    {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(textureName + state.getValue(((FlaxPlantBlock)block).getAgeProperty()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/flaxplant/" + textureName + state.getValue(((FlaxPlantBlock)block).getAgeProperty()))).renderType("cutout"));
        return models;
    }
}
