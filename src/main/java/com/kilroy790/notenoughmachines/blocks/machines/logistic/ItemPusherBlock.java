package com.kilroy790.notenoughmachines.blocks.machines.logistic;

import java.util.Random;

import com.kilroy790.notenoughmachines.blocks.machines.ItemConduitBlock;
import com.kilroy790.notenoughmachines.tiles.machines.logistic.ItemPusherTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class ItemPusherBlock extends ItemConduitBlock {
	
	
	public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;
	
	
	public ItemPusherBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH)
				.with(NORTH, Boolean.valueOf(false))
				.with(EAST, Boolean.valueOf(false))
				.with(SOUTH, Boolean.valueOf(false))
				.with(WEST, Boolean.valueOf(false))
				.with(UP, Boolean.valueOf(false))
				.with(DOWN, Boolean.valueOf(false))
				.with(ENABLED, Boolean.valueOf(true)));
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		return this.getDefaultState().with(FACING, context.getFace().getOpposite())
				.with(NORTH, Boolean.valueOf(this.canConnectTo(world, pos.north(), Direction.NORTH)))
				.with(EAST, Boolean.valueOf(this.canConnectTo(world, pos.east(), Direction.EAST)))
				.with(SOUTH, Boolean.valueOf(this.canConnectTo(world, pos.south(), Direction.SOUTH)))
				.with(WEST, Boolean.valueOf(this.canConnectTo(world, pos.west(), Direction.WEST)))
				.with(UP, Boolean.valueOf(this.canConnectTo(world, pos.up(), Direction.UP)))
				.with(DOWN, Boolean.valueOf(this.canConnectTo(world, pos.down(), Direction.DOWN)))
				.with(ENABLED, Boolean.valueOf(true));
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
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		/*
		 * Called periodically on client side on blocks near the player to show effects (like furnace fire particles). Note that
		 * this method is unrelated to {@link randomTick} and {@link #needsRandomTick}, and will always be called regardless
		 * of whether the block can receive random update ticks
		 */

		if (!stateIn.get(ENABLED) && rand.nextFloat() < 0.25f) {
			double x = (double)pos.getX() + 0.5D + (double)(rand.nextFloat() - 0.5f) * 0.5D;
			double y = (double)pos.getY() + 0.9D;
			double z = (double)pos.getZ() + 0.5D + (double)(rand.nextFloat() - 0.5f) * 0.5D;
			worldIn.addParticle(RedstoneParticleData.REDSTONE_DUST, x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING, NORTH, EAST, SOUTH, WEST, UP, DOWN, ENABLED);
	}
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ItemPusherTile();
	}
}
