package com.kilroy790.notenoughmachines.blocks.building;

import com.kilroy790.notenoughmachines.api.stateproperties.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.tiles.machines.processing.TripHammerTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;




public class HammerHeadBlock extends Block {

	
	private static final VoxelShape FACE_SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);
	private static final VoxelShape NECK_SHAPE = Block.makeCuboidShape(4.0D, 3.0D, 4.0D, 12.0D, 6.0D, 12.0D);
	private static final VoxelShape HEAD_SHAPE = Block.makeCuboidShape(3.0D, 6.0D, 3.0D, 13.0D, 13.0D, 13.0D);
	private static final VoxelShape EYE_SHAPE = Block.makeCuboidShape(5.0D, 13.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	private static final VoxelShape HAMMER_SHAPE = VoxelShapes.or(FACE_SHAPE, NECK_SHAPE, HEAD_SHAPE, EYE_SHAPE);
	
	public static final BooleanProperty FORMED = NEMBlockStateProperties.FORMED;
	
	
	public HammerHeadBlock(Properties properties, String name) {
		super(properties);
		this.setRegistryName(name);
		this.setDefaultState(this.stateContainer.getBaseState().with(FORMED, false));
	}
	
	/*
	protected void onStartFalling(FallingBlockEntity fallingEntity) {
		fallingEntity.setHurtEntities(true);
	}
	
	
	public void onEndFalling(World worldIn, BlockPos pos, BlockState fallingState, BlockState hitState) {
		worldIn.playEvent(1031, pos, 0);
	}*/
	
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {

		if(world.isRemote) return true;
		
		else {
			TileEntity entity = world.getTileEntity(pos);
			if(entity instanceof INamedContainerProvider) NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) entity, entity.getPos()); 
			
			else throw new IllegalStateException("Trip Hammer container provider is missing!");
			
			return true;
		}
	}
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return HAMMER_SHAPE;
	}
	
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FORMED);
	}
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TripHammerTile();
	}
}
