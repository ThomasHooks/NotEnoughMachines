package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.block.WaterWheelBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, NotEnoughMachines.MOD_ID);

    public static final RegistryObject<BlockEntityType<AxleBlockEntity>> AXLE = BLOCK_ENTITIES.register("axle",
            () -> BlockEntityType.Builder.of(AxleBlockEntity::new, AllBlocks.AXLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<FilterBlockEntity>> FILTER = BLOCK_ENTITIES.register("filter",
            () -> BlockEntityType.Builder.of(FilterBlockEntity::new, AllBlocks.FILTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<MillstoneBlockEntity>> MILLSTONE = BLOCK_ENTITIES.register("millstone",
            () -> BlockEntityType.Builder.of(MillstoneBlockEntity::new, AllBlocks.MILLSTONE.get()).build(null));
    public static final RegistryObject<BlockEntityType<WaterWheelBlockEntity>> WATER_WHEEL = BLOCK_ENTITIES.register("water_wheel",
            () -> BlockEntityType.Builder.of(WaterWheelBlockEntity::new, AllBlocks.WATER_WHEEL.get()).build(null));

    public static void registerAll(IEventBus modEventBus)
    {
        BLOCK_ENTITIES.register(modEventBus);
    }
}
