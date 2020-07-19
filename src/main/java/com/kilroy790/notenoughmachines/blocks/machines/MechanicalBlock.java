package com.kilroy790.notenoughmachines.blocks.machines;

import java.util.ArrayList;
import java.util.Objects;

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
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;




public abstract class MechanicalBlock extends Block {

	public static final BooleanProperty SHIFTED = NEMBlockStateProperties.SHIFTED;
	
	public MechanicalBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(SHIFTED, false));
	}	
	
	
	
	/**
	 * Gets an array that contains this machine's Mechanical Inputs/Outputs.
	 * 
	 * @param world This machine's World
	 * @param pos This machine's Block Position
	 * @param state This machine's current Block State
	 */
	abstract public ArrayList<MechanicalContext> getIO(World world, BlockPos pos, BlockState state);
	
	
	
	/**
	 * Gets an array of neighboring machines that are attached to this machine.
	 * It is possible that the returned array is empty if there are not neighboring machines.
	 * 
	 * @param world This machine's World
	 * @param pos This machine's Block Position
	 * @param state This machine's current Block State
	 * 
	 * @return An array of neighboring machines to this machine
	 */
	public ArrayList<MechanicalTile> getNeighbors(World world, BlockPos pos, BlockState state) {

		ArrayList<MechanicalTile> neighbors = new ArrayList<MechanicalTile>();
		for (MechanicalContext io : this.getIO(world, pos, state)) {

			MechanicalBlock neighborBlock = world.getBlockState(io.getPos()).getBlock() instanceof MechanicalBlock ? (MechanicalBlock)world.getBlockState(io.getPos()).getBlock() : null;
			if (neighborBlock != null) {
				BlockPos neighborPos = io.getPos();
				BlockState neighborState = world.getBlockState(neighborPos);
				MechanicalTile neighborTile = neighborBlock.getTile(world, neighborPos, neighborState);
				if (neighborBlock.isAlignedWith(world, neighborPos, neighborState, io) && !neighbors.contains(neighborTile)) {
					neighbors.add(neighborTile);
				}
			}
		}
		return neighbors;
	}
	
	
	
	/**
	 * Checks if the given Mechanical Input/Output is aligned with this machine.
	 * 
	 * @param world This machine's World
	 * @param pos This machine's Block Position
	 * @param state This machine's current Block State
	 * @param context The Mechanical Input/Output that is being checked for alignment
	 * 
	 * @return True if the given direction is aligned with this machine
	 */
	public boolean isAlignedWith(World world, BlockPos pos, BlockState state, MechanicalContext context) {
		
		for (MechanicalContext io : getIO(world, pos, state)) {
			if (io.getFacing() == context.getFacing().getOpposite() && io.isAxle() == context.isAxle()) return true;
		}
		return false;
	}
	
	
	
	/**
	 * Gets this machine's Tile Entity
	 * 
	 * @param world This machine's World
	 * @param pos This machine's Block Position
	 * @param state This machine's current Block State
	 * 
	 * @return This machine's Mechanical Tile Entity
	 */
	public MechanicalTile getTile(IWorld world, BlockPos pos, BlockState state) {
		MechanicalTile tile = world.getTileEntity(pos) instanceof MechanicalTile ? (MechanicalTile)world.getTileEntity(pos) : null;
		return Objects.requireNonNull(tile, "MechanicalBlock: '" + state.getBlock().getRegistryName() + "' tile entity must be an instance of MechanicalTile!");
	}
	
	
	
	/**
	 * Breaks this machine and either drops the machine or destroys it.
	 * 
	 * @param world, the current world
	 * @param pos, The position of the machine
	 * @param destroy, if true the machine is destroyed
	 */
	public void breakMachine(World world, BlockPos pos, Boolean destroy) {
		
		if (world.isRemote) return;
		Block block = world.getBlockState(pos).getBlock();
		if (block instanceof MechanicalBlock) {
			
			if (destroy) {
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
	
	
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		
		if (!worldIn.isRemote()) {
			MechanicalTile tile = worldIn.getTileEntity(pos) instanceof MechanicalTile ? (MechanicalTile)worldIn.getTileEntity(pos) : null;
			if (tile != null) NotEnoughMachines.AETHER.removeFromPowerNetwork(tile);
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {

		if (!worldIn.isRemote()) {
			MechanicalTile tile = worldIn.getTileEntity(pos) instanceof MechanicalTile ? (MechanicalTile)worldIn.getTileEntity(pos) : null;
			if (tile != null) {
				for (MechanicalContext io : this.getIO(worldIn, pos, state)) {
					MechanicalTile neighborTile = tile instanceof MechanicalTile ? (MechanicalTile)worldIn.getTileEntity(io.getPos()) : null;
					if (neighborTile != null) {
						MechanicalBlock neighborBlock = (MechanicalBlock) neighborTile.getBlockState().getBlock();
						if (neighborBlock.isAlignedWith(worldIn, neighborTile.getPos(), neighborTile.getBlockState(), io)) {
							if (!io.isAxle()) {
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







