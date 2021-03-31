package com.kilroy790.notenoughmachines.blocks.machines;

import java.util.ArrayList;

import com.kilroy790.notenoughmachines.power.MechanicalConnectionList;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.tiles.EnclosedAxleTile;
import com.kilroy790.notenoughmachines.utilities.NEMBlockShapes;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;




public class EnclosedAxleBlock extends MechanicalShaftBlock 
{
	public EnclosedAxleBlock(Properties properties) 
	{
		super(properties);
	}

	
	
	@Override
	public ArrayList<MechanicalContext> getIO(World world, BlockPos pos, BlockState state) 
	{
		return MechanicalConnectionList.monoAxle(pos, state.get(AXIS));
	}

	
	
	@Override
	public ItemStack itemWhenDestroyed() 
	{
		//TODO
		return null;
	}

	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return new EnclosedAxleTile();
	}
	
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return NEMBlockShapes.ENCLOSED_AXLE[NEMBlockShapes.AXIS_LOOKUP.get(state.get(AXIS))];
	}
	
	
	
	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{
		return BlockRenderType.MODEL;
	}
}



