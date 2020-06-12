package com.kilroy790.notenoughmachines.setup;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;




public class NEMContainers {

	private static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, NotEnoughMachines.MODID);
	
	//public static final RegistryObject<ContainerType<MillstoneContainer>> MILLSTONE = CONTAINERS.register("millstone", () -> IForgeContainerType.create(MillstoneContainer::new));
	
	
	
	public static void registerAll(IEventBus modEventBus) {
		CONTAINERS.register(modEventBus);
	}
}







