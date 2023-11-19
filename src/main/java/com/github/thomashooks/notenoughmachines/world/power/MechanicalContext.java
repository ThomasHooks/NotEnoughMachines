package com.github.thomashooks.notenoughmachines.world.power;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class MechanicalContext
{
    private BlockPos pos;
    private Direction facing;
    private boolean isAxle;

    /**
     * @param posIn The location of the machine's I/O
     * @param dirFacing The direction that the I/O is facing away from this machine
     * @param isAxle If the machine's I/O is not an axle it is assumed to be a cog I/O
     */
    public MechanicalContext(BlockPos posIn, Direction dirFacing, Boolean isAxle)
    {
        this.pos = posIn;
        this.facing = dirFacing;
        this.isAxle = isAxle;
    }

    /**
     * @return This machine's I/O position
     */
    public BlockPos getPos() {
        return this.pos;
    }

    /**
     * @return The direction This I/O is facing away from its machine
     */
    public Direction getFacing()
    {
        return this.facing;
    }

    /**
     * @return True if this I/O is an axle, false if it is a cog
     */
    public boolean isAxle()
    {
        return this.isAxle;
    }
}
