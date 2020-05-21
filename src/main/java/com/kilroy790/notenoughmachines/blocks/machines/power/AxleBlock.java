package com.kilroy790.notenoughmachines.blocks.machines.power;

import com.kilroy790.notenoughmachines.api.lists.ShapeList;
import com.kilroy790.notenoughmachines.api.stateproperties.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalShaftBlock;
import com.kilroy790.notenoughmachines.tiles.machines.power.AxleTile;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;



//extends Block
public class AxleBlock extends MechanicalShaftBlock {
	
	
	public static final IntegerProperty AXLE_DIRECTION = NEMBlockStateProperties.AXLE_DIRECTION;
	
	//a POWER value of 3 means that the axle is not connected to a source/provider where a value of 15 means it's next to a source/provider
	public static final IntegerProperty POWER = NEMBlockStateProperties.POWER_DISTANCE_3_15;
	public static final int MINPOWERDISTANCE = 3;
	public static final int MAXPOWERDISTANCE = 15;
	
	public static final int TICKRATE = 10;
	
	public static final Direction[][] axisAlignment = {{Direction.UP, Direction.DOWN}, {Direction.NORTH, Direction.SOUTH}, {Direction.WEST, Direction.EAST}};
	public static final int AXELAXISX = 2;
	public static final int AXELAXISY = 0;
	public static final int AXELAXISZ = 1;
	
	

	public AxleBlock(String name) {
		super(Properties.create(Material.WOOD)
				.sound(SoundType.WOOD)
				.hardnessAndResistance(1.8f, 2.0f)
				.harvestTool(ToolType.AXE)
				.harvestLevel(0));
		
		this.setRegistryName(name);
	}
	
	
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(MechanicalShaftBlock.AXIS, context.getNearestLookingDirection().getAxis());
	}
	
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch(state.get(MechanicalShaftBlock.AXIS)) {

		case X:
			return ShapeList.AXLE[0];

		case Y:
			return ShapeList.AXLE[1];

		case Z:
			return ShapeList.AXLE[2];

		default:
			return ShapeList.AXLE[0];
		}
	}
	
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new AxleTile();
	}



	@Override
	public ItemStack itemWhenDestroyed() {
		int rand = (int)(Math.random() * 4.0D);
		return new ItemStack(Items.STICK, 3 + rand);
	}
}







