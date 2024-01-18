package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, NotEnoughMachines.MOD_ID);

    public static final RegistryObject<BlockEntityType<AxleBlockEntity>> AXLE = BLOCK_ENTITIES.register("axle", () -> BlockEntityType.Builder.of(AxleBlockEntity::new, AllBlocks.AXLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<CokeOvenBlockEntity>> COKE_OVEN = BLOCK_ENTITIES.register("coke_oven", () -> BlockEntityType.Builder.of(CokeOvenBlockEntity::new, AllBlocks.COKE_OVEN.get()).build(null));
    public static final RegistryObject<BlockEntityType<CogwheelLargeBlockEntity>> COGWHEEL_LARGE = BLOCK_ENTITIES.register("large_cogwheel", () -> BlockEntityType.Builder.of(CogwheelLargeBlockEntity::new, AllBlocks.COGWHEEL_LARGE.get()).build(null));
    public static final RegistryObject<BlockEntityType<CogwheelSmallBlockEntity>> COGWHEEL_SMALL = BLOCK_ENTITIES.register("small_cogwheel", () -> BlockEntityType.Builder.of(CogwheelSmallBlockEntity::new, AllBlocks.COGWHEEL_SMALL.get()).build(null));
    public static final RegistryObject<BlockEntityType<EnclosedAxleBlockEntity>> ENCLOSED_AXLE = BLOCK_ENTITIES.register("enclosed_axle", () -> BlockEntityType.Builder.of(EnclosedAxleBlockEntity::new, AllBlocks.ENCLOSED_AXLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<FilterBlockEntity>> FILTER = BLOCK_ENTITIES.register("filter", () -> BlockEntityType.Builder.of(FilterBlockEntity::new, AllBlocks.FILTER.get()).build(null));
    public static final RegistryObject<BlockEntityType<GearboxBlockEntity>> GEARBOX = BLOCK_ENTITIES.register("gearbox", () -> BlockEntityType.Builder.of(GearboxBlockEntity::new, AllBlocks.GEARBOX.get()).build(null));
    public static final RegistryObject<BlockEntityType<LockingRailBlockEntity>> LOCKING_RAIL = BLOCK_ENTITIES.register("locking_rail", () -> BlockEntityType.Builder.of(LockingRailBlockEntity::new, AllBlocks.LOCKING_RAIL.get()).build(null));
    public static final RegistryObject<BlockEntityType<MillstoneBlockEntity>> MILLSTONE = BLOCK_ENTITIES.register("millstone", () -> BlockEntityType.Builder.of(MillstoneBlockEntity::new, AllBlocks.MILLSTONE.get()).build(null));
    public static final RegistryObject<BlockEntityType<RollingMillBlockEntity>> ROLLING_MILL = BLOCK_ENTITIES.register("rolling_mill", () -> BlockEntityType.Builder.of(RollingMillBlockEntity::new, AllBlocks.ROLLING_MILL.get()).build(null));
    public static final RegistryObject<BlockEntityType<SackBlockEntity>> SACK = BLOCK_ENTITIES.register("sack", () -> BlockEntityType.Builder.of(SackBlockEntity::new, AllBlocks.SACK.get(), AllBlocks.SACK_WHITE.get(), AllBlocks.SACK_ORANGE.get(), AllBlocks.SACK_MAGENTA.get(), AllBlocks.SACK_LIGHT_BLUE.get(), AllBlocks.SACK_YELLOW.get(), AllBlocks.SACK_LIME.get(), AllBlocks.SACK_PINK.get(), AllBlocks.SACK_GRAY.get(), AllBlocks.SACK_LIGHT_GRAY.get(), AllBlocks.SACK_CYAN.get(), AllBlocks.SACK_PURPLE.get(), AllBlocks.SACK_BLUE.get(), AllBlocks.SACK_BROWN.get(), AllBlocks.SACK_GREEN.get(), AllBlocks.SACK_RED.get(), AllBlocks.SACK_BLACK.get()).build(null));
    public static final RegistryObject<BlockEntityType<TripHammerBlockEntity>> TRIP_HAMMER = BLOCK_ENTITIES.register("trip_hammer", () -> BlockEntityType.Builder.of(TripHammerBlockEntity::new, AllBlocks.TRIP_HAMMER.get()).build(null));
    public static final RegistryObject<BlockEntityType<WaterWheelBlockEntity>> WATER_WHEEL = BLOCK_ENTITIES.register("water_wheel", () -> BlockEntityType.Builder.of(WaterWheelBlockEntity::new, AllBlocks.WATER_WHEEL.get()).build(null));
    public static final RegistryObject<BlockEntityType<WindWheelBlockEntity>> WIND_WHEEL = BLOCK_ENTITIES.register("wind_wheel", () -> BlockEntityType.Builder.of(WindWheelBlockEntity::new, AllBlocks.WIND_WHEEL.get()).build(null));

    public static void registerAll(IEventBus modEventBus)
    {
        BLOCK_ENTITIES.register(modEventBus);
    }
}
