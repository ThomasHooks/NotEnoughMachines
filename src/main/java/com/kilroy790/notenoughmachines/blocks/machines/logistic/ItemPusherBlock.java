package com.kilroy790.notenoughmachines.blocks.machines.logistic;

import java.util.Random;

import com.kilroy790.notenoughmachines.blocks.machines.ItemConduitBlock;
import com.kilroy790.notenoughmachines.tiles.machines.logistic.ItemPusherTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class ItemPusherBlock extends ItemConduitBlock {
	
	public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;
	
	
	
	public ItemPusherBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(ENABLED, Boolean.valueOf(true)));
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
			worldIn.setBlockState(pos, state.with(ENABLED, Boolean.valueOf(locked)), 1 | 2 | 4);
		}
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		
		if (!stateIn.get(ENABLED) && rand.nextFloat() < 0.25f) {
			double x = (double)pos.getX() + 0.5D + (double)(rand.nextFloat() - 0.5f) * 0.5D;
			double y = (double)pos.getY() + 0.9D;
			double z = (double)pos.getZ() + 0.5D + (double)(rand.nextFloat() - 0.5f) * 0.5D;
			worldIn.addParticle(RedstoneParticleData.REDSTONE_DUST, x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}
	
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(ENABLED);
	}
	
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ItemPusherTile();
	}
}







