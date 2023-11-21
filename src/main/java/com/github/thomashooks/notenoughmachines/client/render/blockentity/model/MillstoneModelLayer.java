package com.github.thomashooks.notenoughmachines.client.render.blockentity.model;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class MillstoneModelLayer
{
    public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(NotEnoughMachines.MOD_ID, "millstone"), "main");

    public static LayerDefinition createBody()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        //We can get the axle part from AxleModelLayer
        partdefinition.addOrReplaceChild("runner_stone", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(1.0F, 6.0F, 1.0F, 14.0F, 4.0F, 14.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 56, 18);
    }
}
