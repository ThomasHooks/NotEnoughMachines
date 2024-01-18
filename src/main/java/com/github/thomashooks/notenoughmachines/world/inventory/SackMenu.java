package com.github.thomashooks.notenoughmachines.world.inventory;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.block.SackBlock;
import com.github.thomashooks.notenoughmachines.world.block.entity.SackBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.NotNull;

public class SackMenu extends AbstractContainerMenu
{
    public static final Component TITLE = Component.translatable("container." + NotEnoughMachines.MOD_ID + ".sack");
    private final ContainerLevelAccess levelAccess;
    private final SackBlockEntity container;
    private static final int PLAYER_INVENTORY_START_INDEX = 0;
    private static final int PLAYER_INVENTORY_END_INDEX = 36;
    private static final int BLOCK_INVENTORY_START_INDEX = PLAYER_INVENTORY_START_INDEX + PLAYER_INVENTORY_END_INDEX;
    private static final int NUMBER_OF_BLOCK_SLOTS = SackBlockEntity.CONTAINER_SIZE;
    private static final int BLOCK_INVENTORY_END_INDEX = BLOCK_INVENTORY_START_INDEX + NUMBER_OF_BLOCK_SLOTS;

    public SackMenu(int containerId, Inventory playerInventory, FriendlyByteBuf additionalData)
    {
        this(containerId, playerInventory, playerInventory.player.level().getBlockEntity(additionalData.readBlockPos()));
    }

    public SackMenu(int containerId, Inventory playerInventory, BlockEntity blockEntity)
    {
        super(AllMenus.SACK.get(), containerId);

        checkContainerSize(playerInventory, SackBlockEntity.CONTAINER_SIZE);
        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        addPlayerInventorySlots(playerInventory, 8, 51);

        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent((itemHandler) ->
        {
            for (int column = 0; column < SackBlockEntity.COLUMNS; column++)
            {
                addSlot(new SackSlot(itemHandler, column, 8 + (18 * column), 20));
            }
        });

        if (blockEntity instanceof SackBlockEntity sackBlockEntity)
        {
            this.container = sackBlockEntity;
            this.container.startOpen(playerInventory.player);
        }
        else
            throw new IllegalStateException("%s: Invalid block entity! Expected block entity of type %s but received %s".formatted(SackMenu.class.getCanonicalName(), SackBlockEntity.class.getCanonicalName(), BlockEntity.class.getCanonicalName()));
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index)
    {
        Slot fromSlot = getSlot(index);
        ItemStack fromStack = fromSlot.getItem();
        if (fromStack.getCount() <= 0)
            fromSlot.set(ItemStack.EMPTY);

        if (!fromSlot.hasItem())
            return ItemStack.EMPTY;

        ItemStack stackCopy = fromStack.copy();
        if (index < PLAYER_INVENTORY_END_INDEX)
        {
            if (!moveItemStackTo(fromStack, BLOCK_INVENTORY_START_INDEX, BLOCK_INVENTORY_END_INDEX, false))
                return ItemStack.EMPTY;
        }
        else if (index <= BLOCK_INVENTORY_END_INDEX)
        {
            if (!moveItemStackTo(fromStack, PLAYER_INVENTORY_START_INDEX, PLAYER_INVENTORY_END_INDEX, false))
                return ItemStack.EMPTY;
        }
        else
            throw new IllegalStateException(NotEnoughMachines.MOD_ID + ".%s:slot index is out of bounds".formatted(SackMenu.class.getCanonicalName()));

        fromSlot.setChanged();
        fromSlot.onTake(player, fromStack);
        return stackCopy;
    }

    @Override
    public boolean stillValid(@NotNull Player player)
    {
        if (stillValid(this.levelAccess, player, AllBlocks.SACK.get()))
            return true;

        for (DyeColor color : DyeColor.values())
        {
            Block sackBlock = SackBlock.getBlockByColor(color);
            if (stillValid(this.levelAccess, player, sackBlock))
                return true;
        }
        return false;
    }

    @Override
    public void removed(@NotNull Player player)
    {
        super.removed(player);
        this.container.stopOpen(player);
    }

    public SackBlockEntity getContainer() { return this.container; }

    /**
     * Adds the player's inventory and quick bar slots to this container
     * @param slotX The X coordinate of the top-left inventory slot inside corner in the player's inventory
     * @param slotY The Y coordinate of the top-left inventory slot inside corner in the player's inventory
     */
    protected void addPlayerInventorySlots(Inventory playerInv, int slotX, int slotY)
    {
        //Player's hot bar
        int index = 0;
        for (int colum = 0; colum < 9; colum++)
        {
            addSlot(new Slot(playerInv, index, slotX + (colum * 18), slotY + 58));
            index++;
        }

        //Player's inventory
        for (int row = 0; row < 3; row++)
        {
            for (int colum = 0; colum < 9; colum++)
            {
                addSlot(new Slot(playerInv, index, slotX + (colum * 18), slotY + (row * 18)));
                index++;
            }
        }
    }
}
