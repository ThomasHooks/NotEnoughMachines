package com.kilroy790.notenoughmachines.blocks.machines;

import java.util.ArrayList;
import java.util.Objects;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.power.MechanicalConnectionList;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.state.properties.LargeCogwheelPart;
import com.kilroy790.notenoughmachines.state.properties.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.tiles.LargeCogwheelTile;
import com.kilroy790.notenoughmachines.tiles.MechanicalTile;
import com.kilroy790.notenoughmachines.utilities.NEMBlockShapes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;




public class LargeCogwheelBlock extends MechanicalShaftBlock 
{
	public static final EnumProperty<LargeCogwheelPart> PART = NEMBlockStateProperties.LARGECOGWHEELPART;

	
	
	public LargeCogwheelBlock(Properties properties) 
	{
		super(properties);
		this.setDefaultState(this.getDefaultState().with(PART, LargeCogwheelPart.MID));
	}



	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) 
	{
		super.onBlockPlacedBy(world, pos, state, placer, stack);
		BlockState shiftedState = world.getBlockState(pos);
		Direction.Axis axis = shiftedState.get(AXIS);
		switch (axis) 
		{
		//Axis is East/West
		case X:
			//Top row
			world.setBlockState(pos.add(0, 1, -1), state.with(PART, LargeCogwheelPart.TOPLEFT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(0, 1, 0), state.with(PART, LargeCogwheelPart.TOP).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(0, 1, 1), state.with(PART, LargeCogwheelPart.TOPRIGHT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);

			//Mid row
			world.setBlockState(pos.add(0, 0, -1), state.with(PART, LargeCogwheelPart.MIDLEFT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(0, 0, 1), state.with(PART, LargeCogwheelPart.MIDRIGHT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);

			//Bottom row
			world.setBlockState(pos.add(0, -1, -1), state.with(PART, LargeCogwheelPart.BOTTOMLEFT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(0, -1, 0), state.with(PART, LargeCogwheelPart.BOTTOM).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(0, -1, 1), state.with(PART, LargeCogwheelPart.BOTTOMRIGHT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			break;


			//Axis is Up/Down
		case Y:
			//Top row
			world.setBlockState(pos.add(-1, 0, -1), state.with(PART, LargeCogwheelPart.TOPLEFT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(0, 0, -1), state.with(PART, LargeCogwheelPart.TOP).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(1, 0, -1), state.with(PART, LargeCogwheelPart.TOPRIGHT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);

			//Mid row
			world.setBlockState(pos.add(-1, 0, 0), state.with(PART, LargeCogwheelPart.MIDLEFT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(1, 0, 0), state.with(PART, LargeCogwheelPart.MIDRIGHT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);

			//Bottom row
			world.setBlockState(pos.add(-1, 0, 1), state.with(PART, LargeCogwheelPart.BOTTOMLEFT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(0, 0, 1), state.with(PART, LargeCogwheelPart.BOTTOM).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(1, 0, 1), state.with(PART, LargeCogwheelPart.BOTTOMRIGHT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			break;


			//Axis is South/North
		case Z:
			//Top row
			world.setBlockState(pos.add(-1, 1, 0), state.with(PART, LargeCogwheelPart.TOPLEFT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(0, 1, 0), state.with(PART, LargeCogwheelPart.TOP).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(1, 1, 0), state.with(PART, LargeCogwheelPart.TOPRIGHT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);

			//Mid row
			world.setBlockState(pos.add(-1, 0, 0), state.with(PART, LargeCogwheelPart.MIDLEFT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(1, 0, 0), state.with(PART, LargeCogwheelPart.MIDRIGHT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);

			//Bottom row
			world.setBlockState(pos.add(-1, -1, 0), state.with(PART, LargeCogwheelPart.BOTTOMLEFT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(0, -1, 0), state.with(PART, LargeCogwheelPart.BOTTOM).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			world.setBlockState(pos.add(1, -1, 0), state.with(PART, LargeCogwheelPart.BOTTOMRIGHT).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
			break;

		default:
			throw new IllegalStateException(NotEnoughMachines.MODID + ":LargeCogwheelBlock is in an unknow state!");
		}
	}



	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		BlockState state = super.getStateForPlacement(context);
		BlockPos pos = context.getPos();
		Direction.Axis axis = state.get(AXIS);

		switch (axis) {

		//Axis is East/West
		case X:
			if (pos.getY() < 256 - 1
					&& context.getWorld().getBlockState(pos.add(0, 1, -1)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(0, 1, 0)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(0, 1, 1)).isReplaceable(context)
					&& context.getWorld().getBlockState(pos.add(0, 0, -1)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(0, 0, 1)).isReplaceable(context)
					&& context.getWorld().getBlockState(pos.add(0, -1, -1)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(0, -1, 0)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(0, -1, 1)).isReplaceable(context)) 
			{
				return state.with(PART, LargeCogwheelPart.MID);
			}
			else 
			{
				return null;
			}

			//Axis is Up/Down
		case Y:
			if (context.getWorld().getBlockState(pos.add(-1, 0, -1)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(0, 0, -1)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(1, 0, -1)).isReplaceable(context) 
					&& context.getWorld().getBlockState(pos.add(-1, 0, 0)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(1, 0, 0)).isReplaceable(context) 
					&& context.getWorld().getBlockState(pos.add(-1, 0, 1)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(0, 0, 1)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(1, 0, 1)).isReplaceable(context)) 
			{
				return state.with(PART, LargeCogwheelPart.MID);
			}
			else 
			{
				return null;
			}

			//Axis is South/North
		case Z:
			if (pos.getY() < 256 - 1
					&& context.getWorld().getBlockState(pos.add(-1, 1, 0)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(0, 1, 0)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(1, 1, 0)).isReplaceable(context)
					&& context.getWorld().getBlockState(pos.add(-1, 0, 0)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(1, 0, 0)).isReplaceable(context)
					&& context.getWorld().getBlockState(pos.add(-1, -1, 0)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(0, -1, 0)).isReplaceable(context) && context.getWorld().getBlockState(pos.add(1, -1, 0)).isReplaceable(context)) 
			{
				return state.with(PART, LargeCogwheelPart.MID);
			}
			else 
			{
				return null;
			}

		default:
			throw new IllegalStateException(NotEnoughMachines.MODID + ":LargeCogwheelBlock is in an unknow state!");
		}
	}



	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) 
	{		
		if (!world.isRemote() && !player.isCreative() && player.func_234569_d_(state))	//canHarvestBlock
		{
			spawnDrops(state.with(PART, LargeCogwheelPart.MID), world, pos, (TileEntity)null, player, player.getHeldItemMainhand());
		}
		super.onBlockHarvested(world, pos, state, player);
		setLargeCogwheelBlockState(world, pos, state, Blocks.AIR.getDefaultState(), 1 | 2 | 32);
	}



	@Override
	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) 
	{
		super.harvestBlock(worldIn, player, pos, Blocks.AIR.getDefaultState(), te, stack);
	}
	
	
	
	@Override
	public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion explosion) 
	{
		setLargeCogwheelBlockState(world, pos, state, Blocks.AIR.getDefaultState(), 1 | 2 | 32);
		spawnDrops(state.with(PART, LargeCogwheelPart.MID), world, pos);
		super.onBlockExploded(state, world, pos, explosion);
	}
	
	
	
	private void setLargeCogwheelBlockState(World world, BlockPos pos, BlockState currentState, BlockState newState, int flags) 
	{
		BlockPos centerPos = getMasterTile(world, pos, currentState).getPos();	
		switch (currentState.get(AXIS)) 
		{
		//Axis is East/West
		case X:
			//Top row
			world.setBlockState(centerPos.add(0, 1, -1), newState, flags);
			world.setBlockState(centerPos.add(0, 1, 0), newState, flags);
			world.setBlockState(centerPos.add(0, 1, 1), newState, flags);

			//Mid row
			world.setBlockState(centerPos.add(0, 0, -1), newState, flags);
			world.setBlockState(centerPos, newState, flags);
			world.setBlockState(centerPos.add(0, 0, 1), newState, flags);

			//Bottom row
			world.setBlockState(centerPos.add(0, -1, -1), newState, flags);
			world.setBlockState(centerPos.add(0, -1, 0), newState, flags);
			world.setBlockState(centerPos.add(0, -1, 1), newState, flags);
			break;


		//Axis is Up/Down
		case Y:			
			//Top row
			world.setBlockState(centerPos.add(-1, 0, -1), newState, flags);
			world.setBlockState(centerPos.add(0, 0, -1), newState, flags);
			world.setBlockState(centerPos.add(1, 0, -1), newState, flags);

			//Mid row
			world.setBlockState(centerPos.add(-1, 0, 0), newState, flags);
			world.setBlockState(centerPos, newState, flags);
			world.setBlockState(centerPos.add(1, 0, 0), newState, flags);

			//Bottom row
			world.setBlockState(centerPos.add(-1, 0, 1), newState, flags);
			world.setBlockState(centerPos.add(0, 0, 1), newState, flags);
			world.setBlockState(centerPos.add(1, 0, 1), newState, flags);
			break;


		//Axis is South/North
		case Z:
			//Top row
			world.setBlockState(centerPos.add(-1, 1, 0), newState, flags);
			world.setBlockState(centerPos.add(0, 1, 0), newState, flags);
			world.setBlockState(centerPos.add(1, 1, 0), newState, flags);

			//Mid row
			world.setBlockState(centerPos.add(-1, 0, 0), newState, flags);
			world.setBlockState(centerPos, newState, flags);
			world.setBlockState(centerPos.add(1, 0, 0), newState, flags);

			//Bottom row
			world.setBlockState(centerPos.add(-1, -1, 0), newState, flags);
			world.setBlockState(centerPos.add(0, -1, 0), newState, flags);
			world.setBlockState(centerPos.add(1, -1, 0), newState, flags);
			break;

		default:
			throw new IllegalStateException(NotEnoughMachines.MODID + ":LargeCogwheelBlock is in an unknow state!");
		}
	}
	
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		switch (state.get(PART)) 
		{
		case BOTTOM:
			return NEMBlockShapes.COGWHEEL_SIDE[NEMBlockShapes.AXIS_LOOKUP.get(state.get(AXIS))];
			
		case BOTTOMLEFT:
			return NEMBlockShapes.COGWHEEL_SIDE[NEMBlockShapes.AXIS_LOOKUP.get(state.get(AXIS))];
			
		case BOTTOMRIGHT:
			return NEMBlockShapes.COGWHEEL_SIDE[NEMBlockShapes.AXIS_LOOKUP.get(state.get(AXIS))];
			
		case MID:
			return NEMBlockShapes.COGWHEEL_CENTER[NEMBlockShapes.AXIS_LOOKUP.get(state.get(AXIS))];
			
		case MIDLEFT:
			return NEMBlockShapes.COGWHEEL_SIDE[NEMBlockShapes.AXIS_LOOKUP.get(state.get(AXIS))];
			
		case MIDRIGHT:
			return NEMBlockShapes.COGWHEEL_SIDE[NEMBlockShapes.AXIS_LOOKUP.get(state.get(AXIS))];
			
		case TOP:
			return NEMBlockShapes.COGWHEEL_SIDE[NEMBlockShapes.AXIS_LOOKUP.get(state.get(AXIS))];
			
		case TOPLEFT:
			return NEMBlockShapes.COGWHEEL_SIDE[NEMBlockShapes.AXIS_LOOKUP.get(state.get(AXIS))];
			
		case TOPRIGHT:
			return NEMBlockShapes.COGWHEEL_SIDE[NEMBlockShapes.AXIS_LOOKUP.get(state.get(AXIS))];
			
		default:
			return NEMBlockShapes.COGWHEEL_SIDE[NEMBlockShapes.AXIS_LOOKUP.get(state.get(AXIS))];
		}
	}



	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		super.fillStateContainer(builder);
		builder.add(PART);
	}



	@Override
	public ArrayList<MechanicalContext> getIO(World world, BlockPos pos, BlockState state) 
	{
		return MechanicalConnectionList.largeCogwheel(getMasterTile(world, pos, state).getPos(), state.get(AXIS));
	}



	@Override
	public ItemStack itemWhenDestroyed() 
	{
		int rand = (int)(Math.random() * 4.0D);
		return new ItemStack(Items.STICK, 12 + rand);
	}



	@Override
	public boolean hasTileEntity(BlockState state) 
	{
		return state.get(PART) == LargeCogwheelPart.MID;
	}



	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		if (state.get(PART) == LargeCogwheelPart.MID) 
		{
			return new LargeCogwheelTile();
		}
		else 
		{
			return null;
		}
	}



	@Override
	public MechanicalTile getTile(IWorld world, BlockPos pos, BlockState state) 
	{
		MechanicalTile tile = getMasterTile((World)world, pos, state) instanceof MechanicalTile ? (MechanicalTile)getMasterTile((World)world, pos, state) : null;
		return Objects.requireNonNull(tile, "MechanicalBlock: '" + state.getBlock().getRegistryName() + "' tile entity must be an instance of MechanicalTile!");
	}



	public TileEntity getMasterTile(World world, BlockPos pos, BlockState state) 
	{
		LargeCogwheelPart part = state.get(PART);
		Direction.Axis axis = state.get(AXIS);
		switch (part) 
		{
		case TOPLEFT:
			switch (axis) 
			{
			case X:
				return world.getTileEntity(pos.add(0, -1, 1));
			case Y:
				return world.getTileEntity(pos.add(1, 0, 1));
			case Z:
				return world.getTileEntity(pos.add(1, -1, 0));
			}

		case TOP:
			switch (axis) 
			{
			case X:
				return world.getTileEntity(pos.add(0, -1, 0));
			case Y:
				return world.getTileEntity(pos.add(0, 0, 1));
			case Z:
				return world.getTileEntity(pos.add(0, -1, 0));
			}

		case TOPRIGHT:
			switch (axis) 
			{
			case X:
				return world.getTileEntity(pos.add(0, -1, -1));
			case Y:
				return world.getTileEntity(pos.add(-1, 0, 1));
			case Z:
				return world.getTileEntity(pos.add(-1, -1, 0));
			}

		case MIDLEFT:
			switch (axis) 
			{
			case X:
				return world.getTileEntity(pos.add(0, 0, 1));
			case Y:
				return world.getTileEntity(pos.add(1, 0, 0));
			case Z:
				return world.getTileEntity(pos.add(1, 0, 0));
			}

		case MID:
			return world.getTileEntity(pos);

		case MIDRIGHT:
			switch (axis) 
			{
			case X:
				return world.getTileEntity(pos.add(0, 0, -1));
			case Y:
				return world.getTileEntity(pos.add(-1, 0, 0));
			case Z:
				return world.getTileEntity(pos.add(-1, 0, 0));
			}

		case BOTTOMLEFT:
			switch (axis) 
			{
			case X:
				return world.getTileEntity(pos.add(0, 1, 1));
			case Y:
				return world.getTileEntity(pos.add(1, 0, -1));
			case Z:
				return world.getTileEntity(pos.add(1, 1, 0));
			}

		case BOTTOM:
			switch (axis) 
			{
			case X:
				return world.getTileEntity(pos.add(0, 1, 0));
			case Y:
				return world.getTileEntity(pos.add(0, 0, -1));
			case Z:
				return world.getTileEntity(pos.add(0, 1, 0));
			}

		case BOTTOMRIGHT:
			switch (axis) 
			{
			case X:
				return world.getTileEntity(pos.add(0, 1, -1));
			case Y:
				return world.getTileEntity(pos.add(-1, 0, -1));
			case Z:
				return world.getTileEntity(pos.add(-1, 1, 0));
			}

		default:
			throw new IllegalStateException("Unable to get LargeCogwheelBlock's tile entity!");
		}
	}
}



