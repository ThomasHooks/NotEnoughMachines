package com.kilroy790.notenoughmachines.lists;

import com.kilroy790.notenoughmachines.blocks.AxelBlock;
import com.kilroy790.notenoughmachines.blocks.FlaxPlantBlock;
import com.kilroy790.notenoughmachines.blocks.GearBoxBlock;
import com.kilroy790.notenoughmachines.blocks.LinenBlock;
import com.kilroy790.notenoughmachines.blocks.MillstoneBlock;

import net.minecraftforge.registries.ObjectHolder;

public class BlockList {

	@ObjectHolder("notenoughtmachines:linenblock")
	public static LinenBlock LINENBLOCK;
	
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
