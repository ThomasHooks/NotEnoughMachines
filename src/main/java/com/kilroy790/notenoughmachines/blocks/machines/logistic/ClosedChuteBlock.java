package com.kilroy790.notenoughmachines.blocks.machines.logistic;

import com.kilroy790.notenoughmachines.blocks.machines.ItemConduitBlock;
import com.kilroy790.notenoughmachines.tiles.machines.logistic.ClosedChuteTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;




public class ClosedChuteBlock extends ItemConduitBlock {
	 
	 
	public ClosedChuteBlock(Properties properties, String name) {
		super(properties);
		this.setRegistryName(name);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH)
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
		return this.getDefaultState().with(FACING, context.getFace().getOpposite())
				.with(NORTH, Boolean.valueOf(this.canConnectTo(world, pos.north(), Direction.NORTH)))
				.with(EAST, Boolean.valueOf(this.canConnectTo(world, pos.east(), Direction.EAST)))
				.with(SOUTH, Boolean.valueOf(this.canConnectTo(world, pos.south(), Direction.SOUTH)))
				.with(WEST, Boolean.valueOf(this.canConnectTo(world, pos.west(), Direction.WEST)))
				.with(UP, Boolean.valueOf(this.canConnectTo(world, pos.up(), Direction.UP)))
				.with(DOWN, Boolean.valueOf(this.canConnectTo(world, pos.down(), Direction.DOWN)));
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
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ClosedChuteTile();
	}
}
