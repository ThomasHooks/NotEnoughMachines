package com.kilroy790.notenoughmachines.tiles;

import com.kilroy790.notenoughmachines.containers.TripHammerContainer;
import com.kilroy790.notenoughmachines.power.MechanicalType;
import com.kilroy790.notenoughmachines.recipes.NEMRecipes;
import com.kilroy790.notenoughmachines.recipes.StampingRecipe;

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




public class TripHammerTile extends MechanicalTile implements INamedContainerProvider {
	
	protected ItemStackHandler itemInputHandler;
	public static final int NUMBER_OF_INPUT_SLOTS = 1;
	protected LazyOptional<ItemStackHandler> itemInput = LazyOptional.of(() -> itemInputHandler);
	protected ItemStackHandler itemOutputHandler;
	public static final int NUMBER_OF_OUTPUT_SLOTS = 2;
	protected LazyOptional<ItemStackHandler> itemOutput = LazyOptional.of(() -> itemOutputHandler);
	public static final int NUMBER_OF_TOTAL_SLOTS = NUMBER_OF_INPUT_SLOTS + NUMBER_OF_OUTPUT_SLOTS;
	protected LazyOptional<CombinedInvWrapper> combinedItemHandler = LazyOptional.of(() -> new CombinedInvWrapper(itemInputHandler, itemOutputHandler));
	
	public static int BASE_POWER_LOAD = 60;
	
	public static final int MIN_PROCESSING_TIME = 300;
	protected boolean isProcessing = false;
	protected int maxProcessTime = 0;
	protected int processTimmer = 0;
	
	protected double displacement = 0.0D;
	protected boolean hammerHasHit = false;
	
	public TripHammerTile() {
		super(0, BASE_POWER_LOAD, MechanicalType.SINK, NEMTiles.TRIPHAMMER.get());
		this.itemInputHandler = this.makeItemHandler(NUMBER_OF_INPUT_SLOTS, 64);
		this.itemOutputHandler = this.makeItemHandler(NUMBER_OF_OUTPUT_SLOTS, 64);
	}
	
	
	
	//TODO: Optimize
	@Override
	public void tick() {
		
		if (!world.isRemote()) {
			
			java.util.Optional<StampingRecipe> recipe = getRecipe();
			this.maxProcessTime = recipe.isPresent() ? recipe.get().getProcessingTime() : MIN_PROCESSING_TIME;
			this.isProcessing = canProcess(recipe) && isPowered();
			if (this.isProcessing) {
				moveHammer();
				this.processTimmer++;
				syncClient();
			}
			else {
				this.processTimmer--;
				if (this.processTimmer < 0) this.processTimmer = 0;
			}
			
			if (this.processTimmer > this.maxProcessTime) {
				processItem(recipe);
				this.processTimmer = 0;
			}
		}		
		super.tick();
	}
	
	
	
	protected boolean canProcess(java.util.Optional<StampingRecipe> recipe) {
		
		if (recipe.isPresent() && !itemInputHandler.getStackInSlot(0).isEmpty()) {
			ItemStack result = recipe.get().getCraftingResult(new RecipeWrapper(itemInputHandler));
			ItemStack outputTest = itemOutputHandler.insertItem(0, result, true);
			ItemStack result2 = recipe.get().getSecondaryCraftingResult();
			ItemStack outputTest2 = itemOutputHandler.insertItem(1, result2, true);
			if (outputTest.isEmpty() && outputTest2.isEmpty()) return true;
		}
		return false;
	}
	
	
	
	protected void processItem(java.util.Optional<StampingRecipe> recipe) {
		
		if (recipe.isPresent()) {
			ItemStack result = recipe.get().getCraftingResult(new RecipeWrapper(itemInputHandler));
			ItemStack outputTest = itemOutputHandler.insertItem(0, result, true);
			ItemStack result2 = recipe.get().getSecondaryCraftingResult();
			ItemStack outputTest2 = itemOutputHandler.insertItem(1, result2, true);
			if (outputTest.isEmpty() && outputTest2.isEmpty()) {
				itemOutputHandler.insertItem(0, result, false);
				itemOutputHandler.insertItem(1, result2, false);
				itemInputHandler.extractItem(0, 1, false);
				markDirty();
			}
		}
	}
	
	
	
	protected java.util.Optional<StampingRecipe> getRecipe() {
		return this.world.getRecipeManager().getRecipe(NEMRecipes.Types.STAMPING, new RecipeWrapper(new ItemStackHandler(NonNullList.withSize(1, this.itemInputHandler.getStackInSlot(0).copy()))), this.world);
	}
	
	
	
	protected void moveHammer() {
		
		double angle = getSpeed() != 0 ? (world.getGameTime() * 30.0f * 0.3f) % 360 : 0;
		this.displacement = 0.4375D * Math.sin(Math.toRadians(angle));
		if (this.displacement < 0.05D) this.displacement = 0.0D;
		
		if (!this.hammerHasHit && this.displacement <= 0.1D) {
			world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 0.5f, 1.25f);//0.75f
			this.hammerHasHit = true;
		}
		else if (this.hammerHasHit && this.displacement > 0.1D) {
			this.hammerHasHit = false;
		}
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
		this.displacement = compound.getDouble("displacement");
		this.maxProcessTime = compound.getInt("processtime");
		this.processTimmer = compound.getInt("processtimmer");
		if (compound.contains("inputinv")) {
			this.itemInputHandler.deserializeNBT(compound.getCompound("inputinv"));
		}
		if (compound.contains("outputinv")) {
			this.itemOutputHandler.deserializeNBT(compound.getCompound("outputinv"));
		}
		super.readCustom(compound);
	}
	
	
	
	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) {
		compound.putDouble("displacement", this.displacement);
		compound.putInt("processtime", this.maxProcessTime);
		compound.putInt("processtimmer", this.processTimmer);
		compound.put("inputinv", this.itemInputHandler.serializeNBT());
		compound.put("outputinv", this.itemOutputHandler.serializeNBT());
		return super.writeCustom(compound);
	}
	
	
	
	@Override
	public void remove() {
		super.remove();
		this.itemInput.invalidate();
		this.itemOutput.invalidate();
		this.combinedItemHandler.invalidate();
	}
	
	
	
	@Override
	public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
		return new TripHammerContainer(id, inventory, this);
	}
	
	
	
	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.notenoughtmachines.triphammer");
	}
	
	
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos()).grow(1.0D, 4.0D, 1.0D);
	}
	
	
	
	public double getDisplacement() {
		return this.displacement;
	}
	
	
	
	public int getNumberOfInventorySlots() {
		return NUMBER_OF_TOTAL_SLOTS;
	}
	
	
	
	public ItemStack getInputItem() {
		return this.itemInputHandler.getStackInSlot(0).copy();
	}
	
	
	
	public int getProcessTime() {
		return this.processTimmer;
	}
	
	
	
	public int getMaxProcessTime() {
		return this.maxProcessTime;
	}
}







