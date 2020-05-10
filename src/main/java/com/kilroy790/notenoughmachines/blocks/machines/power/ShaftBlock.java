package com.kilroy790.notenoughmachines.blocks.machines.power;

import com.kilroy790.notenoughmachines.api.lists.ShapeList;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalShaftBlock;
import com.kilroy790.notenoughmachines.tiles.machines.power.AxleTile;

import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;




public class ShaftBlock extends MechanicalShaftBlock {

	public ShaftBlock(String name, Properties properties) {
		super(properties);
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







