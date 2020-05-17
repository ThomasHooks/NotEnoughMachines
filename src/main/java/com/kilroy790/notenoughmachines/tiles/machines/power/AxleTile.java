package com.kilroy790.notenoughmachines.tiles.machines.power;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.api.power.MechanicalType;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalShaftBlock;
import com.kilroy790.notenoughmachines.power.MechanicalInputOutput;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;

import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.AxisAlignedBB;




public class AxleTile extends MechanicalTile {
	
	private Map<Direction.Axis, ArrayList<MechanicalInputOutput>> IO_LOOKUP = new HashMap<Direction.Axis, ArrayList<MechanicalInputOutput>>();
	
	public AxleTile() {
		super(72, 0, MechanicalType.CHANNEL, TileEntityList.AXLE_TILE);
	}
	
	
	
	@Override
	public void onLoad() {
		
		ArrayList<MechanicalInputOutput> X_IO = new ArrayList<MechanicalInputOutput>();
		ArrayList<MechanicalInputOutput> Y_IO = new ArrayList<MechanicalInputOutput>();
		ArrayList<MechanicalInputOutput> Z_IO = new ArrayList<MechanicalInputOutput>();
		X_IO.add(new MechanicalInputOutput(this.pos.east(), Direction.EAST, true));
		X_IO.add(new MechanicalInputOutput(this.pos.west(), Direction.WEST, true));
		Y_IO.add(new MechanicalInputOutput(this.pos.up(), Direction.UP, true));
		Y_IO.add(new MechanicalInputOutput(this.pos.down(), Direction.DOWN, true));
		Z_IO.add(new MechanicalInputOutput(this.pos.north(), Direction.NORTH, true));
		Z_IO.add(new MechanicalInputOutput(this.pos.south(), Direction.SOUTH, true));
		IO_LOOKUP.put(Direction.Axis.X, X_IO);
		IO_LOOKUP.put(Direction.Axis.Y, Y_IO);
		IO_LOOKUP.put(Direction.Axis.Z, Z_IO);
		
		super.onLoad();
	}

	
	
	@Override
	protected void tickCustom() {}

	
	
	@Override
	public ArrayList<MechanicalInputOutput> getMechIO() {
		Axis axis = this.getBlockState().get(MechanicalShaftBlock.AXIS);
		return IO_LOOKUP.get(axis);
	}
	
	
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos()).grow(1.0D);
	}
}







