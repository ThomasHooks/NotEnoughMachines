package com.kilroy790.notenoughmachines.tiles.machines;

import com.kilroy790.notenoughmachines.power.MechanicalType;

import net.minecraft.tileentity.TileEntityType;




public abstract class MotorTile extends MechanicalTile {

	public MotorTile(int powerCapacity, TileEntityType<?> tileEntityTypeIn) {
		super(powerCapacity, 0, MechanicalType.SOURCE, tileEntityTypeIn);
	}

	
	
	@Override
	protected void tickCustom() {}
}







