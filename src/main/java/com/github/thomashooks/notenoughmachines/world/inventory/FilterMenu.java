package com.github.thomashooks.notenoughmachines.world.inventory;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.AllBlocks;
import com.github.thomashooks.notenoughmachines.world.block.entity.FilterBlockEntity;
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

public class FilterMenu extends AbstractContainerMenu
{
    public static final Component TITLE = Component.translatable("container." + NotEnoughMachines.MOD_ID + ".filter");
    private final FilterBlockEntity blockEntity;
    private final Level world;
    private final Inventory playerInventory;
    private final ContainerLevelAccess levelAccess;
    private static final int PLAYER_INVENTORY_START_INDEX = 0;
    private static final int PLAYER_INVENTORY_END_INDEX = 36;
    private static final int FILTER_SLOT_END_INDEX = 65;
    private static final int FILTER_BUFFER_SLOT_INDEX = PLAYER_INVENTORY_START_INDEX + PLAYER_INVENTORY_END_INDEX;

    public FilterMenu(int containerId, Inventory playerInventory, FriendlyByteBuf additionalData)
    {
        this(containerId, playerInventory, playerInventory.player.level().getBlockEntity(additionalData.readBlockPos()));
    }

    public FilterMenu(int containerId, Inventory playerInventory, BlockEntity entity)
    {
        super(AllMenus.FILTER.get(), containerId);
        if (entity instanceof FilterBlockEntity filterEntity)
            this.blockEntity = filterEntity;
        else
            throw new IllegalStateException("FilterMenu: Invalid block entity! Expected block entity of type FilterBlockEntity but received %s".formatted(BlockEntity.class.getCanonicalName()));
        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        this.playerInventory = playerInventory;
        this.world = playerInventory.player.level();

        addPlayerInventorySlots(playerInventory, 7, 105);
        addFilterInventorySlots();
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
            if (!moveItemStackTo(fromStack, FILTER_BUFFER_SLOT_INDEX, 37, false))
                return ItemStack.EMPTY;
        }
        else if (index < FILTER_SLOT_END_INDEX)
        {
            if (!moveItemStackTo(fromStack, PLAYER_INVENTORY_START_INDEX, PLAYER_INVENTORY_END_INDEX, false))
                return ItemStack.EMPTY;
        }
        else
            throw new IllegalStateException(NotEnoughMachines.MOD_ID + ":FilterMenu slot index is out of bounds");

        fromSlot.setChanged();
        fromSlot.onTake(player, fromStack);
        return stackCopy;
    }

    @Override
    public boolean stillValid(Player player)
    {
        return stillValid(this.levelAccess, player, AllBlocks.FILTER.get());
    }

    public FilterBlockEntity getBlockEntity() { return blockEntity; }

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

   protected void addFilterInventorySlots()
   {
       getBlockEntity().getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent( itemHandler ->
       {
           //Inventory buffer slot
           int index = 0;
           addSlot(new SlotItemHandler(itemHandler, index, 15, 47));
           index++;

           //Filters slots
           int slotX = 43;
           int slotY = 20;
           for (int row = 0; row < 4; row++)
           {
               for (int colum = 0; colum < 7; colum++)
               {
                   addSlot(new SlotItemHandler(itemHandler, index, slotX + (colum * 18), slotY + (row * 18)));
                   index++;
               }
           }
       });
   }
}
