package com.github.thomashooks.notenoughmachines.data.models;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.FlaxPlantBlock;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
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
        simpleCubeBlockWithItem(AllBlocks.BRONZE_BLOCK);
        axisBlock((RotatedPillarBlock) AllBlocks.COPPER_PLATE_BLOCK.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top")
        );
        simpleCubeBlockWithItem(AllBlocks.COKE_BLOCK);
        furnaceBlock(AllBlocks.COKE_OVEN.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/coke_oven_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/coke_oven_front"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/coke_oven_front_on"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/coke_oven_side")
        );
        simpleCubeBlockWithItem(AllBlocks.FIRE_BRICKS);
        stairsBlock(((StairBlock) AllBlocks.FIRE_BRICKS_STAIRS.get()), blockTexture(AllBlocks.FIRE_BRICKS.get()));
        slabBlock(((SlabBlock) AllBlocks.FIRE_BRICKS_SLAB.get()), blockTexture(AllBlocks.FIRE_BRICKS.get()), blockTexture(AllBlocks.FIRE_BRICKS.get()));
        wallBlock(((WallBlock) AllBlocks.FIRE_BRICKS_WALL.get()), blockTexture(AllBlocks.FIRE_BRICKS.get()));
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
        simpleCubeBlockWithItem(AllBlocks.TIN_BLOCK);
        simpleCubeBlockWithItem(AllBlocks.TIN_ORE);
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

    public void furnaceBlock(Block block, ResourceLocation side, ResourceLocation front, ResourceLocation frontOn, ResourceLocation top)
    {
        ModelFile furnaceOff = models().orientable(AllBlocks.getName(block), side, front, top);
        ModelFile furnaceOn = models().orientable(AllBlocks.getName(block) + "_on", side, frontOn, top);

        getVariantBuilder(block).forAllStates( state ->
        {
            Direction facing = state.getValue(HorizontalDirectionalBlock.FACING);
            boolean lit = state.getValue(BlockStateProperties.LIT);

            return ConfiguredModel.builder()
                    .modelFile(lit ? furnaceOn : furnaceOff)
                    .rotationY(((int) facing.toYRot() + 180) % 360)
                    .build();
        });
    }
}
