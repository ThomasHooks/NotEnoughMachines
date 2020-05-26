package com.kilroy790.notenoughmachines.utilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;




public class ServerProxy implements IProxy{

	@Override
	public void init() {}
	
	
	
	@Override
	public World getClientWorld() {
		throw new IllegalStateException("This can only run on the Client!");
	}

	

	@Override
	public PlayerEntity getClientPlayer() {
		throw new IllegalStateException("This can only run on the Client!");
	}



	@Override
	public float getClientTick() {
		throw new IllegalStateException("This can only run on the Client!");
	}



	@Override
	public void tickClient() {
		throw new IllegalStateException("This can only run on the Client!");
	}
}







