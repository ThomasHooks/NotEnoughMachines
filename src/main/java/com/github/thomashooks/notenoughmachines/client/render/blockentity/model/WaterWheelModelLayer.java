package com.github.thomashooks.notenoughmachines.client.render.blockentity.model;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class WaterWheelModelLayer
{
    public static final ModelLayerLocation LOCATION = new ModelLayerLocation(new ResourceLocation(NotEnoughMachines.MOD_ID, "water_wheel"), "main");

    public static LayerDefinition createBody()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        //We can get the axle part from AxleModelLayer
        partdefinition.addOrReplaceChild("hub", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(5.0F, 1.0F, 5.0F, 6.0F, 12.0F, 6.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("tril", CubeListBuilder.create()
                .texOffs(0, 18)
                .addBox(-3.0F, 2.0F, 7.0F, 22.0F, 10.0F, 2.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 48, 30);
    }
}
