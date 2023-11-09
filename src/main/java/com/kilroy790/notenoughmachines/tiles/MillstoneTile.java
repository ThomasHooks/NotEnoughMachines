package com.kilroy790.notenoughmachines.tiles;

import javax.annotation.Nonnull;

import com.kilroy790.notenoughmachines.blocks.machines.MillstoneBlock;
import com.kilroy790.notenoughmachines.containers.MillstoneContainer;
import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.recipes.MillingRecipe;
import com.kilroy790.notenoughmachines.recipes.NEMRecipes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
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




public class MillstoneTile extends MechanicalTile implements INamedContainerProvider 
{	
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
	public static final int MIN_PROCESSING_TIME = 100;
	private boolean isProcessing = false;	
	protected int processTimmer = 0;
	protected int maxProcessTime = 0;
	
	private int timer = 0;
	
	public MillstoneTile() 
	{
		super(0, 20, MechanicalType.SINK, NEMTiles.MILLSTONE.get());
		this.itemInput = makeItemHandler(INPUTSLOTS, 64);
		this.itemOutput = makeItemHandler(OUTPUTSLOTS, 64);
	}
	
	
	
	@Override
	public void tick() 
	{
		if (!world.isRemote) 
		{
			java.util.Optional<MillingRecipe> recipe = getRecipe();
			this.maxProcessTime = recipe.isPresent() ? recipe.get().getProcessingTime() : MIN_PROCESSING_TIME;
			this.isProcessing = canProcess(recipe) && isPowered();
			if (this.isProcessing)
				this.processTime++;
			else
			{
				this.processTime--;
				if (this.processTime < 0)
					this.processTime = 0;
			}
			
			if (this.timer > VALIDATE_TICK) 
			{
				this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(MillstoneBlock.LIT, isProcessing), 1 | 2 | 4);
				this.timer = 0;
			}
			else 
				this.timer++;
			
			if (this.processTime > this.maxProcessTime)
			{
				processItem(recipe);
				this.processTime = 0;
			}
		}		
		super.tick();
	}
	
	
	
	protected boolean canProcess(java.util.Optional<MillingRecipe> recipe) 
	{
		if (recipe.isPresent() && !itemInput.getStackInSlot(0).isEmpty()) 
		{
			ItemStack result = recipe.get().getCraftingResult(new RecipeWrapper(itemInput));
			ItemStack outputTest = itemOutput.insertItem(0, result, true);
			return outputTest.isEmpty();
		}
		else 
			return false;
	}
	
	
	
	protected void processItem(java.util.Optional<MillingRecipe> recipe) 
	{	
		if (recipe.isPresent()) 
		{
			ItemStack result = recipe.get().getCraftingResult(new RecipeWrapper(itemInput));
			ItemStack outputTest = itemOutput.insertItem(0, result, true);
			if (outputTest.isEmpty()) 
			{
				itemOutput.insertItem(0, result, false);
				itemInput.extractItem(0, 1, false);
				markDirty();
			}
		}
	}
	
	
	
	protected java.util.Optional<MillingRecipe> getRecipe() 
	{
		return this.world.getRecipeManager().getRecipe(NEMRecipes.Types.MILLING, new RecipeWrapper(new ItemStackHandler(NonNullList.withSize(1, this.itemInput.getStackInSlot(0).copy()))), this.world);
	}
	
	
	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) 
		{
			if (side == null) 
				return combinedItemHandler.cast();
			
			else if (side == Direction.NORTH || side == Direction.EAST || side == Direction.SOUTH || side == Direction.WEST) 
				return itemInputHandler.cast();
			
			else if (side == Direction.UP || side == Direction.DOWN) 
				return itemOutputHandler.cast();
		}
		return super.getCapability(cap, side);
	}
	
	
	
	@Override
	protected void readCustom(CompoundNBT compound) {
		
		if (compound.contains("input")) {
			CompoundNBT inputInv = compound.getCompound("input");
			itemInputHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(inputInv));
		}
		if (compound.contains("output")) {
			CompoundNBT outputInv = compound.getCompound("output");
			itemOutputHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(outputInv));
		}
		if (compound.contains("processtime")) {
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
	public void remove() 
	{
		this.itemInputHandler.invalidate();
		this.itemOutputHandler.invalidate();
		this.combinedItemHandler.invalidate();
		super.remove();
	}
	
	
    
	@Override
	public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) 
	{
		return new MillstoneContainer(id, inventory, this);
	}


	
	@Override
	public ITextComponent getDisplayName() 
	{
		return new TranslationTextComponent("container.notenoughtmachines.millstone");
	}
	
	
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() 
	{
		return new AxisAlignedBB(getPos()).grow(1.0D);
	}
	
	
	
	public int getProcessTime() 
	{
		return processTime;
	}
	
	
	
	public int getMaxProcessTime() 
	{
		return MAX_PROCESS_TIME;
	}
	
	
	
	public void setProcessTime(int time) 
	{
		if (time > MAX_PROCESS_TIME) 
			this.processTime = MAX_PROCESS_TIME;
		
		else if (time < 0) 
			this.processTime = 0;
		
		else 
			this.processTime = time;
	}
	
	
	
	public static int getSizeInventory() 
	{
		return NUMBER_OF_SLOTS;
	}
	
	
	
	public static int getSizeInput() 
	{
		return INPUTSLOTS;
	}
	
	
	
	public static int getSizeOutput() 
	{
		return OUTPUTSLOTS;
	}
}







