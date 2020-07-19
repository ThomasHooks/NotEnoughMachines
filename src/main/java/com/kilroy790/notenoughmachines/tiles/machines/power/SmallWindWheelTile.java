package com.kilroy790.notenoughmachines.tiles.machines.power;

import com.kilroy790.notenoughmachines.blocks.machines.power.SmallWindWheelBlock;
import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.setup.NEMBlocks;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class SmallWindWheelTile extends MechanicalTile {
	
	protected float speedModifier = 1.0f;
	private static final int BASE_POWER_CAPACITY = 200;
	private static final float BASE_SPEED = 12.0f;
	public static final int WINDWHEEL_RADIUS = 8;
	
	public SmallWindWheelTile() {
		super(BASE_POWER_CAPACITY, 0, MechanicalType.SOURCE, NEMTiles.SMALLWINDWHEEL.get());
	}
	
	
	
	@Override
	public void tick() {
		if (!this.world.isRemote()) {
			if (world.getGameTime() % 40 == 1) {
				updateWindSpeed();
				setCapacity((int)(BASE_POWER_CAPACITY * speedModifier));
			}
			if (this.isPowered()) changeSpeed(this, BASE_SPEED * (float)speedModifier);
		}
		super.tick();
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos()).grow((double)WINDWHEEL_RADIUS);
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 4096.0D * 4.0D;
	}
	
	
	
	protected void updateWindSpeed() {
		
		if (!validateArea()) this.speedModifier = 0.0f;
		else if (world.isThundering()) this.speedModifier = 2.0f;
		else if (world.isRaining()) this.speedModifier = 1.33f;
		else this.speedModifier = 1.0f;
	}
	
	
	
	/**
	 * @return True if the area is valid
	 */
	public boolean validateArea() {

		boolean valid = false;
		if (world.canBlockSeeSky(pos) && world.getBlockState(pos).getBlock() == NEMBlocks.SMALLWINDWHEEL.get()) {

			Direction direction = this.getBlockState().get(SmallWindWheelBlock.FACING);
			for (int y = -WINDWHEEL_RADIUS; y <= WINDWHEEL_RADIUS; y++) {
				for (int hor = -WINDWHEEL_RADIUS; hor <= WINDWHEEL_RADIUS; hor++) {

					int x = 0;
					int z = 0;
					if (direction == Direction.NORTH || direction == Direction.SOUTH) x = hor;
					else z = hor;

					BlockPos nextPos = pos.add(x, y, z);
					if (x == 0 && y == 0 && z == 0) continue;

					valid = world.isAirBlock(nextPos);
					if (!valid) return false;
				}
			}
		}
		return valid;
	}



	@Override
	protected void readCustom(CompoundNBT compound) {
		this.speedModifier = compound.getFloat("speedfactor");
		super.readCustom(compound);
	}



	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) {
		compound.putFloat("speedfactor", this.speedModifier);
		return super.writeCustom(compound);
	}
}







