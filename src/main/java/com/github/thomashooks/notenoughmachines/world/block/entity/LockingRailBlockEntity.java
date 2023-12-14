package com.github.thomashooks.notenoughmachines.world.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.block.state.BlockState;

import java.util.UUID;

public class LockingRailBlockEntity extends SyncingBlockEntity
{
    protected AbstractMinecart minecart;
    protected UUID minecartUUID;
    protected double xDelta = 0.0D;
    protected double zDelta = 0.0D;

    public LockingRailBlockEntity(BlockPos pos, BlockState state)
    {
        super(AllBlockEntities.LOCKING_RAIL.get(), pos, state);
    }

    public void lockMinecart(AbstractMinecart cart)
    {
        //This just gets the minecart back after loading
        if (!this.getLevel().isClientSide() && this.minecartUUID != null && this.minecart == null)
        {
            Entity entity = ((ServerLevel) getLevel()).getEntity(this.minecartUUID);
            if (entity instanceof AbstractMinecart abstractMinecart)
                this.minecart = abstractMinecart;
        }

        if (!this.isMinecartLocked())
        {
            this.minecart = cart;
            this.minecartUUID = cart.getUUID();
            this.xDelta = cart.getDeltaMovement().x();
            this.zDelta = cart.getDeltaMovement().z();
            getLevel().playSound(null, getBlockPos(), SoundEvents.PISTON_EXTEND, SoundSource.BLOCKS, 0.5F, 1.25F);
        }

        cart.setDeltaMovement(0.0D, 0.0D, 0.0D);
        cart.setPos(getBlockPos().getX() + 0.5D, getBlockPos().getY(), getBlockPos().getZ() + 0.5D);
    }

    public void unlockMinecart()
    {
        if (this.isMinecartLocked())
        {
            this.minecart.setDeltaMovement(this.xDelta, 0.0D, this.zDelta);
            getLevel().playSound(null, getBlockPos(), SoundEvents.PISTON_CONTRACT, SoundSource.BLOCKS, 0.5F, 1.25F);

            this.minecart = null;
            this.minecartUUID = null;
            this.xDelta = 0.0D;
            this.zDelta = 0.0D;
        }
    }

    public boolean isMinecartLocked() { return this.minecart != null; }

    @Override
    public void load(CompoundTag nbt)
    {
        if (nbt.contains("minecart_uuid"))
        {
            this.minecartUUID = nbt.getUUID("minecart_uuid");
            this.xDelta = nbt.getDouble("minecart_delta_x");
            this.zDelta = nbt.getDouble("minecart_delta_z");
        }
        super.load(nbt);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt)
    {
        if (this.minecartUUID != null)
        {
            nbt.putUUID("minecart_uuid", this.minecartUUID);
            nbt.putDouble("minecart_delta_x", this.xDelta);
            nbt.putDouble("minecart_delta_z", this.zDelta);
        }
        super.saveAdditional(nbt);
    }
}
