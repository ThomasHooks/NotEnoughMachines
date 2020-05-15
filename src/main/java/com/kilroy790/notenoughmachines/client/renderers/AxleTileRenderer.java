package com.kilroy790.notenoughmachines.client.renderers;

import com.kilroy790.notenoughmachines.blocks.machines.MechanicalShaftBlock;
import com.kilroy790.notenoughmachines.client.models.AxleModel;
import com.kilroy790.notenoughmachines.tiles.machines.power.AxleTile;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;




public class AxleTileRenderer extends TileEntityRenderer<AxleTile> {

	private AxleModel model = new AxleModel();
	private static final float SCALE = 0.0625f;
	private static final ResourceLocation texture = new ResourceLocation("notenoughtmachines", "textures/block/axel_tesr.png");
	
	private float angle = 0.0f;
	
	
	
	@Override
	public void render(AxleTile te, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();

		GlStateManager.translatef((float)x, (float)y, (float)z);
		
		GlStateManager.translatef(0.5f, 0.5f, 0.5f);
		switch(te.getBlockState().get(MechanicalShaftBlock.AXIS)) {
		
		case X:
			GlStateManager.rotatef(90.0f, 0.0f, 1.0f, 0.0f);
			break;
			
		case Y:
			GlStateManager.rotatef(90.0f, 1.0f, 0.0f, 0.0f);
			break;
			
		case Z:
			GlStateManager.rotatef(0.0f, 0.0f, 1.0f, 0.0f);
			break;
		}
		GlStateManager.translatef(-0.5f, -0.5f, -0.5f);
		
		//TODO: move this into the Axle tile and pass in the angle/speed factor instead
		angle += 0.0075f;
		if(angle > 360.0f) angle = 0.0f;
		this.model.rotate(0.0f, 0.0f, angle);
		
		this.bindTexture(texture);
		this.model.render(SCALE);
		
		GlStateManager.popMatrix();
	}
}
