package com.kilroy790.notenoughmachines.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import com.kilroy790.notenoughmachines.power.MechanicalInputOutput;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;




public class MachineIOList {
	
	/**
	 * @param pos The position of the machine
	 * @param axis The axis that the machine is aligned with
	 * 
	 * @return New 2-way axle mechanical I/O array
	 */
	public static ArrayList<MechanicalInputOutput> monoAxle(BlockPos pos, Direction.Axis axis) {
		
		ArrayList<MechanicalInputOutput> io;
		switch(axis) {
		case X:
			io = new ArrayList<MechanicalInputOutput>(Arrays.asList(new MechanicalInputOutput(pos.east(), Direction.EAST, true), 
					new MechanicalInputOutput(pos.west(), Direction.WEST, true)));
			break;
		case Y:
			io = new ArrayList<MechanicalInputOutput>(Arrays.asList(new MechanicalInputOutput(pos.up(), Direction.UP, true), 
					new MechanicalInputOutput(pos.down(), Direction.DOWN, true)));
			break;
		case Z:
			io = new ArrayList<MechanicalInputOutput>(Arrays.asList(new MechanicalInputOutput(pos.north(), Direction.NORTH, true), 
					new MechanicalInputOutput(pos.south(), Direction.SOUTH, true)));
			break;
		default:
			io = new ArrayList<MechanicalInputOutput>();
			break;
		
		}
		
		return io;
	}
	
	
	
	/**
	 * @param pos The position of the machine
	 * @param axis The axis that the machine does not have an I/O for
	 * 
	 * @return New 4-way axle mechanical I/O array
	 */
	public static ArrayList<MechanicalInputOutput> biAxle(BlockPos pos, Direction.Axis axis) {
		
		ArrayList<MechanicalInputOutput> io;
		switch(axis) {
		case X:
			io = new ArrayList<MechanicalInputOutput>(Arrays.asList(new MechanicalInputOutput(pos.up(), Direction.UP, true), 
					new MechanicalInputOutput(pos.down(), Direction.DOWN, true), 
					new MechanicalInputOutput(pos.north(), Direction.NORTH, true), 
					new MechanicalInputOutput(pos.south(), Direction.SOUTH, true)));
			break;
		case Y:
			io = new ArrayList<MechanicalInputOutput>(Arrays.asList(new MechanicalInputOutput(pos.east(), Direction.EAST, true), 
					new MechanicalInputOutput(pos.west(), Direction.WEST, true), 
					new MechanicalInputOutput(pos.north(), Direction.NORTH, true), 
					new MechanicalInputOutput(pos.south(), Direction.SOUTH, true)));
			break;
		case Z:
			io = new ArrayList<MechanicalInputOutput>(Arrays.asList(new MechanicalInputOutput(pos.east(), Direction.EAST, true), 
					new MechanicalInputOutput(pos.west(), Direction.WEST, true), 
					new MechanicalInputOutput(pos.up(), Direction.UP, true), 
					new MechanicalInputOutput(pos.down(), Direction.DOWN, true)));
			break;
		default:
			io = new ArrayList<MechanicalInputOutput>();
			break;
		
		}
		
		return io;
	}
	
	
	
	/**
	 * @param pos The position of the machine
	 * 
	 * @return New 6-way axle mechanical I/O array
	 */
	public static ArrayList<MechanicalInputOutput> triAxle(BlockPos pos) {
		
		ArrayList<MechanicalInputOutput> io = new ArrayList<MechanicalInputOutput>(
				Arrays.asList(new MechanicalInputOutput(pos.east(), Direction.EAST, true),
						new MechanicalInputOutput(pos.west(), Direction.WEST, true),
						new MechanicalInputOutput(pos.up(), Direction.UP, true),
						new MechanicalInputOutput(pos.down(), Direction.DOWN, true),
						new MechanicalInputOutput(pos.south(), Direction.SOUTH, true),
						new MechanicalInputOutput(pos.north(), Direction.NORTH, true)));
		return io;
	}
}







