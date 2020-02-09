package com.kilroy790.notenoughmachines.blocks.machines;

import com.kilroy790.notenoughmachines.tiles.machines.FilterTile;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;




public class FilterBlock extends Block {

	
	public static final DirectionProperty FACING = BlockStateProperties.FACING_EXCEPT_UP;
	public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;
	
	private static final VoxelShape OUTTER_BOWL_SHAPE = Block.makeCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape INNER_BOWL_SHAPE = Block.makeCuboidShape(1.0D, 13.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	private static final VoxelShape BOWL_AREA_SHAPE = VoxelShapes.combineAndSimplify(OUTTER_BOWL_SHAPE, INNER_BOWL_SHAPE, IBooleanFunction.ONLY_FIRST);
	private static final VoxelShape MIDDLE_SHAPE = Block.makeCuboidShape(3.0D, 3.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	private static final VoxelShape FILTER_SHAPE = VoxelShapes.or(BOWL_AREA_SHAPE, MIDDLE_SHAPE);
	
	private static final VoxelShape BLOCK_ABOVE_SHAPE = Block.makeCuboidShape(0.0D, 16.0D, 0.0D, 16.0D, 32.0D, 16.0D);
	public static final VoxelShape COLLECTION_AREA_SHAPE = VoxelShapes.or(INNER_BOWL_SHAPE, BLOCK_ABOVE_SHAPE);

	
	public FilterBlock(Properties properties, String name) {
		super(properties);
		this.setRegistryName(name);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.DOWN).with(ENABLED, Boolean.valueOf(true)));
	}
	
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
	     Direction direction = context.getFace().getOpposite();
	     return this.getDefaultState().with(FACING, direction.getAxis() == Direction.Axis.Y ? Direction.DOWN : direction);
	}
	
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		
		if(world.isRemote) return true;
		
		else {
			TileEntity entity = world.getTileEntity(pos);
			if(entity instanceof INamedContainerProvider) NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) entity, entity.getPos()); 
			
			else throw new IllegalStateException("Filter container provider is missing!");
			
			return true;
		}
	}
	
	
	@Override
	public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
	    /*
		 * Block's chance to react to an entity falling on it.
		 */
		
		TileEntity tile = worldIn.getTileEntity(pos);
		if(tile instanceof FilterTile && entityIn instanceof ItemEntity) ((FilterTile)tile).captureItem((ItemEntity) entityIn);
		
		else super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
	}
	
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		/*
		 * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
		 * this block
		 */

		TileEntity tile = worldIn.getTileEntity(pos);
		if(tile != null) NEMItemHelper.dropItemHandlerInventory(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(null), worldIn, pos);
		
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (oldState.getBlock() != state.getBlock()) {
			this.updateEnabledState(worldIn, pos, state);
		}
	}


	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		this.updateEnabledState(worldIn, pos, state);
	}


	private void updateEnabledState(World worldIn, BlockPos pos, BlockState state) {
		boolean locked = !worldIn.isBlockPowered(pos);
		if (locked != state.get(ENABLED)) {
			worldIn.setBlockState(pos, state.with(ENABLED, Boolean.valueOf(locked)), 1|2|4);
		}
	}
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return FILTER_SHAPE;
	}
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING, ENABLED);
	}
	
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new FilterTile();
	}
}
