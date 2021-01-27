package com.kilroy790.notenoughmachines.blocks.machines.power;

import java.util.ArrayList;
import java.util.List;

import com.kilroy790.notenoughmachines.blocks.machines.MechanicalBlock;
import com.kilroy790.notenoughmachines.power.MechanicalConnectionList;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.tiles.machines.power.CreativePowerBoxTile;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class CreativePowerBoxBlock extends MechanicalBlock {

	public CreativePowerBoxBlock(Properties properties) {
		super(properties);
	}

	
	
	@Override
	public ItemStack itemWhenDestroyed() {
		return new ItemStack(Items.AIR);
	}

	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new CreativePowerBoxTile();
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		Style style = new Style();
		style.setColor(TextFormatting.AQUA);
		style.setItalic(true);
		StringTextComponent powerText = new StringTextComponent("How did you get this?");
		powerText.setStyle(style);
		tooltip.add(powerText);
	}



	@Override
	public ArrayList<MechanicalContext> getIO(World world, BlockPos pos, BlockState state) {
		return MechanicalConnectionList.triAxle(pos);
	}
}







