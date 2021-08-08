package com.kilroy790.notenoughmachines.containers;

import com.kilroy790.notenoughmachines.NotEnoughMachines;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;




public class NEMContainers 
{
	private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, NotEnoughMachines.MODID);

	public static final RegistryObject<ContainerType<MillstoneContainer>> MILLSTONE = CONTAINERS.register("millstone", 
			() -> IForgeContainerType.create(
					(id, inventory, data) -> 
					{
						BlockPos pos = data.readBlockPos();
						return new MillstoneContainer(id, Minecraft.getInstance().world, pos, inventory, Minecraft.getInstance().player);
					}));

	public static final RegistryObject<ContainerType<TripHammerContainer>> TRIPHAMMER = CONTAINERS.register("triphammer", 
			() -> IForgeContainerType.create(
					(id, inventory, data) -> 
					{
						BlockPos pos = data.readBlockPos();
						return new TripHammerContainer(id, Minecraft.getInstance().world, pos, inventory, Minecraft.getInstance().player); //NotEnoughMachines.proxy.getClientWorld() NotEnoughMachines.proxy.getClientPlayer()
					}));
	
	public static final RegistryObject<ContainerType<FilterContainer>> FILTER = CONTAINERS.register("filter", 
			() -> IForgeContainerType.create( (id, inventory, data) -> { return new FilterContainer(id, inventory, data); } ));



	public static void registerAll(IEventBus modEventBus) 
	{
		CONTAINERS.register(modEventBus);
	}
}







