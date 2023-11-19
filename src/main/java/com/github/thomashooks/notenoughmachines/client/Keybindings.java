package com.github.thomashooks.notenoughmachines.client;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public class Keybindings
{
    public static final Keybindings INSTANCE = new Keybindings();
    public final KeyMapping RSHIFT_GUI = new KeyMapping("key." + NotEnoughMachines.MOD_ID + ".rshift_gui",
            KeyConflictContext.GUI,
            InputConstants.getKey(InputConstants.KEY_RSHIFT, 0X36), "key.categories." + NotEnoughMachines.MOD_ID);

    private Keybindings() { }
}
