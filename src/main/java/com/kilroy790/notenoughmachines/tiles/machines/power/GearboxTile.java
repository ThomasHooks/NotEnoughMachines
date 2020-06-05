package com.kilroy790.notenoughmachines.tiles.machines.power;

import java.util.ArrayList;
import java.util.Map;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.blocks.machines.power.GearboxBlock;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;
import com.kilroy790.notenoughmachines.utilities.MachineIOList;

import net.minecraft.util.Direction;




public class GearboxTile extends MechanicalTile {

	private Map<Direction.Axis, ArrayList<MechanicalContext>> io;
	private int timer = 0;
	
	
	
	public GearboxTile() {
		super(72, 0, MechanicalType.CHANNEL, TileEntityList.GEARBOX);
	}
	
	
	
	@Override
	public void onLoad() {
		io = MachineIOList.biAxle(pos);
		super.onLoad();
	}

	
	
	@Override
	protected void tickCustom() {
		
		if(!world.isRemote) {
			if(timer >= VALIDATE_TICK) {
				updateBlockStatePowered(isPowered());
				timer = 0;
			}
			else timer++;
		}
	}

	
	
	@Override
	public ArrayList<MechanicalContext> getIO() {
		return io.get(getBlockState().get(GearboxBlock.AXIS));
	}
	


	protected void updateBlockStatePowered(boolean isPowered) {
		this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(GearboxBlock.POWERED, isPowered), 1 | 2 | 4);
	}
}







