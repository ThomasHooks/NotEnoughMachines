package com.kilroy790.notenoughmachines.blocks.machines;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;



public class GearBoxBlock extends Block{

	public GearBoxBlock(String name) {
		super(Properties.create(Material.WOOD)
						.sound(SoundType.WOOD)
						.hardnessAndResistance(1.8f, 2.0f) //.8
						.harvestTool(ToolType.AXE)
						.harvestLevel(0));
		this.setRegistryName(name);
	}
	
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
		//When the gearbox is first placed the front will be set to face the player
		
		if(entity != null) {
			world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);
		}
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
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockRayTraceResult hit) {
		//This method allows the player to set the "front" ie. input of the gearbox by right click on side as long as there main hand is empty
		
		if (player.abilities.allowEdit && player.getHeldItemMainhand().isEmpty()) {
			world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, player)), 2);
			return true;
		}
		else return false;
	}
}




