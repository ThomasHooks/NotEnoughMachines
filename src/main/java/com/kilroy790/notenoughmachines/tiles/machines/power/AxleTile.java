package com.kilroy790.notenoughmachines.tiles.machines.power;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kilroy790.notenoughmachines.blocks.machines.MechanicalShaftBlock;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.AxisAlignedBB;




public class AxleTile extends MechanicalTile {
	
	private Map<Direction.Axis, ArrayList<MechanicalContext>> IO_LOOKUP = new HashMap<Direction.Axis, ArrayList<MechanicalContext>>();
	
	public AxleTile() {
		super(72, 0, MechanicalType.CHANNEL, NEMTiles.AXLE.get());
	}
	
	
	
	@Override
	public void onLoad() {
		
		ArrayList<MechanicalContext> X_IO = new ArrayList<MechanicalContext>();
		ArrayList<MechanicalContext> Y_IO = new ArrayList<MechanicalContext>();
		ArrayList<MechanicalContext> Z_IO = new ArrayList<MechanicalContext>();
		X_IO.add(new MechanicalContext(this.pos.east(), Direction.EAST, true));
		X_IO.add(new MechanicalContext(this.pos.west(), Direction.WEST, true));
		Y_IO.add(new MechanicalContext(this.pos.up(), Direction.UP, true));
		Y_IO.add(new MechanicalContext(this.pos.down(), Direction.DOWN, true));
		Z_IO.add(new MechanicalContext(this.pos.north(), Direction.NORTH, true));
		Z_IO.add(new MechanicalContext(this.pos.south(), Direction.SOUTH, true));
		IO_LOOKUP.put(Direction.Axis.X, X_IO);
		IO_LOOKUP.put(Direction.Axis.Y, Y_IO);
		IO_LOOKUP.put(Direction.Axis.Z, Z_IO);
		
		super.onLoad();
	}

	
	
	@Override
	protected void tickCustom() {}

	
	
	@Override
	public ArrayList<MechanicalContext> getIO() {
		Axis axis = this.getBlockState().get(MechanicalShaftBlock.AXIS);
		return IO_LOOKUP.get(axis);
	}
	
	
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos()).grow(1.0D);
	}
}







