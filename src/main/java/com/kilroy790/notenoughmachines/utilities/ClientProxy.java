package com.kilroy790.notenoughmachines.utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;




public class ClientProxy implements IProxy{

	private static long tick = 0;
	
	@Override
	public void init() {}
	
	
	
	@Override
	public World getClientWorld() {
		return Minecraft.getInstance().world;
	}


	
	@Override
	public PlayerEntity getClientPlayer() {
		return Minecraft.getInstance().player;
	}


	
	@Override
	public float getClientTick() {
		return (float)tick + Minecraft.getInstance().getRenderPartialTicks();
	}

	

	@Override
	public void tickClient() {
		tick++;
	}
}







