package com.github.thomashooks.notenoughmachines.world.inventory;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllMenus
{
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, NotEnoughMachines.MOD_ID);

    public static final RegistryObject<MenuType<FilterMenu>> FILTER = MENUS.register("filter", ()-> IForgeMenuType.create(((windowId, inv, data) -> new FilterMenu(windowId, inv, data))));

    public static void registerAll(IEventBus modEventBus)
    {
        MENUS.register(modEventBus);
    }
}
