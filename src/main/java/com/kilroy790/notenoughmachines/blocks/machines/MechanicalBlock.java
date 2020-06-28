package com.kilroy790.notenoughmachines.blocks.machines;

import javax.annotation.Nullable;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.state.properties.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;




public abstract class MechanicalBlock extends Block {

	public static final BooleanProperty SHIFTED = NEMBlockStateProperties.SHIFTED;
	
	
	
	public MechanicalBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(SHIFTED, false));
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
	
	
	
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return null;
	}

	
	
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
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {

		if(!worldIn.isRemote()) {
			MechanicalTile tile = worldIn.getTileEntity(pos) instanceof MechanicalTile ? (MechanicalTile)worldIn.getTileEntity(pos) : null;
			if(tile != null) {
				for(MechanicalContext io : tile.getIO()) {
					MechanicalTile neighborTile = tile instanceof MechanicalTile ? (MechanicalTile)worldIn.getTileEntity(io.getPos()) : null;
					if(neighborTile != null) {
						if(neighborTile.isAlignedWith(io.getFacing(), io.isAxle())) {
							if(!io.isAxle()) {
								BlockState neighborState = neighborTile.getBlockState().cycle(SHIFTED);
								worldIn.setBlockState(pos, state.with(SHIFTED, neighborState.get(SHIFTED)));
							}
							else {
								worldIn.setBlockState(pos, state.with(SHIFTED, neighborTile.getBlockState().get(SHIFTED)));
							}
							break;
						}
					}
				}
			}
		}
	}

	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(SHIFTED);
	}
	
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	
	
	@Override
	abstract public TileEntity createTileEntity(BlockState state, IBlockReader world);
}







