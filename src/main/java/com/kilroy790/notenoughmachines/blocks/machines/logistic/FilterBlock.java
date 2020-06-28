package com.kilroy790.notenoughmachines.blocks.machines.logistic;

import java.util.List;

import com.kilroy790.notenoughmachines.blocks.machines.ItemConduitBlock;
import com.kilroy790.notenoughmachines.tiles.machines.logistic.FilterTile;
import com.kilroy790.notenoughmachines.utilities.NEMInputHelper;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		
		if (NEMInputHelper.isPressingShift()) {
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("Routes items in different directions").applyTextStyle(TextFormatting.GREEN));
			tooltip.add(new StringTextComponent("depending upon which color the item").applyTextStyle(TextFormatting.GREEN));
			tooltip.add(new StringTextComponent("has been sorted into").applyTextStyle(TextFormatting.GREEN));
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("Hold " + "\u00A72" + "Shift" + "\u00A77" + " to reverse while placing").applyTextStyle(TextFormatting.GRAY));
		}
		else {
			tooltip.add(new StringTextComponent(NEMInputHelper.MORE_INFO_PRESS_SHIFT));
		}
	}
	
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new FilterTile();
	}
}







