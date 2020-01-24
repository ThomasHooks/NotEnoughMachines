package com.kilroy790.notenoughmachines.client.renderers;

import com.kilroy790.notenoughmachines.tiles.machines.ChuteTile;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class ChuteRenderer extends TileEntityRenderer<ChuteTile> {
	//TODO use TESR fast renderer instead
	
	
	@Override
	public void render(ChuteTile tile, double x, double y, double z, float partialTicks, int destroyStage) {
		
		GlStateManager.pushMatrix();
		
		//Direction facing = tile.getBlockState().get(AbstractGeneratorBlock.FACING);
		ItemStack stack = tile.getItemStack();
		if(!stack.isEmpty()) {
			//TODO add directions to the movement of items
			GlStateManager.translated(x + tile.getItemStackDistance(), y + 0.25D, z + 0.5D);
			
			//TODO change to use baked models instead as this method is deprecated
			Minecraft.getInstance().getItemRenderer().renderItem(stack, TransformType.GROUND);
		}
		
		GlStateManager.popMatrix();
	}
}
