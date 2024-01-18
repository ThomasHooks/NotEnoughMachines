package com.github.thomashooks.notenoughmachines.world.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

abstract public class ItemContainerBlockEntity extends LazyTickingBlockEntity
{
    protected ItemStackHandler inventoryItemHandler;
    protected LazyOptional<ItemStackHandler> lazyInventoryItemHandler = LazyOptional.of(() -> inventoryItemHandler);
    public static final String ITEMS_TAG = "Items";

    protected ItemContainerBlockEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state) { super(entityType, pos, state); }

    @Override
    public void onLoad()
    {
        super.onLoad();
        this.lazyInventoryItemHandler = LazyOptional.of(()-> this.inventoryItemHandler);
    }

    @Override
    public void invalidateCaps()
    {
        this.lazyInventoryItemHandler.invalidate();
        super.invalidateCaps();
    }

    @Override
    public void load(CompoundTag nbt)
    {
        super.load(nbt);
        this.inventoryItemHandler.deserializeNBT(nbt.getCompound(ITEMS_TAG));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt)
    {
        super.saveAdditional(nbt);
        nbt.put(ITEMS_TAG, this.inventoryItemHandler.serializeNBT());
    }

    public abstract int getContainerSize();

    public int getMaxStackSize() { return 64; }

    public boolean isEmpty()
    {
        for (int i = 0; i < this.inventoryItemHandler.getSlots(); i++)
        {
            if (!this.inventoryItemHandler.getStackInSlot(i).isEmpty())
                return false;
        }
        return true;
    }

    public int getRedstoneSignal()
    {
        int numberOfItemStacks = 0;
        float redstoneSignal = 0.0F;

        for(int slot = 0; slot < this.getContainerSize(); ++slot)
        {
            ItemStack itemstack = this.inventoryItemHandler.getStackInSlot(slot);
            if (!itemstack.isEmpty())
            {
                redstoneSignal += (float)itemstack.getCount() / (float)Math.min(this.getMaxStackSize(), itemstack.getMaxStackSize());
                ++numberOfItemStacks;
            }
        }

        redstoneSignal /= (float)this.getContainerSize();
        return Mth.floor(redstoneSignal * 14.0F) + (numberOfItemStacks > 0 ? 1 : 0);
    }

    /**
     * Creates a new item stack handler with the given parameters
     * @param size The number of slots this item stack handler has
     * @param slotLimit The item stack limit for each item slot
     * @return A new item stack handler with the given parameters
     */
    public ItemStackHandler makeItemHandler(int size, int slotLimit)
    {
        return new ItemStackHandler(size)
        {
            @Override
            public int getSlotLimit(int slot)
            {
                int limit;
                if (Math.abs(slotLimit) < 1)
                    limit = 1;
                else
                    limit = Math.min(Math.abs(slotLimit), 64);
                return limit;
            }

            @Override
            protected void onContentsChanged(int slot) { setChanged(); }
        };
    }
}
