package com.kilroy790.notenoughmachines.blocks.machines;

import java.util.List;

import com.kilroy790.notenoughmachines.tiles.machines.SmallWindWheelTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;




public class SmallWindWheelBlock extends AbstractGeneratorBlock {

	
	protected static final VoxelShape[] SHAPE_BY_DIR = new VoxelShape[]{Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D), Block.makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D), Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D)};
	protected static final int AXELAXISX = 2;
	protected static final int AXELAXISY = 0;
	protected static final int AXELAXISZ = 1;
	
	
	public SmallWindWheelBlock(String name) {
		super(Properties.create(Material.WOOD)
				.sound(SoundType.WOOD)
				.hardnessAndResistance(1.8f, 2.0f)
				.harvestTool(ToolType.AXE)
				.harvestLevel(0), name);
	}
	
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		
		if(placer == null || world.isRemote) return;
		
		Direction dir = placer.getHorizontalFacing();
		world.setBlockState(pos, state.with(this.getPowerBlockFacing(), dir.getOpposite()), 1|2);
		
		SmallWindWheelTile tile = (SmallWindWheelTile) world.getTileEntity(pos);
		if(!tile.validateArea()) {
			
			placer.sendMessage(new StringTextComponent("Wind Wheel needs 15x15x1 area of free space").setStyle(new Style().setColor(TextFormatting.RED)));
			world.destroyBlock(pos, true);
		}
		Block nextBlock = world.getBlockState(pos.offset(dir)).getBlock();
		if(!(nextBlock instanceof AxleBlock || nextBlock instanceof GearboxBlock)) {
			placer.sendMessage(new StringTextComponent("Wind Wheel must be placed on either an Axle or Gearbox").setStyle(new Style().setColor(TextFormatting.RED)));
			world.destroyBlock(pos, true);
		}
	}
	
	
	@Override
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		
		StringTextComponent powerText = new StringTextComponent("Outputs 2.88kW");
		Style style = new Style();
		
		style.setColor(TextFormatting.AQUA);
		style.setItalic(true);
		powerText.setStyle(style);
		
		tooltip.add(powerText);
	
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos position, ISelectionContext context) {
		//Set the bounding box based on the direction that the block is facing
		
		Direction facing = state.get(this.getPowerBlockFacing());
		if(facing == Direction.EAST || facing == Direction.WEST) return SHAPE_BY_DIR[AXELAXISX];
		else return SHAPE_BY_DIR[AXELAXISZ];
	}
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		
		return new SmallWindWheelTile();
	}
}
