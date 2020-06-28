package com.kilroy790.notenoughmachines.tiles.machines.power;

import java.util.ArrayList;
import java.util.Map;

import com.kilroy790.notenoughmachines.blocks.machines.MechanicalShaftBlock;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;
import com.kilroy790.notenoughmachines.utilities.MachineIOList;

import net.minecraft.util.Direction;




public class SmallCogTile extends MechanicalTile {

	private Map<Direction.Axis, ArrayList<MechanicalContext>> io;
	
	
	
	public SmallCogTile() {
		super(72, 0, MechanicalType.COG, NEMTiles.SMALLCOG.get());
	}

	
	
	@Override
	protected void tickCustom() {}
	
	
	
	@Override
	public void onLoad() {
		io = MachineIOList.smallCogwheel(pos);
		super.onLoad();
	}

	
	
	@Override
	public ArrayList<MechanicalContext> getIO() {
		return io.get(getBlockState().get(MechanicalShaftBlock.AXIS));
	}
}







