package com.github.thomashooks.notenoughmachines.client.render.blockentity;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.client.render.blockentity.model.AxleModelLayer;
import com.github.thomashooks.notenoughmachines.client.render.blockentity.model.TripHammerModelLayer;
import com.github.thomashooks.notenoughmachines.world.block.HorizontalMechanicalBlock;
import com.github.thomashooks.notenoughmachines.world.block.MechanicalBlock;
import com.github.thomashooks.notenoughmachines.world.block.entity.TripHammerBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;

@OnlyIn(Dist.CLIENT)
public class TripHammerRenderer implements BlockEntityRenderer<TripHammerBlockEntity>
{
    public static final Material RESOURCE_LOCATION = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(NotEnoughMachines.MOD_ID + ":block/machine/trip_hammer"));
    private final ModelPart axleBody;
    private final ModelPart shaftBody;
    private final ModelPart hammerHeadBody;


    public TripHammerRenderer(BlockEntityRendererProvider.Context context)
    {
        ModelPart axleModelPart = context.bakeLayer(AxleModelLayer.LOCATION);
        this.axleBody = axleModelPart.getChild("axle");
        ModelPart tripHammerModelPart = context.bakeLayer(TripHammerModelLayer.LOCATION);
        this.shaftBody = tripHammerModelPart.getChild("shaft");
        this.hammerHeadBody = tripHammerModelPart.getChild("hammer_head");
    }

    @Override
    public void render(TripHammerBlockEntity entity, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay)
    {
        poseStack.pushPose();

        double displacement = entity.getDisplacement();
        poseStack.translate(0.0D, displacement, 0.0D);
        VertexConsumer tripHammerVertexConsumer = RESOURCE_LOCATION.buffer(buffer, RenderType::entityCutout);
        this.shaftBody.render(poseStack, tripHammerVertexConsumer, combinedLight, combinedOverlay);
        this.hammerHeadBody.render(poseStack, tripHammerVertexConsumer, combinedLight, combinedOverlay);
        poseStack.translate(0.0D, -displacement, 0.0D);

        ItemStack item = entity.getInputItem();
        if (!item.isEmpty())
        {
            poseStack.translate(0.5D, 0.0625D, 0.5D);
            BakedModel itemModel = Minecraft.getInstance().getItemRenderer().getModel(item, entity.getLevel(), null, 0);
            Minecraft.getInstance().getItemRenderer().render(item, ItemDisplayContext.GROUND, true, poseStack, buffer, combinedLight, combinedOverlay, itemModel);
            poseStack.translate(-0.5D, -0.0625D, -0.5D);
        }

        poseStack.translate(0.5D, 2.5D, 0.5D);
        switch (entity.getBlockState().getValue(HorizontalMechanicalBlock.FACING).getAxis())
        {
            case X -> poseStack.mulPose(new Quaternionf().rotateLocalX(Mth.HALF_PI));
            case Z -> poseStack.mulPose(new Quaternionf().rotateLocalZ(Mth.HALF_PI));
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

        poseStack.popPose();
    }
}
