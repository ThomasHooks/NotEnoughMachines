package com.kilroy790.notenoughmachines.power;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;




public class MechanicalConnectionList 
{

	/**
	 * Creates a new 2-way mechanical axle connection lookup map
	 * <p>
	 * Use this when the machine's axis is either unknown or can change
	 * 
	 * @param pos The position of the machine
	 * 
	 * @return New 2-way mechanical axle connection map
	 */
	public static HashMap<Direction.Axis, ArrayList<MechanicalContext>> monoAxle(BlockPos pos) 
	{
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
	 * Creates a new 2-way mechanical axle connection array
	 * <p>
	 * The axis must be know ahead of time and must never change
	 * 
	 * @param pos The position of the machine
	 * @param axis The axis that the machine is aligned with
	 * 
	 * @return New 2-way mechanical axle connection array
	 */
	public static ArrayList<MechanicalContext> monoAxle(BlockPos pos, Direction.Axis axis) 
	{
		ArrayList<MechanicalContext> io;
		switch (axis) 
		{
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
	 * Creates a new 4-way mechanical axle connection lookup map
	 * <p>
	 * Use this when the machine's axis is either unknown or can change
	 * 
	 * @param pos The position of the machine
	 * 
	 * @return New 4-way mechanical axle connection map
	 */
	public static HashMap<Direction.Axis, ArrayList<MechanicalContext>> biAxle(BlockPos pos) 
	{
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
	 * Creates a new 4-way mechanical axle connection array
	 * <p>
	 * The axis must be know ahead of time and must never change
	 * 
	 * @param pos The position of the machine
	 * @param axis The axis that the machine is aligned with
	 * 
	 * @return New 4-way mechanical axle connection map
	 */
	public static ArrayList<MechanicalContext> biAxle(BlockPos pos, Direction.Axis axis) 
	{		
		ArrayList<MechanicalContext> io;
		switch (axis) 
		{
		case X:
			io = new ArrayList<MechanicalContext>(Arrays.asList(
						new MechanicalContext(pos.up(), Direction.UP, true), 
						new MechanicalContext(pos.down(), Direction.DOWN, true), 
						new MechanicalContext(pos.north(), Direction.NORTH, true), 
						new MechanicalContext(pos.south(), Direction.SOUTH, true)
						));
			break;

		case Y:
			io = new ArrayList<MechanicalContext>(Arrays.asList(
						new MechanicalContext(pos.east(), Direction.EAST, true), 
						new MechanicalContext(pos.west(), Direction.WEST, true), 
						new MechanicalContext(pos.north(), Direction.NORTH, true), 
						new MechanicalContext(pos.south(), Direction.SOUTH, true)
						));
			break;

		case Z:
			io = new ArrayList<MechanicalContext>(Arrays.asList(
						new MechanicalContext(pos.east(), Direction.EAST, true), 
						new MechanicalContext(pos.west(), Direction.WEST, true), 
						new MechanicalContext(pos.up(), Direction.UP, true), 
						new MechanicalContext(pos.down(), Direction.DOWN, true)
						));
			break;

		default:
			io = new ArrayList<MechanicalContext>();
			break;
		}
		
		return io;
	}



	/**
	 * Creates a new 6-way mechanical axle connection array
	 * 
	 * @param pos The position of the machine
	 * 
	 * @return New 6-way mechanical axle connection array
	 */
	public static ArrayList<MechanicalContext> triAxle(BlockPos pos) 
	{
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
	 * Creates a new mechanical small cogwheel connection lookup map
	 * <p>
	 * Use this when the machine's axis is either unknown or can change
	 * 
	 * @param pos The position of the machine
	 * 
	 * @return New mechanical small cogwheel connection map
	 */
	public static HashMap<Direction.Axis, ArrayList<MechanicalContext>> smallCogwheel(BlockPos pos) 
	{	
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
	
	
	
	/**
	 * Creates a new mechanical small cogwheel connection array 
	 * <p>
	 * The axis must be know ahead of time and must never change
	 * 
	 * @param pos The position of the machine
	 * @param axis The axis that the machine is aligned with
	 * 
	 * @return New mechanical small cogwheel connection array
	 */
	public static ArrayList<MechanicalContext> smallCogwheel(BlockPos pos, Direction.Axis axis) 
	{
		ArrayList<MechanicalContext> io;
		switch (axis) 
		{
		case X:
			io = new ArrayList<MechanicalContext>(Arrays.asList(
							new MechanicalContext(pos.east(), Direction.EAST, true), 
							new MechanicalContext(pos.west(), Direction.WEST, true),
							new MechanicalContext(pos.north(), Direction.NORTH, false), 
							new MechanicalContext(pos.south(), Direction.SOUTH, false),
							new MechanicalContext(pos.up(), Direction.UP, false), 
							new MechanicalContext(pos.down(), Direction.DOWN, false)
							));
			break;
			
		case Y:
			io = new ArrayList<MechanicalContext>(Arrays.asList(
							new MechanicalContext(pos.east(), Direction.EAST, false), 
							new MechanicalContext(pos.west(), Direction.WEST, false),
							new MechanicalContext(pos.north(), Direction.NORTH, false), 
							new MechanicalContext(pos.south(), Direction.SOUTH, false),
							new MechanicalContext(pos.up(), Direction.UP, true), 
							new MechanicalContext(pos.down(), Direction.DOWN, true)
							));
			break;
			
		case Z:
			io = new ArrayList<MechanicalContext>(Arrays.asList(
							new MechanicalContext(pos.east(), Direction.EAST, false), 
							new MechanicalContext(pos.west(), Direction.WEST, false),
							new MechanicalContext(pos.north(), Direction.NORTH, true), 
							new MechanicalContext(pos.south(), Direction.SOUTH, true),
							new MechanicalContext(pos.up(), Direction.UP, false), 
							new MechanicalContext(pos.down(), Direction.DOWN, false)
							));
			break;
			
		default:
			io = new ArrayList<MechanicalContext>();
			break;
		}
		
		return io;
	}
	
	
	
	/**
	 * Creates a new mechanical large cogwheel connection array
	 * <p>
	 * The axis must be know ahead of time and must never change
	 * 
	 * @param pos The position of the machine
	 * @param axis The axis that the machine is aligned with
	 * 
	 * @return New mechanical large cogwheel connection array
	 */
	public static ArrayList<MechanicalContext> largeCogwheel(BlockPos pos, Direction.Axis axis) 
	{
		ArrayList<MechanicalContext> io;
		switch (axis)
		{
		case X:
			io = new ArrayList<MechanicalContext>(Arrays.asList(
					
					new MechanicalContext(pos.north(2).up(), Direction.NORTH, false), 
					new MechanicalContext(pos.north(2), Direction.NORTH, false), 
					new MechanicalContext(pos.north(2).down(), Direction.NORTH, false), 
					
					new MechanicalContext(pos.south(2).up(), Direction.SOUTH, false),
					new MechanicalContext(pos.south(2), Direction.SOUTH, false),
					new MechanicalContext(pos.south(2).down(), Direction.SOUTH, false),
					
					new MechanicalContext(pos.up(2).north(), Direction.UP, false), 
					new MechanicalContext(pos.up(2), Direction.UP, false), 
					new MechanicalContext(pos.up(2).south(), Direction.UP, false), 
					
					new MechanicalContext(pos.down(2).north(), Direction.DOWN, false),
					new MechanicalContext(pos.down(2), Direction.DOWN, false),
					new MechanicalContext(pos.down(2).south(), Direction.DOWN, false),
					
					new MechanicalContext(pos.east(), Direction.EAST, true), 
					new MechanicalContext(pos.west(), Direction.WEST, true)
					));
			break;
			
		case Y:
			io = new ArrayList<MechanicalContext>(Arrays.asList(
					new MechanicalContext(pos.east(2).north(), Direction.EAST, false),  
					new MechanicalContext(pos.east(2), Direction.EAST, false),  
					new MechanicalContext(pos.east(2).south(), Direction.EAST, false),  
					
					new MechanicalContext(pos.west(2).north(), Direction.WEST, false),
					new MechanicalContext(pos.west(2), Direction.WEST, false),
					new MechanicalContext(pos.west(2).south(), Direction.WEST, false),
					
					new MechanicalContext(pos.north(2).west(), Direction.NORTH, false),
					new MechanicalContext(pos.north(2), Direction.NORTH, false),
					new MechanicalContext(pos.north(2).east(), Direction.NORTH, false),
					
					new MechanicalContext(pos.south(2).west(), Direction.SOUTH, false),
					new MechanicalContext(pos.south(2), Direction.SOUTH, false),
					new MechanicalContext(pos.south(2).east(), Direction.SOUTH, false),
					
					new MechanicalContext(pos.up(), Direction.UP, true), 
					new MechanicalContext(pos.down(), Direction.DOWN, true)
					));
			break;
			
		case Z:
			io = new ArrayList<MechanicalContext>(Arrays.asList(
					new MechanicalContext(pos.east(2).up(), Direction.EAST, false), 
					new MechanicalContext(pos.east(2), Direction.EAST, false), 
					new MechanicalContext(pos.east(2).down(), Direction.EAST, false), 
					
					new MechanicalContext(pos.west(2).up(), Direction.WEST, false),
					new MechanicalContext(pos.west(2), Direction.WEST, false),
					new MechanicalContext(pos.west(2).down(), Direction.WEST, false),
					
					new MechanicalContext(pos.up(2).east(), Direction.UP, false), 
					new MechanicalContext(pos.up(2), Direction.UP, false), 
					new MechanicalContext(pos.up(2).west(), Direction.UP, false), 
					
					new MechanicalContext(pos.down(2).east(), Direction.DOWN, false),
					new MechanicalContext(pos.down(2), Direction.DOWN, false),
					new MechanicalContext(pos.down(2).west(), Direction.DOWN, false),
					
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
}







