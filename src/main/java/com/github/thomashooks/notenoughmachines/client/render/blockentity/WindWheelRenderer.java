package com.github.thomashooks.notenoughmachines.client.render.blockentity;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.client.render.blockentity.model.AxleModelLayer;
import com.github.thomashooks.notenoughmachines.client.render.blockentity.model.WindWheelModelLayer;
import com.github.thomashooks.notenoughmachines.world.block.WindWheelBlock;
import com.github.thomashooks.notenoughmachines.world.block.MechanicalBlock;
import com.github.thomashooks.notenoughmachines.world.block.entity.WindWheelBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;

public class WindWheelRenderer implements BlockEntityRenderer<WindWheelBlockEntity>
{
    public static final Material RESOURCE_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(NotEnoughMachines.MOD_ID + ":block/machine/wind_wheel"));
    private final ModelPart axleBody;
    private final ModelPart sailBody;
    private final ModelPart stockBody;
    private static final int NUMBER_OF_SAILS = 4;

    public WindWheelRenderer(BlockEntityRendererProvider.Context context)
    {
        ModelPart axleModelPart = context.bakeLayer(AxleModelLayer.LOCATION);
        this.axleBody = axleModelPart.getChild("axle");
        ModelPart windWheelModelPart = context.bakeLayer(WindWheelModelLayer.LOCATION);
        this.sailBody = windWheelModelPart.getChild("sail");
        this.stockBody = windWheelModelPart.getChild("stock");
    }

    @Override
    public void render(WindWheelBlockEntity entity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay)
    {
        poseStack.pushPose();

        float tick = NotEnoughMachines.CLIENT_TIMER.getTick() + partialTicks;
        float speed = entity.getSpeed();
        float angle = (tick * speed * 0.3F) % 360;
        angle = angle / 180.0F * Mth.PI;
        if (entity.getBlockState().getValue(MechanicalBlock.SHIFTED))
            angle += 22.5F / 180.0F * Mth.PI;

        poseStack.translate(0.5D, 0.5D, 0.5D);
        switch (entity.getBlockState().getValue(WindWheelBlock.FACING))
        {
            case NORTH ->
            {
                poseStack.mulPose(new Quaternionf().rotateLocalX(Mth.HALF_PI));
            }
            case EAST ->
            {
                poseStack.mulPose(new Quaternionf().rotateLocalZ(Mth.HALF_PI));
            }
            case SOUTH ->
            {
                poseStack.mulPose(new Quaternionf().rotateLocalX(-Mth.HALF_PI));
                angle *= -1;
            }
            case WEST ->
            {
                poseStack.mulPose(new Quaternionf().rotateLocalZ(-Mth.HALF_PI));
                angle *= -1;
            }
        }
        poseStack.mulPose(new Quaternionf().rotateLocalY(-angle));
        poseStack.translate(-0.5D, -0.5D, -0.5D);

        VertexConsumer axleVertexConsumer = AxleRenderer.RESOURCE_LOCATION.buffer(buffer, RenderType::entityCutout);
        this.axleBody.render(poseStack, axleVertexConsumer, combinedLight, combinedOverlay);

        VertexConsumer windWheelVertexConsumer = RESOURCE_LOCATION.buffer(buffer, RenderType::entityCutout);
        for (int i = 0; i < NUMBER_OF_SAILS; i++)
        {
            poseStack.translate(0.5D, 0.5D, 0.5D);
            poseStack.mulPose(new Quaternionf().rotateLocalY(Mth.HALF_PI * (float) i));
            this.sailBody.zRot = 6.0F / 180.0F * Mth.PI;
            poseStack.translate(-0.5D, -0.5D, -0.5D);
            this.stockBody.render(poseStack, windWheelVertexConsumer, combinedLight, combinedOverlay);
            this.sailBody.render(poseStack, windWheelVertexConsumer, combinedLight, combinedOverlay);
        }

        poseStack.popPose();
    }

    @Override
    public int getViewDistance() { return 4096 * 4; }
}
