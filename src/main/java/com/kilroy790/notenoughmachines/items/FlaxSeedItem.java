package com.kilroy790.notenoughmachines.items;

import com.kilroy790.notenoughmachines.blocks.NEMBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;




public class FlaxSeedItem extends Item implements IPlantable 
{
	public FlaxSeedItem(Properties properties) 
	{
		super(properties);
	}



	@Override
	public ActionResultType onItemUse(ItemUseContext context) 
	{
		ItemStack stack = context.getPlayer().getHeldItemMainhand();
		BlockState state = context.getWorld().getBlockState(context.getPos());
		PlayerEntity player = context.getPlayer();
		World world = context.getWorld();
		Direction facing = context.getFace();
		BlockPos pos = context.getPos();
		if(context.getFace() == Direction.UP && player.canPlayerEdit(pos, facing, stack) && state.getBlock().canSustainPlant(state, world, pos, Direction.UP, this) && world.isAirBlock(pos.up())) 
		{
			world.setBlockState(pos.up(), NEMBlocks.FLAXPLANT.get().getDefaultState());
			world.playSound(player, pos, SoundEvents.ITEM_CROP_PLANT, SoundCategory.BLOCKS, 1.0f, 1.0f);
			if(!player.isCreative() ) 
			{
				stack.shrink(1);
			}
			return ActionResultType.SUCCESS;
		}
		else 
		{
			return ActionResultType.FAIL;
		}
	}



	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos) 
	{
		return PlantType.CROP;
	}



	@Override
	public BlockState getPlant(IBlockReader world, BlockPos pos) 
	{
		return NEMBlocks.FLAXPLANT.get().getDefaultState();
	}
}







