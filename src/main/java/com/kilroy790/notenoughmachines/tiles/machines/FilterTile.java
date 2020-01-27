package com.kilroy790.notenoughmachines.tiles.machines;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.blocks.machines.FilterBlock;
import com.kilroy790.notenoughmachines.containers.FilterContainer;
import com.kilroy790.notenoughmachines.tiles.AbstractNEMBaseTile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;




public class FilterTile extends AbstractNEMBaseTile implements INamedContainerProvider {
	
	
	protected ItemStackHandler itemInv;
	public static final int INVENTORYSLOTS = 4;
	protected LazyOptional<ItemStackHandler> itemInvHandler = LazyOptional.of(() -> itemInv);
	
	protected ItemStackHandler itemFilter;
	public static final int FILTERSLOTS = 1;
	
	protected LazyOptional<CombinedInvWrapper> combinedItemHandler = LazyOptional.of(() -> new CombinedInvWrapper(itemFilter, itemInv));
	public static final int COMBINEDSLOTS = FILTERSLOTS + INVENTORYSLOTS;
	
	protected int itemTransfer = 0;
	public static final int MAX_ITEM_TRANSFER = 1;
	public static final int ITEM_TRANSFER_RATE = 8;
	
	
	public FilterTile() {
		super(TileEntityList.FILTER);
		itemInv = this.makeItemHandler(INVENTORYSLOTS);
		itemFilter = this.makeItemHandler(FILTERSLOTS);
	}
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			//So the player can interact with the Filter
			if(side == null) return combinedItemHandler.cast();
			else if(side != this.getBlockState().get(FilterBlock.FACING)) return itemInvHandler.cast();
		}
		
		return super.getCapability(cap, side);
	}

	
	@Override
	protected void readCustom(CompoundNBT compound) {
		itemInv.deserializeNBT(compound.getCompound("inv"));
		this.itemTransfer = compound.getInt("transfertime");
	}

	
	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) {
		compound.put("inv", itemInv.serializeNBT());
		compound.putInt("transfertime", this.itemTransfer);
		return compound;
	}
	
	
	@Override
	public void remove() {
		super.remove();
		this.itemInvHandler.invalidate();
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
}
