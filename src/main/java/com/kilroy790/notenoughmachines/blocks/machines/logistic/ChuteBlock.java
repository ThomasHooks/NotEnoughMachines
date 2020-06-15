package com.kilroy790.notenoughmachines.blocks.machines.logistic;

import com.kilroy790.notenoughmachines.blocks.machines.ItemConduitBlock;
import com.kilroy790.notenoughmachines.tiles.machines.logistic.ChuteTile;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;




public class ChuteBlock extends ItemConduitBlock {

	public ChuteBlock(Properties properties) {
		super(properties);
	}

	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ChuteTile();
	}
}







