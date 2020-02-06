package com.kilroy790.notenoughmachines.multiblocks;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;




public interface IMultiblock {

	
	/*
	 * @param	pos		Location of the Master
	 * 
	 * @return	true if the Multiblock is formed
	 */
	public boolean isMultiblockFormed(World world, BlockPos pos);
	
	
	/*
	 * Checks if the Multiblock is valid
	 * 
	 * @param	pos		Location of the Master
	 * 
	 * @return	true if the Multiblock is valid
	 */
	public boolean isMultiblockValid(World world, BlockPos pos);
	
	
	/*
	 * Creates a Multiblock at the given location with given size
	 * 
	 * @param	pos		Location of the Master
	 * 
	 * @param	x,y,z	Size of the Multiblock
	 * 
	 * @return	true if the Multiblock was formed
	 */
	public boolean formMultiblock(World world, BlockPos pos, int x, int y, int z);
	
	
	/*
	 * Removes this Multiblock at the given location
	 * 
	 * @param	pos		Location of the Master
	 * 
	 * @return	true if the Multiblock was removed
	 */
	public boolean removeMultiblock(World world, BlockPos pos);
	
	
	/*
	 * @param	pos		Location of the Master
	 * 
	 * @return	The block position of the master block for this Multiblock
	 */
	public BlockPos getMaster(World world, BlockPos pos);
	
	
	// @return	The size of this Multiblock on the X axis
	public int getMultiblockWidth();
	
	
	// @return	The size of this Multiblock on the Y axis
	public int getMultiblockHight();
	
	
	// @return	The size of this Multiblock on the Z axis
	public int getMultiblockDepth();
}
