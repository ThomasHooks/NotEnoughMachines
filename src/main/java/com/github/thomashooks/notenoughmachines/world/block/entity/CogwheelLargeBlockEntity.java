package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.world.block.entity.base.MechanicalBlockEntity;
import com.github.thomashooks.notenoughmachines.world.power.MechanicalType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CogwheelLargeBlockEntity extends MechanicalBlockEntity
{
    public CogwheelLargeBlockEntity(BlockPos pos, BlockState state)
    {
        super(AllBlockEntities.COGWHEEL_LARGE.get(), pos, state, 72, 0, MechanicalType.COG);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public AABB getRenderBoundingBox()
    {
        return new AABB(getBlockPos()).inflate(3.0D);
    }

    @Override
    public float getNumberOfTeeth() { return 16.0F; }
}
