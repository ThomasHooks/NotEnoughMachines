package com.kilroy790.notenoughmachines.utilities;

import java.util.Objects;

import net.minecraft.util.Direction;




public class NEMDirectionHelper {

	static public Direction rotateY(final Direction dir, final boolean ccw, final int times) {
		Objects.requireNonNull(dir, "Direction cannot be null!");
		Direction facing = dir;
		for(int i = 0; i < times; i++) {
			if(ccw) facing = facing.rotateYCCW();
			else facing = facing.rotateY();
		}
		return facing;
	}
	
	
	
	static public Direction rotateX(final Direction dir, final boolean ccw, final int times) {
		Objects.requireNonNull(dir, "Direction cannot be null!");
		Direction facing = dir;
		for(int i = 0; i < times; i++) {
			if(ccw) facing = rotateXCCW(facing);
			else facing = rotateXCW(facing);
		}
		return facing;
	}
	
	
	
	static public Direction rotateXCW(final Direction dir) {
		switch(dir) {
		case DOWN:
			return Direction.SOUTH;
		case EAST:
			return Direction.EAST;
		case NORTH:
			return Direction.DOWN;
		case SOUTH:
			return Direction.UP;
		case UP:
			return Direction.NORTH;
		case WEST:
			return Direction.WEST;
		default:
			throw new IllegalStateException("Unable to get CW facing of " + dir);
		
		}
	}
	
	
	
	static public Direction rotateXCCW(final Direction dir) {
		switch(dir) {
		case DOWN:
			return Direction.NORTH;
		case EAST:
			return Direction.EAST;
		case NORTH:
			return Direction.UP;
		case SOUTH:
			return Direction.DOWN;
		case UP:
			return Direction.SOUTH;
		case WEST:
			return Direction.WEST;
		default:
			throw new IllegalStateException("Unable to get CCW facing of " + dir);
		
		}
	}
}







