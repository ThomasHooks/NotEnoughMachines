package com.kilroy790.notenoughmachines.blocks.machines.power;

import com.kilroy790.notenoughmachines.blocks.machines.MechanicalShaftBlock;
import com.kilroy790.notenoughmachines.tiles.machines.power.SmallCogTile;
import com.kilroy790.notenoughmachines.utilities.NEMBlockShapes;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;




public class SmallCogBlock extends MechanicalShaftBlock {

	public SmallCogBlock(Properties properties) {
		super(properties);
	}
	
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(MechanicalShaftBlock.AXIS)) {

		case X:
			return NEMBlockShapes.COGWHEEL[0];

		case Y:
			return NEMBlockShapes.COGWHEEL[1];

		case Z:
			return NEMBlockShapes.COGWHEEL[2];

		default:
			return NEMBlockShapes.COGWHEEL[0];
		}
	}

	
	
	@Override
	public ItemStack itemWhenDestroyed() {
		int rand = (int)(Math.random() * 4.0D);
		return new ItemStack(Items.STICK, 3 + rand);
	}

	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new SmallCogTile();
	}
}







