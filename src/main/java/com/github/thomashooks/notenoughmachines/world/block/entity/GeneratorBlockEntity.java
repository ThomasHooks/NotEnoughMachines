package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.world.power.MechanicalType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class GeneratorBlockEntity extends MechanicalBlockEntity
{
    protected float speedModifier = 0.0f;

    protected GeneratorBlockEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state)
    {
        super(entityType, pos, state, 0, 0, MechanicalType.SOURCE);
    }

    @Override
    public void tick()
    {
        if (!this.getLevel().isClientSide() && this.isPowered())
            changeSpeed(this, this.getBaseSpeed() * this.speedModifier);

        super.tick();
    }

    @Override
    public void lazyTick()
    {
        this.updateSpeed();
        this.setCapacity((int) (this.getBasePowerCapacity() * Math.abs(this.getBaseSpeed() * this.speedModifier)));

        super.lazyTick();
    }

    abstract protected void updateSpeed();

    abstract public float getBaseSpeed();

    abstract public int getBasePowerCapacity();
}
