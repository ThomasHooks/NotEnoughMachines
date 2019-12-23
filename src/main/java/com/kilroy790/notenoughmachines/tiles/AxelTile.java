package com.kilroy790.notenoughmachines.tiles;

import com.kilroy790.notenoughmachines.lists.TileEntityList;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class AxelTile extends TileEntity implements ITickableTileEntity{

	public AxelTile() {
		super(TileEntityList.AXEL_TILE);
	}

	
	@Override
	public void tick() {
		
	}
}
