package com.kilroy790.notenoughmachines.blocks.machines;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.multiblocks.IMultiblockPart;
import com.kilroy790.notenoughmachines.power.MechanicalConnectionList;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.state.properties.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.state.properties.RollingMillPart;
import com.kilroy790.notenoughmachines.tiles.MechanicalTile;
import com.kilroy790.notenoughmachines.tiles.RollingMillTile;
import com.kilroy790.notenoughmachines.utilities.NEMBlockShapes;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;




public class RollingMillBlock extends MechanicalHorizontalBlock implements IMultiblockPart
{
	public static final EnumProperty<RollingMillPart> PART = NEMBlockStateProperties.ROLLING_MILL_PART;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	
	

	public RollingMillBlock(Properties properties) 
	{
		super(properties);
		this.setDefaultState(this.getDefaultState().with(PART, RollingMillPart.BASE).with(LIT, Boolean.valueOf(false)));
	}
	
	
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) 
	{
		if (world.isRemote) 
		{
			return ActionResultType.SUCCESS;
		}
		else 
		{
			TileEntity entity = getMasterTile(world, pos, state);
			if (entity instanceof INamedContainerProvider) 
				NetworkHooks.openGui((ServerPlayerEntity)player, (INamedContainerProvider)entity, entity.getPos()); 
			else 
				throw new IllegalStateException("Rolling Mill container provider is missing!");
			return ActionResultType.SUCCESS;
		}
	}

	
	
	@Override
	public TileEntity getMasterTile(World world, BlockPos pos, BlockState state) 
	{
		switch (state.get(PART))
		{
		case BASE:
			return world.getTileEntity(pos);
			
		case TOP:
			return world.getTileEntity(pos.down(1));
			
		default:
			throw new IllegalStateException("Unable to get rolling mill tile entity!");
		}
	}
	
	
	
	@Override
	public MechanicalTile getTile(IWorld world, BlockPos pos, BlockState state) 
	{
		MechanicalTile tile = getMasterTile((World)world, pos, state) instanceof MechanicalTile ? (MechanicalTile)getMasterTile((World)world, pos, state) : null;
		return Objects.requireNonNull(tile, "'MechanicalBlock:" + state.getBlock().getRegistryName() + "' tile entity must be an instance of MechanicalTile!");
	}
	
	
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) 
	{
		super.onBlockPlacedBy(world, pos, state, placer, stack);
		
		BlockState shiftedState = world.getBlockState(pos);
		world.setBlockState(pos.up(1), state.with(PART, RollingMillPart.TOP).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
	}
	
	
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) 
	{
		BlockPos pos = context.getPos();
		if (pos.getY() < 256 - getMultiblockHight() && context.getWorld().getBlockState(pos.up(1)).isReplaceable(context)) 
			return super.getStateForPlacement(context).with(FACING, context.getPlacementHorizontalFacing()).with(PART, RollingMillPart.BASE);
		else 
			return null;
	}
	
	
	
	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) 
	{
		TileEntity tile = getMasterTile(world, pos, state);
		if (tile != null) 
			NEMItemHelper.dropItemHandlerInventory(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(null), world, pos);
		
		if (!world.isRemote && !player.isCreative() && player.func_234569_d_(state)) //canHarvestBlock
			spawnDrops(state.with(PART, RollingMillPart.BASE), world, pos, (TileEntity)null, player, player.getHeldItemMainhand());
		
		super.onBlockHarvested(world, pos, state, player);
		
		switch (state.get(PART)) 
		{
		case BASE:
			world.setBlockState(pos.up(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			break;
			
		case TOP:
			world.setBlockState(pos.down(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			break;
			
		default:
			throw new IllegalStateException(NotEnoughMachines.MODID + ":rolling mill block is in an unknown state!");
		}
	}
	
	
	
	@Override
	public void harvestBlock(World world, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) 
	{
		super.harvestBlock(world, player, pos, Blocks.AIR.getDefaultState(), te, stack);
	}
	
	
	
	@Override
	public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion explosion)
	{
		TileEntity tile = getMasterTile(world, pos, state);
		if (tile != null) 
			NEMItemHelper.dropItemHandlerInventory(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(null), world, pos);
		
		switch (state.get(PART)) 
		{
		case BASE:
			world.setBlockState(pos.up(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			break;
			
		case TOP:
			world.setBlockState(pos.down(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			break;
			
		default:
			throw new IllegalStateException(NotEnoughMachines.MODID + ":rolling mill is in an unknown state!");
		}
		
		super.onBlockExploded(state, world, pos, explosion);
	}



	@Override
	@OnlyIn(Dist.CLIENT) 
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) 
	{
		if (stateIn.get(LIT)) 
		{
			double d0 = (double)pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.75D;
			double d1 = (double)pos.getY() + 1.6D + (rand.nextDouble() - 0.5D) * 0.2D;
			double d2 = (double)pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.75D;
			worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			worldIn.playSound(d0, d1, d2, SoundEvents.ENTITY_MINECART_RIDING, SoundCategory.BLOCKS, 0.125F, 0.75F, true);
		}
	}
	
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) 
	{
		Direction facing = state.get(FACING);
		switch (state.get(PART)) 
		{
		case BASE:
			if (facing == Direction.EAST || facing == Direction.WEST) 
				return NEMBlockShapes.ROLLING_MILL_BASE[0];
			else 
				return NEMBlockShapes.ROLLING_MILL_BASE[2];
			
		case TOP:
			if (facing == Direction.EAST || facing == Direction.WEST) 
				return NEMBlockShapes.ROLLING_MILL_FRAME[0];
			else 
				return NEMBlockShapes.ROLLING_MILL_FRAME[2];
			
		default:
			return NEMBlockShapes.ROLLING_MILL_FRAME[1];
		}
	}
	
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		super.fillStateContainer(builder);
		builder.add(PART);
		builder.add(LIT);
	}
	
	

	@Override
	public ItemStack itemWhenDestroyed() 
	{
		return null;
	}
	
	
	
	@Override
	public boolean hasTileEntity(BlockState state) 
	{
		return state.get(PART) == RollingMillPart.BASE;
	}
	
	

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		RollingMillPart part = state.get(PART);
		if (part == RollingMillPart.BASE) 
			return new RollingMillTile();
		else 
			return null;
	}

	
	
	@Override
	public ArrayList<MechanicalContext> getIO(World world, BlockPos pos, BlockState state) 
	{
		switch (state.get(PART)) 
		{
		case TOP:
			return MechanicalConnectionList.monoAxle(pos, state.get(FACING).rotateY().getAxis());
		
		case BASE:
			return MechanicalConnectionList.monoAxle(pos.up(1), state.get(FACING).rotateY().getAxis());
			
		default:
			throw new IllegalStateException(NotEnoughMachines.MODID + ":rolling mill is in an unknown state!");
		}
	}

	
	
	@Override
	public boolean isMultiblockFormed(World world, BlockPos pos, BlockState state) 
	{
		return true;
	}

	
	
	@Override
	public boolean isMultiblockValid(World world, BlockPos pos, BlockState state) 
	{
		return true;
	}

	
	
	@Override
	public int getMultiblockWidth() 
	{
		return 1;
	}

	
	
	@Override
	public int getMultiblockHight() 
	{
		return 2;
	}

	
	
	@Override
	public int getMultiblockDepth() 
	{
		return 1;
	}

	
	
	@Override
	public void formMultiblock(World world, BlockPos pos, BlockState state) 
	{
		
	}

	
	
	@Override
	public void unformMultiblock(World world, BlockPos pos, BlockState state) 
	{
		
	}
}



