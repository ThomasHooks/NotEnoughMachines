package com.kilroy790.notenoughmachines.client.renderers.tiles;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.client.renderers.NEMTextures;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.power.TubWheelTile;
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
public class TubWheelRenderer extends TileEntityRenderer<TubWheelTile> {

	private final ModelRenderer shaft;
	private final ModelRenderer hub;
	private static final int NUMBER_OF_PADDLES = 4;
	private final ModelRenderer paddle[] = new ModelRenderer[NUMBER_OF_PADDLES];
	
	
	
	public TubWheelRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
		this.shaft = new ModelRenderer(16, 20, 0, 0);
		this.shaft.addBox(6.0f, 0.0f, 6.0f, 4.0f, 16.0f, 4.0f, 0.0f);
		this.hub = new ModelRenderer(24, 18, 0, 0);
		this.hub.addBox(5.0f, 1.0f, 5.0f, 6.0f, 12.0f, 6.0f, 0.0f);
		for(int i = 0; i < NUMBER_OF_PADDLES; i++) {
			this.paddle[i] = new ModelRenderer(48, 12, 0, 0);
			this.paddle[i].addBox(-3.0f, 2.0f, 7.0f, 22.0f, 10.0f, 2.0f, 0.0f);
		}
	}

	
	
	@Override
	public void render(TubWheelTile tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		matrixStack.push();
		
		matrixStack.translate(0.5D, 0.5D, 0.5D);
		float tick = NotEnoughMachines.proxy.getClientTick();
		float speed = tile.getSpeed();
		float angle = (tick * speed * 0.3f) % 360;
		angle = angle/180f * (float)Math.PI;
		matrixStack.rotate(Vector3f.YP.rotation(-angle));
		matrixStack.translate(-0.5D, -0.5D, -0.5D);
		
		Material shaftMaterial = new Material(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.AXLE);
		IVertexBuilder shaftVertexBuilder = shaftMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		this.shaft.render(matrixStack, shaftVertexBuilder, combinedLight, combinedOverlay);
		
		Material hubMaterial = new Material(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.TUBWHEEL_HUB);
		IVertexBuilder hubVertexBuilder = hubMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		this.hub.render(matrixStack, hubVertexBuilder, combinedLight, combinedOverlay);
		
		Material paddleMaterial = new Material(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.TUBWHEEL_PADDLE);
		IVertexBuilder paddleVertexBuilder = paddleMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		for(int i = 0; i < NUMBER_OF_PADDLES; i++) {
			matrixStack.translate(0.5D, 0.5D, 0.5D);
			matrixStack.rotate(Vector3f.YP.rotationDegrees(45.0f * (float)i));
			matrixStack.translate(-0.5D, -0.5D, -0.5D);
			this.paddle[i].render(matrixStack, paddleVertexBuilder, combinedLight, combinedOverlay);
		}
		matrixStack.pop();
	}

	
	
	public static void register() {
		ClientRegistry.bindTileEntityRenderer(NEMTiles.TUBWHEEL.get(), TubWheelRenderer::new);
	}
}







