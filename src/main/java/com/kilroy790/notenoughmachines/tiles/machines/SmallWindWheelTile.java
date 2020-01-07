package com.kilroy790.notenoughmachines.tiles.machines;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;




public class SmallWindWheelTile extends TileEntity {

	public SmallWindWheelTile() {
		super(TileEntityList.SMALLWINDWHEEL);
		
	}
	
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		//TODO make the aabb determined on the windsheel's direction
		//this works for the moment but it could be made more efficient as the aabb is a large cube around where the wind wheel could be
		AxisAlignedBB boundingBox = new AxisAlignedBB((double)pos.getX() - (7.0f * 16.0f), (double)pos.getY() - (7.0f * 16.0f), (double)pos.getZ() - (7.0f * 16.0f), 
													  (double)pos.getX() + (7.0f * 16.0f), (double)pos.getY() + (7.0f * 16.0f), (double)pos.getZ() + (7.0f * 16.0f));
		return boundingBox;
	}
}
