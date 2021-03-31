package com.kilroy790.notenoughmachines.tiles;

import com.kilroy790.notenoughmachines.power.MechanicalType;




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







