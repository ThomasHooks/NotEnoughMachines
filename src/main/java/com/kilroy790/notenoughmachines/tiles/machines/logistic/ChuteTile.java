package com.kilroy790.notenoughmachines.tiles.machines.logistic;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.api.stateproperties.ChuteType;
import com.kilroy790.notenoughmachines.blocks.machines.logistic.ChuteBlock;
import com.kilroy790.notenoughmachines.tiles.NEMBaseTile;
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
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;




public class ChuteTile extends NEMBaseTile implements ITickableTileEntity {
	//TODO look into why the chute transfers items so slow
	
	protected ItemStackHandler itemInput;
	protected LazyOptional<ItemStackHandler> itemInputHandler = LazyOptional.of(() -> itemInput);
	
	protected int itemTransfer = 0;
	public static final int MAX_ITEM_TRANSFER = 1;
	public static final int ITEM_TRANSFER_RATE = 8;
	
	
	public ChuteTile() {
		super(TileEntityList.CHUTE);
		final int INPUTSLOTS = 1;
		itemInput = this.makeItemHandler(INPUTSLOTS);
	}
	
	
	@Override
	public void tick() {
		
		if(this.canTransferItem()) this.pushItems(MAX_ITEM_TRANSFER);
		
		else if(!this.getWorld().isRemote && !this.itemInput.getStackInSlot(0).isEmpty()) this.itemTransfer++;
		
		this.syncClient();
	}
	
	
	protected void pushItems(int amount) {
		/*
		 * Will try to push the item stack into the next Container or the Hopper below it
		 * If the Chute can't push into a Container or a Hopper it will drop the item stack in-front, as long as the Chute is not blocked
		 * 
		 * @param	amount		number of items in the stack that will be pushed to the next Container, Hopper, or dropped
		 */
		
		if(this.getWorld().isRemote || itemInput.getStackInSlot(0) == ItemStack.EMPTY) return;
		
		Direction facing = this.getBlockState().get(HorizontalBlock.HORIZONTAL_FACING);
		BlockPos nextPos = pos.offset(facing.getOpposite());
		Block nextBlock = world.getBlockState(nextPos).getBlock();
		
		//drop items in front of the Chute
		if(nextBlock == Blocks.AIR) {
			NEMItemHelper.dropItemStack(world, nextPos, itemInput.extractItem(0, amount, false));
			this.setItemTransfer(0);
		}
		//push items to the next Container
		else {
			//if the Chute is above a Hopper, push items into it first
			if(this.getBlockState().get(ChuteBlock.TYPE) == ChuteType.HOPPER) this.pushToContainer(this.getPos().down(), amount);
			//push items into the next Container
			this.pushToContainer(nextPos, amount);
			
			this.setItemTransfer(0);
		}
	}
	
	
	protected void pushToContainer(BlockPos nextPos, int amount) {
		/*
		 * Will try to push an item stack of size given by amount to the tile at the given block position
		 * 
		 * @param	nextPos		position of the tile to push the item stack into
		 * 
		 * @param	amount		the number of items in the item stack to push
		 */
		
		Direction facing = this.getBlockState().get(HorizontalBlock.HORIZONTAL_FACING);
		TileEntity nextTile = world.getTileEntity(nextPos);
		final int CHUTE_ITEM_SLOT = 0;
		if(nextTile != null && !this.itemInput.getStackInSlot(CHUTE_ITEM_SLOT).isEmpty()) {

			LazyOptional<IItemHandler> nextContainer = nextTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
			nextContainer.ifPresent(h -> {

				ItemStack stackIn = itemInput.getStackInSlot(CHUTE_ITEM_SLOT).copy();
				stackIn.setCount(amount);
				int numOfSlots = h.getSlots();

				for(int slot = 0; slot < numOfSlots; slot++) {

					if(h.isItemValid(slot, stackIn)) {

						ItemStack stackRemander = h.insertItem(slot, stackIn, true);

						if(stackRemander.isEmpty()) {

							h.insertItem(slot, stackIn, false);
							itemInput.extractItem(CHUTE_ITEM_SLOT, amount, false);
							break;
						}
						else continue;
					}
				}
			});
		}
	}
	
	
	//update the Chute's state and then sync the tile to the client
	@Override
	public void syncClient() {
		if(!this.world.isRemote) {
			ChuteBlock.updateType(this.getBlockState(), this.getWorld(), this.getPos());
			this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 1|2);
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
	public void remove() {
		super.remove();
		this.itemInputHandler.invalidate();
	}
	
	
	public ItemStack getItemStack() {
		return this.itemInput.getStackInSlot(0);
	}
	
	
	public boolean canTransferItem() {
		return this.itemTransfer > ITEM_TRANSFER_RATE;
	}
	
	
	protected void setItemTransfer(int tick) {
		this.itemTransfer = tick;
		markDirty();
	}
	
	
	public double getItemStackDistance() {
		//@return the item stacks distance, the value is between 0.0D to 1.0D
		
		if(this.itemTransfer > ITEM_TRANSFER_RATE) return 1.0D - (1.0D/(double)ITEM_TRANSFER_RATE);
		
		else return ((double)this.itemTransfer/(double)ITEM_TRANSFER_RATE) - (1.0D/(double)ITEM_TRANSFER_RATE);
	}


	@Override
	protected void readCustom(CompoundNBT compound) {
		
		itemInput.deserializeNBT(compound.getCompound("inv"));
		this.itemTransfer = compound.getInt("transfertime");
	}


	@Override
	protected CompoundNBT writeCustom(CompoundNBT compound) {
		
		compound.put("inv", itemInput.serializeNBT());
		compound.putInt("transfertime", this.itemTransfer);
		return compound;
	}
}
