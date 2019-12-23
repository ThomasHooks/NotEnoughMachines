package com.kilroy790.notenoughmachines.lists;

import com.kilroy790.notenoughmachines.gui.MillstoneContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;

public class ContainerList {

	@ObjectHolder("notenoughtmachines:millstone")
	public static ContainerType<MillstoneContainer> MILLSTONE_CONTAINER;
}
