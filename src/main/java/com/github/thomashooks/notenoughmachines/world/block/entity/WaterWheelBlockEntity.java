package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.entity.base.GeneratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WaterWheelBlockEntity extends GeneratorBlockEntity
{
    public WaterWheelBlockEntity(BlockPos pos, BlockState state)
    {
        super(AllBlockEntities.WATER_WHEEL.get(), pos, state);
    }

    @Override
    protected void updateSpeed()
    {
        this.speedModifier = 0;
        for (Direction dir : Direction.Plane.HORIZONTAL)
        {
            BlockPos fluidPos = this.getBlockPos().relative(dir);
            FluidState fluid = this.getLevel().getFluidState(fluidPos);
            Vec3 vec = fluid.getFlow(this.getLevel(), fluidPos);
            /*
             * 				North (-Z)
             *
             * West (-X)					East (+X)
             *
             * 				South (+Z)
             *
             * Because counter-clockwise is positive rotation, the flow for both south and west must be negated
             */
            switch (dir)
            {
                case EAST -> this.speedModifier += (float) Math.signum(vec.z());

                case NORTH -> this.speedModifier += (float) Math.signum(vec.x());

                case SOUTH -> this.speedModifier += (float) -Math.signum(vec.x());

                case WEST -> this.speedModifier += (float) -Math.signum(vec.z());

                default -> throw new IllegalStateException(NotEnoughMachines.MOD_ID + ":WaterWheelBlockEntity is in an unknown state!");
            }
        }
    }

    @Override
    public float getBaseSpeed() { return 11.1F; }

    @Override
    public int getBasePowerCapacity() { return 20; }

    @Override
    @OnlyIn(Dist.CLIENT)
    public AABB getRenderBoundingBox()
    {
        return new AABB(getBlockPos()).inflate(1.0D);
    }

    @Override
    public void load(CompoundTag nbt)
    {
        super.load(nbt);

        this.speedModifier = nbt.getFloat("speed_modifier");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt)
    {
        super.saveAdditional(nbt);

        nbt.putFloat("speed_modifier", this.speedModifier);
    }
}
