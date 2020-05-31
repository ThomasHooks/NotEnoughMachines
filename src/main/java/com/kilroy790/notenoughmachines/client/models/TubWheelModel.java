package com.kilroy790.notenoughmachines.client.models;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@OnlyIn(Dist.CLIENT)
public class TubWheelModel extends Model {

	private RendererModel mid;
	private RendererModel tirl[] = new RendererModel[4];
	
	
	
	public TubWheelModel() {
		
		this.mid = new RendererModel(this, 0, 0);
		this.mid.setTextureSize(24, 18);
		this.mid.addBox(-3.0f, 3.0f, -3.0f, 6, 12, 6);
		this.mid.rotationPointX = 8.0f;
		this.mid.rotationPointY = 8.0f;
		this.mid.rotationPointZ = 0.0f;
		
		for(int i = 0; i < 4; i++) {
			this.tirl[i] = new RendererModel(this, 0, 0);
			this.tirl[i].setTextureSize(48, 12);
			this.tirl[i].addBox(-11.0f, 4.0f, -1.0f, 22, 10, 2);
			this.tirl[i].rotationPointX = 8.0f;
			this.tirl[i].rotationPointY = 8.0f;
			this.tirl[i].rotationPointZ = 0.0f;
		}
	}
	
	
	
	public void renderMid(float scale) {
		this.mid.render(scale);
	}
	
	
	
	public void renderTirl(float scale) {
		for(int i = 0; i < 4; i++) {
			this.tirl[i].render(scale);
		}
	}
	
	
	
	public void translate(float x, float y, float z) {
		
		this.mid.offsetX = x;
		this.mid.offsetY = y;
		this.mid.offsetZ = z;
				
		for(int i = 0; i < 4; i++) {
			this.tirl[i].offsetX = x;
			this.tirl[i].offsetY = y;
			this.tirl[i].offsetZ = z;
		}
	}
	
	
	
	public void rotate(float x, float y, float z) {
		
		this.mid.rotateAngleX = x;
		this.mid.rotateAngleY = y;
		this.mid.rotateAngleZ = z;
		
		for(int i = 0; i < 4; i++) {
			this.tirl[i].rotateAngleX = x;
			this.tirl[i].rotateAngleY = y;
			this.tirl[i].rotateAngleZ = z + (0.25f * (float)i * (float)Math.PI);
		}
	}
}







