package com.kilroy790.notenoughmachines.blocks.machines.power;

import java.util.ArrayList;
import java.util.List;

import com.kilroy790.notenoughmachines.blocks.machines.MechanicalBlock;
import com.kilroy790.notenoughmachines.power.MechanicalConnectionList;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.tiles.machines.power.TubWheelTile;
import com.kilroy790.notenoughmachines.utilities.NEMBlockShapes;
import com.kilroy790.notenoughmachines.utilities.NEMInputHelper;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class TubWheelBlock extends MechanicalBlock 
{
	public TubWheelBlock(Properties properties) 
	{
		super(properties);
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		if (NEMInputHelper.isPressingShift()) 
		{
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("Creates mechanical power from flowing water").applyTextStyle(TextFormatting.GREEN));
		}
		else 
		{
			tooltip.add(new StringTextComponent(NEMInputHelper.MORE_INFO_PRESS_SHIFT));
		}
	}

	
	
	@Override
	public ItemStack itemWhenDestroyed() 
	{
		int rand = (int)(Math.random() * 4.0D);
		return new ItemStack(Items.OAK_SLAB, rand + 3);
	}
	
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return NEMBlockShapes.TUBWHEEL;
	}
	
	
	
	/**
	 * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
	 * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	 * @deprecated call via {@link IBlockState#getRenderType()} whenever possible. Implementing/overriding is fine.
	 */
	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{ 
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return new TubWheelTile();
	}



	@Override
	public ArrayList<MechanicalContext> getIO(World world, BlockPos pos, BlockState state) 
	{
		return MechanicalConnectionList.monoAxle(pos, Direction.Axis.Y);
	}
}







