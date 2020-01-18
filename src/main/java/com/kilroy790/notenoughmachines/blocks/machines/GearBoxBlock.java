package com.kilroy790.notenoughmachines.blocks.machines;

import java.util.Random;

import javax.annotation.Nullable;

import com.kilroy790.notenoughmachines.api.stateproperties.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.tiles.machines.GearboxTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;



public class GearboxBlock extends Block{
	
	
	public static final Direction[] GEARBOX_SIDE = {Direction.UP, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN};
	
	protected static final BooleanProperty POWERED = NEMBlockStateProperties.POWERED;

	
	public GearboxBlock(String name) {
		super(Properties.create(Material.WOOD)
						.sound(SoundType.WOOD)
						.hardnessAndResistance(1.8f, 2.0f)
						.harvestTool(ToolType.AXE)
						.harvestLevel(0));
		this.setRegistryName(name);
		this.setDefaultState(this.stateContainer.getBaseState().with(BlockStateProperties.FACING, Direction.NORTH).with(POWERED, Boolean.valueOf(false)));
	}
	
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
		//When the gearbox is first placed the front will be set to face the player
		
		if(entity != null) {
			Direction facing = getFacingFromEntity(pos, entity).getOpposite();
			world.setBlockState(pos, state.with(BlockStateProperties.FACING, facing).with(POWERED, world.getBlockState(pos).get(POWERED)), 2|1);
		}
	}
	
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockRayTraceResult hit) {
		//This method allows the player to set the "front" ie. input of the gearbox by right click on side as long as there main hand is empty
		
		if (player.abilities.allowEdit && player.getHeldItemMainhand().isEmpty()) {
			
			BlockState oldState = world.getBlockState(pos);
			world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, player)).with(POWERED, world.getBlockState(pos).get(POWERED)), 2|1);
			
			double d0 = (double)pos.getX() + 0.5D;
			double d1 = (double)pos.getY() + 0.5D;
			double d2 = (double)pos.getZ() + 0.5D;
			world.playSound(d0, d1, d2, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F, false);
			
			world.notifyBlockUpdate(pos, oldState, world.getBlockState(pos), 2|1);
			
			return true;
		}
		else return false;
	}
	
	
	@OnlyIn(Dist.CLIENT) 
	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		/**
		* Called periodically clientside on blocks near the player to show effects (like furnace fire particles). Note that
		* this method is unrelated to {@link randomTick} and {@link #needsRandomTick}, and will always be called regardless
		* of whether the block can receive random update ticks
		*/
		
		if (stateIn.get(POWERED)) {
  
			double d0 = (double)pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.75D;
			double d1 = (double)pos.getY() + 1.0D + (rand.nextDouble() - 0.5D) * 0.2D;
			double d2 = (double)pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.75D;
			worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	        
			if (rand.nextDouble() < 0.05D) {
				worldIn.playSound(d0, d1, d2, SoundEvents.ENTITY_MINECART_INSIDE, SoundCategory.BLOCKS, 0.025F, 0.75F, false);
			}
		}
	}
	
	
	private static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
		//returns the side that is in front of the player
		
		return Direction.getFacingFromVector((float) (entity.posX - clickedBlock.getX()), (float) (entity.posY - clickedBlock.getY()), (float) (entity.posZ - clickedBlock.getZ()));
	}
	
	
	public static BooleanProperty getPowered() {
		return POWERED;
	}
	
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		//adds a facing property to the gearbox which will indicate which side of the block is the front
		builder.add(BlockStateProperties.FACING, POWERED);
	}
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new GearboxTile();
	}
}




