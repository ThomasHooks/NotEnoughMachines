package com.kilroy790.notenoughmachines.tiles.machines.power;

import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;
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







