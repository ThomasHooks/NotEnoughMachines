package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
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
    private static final float MAX_NUMBER_OF_SIDES_WITH_WATER = 3.0F;
    public WaterWheelBlockEntity(BlockPos pos, BlockState state)
    {
        super(AllBlockEntities.WATER_WHEEL.get(), pos, state);
    }

    @Override
    protected void updateSpeed()
    {
        this.speedModifier = 0.0F;
        float flowRate = 0.0F;
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
                case EAST -> flowRate += (float) Math.signum(vec.z());
                case NORTH -> flowRate += (float) Math.signum(vec.x());
                case SOUTH -> flowRate += (float) -Math.signum(vec.x());
                case WEST -> flowRate += (float) -Math.signum(vec.z());
                default -> throw new IllegalStateException(NotEnoughMachines.MOD_ID + ":WaterWheelBlockEntity is in an unknown state!");
            }
        }
        this.speedModifier = flowRate / MAX_NUMBER_OF_SIDES_WITH_WATER;
    }

    @Override
    public float getBaseSpeed() { return 8.0F; }

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
