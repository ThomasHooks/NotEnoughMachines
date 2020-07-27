package com.kilroy790.notenoughmachines.client.renderers.tiles;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalBlock;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalShaftBlock;
import com.kilroy790.notenoughmachines.client.renderers.NEMTextures;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.power.AxleTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;




@OnlyIn(Dist.CLIENT)
public class AxleRenderer extends TileEntityRenderer<AxleTile> {

	private final ModelRenderer shaft;
	
	public AxleRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
		this.shaft = new ModelRenderer(16, 20, 0, 0);
		this.shaft.addBox(6.0f, 0.0f, 6.0f, 4.0f, 16.0f, 4.0f, 0.0f);
	}

	
	
	@Override
	public void render(AxleTile tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
		matrixStack.push();
		
		matrixStack.translate(0.5D, 0.5D, 0.5D);
		switch(tile.getBlockState().get(MechanicalShaftBlock.AXIS)) {
		case X:
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90));
			break;
		case Y:
			break;
		case Z:
			matrixStack.rotate(Vector3f.XP.rotationDegrees(90));
			break;
		}
//		float tick = NotEnoughMachines.proxy.getClientTick();
		float tick = NotEnoughMachines.CLIENTTIMER.getTick() + partialTicks;
		float speed = tile.getSpeed();
		float angle = (tick * speed * 0.3f) % 360;
		angle = angle/180f * (float)Math.PI;
		if(tile.getBlockState().get(MechanicalBlock.SHIFTED)) angle += 22.5f/180.0f * (float)Math.PI;
		matrixStack.rotate(Vector3f.YP.rotation(-angle));
		matrixStack.translate(-0.5D, -0.5D, -0.5D);
		
		Material material = new Material(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.AXLE);
		IVertexBuilder vertexbuilder = material.getBuffer(buffer, RenderType::getEntityCutout);
		this.shaft.render(matrixStack, vertexbuilder, combinedLightIn, combinedOverlayIn);
		
		matrixStack.pop();
	}
	
	
	
	public static void register() {
		ClientRegistry.bindTileEntityRenderer(NEMTiles.AXLE.get(), AxleRenderer::new);
	}
}







