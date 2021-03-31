package com.kilroy790.notenoughmachines.tiles;

import com.kilroy790.notenoughmachines.blocks.machines.GearboxBlock;
import com.kilroy790.notenoughmachines.power.MechanicalType;




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







