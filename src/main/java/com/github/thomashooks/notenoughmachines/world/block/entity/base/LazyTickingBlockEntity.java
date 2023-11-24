package com.github.thomashooks.notenoughmachines.world.block.entity.base;

import com.github.thomashooks.notenoughmachines.world.block.entity.FilterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public abstract class LazyTickingBlockEntity extends SyncingBlockEntity
{
    private int lazyTimerAlarm = 0;
    private int lazyTimer = 0;
    private int extraLazyTimerAlarm = 0;
    private int extraLazyTimer = 0;

    protected LazyTickingBlockEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state)
    {
        super(entityType, pos, state);

        setLazyTimerAlarm(40);
        setExtraLazyTimerAlarm(80);
    }

    /**
     * This gets called by both the server and all clients approximately 20 times every second.
     * <p>
     * If overridden the super must be called.
     */
    public void tick()
    {
        if (level == null || level.isClientSide())
            return;
        if (pollLazyTimer())
            lazyTick();
        if (pollExtraLazyTimer())
            extraLazyTick();
    }

    /**
     * This gets called by both the server and all clients at a rate controlled by BaseBlockEntity::setLazyTimerAlarm()
     */
    protected void lazyTick()
    {
        //Do nothing
    }

    protected boolean pollLazyTimer()
    {
        lazyTimer++;
        if (lazyTimer >= lazyTimerAlarm)
        {
            lazyTimer = 0;
            return true;
        }
        return false;
    }

    /**
     * Sets this block entity's lazy tick rate
     * @param tickRate The new lazy tick rate
     */
    protected void setLazyTimerAlarm(int tickRate)
    {
        this.lazyTimerAlarm = Math.abs(tickRate);
    }

    protected int getLazyTimerAlarm()
    {
        return this.lazyTimerAlarm;
    }

    /**
     * This gets called by both the server and all clients at a rate controlled by BaseBlockEntity::setExtraLazyTimerAlarm()
     */
    protected void extraLazyTick()
    {
        //Do nothing
    }

    protected boolean pollExtraLazyTimer()
    {
        extraLazyTimer++;
        if (extraLazyTimer >= extraLazyTimerAlarm)
        {
            extraLazyTimer = 0;
            return true;
        }
        return false;
    }

    /**
     * Sets this block entity's extra lazy tick rate
     * @param tickRate The new lazy tick rate
     */
    protected void setExtraLazyTimerAlarm(int tickRate)
    {
        this.extraLazyTimerAlarm = Math.abs(tickRate);
    }

    protected int getExtraLazyTimerAlarm()
    {
        return this.extraLazyTimerAlarm;
    }

    /**
     * Creates a new Item Stack Handler with the given parameters
     * @param numberOfSlots The number of slots this Item Stack Handler has
     * @param slotLimit The Item Stack limit for each Item slot
     * @return A new Item Stack Handler with the given parameters
     */
    protected ItemStackHandler makeItemHandler(int numberOfSlots, int slotLimit)
    {
        return new ItemStackHandler(numberOfSlots)
        {
            @Override
            public int getSlotLimit(int slot)
            {
                int limit;
                if (Math.abs(slotLimit) < 1)
                    limit = 1;
                else
                    limit = Math.min(Math.abs(slotLimit), 64);
                return limit;
            }

            @Override
            protected void onContentsChanged(int slot)
            {
                setChanged();
            }
        };
    }

    /**
     * Helper method used to hook LazyTickingBlockEntity::tick() in blocks
     */
    public static <T extends BlockEntity>BlockEntityTicker<T> getTicker(Level world)
    {
        return world.isClientSide() ? null : (level, pos, blockState, blockEntity) -> ((LazyTickingBlockEntity) blockEntity).tick();
    }
}
