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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class AxleTile extends MechanicalTile {
	
	private Map<Direction.Axis, ArrayList<MechanicalContext>> io;
	
	
	
	public AxleTile() {
		super(72, 0, MechanicalType.SHAFT, NEMTiles.AXLE.get());
	}
	
	
	
	@Override
	public void onLoad() {
		io = MachineIOList.monoAxle(pos);
		super.onLoad();
	}

	
	
	@Override
	protected void tickCustom() {}

	
	
	@Override
	public ArrayList<MechanicalContext> getIO() {
		return io.get(getBlockState().get(MechanicalShaftBlock.AXIS));
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos()).grow(1.0D);
	}
}







