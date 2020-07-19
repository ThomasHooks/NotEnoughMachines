package com.kilroy790.notenoughmachines.tiles.machines.power;

import com.kilroy790.notenoughmachines.blocks.machines.power.GearboxBlock;
import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;




public class GearboxTile extends MechanicalTile {

	private int timer = 0;
		
	public GearboxTile() {
		super(72, 0, MechanicalType.SHAFT, NEMTiles.GEARBOX.get());
	}
	
	
	
	@Override
	public void tick() {
		if (!world.isRemote) {
			if (timer >= VALIDATE_TICK) {
				updateBlockStatePowered(isPowered());
				timer = 0;
			}
			else timer++;
		}
		super.tick();
	}
	


	protected void updateBlockStatePowered(boolean isPowered) {
		this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(GearboxBlock.POWERED, isPowered), 1 | 2 | 4);
	}
}







