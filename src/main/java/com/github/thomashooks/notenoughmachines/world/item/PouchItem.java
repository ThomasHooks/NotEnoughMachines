package com.github.thomashooks.notenoughmachines.world.item;

import com.github.thomashooks.notenoughmachines.client.KeyboardInputHelper;
import com.github.thomashooks.notenoughmachines.util.TooltipKeys;
import com.github.thomashooks.notenoughmachines.world.inventory.tooltip.ItemPouchTooltip;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class PouchItem extends Item implements DyeableLeatherItem
{
    private static final String TAG_ITEMS = "Items";
    private static final String TAG_SLOT = "slot";
    public static final int MAX_WEIGHT = 256;
    public static final int MAX_ITEM_STACK_WEIGHT = 64;
    private static final int RECURSIVE_POUCH_WEIGHT = 4;
    private static final int BAR_COLOR = Mth.color(0.4F, 0.4F, 1.0F);
    public static final int DEFAULT_COLOR = Mth.color(0.863F, 0.827F, 0.718F);

    public PouchItem(Item.Properties properties) { super(properties); }

    @Override
    public boolean overrideStackedOnOther(ItemStack incomingItemStack, @NotNull Slot slot, @NotNull ClickAction clickAction, @NotNull Player player)
    {
        if (incomingItemStack.getCount() != 1 || clickAction != ClickAction.SECONDARY)
            return false;
        else
        {
            ItemStack itemstack = slot.getItem();
            if (itemstack.isEmpty())
            {
                this.playRemoveOneSound(player);
                removeItemStack(incomingItemStack).ifPresent((stack) ->
                {
                    addItemStack(incomingItemStack, slot.safeInsert(stack));
                });
            }
            else if (itemstack.getItem().canFitInsideContainerItems())
            {
                int i = (MAX_WEIGHT - getContentsWeight(incomingItemStack)) / getWeight(itemstack);
                int j = addItemStack(incomingItemStack, slot.safeTake(itemstack.getCount(), i, player));
                if (j > 0)
                    this.playInsertSound(player);
                else
                    this.playFullSound(player);
            }
            return true;
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack currentItemStack, @NotNull ItemStack incomingItemStack, @NotNull Slot slot, @NotNull ClickAction clickAction, @NotNull Player player, @NotNull SlotAccess slotAccess)
    {
        if (currentItemStack.getCount() != 1)
            return false;

        if (clickAction == ClickAction.SECONDARY && slot.allowModification(player))
        {
            if (incomingItemStack.isEmpty())
            {
                removeItemStack(currentItemStack).ifPresent((stack) ->
                {
                    this.playRemoveOneSound(player);
                    slotAccess.set(stack);
                });
            }
            else
            {
                int i = addItemStack(currentItemStack, incomingItemStack);
                if (i > 0)
                {
                    this.playInsertSound(player);
                    incomingItemStack.shrink(i);
                }
                else
                    this.playFullSound(player);
            }
            return true;
        }
        else
            return false;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, Player player, @NotNull InteractionHand interactionHand)
    {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        if (getContentsWeight(itemstack) > 0 && !player.isShiftKeyDown())
        {
            dropAllContents(itemstack, player);
            this.playDropContentsSound(player);
            player.awardStat(Stats.ITEM_USED.get(this));
            return InteractionResultHolder.sidedSuccess(itemstack, world.isClientSide());
        }
        else
            return InteractionResultHolder.fail(itemstack);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext useOnContext)
    {
        BlockPos pos = useOnContext.getClickedPos();
        Level world = useOnContext.getLevel();
        BlockState state = world.getBlockState(pos);
        Player player = useOnContext.getPlayer();
        ItemStack pouchItem = useOnContext.getItemInHand();
        if (state.getBlock() != Blocks.WATER_CAULDRON || !this.hasCustomColor(pouchItem) || player.isShiftKeyDown())
            return InteractionResult.PASS;

        if (state.getValue(LayeredCauldronBlock.LEVEL) == 0)
            return InteractionResult.PASS;

        if (!world.isClientSide())
        {
            this.clearColor(pouchItem);
            LayeredCauldronBlock.lowerFillLevel(state, world, pos);
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack itemStack) { return getContentsWeight(itemStack) > 0; }

    @Override
    public int getBarWidth(@NotNull ItemStack itemStack)
    {
        return Math.min(1 + 12 * getContentsWeight(itemStack) / MAX_WEIGHT, 13);
    }

    @Override
    public int getBarColor(@NotNull ItemStack itemStack) { return BAR_COLOR; }

    @Override
    public int getColor(ItemStack itemStack)
    {
        CompoundTag tag = itemStack.getTagElement("display");
        return tag != null && tag.contains("color", 99) ? tag.getInt("color") : DEFAULT_COLOR;
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack pouchItem)
    {
        NonNullList<ItemStack> items = NonNullList.create();
        getContents(pouchItem).forEach(items::add);
        CompoundTag pouchTag = pouchItem.getOrCreateTag();
        if (!pouchTag.contains(TAG_SLOT))
            pouchTag.putInt(TAG_SLOT, 0);
        return Optional.of(new ItemPouchTooltip(items, pouchTag.getInt(TAG_SLOT), getContentsWeight(pouchItem)));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack pouchItem, Level world, @NotNull List<Component> tooltips, @NotNull TooltipFlag flag)
    {
        if (KeyboardInputHelper.getInstance().pressedKeyW())
        {
            CompoundTag pouchTag = pouchItem.getOrCreateTag();
            if (!pouchTag.contains(TAG_SLOT))
                pouchTag.putInt(TAG_SLOT, 0);

            NonNullList<ItemStack> items = NonNullList.create();
            getContents(pouchItem).forEach(items::add);
            int slot = pouchTag.getInt(TAG_SLOT) + 1;
            if (slot >= items.size())
                slot = items.size() - 1;

            pouchTag.putInt(TAG_SLOT, slot);
        }
        else if (KeyboardInputHelper.getInstance().pressedKeyS())
        {
            CompoundTag pouchTag = pouchItem.getOrCreateTag();
            if (!pouchTag.contains(TAG_SLOT))
                pouchTag.putInt(TAG_SLOT, 0);

            int slot = pouchTag.getInt(TAG_SLOT) - 1;
            if (slot < 0)
                slot = 0;

            pouchTag.putInt(TAG_SLOT, slot);
        }

        tooltips.add(Component.translatable(TooltipKeys.ITEM_POUCH_FULLNESS.getTranslation(), getContentsWeight(pouchItem), MAX_WEIGHT).withStyle(ChatFormatting.BLUE));
        if (KeyboardInputHelper.getInstance().isPressingShift())
        {
            tooltips.add(Component.literal(""));
            tooltips.add(Component.translatable(TooltipKeys.ITEM_POUCH.getTranslation()).withStyle(ChatFormatting.GREEN));
            tooltips.add(Component.translatable(TooltipKeys.ITEM_POUCH_INSERT.getTranslation()).withStyle(ChatFormatting.GRAY));
            tooltips.add(Component.literal(""));
            tooltips.add(Component.translatable(TooltipKeys.ITEM_POUCH_REMOVE.getTranslation()).withStyle(ChatFormatting.GRAY));
            tooltips.add(Component.translatable(TooltipKeys.ITEM_POUCH_SLOT.getTranslation()).withStyle(ChatFormatting.GRAY));
        }
        else
            tooltips.add(Component.translatable(TooltipKeys.MORE_INFO_PRESS_SHIFT.getTranslation()).withStyle(ChatFormatting.GRAY));
        if (!this.hasCustomColor(pouchItem))
            tooltips.add(Component.translatable(TooltipKeys.IS_DYEABLE.getTranslation()).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public void onDestroyed(@NotNull ItemEntity itemEntity)
    {
        ItemUtils.onContainerDestroyed(itemEntity, getContents(itemEntity.getItem()));
    }

    protected static Optional<CompoundTag> getMatchingItem(ItemStack itemStack, ListTag listTag)
    {
        return itemStack.is(AllItems.ITEM_POUCH.get()) ? Optional.empty() : listTag.stream()
                .filter(CompoundTag.class::isInstance)
                .map(CompoundTag.class::cast)
                .filter((nbt) -> { return ItemStack.isSameItemSameTags(ItemStack.of(nbt), itemStack); })
                .findFirst();
    }

    protected static int getWeight(ItemStack itemStack)
    {
        if (itemStack.is(Items.BUNDLE) || itemStack.is(AllItems.ITEM_POUCH.get()))
            return RECURSIVE_POUCH_WEIGHT + getContentsWeight(itemStack);
        else
        {
            if ((itemStack.is(Items.BEEHIVE) || itemStack.is(Items.BEE_NEST)) && itemStack.hasTag())
            {
                CompoundTag tag = BlockItem.getBlockEntityData(itemStack);
                if (tag != null && !tag.getList("Bees", 10).isEmpty())
                    return MAX_ITEM_STACK_WEIGHT;
            }
            return MAX_ITEM_STACK_WEIGHT / itemStack.getMaxStackSize();
        }
    }

    protected static Stream<ItemStack> getContents(@NotNull ItemStack pouchItem)
    {
        CompoundTag compoundtag = pouchItem.getTag();
        if (compoundtag == null)
            return Stream.empty();
        else
        {
            ListTag itemsTag = compoundtag.getList(TAG_ITEMS, 10);
            return itemsTag.stream().map(CompoundTag.class::cast).map(ItemStack::of);
        }
    }

    protected static int getContentsWeight(ItemStack itemStack)
    {
        return getContents(itemStack).mapToInt((stack) -> { return getWeight(stack) * stack.getCount(); }).sum();
    }

    protected static int addItemStack(ItemStack itemPouch, ItemStack incomingItemStack)
    {
        if (!incomingItemStack.isEmpty() && incomingItemStack.getItem().canFitInsideContainerItems())
        {
            CompoundTag pouchTag = itemPouch.getOrCreateTag();
            if (!pouchTag.contains(TAG_ITEMS))
                pouchTag.put(TAG_ITEMS, new ListTag());

            int contentWeight = getContentsWeight(itemPouch);
            int incomingItemStackWeight = getWeight(incomingItemStack);
            int count = Math.min(incomingItemStack.getCount(), (MAX_WEIGHT - contentWeight) / incomingItemStackWeight);
            if (count == 0)
                return 0;
            else
            {
                ListTag itemsTag = pouchTag.getList(TAG_ITEMS, 10);
                Optional<CompoundTag> optional = getMatchingItem(incomingItemStack, itemsTag);
                if (optional.isPresent())
                {
                    CompoundTag itemStackTag = optional.get();
                    ItemStack itemStackInPouch = ItemStack.of(itemStackTag);

                    if (itemStackInPouch.getCount() + count <= itemStackInPouch.getMaxStackSize())
                    {
                        itemStackInPouch.grow(count);
                        itemStackInPouch.save(itemStackTag);
                        itemsTag.remove(itemStackTag);
                        itemsTag.add(0, (Tag)itemStackTag);
                    }
                    else
                    {
                        int remainder = itemStackInPouch.getCount() + count - itemStackInPouch.getMaxStackSize();
                        itemStackInPouch.grow(count - remainder);
                        itemStackInPouch.save(itemStackTag);
                        itemsTag.remove(itemStackTag);
                        itemsTag.add(0, (Tag)itemStackTag);

                        ItemStack itemStackRemainder = incomingItemStack.copyWithCount(remainder);
                        CompoundTag itemStackRemainderTag = new CompoundTag();
                        itemStackRemainder.save(itemStackRemainderTag);
                        itemsTag.add(0, (Tag)itemStackRemainderTag);
                    }
                }
                else
                {
                    ItemStack itemStackToAdd = incomingItemStack.copyWithCount(count);
                    CompoundTag itemStackToAddTag = new CompoundTag();
                    itemStackToAdd.save(itemStackToAddTag);
                    itemsTag.add(0, (Tag)itemStackToAddTag);
                }
                return count;
            }
        }
        else
            return 0;
    }

    protected static Optional<ItemStack> removeItemStack(ItemStack itemPouch)
    {
        CompoundTag pouchTag = itemPouch.getOrCreateTag();
        if (!pouchTag.contains(TAG_ITEMS))
            return Optional.empty();
        else
        {
            ListTag itemList = pouchTag.getList(TAG_ITEMS, 10);
            if (itemList.isEmpty())
                return Optional.empty();
            else
            {
                if (!pouchTag.contains(TAG_SLOT))
                    pouchTag.putInt(TAG_SLOT, 0);

                int slot = pouchTag.getInt(TAG_SLOT);
                CompoundTag itemStackTag = itemList.getCompound(slot);
                ItemStack itemStack = ItemStack.of(itemStackTag);
                itemList.remove(slot);
                if (itemList.isEmpty())
                    itemPouch.removeTagKey(TAG_ITEMS);

                if (--slot < 0)
                    slot = 0;
                pouchTag.putInt(TAG_SLOT, slot);

                return Optional.of(itemStack);
            }
        }
    }

    protected static void dropAllContents(ItemStack pouchItem, Player player)
    {
        CompoundTag pouchTag = pouchItem.getOrCreateTag();
        if (!pouchTag.contains(TAG_ITEMS))
            return;

        if (player instanceof ServerPlayer)
        {
            ListTag itemList = pouchTag.getList(TAG_ITEMS, 10);
            for(int i = 0; i < itemList.size(); ++i)
            {
                CompoundTag itemStackTag = itemList.getCompound(i);
                ItemStack itemstack = ItemStack.of(itemStackTag);
                player.drop(itemstack, true);
            }
        }
        pouchItem.removeTagKey(TAG_ITEMS);
    }

    protected void playRemoveOneSound(@NotNull Entity entity)
    {
        entity.playSound(SoundEvents.BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    protected void playInsertSound(@NotNull Entity entity)
    {
        entity.playSound(SoundEvents.BUNDLE_INSERT, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    protected void playDropContentsSound(@NotNull Entity entity)
    {
        entity.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    protected void playFullSound(@NotNull Entity entity)
    {
        entity.playSound(SoundEvents.VILLAGER_NO, 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
    }
}
