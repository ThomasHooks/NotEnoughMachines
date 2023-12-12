package com.github.thomashooks.notenoughmachines.common.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class KeyboardInputHelper
{
    private static final long GLFW_WINDOW = Minecraft.getInstance().getWindow().getWindow();
    public static final String MORE_INFO_PRESS_SHIFT = "\u00A77" + "Press " + "\u00A72" + "SHIFT" + "\u00A77" + " for more information";

    public static boolean isPressingShift()
    {
        return InputConstants.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputConstants.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT);
    }
}
