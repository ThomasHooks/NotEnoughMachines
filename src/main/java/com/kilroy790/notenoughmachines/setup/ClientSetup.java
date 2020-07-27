package com.kilroy790.notenoughmachines.setup;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.client.renderers.NEMTextures;

import net.minecraft.inventory.container.PlayerContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;




@Mod.EventBusSubscriber(modid = NotEnoughMachines.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

	@SubscribeEvent
	public static void onTextureStitch(TextureStitchEvent.Pre event) {
		
		if(event.getMap().getTextureLocation().equals(PlayerContainer.LOCATION_BLOCKS_TEXTURE)) {
			NotEnoughMachines.LOGGER.debug("Stitching NEM textures to Atlas");
			event.addSprite(NEMTextures.AXLE);
			event.addSprite(NEMTextures.RUNNERSTONE);
			event.addSprite(NEMTextures.TUBWHEEL_HUB);
			event.addSprite(NEMTextures.TUBWHEEL_PADDLE);
			event.addSprite(NEMTextures.WINDSAIL_STOCK);
			event.addSprite(NEMTextures.WINDSAIL_SAIL);
			event.addSprite(NEMTextures.SMALLCOG_OUT);
			event.addSprite(NEMTextures.SMALLCOG_IN);
			event.addSprite(NEMTextures.SMALLCOG_TOOTH);
			event.addSprite(NEMTextures.HAMMER_HEAD);
			event.addSprite(NEMTextures.TRIPHAMMER_SHAFT);
			NotEnoughMachines.LOGGER.debug("All NEM textures have been stitched to Atlas");
		}
	}
}







