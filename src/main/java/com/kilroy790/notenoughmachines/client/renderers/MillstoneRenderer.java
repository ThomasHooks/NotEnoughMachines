package com.kilroy790.notenoughmachines.client.renderers;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.client.models.AxleModel;
import com.kilroy790.notenoughmachines.client.models.MillstoneRunnerModel;
import com.kilroy790.notenoughmachines.tiles.machines.processing.MillstoneTile;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@OnlyIn(Dist.CLIENT)
public class MillstoneRenderer extends TileEntityRenderer<MillstoneTile> {

	private MillstoneRunnerModel runner = new MillstoneRunnerModel();
	private static final ResourceLocation textureRunner = new ResourceLocation("notenoughtmachines", "textures/block/millstone_runner_tesr.png");
	private AxleModel axle = new AxleModel();
	private static final ResourceLocation textureAxle = new ResourceLocation("notenoughtmachines", "textures/block/axel_tesr.png");
	private static final float SCALE = 0.0625f;
	
	
	
	@Override
	public void render(MillstoneTile te, double x, double y, double z, float partialTicks, int destroyStage) {
		
		GlStateManager.pushMatrix();

		GlStateManager.translated(x, y, z);
		GlStateManager.translatef(0.5f, 0.5f, 0.5f);
		this.axle.translate(-0.5f, -0.5f, 0.5f);
		this.runner.translate(-0.5f, -0.5f, 1.0f);
		
		GlStateManager.translatef(0.5f, 0.5f, 0.5f);
		GlStateManager.rotatef(90.0f, 1.0f, 0.0f, 0.0f);
		GlStateManager.translatef(-0.5f, -0.5f, -0.5f);
		
		float tick = NotEnoughMachines.proxy.getClientTick();
		float speed = te.getSpeed();
		float angle = (tick * speed * 0.3f) % 360;
		angle = angle/180f * (float)Math.PI;
		
		this.axle.rotate(0.0f, 0.0f, angle);
		this.runner.rotate(90.0f/180f * (float)Math.PI, 0.0f, angle);
		
		bindTexture(textureAxle);
		this.axle.render(SCALE);
		
		bindTexture(textureRunner);
		this.runner.render(SCALE);
		
		GlStateManager.popMatrix();
	}
}







