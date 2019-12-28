package com.kilroy790.notenoughmachines.lists;

import com.kilroy790.notenoughmachines.blocks.FlaxPlantBlock;
import com.kilroy790.notenoughmachines.blocks.LinenBlock;
import com.kilroy790.notenoughmachines.blocks.machines.AxelBlock;
import com.kilroy790.notenoughmachines.blocks.machines.CreativePowerBoxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.GearBoxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.MillstoneBlock;

import net.minecraftforge.registries.ObjectHolder;

public class BlockList {

	@ObjectHolder("notenoughtmachines:linenblock")
	public static LinenBlock LINENBLOCK;
	
	//machine blocks
	@ObjectHolder("notenoughtmachines:creativepowerbox")
	public static CreativePowerBoxBlock CREATIVEPOWERBOX;
	
	@ObjectHolder("notenoughtmachines:gearbox")
	public static GearBoxBlock GEARBOX;
	
	@ObjectHolder("notenoughtmachines:axel")
	public static AxelBlock AXEL;
	
	@ObjectHolder("notenoughtmachines:millstone")
	public static MillstoneBlock MILLSTONE;
	
	//Crops
	@ObjectHolder("notenoughtmachines:flaxplant")
	public static FlaxPlantBlock FLAXPLANT;
}
