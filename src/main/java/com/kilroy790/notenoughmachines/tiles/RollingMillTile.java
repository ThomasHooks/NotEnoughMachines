package com.kilroy790.notenoughmachines.tiles;

import com.kilroy790.notenoughmachines.blocks.machines.RollingMillBlock;
import com.kilroy790.notenoughmachines.containers.RollingMillContainer;
import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.recipes.NEMRecipes;
import com.kilroy790.notenoughmachines.recipes.RollingRecipe;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.RecipeWrapper;




public class RollingMillTile extends MechanicalTile implements INamedContainerProvider
{
	protected ItemStackHandler itemInputHandler;
	private LazyOptional<ItemStackHandler> itemInput = LazyOptional.of(() -> itemInputHandler);
	private static final int NUMBER_OF_INPUT_SLOTS = 1;
	protected ItemStackHandler itemOutputHandler;
	private LazyOptional<ItemStackHandler> itemOutput = LazyOptional.of(() -> itemOutputHandler);
	private static final int NUMBER_OF_OUTPUT_SLOTS = 1;
	private LazyOptional<CombinedInvWrapper> combinedItemHandler = LazyOptional.of(() -> new CombinedInvWrapper(itemInputHandler, itemOutputHandler));
	private static final int NUMBER_OF_TOTAL_SLOTS = NUMBER_OF_INPUT_SLOTS + NUMBER_OF_OUTPUT_SLOTS;
	
	public static int BASE_POWER_LOAD = 60;
	
	private int processTime = 0;
	private static final int MAX_PROCESS_TIME = 200;
	public static final int MIN_PROCESSING_TIME = 100;
	protected boolean isProcessing = false;
	protected int maxProcessTime = MAX_PROCESS_TIME;
	
	

	public RollingMillTile() 
	{
		super(0, BASE_POWER_LOAD, MechanicalType.SINK, NEMTiles.ROLLING_MILL.get());
		this.itemInputHandler = this.makeItemHandler(NUMBER_OF_INPUT_SLOTS, 64);
		this.itemOutputHandler = this.makeItemHandler(NUMBER_OF_OUTPUT_SLOTS, 64);
	}
	
	
	
	@Override
	public void tick()
	{
		if (!world.isRemote())
		{
			java.util.Optional<RollingRecipe> recipe = getRecipe();
			this.maxProcessTime = recipe.isPresent() ? recipe.get().getProcessingTime() : MIN_PROCESSING_TIME;
			this.isProcessing = canProcess(recipe) && isPowered();
			if (this.isProcessing)
			{
				this.processTime++;
				if (!this.world.getBlockState(this.pos).get(RollingMillBlock.LIT))
					this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(RollingMillBlock.LIT, isProcessing), 1 | 2 | 4);
			}
			else
			{
				this.processTime--;
				if (this.processTime < 0)
					this.processTime = 0;
				if (this.world.getBlockState(this.pos).get(RollingMillBlock.LIT))
					this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(RollingMillBlock.LIT, isProcessing), 1 | 2 | 4);
			}
			
			if (this.processTime > this.maxProcessTime)
			{
				processItem(recipe);
				this.processTime = 0;
				this.world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5f, 1.0f);
			}
		}
		super.tick();
	}
	
	
	
	protected boolean canProcess(java.util.Optional<RollingRecipe> recipe)
	{
		if (recipe.isPresent() && !itemInputHandler.getStackInSlot(0).isEmpty()) 
		{
			ItemStack result = recipe.get().getCraftingResult(new RecipeWrapper(itemInputHandler));
			ItemStack outputTest = itemOutputHandler.insertItem(0, result, true);
			return outputTest.isEmpty();
		}
		else 
			return false;
	}
	
	
	
	protected void processItem(java.util.Optional<RollingRecipe> recipe) 
	{	
		if (recipe.isPresent()) 
		{
			ItemStack result = recipe.get().getCraftingResult(new RecipeWrapper(itemInputHandler));
			ItemStack outputTest = itemOutputHandler.insertItem(0, result, true);
			if (outputTest.isEmpty()) 
			{
				itemOutputHandler.insertItem(0, result, false);
				itemInputHandler.extractItem(0, 1, false);
				markDirty();
			}
		}
	}
	
	
	
	protected java.util.Optional<RollingRecipe> getRecipe() 
	{
		return this.world.getRecipeManager().getRecipe(NEMRecipes.Types.ROLLING, new RecipeWrapper(new ItemStackHandler(NonNullList.withSize(1, this.itemInputHandler.getStackInSlot(0).copy()))), this.world);
	}
	
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
	{
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			if (side == null) 
				return combinedItemHandler.cast();
			
			else if (side == Direction.NORTH || side == Direction.EAST || side == Direction.SOUTH || side == Direction.WEST) 
				return itemInput.cast();
			
			else if (side == Direction.UP || side == Direction.DOWN) 
				return itemOutput.cast();
		}
		return super.getCapability(cap, side);
	}
	
	
	
	@Override
	protected void readCustom(CompoundNBT compound) 
	{	
		this.processTime = compound.getInt("processtime");		
		this.maxProcessTime = compound.getInt("maxprocesstime");
		if (compound.contains("inputinv")) 
			this.itemInputHandler.deserializeNBT(compound.getCompound("inputinv"));
		if (compound.contains("outputinv")) 
			this.itemOutputHandler.deserializeNBT(compound.getCompound("outputinv"));
		
		super.readCustom(compound);
	}
	
	
	
	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) 
	{	
		compound.putInt("processtime", this.processTime);
		compound.putInt("maxprocesstime", this.maxProcessTime);
		compound.put("inputinv", this.itemInputHandler.serializeNBT());
		compound.put("outputinv", this.itemOutputHandler.serializeNBT());
		
		return super.writeCustom(compound);
	}
	
	
	
	@Override
	public void remove() 
	{
		this.itemInput.invalidate();
		this.itemOutput.invalidate();
		this.combinedItemHandler.invalidate();
		super.remove();
	}

	
	
	@Override
	public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) 
	{
		return new RollingMillContainer(id, inventory, this);
	}
	
	

	@Override
	public ITextComponent getDisplayName() 
	{
		return new TranslationTextComponent("container.notenoughtmachines.rollingmill");
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() 
	{
		return new AxisAlignedBB(getPos()).grow(1.0D, 2.0D, 1.0D);
	}
	
	
	
	public int getNumberOfInventorySlots() 
	{
		return NUMBER_OF_TOTAL_SLOTS;
	}
	
	
	
	public static int getSizeInput() 
	{
		return NUMBER_OF_INPUT_SLOTS;
	}
	
	
	
	public static int getSizeOutput() 
	{
		return NUMBER_OF_OUTPUT_SLOTS;
	}
	
	
	
	public ItemStack getInputItem() 
	{
		return this.itemInputHandler.getStackInSlot(0).copy();
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
	
	
	
	public int getProcessTime() 
	{
		return this.processTime;
	}
	
	
	
	public int getMaxProcessTime() 
	{
		return this.maxProcessTime;
	}
}



