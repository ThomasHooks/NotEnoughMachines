package com.kilroy790.notenoughmachines.client.renderers.tiles;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalBlock;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalHorizontalBlock;
import com.kilroy790.notenoughmachines.client.NEMTextures;
import com.kilroy790.notenoughmachines.tiles.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.RollingMillTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.fml.client.registry.ClientRegistry;




public class RollingMillRenderer extends TileEntityRenderer<RollingMillTile>
{
	private static int ROLLER_FAN_COUNT = 4;
	private final ModelRenderer axle;
	private final ModelRenderer roller;
	
	

	public RollingMillRenderer(TileEntityRendererDispatcher rendererDispatcherIn) 
	{
		super(rendererDispatcherIn);
		
		this.axle = new ModelRenderer(16, 20, 0, 0);
		this.axle.addBox(6.0f, 0.0f, 6.0f, 4.0f, 16.0f, 4.0f, 0.0f);
		
		this.roller = new ModelRenderer(32, 18, 0, 0);
		this.roller.addBox(4.0f, 0.0f, 4.0f, 8.0f, 10.0f, 8.0f, 0.0f);
	}

	
	
	@Override
	public void render(RollingMillTile tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) 
	{
		matrixStack.push();
		
		//Move to the center of the top block of the rolling mill
		matrixStack.translate(0.5D, 1.5D, 0.5D);
		switch (tile.getBlockState().get(MechanicalHorizontalBlock.FACING)) 
		{
		case WEST:
		case EAST:
			matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0f));
			break;
			
		case NORTH:
		case SOUTH:
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0f));
			break;

		default:
			throw new IllegalStateException(NotEnoughMachines.MODID + ":rolling mill is in an unknow state!");
		
		}
		
		//Calculate axle and roller rotation angle
		float tick = NotEnoughMachines.CLIENT_TIMER.getTick() + partialTicks;
		float speed = tile.getSpeed();
		float angle = (tick * speed * 0.3f) % 360;
		angle = angle/180f * (float)Math.PI;
		if (tile.getBlockState().get(MechanicalBlock.SHIFTED)) 
			angle += 22.5f/180.0f * (float)Math.PI;
		
		//Render axle
		matrixStack.rotate(Vector3f.YP.rotation(-angle));
		matrixStack.translate(-0.5D, -0.5D, -0.5D);
		RenderMaterial axleMaterial = new RenderMaterial(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.AXLE);
		IVertexBuilder axleVertexbuilder = axleMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		this.axle.render(matrixStack, axleVertexbuilder, combinedLight, combinedOverlay);
		
		//This stops the rotation
		matrixStack.translate(0.5D, 0.5D, 0.5D);
		matrixStack.rotate(Vector3f.YP.rotation(angle));
		matrixStack.translate(-0.5D, -0.5D, -0.5D);
		
		//Render rollers		
		RenderMaterial rollerMaterial = new RenderMaterial(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.ROLLER);
		IVertexBuilder rollerVertexbuilder = rollerMaterial.getBuffer(buffer, RenderType::getEntityCutout);		
		switch (tile.getBlockState().get(MechanicalHorizontalBlock.FACING)) 
		{
		case WEST:
		case EAST:
			renderRollers(angle, 0.0D, 0.1875D, 0.0625D, 0.0D, 0.0D, 0.625D, matrixStack, rollerVertexbuilder, combinedLight, combinedOverlay);
			break;
			
		case NORTH:
		case SOUTH:		
			renderRollers(angle, -0.0625D, 0.1875D, 0.0D, -0.625D, 0.0D, 0.0D, matrixStack, rollerVertexbuilder, combinedLight, combinedOverlay);
			break;

		default:
			throw new IllegalStateException(NotEnoughMachines.MODID + ":rolling mill is in an unknow state!");
		
		}	
		
		matrixStack.pop();
	}
	
	
	
	private void renderRollers(float rotAngle, double topX, double topY, double topZ, double bottomX, double bottomY, double bottomZ, MatrixStack matrixStack, IVertexBuilder vertexbuilder, int combinedLight, int combinedOverlay)
	{
		float fanAngle = 22.5f/180.0f * (float)Math.PI;
		
		matrixStack.translate(topX, topY, topZ);
		matrixStack.translate(0.5D, 0.5D, 0.5D);
		matrixStack.rotate(Vector3f.YP.rotation(-rotAngle));
		matrixStack.translate(-0.5D, -0.5D, -0.5D);
		for (int i = 0; i < ROLLER_FAN_COUNT; i++)
		{
			matrixStack.translate(0.5D, 0.5D, 0.5D);
			matrixStack.rotate(Vector3f.YP.rotation(fanAngle * (float)i));
			matrixStack.translate(-0.5D, -0.5D, -0.5D);
			this.roller.render(matrixStack, vertexbuilder, combinedLight, combinedOverlay);
		}
		
		//This stops the rotation
		matrixStack.translate(0.5D, 0.5D, 0.5D);
		matrixStack.rotate(Vector3f.YP.rotation(rotAngle - fanAngle * ((float)ROLLER_FAN_COUNT + 2.0f)));
		matrixStack.translate(-0.5D, -0.5D, -0.5D);
		
		//Render bottom roller (it counter rotates)
		matrixStack.translate(bottomX, bottomY, bottomZ);
		matrixStack.translate(0.5D, 0.5D, 0.5D);
		matrixStack.rotate(Vector3f.YP.rotation(rotAngle));
		matrixStack.translate(-0.5D, -0.5D, -0.5D);
		for (int i = 0; i < ROLLER_FAN_COUNT; i++)
		{
			matrixStack.translate(0.5D, 0.5D, 0.5D);
			matrixStack.rotate(Vector3f.YP.rotation(fanAngle * (float)i));
			matrixStack.translate(-0.5D, -0.5D, -0.5D);
			this.roller.render(matrixStack, vertexbuilder, combinedLight, combinedOverlay);
		}
	}

	
	
	public static void register() 
	{
		ClientRegistry.bindTileEntityRenderer(NEMTiles.ROLLING_MILL.get(), RollingMillRenderer::new);
	}
}



