package com.kilroy790.notenoughmachines.setup;

import com.kilroy790.notenoughmachines.gui.MillstoneScreen;
import com.kilroy790.notenoughmachines.lists.ContainerList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy{

	@Override
	public void init() {
		
		ScreenManager.registerFactory(ContainerList.MILLSTONE_CONTAINER, MillstoneScreen::new);
	}
	
	
	@Override
	public World getClientWorld() {
		
		return Minecraft.getInstance().world;
	}


	@Override
	public PlayerEntity getClientPlayer() {
		
		return Minecraft.getInstance().player;
	}
}
