package com.kilroy790.notenoughmachines.utilities;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




@OnlyIn(Dist.CLIENT)
public class ClientTimer {

	private static long tick = 0;
	
	
	public long getTick() {
		return tick;
	}

	

	public void tickClient() {
		tick++;
	}
}







