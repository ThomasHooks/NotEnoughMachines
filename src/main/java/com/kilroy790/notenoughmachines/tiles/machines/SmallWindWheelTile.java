package com.kilroy790.notenoughmachines.tiles.machines;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;




public class SmallWindWheelTile extends TileEntity implements ITickableTileEntity{

	
	protected float angle;
	
	
	public SmallWindWheelTile() {
		super(TileEntityList.SMALLWINDWHEEL);
		this.angle = 0.0f;
	}
	
	
	@Override
	public void tick() {
		
		this.angle += 4.0f;
		if(this.angle > 360.0f)this.angle = 0.0f;
	}
	
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		//TODO make the aabb determined on the wind wheel's direction
		//this works for the moment but it could be made more efficient as the aabb is a large cube around where the wind wheel could be
		AxisAlignedBB boundingBox = new AxisAlignedBB((double)pos.getX() - (7.0f * 16.0f), (double)pos.getY() - (7.0f * 16.0f), (double)pos.getZ() - (7.0f * 16.0f), 
													  (double)pos.getX() + (7.0f * 16.0f), (double)pos.getY() + (7.0f * 16.0f), (double)pos.getZ() + (7.0f * 16.0f));
		return boundingBox;
	}
	
	
	public float getWindSailAngle() {
		//returns the angle of the wind sail in radians
		return ((float)Math.PI * this.angle)/180.0f;
	}
}
