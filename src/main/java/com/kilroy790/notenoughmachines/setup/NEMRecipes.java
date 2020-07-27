package com.kilroy790.notenoughmachines.setup;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.recipes.MillingRecipe;
import com.kilroy790.notenoughmachines.recipes.StampingRecipe;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;




public class NEMRecipes {

	public static class Types {
		public static final IRecipeType<StampingRecipe> STAMPING = IRecipeType.register(NotEnoughMachines.MODID + ":stamping");
		public static final IRecipeType<MillingRecipe> MILLING = IRecipeType.register(NotEnoughMachines.MODID + ":milling");
	}
	
	public static final DeferredRegister<IRecipeSerializer<?>> SERIALIZERS = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, NotEnoughMachines.MODID);
	
	public static final RegistryObject<IRecipeSerializer<StampingRecipe>> STAMPING = SERIALIZERS.register("stamping", ()-> ( new StampingRecipe.Serializer() ));
	
	
	
	public static void registerAll(IEventBus modEventBus) {
		SERIALIZERS.register(modEventBus);
	}
}







