package com.kilroy790.notenoughmachines.blocks.machines.power;

import java.util.Random;

import com.kilroy790.notenoughmachines.api.stateproperties.NEMBlockStateProperties;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalBlock;
import com.kilroy790.notenoughmachines.tiles.machines.power.GearboxTile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;




public class GearboxBlock extends MechanicalBlock {
	
	public static final BooleanProperty POWERED = NEMBlockStateProperties.POWERED;
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

	
	
	public GearboxBlock(String name) {
		super(Properties.create(Material.WOOD)
						.sound(SoundType.WOOD)
						.hardnessAndResistance(1.8f, 2.0f)
						.harvestTool(ToolType.AXE)
						.harvestLevel(0));
		
		this.setRegistryName(name);
		
		this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.Y).with(POWERED, Boolean.valueOf(false)));
	}
	
	
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(AXIS, context.getNearestLookingDirection().getAxis());
	}
	
	
	
//	@Override
//	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
//		
//		if (player.abilities.allowEdit && player.getHeldItemMainhand().isEmpty()) {
//			
//			BlockState oldState = world.getBlockState(pos);
//			world.setBlockState(pos, state.with(AXIS, getFacingFromEntity(pos, player).getAxis()).with(POWERED, world.getBlockState(pos).get(POWERED)), 2|1);
//			
//			double d0 = (double)pos.getX() + 0.5D;
//			double d1 = (double)pos.getY() + 0.5D;
//			double d2 = (double)pos.getZ() + 0.5D;
//			world.playSound(d0, d1, d2, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F, false);
//			
//			world.notifyBlockUpdate(pos, oldState, world.getBlockState(pos), 2|1);
//			
//			return true;
//		}
//		else return false;
//	}
	
	
	
	/**
	* Called periodically clientside on blocks near the player to show effects (like furnace fire particles). Note that
	* this method is unrelated to {@link randomTick} and {@link #needsRandomTick}, and will always be called regardless
	* of whether the block can receive random update ticks
	*/
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		
		if (stateIn.get(POWERED)) {
  
			double d0 = (double)pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.75D;
			double d1 = (double)pos.getY() + 1.0D + (rand.nextDouble() - 0.5D) * 0.2D;
			double d2 = (double)pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.75D;
			worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	        
			if (rand.nextDouble() < 0.05D) {
				worldIn.playSound(d0, d1, d2, SoundEvents.ENTITY_MINECART_INSIDE, SoundCategory.BLOCKS, 0.025F, 0.75F, false);
			}
		}
	}
	
	
	
//	private static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
//		return Direction.getFacingFromVector((float) (entity.posX - clickedBlock.getX()), (float) (entity.posY - clickedBlock.getY()), (float) (entity.posZ - clickedBlock.getZ()));
//	}
	
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(AXIS, POWERED);
	}
	
	

	@Override
	public ItemStack itemWhenDestroyed() {
		int rand = (int)(Math.random() * 4.0D);
		return new ItemStack(Items.OAK_WOOD, rand);
	}

	

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new GearboxTile();
	}
}







