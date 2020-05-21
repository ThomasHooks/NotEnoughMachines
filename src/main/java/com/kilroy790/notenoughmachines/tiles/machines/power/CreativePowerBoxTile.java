package com.kilroy790.notenoughmachines.tiles.machines.power;

import java.util.ArrayList;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.api.power.MechanicalType;
import com.kilroy790.notenoughmachines.power.MechanicalInputOutput;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;

import net.minecraft.util.Direction;




public class CreativePowerBoxTile extends MechanicalTile {

	private ArrayList<MechanicalInputOutput> io = new ArrayList<MechanicalInputOutput>();
	
	public CreativePowerBoxTile() {
		super(5000, 0, MechanicalType.SOURCE, TileEntityList.CREATIVEPOWERBOX);
	}
	
	
	
	@Override
	public void onLoad() {
		io.add(new MechanicalInputOutput(this.pos.east(), Direction.EAST, true));
		io.add(new MechanicalInputOutput(this.pos.west(), Direction.WEST, true));
		io.add(new MechanicalInputOutput(this.pos.up(), Direction.UP, true));
		io.add(new MechanicalInputOutput(this.pos.down(), Direction.DOWN, true));
		io.add(new MechanicalInputOutput(this.pos.south(), Direction.SOUTH, true));
		io.add(new MechanicalInputOutput(this.pos.north(), Direction.NORTH, true));
		super.onLoad();
	}

	
	
	@Override
	protected void tickCustom() {}

	
	
	@Override
	public ArrayList<MechanicalInputOutput> getMechIO() {
		return io;
	}
}







