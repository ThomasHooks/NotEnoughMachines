package com.kilroy790.notenoughmachines.setup;

import com.kilroy790.notenoughmachines.recipes.MillingRecipe;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.registries.ObjectHolder;




public class NEMMachineRecipes {

	@ObjectHolder("notenoughtmachines:milling")
	public static IRecipeSerializer<MillingRecipe> MILLING;
}
