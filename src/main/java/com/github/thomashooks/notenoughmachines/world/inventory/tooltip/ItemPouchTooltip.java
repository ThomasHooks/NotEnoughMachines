package com.github.thomashooks.notenoughmachines.world.inventory.tooltip;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public class ItemPouchTooltip implements TooltipComponent
{
    private final NonNullList<ItemStack> items;
    private final int slot;
    private final int weight;

    public ItemPouchTooltip(NonNullList<ItemStack> items, int slot, int weight)
    {
        this.items = items;
        this.slot = slot;
        this.weight = weight;
    }

    public NonNullList<ItemStack> getItems() { return this.items; }

    public int getSlot() { return this.slot; }

    public int getWeight() { return this.weight; }
}
