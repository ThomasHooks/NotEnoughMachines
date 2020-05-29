package com.kilroy790.notenoughmachines.client.models;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@OnlyIn(Dist.CLIENT)
public class MillstoneRunnerModel extends Model {

	protected RendererModel runnerStone;
	
	public MillstoneRunnerModel() {
		this.runnerStone = new RendererModel(this, 0, 0);
		this.runnerStone.setTextureSize(56, 18);
		this.runnerStone.addBox(-7.0f, -2.0f, -7.0f, 14, 4, 14);
		this.runnerStone.rotationPointX = 8.0f;
		this.runnerStone.rotationPointY = 8.0f;
		this.runnerStone.rotationPointZ = 0.0f;
	}
	
	
	
	public void render(float scale) {
		this.runnerStone.render(scale);
	}
	
	
	
	public void translate(float x, float y, float z) {
		this.runnerStone.offsetX = x;
		this.runnerStone.offsetY = y;
		this.runnerStone.offsetZ = z;
	}
	
	
	
	public void rotate(float x, float y, float z) {
		this.runnerStone.rotateAngleX = x;
		this.runnerStone.rotateAngleY = y;
		this.runnerStone.rotateAngleZ = z;
	}
}







