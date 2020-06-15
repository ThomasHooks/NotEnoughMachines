package com.kilroy790.notenoughmachines.tiles.machines.logistic;

import com.kilroy790.notenoughmachines.blocks.machines.logistic.ItemPusherBlock;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.ItemConduitTile;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;




public class ItemPusherTile extends ItemConduitTile {

	protected LazyOptional<ItemStackHandler> itemInvHandler = LazyOptional.of(() -> this.inventory.get(0));



	public ItemPusherTile() {
		super(NEMTiles.ITEMPUSHER.get());
		addItemStackHandler(1, 1);
	}



	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return itemInvHandler.cast();
		return super.getCapability(cap, side);
	}
	
	
	
	@Override
	public boolean canTransferItem() {
		return super.canTransferItem() && this.world.getBlockState(pos).get(ItemPusherBlock.ENABLED);
	}



	@Override
	public boolean canPullItems() {
		return true;
	}



	@Override
	public boolean canPushItems() {
		return true;
	}



	@Override
	public void remove() {
		super.remove();
		this.itemInvHandler.invalidate();
	}
}







