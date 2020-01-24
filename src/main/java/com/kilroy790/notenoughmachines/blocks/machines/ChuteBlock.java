package com.kilroy790.notenoughmachines.blocks.machines;

import com.kilroy790.notenoughmachines.tiles.machines.ChuteTile;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;




public class ChuteBlock extends HorizontalBlock {

	
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);
	
	
	public ChuteBlock(Properties properties, String name) {
		super(properties);
		this.setRegistryName(name);
		this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH));
	}
	
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		
		Direction dir = placer.getHorizontalFacing();
		if(placer.isSneaking()) worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir), 1|2);
		else worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir.getOpposite()), 1|2);
	}
	
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		/*
		 * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
		 * this block
		 */

		TileEntity tile = worldIn.getTileEntity(pos);
		if(tile instanceof ChuteTile) {
			NEMItemHelper.dropItemHandlerInventory(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(null), worldIn, pos);
		}
		
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		//This will prevent transparent block faces
		return BlockRenderLayer.CUTOUT_MIPPED;
		
	}
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos position, ISelectionContext context) {
		return SHAPE;
	}

	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING);
	}
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ChuteTile();
	}
}
