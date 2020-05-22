package com.kilroy790.notenoughmachines.tiles.machines.power;

import java.util.ArrayList;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.api.power.MechanicalType;
import com.kilroy790.notenoughmachines.power.MechanicalInputOutput;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;
import com.kilroy790.notenoughmachines.utilities.MachineIOList;




public class CreativePowerBoxTile extends MechanicalTile {

	private ArrayList<MechanicalInputOutput> io;
	
	public CreativePowerBoxTile() {
		super(5000, 0, MechanicalType.SOURCE, TileEntityList.CREATIVEPOWERBOX);
	}
	
	
	
	@Override
	public void onLoad() {
		io = MachineIOList.triAxle(pos);
		super.onLoad();
	}

	
	
	@Override
	protected void tickCustom() {}

	
	
	@Override
	public ArrayList<MechanicalInputOutput> getMechIO() {
		return io;
	}
}







