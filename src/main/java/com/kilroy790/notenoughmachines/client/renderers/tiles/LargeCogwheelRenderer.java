package com.kilroy790.notenoughmachines.client.renderers.tiles;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalBlock;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalShaftBlock;
import com.kilroy790.notenoughmachines.client.NEMTextures;
import com.kilroy790.notenoughmachines.tiles.LargeCogwheelTile;
import com.kilroy790.notenoughmachines.tiles.NEMTiles;
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
import net.minecraftforge.fml.client.registry.ClientRegistry;




public class LargeCogwheelRenderer extends TileEntityRenderer<LargeCogwheelTile> {

	private final ModelRenderer shaft;
	
	private final ModelRenderer hub;
	
	private final ModelRenderer strut;
	
	private final ModelRenderer rims;
	private final int NUMBER_OF_RIM_SEGMENTS = 16;
	
	private final ModelRenderer teeth;
	private final int NUMBER_OF_TEETH = 16;
	
	public LargeCogwheelRenderer(TileEntityRendererDispatcher rendererDispatcherIn) 
	{
		super(rendererDispatcherIn);
		
		this.shaft = new ModelRenderer(16, 20, 0, 0);
		this.shaft.addBox(6.0f, 0.0f, 6.0f, 4.0f, 16.0f, 4.0f, 0.0f);
		
		this.hub = new ModelRenderer(32, 14, 0, 0);
		this.hub.addBox(4.0f, 5.0f, 4.0f, 8.0f, 6.0f, 8.0f, 0.0f);
		
		this.strut = new ModelRenderer(96, 50, 0, 0);
		this.strut.addBox(2.0f, 6.0f, -15.0f, 2.0f, 4.0f, 46.0f, 0.0f);
		this.strut.addBox(12.0f, 6.0f, -15.0f, 2.0f, 4.0f, 46.0f, 0.0f);
		
		this.rims = new ModelRenderer(24, 6, 0, 0);
		this.rims.addBox(4.0f, 6.0f, -16.0f, 10.0f, 4.0f, 2.0f);
		
		this.teeth = new ModelRenderer(14, 6, 0, 0);
		this.teeth.addBox(6.5f, 7.0f, -20.0f, 3.0f, 2.0f, 4.0f, 0.0f);
	}

	
	
	@Override
	public void render(LargeCogwheelTile tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) 
	{
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
		
		RenderMaterial hubMaterial = new RenderMaterial(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.SMALLCOG_OUT);
		IVertexBuilder hubVertexbuilder = hubMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		this.hub.render(matrixStack, hubVertexbuilder, combinedLightIn, combinedOverlayIn);
		
		RenderMaterial strutMaterial = new RenderMaterial(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.LARGECOG_STRUTS);
		IVertexBuilder strutVertexbuilder = strutMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		this.strut.render(matrixStack, strutVertexbuilder, combinedLightIn, combinedOverlayIn);
		matrixStack.translate(0.5D, 0.5D, 0.5D);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(90.0f));
		matrixStack.translate(-0.5D, -0.5D, -0.5D);
		this.strut.render(matrixStack, strutVertexbuilder, combinedLightIn, combinedOverlayIn);
		
		RenderMaterial rimsMaterial = new RenderMaterial(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.LARGECOG_RIMS);
		IVertexBuilder rimsVertexbuilder = rimsMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		for (int i = 0; i <= NUMBER_OF_RIM_SEGMENTS; i++)
		{
			this.rims.render(matrixStack, rimsVertexbuilder, combinedLightIn, combinedOverlayIn);
			matrixStack.translate(0.5D, 0.5D, 0.5D);
			matrixStack.rotate(Vector3f.YP.rotationDegrees(22.5f * i));
			matrixStack.translate(-0.5D, -0.5D, -0.5D);
		}
		
		RenderMaterial teethMaterial = new RenderMaterial(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.LARGECOG_TEETH);
		IVertexBuilder teethVertexbuilder = teethMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		for (int i = 0; i <= NUMBER_OF_TEETH; i++)
		{
			this.teeth.render(matrixStack, teethVertexbuilder, combinedLightIn, combinedOverlayIn);
			matrixStack.translate(0.5D, 0.5D, 0.5D);
			matrixStack.rotate(Vector3f.YP.rotationDegrees(22.5f * i));
			matrixStack.translate(-0.5D, -0.5D, -0.5D);
		}
		
		matrixStack.pop();
	}
	
	
	
	public static void register() 
	{
		ClientRegistry.bindTileEntityRenderer(NEMTiles.LARGECOG.get(), LargeCogwheelRenderer::new);
	}
}



