package com.kilroy790.notenoughmachines.blocks.machines;

import com.kilroy790.notenoughmachines.api.stateproperties.ChuteType;
import com.kilroy790.notenoughmachines.api.stateproperties.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.tiles.machines.ChuteTile;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
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
	
	public static final EnumProperty<ChuteType> TYPE = NEMBlockStateProperties.CHUTE_TYPE;
	
	
	public ChuteBlock(Properties properties, String name) {
		super(properties);
		this.setRegistryName(name);
		this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(TYPE, ChuteType.ANCHORED));
	}
	
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		
		Direction dir;
		if(placer.isSneaking()) dir = placer.getHorizontalFacing();
		else dir = placer.getHorizontalFacing().getOpposite();
		
		ChuteType type;
		BlockState bottomBlock = worldIn.getBlockState(pos.down());
		if(bottomBlock.isNormalCube(worldIn, pos.down())) type = ChuteType.ANCHORED;
		else if(bottomBlock.getBlock() instanceof HopperBlock) type = ChuteType.HOPPER;
		else type = ChuteType.HANGING;
		
		worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, dir).with(TYPE, type), 1|2);
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
	
	
	
	public static void updateType(BlockState state, World worldIn, BlockPos pos) {
		
		//Keep the facing direction the same as it was
		Direction facing = state.get(HORIZONTAL_FACING);
		
		//check the block below the chute to determine the chute type
		ChuteType type;
		BlockState bottomBlock = worldIn.getBlockState(pos.down());
		if(bottomBlock.isNormalCube(worldIn, pos.down())) type = ChuteType.ANCHORED;
		else if(bottomBlock.getBlock() instanceof HopperBlock || bottomBlock.getBlock() instanceof FilterBlock) type = ChuteType.HOPPER;
		else type = ChuteType.HANGING;
		
		worldIn.setBlockState(pos, state.with(HORIZONTAL_FACING, facing).with(TYPE, type));
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
		builder.add(HORIZONTAL_FACING, TYPE);
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
