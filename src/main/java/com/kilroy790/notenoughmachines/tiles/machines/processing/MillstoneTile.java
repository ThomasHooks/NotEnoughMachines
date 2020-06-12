package com.kilroy790.notenoughmachines.tiles.machines.processing;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import com.kilroy790.notenoughmachines.blocks.machines.processing.MillstoneBlock;
import com.kilroy790.notenoughmachines.containers.MillstoneContainer;
import com.kilroy790.notenoughmachines.power.MechanicalContext;
import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.recipes.MillingRecipe;
import com.kilroy790.notenoughmachines.setup.NEMTiles;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;
import com.kilroy790.notenoughmachines.utilities.MachineIOList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.RecipeWrapper;




public class MillstoneTile extends MechanicalTile implements INamedContainerProvider {
	
	protected ItemStackHandler itemInput;
	protected RecipeWrapper inputRecipe;
	private LazyOptional<ItemStackHandler> itemInputHandler = LazyOptional.of(() -> itemInput);
	private static final int INPUTSLOTS = 1;
	
	protected ItemStackHandler itemOutput;
	private LazyOptional<ItemStackHandler> itemOutputHandler = LazyOptional.of(() -> itemOutput);
	private static final int OUTPUTSLOTS = 1;
	
	private LazyOptional<CombinedInvWrapper> combinedItemHandler = LazyOptional.of(() -> new CombinedInvWrapper(itemInput, itemOutput));
	private static final int NUMBER_OF_SLOTS = INPUTSLOTS + OUTPUTSLOTS;
	
	private int processTime = 0;
	private static final int MAX_PROCESS_TIME = 200;
	private boolean isProcessing = false;
	
	private ArrayList<MechanicalContext> io;
	
	private int timer = 0;
	
	
	
	public MillstoneTile() {
		super(0, 20, MechanicalType.SINK, NEMTiles.MILLSTONE.get());
		
		this.itemInput = makeItemHandler(INPUTSLOTS);
		this.itemOutput = makeItemHandler(OUTPUTSLOTS);
	}
	
	
	
	@Override
	protected void tickCustom() {
		
		if(world.isRemote) return;
		
		//if there is a millable item in the input slot start to process it
		if(attemptMill() && isPowered()) {
			isProcessing = true;
		} 
		else isProcessing = false;
		
		if(isProcessing && isPowered()) {
			processTime++;
		} 
		else {
			processTime--;
			if(processTime < 0) processTime = 0;
		}
		
		if(processTime >= MAX_PROCESS_TIME) {
			millItem();
			isProcessing = false;
			processTime = 0;
		}

		if(this.timer > VALIDATE_TICK) {
			this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(MillstoneBlock.LIT, isProcessing), 1 | 2 | 4);
			this.timer = 0;
		}
		else this.timer++;
	}
	
	
	
	/**
	 * @return True if the item stack in the input can be milled
	 */
    private boolean attemptMill() {
    	
    	for(int i = 0; i < INPUTSLOTS; i++) {
    		
    		ItemStack stack = itemInput.getStackInSlot(i).copy();
    		if(!stack.isEmpty()) {
    			
    			//TODO: I might change this to use ItemStackHandler instead of IInventoy
    			IInventory inventory = new Inventory(stack);
    			MillingRecipe recipe = world.getRecipeManager().getRecipe(MillingRecipe.milling, inventory, world).orElse(null);
    			if(recipe == null) return false;
    			
    			ItemStack result = recipe.getCraftingResult(inventory);
        		
        		ItemStack output = itemOutput.insertItem(i, result.copy(), true);
        		if(output.isEmpty()) return true;
    		}
    		else return false;
    	}
    	
    	return false;
    }
    
    
    
    /**
     * Mills the item stack in the input slot
     */
    private void millItem() {
    	
    	for(int i = 0; i < INPUTSLOTS; i++) {
    		
    		ItemStack stack = itemInput.getStackInSlot(i).copy();
    		if(!stack.isEmpty()) {
    			
    			//TODO: I might change this to use ItemStackHandler instead of IInventoy
    			IInventory inventory = new Inventory(stack);
    			MillingRecipe recipe = world.getRecipeManager().getRecipe(MillingRecipe.milling, inventory, world).orElse(null);
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
	public void onLoad() {
		io = MachineIOList.monoAxle(pos, Direction.Axis.Y);
		super.onLoad();
	}
	
	
	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			
			if(side == null) {
				return combinedItemHandler.cast();
			}
			
			if(side == Direction.NORTH || side == Direction.EAST || side == Direction.SOUTH || side == Direction.WEST) {
				return itemInputHandler.cast();
			}
			
			if(side == Direction.UP || side == Direction.DOWN) {
				return itemOutputHandler.cast();
			}
		}
		return super.getCapability(cap, side);
	}
	
	
	
	@Override
	protected void readCustom(CompoundNBT compound) {

		if(compound.contains("input")) {
			CompoundNBT inputInv = compound.getCompound("input");
			itemInputHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(inputInv));
		}

		if(compound.contains("output")) {
			CompoundNBT outputInv = compound.getCompound("output");
			itemOutputHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(outputInv));
		}

		if(compound.contains("processtime")) {
			processTime = compound.getInt("processtime");
		}

		super.readCustom(compound);
	}
	
	
	
	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) {
		
		itemInputHandler.ifPresent(h -> {
			CompoundNBT inputInv = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
			compound.put("input", inputInv);
		});

		itemOutputHandler.ifPresent(h -> {
			CompoundNBT outputInv = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
			compound.put("output", outputInv);
		});

		compound.putInt("processtime", processTime);
		
		return super.writeCustom(compound);
	}
	
	
	
	@Override
	public void remove() {
		
		this.itemInputHandler.invalidate();
		this.itemOutputHandler.invalidate();
		this.combinedItemHandler.invalidate();
		
		super.remove();
	}

	

	@Override
	public ArrayList<MechanicalContext> getIO() {
		return io;
	}
	
	
    
	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity playerEntity) {
		return new MillstoneContainer(id, world, pos, playerInv, playerEntity);
	}


	
	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("machine.notenoughtmachines.millstone");
	}
	
	
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos()).grow(1.0D);
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
}







