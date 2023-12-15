package com.github.thomashooks.notenoughmachines.data.models;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.FlaxPlantBlock;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.block.OneWayRailBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraftforge.client.model.generators.*;
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
        //Crops
        //--------------------------------------------------------------------------------------------------------------
        createFlaxPlant();

        //Full Blocks
        //--------------------------------------------------------------------------------------------------------------
        simpleCubeBlockWithItem(AllBlocks.BRONZE_BLOCK);
        axisBlock((RotatedPillarBlock) AllBlocks.COPPER_PLATE_BLOCK.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top")
        );
        simpleCubeBlockWithItem(AllBlocks.COKE_BLOCK);
        simpleCubeBlockWithItem(AllBlocks.FIRE_BRICKS);
        simpleCubeBlockWithItem(AllBlocks.FLUXSTONE);
        axisBlock((RotatedPillarBlock) AllBlocks.IRON_PLATE_BLOCK.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/iron_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/iron_plate_block_top")
        );
        simpleCubeBlockWithItem(AllBlocks.LINEN_BLOCK);
        axisBlock((RotatedPillarBlock) AllBlocks.POLISHED_FLUXSTONE.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top")
        );
        simpleCubeBlockWithItem(AllBlocks.TIN_BLOCK);
        simpleCubeBlockWithItem(AllBlocks.TIN_ORE);
        simpleCubeBlockWithItem(AllBlocks.WOODEN_FRAME);

        //Slabs
        //--------------------------------------------------------------------------------------------------------------
        slabBlock(((SlabBlock) AllBlocks.FIRE_BRICKS_SLAB.get()),
                blockTexture(AllBlocks.FIRE_BRICKS.get()),
                blockTexture(AllBlocks.FIRE_BRICKS.get())
        );
        slabBlock(((SlabBlock) AllBlocks.FLUXSTONE_SLAB.get()),
                blockTexture(AllBlocks.FLUXSTONE.get()),
                blockTexture(AllBlocks.FLUXSTONE.get())
        );
        slabBlock(((SlabBlock) AllBlocks.POLISHED_FLUXSTONE_SLAB.get()),
                blockTexture(AllBlocks.POLISHED_FLUXSTONE.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top")
        );

        //Stairs
        //--------------------------------------------------------------------------------------------------------------
        stairsBlock(((StairBlock) AllBlocks.FIRE_BRICKS_STAIRS.get()),
                blockTexture(AllBlocks.FIRE_BRICKS.get())
        );
        stairsBlock(((StairBlock) AllBlocks.FLUXSTONE_STAIRS.get()),
                blockTexture(AllBlocks.FLUXSTONE.get())
        );
        stairsBlock(((StairBlock) AllBlocks.POLISHED_FLUXSTONE_STAIRS.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top")
        );

        //Walls
        //--------------------------------------------------------------------------------------------------------------
        wallBlock(((WallBlock) AllBlocks.FIRE_BRICKS_WALL.get()),
                blockTexture(AllBlocks.FIRE_BRICKS.get())
        );
        wallBlock(((WallBlock) AllBlocks.FLUXSTONE_WALL.get()),
                blockTexture(AllBlocks.FLUXSTONE.get())
        );
        wallBlock(((WallBlock) AllBlocks.POLISHED_FLUXSTONE_WALL.get())
                , blockTexture(AllBlocks.POLISHED_FLUXSTONE.get())
        );

        //Furnaces
        //--------------------------------------------------------------------------------------------------------------
        furnaceBlock(AllBlocks.COKE_OVEN.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/coke_oven_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/coke_oven_front"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/coke_oven_front_on"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/coke_oven_side")
        );

        //Rails
        //--------------------------------------------------------------------------------------------------------------
        passiveStraightRailBlock(AllBlocks.CROSSOVER_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/crossover_rail")
        );
        directionalRailBlock(AllBlocks.ONE_WAY_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/one_way_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/one_way_rail_on")
        );
        passiveRailBlock(AllBlocks.HIGH_SPEED_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_rail_corner")
        );
        activeRailBlock(AllBlocks.HIGH_SPEED_ACTIVATOR_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_activator_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_activator_rail_on")
        );
        passiveStraightRailBlock(AllBlocks.HIGH_SPEED_CROSSOVER_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_crossover_rail")
        );
        activeRailBlock(AllBlocks.HIGH_SPEED_DETECTOR_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_detector_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_detector_rail_on")
        );
        activeRailBlock(AllBlocks.HIGH_SPEED_LOCKING_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_locking_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_locking_rail_on")
        );
        directionalRailBlock(AllBlocks.HIGH_SPEED_ONE_WAY_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_one_way_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_one_way_rail_on")
        );
        activeRailBlock(AllBlocks.HIGH_SPEED_POWERED_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_powered_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_powered_rail_on")
        );
        activeRailBlock(AllBlocks.LOCKING_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/locking_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/locking_rail_on")
        );
    }

    private void simpleCubeBlockWithItem(RegistryObject<? extends Block> blockObject)
    {
        simpleBlockWithItem(blockObject.get(), cubeAll(blockObject.get()));
    }

    private void createFlaxPlant()
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

    private void furnaceBlock(Block block, ResourceLocation side, ResourceLocation front, ResourceLocation frontOn, ResourceLocation top)
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

    private void passiveStraightRailBlock(Block block, ResourceLocation top)
    {
        ModelFile rail = rail(AllBlocks.getName(block), top);
        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.NORTH_SOUTH).addModels(ConfiguredModel.builder().modelFile(rail).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.EAST_WEST).addModels(ConfiguredModel.builder().modelFile(rail).rotationY(90).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.ASCENDING_NORTH).addModels(ConfiguredModel.builder().modelFile(rail).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.ASCENDING_EAST).addModels(ConfiguredModel.builder().modelFile(rail).rotationY(90).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.ASCENDING_SOUTH).addModels(ConfiguredModel.builder().modelFile(rail).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.ASCENDING_WEST).addModels(ConfiguredModel.builder().modelFile(rail).rotationY(90).build());
    }

    private void passiveRailBlock(Block block, ResourceLocation straight, ResourceLocation corner)
    {
        ModelFile rail = rail(AllBlocks.getName(block), straight);
        ModelFile railCorner = railCorner(AllBlocks.getName(block) + "_corner", corner);
        ModelFile railRaisedNE = railRaised(AllBlocks.getName(block) + "_raised_ne", straight, true);
        ModelFile railCRaisedSE = railRaised(AllBlocks.getName(block) + "_raised_sw", straight, false);
        getVariantBuilder(block).forAllStatesExcept( state ->
        {
            RailShape shape = state.getValue(BlockStateProperties.RAIL_SHAPE);
            int yRot = 0;
            ModelFile modelFile = rail;
            switch (shape)
            {
                case NORTH_SOUTH ->     { modelFile = rail; yRot = 0; }
                case EAST_WEST ->       { modelFile = rail; yRot = 90; }
                case ASCENDING_EAST ->  { modelFile = railRaisedNE; yRot = 90; }
                case ASCENDING_WEST ->  { modelFile = railCRaisedSE; yRot = 90; }
                case ASCENDING_NORTH -> { modelFile = railRaisedNE; yRot = 0; }
                case ASCENDING_SOUTH -> { modelFile = railCRaisedSE; yRot = 0; }
                case SOUTH_EAST ->      { modelFile = railCorner; yRot = 0; }
                case SOUTH_WEST ->      { modelFile = railCorner; yRot = 90; }
                case NORTH_WEST ->      { modelFile = railCorner; yRot = 180; }
                case NORTH_EAST ->      { modelFile = railCorner; yRot = 270; }
            };
            return ConfiguredModel.builder()
                    .modelFile(modelFile)
                    .rotationY(yRot)
                    .build();
        }, BaseRailBlock.WATERLOGGED);
    }

    private void activeRailBlock(Block block, ResourceLocation off, ResourceLocation on)
    {
        ModelFile rail = rail(AllBlocks.getName(block), off);
        ModelFile railOn = rail(AllBlocks.getName(block) + "_on", on);
        ModelFile railRaisedNEOn = railRaised(AllBlocks.getName(block) + "_on_raised_ne", on, true);
        ModelFile railRaisedSWOn = railRaised(AllBlocks.getName(block) + "_on_raised_sw", on, false);
        ModelFile railRaisedNE = railRaised(AllBlocks.getName(block) + "_raised_ne", off, true);
        ModelFile railRaisedSW = railRaised(AllBlocks.getName(block) + "_raised_sw", off, false);
        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.NORTH_SOUTH).with(BlockStateProperties.POWERED, false).addModels(ConfiguredModel.builder().modelFile(rail).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.NORTH_SOUTH).with(BlockStateProperties.POWERED, true).addModels(ConfiguredModel.builder().modelFile(railOn).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.EAST_WEST).with(BlockStateProperties.POWERED, false).addModels(ConfiguredModel.builder().modelFile(rail).rotationY(90).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.EAST_WEST).with(BlockStateProperties.POWERED, true).addModels(ConfiguredModel.builder().modelFile(railOn).rotationY(90).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.ASCENDING_NORTH).with(BlockStateProperties.POWERED, false).addModels(ConfiguredModel.builder().modelFile(railRaisedNE).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.ASCENDING_NORTH).with(BlockStateProperties.POWERED, true).addModels(ConfiguredModel.builder().modelFile(railRaisedNEOn).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.ASCENDING_EAST).with(BlockStateProperties.POWERED, false).addModels(ConfiguredModel.builder().modelFile(railRaisedNE).rotationY(90).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.ASCENDING_EAST).with(BlockStateProperties.POWERED, true).addModels(ConfiguredModel.builder().modelFile(railRaisedNEOn).rotationY(90).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.ASCENDING_SOUTH).with(BlockStateProperties.POWERED, false).addModels(ConfiguredModel.builder().modelFile(railRaisedSW).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.ASCENDING_SOUTH).with(BlockStateProperties.POWERED, true).addModels(ConfiguredModel.builder().modelFile(railRaisedSWOn).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.ASCENDING_WEST).with(BlockStateProperties.POWERED, false).addModels(ConfiguredModel.builder().modelFile(railRaisedSW).rotationY(90).build())
                .partialState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.ASCENDING_WEST).with(BlockStateProperties.POWERED, true).addModels(ConfiguredModel.builder().modelFile(railRaisedSWOn).rotationY(90).build());
    }

    private void directionalRailBlock(Block block, ResourceLocation off, ResourceLocation on)
    {
        ModelFile rail = rail(AllBlocks.getName(block), off);
        ModelFile railOn = rail(AllBlocks.getName(block) + "_on", on);
        ModelFile railRaisedNEOn = railRaised(AllBlocks.getName(block) + "_on_raised_ne", on, true);
        ModelFile railRaisedSWOn = railRaised(AllBlocks.getName(block) + "_on_raised_sw", on, false);
        ModelFile railRaisedNE = railRaised(AllBlocks.getName(block) + "_raised_ne", off, true);
        ModelFile railRaisedSW = railRaised(AllBlocks.getName(block) + "_raised_sw", off, false);

        getVariantBuilder(block).forAllStatesExcept( state ->
        {
            Direction facing = state.getValue(OneWayRailBlock.FACING);
            RailShape shape = state.getValue(OneWayRailBlock.SHAPE);
            boolean powered = state.getValue(OneWayRailBlock.POWERED);
            ModelFile modelFile = switch (shape)
            {
                case ASCENDING_NORTH, ASCENDING_EAST -> powered ? railRaisedNEOn : railRaisedNE;
                case ASCENDING_SOUTH, ASCENDING_WEST -> powered ? railRaisedSWOn : railRaisedSW;
                default -> powered ? railOn : rail;
            };

            if ((facing == Direction.SOUTH && shape == RailShape.ASCENDING_SOUTH) || (facing == Direction.WEST && shape == RailShape.ASCENDING_WEST))
                modelFile = powered ? railRaisedNEOn : railRaisedNE;
            else if ((facing == Direction.SOUTH && shape == RailShape.ASCENDING_NORTH) || (facing == Direction.WEST && shape == RailShape.ASCENDING_EAST))
                modelFile = powered ? railRaisedSWOn : railRaisedSW;

            return ConfiguredModel.builder()
                    .modelFile(modelFile)
                    .rotationY(((int) facing.toYRot() + 180) % 360)
                    .build();
        }, BaseRailBlock.WATERLOGGED);
    }

    private BlockModelBuilder rail(String name, ResourceLocation rail)
    {
        return models().withExistingParent(name, ModelProvider.BLOCK_FOLDER + "/rail_flat")
                .texture("rail", rail)
                .renderType("cutout");
    }

    private BlockModelBuilder railCorner(String name, ResourceLocation rail)
    {
        return models().withExistingParent(name, ModelProvider.BLOCK_FOLDER + "/rail_curved")
                .texture("rail", rail)
                .renderType("cutout");
    }

    private BlockModelBuilder railRaised(String name, ResourceLocation rail, boolean isNE)
    {
        return models().withExistingParent(name, ModelProvider.BLOCK_FOLDER + "/template_rail_raised_" + (isNE ? "ne" : "sw"))
                .texture("rail", rail)
                .renderType("cutout");
    }
}
