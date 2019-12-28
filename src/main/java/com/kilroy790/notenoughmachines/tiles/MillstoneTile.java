package com.kilroy790.notenoughmachines.tiles;

import javax.annotation.Nonnull;

import com.kilroy790.notenoughmachines.api.crafting.MillingRecipe;
import com.kilroy790.notenoughmachines.api.power.CapabilityMechanical;
import com.kilroy790.notenoughmachines.api.power.IMechanicalPower;
import com.kilroy790.notenoughmachines.api.power.MechanicalPowerConsumer;
import com.kilroy790.notenoughmachines.blocks.machines.MillstoneBlock;
import com.kilroy790.notenoughmachines.gui.MillstoneContainer;
import com.kilroy790.notenoughmachines.lists.TileEntityList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
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
import net.minecraftforge.items.wrapper.RecipeWrapper;




public class MillstoneTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

	public MillstoneTile() {
		super(TileEntityList.MILLSTONE_TILE);
		
		//these are here so that a combined item hander can be made
		itemInput = makeItemInputHandler();
		itemOutput = makeItemOutputHandler();
		
		powerInput = makeMechanicalPowerHandler();
		
		isProcessing = false;
	}
	
	
	//TODO add a Power capability to the millstone
	
	
	protected ItemStackHandler itemInput;
	protected RecipeWrapper inputRecipe;
	private LazyOptional<ItemStackHandler> itemInputHandler = LazyOptional.of(() -> itemInput);
	
	private static final int INPUTSLOTS = 1;
	
	
	protected ItemStackHandler itemOutput;
	private LazyOptional<ItemStackHandler> itemOutputHandler = LazyOptional.of(() -> itemOutput);
	
	private static final int OUTPUTSLOTS = 1;
	
	
	private LazyOptional<CombinedInvWrapper> combinedItemHandler = LazyOptional.of(() -> new CombinedInvWrapper(itemInput, itemOutput));
	
	private static final int NUMBER_OF_SLOTS = INPUTSLOTS + OUTPUTSLOTS;
	
	
	private MechanicalPowerConsumer powerInput;
	private LazyOptional<IMechanicalPower> powerInputHandler = LazyOptional.of(() -> powerInput);
	
	private static final int POWERCAPACITY = 800;
	private static final int MAXPOWERRECEIVED = 30;
	private static final int MAXPOWERSENT = 0;
	private static final int PROCESSINGPOWER = 5;
	
	
	//there are ~20 tick per second
	private int processTime = 0;
	private static final int MAX_PROCESS_TIME = 200;
	private boolean isProcessing;
	
	

	
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
		else if(cap == CapabilityMechanical.MECHANICAL) {
			if(side == Direction.UP) {
				return powerInputHandler.cast();
			}
		}
		
		
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
		
		if(world.isRemote) return;
		
		//if there is a millable item in the input slot start to process it
		if(attemptMill() && powerInput.isPowered()) {
			isProcessing = true;
		} else isProcessing = false;
		
		//Increase process timer and consume power
		if(isProcessing) {
			powerInput.consumePower(PROCESSINGPOWER, false);
			processTime++;
		} else {
			processTime--;
			if(processTime < 0) processTime = 0;
		}
		
		if(processTime >= MAX_PROCESS_TIME) {
			millItem();
			isProcessing = false;
		}

		//Update the millstone's block state
		this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(MillstoneBlock.LIT, isProcessing), 3);
	}
	
	
    private boolean attemptMill() {
    	/*
    	 * attempts to mill the input item(s)
    	 * @return true if the input(s) can be milled, false otherwise
    	 */
    	
    	//Loop over all input slots and check for a millable item
    	for(int i = 0; i < INPUTSLOTS; i++) {
    		
    		ItemStack stack = itemInput.getStackInSlot(i).copy();
    		if(!stack.isEmpty()) {
    			
    			//I might change this to use ItemStackHandler instead of IInventoy
    			IInventory inventory = new Inventory(stack);
    			//get the recipe for the given item, if it's not valid recipe will equal null
    			MillingRecipe recipe = world.getRecipeManager().getRecipe(MillingRecipe.milling, inventory, world).orElse(null);
    			
    			//This checks if the recipe is valid
    			//this is done to prevent a crash if an invalid item is put into the input slot
    			if(recipe == null) return false;
    			
    			ItemStack result = recipe.getCraftingResult(inventory);
        		
        		ItemStack output = itemOutput.insertItem(i, result.copy(), true);
        		if(output.isEmpty()) return true;
    		}
    		else return false;
    	}
    	
    	return false;
    }
    
    
    private void millItem() {
    	//mills the item(s) in the input slot
    	
    	//Loop over all input slots and check for a millable item
    	for(int i = 0; i < INPUTSLOTS; i++) {
    		
    		ItemStack stack = itemInput.getStackInSlot(i).copy();
    		if(!stack.isEmpty()) {
    			
    			//I might change this to use ItemStackHandler instead of IInventoy
    			IInventory inventory = new Inventory(stack);
    			//get the recipe for the given item, if it's not valid recipe will equal null
    			MillingRecipe recipe = world.getRecipeManager().getRecipe(MillingRecipe.milling, inventory, world).orElse(null);
    			
    			//This checks if the recipe is valid
    			//this is done to prevent a crash if an invalid item is put into the input slot
    			if(recipe == null) break;
    			
    			ItemStack result = recipe.getCraftingResult(inventory);
        		
        		ItemStack output = itemOutput.insertItem(i, result.copy(), false);
        		if(output.isEmpty()) {
        			itemInput.extractItem(i, 1, false);
        			processTime = 0;
        			markDirty();
        		}
    		}
    	}
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
	
	public int getMaxProcessTime() {
		return MAX_PROCESS_TIME;
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
	
	public static int getSizeInput() {
		return INPUTSLOTS;
	}
	
	public static int getSizeOutput() {
		return OUTPUTSLOTS;
	}
	
	
	private ItemStackHandler makeItemInputHandler() {
		//Create a new input item stack handler 
		
		return new ItemStackHandler(INPUTSLOTS) {
			
			@Override
			protected void onContentsChanged(int slot) {
				
				markDirty();
			}
		};
	}
	
	
	private ItemStackHandler makeItemOutputHandler() {
		//Create a new output item stack handler 
		
		return new ItemStackHandler(OUTPUTSLOTS) {
			
			@Override
			protected void onContentsChanged(int slot) {
				
				markDirty();
			}
		};
	}
	
	
	private MechanicalPowerConsumer makeMechanicalPowerHandler() {
		
		return new MechanicalPowerConsumer(POWERCAPACITY, MAXPOWERRECEIVED, MAXPOWERSENT);
	}
}







