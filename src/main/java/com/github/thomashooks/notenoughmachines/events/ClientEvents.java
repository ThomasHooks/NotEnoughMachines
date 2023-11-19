package com.github.thomashooks.notenoughmachines.events;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NotEnoughMachines.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents
{
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onTick(ClientTickEvent event)
    {
        if (event.phase == Phase.START)
            NotEnoughMachines.CLIENT_TIMER.tickClient();
    }

    @SubscribeEvent
    public static void onWorldLoad(LevelEvent.Load event)
    {
        LevelAccessor world = event.getLevel();
        if (!world.isClientSide())
            return;
    }

    @SubscribeEvent
    public static void onWorldUnload(LevelEvent.Unload event)
    {
        LevelAccessor world = event.getLevel();
        if (!world.isClientSide())
            return;
    }
}
