package com.kilroy790.notenoughmachines.tiles.machines.processing;

import com.kilroy790.notenoughmachines.containers.TripHammerContainer;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.NEMBaseTile;

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




public class TripHammerTile extends NEMBaseTile implements INamedContainerProvider {
	
	protected ItemStackHandler itemInputHandler;
	public static final int INPUTSLOTS = 1;
	protected LazyOptional<ItemStackHandler> itemInput = LazyOptional.of(() -> itemInputHandler);
	protected ItemStackHandler itemOutputHandler;
	public static final int OUTPUTSLOTS = 2;
	protected LazyOptional<ItemStackHandler> itemOutput = LazyOptional.of(() -> itemOutputHandler);
	public static final int COMBINEDSLOTS = INPUTSLOTS + OUTPUTSLOTS;
	protected LazyOptional<CombinedInvWrapper> combinedItemHandler = LazyOptional.of(() -> new CombinedInvWrapper(itemInputHandler, itemOutputHandler));
	
	public TripHammerTile() {
		super(NEMTiles.TRIPHAMMER.get());
		this.itemInputHandler = this.makeItemHandler(INPUTSLOTS, 64);
		this.itemOutputHandler = this.makeItemHandler(OUTPUTSLOTS, 64);
	}

	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (side == null) return combinedItemHandler.cast();
			
			else if (side == Direction.DOWN) return itemOutput.cast();
			
			else return itemInput.cast();
		}
		return super.getCapability(cap, side);
	}
	
	
	
	@Override
	protected void readCustom(CompoundNBT compound) {
		
	}

	
	
	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) {
		return compound;
	}

	
	
	@Override
	public void remove() {
		super.remove();
		this.itemInput.invalidate();
		this.itemOutput.invalidate();
		this.combinedItemHandler.invalidate();
	}
	
	
	
	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity playerEntity) {
		return new TripHammerContainer(id, world, pos, playerInv, playerEntity);
	}

	
	
	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("machine.notenoughtmachines.triphammer");
	}


	
	public int getNumberOfInventorySlots() {
		return COMBINEDSLOTS;
	}
}







