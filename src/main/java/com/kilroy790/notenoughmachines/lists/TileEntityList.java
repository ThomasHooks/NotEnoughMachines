package com.kilroy790.notenoughmachines.lists;

import com.kilroy790.notenoughmachines.tiles.MillstoneTile;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class TileEntityList {

	@ObjectHolder("notenoughtmachines:millstone")
	public static TileEntityType<MillstoneTile> MILLSTONE_TILE;
	
	@ObjectHolder("notenoughtmachines:axel")
	public static TileEntityType<MillstoneTile> AXEL_TILE;
}
