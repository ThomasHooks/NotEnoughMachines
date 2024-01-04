package com.github.thomashooks.notenoughmachines.world.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

public class FuelBlockItem extends BlockItem
{
    private int burnTime;

    public FuelBlockItem(Block block, int burnTimeIn, Properties properties)
    {
        super(block, properties);
        this.burnTime = burnTimeIn;
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) { return this.burnTime; }
}
