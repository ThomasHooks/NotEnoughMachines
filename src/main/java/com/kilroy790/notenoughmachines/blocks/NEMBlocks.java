package com.kilroy790.notenoughmachines.blocks;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.logicgates.ANDGateBlock;
import com.kilroy790.notenoughmachines.blocks.logicgates.ORGateBlock;
import com.kilroy790.notenoughmachines.blocks.machines.AxleBlock;
import com.kilroy790.notenoughmachines.blocks.machines.ChuteBlock;
import com.kilroy790.notenoughmachines.blocks.machines.CreativePowerBoxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.EnclosedAxleBlock;
import com.kilroy790.notenoughmachines.blocks.machines.FilterBlock;
import com.kilroy790.notenoughmachines.blocks.machines.GearboxBlock;
import com.kilroy790.notenoughmachines.blocks.machines.ItemPusherBlock;
import com.kilroy790.notenoughmachines.blocks.machines.LargeCogwheelBlock;
import com.kilroy790.notenoughmachines.blocks.machines.MillstoneBlock;
import com.kilroy790.notenoughmachines.blocks.machines.SmallCogwheelBlock;
import com.kilroy790.notenoughmachines.blocks.machines.SmallWindWheelBlock;
import com.kilroy790.notenoughmachines.blocks.machines.TripHammerBlock;
import com.kilroy790.notenoughmachines.blocks.machines.TubWheelBlock;

import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;




public class NEMBlocks 
{
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NotEnoughMachines.MODID);

	//Building Blocks
	public static final RegistryObject<LinenBlock> LINENBLOCK = BLOCKS.register("linenblock", 
			()-> new LinenBlock(Block.Properties
					.create(Material.WOOL)
					.sound(SoundType.CLOTH)
					.hardnessAndResistance(0.8f, 2.0f)
					.harvestLevel(0)));
	
	
	
	public static final RegistryObject<Block> COPPERORE = BLOCKS.register("copper_ore", 
			()-> new Block(Block.Properties
					.create(Material.ROCK)
					.sound(SoundType.STONE)
					.hardnessAndResistance(2.4f, 2.4f)
					.harvestTool(ToolType.PICKAXE)
					.harvestLevel(1)));
	
	
	
	public static final RegistryObject<Block> FLUXSTONE = BLOCKS.register("fluxstone", 
			()-> new Block(Block.Properties
					.create(Material.ROCK)
					.sound(SoundType.STONE)
					.hardnessAndResistance(1.5f, 3.0f)
					.harvestTool(ToolType.PICKAXE)
					.harvestLevel(0)));
	
	
	
	public static final RegistryObject<Block> FLUXSTONE_SLAB = BLOCKS.register("fluxstone_slab", 
			()-> new SlabBlock(Block.Properties.from(FLUXSTONE.get())));
	
	
	
	public static final RegistryObject<Block> FLUXSTONE_STAIRS = BLOCKS.register("fluxstone_stairs", 
			()-> new StairsBlock(() -> FLUXSTONE.get().getDefaultState(), Block.Properties.from(FLUXSTONE.get())));
	
	
	
	public static final RegistryObject<Block> FLUXSTONE_WALL = BLOCKS.register("fluxstone_wall", 
			()-> new WallBlock(Block.Properties.from(FLUXSTONE.get())));
	
	
	
	public static final RegistryObject<Block> FLUXSTONE_BUTTON = BLOCKS.register("fluxstone_button", 
			()-> new NEMButtonBlock(Block.Properties.from(FLUXSTONE.get()).doesNotBlockMovement()));
	
	
	
	public static final RegistryObject<Block> FLUXSTONE_PRESSURE_PLATE = BLOCKS.register("fluxstone_pressure_plate", 
			()-> new NEMPressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, Block.Properties.from(FLUXSTONE.get()).doesNotBlockMovement()));
	
	
	
	public static final RegistryObject<Block> POLISHED_FLUXSTONE = BLOCKS.register("polished_fluxstone", 
			()-> new Block(Block.Properties
					.create(Material.ROCK)
					.sound(SoundType.STONE)
					.hardnessAndResistance(1.5f, 3.0f)
					.harvestTool(ToolType.PICKAXE)
					.harvestLevel(0)));
	
	
	
	public static final RegistryObject<Block> POLISHED_FLUXSTONE_SLAB = BLOCKS.register("polished_fluxstone_slab", 
			()-> new SlabBlock(Block.Properties.from(POLISHED_FLUXSTONE.get())));
	
	
	
	public static final RegistryObject<Block> POLISHED_FLUXSTONE_STAIRS = BLOCKS.register("polished_fluxstone_stairs", 
			()-> new StairsBlock(() -> POLISHED_FLUXSTONE.get().getDefaultState(), Block.Properties.from(POLISHED_FLUXSTONE.get())));
	
	
	
	public static final RegistryObject<Block> POLISHED_FLUXSTONE_WALL = BLOCKS.register("polished_fluxstone_wall", 
			()-> new WallBlock(Block.Properties.from(POLISHED_FLUXSTONE.get())));
	
	
	
	public static final RegistryObject<Block> POLISHED_FLUXSTONE_BUTTON = BLOCKS.register("polished_fluxstone_button", 
			()-> new NEMButtonBlock(Block.Properties.from(POLISHED_FLUXSTONE.get()).doesNotBlockMovement()));
	
	
	
	public static final RegistryObject<Block> POLISHED_FLUXSTONE_PRESSURE_PLATE = BLOCKS.register("polished_fluxstone_pressure_plate", 
			()-> new NEMPressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, Block.Properties.from(POLISHED_FLUXSTONE.get()).doesNotBlockMovement()));
	
	
	
	public static final RegistryObject<PanelBlock> CUT_PANLE = BLOCKS.register("cut_panle", 
			()-> new PanelBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));

	
	
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

	
	
	public static final RegistryObject<EnclosedAxleBlock> ENCLOSED_AXLE = BLOCKS.register("enclosedaxle", 
			()-> new EnclosedAxleBlock(Block.Properties
					.create(Material.WOOD)
					.sound(SoundType.WOOD)
					.hardnessAndResistance(1.8f, 2.0f)
					.harvestTool(ToolType.AXE)
					.harvestLevel(0)));
	
	
	
	public static final RegistryObject<SmallCogwheelBlock> SMALLCOG = BLOCKS.register("smallcog", 
			()-> new SmallCogwheelBlock(Block.Properties
					.create(Material.WOOD)
					.sound(SoundType.WOOD)
					.hardnessAndResistance(1.8f, 2.0f)
					.harvestTool(ToolType.AXE)
					.harvestLevel(0)));
	
	
	
	public static final RegistryObject<LargeCogwheelBlock> LARGECOG = BLOCKS.register("largecog", 
			()-> new LargeCogwheelBlock(Block.Properties
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
	
	
	
	public static final RegistryObject<TripHammerBlock> TRIPHAMMER = BLOCKS.register("triphammer", 
			()-> new TripHammerBlock(Block.Properties
					.create(Material.WOOD)
					.sound(SoundType.ANVIL)
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
					.create(Material.PLANTS)
					.doesNotBlockMovement()
					.sound(SoundType.CROP)
					.tickRandomly()
					.harvestLevel(0)));



	public static void registerAll(IEventBus modEventBus) 
	{
		BLOCKS.register(modEventBus);
	}
}







