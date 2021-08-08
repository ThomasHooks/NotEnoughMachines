package com.kilroy790.notenoughmachines.blocks.machines;

import java.util.List;

import com.kilroy790.notenoughmachines.tiles.ChuteTile;
import com.kilroy790.notenoughmachines.utilities.NEMInputHelper;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class ChuteBlock extends ItemConduitBlock 
{
	public ChuteBlock(Properties properties) 
	{
		super(properties);
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		if (NEMInputHelper.isPressingShift()) 
		{
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("Pushes items forward").mergeStyle(TextFormatting.GREEN));
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("Hold " + "\u00A72" + "Shift" + "\u00A77" + " to reverse while placing").mergeStyle(TextFormatting.GRAY));
		}
		else {
			tooltip.add(new StringTextComponent(NEMInputHelper.MORE_INFO_PRESS_SHIFT));
		}
	}

	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return new ChuteTile();
	}
}







