package com.kilroy790.notenoughmachines.api.lists;

import com.kilroy790.notenoughmachines.containers.MillstoneContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;

public class ContainerList {

	@ObjectHolder("notenoughtmachines:millstone")
	public static ContainerType<MillstoneContainer> MILLSTONE_CONTAINER;
}
