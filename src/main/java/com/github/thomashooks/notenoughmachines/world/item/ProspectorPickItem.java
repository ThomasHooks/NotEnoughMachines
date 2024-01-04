package com.github.thomashooks.notenoughmachines.world.item;

import com.github.thomashooks.notenoughmachines.client.KeyboardInputHelper;
import com.github.thomashooks.notenoughmachines.integration.config.CommonConfigs;
import com.github.thomashooks.notenoughmachines.integration.tags.AllTags;
import com.github.thomashooks.notenoughmachines.util.TooltipKeys;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProspectorPickItem extends TieredItem implements Vanishable
{
    private final float attackDamage;
    private final float speed;

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ProspectorPickItem(Tier tier, float attackDamageModifier, float attackSpeedModifier, Properties properties)
    {
        super(tier, properties);
        this.speed = tier.getSpeed();
        this.attackDamage = attackDamageModifier + tier.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", this.getAttackDamage(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", attackSpeedModifier, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context)
    {
        Level world = context.getLevel();
        Player player = context.getPlayer();
        if (!world.isClientSide() && player != null)
        {
            final int scanRange = CommonConfigs.PROSPECTOR_PICK_RANGE.get();
            BlockPos clickedPos = context.getClickedPos();
            for (int y = -scanRange; y <= scanRange; y++)
            {
                for (int x = -scanRange; x <= scanRange; x++)
                {
                    for (int z = -scanRange; z <= scanRange; z++)
                    {
                        BlockState state = world.getBlockState(clickedPos.offset(x, y, z));
                        if (this.isOreBlock(state))
                        {
                            messageTraceAmounts(player, state.getBlock());
                            context.getItemInHand().hurtAndBreak(1, player, (entity) -> { entity.broadcastBreakEvent(EquipmentSlot.MAINHAND); });
                            return InteractionResult.CONSUME;
                        }
                    }
                }
            }
            messageNothingOfValue(player);
            context.getItemInHand().hurtAndBreak(1, player, (entity) -> { entity.broadcastBreakEvent(EquipmentSlot.MAINHAND); });
        }

        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pickItem, @Nullable Level world, @NotNull List<Component> tooltips, @NotNull TooltipFlag flag)
    {
        if (KeyboardInputHelper.getInstance().isPressingShift())
        {
            tooltips.add(Component.literal(""));
            tooltips.add(Component.translatable(TooltipKeys.PROSPECTOR_PICK.getTranslation()).withStyle(ChatFormatting.GREEN));
            tooltips.add(Component.translatable(TooltipKeys.PROSPECTOR_PICK_SCAN.getTranslation(), Integer.toString(CommonConfigs.PROSPECTOR_PICK_RANGE.get())).withStyle(ChatFormatting.GRAY));
        }
        else
            tooltips.add(Component.translatable(TooltipKeys.MORE_INFO_PRESS_SHIFT.getTranslation()).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, BlockState state) { return state.is(BlockTags.MINEABLE_WITH_PICKAXE) ? this.speed : 1.0F; }

    @Override
    public boolean hurtEnemy(ItemStack pickItem, @NotNull LivingEntity target, @NotNull LivingEntity attacker)
    {
        pickItem.hurtAndBreak(2, attacker, (entity) -> { entity.broadcastBreakEvent(EquipmentSlot.MAINHAND); });
        return true;
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack pickItem, @NotNull Level world, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity livingEntity)
    {
        if (!world.isClientSide() && state.getDestroySpeed(world, pos) != 0.0F)
            pickItem.hurtAndBreak(1, livingEntity, (entity) -> { entity.broadcastBreakEvent(EquipmentSlot.MAINHAND); });

        return true;
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot equipmentSlot)
    {
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) { return ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction); }

    @Override
    public boolean isCorrectToolForDrops(@NotNull BlockState state) { return TierSortingRegistry.isCorrectTierForDrops(getTier(), state) && state.is(BlockTags.MINEABLE_WITH_PICKAXE); }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack pickItem, Enchantment enchantment)
    {
        return super.canApplyAtEnchantingTable(pickItem, enchantment)
                || enchantment.equals(Enchantments.SILK_TOUCH)
                || enchantment.equals(Enchantments.BLOCK_FORTUNE)
                || enchantment.equals(Enchantments.BLOCK_EFFICIENCY);
    }

    public float getAttackDamage() { return this.attackDamage; }

    private boolean isOreBlock(BlockState state)
    {
        return state.is(AllTags.Blocks.ORES_IN_DEEPSLATE)
                || state.is(AllTags.Blocks.ORES_IN_NETHERRACK)
                || state.is(AllTags.Blocks.ORES_IN_STONE)
                || state.is(Blocks.AMETHYST_CLUSTER)
                || state.is(Blocks.ANCIENT_DEBRIS);
    }

    private void messageTraceAmounts(Player player, Block block)
    {
        MutableComponent message = Component.translatable(TooltipKeys.PROSPECTOR_PICK_TRACE_AMOUNTS.getTranslation(), block.getName());
        if (block.equals(Blocks.AMETHYST_CLUSTER) || block.equals(Blocks.ANCIENT_DEBRIS) || block.equals(Blocks.DIAMOND_ORE) || block.equals(Blocks.DEEPSLATE_DIAMOND_ORE))
            player.sendSystemMessage(message.withStyle(ChatFormatting.DARK_AQUA));
        else
            player.sendSystemMessage(message.withStyle(ChatFormatting.GREEN));
    }

    private void messageNothingOfValue(Player player)
    {
        player.sendSystemMessage(Component.translatable(TooltipKeys.PROSPECTOR_PICK_NOTHING.getTranslation()).withStyle(ChatFormatting.GRAY));
    }
}
