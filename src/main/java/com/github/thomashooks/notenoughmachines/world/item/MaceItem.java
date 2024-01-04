package com.github.thomashooks.notenoughmachines.world.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;

public class MaceItem extends TieredItem implements Vanishable
{
    private final float attackDamage;
    private final float speed;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public MaceItem(Tier tier, float attackDamageModifier, float attackSpeedModifier, float knockBackModifier, Properties properties)
    {
        super(tier, properties);
        this.attackDamage = attackDamageModifier + tier.getAttackDamageBonus();
        this.speed = tier.getSpeed();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.getAttackDamage(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", knockBackModifier, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", attackSpeedModifier, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public boolean canAttackBlock(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, Player player) { return !player.isCreative(); }

    @Override
    public float getDestroySpeed(@NotNull ItemStack itemStack, BlockState state) { return state.is(BlockTags.MINEABLE_WITH_PICKAXE) ? this.speed : 1.0F; }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, @NotNull LivingEntity target, @NotNull LivingEntity attacker)
    {
        itemStack.hurtAndBreak(1, attacker, (entity) -> { entity.broadcastBreakEvent(EquipmentSlot.MAINHAND); });
        return true;
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity livingEntity)
    {
        if (!world.isClientSide() && state.getDestroySpeed(world, pos) != 0.0F)
            itemStack.hurtAndBreak(1, livingEntity, (entity) -> { entity.broadcastBreakEvent(EquipmentSlot.MAINHAND); });

        return true;
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot equipmentSlot)
    {
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction)
    {
        return ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction) || ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }

    @Override
    public boolean isCorrectToolForDrops(@NotNull BlockState state)
    {
        return TierSortingRegistry.isCorrectTierForDrops(getTier(), state) && state.is(BlockTags.MINEABLE_WITH_PICKAXE);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment)
    {
        return super.canApplyAtEnchantingTable(stack, enchantment)
                || enchantment.equals(Enchantments.SILK_TOUCH)
                || enchantment.equals(Enchantments.BLOCK_FORTUNE)
                || enchantment.equals(Enchantments.BLOCK_EFFICIENCY)
                || enchantment.equals(Enchantments.MOB_LOOTING)
                || enchantment.equals(Enchantments.SMITE);
    }

    public float getAttackDamage() { return this.attackDamage; }
}
