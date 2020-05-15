package com.kilroy790.notenoughmachines;

import net.minecraft.world.IWorld;
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
}
