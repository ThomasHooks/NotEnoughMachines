package com.kilroy790.notenoughmachines.client.renderers;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.machines.power.GearboxBlock;
import com.kilroy790.notenoughmachines.client.models.AxleModel;
import com.kilroy790.notenoughmachines.tiles.machines.power.GearboxTile;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@OnlyIn(Dist.CLIENT)
public class GearboxRenderer extends TileEntityRenderer<GearboxTile> {

	private AxleModel model = new AxleModel();
	private AxleModel model2 = new AxleModel();
	private static final float SCALE = 0.0625f;
	private static final ResourceLocation texture = new ResourceLocation("notenoughtmachines", "textures/block/axel_tesr.png");
	
	
	
	@Override
	public void render(GearboxTile tile, double x, double y, double z, float partialTicks, int destroyStage) {
		
		GlStateManager.pushMatrix();
		GlStateManager.translatef((float)x, (float)y, (float)z);
		
		float tick = NotEnoughMachines.proxy.getClientTick();
		float speed = tile.getSpeed();
		float angle = (tick * speed * 0.3f) % 360;
		angle = angle/180f * (float)Math.PI;
		
		GlStateManager.translatef(0.5f, 0.5f, 0.5f);
		switch(tile.getBlockState().get(GearboxBlock.AXIS)) {
		
		case X:
			this.model.rotate(0.0f, 0.0f, angle);
			this.model2.rotate(0.5f * (float)Math.PI, angle, 0.0f);
			this.model2.translate(0.0f, 0.5f, 0.5f);
			break;
			
		case Y:
			this.model.rotate(0.0f, 0.0f, angle);
			this.model2.rotate(0.5f * (float)Math.PI, angle, 0.0f);
			this.model2.translate(0.0f, 0.5f, 0.5f);
			GlStateManager.rotatef(90.0f, 0.0f, 0.0f, 1.0f);
			break;
			
		case Z:
			this.model.rotate(0.0f, 0.0f, angle);
			this.model2.rotate(0.5f * (float)Math.PI, angle, 0.0f);
			this.model2.translate(0.0f, 0.5f, 0.5f);
			GlStateManager.rotatef(90.0f, 0.0f, 1.0f, 0.0f);
			break;
			
		default:
			this.model.rotate(0.0f, 0.0f, angle);
			this.model2.rotate(0.5f * (float)Math.PI, angle, 0.0f);
			this.model2.translate(0.0f, 0.5f, 0.5f);
			GlStateManager.rotatef(90.0f, 0.0f, 0.0f, 1.0f);
			break;
		}
		GlStateManager.translatef(-0.5f, -0.5f, -0.5f);
		
		this.bindTexture(texture);
		this.model.render(SCALE);
		this.model2.render(SCALE);
		
		GlStateManager.popMatrix();
	}
}







