package com.kilroy790.notenoughmachines.client.renderers;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.client.models.AxleModel;
import com.kilroy790.notenoughmachines.client.models.TubWheelModel;
import com.kilroy790.notenoughmachines.tiles.machines.power.TubWheelTile;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;



@OnlyIn(Dist.CLIENT)
public class TubWheelRenderer extends TileEntityRenderer<TubWheelTile> {
	
	private AxleModel axle = new AxleModel();
	private static final ResourceLocation textureAxle = new ResourceLocation("notenoughtmachines", "textures/block/axel_tesr.png");
	
	private TubWheelModel model = new TubWheelModel();
	private static final ResourceLocation textureMid = new ResourceLocation("notenoughtmachines", "textures/block/tubwheel_mid.png");
	private static final ResourceLocation textureTirl = new ResourceLocation("notenoughtmachines", "textures/block/tubwheel_tirl.png");
	
	private static final float SCALE = 0.0625f;
	
	@Override
	public void render(TubWheelTile tile, double x, double y, double z, float partialTicks, int destroyStage) {
		
		GlStateManager.pushMatrix();

		GlStateManager.translatef((float)x, (float)y, (float)z);
		
		GlStateManager.translatef(0.5f, 0.5f, 0.5f);
		GlStateManager.rotatef(90.0f, 1.0f, 0.0f, 0.0f);
		GlStateManager.translatef(-0.5f, -0.5f, -0.5f);
		
		float tick = NotEnoughMachines.proxy.getClientTick();
		float speed = tile.getSpeed();
		float angle = (tick * speed * 0.3f) % 360;
		angle = angle/180f * (float)Math.PI;
		
		this.axle.rotate(0.0f, 0.0f, angle);
		this.model.rotate(90.0f/180f * (float)Math.PI, 0.0f, angle);
		
		this.bindTexture(textureAxle);
		this.axle.render(SCALE);
		
		this.bindTexture(textureMid);
		this.model.renderMid(SCALE);
		
		this.bindTexture(textureTirl);
		this.model.renderTirl(SCALE);
		
		GlStateManager.popMatrix();
	}
}







