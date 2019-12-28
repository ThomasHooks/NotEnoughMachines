package com.kilroy790.notenoughmachines.api.crafting;

import com.kilroy790.notenoughmachines.lists.BlockList;
import com.kilroy790.notenoughmachines.lists.MachineRecipeList;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class MillingRecipe extends AbstractMachineRecipe{

	public MillingRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn,
			ItemStack resultIn, float experienceIn, int processTimeIn) {
		
		super(milling, idIn, groupIn, ingredientIn, resultIn, experienceIn, processTimeIn);
	}
		
	
	
	public static final IRecipeType<MillingRecipe> milling = IRecipeType.register("milling");
	
		
	
	public ItemStack getIcon() {
		
		return new ItemStack(BlockList.MILLSTONE);
	}
	

	@Override
	public IRecipeSerializer<?> getSerializer() {
		
		return IRecipeSerializer.register("milling", MachineRecipeList.MILLING);
	}
}
