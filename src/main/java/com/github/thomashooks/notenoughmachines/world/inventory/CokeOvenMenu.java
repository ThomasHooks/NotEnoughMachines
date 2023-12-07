package com.github.thomashooks.notenoughmachines.world.inventory;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.block.CokeOvenBlock;
import com.github.thomashooks.notenoughmachines.world.block.entity.CokeOvenBlockEntity;
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

public class CokeOvenMenu extends AbstractContainerMenu
{
    public static final Component TITLE = Component.translatable("container." + NotEnoughMachines.MOD_ID + "." + AllBlocks.getName(AllBlocks.COKE_OVEN.get()));
    private final CokeOvenBlockEntity blockEntity;
    private final Level world;
    private final Inventory playerInventory;
    private final ContainerData data;
    private final ContainerLevelAccess levelAccess;
    private static final int PLAYER_INVENTORY_START_INDEX = 0;
    private static final int PLAYER_INVENTORY_END_INDEX = 36;
    private static final int MACHINE_INVENTORY_START_INDEX = PLAYER_INVENTORY_START_INDEX + PLAYER_INVENTORY_END_INDEX;
    private static final int NUMBER_OF_MACHINE_SLOTS = 2;
    private static final int MACHINE_INVENTORY_END_INDEX = MACHINE_INVENTORY_START_INDEX + NUMBER_OF_MACHINE_SLOTS;

    public CokeOvenMenu(int containerId, Inventory playerInventory, FriendlyByteBuf additionalData)
    {
        this(containerId, playerInventory, playerInventory.player.level().getBlockEntity(additionalData.readBlockPos()), new SimpleContainerData(2));
    }

    public CokeOvenMenu(int containerId, Inventory playerInventory, BlockEntity entity, ContainerData data)
    {
        super(AllMenus.COKE_OVEN.get(), containerId);
        if (entity instanceof CokeOvenBlockEntity cokeOvenBlockEntity)
            this.blockEntity = cokeOvenBlockEntity;
        else
            throw new IllegalStateException("CokeOvenMenu: Invalid block entity! Expected block entity of type CokeOvenBlockEntity but received %s".formatted(BlockEntity.class.getCanonicalName()));
        checkContainerSize(playerInventory, 2);
        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        this.playerInventory = playerInventory;
        this.world = playerInventory.player.level();
        this.data = data;

        addPlayerInventorySlots(playerInventory, 7, 48);

        cokeOvenBlockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemHandler ->
        {
            //Input slot
            addSlot(new SlotItemHandler(itemHandler, 0, 55, 17));

            //Output slot
            addSlot(new SlotItemHandler(itemHandler, 1, 114, 17));
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
            throw new IllegalStateException(NotEnoughMachines.MOD_ID + ":CokeOvenMenu slot index is out of bounds");

        fromSlot.setChanged();
        fromSlot.onTake(player, fromStack);
        return stackCopy;
    }

    @Override
    public boolean stillValid(Player player) { return stillValid(this.levelAccess, player, AllBlocks.COKE_OVEN.get()); }

    public float getProgressBar()
    {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressBarSize = 14;
        return maxProgress != 0 && progress != 0 ? (float) (progress * progressBarSize) / (float) maxProgress : 0.0F;
    }

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
