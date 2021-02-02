package com.kilroy790.notenoughmachines.tiles.machines.power;

import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class LargeCogwheelTile extends MechanicalTile 
{
	public LargeCogwheelTile() 
	{
		super(72, 0, MechanicalType.COG, NEMTiles.LARGECOG.get());
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() 
	{
		return new AxisAlignedBB(getPos()).grow(3.0D);
	}
	
	
	
	@Override
	protected float numberOfTeeth() 
	{
		return 16.0f;
	}
}



