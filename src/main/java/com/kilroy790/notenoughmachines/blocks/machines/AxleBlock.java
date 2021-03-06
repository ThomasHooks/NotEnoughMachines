package com.kilroy790.notenoughmachines.blocks.machines;

import java.util.ArrayList;

import com.kilroy790.notenoughmachines.power.MechanicalConnectionList;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.tiles.AxleTile;
import com.kilroy790.notenoughmachines.utilities.NEMBlockShapes;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;




public class AxleBlock extends MechanicalShaftBlock 
{
	public AxleBlock(Properties properties) 
	{
		super(properties);
	}



	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return NEMBlockShapes.AXLE[NEMBlockShapes.AXIS_LOOKUP.get(state.get(AXIS))];
	}



	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return new AxleTile();
	}



	@Override
	public ItemStack itemWhenDestroyed() 
	{
		int rand = (int)(Math.random() * 4.0D);
		return new ItemStack(Items.STICK, 3 + rand);
	}



	@Override
	public ArrayList<MechanicalContext> getIO(World world, BlockPos pos, BlockState state) 
	{
		return MechanicalConnectionList.monoAxle(pos, state.get(AXIS));
	}
}







