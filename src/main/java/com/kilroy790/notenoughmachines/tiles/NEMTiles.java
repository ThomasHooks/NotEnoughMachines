package com.kilroy790.notenoughmachines.tiles;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.NEMBlocks;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;




public class NEMTiles 
{
	private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, NotEnoughMachines.MODID);
	
	
	
	public static final RegistryObject<TileEntityType<CreativePowerBoxTile>> CREATIVEPOWERBOX = TILES.register("creativepowerbox", 
			() -> TileEntityType.Builder.create(CreativePowerBoxTile::new, NEMBlocks.CREATIVEPOWERBOX.get()).build(null));
	
	
	
	public static final RegistryObject<TileEntityType<SmallWindWheelTile>> SMALLWINDWHEEL = TILES.register("smallwindwheel", 
			() -> TileEntityType.Builder.create(SmallWindWheelTile::new, NEMBlocks.SMALLWINDWHEEL.get()).build(null));
	
	
	
	public static final RegistryObject<TileEntityType<TubWheelTile>> TUBWHEEL = TILES.register("tubwheel", 
			() -> TileEntityType.Builder.create(TubWheelTile::new, NEMBlocks.TUBWHEEL.get()).build(null));
	
	
	
	public static final RegistryObject<TileEntityType<MillstoneTile>> MILLSTONE = TILES.register("millstone", 
			() -> TileEntityType.Builder.create(MillstoneTile::new, NEMBlocks.MILLSTONE.get()).build(null));
	
	
	
	public static final RegistryObject<TileEntityType<TripHammerTile>> TRIPHAMMER = TILES.register("triphammer", 
			() -> TileEntityType.Builder.create(TripHammerTile::new, NEMBlocks.TRIPHAMMER.get()).build(null));
	
	
	
	public static final RegistryObject<TileEntityType<AxleTile>> AXLE = TILES.register("axle", 
			() -> TileEntityType.Builder.create(AxleTile::new, NEMBlocks.AXLE.get()).build(null));
	
	
	
	public static final RegistryObject<TileEntityType<EnclosedAxleTile>> ENCLOSED_AXLE = TILES.register("enclosedaxle", 
			() -> TileEntityType.Builder.create(EnclosedAxleTile::new, NEMBlocks.ENCLOSED_AXLE.get()).build(null));
	
	
	
	public static final RegistryObject<TileEntityType<SmallCogwheelTile>> SMALLCOG = TILES.register("smallcog", 
			() -> TileEntityType.Builder.create(SmallCogwheelTile::new, NEMBlocks.SMALLCOG.get()).build(null));
	
	
	
	public static final RegistryObject<TileEntityType<LargeCogwheelTile>> LARGECOG = TILES.register("largecog", 
			() -> TileEntityType.Builder.create(LargeCogwheelTile::new, NEMBlocks.LARGECOG.get()).build(null));
	
	
	
	public static final RegistryObject<TileEntityType<GearboxTile>> GEARBOX = TILES.register("gearbox", 
			() -> TileEntityType.Builder.create(GearboxTile::new, NEMBlocks.GEARBOX.get()).build(null));
	
	
	
	public static final RegistryObject<TileEntityType<ChuteTile>> CHUTE = TILES.register("chute", 
			() -> TileEntityType.Builder.create(ChuteTile::new, NEMBlocks.CHUTE.get()).build(null));
	
	
	
	public static final RegistryObject<TileEntityType<FilterTile>> FILTER = TILES.register("filter", 
			() -> TileEntityType.Builder.create(FilterTile::new, NEMBlocks.FILTER.get()).build(null));
	
	
	
	public static final RegistryObject<TileEntityType<ItemPusherTile>> ITEMPUSHER = TILES.register("itempusher", 
			() -> TileEntityType.Builder.create(ItemPusherTile::new, NEMBlocks.ITEMPUSHER.get()).build(null));

	
	
	public static void registerAll(IEventBus modEventBus) 
	{
		TILES.register(modEventBus);
	}
}







