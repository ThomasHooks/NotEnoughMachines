package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.world.block.SackBlock;
import com.github.thomashooks.notenoughmachines.world.inventory.SackMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SackBlockEntity extends ItemContainerBlockEntity implements MenuProvider
{
    public static final int COLUMNS = 9;
    public static final int ROWS = 1;
    public static final int CONTAINER_SIZE = COLUMNS * ROWS;
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter()
    {
        @Override
        protected void onOpen(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState state)
        {
            SackBlockEntity.this.playSound(SoundEvents.BUNDLE_DROP_CONTENTS);
            SackBlockEntity.this.updateBlockState(state, true);
        }

        @Override
        protected void onClose(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState state)
        {
            SackBlockEntity.this.playSound(SoundEvents.BUNDLE_INSERT);
            SackBlockEntity.this.updateBlockState(state, false);
        }

        @Override
        protected void openerCountChanged(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState state, int previousOpenCount, int openCount) { }

        @Override
        protected boolean isOwnContainer(@NotNull Player player)
        {
            if (player.containerMenu instanceof SackMenu sackMenu)
                return sackMenu.getContainer() == SackBlockEntity.this;
            else
                return false;
        }
    };

    public SackBlockEntity(BlockPos pos, BlockState state)
    {
        super(AllBlockEntities.SACK.get(), pos, state);
        this.inventoryItemHandler = makeItemHandler(CONTAINER_SIZE, 64);
        setLazyTimerAlarm(30);
    }

    @Override
    protected void lazyTick()
    {
        if (!this.getLevel().isClientSide())
            this.recheckOpen();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
            return this.lazyInventoryItemHandler.cast();

        return super.getCapability(cap, side);
    }

    @Override
    public int getContainerSize() { return CONTAINER_SIZE; }

    @Override
    public @NotNull Component getDisplayName() { return SackMenu.TITLE; }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory playerInventory, @NotNull Player player) { return new SackMenu(containerId, playerInventory, this); }

    public void startOpen(Player player)
    {
        if (!this.remove && !player.isSpectator())
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());

    }

    public void stopOpen(Player player)
    {
        if (!this.remove && !player.isSpectator())
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
    }

    public void recheckOpen()
    {
        if (!this.remove)
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
    }

    private void updateBlockState(BlockState state, boolean isOpen)
    {
        this.level.setBlock(this.getBlockPos(), state.setValue(SackBlock.OPEN, Boolean.valueOf(isOpen)), 3);
    }

    private void playSound(SoundEvent soundEvent)
    {
        double x = (double) this.worldPosition.getX() + 0.5D;
        double y = (double) this.worldPosition.getY() + 1.0D;
        double z = (double) this.worldPosition.getZ() + 0.5D;
        this.level.playSound(null, x, y, z, soundEvent, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }
}
