package com.github.thomashooks.notenoughmachines.client.render.blockentity;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.client.render.blockentity.model.RollingMillModelLayer;
import com.github.thomashooks.notenoughmachines.world.block.HorizontalMechanicalBlock;
import com.github.thomashooks.notenoughmachines.world.block.MechanicalBlock;
import com.github.thomashooks.notenoughmachines.world.block.entity.RollingMillBlockEntity;
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

public class RollingMillRenderer implements BlockEntityRenderer<RollingMillBlockEntity>
{
    public static final Material RESOURCE_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(NotEnoughMachines.MOD_ID + ":block/machine/rolling_mill"));
    private final ModelPart rollerTopBody;
    private final ModelPart rollerBottomBody;

    public RollingMillRenderer(BlockEntityRendererProvider.Context context)
    {
        ModelPart modelPart = context.bakeLayer(RollingMillModelLayer.LOCATION);
        this.rollerTopBody = modelPart.getChild("roller_top");
        this.rollerBottomBody = modelPart.getChild("roller_bottom");
    }

    @Override
    public void render(RollingMillBlockEntity entity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay)
    {
        poseStack.pushPose();

        float tick = NotEnoughMachines.CLIENT_TIMER.getTick() + partialTicks;
        float speed = entity.getSpeed();
        float angle = (tick * speed * 0.3F) % 360;
        angle = angle / 180.0F * Mth.PI;
        if (entity.getBlockState().getValue(MechanicalBlock.SHIFTED))
            angle += 22.5F / 180.0F * Mth.PI;

        VertexConsumer vertexConsumer = RESOURCE_LOCATION.buffer(buffer, RenderType::entityCutout);

        //Translate and render bottom roller
        poseStack.translate(0.5D, 0.5D, 0.5D);
        switch (entity.getBlockState().getValue(HorizontalMechanicalBlock.FACING).getAxis())
        {
            case X -> poseStack.mulPose(new Quaternionf().rotateLocalX(Mth.HALF_PI));
            case Z -> poseStack.mulPose(new Quaternionf().rotateLocalZ(Mth.HALF_PI));
        }
        poseStack.mulPose(new Quaternionf().rotateLocalY(-angle));
        poseStack.translate(-0.5D, -0.5D, -0.5D);
        this.rollerBottomBody.render(poseStack, vertexConsumer, combinedLight, combinedOverlay);

        //Stop the rotation so we can render the top roller
        poseStack.translate(0.5D, 0.5D, 0.5D);
        poseStack.mulPose(new Quaternionf().rotateLocalY(angle));
        poseStack.translate(-0.5D, -0.5D, -0.5D);

        //Translate and render top roller
        double modelsYPos = 3.0D / 16.0D;
        double modelsXPos = 4.5D / 16.0D;
        switch (entity.getBlockState().getValue(HorizontalMechanicalBlock.FACING).getAxis())
        {
            case X -> poseStack.translate(0.0D, modelsYPos, -modelsXPos);
            case Z -> poseStack.translate(modelsXPos, modelsYPos, 0.0D);
        }
        poseStack.translate(0.5D, 0.5D, 0.5D);
        poseStack.mulPose(new Quaternionf().rotateLocalY(angle));
        poseStack.translate(-0.5D, -0.5D, -0.5D);
        this.rollerTopBody.render(poseStack, vertexConsumer, combinedLight, combinedOverlay);

        poseStack.popPose();
    }
}
