package com.kilroy790.notenoughmachines.blocks.machines.power;

import java.util.ArrayList;
import java.util.List;

import com.kilroy790.notenoughmachines.blocks.machines.MechanicalBlock;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalHorizontalBlock;
import com.kilroy790.notenoughmachines.power.MechanicalConnectionList;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.tiles.machines.power.SmallWindWheelTile;
import com.kilroy790.notenoughmachines.utilities.NEMInputHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class SmallWindWheelBlock extends MechanicalHorizontalBlock 
{
	protected static final VoxelShape[] SHAPE_BY_DIR = new VoxelShape[]
		{
			Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D), 
			Block.makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 16.0D), 
			Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D)
		};
	protected static final int AXELAXISX = 2;
	protected static final int AXELAXISY = 0;
	protected static final int AXELAXISZ = 1;

	public SmallWindWheelBlock(Properties properties) 
	{
		super(properties);
	}



	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {

		if (placer == null || world.isRemote) return;

		Direction dir = placer.getHorizontalFacing();
		SmallWindWheelTile tile = (SmallWindWheelTile) world.getTileEntity(pos);
		if (!tile.validateArea()) {
			placer.sendMessage(new StringTextComponent("Wind Wheel needs 16x16x1 area of free space").setStyle(new Style().setColor(TextFormatting.RED)));
			world.destroyBlock(pos, true);
		}
		Block nextBlock = world.getBlockState(pos.offset(dir)).getBlock();
		if (!(nextBlock instanceof MechanicalBlock)) {
			placer.sendMessage(new StringTextComponent("Wind Wheel must be placed on a machine").setStyle(new Style().setColor(TextFormatting.RED)));
			world.destroyBlock(pos, true);
		}
	}



	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return super.getStateForPlacement(context).with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}



	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) 
	{
		if (NEMInputHelper.isPressingShift()) 
		{
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("Creates mechanical power from the wind").applyTextStyle(TextFormatting.GREEN));
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new StringTextComponent("Must be placed " + "\u00A72" + "Above Ground" + "\u00A77").applyTextStyle(TextFormatting.GRAY));
		}
		else 
		{
			tooltip.add(new StringTextComponent(NEMInputHelper.MORE_INFO_PRESS_SHIFT));
		}
	}



	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos position, ISelectionContext context) 
	{
		Direction facing = state.get(FACING);
		if (facing == Direction.EAST || facing == Direction.WEST) 
		{
			return SHAPE_BY_DIR[AXELAXISX];
		}
		else 
		{
			return SHAPE_BY_DIR[AXELAXISZ];
		}
	}



	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return new SmallWindWheelTile();
	}



	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}



	@Override
	public ItemStack itemWhenDestroyed() 
	{
		return null;
	}



	@Override
	public ArrayList<MechanicalContext> getIO(World world, BlockPos pos, BlockState state) 
	{
		return MechanicalConnectionList.monoAxle(pos, state.get(FACING).getAxis());
	}
}







