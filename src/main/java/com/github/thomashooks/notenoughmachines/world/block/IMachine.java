package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.world.block.entity.MechanicalBlockEntity;
import com.github.thomashooks.notenoughmachines.world.power.MechanicalContext;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public interface IMachine
{
    /**
     * Gets an array that contains this machine's mechanical connection points
     * @param world This machine's World
     * @param pos This machine's Block Position
     * @param state This machine's current Block State
     */
    ArrayList<MechanicalContext> getMechanicalConnections(Level world, BlockPos pos, BlockState state);

    /**
     * Gets an array of neighboring machines that are attached to this machine.
     * It is possible that the returned array is empty if there are not neighboring machines.
     * @param world This machine's World
     * @param pos This machine's Block Position
     * @param state This machine's current Block State
     * @return An array of neighboring machines to this machine
     */
    ArrayList<MechanicalBlockEntity> getNeighbors(Level world, BlockPos pos, BlockState state);

    /**
     * Checks if the given Mechanical Input/Output is aligned with this machine.
     * @param world This machine's World
     * @param pos This machine's Block Position
     * @param state This machine's current Block State
     * @param context The Mechanical Input/Output that is being checked for alignment
     * @return True if the given MechanicalContext is aligned with this machine
     */
    boolean isAlignedWith(Level world, BlockPos pos, BlockState state, MechanicalContext context);

    /**
     * Gets this machine's Block Entity
     * @param world This machine's World
     * @param pos This machine's Block Position
     * @param state This machine's current Block State
     * @return This machine's Mechanical Block Entity
     */
    MechanicalBlockEntity getMechanicalEntity(LevelAccessor world, BlockPos pos, BlockState state);
}
