package com.kilroy790.notenoughmachines.tiles;

import javax.annotation.Nonnull;

import com.kilroy790.notenoughmachines.gui.MillstoneContainer;
import com.kilroy790.notenoughmachines.lists.TileEntityList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;




public class MillstoneTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider{

	public MillstoneTile() {
		super(TileEntityList.MILLSTONE_TILE);
		
		//these are here so that a combined item hander can be made
		itemInput = makeItemInputHandler();
		itemOutput = makeItemOutputHandler();
	}
	
	
	
	
	protected ItemStackHandler itemInput;
	private LazyOptional<ItemStackHandler> itemInputHandler = LazyOptional.of(() -> itemInput);
	private static final int inputSlots = 1;
	
	
	protected ItemStackHandler itemOutput;
	private LazyOptional<ItemStackHandler> itemOutputHandler = LazyOptional.of(() -> itemOutput);
	private static final int outputSlots = 1;
	
	
	private LazyOptional<CombinedInvWrapper> combinedItemHandler = LazyOptional.of(() -> new CombinedInvWrapper(itemInput, itemOutput));
	private static final int NUMBER_OF_SLOTS = inputSlots + outputSlots;
	
	
	private int processTime = 0;
	//number of tick until done
	//there are ~20 tick per second
	public static final int MAX_PROCESS_TIME = 200;

	


	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			
			if(side == null) {
				//this is so the player can access all of the millstone slots
				return combinedItemHandler.cast();
			}
			if(side == Direction.NORTH || side == Direction.EAST || side == Direction.SOUTH || side == Direction.WEST) {
				//the millstone will except input form any of the sides
				return itemInputHandler.cast();
			}
			if(side == Direction.DOWN) {
				//the millstone only outputs to the bottom
				return itemOutputHandler.cast();
			}
		}
		
		//TODO add a Power capability to the millstone
		
		return super.getCapability(cap, side);
	}
	
	
	@Override
	public void read(CompoundNBT compound) {
		
		if(compound.contains("input")) {
			CompoundNBT inputInv = compound.getCompound("input");
			itemInputHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(inputInv));
		}
		
		if(compound.contains("output")) {
			CompoundNBT outputInv = compound.getCompound("output");
			itemOutputHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(outputInv));
		}
		
		if(compound.contains("process")) {
			processTime = compound.getInt("process");
		}
		
		super.read(compound);
	}
	
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		
		itemInputHandler.ifPresent(h -> {
			CompoundNBT inputInv = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
			compound.put("input", inputInv);
		});
		
		itemOutputHandler.ifPresent(h -> {
			CompoundNBT outputInv = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
			compound.put("output", outputInv);
		});
		
		compound.putInt("process", processTime);
		
		return super.write(compound);
	}
	
	
	@Override
	public void remove() {
		super.remove();
		this.itemInputHandler.invalidate();
		this.itemInputHandler.invalidate();
		this.combinedItemHandler.invalidate();
	}
	
	
	@Override
	public void tick() {
		
		//Increase process timer
		processTime++;
		if (processTime > MAX_PROCESS_TIME) processTime = 0;
	}
	
	
	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity playerEntity) {
		
		return new MillstoneContainer(id, world, pos, playerInv, playerEntity);
	}


	@Override
	public ITextComponent getDisplayName() {
		
		return new StringTextComponent(getType().getRegistryName().getPath());
	}
	
	
	public int getProcessTime() {
		return processTime;
	}
	
	public void setProcessTime(int time) {
		
		if(time > MAX_PROCESS_TIME) {
			this.processTime = MAX_PROCESS_TIME;
		}
		else if(time < 0) {
			this.processTime = 0;
		}
		else this.processTime = time;
	}
	
	
	public static int getSizeInventory() {
		return NUMBER_OF_SLOTS;
	}
	
	
	private ItemStackHandler makeItemInputHandler() {
		//Create a new input item stack handler 
		
		return new ItemStackHandler(inputSlots) {
			
			@Override
			protected void onContentsChanged(int slot) {
				
				markDirty();
			}
		};
	}
	
	
	private ItemStackHandler makeItemOutputHandler() {
		//Create a new output item stack handler 
		
		return new ItemStackHandler(outputSlots) {
			
			@Override
			protected void onContentsChanged(int slot) {
				
				markDirty();
			}
		};
	}
}







