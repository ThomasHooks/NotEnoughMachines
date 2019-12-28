package com.kilroy790.notenoughmachines.blocks.machines;

import javax.annotation.Nullable;

import com.kilroy790.notenoughmachines.api.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.tiles.AxelTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class AxelBlock extends Block {

	public AxelBlock(String name) {
		super(Properties.create(Material.WOOD)
				.sound(SoundType.WOOD)
				.hardnessAndResistance(1.8f, 2.0f)
				.harvestTool(ToolType.AXE)
				.harvestLevel(0));
		this.setRegistryName(name);
		this.setDefaultState(this.stateContainer.getBaseState().with(getAxelDirection(), Integer.valueOf(1)));
	}
	
	
	public static final IntegerProperty AXLE_DIRECTION = NEMBlockStateProperties.AXLE_DIRECTION;
	
	
	private static final VoxelShape[] SHAPE_BY_DIR = new VoxelShape[]{Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D), Block.makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D), Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D)};
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		
		return true;
	}
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		
		return new AxelTile();
	}
	
	
	@Override
	public BlockRenderLayer getRenderLayer() {
		//This will prevent transparent block faces
		
		return BlockRenderLayer.CUTOUT_MIPPED;
		
	}
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos position, ISelectionContext context) {
		//Set the bounding box based on the direction that the block is facing
		
		return SHAPE_BY_DIR[state.get(this.getAxelDirection())];
	}
	
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
		//When the block is first placed the front will be set to face the player
		
		if(entity != null) {
			//world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);
			
			world.setBlockState(pos, state.with(AXLE_DIRECTION, getAxisAlignment(pos, entity)));
		}
	}
	
	
	private static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
		//returns the side that is in front of the player
		
		return Direction.getFacingFromVector((float) (entity.posX - clickedBlock.getX()), (float) (entity.posY - clickedBlock.getY()), (float) (entity.posZ - clickedBlock.getZ()));
	}
	
	
	private int getAxisAlignment(BlockPos pos, LivingEntity entity) {
		/*
		 * Brief			return the alignment of the given axle
		 */
		
		
		switch(getFacingFromEntity(pos, entity)) {
		case UP :
		case DOWN :
			return 0;
			
		case NORTH :
		case SOUTH :
			return 1;
			
		case EAST :
		case WEST :
			return 2;
			
		default :
			//North/South
			return 1;
		}
	}
	
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		//adds a axle direction property to the block which will indicate which axis the axle is aligned with
		
		builder.add(AXLE_DIRECTION);
	}


	public IntegerProperty getAxelDirection() {
		
		return AXLE_DIRECTION;
	}
}
