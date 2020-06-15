package com.kilroy790.notenoughmachines.blocks.machines.logistic;

import com.kilroy790.notenoughmachines.blocks.machines.ItemConduitBlock;
import com.kilroy790.notenoughmachines.tiles.machines.logistic.FilterTile;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;




public class FilterBlock extends ItemConduitBlock {
	 
	public FilterBlock(Properties properties) {
		super(properties);
	}
	
	
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		
		if(world.isRemote) {
			return ActionResultType.SUCCESS;
		}
		else {
			TileEntity entity = world.getTileEntity(pos);
			if(entity instanceof INamedContainerProvider) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) entity, entity.getPos()); 
			}
			else {
				throw new IllegalStateException("Container provider is missing!");
			}
			return ActionResultType.SUCCESS;
		}
	}
	
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new FilterTile();
	}
}







