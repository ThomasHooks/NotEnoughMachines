package com.kilroy790.notenoughmachines.tiles.machines.logistic;

import java.util.Objects;

import javax.annotation.Nullable;

import com.kilroy790.notenoughmachines.blocks.machines.logistic.ItemPusherBlock;
import com.kilroy790.notenoughmachines.containers.FilterContainer;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.ItemConduitTile;
import com.kilroy790.notenoughmachines.utilities.NEMDirectionHelper;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;




public class FilterTile extends ItemConduitTile implements INamedContainerProvider {

	private final static int BUFFER = 0;
	private final static int NUMBER_OF_BUFFER_SLOTS = 1;
	private final static int BUFFER_SLOT = 0;
	private final static int RED_FILTER = 1;
	private final static int GREEN_FILTER = 2;
	private final static int BLUE_FILTER = 3;
	private final static int YELLOW_FILTER	 = 4;
	private final static int NUMBER_OF_FILTERS = 4;
	private final static int NUMBER_OF_FILTER_SLOTS = 7;
	private LazyOptional<ItemStackHandler> bufferHandler = LazyOptional.of(() -> this.inventory.get(BUFFER));
	private LazyOptional<CombinedInvWrapper> combinedHandler = LazyOptional.of(
			() -> new CombinedInvWrapper(
					this.inventory.get(BUFFER), 
					this.inventory.get(RED_FILTER), 
					this.inventory.get(GREEN_FILTER), 
					this.inventory.get(BLUE_FILTER), 
					this.inventory.get(YELLOW_FILTER)));
	
	
	public enum FilterColor {
		RED(1),
		GREEN(2),
		BLUE(3),
		YELLOW(4);
		
		private int number;
		
		private FilterColor(int numberIn) {
			this.number = numberIn;
		}
		
		public int getSlot() {
			return this.number;
		}
		
		@Nullable
		static public FilterColor getFilter(int number) {
			switch(number) {
			case 1:
				return RED;
			case 2:
				return GREEN;
			case 3:
				return BLUE;
			case 4:
				return YELLOW;
			default:
				return null;
			}
		}
	}



	public FilterTile() {
		super(NEMTiles.FILTER.get());
		
		//Buffer
		this.addItemStackHandler(NUMBER_OF_BUFFER_SLOTS);
		
		//Filters
		for(int i = 0; i < NUMBER_OF_FILTERS; i++) {
			this.addItemStackHandler(NUMBER_OF_FILTER_SLOTS, 1);
		}
	}
	
	
	
	@Override
	public void tick() {
		if(!this.world.isRemote) {
			if(canTransferItem()) {
				pushItems(this.inventory.get(BUFFER), MAX_ITEM_TRANSFER);
				this.transferTimer = 0;
				markDirty();
			}
			this.transferTimer++;
			if(this.transferTimer > ITEM_TRANSFER_RATE) this.transferTimer = ITEM_TRANSFER_RATE;
		}
	}
	
	
	
	/**
	 * Will try to push Items to the Container or drop Items behind this Item Conduit
	 * 
	 * @param itemHandler The item handler for this tile
	 * @param amount Number of items in the stack that will be pushed to the next Container
	 */
	@Override
	protected void pushItems(ItemStackHandler itemHandler, int amount) {

		ItemStack stackInBuffer = this.inventory.get(BUFFER).getStackInSlot(0);
		if(stackInBuffer.isEmpty()) return;

		for(int i = RED_FILTER; i <= NUMBER_OF_FILTERS; i++) {
			for(int slot = 0; slot < NUMBER_OF_FILTER_SLOTS; slot++) {
				ItemStack stackInFilter = this.inventory.get(i).getStackInSlot(slot);
				if(stackInFilter.isEmpty()) continue;

				if(stackInBuffer.getItem().equals(stackInFilter.getItem())) {
					Direction facing = getFilterFacing(FilterColor.getFilter(i));
					Objects.requireNonNull(facing, "Direction cannot be null!");
					BlockPos nextPos = pos.offset(facing);
					if(this.world.getBlockState(nextPos).isAir(this.getWorld(), nextPos)) {
						NEMItemHelper.dropItemStack(world, nextPos, itemHandler.extractItem(BUFFER_SLOT, amount, false));
					}
					else {
						NEMItemHelper.pushToContainer(world, pos.offset(facing), facing.getOpposite(), itemHandler, BUFFER_SLOT, amount);
					}
					return;
				}
			}
		}
		super.pushItems(itemHandler, amount);
	}
	
	
	
	@Nullable
	protected Direction getFilterFacing(FilterColor filter) {
		Direction facing = this.getBlockState().get(ItemPusherBlock.FACING);
		switch(filter) {

		case RED:
			if(facing == Direction.UP || facing == Direction.DOWN) {
				boolean ccw = facing == Direction.DOWN ? false : true;
				Direction dir = Direction.UP;
				return NEMDirectionHelper.rotateX(dir, ccw, 1);
			}
			else return Direction.UP;

		case BLUE:
			if(facing == Direction.UP || facing == Direction.DOWN) {
				boolean ccw = facing == Direction.DOWN ? false : true;
				Direction dir = Direction.DOWN;
				return NEMDirectionHelper.rotateX(dir, ccw, 1);
				
			}
			else return Direction.DOWN;

		case GREEN:
			if(facing == Direction.UP || facing == Direction.DOWN) return Direction.WEST;
			else {
				int times = 0;
				if(facing == Direction.NORTH) times = 0;
				else if(facing == Direction.EAST) times = 1;
				else if(facing == Direction.SOUTH) times = 2;
				else if(facing == Direction.WEST) times = 3;
				Direction dir = Direction.WEST;
				return NEMDirectionHelper.rotateY(dir, false, times);
			}

		case YELLOW:
			if(facing == Direction.UP || facing == Direction.DOWN) return Direction.EAST;
			else {
				int times = 0;
				if(facing == Direction.NORTH) times = 0;
				else if(facing == Direction.EAST) times = 1;
				else if(facing == Direction.SOUTH) times = 2;
				else if(facing == Direction.WEST) times = 3;
				Direction dir = Direction.EAST;
				return NEMDirectionHelper.rotateY(dir, false, times);
			}

		default:
			return null;
		}
	}



	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if(side == null) return combinedHandler.cast();
			else return bufferHandler.cast();
		}
		return super.getCapability(cap, side);
	}



	@Override
	public void remove() {
		super.remove();
		this.bufferHandler.invalidate();
		this.combinedHandler.invalidate();
	}

	
	
	@Override
	public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
		return new FilterContainer(id, inventory, this);
	}



	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.notenoughtmachines.filter");
	}
}







