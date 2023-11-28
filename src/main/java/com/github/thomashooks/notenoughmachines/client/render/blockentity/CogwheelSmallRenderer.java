package com.github.thomashooks.notenoughmachines.client.render.blockentity;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.client.render.blockentity.model.AxleModelLayer;
import com.github.thomashooks.notenoughmachines.client.render.blockentity.model.CogwheelSmallModelLayer;
import com.github.thomashooks.notenoughmachines.world.block.MechanicalBlock;
import com.github.thomashooks.notenoughmachines.world.block.RotatingShaftBlock;
import com.github.thomashooks.notenoughmachines.world.block.entity.CogwheelSmallBlockEntity;
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

public class CogwheelSmallRenderer implements BlockEntityRenderer<CogwheelSmallBlockEntity>
{
    public static final Material RESOURCE_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(NotEnoughMachines.MOD_ID + ":block/machine/small_cogwheel"));
    private final ModelPart axleBody;
    private final ModelPart outerHubBody;
    private final ModelPart innerHubBody;
    private final ModelPart toothBody;
    private static final int NUMBER_OF_TEETH = 4;

    public CogwheelSmallRenderer(BlockEntityRendererProvider.Context context)
    {
        ModelPart axleModelPart = context.bakeLayer(AxleModelLayer.LOCATION);
        this.axleBody = axleModelPart.getChild("axle");
        ModelPart cogwheelModelPart = context.bakeLayer(CogwheelSmallModelLayer.LOCATION);
        this.outerHubBody = cogwheelModelPart.getChild("outer_hub");
        this.innerHubBody = cogwheelModelPart.getChild("inner_hub");
        this.toothBody = cogwheelModelPart.getChild("tooth");
    }

    @Override
    public void render(CogwheelSmallBlockEntity entity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay)
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

        VertexConsumer axleVertexConsumer = AxleRenderer.RESOURCE_LOCATION.buffer(buffer, RenderType::entityCutout);
        this.axleBody.render(poseStack, axleVertexConsumer, combinedLight, combinedOverlay);

        VertexConsumer cogwheelVertexConsumer = RESOURCE_LOCATION.buffer(buffer, RenderType::entityCutout);
        this.outerHubBody.render(poseStack, cogwheelVertexConsumer, combinedLight, combinedOverlay);

        poseStack.translate(0.5D, 0.5D, 0.5D);
        poseStack.mulPose(new Quaternionf().rotateLocalY(Mth.PI / 4.0F));
        poseStack.translate(-0.5D, -0.5D, -0.5D);
        this.innerHubBody.render(poseStack, cogwheelVertexConsumer, combinedLight, combinedOverlay);

        for (int i = 0; i < NUMBER_OF_TEETH; i++)
        {
            poseStack.translate(0.5D, 0.5D, 0.5D);
            poseStack.mulPose(new Quaternionf().rotateLocalY((Mth.PI / 4.0F) * (float) i));
            poseStack.translate(-0.5D, -0.5D, -0.5D);
            this.toothBody.render(poseStack, cogwheelVertexConsumer, combinedLight, combinedOverlay);
        }

        poseStack.popPose();
    }
}
