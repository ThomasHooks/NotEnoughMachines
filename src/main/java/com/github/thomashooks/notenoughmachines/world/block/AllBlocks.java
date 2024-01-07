package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.ToIntFunction;

public class AllBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NotEnoughMachines.MOD_ID);

    public static final RegistryObject<Block> AXLE = BLOCKS.register("axle",
            ()-> new AxleBlock(Block.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(1.8F, 2.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> BRONZE_BLOCK = BLOCKS.register("bronze_block",
            ()-> new Block(Block.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.COPPER)
            ));
    public static final RegistryObject<Block> BRONZE_LADDER = BLOCKS.register("bronze_ladder",
            ()-> new LadderBlock(Block.Properties.of()
                    .forceSolidOff()
                    .strength(0.7F, 6.0F)
                    .sound(SoundType.COPPER)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
            ));
    public static final RegistryObject<Block> BRONZE_PLATE_BLOCK = BLOCKS.register("bronze_plate_block",
            ()-> new RotatedPillarBlock(Block.Properties.copy(AllBlocks.BRONZE_BLOCK.get())
            ));
    public static final RegistryObject<Block> BRONZE_PLATE_SLAB = BLOCKS.register("bronze_plate_slab",
            ()-> new SlabBlock(Block.Properties.copy(BRONZE_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> BRONZE_PLATE_STAIRS = BLOCKS.register("bronze_plate_stairs",
            ()-> new StairBlock(() -> BRONZE_PLATE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(BRONZE_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> BRONZE_SCAFFOLDING = BLOCKS.register("bronze_scaffolding",
            ()-> new MetalScaffoldBlock(19, Block.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .noCollission()
                    .strength(0.4F, 6.0F)
                    .sound(SoundType.COPPER)
                    .dynamicShape()
                    .isValidSpawn(AllBlocks::never)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(AllBlocks::never)
            ));
    public static final RegistryObject<Block> BUFFER_STOP_RAIL = BLOCKS.register("buffer_stop_rail",
            ()-> new BufferStopRailBlock(Block.Properties.of()
                    .strength(1.05F)
                    .sound(SoundType.METAL)
                    .noOcclusion()
                    .isRedstoneConductor(AllBlocks::always)
            ));
    public static final RegistryObject<Block> CHIME_RAIL = BLOCKS.register("chime_rail",
            ()-> new ChimeRailBlock(Block.Properties.of()
                    .noCollission()
                    .strength(0.7F)
                    .sound(SoundType.METAL)
            ));
    public static final RegistryObject<Block> COKE_BLOCK = BLOCKS.register("coke_block",
            ()-> new Block(Block.Properties.of()
                    .mapColor(MapColor.COLOR_GRAY)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
            ));
    public static final RegistryObject<Block> COKE_OVEN = BLOCKS.register("coke_oven",
            ()-> new CokeOvenBlock(Block.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.5F, 3.5F)
                    .lightLevel(litBlockEmission(13))
            ));
    public static final RegistryObject<Block> CROSSOVER_RAIL = BLOCKS.register("crossover_rail",
            ()-> new CrossoverRailBlock(Block.Properties.of()
                    .noCollission()
                    .strength(0.7F)
                    .sound(SoundType.METAL)
            ));
    public static final RegistryObject<Block> COGWHEEL_LARGE = BLOCKS.register("large_cogwheel",
            ()-> new CogwheelLargeBlock(Block.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(1.8F, 2.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> COGWHEEL_SMALL = BLOCKS.register("small_cogwheel",
            ()-> new CogwheelSmallBlock(Block.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(1.8F, 2.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> CONJUNCTIONER = BLOCKS.register("conjunctioner",
            ()-> new ConjunctionerBlock(Block.Properties
                    .copy(Blocks.REPEATER)
                    .instabreak()
                    .sound(SoundType.STONE)
                    .pushReaction(PushReaction.DESTROY)
            ));
    public static final RegistryObject<Block> COPPER_PLATE_BLOCK = BLOCKS.register("copper_plate_block",
            ()-> new WeatheringRotatedPillarBlock(IWeatheringBlock.State.UNAFFECTED, Block.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.COPPER)
            ));
    public static final RegistryObject<Block> COPPER_PLATE_BLOCK_EXPOSED = BLOCKS.register("copper_plate_block_exposed",
            ()-> new WeatheringRotatedPillarBlock(IWeatheringBlock.State.EXPOSED, Block.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_BLOCK_WEATHERED = BLOCKS.register("copper_plate_block_weathered",
            ()-> new WeatheringRotatedPillarBlock(IWeatheringBlock.State.WEATHERED, Block.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_BLOCK_OXIDIZED = BLOCKS.register("copper_plate_block_oxidized",
            ()-> new WeatheringRotatedPillarBlock(IWeatheringBlock.State.OXIDIZED, Block.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_BLOCK_WAXED = BLOCKS.register("copper_plate_block_waxed",
            ()-> new WeatheringRotatedPillarBlock(IWeatheringBlock.State.UNAFFECTED, Block.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_BLOCK_EXPOSED_WAXED = BLOCKS.register("copper_plate_block_exposed_waxed",
            ()-> new WeatheringRotatedPillarBlock(IWeatheringBlock.State.EXPOSED, Block.Properties.copy(COPPER_PLATE_BLOCK_EXPOSED.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_BLOCK_WEATHERED_WAXED = BLOCKS.register("copper_plate_block_weathered_waxed",
            ()-> new WeatheringRotatedPillarBlock(IWeatheringBlock.State.WEATHERED, Block.Properties.copy(COPPER_PLATE_BLOCK_WEATHERED.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_BLOCK_OXIDIZED_WAXED = BLOCKS.register("copper_plate_block_oxidized_waxed",
            ()-> new WeatheringRotatedPillarBlock(IWeatheringBlock.State.OXIDIZED, Block.Properties.copy(COPPER_PLATE_BLOCK_OXIDIZED.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_SLAB = BLOCKS.register("copper_plate_slab",
            ()-> new WeatheringSlabBlock(IWeatheringBlock.State.UNAFFECTED, Block.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_SLAB_EXPOSED = BLOCKS.register("copper_plate_slab_exposed",
            ()-> new WeatheringSlabBlock(IWeatheringBlock.State.EXPOSED, Block.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_SLAB_WEATHERED = BLOCKS.register("copper_plate_slab_weathered",
            ()-> new WeatheringSlabBlock(IWeatheringBlock.State.WEATHERED, Block.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_SLAB_OXIDIZED = BLOCKS.register("copper_plate_slab_oxidized",
            ()-> new WeatheringSlabBlock(IWeatheringBlock.State.OXIDIZED, Block.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_SLAB_WAXED = BLOCKS.register("copper_plate_slab_waxed",
            ()-> new WeatheringSlabBlock(IWeatheringBlock.State.UNAFFECTED, Block.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_SLAB_EXPOSED_WAXED = BLOCKS.register("copper_plate_slab_exposed_waxed",
            ()-> new WeatheringSlabBlock(IWeatheringBlock.State.EXPOSED, Block.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_SLAB_WEATHERED_WAXED = BLOCKS.register("copper_plate_slab_weathered_waxed",
            ()-> new WeatheringSlabBlock(IWeatheringBlock.State.WEATHERED, Block.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_SLAB_OXIDIZED_WAXED = BLOCKS.register("copper_plate_slab_oxidized_waxed",
            ()-> new WeatheringSlabBlock(IWeatheringBlock.State.OXIDIZED, Block.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_STAIRS = BLOCKS.register("copper_plate_stairs",
            ()-> new WeatheringStairBlock(IWeatheringBlock.State.UNAFFECTED, () -> COPPER_PLATE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_STAIRS_EXPOSED = BLOCKS.register("copper_plate_stairs_exposed",
            ()-> new WeatheringStairBlock(IWeatheringBlock.State.EXPOSED, () -> COPPER_PLATE_BLOCK_EXPOSED.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_STAIRS_WEATHERED = BLOCKS.register("copper_plate_stairs_weathered",
            ()-> new WeatheringStairBlock(IWeatheringBlock.State.WEATHERED, () -> COPPER_PLATE_BLOCK_WEATHERED.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_STAIRS_OXIDIZED = BLOCKS.register("copper_plate_stairs_oxidized",
            ()-> new WeatheringStairBlock(IWeatheringBlock.State.OXIDIZED, () -> COPPER_PLATE_BLOCK_OXIDIZED.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_STAIRS_WAXED = BLOCKS.register("copper_plate_stairs_waxed",
            ()-> new WeatheringStairBlock(IWeatheringBlock.State.UNAFFECTED, () -> COPPER_PLATE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_STAIRS_EXPOSED_WAXED = BLOCKS.register("copper_plate_stairs_exposed_waxed",
            ()-> new WeatheringStairBlock(IWeatheringBlock.State.EXPOSED, () -> COPPER_PLATE_BLOCK_EXPOSED.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_STAIRS_WEATHERED_WAXED = BLOCKS.register("copper_plate_stairs_weathered_waxed",
            ()-> new WeatheringStairBlock(IWeatheringBlock.State.WEATHERED, () -> COPPER_PLATE_BLOCK_WEATHERED.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> COPPER_PLATE_STAIRS_OXIDIZED_WAXED = BLOCKS.register("copper_plate_stairs_oxidized_waxed",
            ()-> new WeatheringStairBlock(IWeatheringBlock.State.OXIDIZED, () -> COPPER_PLATE_BLOCK_OXIDIZED.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(COPPER_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> ENCLOSED_AXLE = BLOCKS.register("enclosed_axle",
            ()-> new EnclosedAxleBlock(Block.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(1.8F, 2.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> FIRE_BRICKS = BLOCKS.register("fire_bricks",
            ()-> new Block(Block.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(2.0F, 6.0F)
            ));
    public static final RegistryObject<Block> FIRE_BRICKS_SLAB = BLOCKS.register("fire_bricks_slab",
            ()-> new SlabBlock(Block.Properties.copy(FIRE_BRICKS.get())
                    .requiresCorrectToolForDrops()
                    .strength(2.0F, 6.0F)
            ));
    public static final RegistryObject<Block> FIRE_BRICKS_STAIRS = BLOCKS.register("fire_bricks_stairs",
            ()-> new StairBlock(() -> FIRE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(FIRE_BRICKS.get())
                            .requiresCorrectToolForDrops()
                            .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> FIRE_BRICKS_WALL = BLOCKS.register("fire_bricks_wall",
            ()-> new WallBlock(Block.Properties.copy(FIRE_BRICKS.get())
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> FILTER = BLOCKS.register("filter",
            () -> new FilterBlock(Block.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(1.2F, 2.0F)
                    .sound(SoundType.METAL)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> FLAXPLANT = BLOCKS.register("flaxplant",
            ()-> new FlaxPlantBlock(Block.Properties
                    .copy(Blocks.WHEAT)
                    .noOcclusion()
                    .noCollission()
            ));
    public static final RegistryObject<Block> FLUXSTONE = BLOCKS.register("fluxstone",
            ()-> new Block(Block.Properties.of()
                    .mapColor(MapColor.QUARTZ)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 5.0f)
                    .sound(SoundType.DEEPSLATE)
            ));
    public static final RegistryObject<Block> FLUXSTONE_SLAB = BLOCKS.register("fluxstone_slab",
            ()-> new SlabBlock(Block.Properties.copy(FLUXSTONE.get())
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> FLUXSTONE_STAIRS = BLOCKS.register("fluxstone_stairs",
            ()-> new StairBlock(() -> FLUXSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(FLUXSTONE.get())
                            .requiresCorrectToolForDrops()
                            .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> FLUXSTONE_WALL = BLOCKS.register("fluxstone_wall",
            ()-> new WallBlock(Block.Properties.copy(FLUXSTONE.get())
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> GEARBOX = BLOCKS.register("gearbox",
            ()-> new GearboxBlock(Block.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(1.8F, 2.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> GOLD_PLATE_BLOCK = BLOCKS.register("gold_plate_block",
            ()-> new RotatedPillarBlock(Block.Properties.copy(Blocks.GOLD_BLOCK)
            ));
    public static final RegistryObject<Block> GOLD_PLATE_SLAB = BLOCKS.register("gold_plate_slab",
            ()-> new SlabBlock(Block.Properties.copy(AllBlocks.GOLD_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> GOLD_PLATE_STAIRS = BLOCKS.register("gold_plate_stairs",
            ()-> new StairBlock(() -> GOLD_PLATE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(GOLD_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> HIGH_SPEED_RAIL = BLOCKS.register("high_speed_rail",
            ()-> new HighSpeedRailBlock(Block.Properties.of()
                    .noCollission()
                    .strength(1.05F)
                    .sound(SoundType.COPPER)
            ));
    public static final RegistryObject<Block> HIGH_SPEED_ACTIVATOR_RAIL = BLOCKS.register("high_speed_activator_rail",
            ()-> new HighSpeedActivatorRailBlock(Block.Properties.copy(HIGH_SPEED_RAIL.get())
            ));
    public static final RegistryObject<Block> HIGH_SPEED_BUFFER_STOP_RAIL = BLOCKS.register("high_speed_buffer_stop_rail",
            ()-> new BufferStopRailBlock(Block.Properties.of()
                    .strength(1.4F)
                    .sound(SoundType.COPPER)
                    .noOcclusion()
                    .isRedstoneConductor(AllBlocks::always)
            ));
    public static final RegistryObject<Block> HIGH_SPEED_CHIME_RAIL = BLOCKS.register("high_speed_chime_rail",
            ()-> new HighSpeedChimeRailBlock(Block.Properties.copy(HIGH_SPEED_RAIL.get())
            ));
    public static final RegistryObject<Block> HIGH_SPEED_CROSSOVER_RAIL = BLOCKS.register("high_speed_crossover_rail",
            ()-> new HighSpeedCrossoverRailBlock(Block.Properties.copy(HIGH_SPEED_RAIL.get())
            ));
    public static final RegistryObject<Block> HIGH_SPEED_DETECTOR_RAIL = BLOCKS.register("high_speed_detector_rail",
            ()-> new HighSpeedDetectorRailBlock(Block.Properties.copy(HIGH_SPEED_RAIL.get())
            ));
    public static final RegistryObject<Block> HIGH_SPEED_POWERED_RAIL = BLOCKS.register("high_speed_powered_rail",
            ()-> new HighSpeedPoweredRailBlock(Block.Properties.copy(HIGH_SPEED_RAIL.get())
            ));
    public static final RegistryObject<Block> HIGH_SPEED_LIMITER_RAIL = BLOCKS.register("high_speed_limiter_rail",
            ()-> new LimiterRailBlock(true, Block.Properties.copy(HIGH_SPEED_RAIL.get())
            ));
    public static final RegistryObject<Block> HIGH_SPEED_LOCKING_RAIL = BLOCKS.register("high_speed_locking_rail",
            ()-> new LockingRailBlock(true, Block.Properties.copy(HIGH_SPEED_RAIL.get())
            ));
    public static final RegistryObject<Block> HIGH_SPEED_ONE_WAY_RAIL = BLOCKS.register("high_speed_one_way_rail",
            ()-> new OneWayRailBlock(true, Block.Properties.copy(HIGH_SPEED_RAIL.get())
            ));
    public static final RegistryObject<Block> IRON_LADDER = BLOCKS.register("iron_ladder",
            ()-> new LadderBlock(Block.Properties.of()
                    .forceSolidOff()
                    .strength(0.7F, 6.0F)
                    .sound(SoundType.METAL)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
            ));
    public static final RegistryObject<Block> IRON_PLATE_BLOCK = BLOCKS.register("iron_plate_block",
            ()-> new RotatedPillarBlock(Block.Properties
                    .copy(Blocks.IRON_BLOCK)
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
            ));
    public static final RegistryObject<Block> IRON_PLATE_SLAB = BLOCKS.register("iron_plate_slab",
            ()-> new SlabBlock(Block.Properties.copy(IRON_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> IRON_PLATE_STAIRS = BLOCKS.register("iron_plate_stairs",
            ()-> new StairBlock(() -> IRON_PLATE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(IRON_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> IRON_SCAFFOLDING = BLOCKS.register("iron_scaffolding",
            ()-> new MetalScaffoldBlock(13, Block.Properties.of()
                    .mapColor(MapColor.METAL)
                    .noCollission()
                    .strength(0.4F, 6.0F)
                    .sound(SoundType.METAL)
                    .dynamicShape()
                    .isValidSpawn(AllBlocks::never)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(AllBlocks::never)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK = BLOCKS.register("linen_block",
            ()-> new LinenBlock(Block.Properties.of()
                    .mapColor(MapColor.WOOL)
                    .instrument(NoteBlockInstrument.FLUTE)
                    .strength(0.8f, 2.0f)
                    .sound(SoundType.WOOL)
                    .ignitedByLava()
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_WHITE = BLOCKS.register("linen_block_white",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.SNOW)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_ORANGE = BLOCKS.register("linen_block_orange",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_ORANGE)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_MAGENTA = BLOCKS.register("linen_block_magenta",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_MAGENTA)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_LIGHT_BLUE = BLOCKS.register("linen_block_light_blue",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_YELLOW = BLOCKS.register("linen_block_yellow",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_YELLOW)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_LIME = BLOCKS.register("linen_block_lime",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_PINK = BLOCKS.register("linen_block_pink",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_PINK)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_GRAY = BLOCKS.register("linen_block_gray",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_GRAY)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_LIGHT_GRAY = BLOCKS.register("linen_block_light_gray",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_LIGHT_GRAY)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_CYAN = BLOCKS.register("linen_block_cyan",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_CYAN)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_PURPLE = BLOCKS.register("linen_block_purple",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_PURPLE)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_BLUE = BLOCKS.register("linen_block_blue",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_BLUE)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_BROWN = BLOCKS.register("linen_block_brown",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_BROWN)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_GREEN = BLOCKS.register("linen_block_green",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_GREEN)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_RED = BLOCKS.register("linen_block_red",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_RED)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK_BLACK = BLOCKS.register("linen_block_black",
            ()-> new LinenBlock(Block.Properties.copy(LINEN_BLOCK.get())
                    .mapColor(MapColor.COLOR_BLACK)
            ));
    public static final RegistryObject<Block> LIMITER_RAIL = BLOCKS.register("limiter_rail",
            ()-> new LimiterRailBlock(false, Block.Properties.of()
                    .noCollission()
                    .strength(0.7F)
                    .sound(SoundType.METAL)
            ));
    public static final RegistryObject<Block> LOCKING_RAIL = BLOCKS.register("locking_rail",
            ()-> new LockingRailBlock(false, Block.Properties.of()
                    .noCollission()
                    .strength(0.7F)
                    .sound(SoundType.METAL)
            ));
    public static final RegistryObject<Block> MILLSTONE = BLOCKS.register("millstone",
            ()-> new MillstoneBlock(Block.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.8F, 3.0F)
                    .sound(SoundType.STONE)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> ONE_WAY_RAIL = BLOCKS.register("one_way_rail",
            ()-> new OneWayRailBlock(false, Block.Properties.of()
                    .noCollission()
                    .strength(0.7F)
                    .sound(SoundType.METAL)
            ));
    public static final RegistryObject<Block> POLISHED_FLUXSTONE = BLOCKS.register("polished_fluxstone",
            ()-> new RotatedPillarBlock(Block.Properties.copy(FLUXSTONE.get())
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> POLISHED_FLUXSTONE_SLAB = BLOCKS.register("polished_fluxstone_slab",
            ()-> new SlabBlock(Block.Properties.copy(POLISHED_FLUXSTONE.get())
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> POLISHED_FLUXSTONE_STAIRS = BLOCKS.register("polished_fluxstone_stairs",
            ()-> new StairBlock(() -> POLISHED_FLUXSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(POLISHED_FLUXSTONE.get())
                            .requiresCorrectToolForDrops()
                            .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> POLISHED_FLUXSTONE_WALL = BLOCKS.register("polished_fluxstone_wall",
            ()-> new WallBlock(Block.Properties.copy(POLISHED_FLUXSTONE.get())
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> RAW_TIN_BLOCK = BLOCKS.register("raw_tin_block",
            ()-> new Block(Block.Properties.of()
                    .mapColor(MapColor.RAW_IRON)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(5.0F, 6.0F)
            ));
    public static final RegistryObject<Block> ROLLING_MILL = BLOCKS.register("rolling_mill",
            ()-> new RollingMillBlock(Block.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(2.8F, 3.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> TIN_BLOCK = BLOCKS.register("tin_block",
            ()-> new Block(Block.Properties.of()
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.METAL)
            ));
    public static final RegistryObject<Block> TIN_PLATE_BLOCK = BLOCKS.register("tin_plate_block",
            ()-> new RotatedPillarBlock(Block.Properties.copy(AllBlocks.TIN_BLOCK.get())
            ));
    public static final RegistryObject<Block> TIN_PLATE_SLAB = BLOCKS.register("tin_plate_slab",
            ()-> new SlabBlock(Block.Properties.copy(AllBlocks.TIN_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> TIN_PLATE_STAIRS = BLOCKS.register("tin_plate_stairs",
            ()-> new StairBlock(() -> TIN_PLATE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(TIN_PLATE_BLOCK.get())
            ));
    public static final RegistryObject<Block> TRIP_HAMMER = BLOCKS.register("trip_hammer",
            ()-> new TripHammerBlock(Block.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(2.8F, 3.0F)
                    .sound(SoundType.ANVIL)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> TIN_ORE = BLOCKS.register("tin_ore",
            ()-> new DropExperienceBlock(Block.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F) //, UniformInt.of(0, 1)
            ));
    public static final RegistryObject<Block> VERMILION_BLOCK = BLOCKS.register("vermilion_block",
            ()-> new PoweredBlock(Block.Properties.of()
                    .mapColor(MapColor.FIRE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
                    .lightLevel( (state) -> { return 7; })
                    .isRedstoneConductor(AllBlocks::never)
            ));
    public static final RegistryObject<Block> VERMILION_PRESSURE_PLATE = BLOCKS.register("vermilion_pressure_plate",
            ()-> new PressablePlateBlock(PressablePlateBlock.Sensitivity.PLAYERS, BlockSetType.IRON, Block.Properties.of()
                    .mapColor(MapColor.FIRE)
                    .forceSolidOn()
                    .requiresCorrectToolForDrops()
                    .noCollission().strength(0.5F)
                    .pushReaction(PushReaction.DESTROY)
            ));
    public static final RegistryObject<Block> WATER_WHEEL = BLOCKS.register("water_wheel",
            ()-> new WaterWheelBlock(Block.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(1.8F, 2.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> WIND_WHEEL = BLOCKS.register("wind_wheel",
            ()-> new WindWheelBlock(Block.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(1.8F, 2.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> WOODEN_FRAME = BLOCKS.register("wooden_frame",
            ()-> new Block(Block.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            ));
    public static final RegistryObject<Block> WOODEN_FRAME_SLAB = BLOCKS.register("wooden_frame_slab",
            ()-> new SlabBlock(Block.Properties.copy(WOODEN_FRAME.get())
                    .strength(2.0F, 3.0F)
            ));
    public static final RegistryObject<Block> WOODEN_FRAME_STAIRS = BLOCKS.register("wooden_frame_stairs",
            ()-> new StairBlock(() -> WOODEN_FRAME.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(WOODEN_FRAME.get())
                            .strength(2.0F, 3.0F)
            ));

    public static void registerAll(IEventBus modEventBus) { BLOCKS.register(modEventBus); }

    public static String getName(Block block) { return ForgeRegistries.BLOCKS.getKey(block).getPath(); }

    public static boolean always(BlockState state, BlockGetter blockGetter, BlockPos pos) { return true; }
    public static Boolean always(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entityType) { return (boolean)true; }
    public static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos) { return false; }
    public static Boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entityType) { return (boolean)false; }

    protected static ToIntFunction<BlockState> litBlockEmission(int lightLevel)
    {
        return (state) -> { return state.getValue(BlockStateProperties.LIT) ? lightLevel : 0; };
    }
}
