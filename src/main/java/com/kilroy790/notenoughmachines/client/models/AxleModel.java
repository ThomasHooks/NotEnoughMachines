package com.kilroy790.notenoughmachines.client.models;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@OnlyIn(Dist.CLIENT)
public class AxleModel extends Model {

	
	protected RendererModel shaft = (new RendererModel(this, 5, 0)).setTextureSize(4, 16);
	
	
	public AxleModel() {
		
		this.shaft.addBox(6.0f, 6.0f, 0.0f, 4, 4, 16);
	}
}
