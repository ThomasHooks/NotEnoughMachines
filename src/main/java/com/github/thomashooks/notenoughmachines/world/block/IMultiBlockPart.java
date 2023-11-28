package com.github.thomashooks.notenoughmachines.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface IMultiBlockPart
{
    /**
     * @param state This block part's current block state
     * @param pos This block part's current position
     * @return Gets this multi-block part's core block position
     */
    BlockPos getCoreBlockPos(BlockState state, BlockPos pos);

    /**
     * @param world This core part's world
     * @param corePos This core part's position
     * @param state This core part's current block state
     * @return Is true if the multi-block structure is valid
     */
    public boolean isValidForPlacement(Level world, BlockPos corePos, BlockState state);

    /**
     * @param world This part's world
     * @param pos This part's position
     * @param state This part's current block state
     * @return Is true if the multi-block structure is still valid
     */
    public boolean isValid(Level world, BlockPos pos, BlockState state);
}
