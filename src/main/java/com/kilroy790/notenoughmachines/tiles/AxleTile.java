package com.kilroy790.notenoughmachines.tiles;

import com.kilroy790.notenoughmachines.power.MechanicalType;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class AxleTile extends MechanicalTile {
	
	public AxleTile() {
		super(72, 0, MechanicalType.SHAFT, NEMTiles.AXLE.get());
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos()).grow(1.0D);
	}
}







