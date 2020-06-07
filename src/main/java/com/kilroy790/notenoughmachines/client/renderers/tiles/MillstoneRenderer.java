package com.kilroy790.notenoughmachines.client.renderers.tiles;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.client.renderers.NEMTextures;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.processing.MillstoneTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;




@OnlyIn(Dist.CLIENT)
public class MillstoneRenderer extends TileEntityRenderer<MillstoneTile> {

	private final ModelRenderer shaft;
	private final ModelRenderer runnerstone;
	
	public MillstoneRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
		this.shaft = new ModelRenderer(16, 20, 0, 0);
		this.shaft.addBox(6.0f, 0.0f, 6.0f, 4.0f, 16.0f, 4.0f, 0.0f);
		this.runnerstone = new ModelRenderer(56, 18, 0, 0);
		this.runnerstone.addBox(1.0f, 6.0f, 1.0f, 14.0f, 4.0f, 14.0f);
	}

	
	
	@SuppressWarnings("deprecation")
	@Override
	public void render(MillstoneTile tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		
		matrixStack.push();
		
		matrixStack.translate(0.5D, 0.5D, 0.5D);
		float tick = NotEnoughMachines.proxy.getClientTick();
		float speed = tile.getSpeed();
		float angle = (tick * speed * 0.3f) % 360;
		angle = angle/180f * (float)Math.PI;
		matrixStack.rotate(Vector3f.YP.rotation(-angle));
		matrixStack.translate(-0.5D, -0.5D, -0.5D);
		
		Material shaftMaterial = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, NEMTextures.AXLE);
		IVertexBuilder shaftVertexBuilder = shaftMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		this.shaft.render(matrixStack, shaftVertexBuilder, combinedLight, combinedOverlay);
		
		Material runnerMaterial = new Material(AtlasTexture.LOCATION_BLOCKS_TEXTURE, NEMTextures.RUNNERSTONE);
		IVertexBuilder runnerVertexBuilder = runnerMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		this.runnerstone.render(matrixStack, runnerVertexBuilder, combinedLight, combinedOverlay);
		
		matrixStack.pop();
	}
	
	
	
	public static void register() {
		ClientRegistry.bindTileEntityRenderer(NEMTiles.MILLSTONE.get(), MillstoneRenderer::new);
	}
}







