package com.kilroy790.notenoughmachines.api.lists;

import com.kilroy790.notenoughmachines.recipes.MillingRecipe;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.registries.ObjectHolder;




public class MachineRecipeList {

	@ObjectHolder("notenoughtmachines:milling")
	public static IRecipeSerializer<MillingRecipe> MILLING;
}
