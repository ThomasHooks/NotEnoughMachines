package com.github.thomashooks.notenoughmachines.client.render.blockentity.model;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class RollingMillModelLayer
{
    public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(NotEnoughMachines.MOD_ID, "rolling_mill"), "main");

    public static LayerDefinition createBody()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("roller_top", CubeListBuilder.create()
                .texOffs(0, 20)
                .addBox(6.0F, 0.0F, 6.0F, 4.0F, 10.0F, 4.0F), PartPose.ZERO); //10.5F, 3.0F, 6.0F
        partdefinition.addOrReplaceChild("roller_bottom", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(6.0F, 0.0F, 6.0F, 4.0F, 16.0F, 4.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 16, 34);
    }
}
