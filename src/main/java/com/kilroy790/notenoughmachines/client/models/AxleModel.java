package com.kilroy790.notenoughmachines.client.models;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@OnlyIn(Dist.CLIENT)
public class AxleModel extends Model {

	
	protected RendererModel shaft;
	
	
	public AxleModel() {
		this.shaft = new RendererModel(this, 0, 0);
		this.shaft.setTextureSize(40, 20);
		this.shaft.addBox(-2.0f, -2.0f, 0.0f, 4, 4, 16, 0.0f);
		this.shaft.rotationPointX = 8.0f;
		this.shaft.rotationPointY = 8.0f;
		this.shaft.rotationPointZ = 0.0f;
	}
	
	
	
	public void render(float scale) {
		this.shaft.render(scale);
	}
	
	
	
	public void translate(float x, float y, float z) {
		this.shaft.offsetX += x;
		this.shaft.offsetY += y;
		this.shaft.offsetZ += z;
	}
	
	
	
	public void rotate(float x, float y, float z) {
		this.shaft.rotateAngleX = x;
		this.shaft.rotateAngleY = y;
		this.shaft.rotateAngleZ = z;
	}
}
