package com.kilroy790.notenoughmachines.tiles.machines.power;

import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;




public class SmallCogwheelTile extends MechanicalTile 
{	
	public SmallCogwheelTile() 
	{
		super(72, 0, MechanicalType.COG, NEMTiles.SMALLCOG.get());
	}
	
	
	
	@Override
	protected float numberOfTeeth() 
	{
		return 8.0f;
	}
}







