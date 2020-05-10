package com.kilroy790.notenoughmachines.api.lists;

import com.kilroy790.notenoughmachines.blocks.building.HammerHeadBlock;
import com.kilroy790.notenoughmachines.blocks.building.LinenBlock;
import com.kilroy790.notenoughmachines.blocks.crops.FlaxPlantBlock;
import com.kilroy790.notenoughmachines.blocks.logicgates.ANDGateBlock;
import com.kilroy790.notenoughmachines.blocks.logicgates.ORGateBlock;
import com.kilroy790.notenoughmachines.blocks.machines.logistic.ChuteBlock;
import com.kilroy790.notenoughmachines.blocks.machines.logistic.ClosedChuteBlock;
import com.kilroy790.notenoughmachines.blocks.machines.logistic.FilterBlock;
import com.kilroy790.notenoughmachines.blocks.machines.logistic.ItemPusherBlock;
import com.kilroy790.notenoughmachines.blocks.machines.power.AxleBlock;
import com.kilroy790.notenoughmachines.blocks.machines.power.CreativePowerBoxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.power.GearboxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.power.SmallWindWheelBlock;
import com.kilroy790.notenoughmachines.blocks.machines.processing.MillstoneBlock;

import net.minecraftforge.registries.ObjectHolder;




public class BlockList {

	@ObjectHolder("notenoughtmachines:linenblock")
	public static LinenBlock LINENBLOCK;
	
	@ObjectHolder("notenoughtmachines:hammerhead")
	public static HammerHeadBlock HAMMERHEAD;
	
	//Machines
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
	
	//Logic Gates
	@ObjectHolder("notenoughtmachines:andgate")
	public static ANDGateBlock ANDGATE;
	
	@ObjectHolder("notenoughtmachines:orgate")
	public static ORGateBlock ORGATE;
	
	//transport
	@ObjectHolder("notenoughtmachines:chute")
	public static ChuteBlock CHUTE;
	
	@ObjectHolder("notenoughtmachines:closedchute")
	public static ClosedChuteBlock CLOSEDCHUTE;
	
	@ObjectHolder("notenoughtmachines:itempusher")
	public static ItemPusherBlock ITEMPUSHER;
	
	@ObjectHolder("notenoughtmachines:filter")
	public static FilterBlock FILTER;
	
	//Crops
	@ObjectHolder("notenoughtmachines:flaxplant")
	public static FlaxPlantBlock FLAXPLANT;
}
