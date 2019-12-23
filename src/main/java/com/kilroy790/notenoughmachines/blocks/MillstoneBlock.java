package com.kilroy790.notenoughmachines.blocks;

import com.kilroy790.notenoughmachines.api.utilities.InventoryHandler;
import com.kilroy790.notenoughmachines.tiles.MillstoneTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;

public class MillstoneBlock extends Block{

	public MillstoneBlock(String name) {
		super(Properties.create(Material.ROCK)
				.sound(SoundType.STONE)
				.hardnessAndResistance(2.8f, 3.0f)
				.harvestTool(ToolType.PICKAXE)
				.harvestLevel(0));
		this.setRegistryName(name);
	}

	
	@Override
	public boolean hasTileEntity(BlockState state) {
		
		return true;
	}
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		
		return new MillstoneTile();
	}
	
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {

		if(world.isRemote) {
			return true;
		} else {
			TileEntity entity = world.getTileEntity(pos);
			if(entity instanceof INamedContainerProvider) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) entity, entity.getPos());
			} else {
                throw new IllegalStateException("Millstone container provider is missing!");
            }
			return true;
		}
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
		//Drop items in the millstone when it is broken
		if (state.getBlock() != newState.getBlock()) {
	         TileEntity tile = world.getTileEntity(pos);
	         InventoryHandler.dropItemHandlerInventory(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(null), world, pos);
	         super.onReplaced(state, world, pos, newState, isMoving);
		}
	}
}
