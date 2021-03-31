package com.kilroy790.notenoughmachines.setup.lootmodifiers;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;
import com.kilroy790.notenoughmachines.items.NEMItems;

import net.minecraft.item.ItemStack;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;




public class GrassLootModifier extends LootModifier
{
	private final boolean enableLootModifier;
	private final float dropProbability;
	
	protected GrassLootModifier(ILootCondition[] conditionsIn, boolean enableLootModifierIn, float dropProbabilityIn) 
	{
		super(conditionsIn);
		this.enableLootModifier = enableLootModifierIn;
		this.dropProbability = dropProbabilityIn;
	}

	
	
	@Nonnull
	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) 
	{
		if (enableLootModifier && context.getRandom().nextFloat() <= dropProbability)
		{
			generatedLoot.add(new ItemStack(NEMItems.FLAXSEED.get()));
		}
		return generatedLoot;
	}
	
	
	
	public static class Serializer extends GlobalLootModifierSerializer<GrassLootModifier>
	{
		@Override
		public GrassLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] conditionsIn) 
		{
			return new GrassLootModifier(conditionsIn, JSONUtils.getBoolean(object, "enableLootModifier"), JSONUtils.getFloat(object, "dropProbability"));
		}
	}
}



