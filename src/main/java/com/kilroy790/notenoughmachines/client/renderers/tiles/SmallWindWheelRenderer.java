package com.kilroy790.notenoughmachines.client.renderers.tiles;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalBlock;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalHorizontalBlock;
import com.kilroy790.notenoughmachines.client.renderers.NEMTextures;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.power.SmallWindWheelTile;
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
public class SmallWindWheelRenderer extends TileEntityRenderer<SmallWindWheelTile> {

	private final ModelRenderer shaft;
	private static final int NUMBER_OF_SAILS = 4;
	private final ModelRenderer stock[] = new ModelRenderer[NUMBER_OF_SAILS];
	private final ModelRenderer sail[] = new ModelRenderer[NUMBER_OF_SAILS];
	
	
	
	public SmallWindWheelRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
		this.shaft = new ModelRenderer(16, 20, 0, 0);
		this.shaft.addBox(6.0f, 0.0f, 6.0f, 4.0f, 16.0f, 4.0f, 0.0f);
		for(int i = 0; i < NUMBER_OF_SAILS; i++) {
			this.stock[i] = new ModelRenderer(276, 138, 0, 0);
			this.stock[i].addBox(6.0f, 4.0f, 10.0f, 4.0f, 4.0f, 134.0f, 0.0f);
			this.sail[i] = new ModelRenderer(240, 98, 0, 0);
			this.sail[i].addBox(10.5f, 4.0f, 48.0f, 24.0f, 2.0f, 96.0f, 0.0f);
		}
	}

	
	
	@Override
	public void render(SmallWindWheelTile tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		matrixStack.push();
		
		float tick = NotEnoughMachines.CLIENTTIMER.getTick() + partialTicks;
		float speed = tile.getSpeed();
		float angle = (tick * speed * 0.3f) % 360;
		angle = angle/180f * (float)Math.PI;
		matrixStack.translate(0.5D, 0.5D, 0.5D);
		switch(tile.getBlockState().get(MechanicalHorizontalBlock.FACING)) {
		case EAST:
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90));
			break;
		case NORTH:
			matrixStack.rotate(Vector3f.XP.rotationDegrees(90));
			break;
		case SOUTH:
			matrixStack.rotate(Vector3f.XP.rotationDegrees(-90));
			angle *= -1;
			break;
		case WEST:
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(-90));
			angle *= -1;
			break;
		default:
			break;
		}
		if(tile.getBlockState().get(MechanicalBlock.SHIFTED)) angle += 22.5f/180.0f * (float)Math.PI;
		matrixStack.rotate(Vector3f.YP.rotation(-angle));
		matrixStack.translate(-0.5D, -0.5D, -0.5D);
		
		Material shaftMaterial = new Material(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.AXLE);
		IVertexBuilder shaftVertexbuilder = shaftMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		this.shaft.render(matrixStack, shaftVertexbuilder, combinedLight, combinedOverlay);
		
		Material stockMaterial = new Material(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.WINDSAIL_STOCK);
		IVertexBuilder stockVertexBuilder = stockMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		Material sailMaterial = new Material(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.WINDSAIL_SAIL);
		IVertexBuilder sailVertexBuilder = sailMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		for(int i = 0; i < NUMBER_OF_SAILS; i++) {
			matrixStack.translate(0.5D, 0.5D, 0.5D);
			matrixStack.rotate(Vector3f.YP.rotationDegrees(90.0f * (float)i));
			this.sail[i].rotateAngleZ = 6.0f/180.0f * (float)Math.PI;
			matrixStack.translate(-0.5D, -0.5D, -0.5D);
			this.stock[i].render(matrixStack, stockVertexBuilder, combinedLight, combinedOverlay);
			this.sail[i].render(matrixStack, sailVertexBuilder, combinedLight, combinedOverlay);
		}		
		matrixStack.pop();
	}
	
	
	
	public static void register() {
		ClientRegistry.bindTileEntityRenderer(NEMTiles.SMALLWINDWHEEL.get(), SmallWindWheelRenderer::new);
	}
}







