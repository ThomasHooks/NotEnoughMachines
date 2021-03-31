package com.kilroy790.notenoughmachines.tiles;

import com.kilroy790.notenoughmachines.power.MechanicalType;




public class CreativePowerBoxTile extends MechanicalTile {
	
	public CreativePowerBoxTile() {
		super(5000, 0, MechanicalType.SOURCE, NEMTiles.CREATIVEPOWERBOX.get());
	}

	
	
	@Override
	public void tick() {
		if (!world.isRemote()) changeSpeed(this, 33.3f);
		super.tick();
	}
}







