package com.kilroy790.notenoughmachines.integration.jei.categories;

import com.kilroy790.notenoughmachines.NotEnoughMachines;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;




public abstract class NEMRecipeCategory<T extends IRecipe<?>> implements IRecipeCategory<T> 
{
	private ResourceLocation UID;
	private String name;
	private final IDrawable backgorund;
	private final IDrawable icon;
	
	NEMRecipeCategory(ResourceLocation uidIn, IDrawable backgorundIn, IDrawable iconIn)
	{
		UID = uidIn;
		this.name = uidIn.getPath();
		
		this.backgorund = backgorundIn;
		this.icon = iconIn;
	}
	
	
	
	@Override
	public ResourceLocation getUid() 
	{
		return UID;
	}
	
	
	
	@Override
	abstract public Class<? extends T> getRecipeClass();
	
	
	
	@Override
	public String getTitle() 
	{
		return new TranslationTextComponent("jei.gui.category." + NotEnoughMachines.MODID + "." + name).getString();
	}
	
	
	
	@Override
	public IDrawable getBackground() 
	{
		return this.backgorund;
	}
	
	
	
	@Override
	public IDrawable getIcon() 
	{
		return this.icon;
	}
}



