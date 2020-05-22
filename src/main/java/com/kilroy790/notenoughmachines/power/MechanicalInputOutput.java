package com.kilroy790.notenoughmachines.power;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;




public class MechanicalInputOutput {

	private BlockPos pos;
	private Direction facing;
	private boolean isAxle;
	
	
	/**
	 * @param posIn The location of the Mechanical I/O
	 * @param dirFacing The direction that the I/O is facing towards 
	 * @param isAxle If the Mechanical I/O is not an axle it is assumed to be a cog I/O
	 */
	public MechanicalInputOutput(BlockPos posIn, Direction dirFacing, Boolean isAxle) {
		this.pos = posIn;
		this.facing = dirFacing;
		this.isAxle = isAxle;
	}


	/**
	 * @return This Mechanical I/O's position
	 */
	public BlockPos getPos() {
		return this.pos;
	}


	/**
	 * @return The direction This Mechanical I/O is facing
	 */
	public Direction getFacing() {
		return this.facing;
	}


	/**
	 * @return True if this Mechanical I/O is an axle, false if it is a cog
	 */
	public boolean isAxle() {
		return this.isAxle;
	}
}







