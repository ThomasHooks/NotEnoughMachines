package com.github.thomashooks.notenoughmachines.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.extensions.IForgeBlock;

public class LinenBlock extends Block implements IForgeBlock
{
    public LinenBlock(Properties properties) { super(properties); }

    /**
     * Block's chance to react to a living entity falling on it.
     */
    @Override
    public void fallOn(Level world, BlockState blockState, BlockPos pos, Entity entity, float fallDistance)
    {
        if (entity.isSuppressingBounce())
        {
            super.fallOn(world, blockState, pos, entity, fallDistance);
        } else
        {
            entity.causeFallDamage(fallDistance, 0.0F, world.damageSources().fall());
        }

    }

    /**
     * Called when an Entity lands on this Block. This method *must* update motionY because the entity will not do that
     * on its own
     */
    @Override
    public void updateEntityAfterFallOn(BlockGetter blockGetter, Entity entity) {
        if (entity.isSuppressingBounce())
        {
            super.updateEntityAfterFallOn(blockGetter, entity);
        }
        else
        {
            this.bounceUp(entity);
        }

    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
    {
        return 30;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction)
    {
        return 60;
    }

    private void bounceUp(Entity entity)
    {
        Vec3 vec3 = entity.getDeltaMovement();
        if (vec3.y < 0.0D)
        {
            double d0 = entity instanceof LivingEntity ? 0.45D : 0.36D;
            entity.setDeltaMovement(vec3.x, -vec3.y * d0, vec3.z);
        }
    }
}
