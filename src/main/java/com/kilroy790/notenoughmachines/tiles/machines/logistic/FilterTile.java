package com.kilroy790.notenoughmachines.tiles.machines.logistic;

import java.util.List;
import java.util.stream.Collectors;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.blocks.machines.logistic.FilterBlock;
import com.kilroy790.notenoughmachines.containers.FilterContainer;
import com.kilroy790.notenoughmachines.tiles.NEMBaseTile;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;




public class FilterTile extends NEMBaseTile implements INamedContainerProvider, ITickableTileEntity {
	//TODO look into making it into a smart filter
	
	protected ItemStackHandler itemInv;
	public static final int INVENTORYSLOTS = 4;
	
	protected ItemStackHandler itemFilter;
	public static final int FILTERSLOTS = 1;
	protected LazyOptional<ItemStackHandler> itemFilterHandler = LazyOptional.of(() -> itemFilter);
	
	public static final int COMBINEDSLOTS = FILTERSLOTS + INVENTORYSLOTS;
	protected LazyOptional<CombinedInvWrapper> combinedItemHandler = LazyOptional.of(() -> new CombinedInvWrapper(itemFilter, itemInv));
	
	protected int itemTransfer = 0;
	public static final int MAX_ITEM_TRANSFER = 1;
	public static final int ITEM_TRANSFER_RATE = 8;
	
	
	public FilterTile() {
		super(TileEntityList.FILTER);
		itemInv = this.makeItemHandler(INVENTORYSLOTS);
		itemFilter = this.makeItemHandler(FILTERSLOTS);
	}
	
	
	@Override
	public void tick() {
		
		if(!this.world.isRemote) {
			//move items from the filter slot to the Filter's inventory
			NEMItemHelper.moveItemsInternally(this.itemFilter, 0, 1, this.itemInv);
			
			if(this.canTransferItem()) {
				//push items in the Filter's inventory to the container it's facing
				this.pushItems(this.itemInv, MAX_ITEM_TRANSFER);
				//pull items into the Filter's filter slot
				this.pullItems(this.itemFilter, MAX_ITEM_TRANSFER);
				//Reset the Filter's item transfer cool-down
				this.setItemTransfer(0);
			}
			//Increment the Filter's item transfer cool-down
			this.itemTransfer++;
		}
	}
	
	
	protected void pushItems(ItemStackHandler itemHandler, int amount) {
		/*
		 * Will try to push the item stack into the next Container
		 * 
		 * @param	itemHandler		the item handler for this tile
		 * 
		 * @param	amount			number of items in the stack that will be pushed to the next Container
		 */

		Direction facing = this.getBlockState().get(FilterBlock.FACING);
		for(int slot = 0; slot < itemHandler.getSlots(); slot++) {

			if(itemHandler.getStackInSlot(slot).isEmpty()) continue;

			else {
				NEMItemHelper.pushToContainer(world, pos.offset(facing), facing.getOpposite(), itemHandler, slot, amount);
				break;
			}
		}
	}
	
	
	protected void pullItems(ItemStackHandler itemHandler, int amount) {
		/*
		 * Will try to pull items inside of the capture area into the filter slot
		 * 
		 * @param	itemHandler		the item handler for this tile
		 * 
		 * @param	amount			number of items in the stack that will be pulled
		 */
		
		BlockPos posUp = this.getPos().up();
		if(this.world.getBlockState(posUp).isAir(this.getWorld(), posUp)) {
			for(ItemEntity itemEntity : this.getCapturedItems()) {
				this.captureItem(itemEntity);
			}
		}
		else NEMItemHelper.pullFromContainer(world, posUp, Direction.DOWN, itemHandler, 0, amount);
	}
	
	
	public void captureItem(ItemEntity item) {
		//Will try to add the item entity into the filter slot
		
		ItemStack stack = item.getItem().copy();
		ItemStack stackRemander = itemFilter.insertItem(0, stack, false);
		if(stackRemander.isEmpty()) item.remove();
		else item.setItem(stackRemander);
	}
	
	
	protected List<ItemEntity> getCapturedItems() {
		return FilterBlock.COLLECTION_AREA_SHAPE.toBoundingBoxList().stream().flatMap(h -> {
			return this.getWorld().getEntitiesWithinAABB(ItemEntity.class, h.offset(this.getPos().getX() - 0.5D, this.getPos().getY() - 0.5D, this.getPos().getZ() - 0.5D), EntityPredicates.IS_ALIVE).stream();
		}).collect(Collectors.toList());
	}
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			//This is done so the player can interact with the Filter's combined inventory
			if(side == null) return combinedItemHandler.cast();
			else return itemFilterHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	
	@Override
	protected void readCustom(CompoundNBT compound) {
		this.itemFilter.deserializeNBT(compound.getCompound("filter"));
		this.itemInv.deserializeNBT(compound.getCompound("inv"));
		this.itemTransfer = compound.getInt("transfertime");
	}

	
	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) {
		compound.put("filter", this.itemFilter.serializeNBT());
		compound.put("inv", this.itemInv.serializeNBT());
		compound.putInt("transfertime", this.itemTransfer);
		return compound;
	}
	
	
	@Override
	public void remove() {
		super.remove();
		this.itemFilterHandler.invalidate();
		this.combinedItemHandler.invalidate();
	}


	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity playerEntity) {
		return new FilterContainer(id, world, pos, playerInv, playerEntity);
	}


	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("block.notenoughtmachines.filter");
	}
	
	
	public int getNumberOfInventorySlots() {
		return INVENTORYSLOTS;
	}
	
	
	public boolean canTransferItem() {
		//@return	true if it can transfer items
		return this.itemTransfer > ITEM_TRANSFER_RATE && this.world.getBlockState(pos).get(FilterBlock.ENABLED);
	}
	
	
	protected void setItemTransfer(int tick) {
		this.itemTransfer = tick;
		markDirty();
	}
}
