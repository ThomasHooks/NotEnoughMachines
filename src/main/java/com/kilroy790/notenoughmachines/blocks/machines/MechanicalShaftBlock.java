package com.kilroy790.notenoughmachines.blocks.machines;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;




public abstract class MechanicalShaftBlock extends MechanicalBlock {

	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
	
	
	
	public MechanicalShaftBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(AXIS, Direction.Axis.X));
	}



	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(MechanicalShaftBlock.AXIS, context.getFace().getAxis());
	}

	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(AXIS);
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
}







