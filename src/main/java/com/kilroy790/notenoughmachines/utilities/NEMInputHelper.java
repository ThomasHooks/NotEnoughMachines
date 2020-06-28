package com.kilroy790.notenoughmachines.utilities;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@OnlyIn(Dist.CLIENT)
public class NEMInputHelper {

	private static final long GLFW_WINDOW = Minecraft.getInstance().getMainWindow().getHandle();
	
	public static final String MORE_INFO_PRESS_SHIFT = "\u00A77" + "Press " + "\u00A72" + "SHIFT" + "\u00A77" + " for more information";
	
	
	
	/**
	 * @return True if either shift key is being pressed
	 */
	public static boolean isPressingShift() {
		return InputMappings.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) || InputMappings.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT);
	}
	
	
	
	/**
	 * @return True if either control key is being pressed
	 */
	public static boolean isPressingControl() {
		return InputMappings.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_LEFT_CONTROL) || InputMappings.isKeyDown(GLFW_WINDOW, GLFW.GLFW_KEY_RIGHT_CONTROL);
	}
}







