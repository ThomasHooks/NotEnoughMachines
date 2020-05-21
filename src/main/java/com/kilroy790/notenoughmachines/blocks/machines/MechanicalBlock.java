package com.kilroy790.notenoughmachines.blocks.machines;

import javax.annotation.Nullable;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;




public abstract class MechanicalBlock extends Block {

	public MechanicalBlock(Properties properties) {
		super(properties);
	}
	
	
	
	/**
	 * Breaks this machine and either drops the machine or destroys it
	 * 
	 * @param world, the current world
	 * 
	 * @param pos, The position of the machine
	 * 
	 * @param destroy, if true the machine is destroyed
	 */
	public void breakMachine(World world, BlockPos pos, Boolean destroy) {
		if(world.isRemote) return;
		
		Block block = world.getBlockState(pos).getBlock();
		if(block instanceof MechanicalBlock) {
			
			if(destroy) {
				NEMItemHelper.dropItemStack(world, pos, itemWhenDestroyed());
				world.destroyBlock(pos, false);
			}
			
			else world.destroyBlock(pos, true);
		}
	}
	
	
	
	/**
	 * @return The item stack that is dropped when this machine is broken
	 */
	@Nullable
	abstract public ItemStack itemWhenDestroyed();
	
	
	
	/*
	 * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
	 * this block
	 */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		
		if(!worldIn.isRemote()) {
			MechanicalTile tile = worldIn.getTileEntity(pos) instanceof MechanicalTile ? (MechanicalTile)worldIn.getTileEntity(pos) : null;
			if(tile != null) NotEnoughMachines.AETHER.removeFromPowerNetwork(tile);
		}
		
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	
	
	@Override
	public boolean isSolid(BlockState state) {
		return false;
	}
	
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	
	
	@Override
	abstract public TileEntity createTileEntity(BlockState state, IBlockReader world);
}







