package com.github.thomashooks.notenoughmachines.world.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.items.ItemStackHandler;

public abstract class BaseBlockEntity extends BlockEntity
{
    protected BaseBlockEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state)
    {
        super(entityType, pos, state);
    }

    @Override
    public void load(CompoundTag compoundTag)
    {
        super.load(compoundTag);
        this.loadCustom(compoundTag);
    }

    protected abstract void loadCustom(CompoundTag compoundTag);

    @Override
    protected void saveAdditional(CompoundTag compoundTag)
    {
        super.saveAdditional(compoundTag);
        this.saveCustom(compoundTag);
    }

    protected abstract void saveCustom(CompoundTag compoundTag);

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt)
    {
        super.onDataPacket(net, pkt);
    }

    public void syncClient()
    {
        if (!this.level.isClientSide) this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2 | 16);
    }

    public void triggerBlockUpdate()
    {
        if (!this.level.isClientSide) this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 1);
    }

    /**
     * Creates a new Item Stack Handler with the given parameters
     *
     * @param numSlots The number of slots this Item Stack Handler has
     * @param stackLimit The Item Stack limit for each Item slot
     *
     * @return A new Item Stack Handler with the given parameters
     */
    protected ItemStackHandler makeItemHandler(int numSlots, int stackLimit)
    {
        return new ItemStackHandler(numSlots)
        {
            @Override
            public int getSlotLimit(int slot)
            {
                int limit;
                if (Math.abs(stackLimit) < 1)
                    limit = 1;
                else
                    limit = Math.min(Math.abs(stackLimit), 64);
                return limit;
            }

            @Override
            protected void onContentsChanged(int slot)
            {
                setChanged();
            }
        };
    }
}
