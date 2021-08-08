package com.kilroy790.notenoughmachines.blocks.machines;

import java.util.List;
import java.util.Random;

import com.kilroy790.notenoughmachines.tiles.ItemPusherTile;
import com.kilroy790.notenoughmachines.utilities.NEMInputHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class ItemPusherBlock extends ItemConduitBlock 
{	
	public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;
	
	
	
	public ItemPusherBlock(Properties properties) 
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(ENABLED, Boolean.valueOf(true)));
	}
	
	
	
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) 
	{
		if (oldState.getBlock() != state.getBlock()) 
		{
			this.updateEnabledState(worldIn, pos, state);
		}
	}
	
	
	
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) 
	{
		this.updateEnabledState(worldIn, pos, state);
	}
	
	
	
	private void updateEnabledState(World worldIn, BlockPos pos, BlockState state) 
	{
		boolean locked = !worldIn.isBlockPowered(pos);
		if (locked != state.get(ENABLED)) 
		{
			worldIn.setBlockState(pos, state.with(ENABLED, Boolean.valueOf(locked)), 1 | 2 | 4);
		}
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		if (NEMInputHelper.isPressingShift()) 
		{
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("Pulls items from behind").mergeStyle(TextFormatting.GREEN));
			tooltip.add(new StringTextComponent("and pushes them forward").mergeStyle(TextFormatting.GREEN));
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("Locks when powered with " + "\u00A74" + "Redstone").mergeStyle(TextFormatting.GRAY));
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("Hold " + "\u00A72" + "Shift" + "\u00A77" + " to reverse while placing").mergeStyle(TextFormatting.GRAY));
		}
		else 
		{
			tooltip.add(new StringTextComponent(NEMInputHelper.MORE_INFO_PRESS_SHIFT));
		}
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) 
	{
		if (!stateIn.get(ENABLED) && rand.nextFloat() < 0.25f) 
		{
			double x = (double)pos.getX() + 0.5D + (double)(rand.nextFloat() - 0.5f) * 0.5D;
			double y = (double)pos.getY() + 0.9D;
			double z = (double)pos.getZ() + 0.5D + (double)(rand.nextFloat() - 0.5f) * 0.5D;
			worldIn.addParticle(RedstoneParticleData.REDSTONE_DUST, x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}
	
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) 
	{
		super.fillStateContainer(builder);
		builder.add(ENABLED);
	}
	
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return new ItemPusherTile();
	}
}







