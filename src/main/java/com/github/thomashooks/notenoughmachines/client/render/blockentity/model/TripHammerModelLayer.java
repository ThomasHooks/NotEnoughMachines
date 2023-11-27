package com.github.thomashooks.notenoughmachines.client.render.blockentity.model;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class TripHammerModelLayer
{
    public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(NotEnoughMachines.MOD_ID, "trip_hammer"), "main");

    public static LayerDefinition createBody()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("shaft", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(6.0F, 10.0F, 6.0F, 4.0F, 48.0F, 4.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("hammer_head", CubeListBuilder.create()
                .texOffs(0, 52)
                .addBox(5.0F, 2.0F, 5.0F, 6.0F, 8.0F, 6.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 24, 66);
    }
}
