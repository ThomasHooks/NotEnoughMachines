package com.kilroy790.notenoughmachines.power;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;




public class MechanicalInputOutput {

	private BlockPos pos;
	private Direction facing;
	private boolean isAxle;
	
	
	/**
	 * 
	 * @param posIn			The location of the Mechanical I/O
	 * @param dirFacing		The direction that the I/O is facing towards 
	 * @param isAxle		If the Mechanical I/O is not an axle it is assumed to be a cog I/O
	 */
	public MechanicalInputOutput(BlockPos posIn, Direction dirFacing, Boolean isAxle) {
		this.pos = posIn;
		this.facing = dirFacing;
		this.isAxle = isAxle;
	}


	public BlockPos getPos() {
		return this.pos;
	}


	public Direction getFacing() {
		return this.facing;
	}


	public boolean isAxle() {
		return this.isAxle;
	}
}







