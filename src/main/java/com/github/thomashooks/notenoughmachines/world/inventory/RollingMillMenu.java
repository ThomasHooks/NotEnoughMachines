package com.github.thomashooks.notenoughmachines.world.inventory;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.block.entity.MillstoneBlockEntity;
import com.github.thomashooks.notenoughmachines.world.block.entity.RollingMillBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public class RollingMillMenu extends AbstractContainerMenu
{
    public static final Component TITLE = Component.translatable("container." + NotEnoughMachines.MOD_ID + ".rolling_mill");
    private final RollingMillBlockEntity blockEntity;
    private final Level world;
    private final Inventory playerInventory;
    private final ContainerData data;
    private final ContainerLevelAccess levelAccess;
    private static final int PLAYER_INVENTORY_START_INDEX = 0;
    private static final int PLAYER_INVENTORY_END_INDEX = 36;
    private static final int MACHINE_INVENTORY_START_INDEX = PLAYER_INVENTORY_START_INDEX + PLAYER_INVENTORY_END_INDEX;
    private static final int NUMBER_OF_MACHINE_SLOTS = 2;
    private static final int MACHINE_INVENTORY_END_INDEX = MACHINE_INVENTORY_START_INDEX + NUMBER_OF_MACHINE_SLOTS;

    public RollingMillMenu(int containerId, Inventory playerInventory, FriendlyByteBuf additionalData)
    {
        this(containerId, playerInventory, playerInventory.player.level().getBlockEntity(additionalData.readBlockPos()), new SimpleContainerData(2));
    }

    public RollingMillMenu(int containerId, Inventory playerInventory, BlockEntity entity, ContainerData data)
    {
        super(AllMenus.ROLLING_MILL.get(), containerId);
        if (entity instanceof RollingMillBlockEntity rollingMillEntity)
            this.blockEntity = rollingMillEntity;
        else
            throw new IllegalStateException("RollingMillMenu: Invalid block entity! Expected block entity of type RollingMillBlockEntity but received %s".formatted(BlockEntity.class.getCanonicalName()));
        checkContainerSize(playerInventory, 2);
        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        this.playerInventory = playerInventory;
        this.world = playerInventory.player.level();
        this.data = data;

        addPlayerInventorySlots(playerInventory, 7, 48);

        rollingMillEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemHandler ->
        {
            //Input slot
            addSlot(new SlotItemHandler(itemHandler, 0, 61, 17));

            //Output slot
            addSlot(new SlotItemHandler(itemHandler, 1, 99, 17));
        });

        addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index)
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
            if (!moveItemStackTo(fromStack, MACHINE_INVENTORY_START_INDEX, MACHINE_INVENTORY_END_INDEX - 1, false))
                return ItemStack.EMPTY;
        }
        else if (index < MACHINE_INVENTORY_END_INDEX)
        {
            if (!moveItemStackTo(fromStack, PLAYER_INVENTORY_START_INDEX, PLAYER_INVENTORY_END_INDEX, false))
                return ItemStack.EMPTY;
        }
        else
            throw new IllegalStateException(NotEnoughMachines.MOD_ID + ":RollingMillMenu slot index is out of bounds");

        fromSlot.setChanged();
        fromSlot.onTake(player, fromStack);
        return stackCopy;
    }

    @Override
    public boolean stillValid(Player player) { return stillValid(this.levelAccess, player, AllBlocks.ROLLING_MILL.get()); }

    public int getProgressBar()
    {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressBarSize = 16;
        return maxProgress != 0 && progress != 0 ? progress * progressBarSize / maxProgress : 0;
    }

    public RollingMillBlockEntity getBlockEntity() { return blockEntity; }

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
