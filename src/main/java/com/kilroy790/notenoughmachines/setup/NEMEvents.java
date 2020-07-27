package com.kilroy790.notenoughmachines.setup;

import com.kilroy790.notenoughmachines.NotEnoughMachines;

import net.minecraft.world.IWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;




@EventBusSubscriber
public class NEMEvents {

	@SubscribeEvent
	public static void onWorldLoad(WorldEvent.Load event) {
		IWorld world = event.getWorld();
		NotEnoughMachines.AETHER.onWorldLoad(world);
	}
	
	
	
	@SubscribeEvent
	public static void onWorldUnload(WorldEvent.Unload event) {
		IWorld world = event.getWorld();
		NotEnoughMachines.AETHER.onWorldUnload(world);
	}
	
	
	
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onClientTick(ClientTickEvent event) {
		
		if(event.phase == Phase.START) return;
		
//		NotEnoughMachines.proxy.tickClient();
		NotEnoughMachines.CLIENTTIMER.tickClient();
	}
}
