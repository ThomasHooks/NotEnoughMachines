package com.kilroy790.notenoughmachines.blocks.machines.power;

import java.util.List;

import com.kilroy790.notenoughmachines.tiles.machines.power.CreativePowerBoxTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;




public class CreativePowerBoxBlock extends Block {

	public CreativePowerBoxBlock(String name) {
		super(Properties.create(Material.WOOD)
				.sound(SoundType.WOOD)
				.hardnessAndResistance(1.8f, 2.0f)
				.harvestTool(ToolType.AXE)
				.harvestLevel(0));
		this.setRegistryName(name);
	}
	
	
	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
		
		if(world.isRemote) return;
	}
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		
		return true;
	}
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		
		return new CreativePowerBoxTile();
	}
	
	
	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		
		StringTextComponent powerText = new StringTextComponent("Outputs 1MW");
		Style style = new Style();
		
		style.setColor(TextFormatting.AQUA);
		style.setItalic(true);
		powerText.setStyle(style);
		
		tooltip.add(powerText);
	
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
