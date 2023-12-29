package com.github.thomashooks.notenoughmachines.world.item;

import com.github.thomashooks.notenoughmachines.util.ToolTipKeys;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PaddedArmorItem extends ArmorItem implements DyeableLeatherItem
{
    public static final int DEFAULT_COLOR = Mth.color(0.863F, 0.827F, 0.718F);
    public PaddedArmorItem(ArmorMaterial armorMaterial, Type armorType, Properties properties)
    {
        super(armorMaterial, armorType, properties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext useOnContext)
    {
        BlockPos pos = useOnContext.getClickedPos();
        Level world = useOnContext.getLevel();
        BlockState state = world.getBlockState(pos);
        Player player = useOnContext.getPlayer();
        ItemStack paddedArmorItem = useOnContext.getItemInHand();
        if (state.getBlock() != Blocks.WATER_CAULDRON || !this.hasCustomColor(paddedArmorItem) || player.isShiftKeyDown())
            return InteractionResult.PASS;

        if (state.getValue(LayeredCauldronBlock.LEVEL) == 0)
            return InteractionResult.PASS;

        if (!world.isClientSide())
        {
            this.clearColor(paddedArmorItem);
            player.awardStat(Stats.CLEAN_ARMOR);
            LayeredCauldronBlock.lowerFillLevel(state, world, pos);
        }

        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level world, @NotNull List<Component> toolTips, @NotNull TooltipFlag flags)
    {
        if (itemStack.getItem().equals(AllItems.PADDED_BOOTS.orElseThrow(IllegalStateException::new)))
            toolTips.add(Component.translatable(ToolTipKeys.REDUCES_FALL_DAMAGE.getTranslation()).withStyle(ChatFormatting.GREEN));

        if (!this.hasCustomColor(itemStack))
            toolTips.add(Component.translatable(ToolTipKeys.IS_DYEABLE.getTranslation()).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public int getColor(ItemStack itemStack)
    {
        CompoundTag compoundtag = itemStack.getTagElement("display");
        return compoundtag != null && compoundtag.contains("color", 99) ? compoundtag.getInt("color") : DEFAULT_COLOR;
    }

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) { return true; }

    public static void onLivingEntityFall(@NotNull LivingFallEvent event)
    {
        if (!(event.getEntity() instanceof Player player))
            return;

        final ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
        if (AllItems.PADDED_BOOTS.orElseThrow(IllegalStateException::new).equals(boots.getItem()))
            event.setDamageMultiplier(0.25F);
    }
}
