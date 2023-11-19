package com.github.thomashooks.notenoughmachines.world.block.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public abstract class SyncingBlockEntity extends BlockEntity
{
    protected SyncingBlockEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state)
    {
        super(entityType, pos, state);
    }

    @Override
    public void load(CompoundTag nbt)
    {
        super.load(nbt);
    }

    /**
     * Special loading for client side nbt packets
     * @param nbt This block entity's data packet
     */
    protected void loadClient(CompoundTag nbt)
    {
        load(nbt);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt)
    {
        super.saveAdditional(nbt);
    }

    /**
     * Special saving for client side nbt packets
     * @param nbt This block entity's data packet
     */
    protected void saveClient(CompoundTag nbt)
    {
        saveAdditional(nbt);
    }

    //
    @Override
    public CompoundTag getUpdateTag()
    {
        CompoundTag nbt = new CompoundTag();
        saveClient(nbt);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag nbt)
    {
        loadClient(nbt);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt)
    {
        CompoundTag nbt = pkt.getTag();
        if (nbt == null)
            nbt = new CompoundTag();
        loadClient(nbt);
    }

    /**
     * Causes the Client to sync with the Server
     */
    public void syncClient()
    {
        if (this.level.isClientSide)
            return;
        this.level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    public void sendData()
    {
        if (this.level instanceof ServerLevel server)
            server.getChunkSource().blockChanged(getBlockPos());
    }
}
