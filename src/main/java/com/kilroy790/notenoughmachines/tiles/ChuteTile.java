package com.kilroy790.notenoughmachines.tiles;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;




public class ChuteTile extends ItemConduitTile {
	
	private final static int NUMBER_OF_BUFFER_SLOTS = 1;
	private final static int BUFFER_SLOTS_LIMIT = 1;
	private final static int BUFFER = 0;
	private LazyOptional<ItemStackHandler> bufferHandler = LazyOptional.of(() -> this.inventory.get(BUFFER));
	
	
	
	public ChuteTile() {
		super(NEMTiles.CHUTE.get());
		addItemStackHandler(NUMBER_OF_BUFFER_SLOTS, BUFFER_SLOTS_LIMIT);
	}
	
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return this.bufferHandler.cast();
		return super.getCapability(cap, side);
	}
	
	
	
	@Override
	public boolean canPushItems() {
		return true;
	}



	@Override
	public void remove() {
		super.remove();
		this.bufferHandler.invalidate();
	}
}







