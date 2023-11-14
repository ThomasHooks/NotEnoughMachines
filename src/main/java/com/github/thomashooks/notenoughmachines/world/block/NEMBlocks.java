package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NEMBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NotEnoughMachines.MOD_ID);

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

    public static final RegistryObject<Block> FLAXPLANT = BLOCKS.register("flaxplant",
            ()-> new FlaxPlantBlock(Block.Properties
                    .copy(Blocks.WHEAT)
                    .noOcclusion()
                    .noCollission()
            ));

    public static final RegistryObject<Block> FLUXSTONE = BLOCKS.register("fluxstone",
            ()-> new Block(Block.Properties
                    .copy(Blocks.DIORITE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 3.0f)
            ));

    public static final RegistryObject<Block> FLUXSTONE_SLAB = BLOCKS.register("fluxstone_slab",
            ()-> new SlabBlock(Block.Properties
                    .copy(Blocks.DIORITE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 3.0f)
            ));

    public static final RegistryObject<Block> FLUXSTONE_STAIRS = BLOCKS.register("fluxstone_stairs",
            ()-> new StairBlock(() -> NEMBlocks.FLUXSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .copy(Blocks.DIORITE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5f, 3.0f)
            ));

    public static final RegistryObject<Block> FLUXSTONE_WALL = BLOCKS.register("fluxstone_wall",
            ()-> new WallBlock(Block.Properties
                    .copy(Blocks.DIORITE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 3.0f)
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

    public static final RegistryObject<Block> POLISHED_FLUXSTONE = BLOCKS.register("polished_fluxstone",
            ()-> new Block(Block.Properties
                    .copy(Blocks.POLISHED_DIORITE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 3.0f)
            ));

    public static final RegistryObject<Block> POLISHED_FLUXSTONE_SLAB = BLOCKS.register("polished_fluxstone_slab",
            ()-> new SlabBlock(Block.Properties
                    .copy(Blocks.DIORITE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 3.0f)
            ));

    public static final RegistryObject<Block> POLISHED_FLUXSTONE_STAIRS = BLOCKS.register("polished_fluxstone_stairs",
            ()-> new StairBlock(() -> NEMBlocks.POLISHED_FLUXSTONE.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .copy(Blocks.DIORITE)
                            .requiresCorrectToolForDrops()
                            .strength(1.5f, 3.0f)
            ));

    public static final RegistryObject<Block> POLISHED_FLUXSTONE_WALL = BLOCKS.register("polished_fluxstone_wall",
            ()-> new WallBlock(Block.Properties
                    .copy(Blocks.DIORITE)
                    .requiresCorrectToolForDrops()
                    .strength(1.5f, 3.0f)
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
