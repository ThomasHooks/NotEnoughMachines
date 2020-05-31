package com.kilroy790.notenoughmachines.blocks.machines.power;

import com.kilroy790.notenoughmachines.api.lists.ShapeList;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalBlock;
import com.kilroy790.notenoughmachines.tiles.machines.power.TubWheelTile;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;




public class TubWheelBlock extends MechanicalBlock {

	public TubWheelBlock(Properties properties, String name) {
		super(properties);
		this.setRegistryName(name);
	}

	
	
	@Override
	public ItemStack itemWhenDestroyed() {
		int rand = (int)(Math.random() * 4.0D);
		return new ItemStack(Items.OAK_SLAB, rand + 3);
	}
	
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return ShapeList.TUBWHEEL;
	}
	
	
	
	/**
	 * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
	 * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
	 * @deprecated call via {@link IBlockState#getRenderType()} whenever possible. Implementing/overriding is fine.
	 */
	@Override
	public BlockRenderType getRenderType(BlockState state) { 
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TubWheelTile();
	}
}







