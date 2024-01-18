package com.github.thomashooks.notenoughmachines.world.item;

import com.github.thomashooks.notenoughmachines.world.block.SackBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class SackItem extends BlockItem
{
    public SackItem(Block block, Properties properties) { super(block, properties); }

    @Override
    public boolean canFitInsideContainerItems() { return !(this.getBlock() instanceof SackBlock); }
}
