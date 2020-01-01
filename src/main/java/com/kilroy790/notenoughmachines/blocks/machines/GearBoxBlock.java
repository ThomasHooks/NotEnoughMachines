package com.kilroy790.notenoughmachines.blocks.machines;

import javax.annotation.Nullable;

import com.kilroy790.notenoughmachines.tiles.GearboxTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
import net.minecraftforge.common.ToolType;



public class GearboxBlock extends Block{
	
	
	public static final Direction[] GEARBOX_SIDE = {Direction.UP, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.DOWN};

	
	public GearboxBlock(String name) {
		super(Properties.create(Material.WOOD)
						.sound(SoundType.WOOD)
						.hardnessAndResistance(1.8f, 2.0f)
						.harvestTool(ToolType.AXE)
						.harvestLevel(0));
		this.setRegistryName(name);
	}
	
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
		//When the gearbox is first placed the front will be set to face the player
		
		if(entity != null) {
			world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2|1);
		}
	}
	
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockRayTraceResult hit) {
		//This method allows the player to set the "front" ie. input of the gearbox by right click on side as long as there main hand is empty
		
		if (player.abilities.allowEdit && player.getHeldItemMainhand().isEmpty()) {
			
			BlockState oldState = world.getBlockState(pos);
			world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, player)), 2|1);
			
			double d0 = (double)pos.getX() + 0.5D;
			double d1 = (double)pos.getY() + 0.5D;
			double d2 = (double)pos.getZ() + 0.5D;
			world.playSound(d0, d1, d2, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F, false);
			
			world.notifyBlockUpdate(pos, oldState, world.getBlockState(pos), 2|1);
			
			return true;
		}
		else return false;
	}
	
	
	private static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
		//returns the side that is in front of the player
		
		return Direction.getFacingFromVector((float) (entity.posX - clickedBlock.getX()), (float) (entity.posY - clickedBlock.getY()), (float) (entity.posZ - clickedBlock.getZ()));
	}
	
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		//adds a facing property to the gearbox which will indicate which side of the block is the front
		builder.add(BlockStateProperties.FACING);
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




