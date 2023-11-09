package com.kilroy790.notenoughmachines.containers;

import com.kilroy790.notenoughmachines.NotEnoughMachines;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;




public class NEMContainers 
{
	private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, NotEnoughMachines.MODID);
	
	
	
	public static final RegistryObject<ContainerType<MillstoneContainer>> MILLSTONE = CONTAINERS.register("millstone", 
			() -> IForgeContainerType.create( (id, inventory, data) -> { return new MillstoneContainer(id, inventory, data); } ));
	
	
	
	public static final RegistryObject<ContainerType<TripHammerContainer>> TRIPHAMMER = CONTAINERS.register("triphammer", 
			() -> IForgeContainerType.create( (id, inventory, data) -> { return new TripHammerContainer(id, inventory, data); } ));
	
	
	
	public static final RegistryObject<ContainerType<FilterContainer>> FILTER = CONTAINERS.register("filter", 
			() -> IForgeContainerType.create( (id, inventory, data) -> { return new FilterContainer(id, inventory, data); } ));
	
	
	
	public static final RegistryObject<ContainerType<RollingMillContainer>> ROLLING_MILL = CONTAINERS.register("rollingmill", 
			() -> IForgeContainerType.create( (id, inventory, data) -> { return new RollingMillContainer(id, inventory, data); } ));



	public static void registerAll(IEventBus modEventBus) 
	{
		CONTAINERS.register(modEventBus);
	}
}



