package com.github.thomashooks.notenoughmachines.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class KeyboardInputHelper
{
    private static KeyboardInputHelper instance;
    private static final long GLFW_WINDOW = Minecraft.getInstance().getWindow().getWindow();
    private boolean holdingKeyW = false;
    private boolean holdingKeyS = false;

    private KeyboardInputHelper() { instance = this; }

    public static KeyboardInputHelper getInstance()
    {
        if (instance == null)
            instance = new KeyboardInputHelper();
        return instance;
    }

    public boolean isPressingShift()
    {
        return InputConstants.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputConstants.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    public boolean isPressingCtrl()
    {
        return InputConstants.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_LEFT_CONTROL) || InputConstants.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_RIGHT_CONTROL);
    }

    public boolean pressedKeyW()
    {
        if (!this.holdingKeyW && InputConstants.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_W))
        {
            this.holdingKeyW = true;
            return true;
        }
        else if (this.holdingKeyW && !InputConstants.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_W))
            this.holdingKeyW = false;

        return false;
    }

    public boolean pressedKeyS()
    {
        if (!this.holdingKeyS && InputConstants.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_S))
        {
            this.holdingKeyS = true;
            return true;
        }
        else if (this.holdingKeyS && !InputConstants.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_S))
            this.holdingKeyS = false;

        return false;
    }
}
