package com.github.thomashooks.notenoughmachines.client.render.blockentity;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.client.render.blockentity.model.AxleModelLayer;
import com.github.thomashooks.notenoughmachines.client.render.blockentity.model.WaterWheelModelLayer;
import com.github.thomashooks.notenoughmachines.world.block.MechanicalBlock;
import com.github.thomashooks.notenoughmachines.world.block.entity.WaterWheelBlockEntity;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;

@OnlyIn(Dist.CLIENT)
public class WaterWheelRenderer implements BlockEntityRenderer<WaterWheelBlockEntity>
{
    public static final Material RESOURCE_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(NotEnoughMachines.MOD_ID + ":block/machine/water_wheel"));
    private final ModelPart axleBody;
    private final ModelPart hubBody;
    private final ModelPart trilBody;
    private static final int NUMBER_OF_TRILS = 4;

    public WaterWheelRenderer(BlockEntityRendererProvider.Context context)
    {
        ModelPart axleModelPart = context.bakeLayer(AxleModelLayer.LOCATION);
        this.axleBody = axleModelPart.getChild("axle");
        ModelPart waterWheelModelPart = context.bakeLayer(WaterWheelModelLayer.LOCATION);
        this.hubBody = waterWheelModelPart.getChild("hub");
        this.trilBody = waterWheelModelPart.getChild("tril");
    }

    @Override
    public void render(WaterWheelBlockEntity entity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay)
    {
        poseStack.pushPose();

        poseStack.translate(0.5D, 0.5D, 0.5D);
        float tick = NotEnoughMachines.CLIENT_TIMER.getTick() + partialTicks;
        float speed = entity.getSpeed();
        float angle = (tick * speed * 0.3F) % 360;
        angle = angle / 180.0F * Mth.PI;
        if (entity.getBlockState().getValue(MechanicalBlock.SHIFTED))
            angle += 22.5F / 180.0F * Mth.PI;
        poseStack.mulPose(new Quaternionf().rotateLocalY(-angle));
        poseStack.translate(-0.5D, -0.5D, -0.5D);

        VertexConsumer axleVertexConsumer = AxleRenderer.RESOURCE_LOCATION.buffer(bufferSource, RenderType::entityCutout);
        this.axleBody.render(poseStack, axleVertexConsumer, combinedLight, combinedOverlay);

        VertexConsumer waterWheelVertexConsumer = RESOURCE_LOCATION.buffer(bufferSource, RenderType::entityCutout);
        this.hubBody.render(poseStack, waterWheelVertexConsumer, combinedLight, combinedOverlay);

        float trilAngle = 45.0F / 180.0F * Mth.PI;
        for (int i = 0; i < NUMBER_OF_TRILS; i++)
        {
            poseStack.translate(0.5D, 0.5D, 0.5D);
            poseStack.mulPose(new Quaternionf().rotateLocalY(-trilAngle * (float) i));
            poseStack.translate(-0.5D, -0.5D, -0.5D);
            this.trilBody.render(poseStack, waterWheelVertexConsumer, combinedLight, combinedOverlay);
        }

        poseStack.popPose();
    }
}
