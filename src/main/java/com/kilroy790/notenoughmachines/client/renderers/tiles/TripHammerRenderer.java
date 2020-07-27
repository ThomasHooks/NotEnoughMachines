package com.kilroy790.notenoughmachines.client.renderers.tiles;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalBlock;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalHorizontalBlock;
import com.kilroy790.notenoughmachines.client.renderers.NEMTextures;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.processing.TripHammerTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;




@OnlyIn(Dist.CLIENT)
public class TripHammerRenderer extends TileEntityRenderer<TripHammerTile> {

	private final ModelRenderer hammer;
	private final ModelRenderer shaft;
	private final ModelRenderer axle;
	
	public TripHammerRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
		this.hammer = new ModelRenderer(24, 14, 0, 0);
		this.hammer.addBox(5.0f, 2.0f, 5.0f, 6.0f, 8.0f, 6.0f, 0.0f);
		this.shaft = new ModelRenderer(16, 52, 0, 0);
		this.shaft.addBox(6.0f, 10.0f, 6.0f, 4.0f, 48.0f, 4.0f);
		this.axle = new ModelRenderer(16, 20, 0, 0);
		this.axle.addBox(6.0f, 0.0f, 6.0f, 4.0f, 16.0f, 4.0f, 0.0f);
	}

	
	
	@Override
	public void render(TripHammerTile tile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		matrixStack.push();
		
		double displacement = tile.getDisplacement();
		matrixStack.translate(0.0D, displacement, 0.0D);
		Material hammerMaterial = new Material(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.HAMMER_HEAD);
		IVertexBuilder hammerVertexbuilder = hammerMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		this.hammer.render(matrixStack, hammerVertexbuilder, combinedLight, combinedOverlay);
		Material shaftMaterial = new Material(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.TRIPHAMMER_SHAFT);
		IVertexBuilder shaftVertexbuilder = shaftMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		this.shaft.render(matrixStack, shaftVertexbuilder, combinedLight, combinedOverlay);
		matrixStack.translate(0.0D, -displacement, 0.0D);
		
		ItemStack item = tile.getInputItem();
		if (!item.isEmpty()) {
			matrixStack.translate(0.5D, 0.0625D, 0.5D);
			IBakedModel itemModel = Minecraft.getInstance().getItemRenderer().getItemModelWithOverrides(item, tile.getWorld(), null);
			Minecraft.getInstance().getItemRenderer().renderItem(item, ItemCameraTransforms.TransformType.GROUND, true, matrixStack, buffer, combinedLight, combinedOverlay, itemModel);
			matrixStack.translate(-0.5D, -0.0625D, -0.5D);
		}
		
		matrixStack.translate(0.5D, 2.5D, 0.5D);
		switch (tile.getBlockState().get(MechanicalHorizontalBlock.FACING)) {
	
		case WEST:
		case EAST:
			matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0f));
			break;
			
		case NORTH:
		case SOUTH:
			matrixStack.rotate(Vector3f.ZP.rotationDegrees(90.0f));
			break;

		default:
			throw new IllegalStateException(NotEnoughMachines.MODID + ":TripHammerBlock is in an unknow state!");
		
		}
		float tick = NotEnoughMachines.CLIENTTIMER.getTick() + partialTicks;
		float speed = tile.getSpeed();
		float angle = (tick * speed * 0.3f) % 360;
		angle = angle/180f * (float)Math.PI;
		if (tile.getBlockState().get(MechanicalBlock.SHIFTED)) angle += 22.5f/180.0f * (float)Math.PI;
		matrixStack.rotate(Vector3f.YP.rotation(-angle));
		matrixStack.translate(-0.5D, -0.5D, -0.5D);
		Material axleMaterial = new Material(PlayerContainer.LOCATION_BLOCKS_TEXTURE, NEMTextures.AXLE);
		IVertexBuilder axleVertexbuilder = axleMaterial.getBuffer(buffer, RenderType::getEntityCutout);
		this.axle.render(matrixStack, axleVertexbuilder, combinedLight, combinedOverlay);
		
		matrixStack.pop();
	}

	
	
	public static void register() {
		ClientRegistry.bindTileEntityRenderer(NEMTiles.TRIPHAMMER.get(), TripHammerRenderer::new);
	}
}







