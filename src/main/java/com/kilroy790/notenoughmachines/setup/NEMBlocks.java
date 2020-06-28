package com.kilroy790.notenoughmachines.setup;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.building.HammerHeadBlock;
import com.kilroy790.notenoughmachines.blocks.building.LinenBlock;
import com.kilroy790.notenoughmachines.blocks.crops.FlaxPlantBlock;
import com.kilroy790.notenoughmachines.blocks.logicgates.ANDGateBlock;
import com.kilroy790.notenoughmachines.blocks.logicgates.ORGateBlock;
import com.kilroy790.notenoughmachines.blocks.machines.logistic.ChuteBlock;
import com.kilroy790.notenoughmachines.blocks.machines.logistic.FilterBlock;
import com.kilroy790.notenoughmachines.blocks.machines.logistic.ItemPusherBlock;
import com.kilroy790.notenoughmachines.blocks.machines.power.AxleBlock;
import com.kilroy790.notenoughmachines.blocks.machines.power.CreativePowerBoxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.power.GearboxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.power.SmallCogBlock;
import com.kilroy790.notenoughmachines.blocks.machines.power.SmallWindWheelBlock;
import com.kilroy790.notenoughmachines.blocks.machines.power.TubWheelBlock;
import com.kilroy790.notenoughmachines.blocks.machines.processing.MillstoneBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;




public class NEMBlocks {

	private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, NotEnoughMachines.MODID);

	//Building Blocks
	public static final RegistryObject<LinenBlock> LINENBLOCK = BLOCKS.register("linenblock", 
			()-> new LinenBlock(Block.Properties
					.create(Material.WOOL)
					.sound(SoundType.CLOTH)
					.hardnessAndResistance(0.8f, 2.0f)
					.harvestLevel(0)));

	public static final RegistryObject<HammerHeadBlock> HAMMERHEAD = BLOCKS.register("hammerhead", 
			()-> new HammerHeadBlock(Block.Properties
					.create(Material.ANVIL)
					.hardnessAndResistance(5.0f, 1200.0f)
					.harvestTool(ToolType.PICKAXE)
					.harvestLevel(2)
					.sound(SoundType.ANVIL)));

	//Machine Blocks
	public static final RegistryObject<CreativePowerBoxBlock> CREATIVEPOWERBOX = BLOCKS.register("creativepowerbox", 
			()-> new CreativePowerBoxBlock(Block.Properties
					.create(Material.WOOD)
					.sound(SoundType.WOOD)
					.hardnessAndResistance(1.8f, 2.0f)
					.harvestTool(ToolType.AXE)
					.harvestLevel(0)));

	public static final RegistryObject<SmallWindWheelBlock> SMALLWINDWHEEL = BLOCKS.register("smallwindwheel", 
			()-> new SmallWindWheelBlock(Block.Properties
					.create(Material.WOOD)
					.sound(SoundType.WOOD)
					.hardnessAndResistance(1.8f, 2.0f)
					.harvestTool(ToolType.AXE)
					.harvestLevel(0)));

	public static final RegistryObject<TubWheelBlock> TUBWHEEL = BLOCKS.register("tubwheel", 
			()-> new TubWheelBlock(Block.Properties
					.create(Material.WOOD)
					.hardnessAndResistance(1.8f, 2.0f)
					.harvestTool(ToolType.AXE)
					.harvestLevel(0)
					.sound(SoundType.WOOD)));

	public static final RegistryObject<GearboxBlock> GEARBOX = BLOCKS.register("gearbox", 
			()-> new GearboxBlock(Block.Properties
					.create(Material.WOOD)
					.sound(SoundType.WOOD)
					.hardnessAndResistance(1.8f, 2.0f)
					.harvestTool(ToolType.AXE)
					.harvestLevel(0)));

	public static final RegistryObject<AxleBlock> AXLE = BLOCKS.register("axle", 
			()-> new AxleBlock(Block.Properties
					.create(Material.WOOD)
					.sound(SoundType.WOOD)
					.hardnessAndResistance(1.8f, 2.0f)
					.harvestTool(ToolType.AXE)
					.harvestLevel(0)));
	
	public static final RegistryObject<SmallCogBlock> SMALLCOG = BLOCKS.register("smallcog", 
			()-> new SmallCogBlock(Block.Properties
					.create(Material.WOOD)
					.sound(SoundType.WOOD)
					.hardnessAndResistance(1.8f, 2.0f)
					.harvestTool(ToolType.AXE)
					.harvestLevel(0)));

	public static final RegistryObject<MillstoneBlock> MILLSTONE = BLOCKS.register("millstone", 
			()-> new MillstoneBlock(Block.Properties
					.create(Material.ROCK)
					.sound(SoundType.STONE)
					.hardnessAndResistance(2.8f, 3.0f)
					.harvestTool(ToolType.PICKAXE)
					.harvestLevel(0)));

	//Logic Gate Blocks
	public static final RegistryObject<ANDGateBlock> ANDGATE = BLOCKS.register("andgate", 
			()-> new ANDGateBlock(Block.Properties
					.create(Material.MISCELLANEOUS)
					.hardnessAndResistance(0.0f)
					.sound(SoundType.WOOD)));

	public static final RegistryObject<ORGateBlock> ORGATE = BLOCKS.register("orgate", 
			()-> new ORGateBlock(Block.Properties
					.create(Material.MISCELLANEOUS)
					.hardnessAndResistance(0.0f)
					.sound(SoundType.WOOD)));

	//Transport Blocks
	public static final RegistryObject<ChuteBlock> CHUTE = BLOCKS.register("chute", 
			()-> new ChuteBlock(Block.Properties
					.create(Material.MISCELLANEOUS)
					.hardnessAndResistance(1.8f, 2.0f)
					.sound(SoundType.WOOD)
					.harvestTool(ToolType.AXE)
					.harvestLevel(0)));

	public static final RegistryObject<FilterBlock> FILTER = BLOCKS.register("filter", 
			()-> new FilterBlock(Block.Properties
					.create(Material.WOOD)
					.hardnessAndResistance(1.2f, 2.0f)
					.sound(SoundType.WOOD)
					.harvestTool(ToolType.AXE)
					.harvestLevel(0)));

	public static final RegistryObject<ItemPusherBlock> ITEMPUSHER = BLOCKS.register("itempusher", 
			()-> new ItemPusherBlock(Block.Properties
					.create(Material.IRON)
					.hardnessAndResistance(1.2f, 2.0f)
					.sound(SoundType.METAL)
					.harvestTool(ToolType.PICKAXE)
					.harvestLevel(0)));

	//Crops Blocks
	public static final RegistryObject<FlaxPlantBlock> FLAXPLANT = BLOCKS.register("flaxplant", 
			()-> new FlaxPlantBlock(Block.Properties
					.create(Material.PLANTS).doesNotBlockMovement()
					.sound(SoundType.CROP)
					.tickRandomly()
					.harvestLevel(0)));



	public static void registerAll(IEventBus modEventBus) {
		BLOCKS.register(modEventBus);
	}
}







