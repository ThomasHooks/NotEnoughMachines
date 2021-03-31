package com.kilroy790.notenoughmachines.blocks;

import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;




public class NEMButtonBlock extends AbstractButtonBlock 
{
	public NEMButtonBlock(Properties properties) 
	{
		super(false, properties);
	}

	
	
	@Override
	protected SoundEvent getSoundEvent(boolean isOn) 
	{
		return isOn ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
	}
}



