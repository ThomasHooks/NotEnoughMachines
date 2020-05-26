package com.kilroy790.notenoughmachines.utilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface IProxy {

	void init();
	
	World getClientWorld();
	
	PlayerEntity getClientPlayer();
	
	float getClientTick();
	
	void tickClient();
}
