package com.kilroy790.notenoughmachines.client.renderers.tiles;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalBlock;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalShaftBlock;
import com.kilroy790.notenoughmachines.client.NEMTextures;
import com.kilroy790.notenoughmachines.tiles.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.SmallCogwheelTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;




@OnlyIn(Dist.CLIENT)
public class SmallCogRenderer extends TileEntityRenderer<SmallCogwheelTile> {

	private final ModelRenderer shaft;
	private final ModelRenderer wheel1;
	private final ModelRenderer wheel2;
	private static final int NUMBER_OF_TEETH = 4;
	private final ModelRenderer tooth[] = new ModelRenderer[NUMBER_OF_TEETH];
	
	public SmallCogRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
		this.shaft = new ModelRenderer(16, 20, 0, 0);
		this.shaft.addBox(6.0f, 0.0f, 6.0f, 4.0f, 16.0f, 4.0f, 0.0f);
		this.wheel1 = new ModelRenderer(32, 14, 0, 0);
		this.wheel1.addBox(4.0f, 5.0f, 4.0f, 8.0f, 6.0f, 8.0f, 0.0f);
		this.wheel2 = new ModelRenderer(36, 13, 0, 0);
		this.wheel2.addBox(3.5f, 6.0f, 3.5f, 9.0f, 4.0f, 9.0f, 0.0f);
		for(int i = 0; i < NUMBER_OF_TEETH; i++) {
			this.tooth[i] = new ModelRenderer(50, 24, 0, 0);
			this.tooth[i].addBox(6.5f, 7.0f, -3.0f, 3.0f, 2.0f, 22.0f, 0.0f);
		}
	}

	
	
	@Override
	public void render(SmallCogwheelTile tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
		matrixStack.push();
		
		matrixStack.translate(0.5D, 0.5D, 0.5D);
		switch (tile.getBlockState().get(MechanicalShaftBlock.AXIS)) {
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
		float tick = NotEnoughMachines.CLIENT_TIMER.getTick() + partialTicks;
		float speed = tile.getSpeed();
		float angle = (tick * speed * 0.3f) % 360;
		angle = angle/180f * (float)Math.PI;
		if(tile.getBlockState().get(MechanicalBlock.SHIFTED)) angle += 22.5f/180.0f * (float)Math.PI;
		matrixStack.rotate(Vector3f.YP.rotation(-angle));
		matrixStack.translate(-0.5D, -0.5D, -0.5D);
		
		RenderMaterial shaftMaterial = new RenderMaterial(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.AXLE);
		IVertexBuilder shaftVertexbuilder = shaftMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		this.shaft.render(matrixStack, shaftVertexbuilder, combinedLightIn, combinedOverlayIn);
		
		RenderMaterial wheel1Material = new RenderMaterial(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.SMALLCOG_OUT);
		IVertexBuilder wheel1Vertexbuilder = wheel1Material.getBuffer(buffer, RenderType::getEntityCutout);
		this.wheel1.render(matrixStack, wheel1Vertexbuilder, combinedLightIn, combinedOverlayIn);
		
		matrixStack.translate(0.5D, 0.5D, 0.5D);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(45.0f));
		matrixStack.translate(-0.5D, -0.5D, -0.5D);
		RenderMaterial wheel2Material = new RenderMaterial(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.SMALLCOG_IN);
		IVertexBuilder wheel2Vertexbuilder = wheel2Material.getBuffer(buffer, RenderType::getEntityCutout);
		this.wheel2.render(matrixStack, wheel2Vertexbuilder, combinedLightIn, combinedOverlayIn);
		
		RenderMaterial toothMaterial = new RenderMaterial(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.SMALLCOG_TOOTH);
		IVertexBuilder toothVertexBuilder = toothMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		for(int i = 0; i < NUMBER_OF_TEETH; i++) {
			matrixStack.translate(0.5D, 0.5D, 0.5D);
			matrixStack.rotate(Vector3f.YP.rotationDegrees(45.0f * (float)i));
			matrixStack.translate(-0.5D, -0.5D, -0.5D);
			this.tooth[i].render(matrixStack, toothVertexBuilder, combinedLightIn, combinedOverlayIn);
		}
		
		matrixStack.pop();
	}
	
	
	
	public static void register() {
		ClientRegistry.bindTileEntityRenderer(NEMTiles.SMALLCOG.get(), SmallCogRenderer::new);
	}
}







