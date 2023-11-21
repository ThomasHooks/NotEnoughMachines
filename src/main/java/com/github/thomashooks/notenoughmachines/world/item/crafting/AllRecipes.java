package com.github.thomashooks.notenoughmachines.world.item.crafting;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllRecipes
{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, NotEnoughMachines.MOD_ID);

    public static final RegistryObject<RecipeSerializer<MillingRecipe>> MILLING = SERIALIZERS.register("milling", ()-> MillingRecipe.Serializer.MILLING);

    public static void registerAll(IEventBus eventBus) { SERIALIZERS.register(eventBus); }
}
