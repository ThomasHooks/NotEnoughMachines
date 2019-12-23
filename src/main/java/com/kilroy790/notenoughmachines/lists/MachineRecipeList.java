package com.kilroy790.notenoughmachines.lists;

import com.kilroy790.notenoughmachines.api.crafting.MillingRecipe;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.registries.ObjectHolder;

public class MachineRecipeList {

	@ObjectHolder("notenoughtmachines:milling")
	public static IRecipeSerializer<MillingRecipe> MILLING;
	
	//public static final IRecipeSerializer<MillingRecipe> MILLING = new MachineRecipeSerializer<>(MillingRecipe::new, 200);
	
}
