package com.kilroy790.notenoughmachines.blocks.machines;

import java.util.Random;

import com.kilroy790.notenoughmachines.tiles.machines.MillstoneTile;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;




public class MillstoneBlock extends Block{

	public MillstoneBlock(String name) {
		super(Properties.create(Material.ROCK)
				.sound(SoundType.STONE)
				.hardnessAndResistance(2.8f, 3.0f)
				.harvestTool(ToolType.PICKAXE)
				.harvestLevel(0));
		this.setRegistryName(name);
		this.setDefaultState(this.stateContainer.getBaseState().with(LIT, Boolean.valueOf(false)));
	}

	
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		
		return true;
	}
	
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		
		return new MillstoneTile();
	}
	
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {

		if(world.isRemote) {
			return true;
		} else {
			TileEntity entity = world.getTileEntity(pos);
			if(entity instanceof INamedContainerProvider) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) entity, entity.getPos());
			} else {
                throw new IllegalStateException("Millstone container provider is missing!");
            }
			return true;
		}
	}
	
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		/*
		 * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
		 * this block
		 */

		TileEntity tile = worldIn.getTileEntity(pos);
		if(tile instanceof MillstoneTile) {
			NEMItemHelper.dropItemHandlerInventory(tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(null), worldIn, pos);
		}
		
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	
	@OnlyIn(Dist.CLIENT) 
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		/**
		* Called periodically clientside on blocks near the player to show effects (like furnace fire particles). Note that
		* this method is unrelated to {@link randomTick} and {@link #needsRandomTick}, and will always be called regardless
		* of whether the block can receive random update ticks
		*/
		
		if (stateIn.get(LIT)) {
  
			double d0 = (double)pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.75D;
			double d1 = (double)pos.getY() + 1.0D + (rand.nextDouble() - 0.5D) * 0.2D;
			double d2 = (double)pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.75D;
			worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	        
			worldIn.playSound(d0, d1, d2, SoundEvents.ENTITY_MINECART_RIDING, SoundCategory.BLOCKS, 0.25F, 0.75F, false);
		}
	}
	
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(LIT);
	}
}
