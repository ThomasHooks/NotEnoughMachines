package com.kilroy790.notenoughmachines.items;

import com.kilroy790.notenoughmachines.NotEnoughMachines;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;




public class NEMItemGroup {

	public static ItemGroup NEM_ITEMGROUP = new ItemGroup(NotEnoughMachines.MODID) {

		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon() {
			return new ItemStack(NEMItems.GEAR.get());
		}
	};
}







