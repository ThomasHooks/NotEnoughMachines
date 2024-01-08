package com.github.thomashooks.notenoughmachines.data.models;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.FlaxPlantBlock;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.block.OneWayRailBlock;
import com.github.thomashooks.notenoughmachines.world.block.state.properties.AllBlockStateProperties;
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
        createSimpleCubeWithItem(AllBlocks.BRONZE_BLOCK);
        createSimpleTransparentCube(AllBlocks.BRONZE_GRATE.get(), new ResourceLocation(NotEnoughMachines.MOD_ID, "block/bronze_grate"));
        axisBlock((RotatedPillarBlock) AllBlocks.BRONZE_PLATE_BLOCK.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/bronze_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/bronze_plate_block_top")
        );
        axisBlock((RotatedPillarBlock) AllBlocks.COPPER_PLATE_BLOCK.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top")
        );
        axisBlock((RotatedPillarBlock) AllBlocks.COPPER_PLATE_BLOCK_EXPOSED.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_exposed"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_exposed")
        );
        axisBlock((RotatedPillarBlock) AllBlocks.COPPER_PLATE_BLOCK_WEATHERED.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_weathered"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_weathered")
        );
        axisBlock((RotatedPillarBlock) AllBlocks.COPPER_PLATE_BLOCK_OXIDIZED.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_oxidized"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_oxidized")
        );
        axisBlock((RotatedPillarBlock) AllBlocks.COPPER_PLATE_BLOCK_WAXED.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top")
        );
        axisBlock((RotatedPillarBlock) AllBlocks.COPPER_PLATE_BLOCK_EXPOSED_WAXED.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_exposed"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_exposed")
        );
        axisBlock((RotatedPillarBlock) AllBlocks.COPPER_PLATE_BLOCK_WEATHERED_WAXED.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_weathered"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_weathered")
        );
        axisBlock((RotatedPillarBlock) AllBlocks.COPPER_PLATE_BLOCK_OXIDIZED_WAXED.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_oxidized"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_oxidized")
        );
        createSimpleCubeWithItem(AllBlocks.COKE_BLOCK);
        axisBlock((RotatedPillarBlock) AllBlocks.GOLD_PLATE_BLOCK.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/gold_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/gold_plate_block_top")
        );
        createSimpleCubeWithItem(AllBlocks.FIRE_BRICKS);
        createSimpleCubeWithItem(AllBlocks.FLUXSTONE);
        axisBlock((RotatedPillarBlock) AllBlocks.IRON_PLATE_BLOCK.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/iron_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/iron_plate_block_top")
        );
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_WHITE);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_ORANGE);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_MAGENTA);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_LIGHT_BLUE);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_YELLOW);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_LIME);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_PINK);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_GRAY);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_LIGHT_GRAY);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_CYAN);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_PURPLE);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_BLUE);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_BROWN);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_GREEN);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_RED);
        createSimpleCubeWithItem(AllBlocks.LINEN_BLOCK_BLACK);
        axisBlock((RotatedPillarBlock) AllBlocks.POLISHED_FLUXSTONE.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top")
        );
        createSimpleCubeWithItem(AllBlocks.RAW_TIN_BLOCK);
        createSimpleCubeWithItem(AllBlocks.TIN_BLOCK);
        createSimpleCubeWithItem(AllBlocks.TIN_ORE);
        axisBlock((RotatedPillarBlock) AllBlocks.TIN_PLATE_BLOCK.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/tin_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/tin_plate_block_top")
        );
        createSimpleCubeWithItem(AllBlocks.VERMILION_BLOCK);
        createSimpleCubeWithItem(AllBlocks.WOODEN_FRAME);

        //Slabs
        //--------------------------------------------------------------------------------------------------------------
        slabBlock(((SlabBlock) AllBlocks.BRONZE_PLATE_SLAB.get()),
                blockTexture(AllBlocks.BRONZE_PLATE_BLOCK.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/bronze_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/bronze_plate_block_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/bronze_plate_block_top")
        );
        slabBlock(((SlabBlock) AllBlocks.COPPER_PLATE_SLAB.get()),
                blockTexture(AllBlocks.COPPER_PLATE_BLOCK.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top")
        );
        slabBlock(((SlabBlock) AllBlocks.COPPER_PLATE_SLAB_EXPOSED.get()),
                blockTexture(AllBlocks.COPPER_PLATE_BLOCK_EXPOSED.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_exposed"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_exposed"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_exposed")
        );
        slabBlock(((SlabBlock) AllBlocks.COPPER_PLATE_SLAB_WEATHERED.get()),
                blockTexture(AllBlocks.COPPER_PLATE_BLOCK_WEATHERED.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_weathered"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_weathered"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_weathered")
        );
        slabBlock(((SlabBlock) AllBlocks.COPPER_PLATE_SLAB_OXIDIZED.get()),
                blockTexture(AllBlocks.COPPER_PLATE_BLOCK_OXIDIZED.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_oxidized"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_oxidized"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_oxidized")
        );
        slabBlock(((SlabBlock) AllBlocks.COPPER_PLATE_SLAB_WAXED.get()),
                blockTexture(AllBlocks.COPPER_PLATE_BLOCK_WAXED.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top")
        );
        slabBlock(((SlabBlock) AllBlocks.COPPER_PLATE_SLAB_EXPOSED_WAXED.get()),
                blockTexture(AllBlocks.COPPER_PLATE_BLOCK_EXPOSED_WAXED.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_exposed"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_exposed"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_exposed")
        );
        slabBlock(((SlabBlock) AllBlocks.COPPER_PLATE_SLAB_WEATHERED_WAXED.get()),
                blockTexture(AllBlocks.COPPER_PLATE_BLOCK_WEATHERED_WAXED.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_weathered"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_weathered"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_weathered")
        );
        slabBlock(((SlabBlock) AllBlocks.COPPER_PLATE_SLAB_OXIDIZED_WAXED.get()),
                blockTexture(AllBlocks.COPPER_PLATE_BLOCK_OXIDIZED_WAXED.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_oxidized"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_oxidized"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_oxidized")
        );
        slabBlock(((SlabBlock) AllBlocks.FIRE_BRICKS_SLAB.get()),
                blockTexture(AllBlocks.FIRE_BRICKS.get()),
                blockTexture(AllBlocks.FIRE_BRICKS.get())
        );
        slabBlock(((SlabBlock) AllBlocks.FLUXSTONE_SLAB.get()),
                blockTexture(AllBlocks.FLUXSTONE.get()),
                blockTexture(AllBlocks.FLUXSTONE.get())
        );
        slabBlock(((SlabBlock) AllBlocks.GOLD_PLATE_SLAB.get()),
                blockTexture(AllBlocks.GOLD_PLATE_BLOCK.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/gold_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/gold_plate_block_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/gold_plate_block_top")
        );
        slabBlock(((SlabBlock) AllBlocks.IRON_PLATE_SLAB.get()),
                blockTexture(AllBlocks.IRON_PLATE_BLOCK.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/iron_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/iron_plate_block_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/iron_plate_block_top")
        );
        slabBlock(((SlabBlock) AllBlocks.POLISHED_FLUXSTONE_SLAB.get()),
                blockTexture(AllBlocks.POLISHED_FLUXSTONE.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top")
        );
        slabBlock(((SlabBlock) AllBlocks.TIN_PLATE_SLAB.get()),
                blockTexture(AllBlocks.TIN_PLATE_BLOCK.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/tin_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/tin_plate_block_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/tin_plate_block_top")
        );
        slabBlock(((SlabBlock) AllBlocks.WOODEN_FRAME_SLAB.get()),
                blockTexture(AllBlocks.WOODEN_FRAME.get()),
                blockTexture(AllBlocks.WOODEN_FRAME.get())
        );

        //Stairs
        //--------------------------------------------------------------------------------------------------------------
        stairsBlock(((StairBlock) AllBlocks.BRONZE_PLATE_STAIRS.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/bronze_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/bronze_plate_block_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/bronze_plate_block_top")
        );
        stairsBlock(((StairBlock) AllBlocks.COPPER_PLATE_STAIRS.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top")
        );
        stairsBlock(((StairBlock) AllBlocks.COPPER_PLATE_STAIRS_EXPOSED.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_exposed"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_exposed"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_exposed")
        );
        stairsBlock(((StairBlock) AllBlocks.COPPER_PLATE_STAIRS_WEATHERED.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_weathered"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_weathered"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_weathered")
        );
        stairsBlock(((StairBlock) AllBlocks.COPPER_PLATE_STAIRS_OXIDIZED.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_oxidized"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_oxidized"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_oxidized")
        );
        stairsBlock(((StairBlock) AllBlocks.COPPER_PLATE_STAIRS_WAXED.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top")
        );
        stairsBlock(((StairBlock) AllBlocks.COPPER_PLATE_STAIRS_EXPOSED_WAXED.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_exposed"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_exposed"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_exposed")
        );
        stairsBlock(((StairBlock) AllBlocks.COPPER_PLATE_STAIRS_WEATHERED_WAXED.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_weathered"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_weathered"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_weathered")
        );
        stairsBlock(((StairBlock) AllBlocks.COPPER_PLATE_STAIRS_OXIDIZED_WAXED.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_side_oxidized"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_oxidized"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/copper_plate_block_top_oxidized")
        );
        stairsBlock(((StairBlock) AllBlocks.FIRE_BRICKS_STAIRS.get()),
                blockTexture(AllBlocks.FIRE_BRICKS.get())
        );
        stairsBlock(((StairBlock) AllBlocks.FLUXSTONE_STAIRS.get()),
                blockTexture(AllBlocks.FLUXSTONE.get())
        );
        stairsBlock(((StairBlock) AllBlocks.GOLD_PLATE_STAIRS.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/gold_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/gold_plate_block_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/gold_plate_block_top")
        );
        stairsBlock(((StairBlock) AllBlocks.IRON_PLATE_STAIRS.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/iron_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/iron_plate_block_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/iron_plate_block_top")
        );
        stairsBlock(((StairBlock) AllBlocks.POLISHED_FLUXSTONE_STAIRS.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/polished_fluxstone_top")
        );
        stairsBlock(((StairBlock) AllBlocks.TIN_PLATE_STAIRS.get()),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/tin_plate_block_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/tin_plate_block_top"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/tin_plate_block_top")
        );
        stairsBlock(((StairBlock) AllBlocks.WOODEN_FRAME_STAIRS.get()),
                blockTexture(AllBlocks.WOODEN_FRAME.get())
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

        //Ladders
        //--------------------------------------------------------------------------------------------------------------
        createScaffolding(AllBlocks.BRONZE_SCAFFOLDING.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/bronze_scaffolding")),
                new ModelFile.UncheckedModelFile(modLoc("block/bronze_scaffolding_unstable"))
        );
        createScaffolding(AllBlocks.IRON_SCAFFOLDING.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/iron_scaffolding")),
                new ModelFile.UncheckedModelFile(modLoc("block/iron_scaffolding_unstable"))
        );

        //Furnaces
        //--------------------------------------------------------------------------------------------------------------
        createFurnace(AllBlocks.COKE_OVEN.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/coke_oven_side"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/coke_oven_front"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/coke_oven_front_on"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/coke_oven_side")
        );

        //Redstone
        //--------------------------------------------------------------------------------------------------------------
        createPressurePlate(AllBlocks.VERMILION_PRESSURE_PLATE.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/vermilion_block")
        );

        //Rails
        //--------------------------------------------------------------------------------------------------------------
        createActiveRail(AllBlocks.CHIME_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/chime_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/chime_rail_on")
        );
        createPassiveStraightRail(AllBlocks.CROSSOVER_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/crossover_rail")
        );
        createDirectionalRail(AllBlocks.ONE_WAY_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/one_way_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/one_way_rail_on")
        );
        createPassiveRail(AllBlocks.HIGH_SPEED_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_rail_corner")
        );
        createActiveRail(AllBlocks.HIGH_SPEED_ACTIVATOR_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_activator_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_activator_rail_on")
        );
        createActiveRail(AllBlocks.HIGH_SPEED_CHIME_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_chime_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_chime_rail_on")
        );
        createPassiveStraightRail(AllBlocks.HIGH_SPEED_CROSSOVER_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_crossover_rail")
        );
        createActiveRail(AllBlocks.HIGH_SPEED_DETECTOR_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_detector_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_detector_rail_on")
        );
        createFourSpeedRail(AllBlocks.HIGH_SPEED_LIMITER_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_limiter_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_limiter_rail_1tick_on"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_limiter_rail_2tick_on"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_limiter_rail_3tick_on"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_limiter_rail_4tick_on")
        );
        createActiveRail(AllBlocks.HIGH_SPEED_LOCKING_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_locking_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_locking_rail_on")
        );
        createDirectionalRail(AllBlocks.HIGH_SPEED_ONE_WAY_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_one_way_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_one_way_rail_on")
        );
        createActiveRail(AllBlocks.HIGH_SPEED_POWERED_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_powered_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/high_speed_powered_rail_on")
        );
        createFourSpeedRail(AllBlocks.LIMITER_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/limiter_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/limiter_rail_1tick_on"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/limiter_rail_2tick_on"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/limiter_rail_3tick_on"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/limiter_rail_4tick_on")
        );
        createActiveRail(AllBlocks.LOCKING_RAIL.get(),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/locking_rail"),
                new ResourceLocation(NotEnoughMachines.MOD_ID, "block/locking_rail_on")
        );
    }

    private void createSimpleCubeWithItem(RegistryObject<? extends Block> blockObject)
    {
        simpleBlockWithItem(blockObject.get(), cubeAll(blockObject.get()));
    }

    private void createSimpleTransparentCube(Block block, ResourceLocation texture)
    {
        simpleBlockWithItem(block, transparentCube(AllBlocks.getName(block), texture));
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

    private void createFurnace(Block block, ResourceLocation side, ResourceLocation front, ResourceLocation frontOn, ResourceLocation top)
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

    public void createPressurePlate(Block block, ResourceLocation texture)
    {
        ModelFile pressurePlate = models().pressurePlate(AllBlocks.getName(block), texture);
        ModelFile pressurePlateDown = models().pressurePlateDown(AllBlocks.getName(block) + "_down", texture);
        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.POWERED, true).addModels(new ConfiguredModel(pressurePlateDown))
                .partialState().with(BlockStateProperties.POWERED, false).addModels(new ConfiguredModel(pressurePlate));
    }

    public void createScaffolding(Block block, ModelFile stableModel, ModelFile unstableModel)
    {
        getVariantBuilder(block)
                .partialState().with(AllBlockStateProperties.UNSTABLE, true).addModels(new ConfiguredModel(unstableModel))
                .partialState().with(AllBlockStateProperties.UNSTABLE, false).addModels(new ConfiguredModel(stableModel));
    }

    private void createPassiveStraightRail(Block block, ResourceLocation top)
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

    private void createPassiveRail(Block block, ResourceLocation straight, ResourceLocation corner)
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

    private void createActiveRail(Block block, ResourceLocation off, ResourceLocation on)
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

    private void createFourSpeedRail(Block block, ResourceLocation off, ResourceLocation onTick1, ResourceLocation onTick2, ResourceLocation onTick3, ResourceLocation onTick4)
    {
        ModelFile rail = rail(AllBlocks.getName(block), off);
        ModelFile railRaisedNE = railRaised(AllBlocks.getName(block) + "_raised_ne", off, true);
        ModelFile railRaisedSW = railRaised(AllBlocks.getName(block) + "_raised_sw", off, false);

        ModelFile railOnTick1 = rail(AllBlocks.getName(block) + "_on_tick1", onTick1);
        ModelFile railRaisedNEOnTick1 = railRaised(AllBlocks.getName(block) + "_on_tick1_raised_ne", onTick1, true);
        ModelFile railRaisedSWOnTick1 = railRaised(AllBlocks.getName(block) + "_on_tick1_raised_sw", onTick1, false);

        ModelFile railOnTick2 = rail(AllBlocks.getName(block) + "_on_tick2", onTick2);
        ModelFile railRaisedNEOnTick2 = railRaised(AllBlocks.getName(block) + "_on_tick2_raised_ne", onTick2, true);
        ModelFile railRaisedSWOnTick2 = railRaised(AllBlocks.getName(block) + "_on_tick2_raised_sw", onTick2, false);

        ModelFile railOnTick3 = rail(AllBlocks.getName(block) + "_on_tick3", onTick3);
        ModelFile railRaisedNEOnTick3 = railRaised(AllBlocks.getName(block) + "_on_tick3_raised_ne", onTick3, true);
        ModelFile railRaisedSWOnTick3 = railRaised(AllBlocks.getName(block) + "_on_tick3_raised_sw", onTick3, false);

        ModelFile railOnTick4 = rail(AllBlocks.getName(block) + "_on_tick4", onTick4);
        ModelFile railRaisedNEOnTick4 = railRaised(AllBlocks.getName(block) + "_on_tick4_raised_ne", onTick4, true);
        ModelFile railRaisedSWOnTick4 = railRaised(AllBlocks.getName(block) + "_on_tick4_raised_sw", onTick4, false);

        getVariantBuilder(block).forAllStatesExcept( state ->
        {
            int tick = state.getValue(AllBlockStateProperties.SPEED);
            RailShape shape = state.getValue(BlockStateProperties.RAIL_SHAPE_STRAIGHT);
            boolean powered = state.getValue(BlockStateProperties.POWERED);
            ModelFile modelFile = null;
            if (powered)
            {
                switch (shape)
                {
                    case ASCENDING_NORTH, ASCENDING_EAST ->
                    {
                        switch (tick)
                        {
                            case 1 -> modelFile = railRaisedNEOnTick1;
                            case 2 -> modelFile = railRaisedNEOnTick2;
                            case 3-> modelFile = railRaisedNEOnTick3;
                            case 4 -> modelFile = railRaisedNEOnTick4;
                        }
                    }
                    case ASCENDING_SOUTH, ASCENDING_WEST ->
                    {
                        switch (tick)
                        {
                            case 1 -> modelFile = railRaisedSWOnTick1;
                            case 2 -> modelFile = railRaisedSWOnTick2;
                            case 3-> modelFile = railRaisedSWOnTick3;
                            case 4 -> modelFile = railRaisedSWOnTick4;
                        }
                    }
                    default ->
                    {
                        switch (tick)
                        {
                            case 1 -> modelFile = railOnTick1;
                            case 2 -> modelFile = railOnTick2;
                            case 3-> modelFile = railOnTick3;
                            case 4 -> modelFile = railOnTick4;
                        }
                    }
                }
            }
            else
            {
                switch (shape)
                {
                    case ASCENDING_NORTH, ASCENDING_EAST -> modelFile = railRaisedNE;
                    case ASCENDING_SOUTH, ASCENDING_WEST ->modelFile = railRaisedSW;
                    default -> modelFile = rail;
                }
            }

            int yRot = switch (shape)
            {
                case EAST_WEST, ASCENDING_EAST, ASCENDING_WEST -> 90;
                default -> 0;
            };

            return ConfiguredModel.builder()
                    .modelFile(modelFile)
                    .rotationY((yRot))
                    .build();
        }, BaseRailBlock.WATERLOGGED);
    }

    private void createDirectionalRail(Block block, ResourceLocation off, ResourceLocation on)
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

    private BlockModelBuilder transparentCube(String name, ResourceLocation texture)
    {
        return models().withExistingParent(name, ModelProvider.BLOCK_FOLDER + "/cube_all")
                .texture("all", texture)
                .renderType("cutout");
    }
}
