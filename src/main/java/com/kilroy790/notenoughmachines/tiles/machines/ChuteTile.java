package com.kilroy790.notenoughmachines.tiles.machines;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.blocks.machines.ChuteBlock;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;




public class ChuteTile extends TileEntity implements ITickableTileEntity {

	
	protected ItemStackHandler itemInput;
	protected LazyOptional<ItemStackHandler> itemInputHandler = LazyOptional.of(() -> itemInput);
	
	protected int itemTransfer = 0;
	public static final int MAX_ITEM_TRANSFER = 1;
	
	
	public ChuteTile() {
		super(TileEntityList.CHUTE);
		final int INPUTSLOTS = 1;
		itemInput = makeItemHandler(INPUTSLOTS);
	}
	
	
	@Override
	public void tick() {
		
		if(this.canTransferItem()) this.pushItems(MAX_ITEM_TRANSFER);
		else this.itemTransfer++;
	}
	
	
	protected void pushItems(int amount) {
		
		if(this.getWorld().isRemote || itemInput.getStackInSlot(0) == ItemStack.EMPTY) return;
		
		Direction facing = this.getBlockState().get(HorizontalBlock.HORIZONTAL_FACING);
		BlockPos nextPos = pos.offset(facing.getOpposite());
		Block nextBlock = world.getBlockState(nextPos).getBlock();
		
		//drop items in front of the chute
		if(nextBlock == Blocks.AIR) {
			NEMItemHelper.dropItemStack(world, nextPos, itemInput.extractItem(0, amount, false));
			this.setItemTransfer(0);
		}
		//push items to the next Container
		else {
			TileEntity nextTile = world.getTileEntity(nextPos);
			if(nextTile != null) {
				LazyOptional<IItemHandler> nextContainer = nextTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
				nextContainer.ifPresent(h -> {
					if(h.isItemValid(0, itemInput.getStackInSlot(0))) {
						h.insertItem(0, itemInput.extractItem(0, amount, false), false);
						this.setItemTransfer(0);
					}
				});
			}
		}
	}
	
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			
			Direction facing = this.getBlockState().get(ChuteBlock.HORIZONTAL_FACING);
			if(side == facing || side == Direction.UP || side == null) return itemInputHandler.cast();
		}
		
		return super.getCapability(cap, side);
	}
	
	
	@Override
	public void read(CompoundNBT compound) {
		
		if(compound.contains("inv")) {
			CompoundNBT inputInv = compound.getCompound("inv");
			itemInputHandler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(inputInv));
		}
		
		super.read(compound);
	}
	
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		
		itemInputHandler.ifPresent(h -> {
			CompoundNBT inputInv = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
			compound.put("inv", inputInv);
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
	
	
	public boolean canTransferItem() {
		return this.itemTransfer > 8;
	}
	
	
	protected void setItemTransfer(int tick) {
		this.itemTransfer = tick;
	}
	
	
	public float getItemStackDistance() {
		//@return value is between 0 to 1
		if(this.itemTransfer > 8) return 1.0f;
		return (float)this.itemTransfer/8;
	}
}
