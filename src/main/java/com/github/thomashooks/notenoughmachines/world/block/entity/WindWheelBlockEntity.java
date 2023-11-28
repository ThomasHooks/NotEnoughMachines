package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.world.block.WindWheelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import oshi.util.tuples.Pair;

public class WindWheelBlockEntity extends GeneratorBlockEntity
{
    public static final int WIND_WHEEL_RADIUS = 8;

    public WindWheelBlockEntity(BlockPos pos, BlockState state)
    {
        super(AllBlockEntities.WIND_WHEEL.get(), pos, state);
        this.speedModifier = 1.0F;
    }

    @Override
    protected void updateSpeed()
    {
        if (!isAreaValid())
        {
            this.speedModifier = 0.0F;
            return;
        }

        if (getLevel().isThundering())
            this.speedModifier = 2.0F;
        else if (getLevel().isRaining())
            this.speedModifier = 1.33F;
        else
            this.speedModifier = 1.0F;

        if (this.getBlockState().getValue(WindWheelBlock.FACING) == Direction.SOUTH || this.getBlockState().getValue(WindWheelBlock.FACING) == Direction.WEST)
            this.speedModifier *= -1.0F;
    }

    protected boolean isAreaValid()
    {
        if (!getLevel().canSeeSky(getBlockPos()))
            return false;

        Direction direction = getBlockState().getValue(WindWheelBlock.FACING);
        for (int y = -WIND_WHEEL_RADIUS; y <= WIND_WHEEL_RADIUS; y++)
        {
            for (int horizontal = -WIND_WHEEL_RADIUS; horizontal <= WIND_WHEEL_RADIUS; horizontal++)
            {
                int x = 0, z = 0;
                var pair = chooseHorizontalAxis(direction, horizontal);
                x = pair.getA();
                z = pair.getB();

                if (y == 0 && horizontal == 0)
                    continue; //Need to skip the center

                if (!getLevel().getBlockState(getBlockPos().offset(x, y, z)).isAir())
                    return false;
            }
        }
        return true;
    }

    protected Pair<Integer, Integer> chooseHorizontalAxis(Direction direction, int horizontal)
    {
        if (direction == Direction.NORTH || direction == Direction.SOUTH)
            return new Pair<>(horizontal, 0); //Choose X axis
        else
            return new Pair<>(0, horizontal); //Choose Z axis
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public AABB getRenderBoundingBox() { return new AABB(getBlockPos()).inflate((double) WIND_WHEEL_RADIUS); }

    @Override
    public float getBaseSpeed() { return 12.0F; }

    @Override
    public int getBasePowerCapacity() { return 80; }
}
