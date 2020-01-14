package com.kilroy790.notenoughmachines.blocks.machines;

import java.util.Random;

import javax.annotation.Nullable;

import com.kilroy790.notenoughmachines.api.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.tiles.machines.AxleTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;




public class AxleBlock extends Block {
	
	
	public static final IntegerProperty AXLE_DIRECTION = NEMBlockStateProperties.AXLE_DIRECTION;
	
	//a POWER value of 3 means that the axle is not connected to a source/provider where a value of 15 means it's next to a source/provider
	public static final IntegerProperty POWER = NEMBlockStateProperties.POWER_DISTANCE_3_15;
	public static final int MINPOWERDISTANCE = 3;
	public static final int MAXPOWERDISTANCE = 15;
	
	public static final int TICKRATE = 10;
	
	public static final Direction[][] axisAlignment = {{Direction.UP, Direction.DOWN}, {Direction.NORTH, Direction.SOUTH}, {Direction.WEST, Direction.EAST}};
	public static final int AXELAXISX = 2;
	public static final int AXELAXISY = 0;
	public static final int AXELAXISZ = 1;
	
	private static final VoxelShape[] SHAPE_BY_DIR = new VoxelShape[]{Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D), Block.makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D), Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D)};
	

	public AxleBlock(String name) {
		super(Properties.create(Material.WOOD)
				.sound(SoundType.WOOD)
				.hardnessAndResistance(1.8f, 2.0f)
				.harvestTool(ToolType.AXE)
				.harvestLevel(0));
		this.setRegistryName(name);
		this.setDefaultState(this.stateContainer.getBaseState().with(AXLE_DIRECTION, Integer.valueOf(1)).with(POWER, Integer.valueOf(3)));
	}
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new AxleTile();
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
		//When the axle is first placed it alignment and distance form the source/producer will be set
		
		if(entity != null) {
			
			int axleDir = getAxisAlignment(pos, entity);
			int axleDist = MINPOWERDISTANCE;
			
			updateAxleState(world, pos, state, axleDir, axleDist);
			world.notifyNeighborsOfStateChange(pos, this);
		}
	}
	
	
	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos,
			boolean isMoving) {

		int axleDir = state.getBlockState().get(AXLE_DIRECTION);
		//int axleDir = world.getBlockState(pos).get(AXLE_DIRECTION);
		int axleDist = MINPOWERDISTANCE;
		updateAxleState(world, pos, state, axleDir, axleDist);
	}
	
	
	@Override
	public void tick(BlockState state, World world, BlockPos pos, Random random) {
		
		int axleDir = state.getBlockState().get(AXLE_DIRECTION);
		//int axleDist = state.getBlockState().get(POWER);
		int axleDist = MINPOWERDISTANCE;
		updateAxleState(world, pos, state, axleDir, axleDist);
	}
	
	
	private void updateAxleState(World world, BlockPos pos, BlockState state, int axleDir, int axleDist) {
		/*
		 * 
		 */
		
		
		int nextAxlePowerLevel = MINPOWERDISTANCE;
		int newPowerLevel = axleDist;
		int powerLevel[] = new int[2];
		powerLevel[0] = MINPOWERDISTANCE;
		powerLevel[1] = MINPOWERDISTANCE;
		boolean nextToSource = false;
		
		for(int i = 0; i < 2; i++) {
			
			Direction direction = axisAlignment[axleDir][i];
			BlockPos posNext = pos.offset(direction);
			Block blockNext = world.getBlockState(posNext).getBlock();
			
			if(blockNext instanceof CreativePowerBoxBlock) {
				//TODO use AbstractPowerSourceBlock instead
				nextAxlePowerLevel = MAXPOWERDISTANCE + 1;
				powerLevel[i] = MAXPOWERDISTANCE + 1;
				nextToSource = true;
			}
			
			else if(blockNext instanceof GearboxBlock) {
				Direction gearboxInput = world.getBlockState(posNext).get(BlockStateProperties.FACING);
				if(gearboxInput != axisAlignment[axleDir][i].getOpposite()) {
					//axle is attached to a gearbox's output
					nextAxlePowerLevel = MAXPOWERDISTANCE + 1;
					powerLevel[i] = MAXPOWERDISTANCE + 1;
				}
			}
			
			else if(newPowerLevel == MAXPOWERDISTANCE) {
				newPowerLevel = MINPOWERDISTANCE;
				nextAxlePowerLevel = MINPOWERDISTANCE;
			}
			
			else if(blockNext instanceof AxleBlock) {
				powerLevel[i] = world.getBlockState(posNext).get(POWER);
				if(powerLevel[i] > nextAxlePowerLevel) nextAxlePowerLevel = powerLevel[i];
			}
		}
		
		if(powerLevel[0] < newPowerLevel && powerLevel[1] < newPowerLevel && !nextToSource) {
			//when the axle is the local maxima 
			newPowerLevel = MINPOWERDISTANCE;
		}
		
		if(nextAxlePowerLevel > newPowerLevel) newPowerLevel = nextAxlePowerLevel - 1;
		
		if(newPowerLevel < MINPOWERDISTANCE) newPowerLevel = MINPOWERDISTANCE;
		
		world.setBlockState(pos, state.with(AXLE_DIRECTION, Integer.valueOf(axleDir)).with(POWER, Integer.valueOf(newPowerLevel)));
	}
	
	
	private static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
		//returns the side that is in front of the player
		return Direction.getFacingFromVector((float) (entity.posX - clickedBlock.getX()), (float) (entity.posY - clickedBlock.getY()), (float) (entity.posZ - clickedBlock.getZ()));
	}
	
	
	private int getAxisAlignment(BlockPos pos, LivingEntity entity) {
		/*
		 * @return		the alignment of the given axle
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
	public int tickRate(IWorldReader worldIn) {
		return TICKRATE;
	}
	
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		//adds a axle direction property to the block which will indicate which axis the axle is aligned with
		builder.add(AXLE_DIRECTION, POWER);
	}


	public IntegerProperty getAxelDirection() {
		return AXLE_DIRECTION;
	}
	
	
	public IntegerProperty getPowerDistance() {
		return POWER;
	}
}




