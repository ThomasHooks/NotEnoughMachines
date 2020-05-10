package com.kilroy790.notenoughmachines.client.renderers;

import com.kilroy790.notenoughmachines.tiles.machines.logistic.ChuteTile;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class ChuteRenderer extends TileEntityRenderer<ChuteTile> {
	//TODO look into TESR fast renderer instead
	//TODO fix item jittering 
	//TODO change so that if the chute is blocked that it does not continue to scroll items
	
	
	@Override
	public void render(ChuteTile tile, double x, double y, double z, float partialTicks, int destroyStage) {
		
		GlStateManager.pushMatrix();
		
		ItemStack stack = tile.getItemStack();
		if(!stack.isEmpty()) {
			
			switch(tile.getBlockState().get(HorizontalBlock.HORIZONTAL_FACING).getOpposite()) {
			case SOUTH:
				GlStateManager.translated(x + 0.5D, y + 0.25D, z + tile.getItemStackDistance());
				break;
				
			case NORTH:
				GlStateManager.translated(x + 0.5D, y + 0.25D, z + (1.0D - tile.getItemStackDistance()));
				break;
				
			case EAST:
				GlStateManager.translated(x + tile.getItemStackDistance(), y + 0.25D, z + 0.5D);
				break;
				
			case WEST:
				GlStateManager.translated(x + (1.0D - tile.getItemStackDistance()), y + 0.25D, z + 0.5D);
				break;
				
			default:
				//to north
				GlStateManager.translated(x + 0.5D, y + 0.25D, z + (1.0D - tile.getItemStackDistance()));
				break;
			}
			
			//TODO change to use another method instead as this is deprecated
			Minecraft.getInstance().getItemRenderer().renderItem(stack, TransformType.GROUND);
		}
		
		GlStateManager.popMatrix();
	}
}
