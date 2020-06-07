package com.kilroy790.notenoughmachines.setup;

import com.kilroy790.notenoughmachines.containers.FilterContainer;
import com.kilroy790.notenoughmachines.containers.MillstoneContainer;
import com.kilroy790.notenoughmachines.containers.TripHammerContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;




public class ContainerList {

	@ObjectHolder("notenoughtmachines:millstone")
	public static ContainerType<MillstoneContainer> MILLSTONE_CONTAINER;
	
	@ObjectHolder("notenoughtmachines:triphammer")
	public static ContainerType<TripHammerContainer> TRIPHAMMER;
	
	@ObjectHolder("notenoughtmachines:filter")
	public static ContainerType<FilterContainer> FILTER_CONTAINER;
}
