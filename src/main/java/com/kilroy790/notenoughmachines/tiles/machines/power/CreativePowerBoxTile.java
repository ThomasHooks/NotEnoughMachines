package com.kilroy790.notenoughmachines.tiles.machines.power;

import java.util.ArrayList;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.api.power.MechanicalType;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;
import com.kilroy790.notenoughmachines.utilities.MachineIOList;




public class CreativePowerBoxTile extends MechanicalTile {

	private ArrayList<MechanicalContext> io;
	
	public CreativePowerBoxTile() {
		super(5000, 0, MechanicalType.SOURCE, TileEntityList.CREATIVEPOWERBOX);
	}
	
	
	
	@Override
	public void onLoad() {
		io = MachineIOList.triAxle(pos);
		super.onLoad();
	}

	
	
	@Override
	protected void tickCustom() {
		
		if(!world.isRemote()) changeSpeed(this, 33.3f);
	}

	
	
	@Override
	public ArrayList<MechanicalContext> getIO() {
		return io;
	}
}







