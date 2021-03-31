package com.kilroy790.notenoughmachines.blocks;

import java.util.Random;

import com.kilroy790.notenoughmachines.items.NEMItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class FlaxPlantBlock extends CropsBlock implements IGrowable {

	public static final IntegerProperty FLAXPLANT_AGE = BlockStateProperties.AGE_0_7;

	
	
	public FlaxPlantBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(this.getAgeProperty(), Integer.valueOf(0)));
	}


	
	@Override
	@OnlyIn(Dist.CLIENT)
	protected IItemProvider getSeedsItem() {
		return NEMItems.FLAXSEED.get();
	}

	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(this.getSeedsItem());
	}

	

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock() instanceof FarmlandBlock;
	}

	

	@Override
	public IntegerProperty getAgeProperty() {
		return FLAXPLANT_AGE;
	}

	

	@Override
	public int getMaxAge() {
		return 7;
	}


	
	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return !this.isMaxAge(state);
	}

	

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	

	@Override
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		this.grow(worldIn, pos, state);
	}

	

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FLAXPLANT_AGE);
	}
}







