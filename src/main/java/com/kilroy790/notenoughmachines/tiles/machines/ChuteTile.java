package com.kilroy790.notenoughmachines.tiles.machines;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.blocks.machines.ChuteBlock;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;




public class ChuteTile extends TileEntity {

	
	protected ItemStackHandler itemInput;
	protected LazyOptional<ItemStackHandler> itemInputHandler = LazyOptional.of(() -> itemInput);
	
	private float itemDistance = 0.0f;
	
	
	public ChuteTile() {
		super(TileEntityList.CHUTE);
		final int INPUTSLOTS = 1;
		itemInput = makeItemHandler(INPUTSLOTS);
	}
	
	
	protected void pushItemStack() {
		
	}
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		
		Direction facing = this.world.getBlockState(pos).get(ChuteBlock.HORIZONTAL_FACING);
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side == facing.getOpposite()) {
			
		}
		
		return super.getCapability(cap, side);
	}
	
	
	@Override
	public void read(CompoundNBT compound) {
		
		if(compound.contains("input")) {
			CompoundNBT inputInv = compound.getCompound("input");
			itemInputHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(inputInv));
		}
		
		super.read(compound);
	}
	
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		
		itemInputHandler.ifPresent(h -> {
			CompoundNBT inputInv = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
			compound.put("input", inputInv);
		});
		
		return super.write(compound);
	}
	
	
	@Override
	public void remove() {
		super.remove();
		this.itemInputHandler.invalidate();
	}
	
	
	protected ItemStackHandler makeItemHandler(int numSlots) {
		return new ItemStackHandler(numSlots) {
			
			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
			}
		};
	}
	
	
	public float getItemStackDistance() {
		//@return value is between 0 to 1
		return itemDistance;
	}
}
