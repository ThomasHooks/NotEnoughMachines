package com.kilroy790.notenoughmachines.tiles.machines;

import com.kilroy790.notenoughmachines.api.lists.TileEntityList;
import com.kilroy790.notenoughmachines.blocks.machines.ChuteBlock;
import com.kilroy790.notenoughmachines.utilities.NEMItemHelper;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;




public class ChuteTile extends TileEntity implements ITickableTileEntity {

	
	protected ItemStackHandler itemInput;
	protected LazyOptional<ItemStackHandler> itemInputHandler = LazyOptional.of(() -> itemInput);
	
	protected int itemTransfer = 0;
	public static final int MAX_ITEM_TRANSFER = 1;
	public static final int ITEM_TRANSFER_RATE = 8;
	
	
	public ChuteTile() {
		super(TileEntityList.CHUTE);
		final int INPUTSLOTS = 1;
		itemInput = makeItemHandler(INPUTSLOTS);
	}
	
	
	@Override
	public void tick() {
		
		if(this.canTransferItem()) {
			this.pushItems(MAX_ITEM_TRANSFER);
		}
		else {
			this.itemTransfer++;
			this.world.notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 1|2);
		}
	}
	
	
	protected void pushItems(int amount) {
		
		if(this.getWorld().isRemote || itemInput.getStackInSlot(0) == ItemStack.EMPTY) return;
		
		Direction facing = this.getBlockState().get(HorizontalBlock.HORIZONTAL_FACING);
		BlockPos nextPos = pos.offset(facing.getOpposite());
		Block nextBlock = world.getBlockState(nextPos).getBlock();
		
		//drop items in front of the chute
		if(nextBlock == Blocks.AIR) {
			NEMItemHelper.dropItemStack(world, nextPos, itemInput.extractItem(0, amount, false));
		}
		//push items to the next Container
		else {
			TileEntity nextTile = world.getTileEntity(nextPos);
			if(nextTile != null) {
				
				//This is done so that the items will render correctly, otherwise they do not stay in the chute long enough to be rendered
				if(nextTile instanceof ChuteTile) ((ChuteTile) nextTile).setItemTransfer(0);
				
				LazyOptional<IItemHandler> nextContainer = nextTile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing);
				nextContainer.ifPresent(h -> {
					
					ItemStack stackIn = itemInput.getStackInSlot(0).copy();
					stackIn.setCount(amount);
					int numOfSlots = h.getSlots();
					
					for(int slot = 0; slot < numOfSlots; slot++) {
						
						if(h.isItemValid(slot, stackIn)) {
							
							ItemStack stackRemander = h.insertItem(slot, stackIn, true);
							
							if(stackRemander.isEmpty()) {
								
								h.insertItem(slot, stackIn, false);
								itemInput.extractItem(0, amount, false);
								break;
							}
							else continue;
						}
					}
				});
			}
		}
		
		this.setItemTransfer(0);
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
		
		super.read(compound);
		itemInput.deserializeNBT(compound.getCompound("inv"));
		this.itemTransfer = compound.getInt("transfertime");
	}
	
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		
		compound = super.write(compound);
		compound.put("inv", itemInput.serializeNBT());
		compound.putInt("transfertime", this.itemTransfer);
		return compound;
	}
	
	
	@Override
	public CompoundNBT getUpdateTag() {
		return this.write(new CompoundNBT());
	}
	
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getPos(), 0, this.getUpdateTag());
	}
	
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		//NotEnoughMachines.logger.debug("hit");
		this.read(pkt.getNbtCompound());
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
		//@return the item stacks distance the value is between 0 to 1
		//if(this.itemTransfer > ITEM_TRANSFER_RATE) return 1.0D;
		if(this.itemTransfer < 0) return 0.0D;
		else return (double)this.itemTransfer/(double)ITEM_TRANSFER_RATE;
	}
}
