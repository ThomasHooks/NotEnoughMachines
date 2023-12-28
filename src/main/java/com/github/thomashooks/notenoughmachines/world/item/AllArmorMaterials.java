package com.github.thomashooks.notenoughmachines.world.item;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum AllArmorMaterials implements StringRepresentable, ArmorMaterial
{
    LINEN("linen", 7, Util.make(new EnumMap<>(ArmorItem.Type.class), (typeMap) ->
    {
        typeMap.put(ArmorItem.Type.BOOTS, 1);
        typeMap.put(ArmorItem.Type.LEGGINGS, 3);
        typeMap.put(ArmorItem.Type.CHESTPLATE, 4);
        typeMap.put(ArmorItem.Type.HELMET, 2);
    }), 15, SoundEvents.ARMOR_EQUIP_GENERIC, 0.0F, 0.1F, () ->
    {
        return Ingredient.of(AllItems.LINEN.orElseThrow(IllegalStateException::new));
    });

    private static final EnumMap<ArmorItem.Type, Integer> BASE_DURABILITY = Util.make(new EnumMap<>(ArmorItem.Type.class), (typeMap) ->
    {
        typeMap.put(ArmorItem.Type.BOOTS, 13);
        typeMap.put(ArmorItem.Type.LEGGINGS, 15);
        typeMap.put(ArmorItem.Type.CHESTPLATE, 16);
        typeMap.put(ArmorItem.Type.HELMET, 11);
    });

    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionAmountForType;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    AllArmorMaterials(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> protectionAmountForType, int enchantmentValue, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient)
    {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmountForType = protectionAmountForType;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    @Override
    public int getDurabilityForType(ArmorItem.@NotNull Type armorType)
    {
        return BASE_DURABILITY.get(armorType) * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.@NotNull Type armorType)
    {
        return this.protectionAmountForType.get(armorType);
    }

    @Override
    public int getEnchantmentValue() { return this.enchantmentValue; }

    @Override
    public @NotNull SoundEvent getEquipSound() { return this.equipSound; }

    @Override
    public @NotNull Ingredient getRepairIngredient() { return this.repairIngredient.get(); }

    @Override
    public @NotNull String getName()
    {
        return NotEnoughMachines.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() { return this.toughness; }

    @Override
    public float getKnockbackResistance() { return this.knockbackResistance; }

    @Override
    public @NotNull String getSerializedName() { return this.name; }
}
