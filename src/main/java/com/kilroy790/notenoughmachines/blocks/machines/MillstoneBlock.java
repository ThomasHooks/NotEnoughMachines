package com.kilroy790.notenoughmachines.blocks.machines;

import java.util.ArrayList;
import java.util.Random;

import com.kilroy790.notenoughmachines.power.MechanicalConnectionList;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.tiles.MillstoneTile;
import com.kilroy790.notenoughmachines.utilities.NEMBlockShapes;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;




public class MillstoneBlock extends MechanicalBlock 
{
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	

	public MillstoneBlock(Properties properties) 
	{
		super(properties);
		this.setDefaultState(this.getDefaultState().with(LIT, Boolean.valueOf(false)));
	}



	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) 
	{
		if (world.isRemote) 
		{
			return ActionResultType.SUCCESS; 
		}
		else 
		{
			TileEntity entity = world.getTileEntity(pos);
			if (entity instanceof INamedContainerProvider) 
			{
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) entity, entity.getPos());
			} 
			else 
			{
				throw new IllegalStateException("Millstone container provider is missing!");
			}
			return ActionResultType.SUCCESS;
		}
	}



	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		
		TileEntity tile = worldIn.getTileEntity(pos);
		if (tile instanceof MillstoneTile) 
		{
			NEMItemHelper.dropItemHandlerInventory(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(null), worldIn, pos);
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}



	@Override
	@OnlyIn(Dist.CLIENT) 
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) 
	{
		if (stateIn.get(LIT)) 
		{
			double d0 = (double)pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.75D;
			double d1 = (double)pos.getY() + 0.6D + (rand.nextDouble() - 0.5D) * 0.2D;
			double d2 = (double)pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.75D;
			worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			worldIn.playSound(d0, d1, d2, SoundEvents.ENTITY_MINECART_RIDING, SoundCategory.BLOCKS, 0.25F, 0.75F, false);
		}
	}



	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) 
	{
		return NEMBlockShapes.MILLSTONE;
	}



	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) 
	{
		super.fillStateContainer(builder);
		builder.add(LIT);
	}



	@Override
	public ItemStack itemWhenDestroyed() 
	{
		int rand = (int)(Math.random() * 4.0D);
		return new ItemStack(Items.SMOOTH_STONE_SLAB, rand + 1);
	}



	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		return new MillstoneTile();
	}



	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{ 
		return BlockRenderType.MODEL;
	}



	@Override
	public ArrayList<MechanicalContext> getIO(World world, BlockPos pos, BlockState state) 
	{
		return MechanicalConnectionList.monoAxle(pos, Direction.Axis.Y);
	}
}







