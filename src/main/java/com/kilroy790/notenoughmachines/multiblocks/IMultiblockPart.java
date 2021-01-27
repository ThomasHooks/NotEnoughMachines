package com.kilroy790.notenoughmachines.multiblocks;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;




public interface IMultiblockPart {

	/**
	 * Checks if the Multiblock structure is currently formed
	 * 
	 * @param world This part's world
	 * @param pos This part's position
	 * @param state This part's current block state
	 * 
	 * @return true if the Multiblock structure is currently formed
	 */
	public boolean isMultiblockFormed(World world, BlockPos pos, BlockState state);
	
	
	
	/**
	 * Checks if the Multiblock structure is valid
	 * 
	 * @param world This part's world
	 * @param pos This part's position
	 * @param state This part's current block state
	 * 
	 * @return true if the Multiblock structure is valid
	 */
	public boolean isMultiblockValid(World world, BlockPos pos, BlockState state);
	
	
	
	/**
	 * Creates a Multiblock structure at the given location with given size
	 * 
	 * @param world The master's world
	 * @param pos The master's position
	 * @param state The master's current block state
	 */
	public void formMultiblock(World world, BlockPos pos, BlockState state);
	
	
	
	/**
	 * Removes this Multiblock structure 
	 * 
	 * @param world This part's world
	 * @param pos This part's position
	 * @param state This part's current block state
	 */
	public void unformMultiblock(World world, BlockPos pos, BlockState state);
	
	

	/**
	 * Gets this multiblock part's master tile entity
	 * 
	 * @param world This part's world
	 * @param pos This part's position
	 * @param state This part's current block state
	 */
	@Nullable
	public TileEntity getMasterTile(World world, BlockPos pos, BlockState state);
	
	
	
	/**
	 * @return The size of this Multiblock on the X axis.
	 */
	public int getMultiblockWidth();
	
	

	/**
	 * @return The size of this Multiblock on the Y axis.
	 */
	public int getMultiblockHight();
	
	
	
	/**
	 * @return The size of this Multiblock on the Z axis.
	 */
	public int getMultiblockDepth();
}







