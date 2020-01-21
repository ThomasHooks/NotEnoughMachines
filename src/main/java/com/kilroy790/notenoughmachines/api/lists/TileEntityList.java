package com.kilroy790.notenoughmachines.api.lists;

import com.kilroy790.notenoughmachines.tiles.machines.AxleTile;
import com.kilroy790.notenoughmachines.tiles.machines.ChuteTile;
import com.kilroy790.notenoughmachines.tiles.machines.CreativePowerBoxTile;
import com.kilroy790.notenoughmachines.tiles.machines.GearboxTile;
import com.kilroy790.notenoughmachines.tiles.machines.MillstoneTile;
import com.kilroy790.notenoughmachines.tiles.machines.SmallWindWheelTile;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;




public class TileEntityList {

	@ObjectHolder("notenoughtmachines:creativepowerbox")
	public static TileEntityType<CreativePowerBoxTile> CREATIVEPOWERBOX;
	
	@ObjectHolder("notenoughtmachines:smallwindwheel")
	public static TileEntityType<SmallWindWheelTile> SMALLWINDWHEEL;
	
	@ObjectHolder("notenoughtmachines:millstone")
	public static TileEntityType<MillstoneTile> MILLSTONE_TILE;
	
	@ObjectHolder("notenoughtmachines:axle")
	public static TileEntityType<AxleTile> AXLE_TILE;
	
	@ObjectHolder("notenoughtmachines:gearbox")
	public static TileEntityType<GearboxTile> GEARBOX;
	
	@ObjectHolder("notenoughtmachines:chute")
	public static TileEntityType<ChuteTile> CHUTE;
}
