package com.kilroy790.notenoughmachines.blocks.machines;

import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SixWayBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;




public abstract class ItemConduitBlock extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	public static final BooleanProperty EAST = BlockStateProperties.EAST;
	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	public static final BooleanProperty WEST = BlockStateProperties.WEST;
	public static final BooleanProperty UP = BlockStateProperties.UP;
	public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
	
	protected static final VoxelShape PUSHER_SHAPE = Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 12.0D, 12.0D);
	protected static final VoxelShape CHUTE_SHAPE_NORTH = VoxelShapes.or(PUSHER_SHAPE, Block.makeCuboidShape(5.0D, 5.0D, 3.0D, 11.0D, 11.0D, 4.0D));
	protected static final VoxelShape CHUTE_SHAPE_EAST = VoxelShapes.or(PUSHER_SHAPE, Block.makeCuboidShape(12.0D, 5.0D, 5.0D, 13.0D, 11.0D, 11.0D));
	protected static final VoxelShape CHUTE_SHAPE_WEST = VoxelShapes.or(PUSHER_SHAPE, Block.makeCuboidShape(3.0D, 5.0D, 5.0D, 4.0D, 11.0D, 11.0D));
	protected static final VoxelShape CHUTE_SHAPE_SOUTH = VoxelShapes.or(PUSHER_SHAPE, Block.makeCuboidShape(5.0D, 5.0D, 12.0D, 11.0D, 11.0D, 13.0D));
	protected static final VoxelShape CHUTE_SHAPE_UP = VoxelShapes.or(PUSHER_SHAPE, Block.makeCuboidShape(5.0D, 12.0D, 5.0D, 11.0D, 13.0D, 11.0D));
	protected static final VoxelShape CHUTE_SHAPE_DOWN = VoxelShapes.or(PUSHER_SHAPE, Block.makeCuboidShape(5.0D, 3.0D, 5.0D, 11.0D, 4.0D, 11.0D));
	
	protected static final VoxelShape TUBE_NORTH_SHAPE = Block.makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 6.0D);
	protected static final VoxelShape TUBE_EAST_SHAPE = Block.makeCuboidShape(10.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
	protected static final VoxelShape TUBE_SOUTH_SHAPE = Block.makeCuboidShape(6.0D, 6.0D, 10.0D, 10.0D, 10.0D, 16.0D);
	protected static final VoxelShape TUBE_WEST_SHAPE = Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 6.0D, 10.0D, 10.0D);
	protected static final VoxelShape TUBE_UP_SHAPE = Block.makeCuboidShape(6.0D, 10.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	protected static final VoxelShape TUBE_DOWN_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);
	
	public ItemConduitBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState()
				.with(FACING, Direction.NORTH)
				.with(NORTH, Boolean.valueOf(false))
				.with(EAST, Boolean.valueOf(false))
				.with(SOUTH, Boolean.valueOf(false))
				.with(WEST, Boolean.valueOf(false))
				.with(UP, Boolean.valueOf(false))
				.with(DOWN, Boolean.valueOf(false)));
	}

	
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		Direction facing = context.getPlayer().isSneaking() ? context.getFace().getOpposite() : context.getFace();
		return this.getDefaultState()
				.with(FACING, facing)
				.with(NORTH, Boolean.valueOf(this.canConnectTo(world, pos.north(), Direction.NORTH)))
				.with(EAST, Boolean.valueOf(this.canConnectTo(world, pos.east(), Direction.EAST)))
				.with(SOUTH, Boolean.valueOf(this.canConnectTo(world, pos.south(), Direction.SOUTH)))
				.with(WEST, Boolean.valueOf(this.canConnectTo(world, pos.west(), Direction.WEST)))
				.with(UP, Boolean.valueOf(this.canConnectTo(world, pos.up(), Direction.UP)))
				.with(DOWN, Boolean.valueOf(this.canConnectTo(world, pos.down(), Direction.DOWN)));
	}
	
	
	
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return stateIn.with(SixWayBlock.FACING_TO_PROPERTY_MAP.get(facing), Boolean.valueOf(this.canConnectTo((World) worldIn, facingPos, facing)));
	}
	
	
	
	protected boolean canConnectTo(World worldIn, BlockPos pos, Direction side) {
		
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile != null) {
			LazyOptional<IItemHandler> container = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side.getOpposite());
			if (container.isPresent()) return true;
		}
		return false;
	}
	
	
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile != null) NEMItemHelper.dropItemHandlerInventory(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(null), worldIn, pos);
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      switch ((Direction)state.get(FACING)) {
	      case NORTH:
	         return VoxelShapes.or(CHUTE_SHAPE_NORTH, getTubeShapes(state));
	      case SOUTH:
	         return VoxelShapes.or(CHUTE_SHAPE_SOUTH, getTubeShapes(state));
	      case WEST:
	         return VoxelShapes.or(CHUTE_SHAPE_WEST, getTubeShapes(state));
	      case EAST:
	         return VoxelShapes.or(CHUTE_SHAPE_EAST, getTubeShapes(state));
	      case UP:
	    	  return VoxelShapes.or(CHUTE_SHAPE_UP, getTubeShapes(state));
	      case DOWN:
		         return VoxelShapes.or(CHUTE_SHAPE_DOWN, getTubeShapes(state));
	      default:
	         return VoxelShapes.or(CHUTE_SHAPE_NORTH, getTubeShapes(state));
	      }
	}
	
	
	
	/**
	 * Gets the item conduit's bounding box
	 * 
	 * @param state Is the block state of the Item Conduit
	 * @return The bounding box for the given Item Conduit Block
	 */
	protected VoxelShape getTubeShapes(BlockState state) {
		VoxelShape shape = VoxelShapes.empty();
		if (state.get(NORTH)) shape = VoxelShapes.or(shape, TUBE_NORTH_SHAPE);
		if (state.get(EAST)) shape = VoxelShapes.or(shape, TUBE_EAST_SHAPE);
		if (state.get(SOUTH)) shape = VoxelShapes.or(shape, TUBE_SOUTH_SHAPE);
		if (state.get(WEST)) shape = VoxelShapes.or(shape, TUBE_WEST_SHAPE);
		if (state.get(UP)) shape = VoxelShapes.or(shape, TUBE_UP_SHAPE);
		if (state.get(DOWN)) shape = VoxelShapes.or(shape, TUBE_DOWN_SHAPE);
		return shape;
	}
	
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING, NORTH, EAST, SOUTH, WEST, UP, DOWN);
	}
	
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	
	
	@Override
	abstract public TileEntity createTileEntity(BlockState state, IBlockReader world);
}







