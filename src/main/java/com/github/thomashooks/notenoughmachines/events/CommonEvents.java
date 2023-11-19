package com.github.thomashooks.notenoughmachines.events;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CommonEvents
{
    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event)
    {
        LevelAccessor world = event.getLevel();
        NotEnoughMachines.AETHER.onWorldLoad(world);
    }

    @SubscribeEvent
    public static void onWorldUnload(LevelEvent.Unload event)
    {
        LevelAccessor world = event.getLevel();
        NotEnoughMachines.AETHER.onWorldUnload(world);
    }
}
