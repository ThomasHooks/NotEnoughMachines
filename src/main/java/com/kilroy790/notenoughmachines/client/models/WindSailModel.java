package com.kilroy790.notenoughmachines.client.models;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@OnlyIn(Dist.CLIENT)
public class WindSailModel extends Model {
	
	
	protected RendererModel stock;
	protected RendererModel sail;
	protected static final float SAIL_ANGLE = 0.1963f;
	
	
	public WindSailModel() {
		
		this.stock = new RendererModel(this, 0, 0);
		this.stock.setTextureSize(16, 16);
		this.stock.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.stock.addBox(2.0f, -2.0f, -2.0f, 118, 4, 4);

		this.sail = new RendererModel(this, 0, 0);
		this.sail.setTextureSize(198, 19);
		this.sail.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.sail.addBox(24.0f, -18.0f, -1.0f, 96, 16, 3);
	}
	
	
	public void renderAll(float scale) {
		
		this.stock.render(scale);
		
		//this.sail.rotateAngleX = 0.1963f;
		this.sail.render(scale);
	}
	
	
	public void renderStock(float scale) {
		
		this.stock.render(scale);
	}
	
	
	public void renderSail(float scale) {
		
		//this.sail.rotateAngleX = 0.1963f;
		this.sail.render(scale);
	}
	
	
	public void rotate(float x, float y, float z) {
		
		this.stock.rotateAngleX = x;
		this.stock.rotateAngleY = y;
		this.stock.rotateAngleZ = z;
		
		//the entire wind sail must rotate as one structure
		this.sail.rotateAngleX = this.stock.rotateAngleX + SAIL_ANGLE;
		this.sail.rotateAngleY = this.stock.rotateAngleY;
		this.sail.rotateAngleZ = this.stock.rotateAngleZ;
	}
}
