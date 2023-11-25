package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
            ()-> new RotatedPillarBlock(Block.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.COPPER)
            ));
    public static final RegistryObject<Block> ENCLOSED_AXLE = BLOCKS.register("enclosed_axle",
            ()-> new EnclosedAxleBlock(Block.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(1.8F, 2.0F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
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
            ()-> new SlabBlock(Block.Properties
                    .copy(AllBlocks.FLUXSTONE.get())
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> FLUXSTONE_STAIRS = BLOCKS.register("fluxstone_stairs",
            ()-> new StairBlock(() -> AllBlocks.FLUXSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .copy(AllBlocks.FLUXSTONE.get())
                            .requiresCorrectToolForDrops()
                            .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> FLUXSTONE_WALL = BLOCKS.register("fluxstone_wall",
            ()-> new WallBlock(Block.Properties
                    .copy(AllBlocks.FLUXSTONE.get())
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
    public static final RegistryObject<Block> IRON_PLATE_BLOCK = BLOCKS.register("iron_plate_block",
            ()-> new RotatedPillarBlock(Block.Properties
                    .copy(Blocks.IRON_BLOCK)
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.METAL)
            ));
    public static final RegistryObject<Block> LINEN_BLOCK = BLOCKS.register("linen_block",
            ()-> new LinenBlock(Block.Properties.of()
                    .mapColor(MapColor.WOOL)
                    .instrument(NoteBlockInstrument.GUITAR)
                    .strength(0.8f, 2.0f)
                    .sound(SoundType.WOOL)
                    .ignitedByLava()
            ));

    public static final RegistryObject<Block> MILLSTONE = BLOCKS.register("millstone",
            ()-> new MillstoneBlock(Block.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(2.8F, 3.0F)
                    .sound(SoundType.STONE)
                    .noOcclusion()
            ));
    public static final RegistryObject<Block> POLISHED_FLUXSTONE = BLOCKS.register("polished_fluxstone",
            ()-> new RotatedPillarBlock(Block.Properties
                    .copy(AllBlocks.FLUXSTONE.get())
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> POLISHED_FLUXSTONE_SLAB = BLOCKS.register("polished_fluxstone_slab",
            ()-> new SlabBlock(Block.Properties
                    .copy(AllBlocks.FLUXSTONE.get())
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> POLISHED_FLUXSTONE_STAIRS = BLOCKS.register("polished_fluxstone_stairs",
            ()-> new StairBlock(() -> AllBlocks.POLISHED_FLUXSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .copy(AllBlocks.FLUXSTONE.get())
                            .requiresCorrectToolForDrops()
                            .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> POLISHED_FLUXSTONE_WALL = BLOCKS.register("polished_fluxstone_wall",
            ()-> new WallBlock(Block.Properties
                    .copy(AllBlocks.FLUXSTONE.get())
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 5.0f)
            ));
    public static final RegistryObject<Block> TIN_BLOCK = BLOCKS.register("tin_block",
            ()-> new Block(Block.Properties.of()
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.METAL)
            ));
    public static final RegistryObject<Block> TIN_ORE = BLOCKS.register("tin_ore",
            ()-> new DropExperienceBlock(Block.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F) //, UniformInt.of(0, 1)
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

    public static void registerAll(IEventBus modEventBus)
    {
        BLOCKS.register(modEventBus);
    }
}
