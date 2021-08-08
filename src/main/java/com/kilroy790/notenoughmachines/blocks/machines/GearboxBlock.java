package com.kilroy790.notenoughmachines.blocks.machines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.kilroy790.notenoughmachines.power.MechanicalConnectionList;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.state.properties.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.tiles.GearboxTile;
import com.kilroy790.notenoughmachines.utilities.NEMBlockShapes;
import com.kilroy790.notenoughmachines.utilities.NEMInputHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
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




public class GearboxBlock extends MechanicalBlock 
{
	public static final BooleanProperty POWERED = NEMBlockStateProperties.POWERED;
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

	
	
	public GearboxBlock(Properties properties) 
	{
		super(properties);
		this.setDefaultState(getDefaultState()
				.with(AXIS, Direction.Axis.Y)
				.with(POWERED, Boolean.valueOf(false)));
	}
	
	
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		return super.getStateForPlacement(context).with(AXIS, context.getNearestLookingDirection().getAxis());
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		if (NEMInputHelper.isPressingShift()) 
		{
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("The side it's placed against will").mergeStyle(TextFormatting.GREEN));
			tooltip.add(new StringTextComponent("not be connected").mergeStyle(TextFormatting.GREEN));
		}
		else 
		{
			tooltip.add(new StringTextComponent(NEMInputHelper.MORE_INFO_PRESS_SHIFT));
		}
	}



	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return NEMBlockShapes.GEARBOX[NEMBlockShapes.AXIS_LOOKUP.get(state.get(AXIS))];
	}
	
	
	
	/**
	* Called periodically clientside on blocks near the player to show effects (like furnace fire particles). Note that
	* this method is unrelated to {@link randomTick} and {@link #needsRandomTick}, and will always be called regardless
	* of whether the block can receive random update ticks
	*/
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) 
	{
		if (stateIn.get(POWERED)) 
		{
			double d0 = (double)pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.75D;
			double d1 = (double)pos.getY() + 1.0D + (rand.nextDouble() - 0.5D) * 0.2D;
			double d2 = (double)pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.75D;
			worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	      
			if (rand.nextDouble() < 0.05D) 
			{
				worldIn.playSound(d0, d1, d2, SoundEvents.ENTITY_MINECART_INSIDE, SoundCategory.BLOCKS, 0.025F, 0.75F, false);
			}
		}
	}
	
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		super.fillStateContainer(builder);
		builder.add(AXIS, POWERED);
	}
	
	

	@Override
	public ItemStack itemWhenDestroyed() 
	{
		int rand = (int)(Math.random() * 4.0D);
		return new ItemStack(Items.OAK_WOOD, rand);
	}

	

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return new GearboxTile();
	}



	@Override
	public ArrayList<MechanicalContext> getIO(World world, BlockPos pos, BlockState state) 
	{
		return MechanicalConnectionList.biAxle(pos, state.get(AXIS));
	}
}







