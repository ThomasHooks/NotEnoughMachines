package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.world.block.entity.base.MechanicalBlockEntity;
import com.github.thomashooks.notenoughmachines.world.power.MechanicalType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EnclosedAxleBlockEntity extends MechanicalBlockEntity
{
    public EnclosedAxleBlockEntity(BlockPos pos, BlockState state)
    {
        super(AllBlockEntities.ENCLOSED_AXLE.get(), pos, state, 72, 0, MechanicalType.SHAFT);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public AABB getRenderBoundingBox()
    {
        return new AABB(getBlockPos()).inflate(1.0D);
    }
}
