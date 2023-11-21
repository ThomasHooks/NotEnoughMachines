package com.github.thomashooks.notenoughmachines.data.models;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.FlaxPlantBlock;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
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
        axisBlock((RotatedPillarBlock) AllBlocks.COPPER_PLATE_BLOCK.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top")
        );
        createFlaxPlant();
        simpleCubeBlockWithItem(AllBlocks.FLUXSTONE);
        stairsBlock(((StairBlock) AllBlocks.FLUXSTONE_STAIRS.get()), blockTexture(AllBlocks.FLUXSTONE.get()));
        slabBlock(((SlabBlock) AllBlocks.FLUXSTONE_SLAB.get()), blockTexture(AllBlocks.FLUXSTONE.get()), blockTexture(AllBlocks.FLUXSTONE.get()));
        wallBlock(((WallBlock) AllBlocks.FLUXSTONE_WALL.get()), blockTexture(AllBlocks.FLUXSTONE.get()));
        axisBlock((RotatedPillarBlock) AllBlocks.IRON_PLATE_BLOCK.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/iron_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/iron_plate_block_top")
        );
        simpleCubeBlockWithItem(AllBlocks.LINEN_BLOCK);
        axisBlock((RotatedPillarBlock) AllBlocks.POLISHED_FLUXSTONE.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top")
        );
        stairsBlock(((StairBlock) AllBlocks.POLISHED_FLUXSTONE_STAIRS.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top")
        );
        slabBlock(((SlabBlock) AllBlocks.POLISHED_FLUXSTONE_SLAB.get()),
                blockTexture(AllBlocks.POLISHED_FLUXSTONE.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top")
        );
        wallBlock(((WallBlock) AllBlocks.POLISHED_FLUXSTONE_WALL.get()), blockTexture(AllBlocks.POLISHED_FLUXSTONE.get()));
        simpleCubeBlockWithItem(AllBlocks.WOODEN_FRAME);
    }

    public void simpleCubeBlockWithItem(RegistryObject<? extends Block> blockObject)
    {
        simpleBlockWithItem(blockObject.get(), cubeAll(blockObject.get()));
    }

    public void createFlaxPlant()
    {
        Function<BlockState, ConfiguredModel[]> function = state -> flaxCropStates(state, (CropBlock) AllBlocks.FLAXPLANT.get(), "flaxplant_stage");
        getVariantBuilder((CropBlock) AllBlocks.FLAXPLANT.get()).forAllStates(function);
    }

    private ConfiguredModel[] flaxCropStates(BlockState state, CropBlock block, String textureName)
    {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(textureName + state.getValue(((FlaxPlantBlock)block).getAgeProperty()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/flaxplant/" + textureName + state.getValue(((FlaxPlantBlock)block).getAgeProperty()))).renderType("cutout"));
        return models;
    }
}
