package com.kilroy790.notenoughmachines.api.lists;

import com.kilroy790.notenoughmachines.blocks.building.LinenBlock;
import com.kilroy790.notenoughmachines.blocks.crops.FlaxPlantBlock;
import com.kilroy790.notenoughmachines.blocks.machines.AxleBlock;
import com.kilroy790.notenoughmachines.blocks.machines.CreativePowerBoxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.GearboxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.MillstoneBlock;
import com.kilroy790.notenoughmachines.blocks.machines.SmallWindWheelBlock;

import net.minecraftforge.registries.ObjectHolder;

public class BlockList {

	@ObjectHolder("notenoughtmachines:linenblock")
	public static LinenBlock LINENBLOCK;
	
	//machine blocks
	@ObjectHolder("notenoughtmachines:creativepowerbox")
	public static CreativePowerBoxBlock CREATIVEPOWERBOX;
	
	@ObjectHolder("notenoughtmachines:smallwindwheel")
	public static SmallWindWheelBlock SMALLWINDWHEEL;
	
	@ObjectHolder("notenoughtmachines:gearbox")
	public static GearboxBlock GEARBOX;
	
	@ObjectHolder("notenoughtmachines:axle")
	public static AxleBlock AXLE;
	
	@ObjectHolder("notenoughtmachines:millstone")
	public static MillstoneBlock MILLSTONE;
	
	//Crops
	@ObjectHolder("notenoughtmachines:flaxplant")
	public static FlaxPlantBlock FLAXPLANT;
}
