package com.kilroy790.notenoughmachines.tiles.machines.power;

import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;




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







