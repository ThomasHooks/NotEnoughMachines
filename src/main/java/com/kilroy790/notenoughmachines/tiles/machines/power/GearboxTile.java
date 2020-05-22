package com.kilroy790.notenoughmachines.tiles.machines.power;

import java.util.ArrayList;
import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.api.power.MechanicalType;
import com.kilroy790.notenoughmachines.blocks.machines.power.GearboxBlock;
import com.kilroy790.notenoughmachines.power.MechanicalInputOutput;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;
import com.kilroy790.notenoughmachines.utilities.MachineIOList;

import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;




public class GearboxTile extends MechanicalTile {

	private ArrayList<MechanicalInputOutput> io;
	private int timer = 0;
			
	public GearboxTile() {
		super(72, 0, MechanicalType.CHANNEL, TileEntityList.GEARBOX);
	}
	
	
	
	@Override
	public void onLoad() {
		io = MachineIOList.triAxle(pos);
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
	public ArrayList<MechanicalInputOutput> getMechIO() {
		return io;
	}
	


	protected void updateBlockStatePowered(boolean isPowered) {
		Direction facing = this.world.getBlockState(pos).get(BlockStateProperties.FACING);
		this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(BlockStateProperties.FACING, facing).with(GearboxBlock.getPowered(), isPowered), 1 | 2);
	}
}







