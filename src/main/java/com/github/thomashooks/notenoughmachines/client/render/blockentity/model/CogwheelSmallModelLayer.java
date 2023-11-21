package com.github.thomashooks.notenoughmachines.client.render.blockentity.model;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class CogwheelSmallModelLayer
{
    public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(NotEnoughMachines.MOD_ID, "small_cogwheel"), "main");

    public static LayerDefinition createBody()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("outer_hub", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(4.0F, 5.0F, 4.0F, 8.0F, 6.0F, 8.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("inner_hub", CubeListBuilder.create()
                .texOffs(0, 14)
                .addBox(3.5F, 6.0F, 3.5F, 9.0F, 4.0F, 9.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("tooth", CubeListBuilder.create()
                .texOffs(0, 27)
                .addBox(6.5F, 7.0F, -3.0F, 3.0F, 2.0F, 22.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 50, 52);
    }
}
