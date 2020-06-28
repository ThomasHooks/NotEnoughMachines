package com.kilroy790.notenoughmachines.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.kilroy790.notenoughmachines.power.MechanicalContext;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;




public class MachineIOList {

	/**
	 * Creates a new 2-way mechanical axle I/O lookup map. Use this when the machine's axis is either unknown or can change.
	 * 
	 * @param pos The position of the machine
	 * 
	 * @return New 2-way mechanical axle I/O lookup map
	 */
	public static HashMap<Direction.Axis, ArrayList<MechanicalContext>> monoAxle(BlockPos pos) {

		HashMap<Direction.Axis, ArrayList<MechanicalContext>> io = new HashMap<Direction.Axis, ArrayList<MechanicalContext>>();
		io.put(Direction.Axis.X, new ArrayList<MechanicalContext>(
				Arrays.asList(
						new MechanicalContext(pos.east(), Direction.EAST, true), 
						new MechanicalContext(pos.west(), Direction.WEST, true)
						)));
		
		io.put(Direction.Axis.Y, new ArrayList<MechanicalContext>(
				Arrays.asList(
						new MechanicalContext(pos.up(), Direction.UP, true), 
						new MechanicalContext(pos.down(), Direction.DOWN, true)
						)));
		
		io.put(Direction.Axis.Z, new ArrayList<MechanicalContext>(
				Arrays.asList(
						new MechanicalContext(pos.north(), Direction.NORTH, true), 
						new MechanicalContext(pos.south(), Direction.SOUTH, true)
						)));
		return io;
	}
	
	
	
	/**
	 * Creates a new 2-way mechanical axle I/O array. The axis must be know ahead of time and must never change.
	 * 
	 * @param pos The position of the machine
	 * @param axis The axis that the machine is aligned with
	 * 
	 * @return New 2-way mechanical axle I/O array
	 */
	public static ArrayList<MechanicalContext> monoAxle(BlockPos pos, Direction.Axis axis) {

		ArrayList<MechanicalContext> io;
		switch(axis) {

		case X:
			io = new ArrayList<MechanicalContext>(Arrays.asList(
							new MechanicalContext(pos.east(), Direction.EAST, true), 
							new MechanicalContext(pos.west(), Direction.WEST, true)
							));
			break;

		case Y:
			io = new ArrayList<MechanicalContext>(Arrays.asList(
							new MechanicalContext(pos.up(), Direction.UP, true), 
							new MechanicalContext(pos.down(), Direction.DOWN, true)
							));
			break;

		case Z:
			io = new ArrayList<MechanicalContext>(Arrays.asList(
							new MechanicalContext(pos.north(), Direction.NORTH, true), 
							new MechanicalContext(pos.south(), Direction.SOUTH, true)
							));
			break;

		default:
			io = new ArrayList<MechanicalContext>();
			break;
		}
		return io;
	}



	/**
	 * Creates a new 4-way mechanical axle I/O lookup map. Use this when the machine's axis is either unknown or can change.
	 * 
	 * @param pos The position of the machine
	 * 
	 * @return New 4-way mechanical axle I/O lookup map
	 */
	public static HashMap<Direction.Axis, ArrayList<MechanicalContext>> biAxle(BlockPos pos) {

		HashMap<Direction.Axis, ArrayList<MechanicalContext>> io = new HashMap<Direction.Axis, ArrayList<MechanicalContext>>();
		io.put(Direction.Axis.X, new ArrayList<MechanicalContext>(
				Arrays.asList(
						new MechanicalContext(pos.up(), Direction.UP, true), 
						new MechanicalContext(pos.down(), Direction.DOWN, true), 
						new MechanicalContext(pos.north(), Direction.NORTH, true), 
						new MechanicalContext(pos.south(), Direction.SOUTH, true)
						)));
		
		io.put(Direction.Axis.Y, new ArrayList<MechanicalContext>(
				Arrays.asList(
						new MechanicalContext(pos.east(), Direction.EAST, true), 
						new MechanicalContext(pos.west(), Direction.WEST, true), 
						new MechanicalContext(pos.north(), Direction.NORTH, true), 
						new MechanicalContext(pos.south(), Direction.SOUTH, true)
						)));
		
		io.put(Direction.Axis.Z, new ArrayList<MechanicalContext>(
				Arrays.asList(
						new MechanicalContext(pos.east(), Direction.EAST, true), 
						new MechanicalContext(pos.west(), Direction.WEST, true), 
						new MechanicalContext(pos.up(), Direction.UP, true), 
						new MechanicalContext(pos.down(), Direction.DOWN, true)
						)));
		return io;
	}



	/**
	 * Creates a new 6-way mechanical axle I/O array.
	 * 
	 * @param pos The position of the machine
	 * 
	 * @return New 6-way mechanical axle I/O array
	 */
	public static ArrayList<MechanicalContext> triAxle(BlockPos pos) {

		ArrayList<MechanicalContext> io = new ArrayList<MechanicalContext>(
				Arrays.asList(
						new MechanicalContext(pos.east(), Direction.EAST, true),
						new MechanicalContext(pos.west(), Direction.WEST, true),
						new MechanicalContext(pos.up(), Direction.UP, true),
						new MechanicalContext(pos.down(), Direction.DOWN, true),
						new MechanicalContext(pos.south(), Direction.SOUTH, true),
						new MechanicalContext(pos.north(), Direction.NORTH, true)
						));
		return io;
	}
	
	
	
	/**
	 * 
	 * @param pos
	 * 
	 * @return
	 */
	public static HashMap<Direction.Axis, ArrayList<MechanicalContext>> smallCogwheel(BlockPos pos) {
		
		HashMap<Direction.Axis, ArrayList<MechanicalContext>> io = new HashMap<Direction.Axis, ArrayList<MechanicalContext>>();
		io.put(Direction.Axis.X, new ArrayList<MechanicalContext>(
				Arrays.asList(
						new MechanicalContext(pos.east(), Direction.EAST, true), 
						new MechanicalContext(pos.west(), Direction.WEST, true),
						new MechanicalContext(pos.north(), Direction.NORTH, false), 
						new MechanicalContext(pos.south(), Direction.SOUTH, false),
						new MechanicalContext(pos.up(), Direction.UP, false), 
						new MechanicalContext(pos.down(), Direction.DOWN, false)
						)));
		
		io.put(Direction.Axis.Y, new ArrayList<MechanicalContext>(
				Arrays.asList(
						new MechanicalContext(pos.east(), Direction.EAST, false), 
						new MechanicalContext(pos.west(), Direction.WEST, false),
						new MechanicalContext(pos.north(), Direction.NORTH, false), 
						new MechanicalContext(pos.south(), Direction.SOUTH, false),
						new MechanicalContext(pos.up(), Direction.UP, true), 
						new MechanicalContext(pos.down(), Direction.DOWN, true)
						)));
		
		io.put(Direction.Axis.Z, new ArrayList<MechanicalContext>(
				Arrays.asList(
						new MechanicalContext(pos.east(), Direction.EAST, false), 
						new MechanicalContext(pos.west(), Direction.WEST, false),
						new MechanicalContext(pos.north(), Direction.NORTH, true), 
						new MechanicalContext(pos.south(), Direction.SOUTH, true),
						new MechanicalContext(pos.up(), Direction.UP, false), 
						new MechanicalContext(pos.down(), Direction.DOWN, false)
						)));
		return io;
	}
}







