package com.kilroy790.notenoughmachines.integration.jei;

import java.util.List;
import java.util.stream.Collectors;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.NEMBlocks;
import com.kilroy790.notenoughmachines.integration.jei.categories.MillingRecipeCategory;
import com.kilroy790.notenoughmachines.integration.jei.categories.RollingRecipeCategory;
import com.kilroy790.notenoughmachines.integration.jei.categories.StampingRecipeCategory;
import com.kilroy790.notenoughmachines.recipes.NEMRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;




@JeiPlugin
public class NEMPlugin implements IModPlugin 
{
	private static final ResourceLocation UID = new ResourceLocation(NotEnoughMachines.MODID, "jei_plugin");
	
	public static final ResourceLocation STAMPING_CATEGORY_UID = new ResourceLocation(NotEnoughMachines.MODID, "stamping");
	public static final ResourceLocation MILLING_CATEGORY_UID = new ResourceLocation(NotEnoughMachines.MODID, "milling");
	public static final ResourceLocation ROLLING_CATEGORY_UID = new ResourceLocation(NotEnoughMachines.MODID, "rolling");
	
	@Override
	public ResourceLocation getPluginUid() 
	{
		return UID;
	}
	
	
	
	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) 
	{
		registration.addRecipeCatalyst(new ItemStack(NEMBlocks.TRIPHAMMER.get()), STAMPING_CATEGORY_UID);
		registration.addRecipeCatalyst(new ItemStack(NEMBlocks.MILLSTONE.get()), MILLING_CATEGORY_UID);
		registration.addRecipeCatalyst(new ItemStack(NEMBlocks.ROLLING_MILL.get()), ROLLING_CATEGORY_UID);
	}
	
	
	
	@SuppressWarnings("resource")
	@Override
	public void registerRecipes(IRecipeRegistration registration) 
	{
		List<IRecipe<?>> stampingRecipes = Minecraft.getInstance().world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType().equals(NEMRecipes.Types.STAMPING)).collect(Collectors.toList());
		registration.addRecipes(stampingRecipes, STAMPING_CATEGORY_UID);
		
		List<IRecipe<?>> millingRecipes = Minecraft.getInstance().world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType().equals(NEMRecipes.Types.MILLING)).collect(Collectors.toList());
		registration.addRecipes(millingRecipes, MILLING_CATEGORY_UID);
		
		List<IRecipe<?>> rollingRecipes = Minecraft.getInstance().world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType().equals(NEMRecipes.Types.ROLLING)).collect(Collectors.toList());
		registration.addRecipes(rollingRecipes, ROLLING_CATEGORY_UID);
	}
	
	
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) 
	{
		registration.addRecipeCategories(new StampingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new MillingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new RollingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}
}



