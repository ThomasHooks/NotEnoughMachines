package com.kilroy790.notenoughmachines.lists;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupList {

	public static ItemGroup NEM_ITEMGROUP = new ItemGroup("notenoughtmachines") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemList.GEAR);
		}
		
	};
}
