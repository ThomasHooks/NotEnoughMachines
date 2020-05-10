package com.kilroy790.notenoughmachines.power;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;




public class MechanicalInputOutputWrapper {

	private BlockPos pos;
	private Direction facing;
	private MechIOType typeIO;
	
	
	
	public MechanicalInputOutputWrapper(BlockPos posIn, Direction dirFacing, MechIOType ioType) {
		this.pos = posIn;
		this.facing = dirFacing;
		this.typeIO = ioType;
	}


	public BlockPos getPos() {
		return this.pos;
	}


	public Direction getFacing() {
		return this.facing;
	}


	public MechIOType getType() {
		return this.typeIO;
	}
}







