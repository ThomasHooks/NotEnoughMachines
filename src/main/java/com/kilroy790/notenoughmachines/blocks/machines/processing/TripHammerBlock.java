package com.kilroy790.notenoughmachines.blocks.machines.processing;

import java.util.ArrayList;
import java.util.Objects;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalHorizontalBlock;
import com.kilroy790.notenoughmachines.multiblocks.IMultiblockPart;
import com.kilroy790.notenoughmachines.power.MechanicalConnectionList;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.state.properties.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.state.properties.TripHammerPart;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;
import com.kilroy790.notenoughmachines.tiles.machines.processing.TripHammerTile;
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
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;




public class TripHammerBlock extends MechanicalHorizontalBlock implements IMultiblockPart {
	
	public static final EnumProperty<TripHammerPart> PART = NEMBlockStateProperties.TRIPHAMMERPART;
	
	public TripHammerBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(PART, TripHammerPart.BASE));
	}
	
	
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (world.isRemote) {
			return ActionResultType.SUCCESS;
		}
		else {
			TileEntity entity = getMasterTile(world, pos, state);
			if (entity instanceof INamedContainerProvider) NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) entity, entity.getPos()); 
			else throw new IllegalStateException("Trip Hammer container provider is missing!");
			return ActionResultType.SUCCESS;
		}
	}
	
	
	
	public  TileEntity getMasterTile(World world, BlockPos pos, BlockState state) {
		TripHammerPart part = state.get(PART);
		switch (part) {
		
		case BASE:
			return world.getTileEntity(pos);
			
		case LOWERFRAME:
			return world.getTileEntity(pos.down(1));
			
		case CAM:
			return world.getTileEntity(pos.down(2));
			
		case UPPERFRAME:
			return world.getTileEntity(pos.down(3));
			
		default:
			throw new IllegalStateException("Unable to get TripHammerBlock's tile entity!");
		}
	}
	
	
	
	@Override
	public MechanicalTile getTile(IWorld world, BlockPos pos, BlockState state) {
		MechanicalTile tile = getMasterTile((World)world, pos, state) instanceof MechanicalTile ? (MechanicalTile)getMasterTile((World)world, pos, state) : null;
		return Objects.requireNonNull(tile, "MechanicalBlock: '" + state.getBlock().getRegistryName() + "' tile entity must be an instance of MechanicalTile!");
	}
	
	
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		super.onBlockPlacedBy(world, pos, state, placer, stack);
		BlockState shiftedState = world.getBlockState(pos);
		world.setBlockState(pos.up(1), state.with(PART, TripHammerPart.LOWERFRAME).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
		world.setBlockState(pos.up(2), state.with(PART, TripHammerPart.CAM).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
		world.setBlockState(pos.up(3), state.with(PART, TripHammerPart.UPPERFRAME).with(SHIFTED, shiftedState.get(SHIFTED)), 1 | 2);
	}
	
	
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockPos pos = context.getPos();
		if (pos.getY() < 256 - 4 && context.getWorld().getBlockState(pos.up(1)).isReplaceable(context) && context.getWorld().getBlockState(pos.up(2)).isReplaceable(context) && context.getWorld().getBlockState(pos.up(3)).isReplaceable(context)) {
			return super.getStateForPlacement(context).with(FACING, context.getPlacementHorizontalFacing()).with(PART, TripHammerPart.BASE);
		}
		else {
			return null;
		}
	}
	
	
	
	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		TileEntity tile = getMasterTile(world, pos, state);
		if (tile != null) {
			NEMItemHelper.dropItemHandlerInventory(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(null), world, pos);
		}
		
		TripHammerPart part = state.get(PART);
		switch (part) {
		
		case BASE:
			world.setBlockState(pos.up(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.up(2), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.up(3), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			break;
			
		case CAM:
			world.setBlockState(pos.down(2), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.down(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.up(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			break;
			
		case LOWERFRAME:
			world.setBlockState(pos.down(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.up(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.up(2), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			break;
			
		case UPPERFRAME:
			world.setBlockState(pos.down(3), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.down(2), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.down(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			break;
			
		default:
			throw new IllegalStateException(NotEnoughMachines.MODID + ":TripHammerBlock is in an unknow state!");
		}
		
		if (!world.isRemote && !player.isCreative() && player.canHarvestBlock(state)) {
			spawnDrops(state.with(PART, TripHammerPart.BASE), world, pos, (TileEntity)null, player, player.getHeldItemMainhand());
		}
		
		super.onBlockHarvested(world, pos, state, player);
	}
	
	
	
	@Override
	public void harvestBlock(World world, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) {
		super.harvestBlock(world, player, pos, Blocks.AIR.getDefaultState(), te, stack);
	}
	
	
	
	@Override
	public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion explosion) {
		TileEntity tile = getMasterTile(world, pos, state);
		if (tile != null) {
			NEMItemHelper.dropItemHandlerInventory(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(null), world, pos);
		}
		TripHammerPart part = state.get(PART);
		switch (part) {
		
		case BASE:
			world.setBlockState(pos.up(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.up(2), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.up(3), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			break;
			
		case CAM:
			world.setBlockState(pos.down(2), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.down(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.up(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			break;
			
		case LOWERFRAME:
			world.setBlockState(pos.down(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.up(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.up(2), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			break;
			
		case UPPERFRAME:
			world.setBlockState(pos.down(3), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.down(2), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			world.setBlockState(pos.down(1), Blocks.AIR.getDefaultState(), 1 | 2 | 32);
			break;
			
		default:
			throw new IllegalStateException(NotEnoughMachines.MODID + ":TripHammerBlock is in an unknow state!");
		}
		spawnDrops(state.with(PART, TripHammerPart.BASE), world, pos);
		super.onBlockExploded(state, world, pos, explosion);
	}
	
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		Direction facing = state.get(FACING);
		TripHammerPart part = state.get(PART);
		switch (part) {
		
		case BASE:
			if (facing == Direction.EAST || facing == Direction.WEST) {
				return NEMBlockShapes.TRIPHAMMER_BASE[0];
			}
			else {
				return NEMBlockShapes.TRIPHAMMER_BASE[2];
			}
			
		case CAM:
		case LOWERFRAME:
		case UPPERFRAME:
			if (facing == Direction.EAST || facing == Direction.WEST) {
				return NEMBlockShapes.TRIPHAMMER_FRAME[0];
			}
			else {
				return NEMBlockShapes.TRIPHAMMER_FRAME[2];
			}
			
		default:
			return NEMBlockShapes.TRIPHAMMER_FRAME[1];
		}
	}
	
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(PART);
	}
	
	
	
	@Override
	public ItemStack itemWhenDestroyed() {
		return null;
	}
	
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		TripHammerPart part = state.get(PART);
		if (part == TripHammerPart.BASE) return true;
		else return false;
	}
	
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		TripHammerPart part = state.get(PART);
		if (part == TripHammerPart.BASE) {
			return new TripHammerTile();
		}
		else {
			return null;
		}
	}



	@Override
	public ArrayList<MechanicalContext> getIO(World world, BlockPos pos, BlockState state) {
		switch (state.get(PART)) {
			
		case CAM:
			return MechanicalConnectionList.monoAxle(pos, state.get(FACING).rotateY().getAxis());
		
		case BASE:
			return MechanicalConnectionList.monoAxle(pos.up(2), state.get(FACING).rotateY().getAxis());
			
		case LOWERFRAME:
			return MechanicalConnectionList.monoAxle(pos.up(), state.get(FACING).rotateY().getAxis());
			
		case UPPERFRAME:
			return MechanicalConnectionList.monoAxle(pos.down(), state.get(FACING).rotateY().getAxis());
			
		default:
			throw new IllegalStateException(NotEnoughMachines.MODID + ":TripHammerBlock is in an unknow state!");
		}
	}
	
	
	
	@Override
	public boolean isMultiblockFormed(World world, BlockPos pos, BlockState state) {
		return true;
	}
	
	
	
	@Override
	public boolean isMultiblockValid(World world, BlockPos pos, BlockState state) {
		return true;
	}
	
	
	
	@Override
	public void formMultiblock(World world, BlockPos pos, BlockState state) {
	}
	
	
	
	@Override
	public void unformMultiblock(World world, BlockPos pos, BlockState state) {
	}
	
	
	
	@Override
	public int getMultiblockWidth() {
		return 1;
	}
	
	
	
	@Override
	public int getMultiblockHight() {
		return 4;
	}
	
	
	
	@Override
	public int getMultiblockDepth() {
		return 1;
	}
}







