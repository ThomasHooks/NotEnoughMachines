package com.kilroy790.notenoughmachines.tiles.machines;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.blocks.machines.FilterBlock;
import com.kilroy790.notenoughmachines.containers.FilterContainer;
import com.kilroy790.notenoughmachines.tiles.AbstractNEMBaseTile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;




public class FilterTile extends AbstractNEMBaseTile implements INamedContainerProvider, ITickableTileEntity {
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
			this.moveItemsInternally(this.itemFilter, 0, 1, this.itemInv);
			//push items in the Filter's inventory to the container it's facing
			if(this.canTransferItem()) this.pushItems(this.itemInv, MAX_ITEM_TRANSFER);
			//Increment the Filter's item transfer cool-down
			this.itemTransfer++;
		}
	}
	
	
	protected void moveItemsInternally(ItemStackHandler itemHandler1, int index, int amount, ItemStackHandler itemHandler2) {
		
		ItemStack stackIn = itemHandler1.getStackInSlot(index).copy();
		if(!stackIn.isEmpty() && stackIn.getCount() > 1) {
			
			stackIn.setCount(amount);
			for(int slot = 0; slot < itemHandler2.getSlots(); slot++) {
				
				if(itemHandler2.isItemValid(slot, stackIn)) {

					ItemStack stackRemander = itemHandler2.insertItem(slot, stackIn, true);
					if(stackRemander.isEmpty()) {

						itemHandler2.insertItem(slot, stackIn, false);
						itemHandler1.extractItem(index, amount, false);
						break;
					}

					else continue;
				}
			}
		}
	}
	
	
	protected void pushItems(ItemStackHandler itemHandler, int amount) {
		/*
		 * Will try to push the item stack into the next Container
		 * 
		 * @param	amount		number of items in the stack that will be pushed to the next Container
		 */

		Direction facing = this.getBlockState().get(FilterBlock.FACING);
		for(int slot = 0; slot < itemHandler.getSlots(); slot++) {

			if(itemHandler.getStackInSlot(slot).isEmpty()) continue;

			else {
				this.pushToContainer(pos.offset(facing), facing.getOpposite(), itemHandler, slot, amount);
				break;
			}
		}

		this.setItemTransfer(0);
	}
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			//So the player can interact with the Filter
			if(side == null) return combinedItemHandler.cast();
			
			else if(side != this.getBlockState().get(FilterBlock.FACING)) return itemFilterHandler.cast();
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
		return (this.itemTransfer > ITEM_TRANSFER_RATE);
	}
	
	
	protected void setItemTransfer(int tick) {
		this.itemTransfer = tick;
		markDirty();
	}
}
