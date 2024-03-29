package com.github.thomashooks.notenoughmachines.client.render.blockentity;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.client.render.blockentity.model.AxleModelLayer;
import com.github.thomashooks.notenoughmachines.world.block.MechanicalBlock;
import com.github.thomashooks.notenoughmachines.world.block.RotatingShaftBlock;
import com.github.thomashooks.notenoughmachines.world.block.entity.EnclosedAxleBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.Mth;
import org.joml.Quaternionf;

public class EnclosedAxleRenderer implements BlockEntityRenderer<EnclosedAxleBlockEntity>
{
    private final ModelPart axleBody;

    public EnclosedAxleRenderer(BlockEntityRendererProvider.Context context)
    {
        ModelPart modelPart = context.bakeLayer(AxleModelLayer.LOCATION);
        this.axleBody = modelPart.getChild("axle");
    }

    @Override
    public void render(EnclosedAxleBlockEntity entity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay)
    {
        poseStack.pushPose();

        poseStack.translate(0.5D, 0.5D, 0.5D);
        switch (entity.getBlockState().getValue(RotatingShaftBlock.AXIS))
        {
            case X -> poseStack.mulPose(new Quaternionf().rotateLocalZ(Mth.HALF_PI));
            case Z -> poseStack.mulPose(new Quaternionf().rotateLocalX(Mth.HALF_PI));
        }

        float tick = NotEnoughMachines.CLIENT_TIMER.getTick() + partialTicks;
        float speed = entity.getSpeed();
        float angle = (tick * speed * 0.3F) % 360;
        angle = angle / 180.0F * Mth.PI;
        if (entity.getBlockState().getValue(MechanicalBlock.SHIFTED))
            angle += 22.5F / 180.0F * Mth.PI;
        poseStack.mulPose(new Quaternionf().rotateLocalY(-angle));
        poseStack.translate(-0.5D, -0.5D, -0.5D);

        VertexConsumer vertexConsumer = AxleRenderer.RESOURCE_LOCATION.buffer(buffer, RenderType::entityCutout);
        this.axleBody.render(poseStack, vertexConsumer, combinedLight, combinedOverlay);

        poseStack.popPose();
    }
}
