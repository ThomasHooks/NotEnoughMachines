package com.github.thomashooks.notenoughmachines.common.util;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class KeyboardInputHelper
{
    private static final long GLFW_WINDOW = Minecraft.getInstance().getWindow().getWindow();

    public static boolean isPressingShift()
    {
        return InputConstants.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputConstants.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    public static boolean isPressingCtrl()
    {
        return InputConstants.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_LEFT_CONTROL) || InputConstants.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_RIGHT_CONTROL);
    }
}
