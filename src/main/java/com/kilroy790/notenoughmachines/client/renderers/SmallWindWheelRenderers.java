package com.kilroy790.notenoughmachines.client.renderers;

import com.kilroy790.notenoughmachines.blocks.AbstractPowerBlock;
import com.kilroy790.notenoughmachines.client.models.WindSailModel;
import com.kilroy790.notenoughmachines.tiles.machines.SmallWindWheelTile;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@OnlyIn(Dist.CLIENT)
public class SmallWindWheelRenderers extends TileEntityRenderer<SmallWindWheelTile> {
	//TODO use forges TER Fast instead of the regular TER
	
	
	private WindSailModel windSail[] = new WindSailModel[NUMBEROFSAILS];
	private ResourceLocation stockTexture = new ResourceLocation("notenoughtmachines", "textures/block/windsail_stock.png");
	private ResourceLocation sailTexture = new ResourceLocation("notenoughtmachines", "textures/block/windsail.png");
	private static final float SCALE = 0.0625f;
	private static final int NUMBEROFSAILS = 4;
	
	
	@Override
	public void render(SmallWindWheelTile tile, double x, double y, double z, float partialTicks, int destroyStage) {
		
		GlStateManager.pushMatrix();

		GlStateManager.translated(x + 0.5D, y + 0.5D, z + 0.5D);
		
		//Set the small wind wheels' to direction
		Direction facing = tile.getBlockState().get(AbstractPowerBlock.FACING);
		if (facing == Direction.NORTH) GlStateManager.rotatef(180.0f, 1.0f, 0.0f, 0.0f);
		//by default it faces towards the south
		else if (facing == Direction.EAST) GlStateManager.rotatef(90.0f, 0.0f, 1.0f, 0.0f);
		else if (facing == Direction.WEST) GlStateManager.rotatef(-90.0f, 0.0f, 1.0f, 0.0f);
		
		//get the current angle of the wind wheel
		float rotationAngle = tile.getWindSailAngle() + partialTicks * 0.00005f;
		
		//create all 4 wind sails and rotate them around the axle
		//I may move all of this to another model
		for (int i = 0; i < NUMBEROFSAILS; i++) {

			windSail[i] = new WindSailModel();
			
			windSail[i].rotate(0.0f, 0.0f, ((float)Math.PI * i) / 2.0f + rotationAngle);
		}
		
		for (int i = 0; i < NUMBEROFSAILS; i++) {
			//render all wind sails
			this.bindTexture(stockTexture);
			this.windSail[i].renderStock(SCALE);
			
			this.bindTexture(sailTexture);
			this.windSail[i].renderSail(SCALE);
		}
		
		GlStateManager.popMatrix();
	}
}
