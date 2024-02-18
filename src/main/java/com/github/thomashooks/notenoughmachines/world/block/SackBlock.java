package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.util.TooltipKeys;
import com.github.thomashooks.notenoughmachines.world.block.entity.AllBlockEntities;
import com.github.thomashooks.notenoughmachines.world.block.entity.SackBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SackBlock extends Block implements EntityBlock
{
    protected static final VoxelShape INNER_BASE_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
    protected static final VoxelShape OUTER_BASE_SHAPE = Block.box(1.0D, 1.0D, 1.0D, 15.0D, 11.0D, 15.0D);
    protected static final VoxelShape NECK_CLOSED_SHAPE = Block.box(6.0D, 12.0D, 6.0D, 10.0D, 13.0D, 10.0D);
    protected static final VoxelShape LIP_CLOSED_SHAPE = Block.box(5.0D, 13.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    protected static final VoxelShape NECK_OPENED_SHAPE = Block.box(3.0D, 12.0D, 3.0D, 13.0D, 13.0D, 13.0D);
    protected static final VoxelShape LIP_OPENED_SHAPE = Block.box(2.0D, 13.0D, 2.0D, 14.0D, 14.0D, 14.0D);
    protected static final VoxelShape SACK_CLOSED_SHAPE = Shapes.or(INNER_BASE_SHAPE, OUTER_BASE_SHAPE, NECK_CLOSED_SHAPE, LIP_CLOSED_SHAPE);
    protected static final VoxelShape SACK_OPENED_SHAPE = Shapes.or(INNER_BASE_SHAPE, OUTER_BASE_SHAPE, NECK_OPENED_SHAPE, LIP_OPENED_SHAPE);
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    @Nullable
    private final DyeColor color;

    public SackBlock(@javax.annotation.Nullable DyeColor dyeColor, Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(OPEN, false)
        );
        this.color = dyeColor;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult hitResult)
    {
        if (!world.isClientSide())
        {
            if (world.getBlockEntity(pos) instanceof SackBlockEntity sackBlockEntity)
            {
                NetworkHooks.openScreen((ServerPlayer) player, sackBlockEntity, sackBlockEntity.getBlockPos());
                PiglinAi.angerNearbyPiglins(player, true);
            }
            else
                throw new IllegalStateException(NotEnoughMachines.MOD_ID + ".%s: menu provider is missing!".formatted(SackBlock.class.getCanonicalName()));
        }
        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    @Override
    public void playerWillDestroy(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Player player)
    {
        if (world.getBlockEntity(pos) instanceof SackBlockEntity sackBlockEntity)
        {
            if (!world.isClientSide() && player.isCreative() && !sackBlockEntity.isEmpty())
            {
                ItemStack itemStack = getColoredItemStack(this.getColor());
                sackBlockEntity.saveToItem(itemStack);
                ItemEntity itementity = new ItemEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, itemStack);
                itementity.setDefaultPickUpDelay();
                world.addFreshEntity(itementity);
            }
        }
        super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    public void onRemove(@NotNull BlockState oldState, @NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving)
    {
        if (!oldState.is(newState.getBlock()))
        {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SackBlockEntity)
                world.updateNeighbourForOutputSignal(pos, oldState.getBlock());
        }
        super.onRemove(oldState, world, pos, newState, isMoving);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack stack, @Nullable BlockGetter blockGetter, @NotNull List<Component> tooltips, @NotNull TooltipFlag flag)
    {
        CompoundTag sackItemNBT = BlockItem.getBlockEntityData(stack);
        if (sackItemNBT != null)
        {
            if (sackItemNBT.contains(SackBlockEntity.ITEMS_TAG, Tag.TAG_COMPOUND))
            {
                NonNullList<ItemStack> itemStacks = NonNullList.withSize(SackBlockEntity.CONTAINER_SIZE, ItemStack.EMPTY);
                ContainerHelper.loadAllItems(sackItemNBT.getCompound(SackBlockEntity.ITEMS_TAG), itemStacks);
                int printableItemStacks = 0;
                int numberOfItemStacks = 0;

                for (ItemStack itemStack : itemStacks)
                {
                    if (!itemStack.isEmpty())
                    {
                        ++numberOfItemStacks;
                        if (printableItemStacks <= 4)
                        {
                            ++printableItemStacks;
                            MutableComponent mutablecomponent = itemStack.getHoverName().copy();
                            mutablecomponent.append(" x").append(String.valueOf(itemStack.getCount()));
                            tooltips.add(mutablecomponent);
                        }
                    }
                }
                if (numberOfItemStacks == 0)
                    tooltips.add(Component.translatable(TooltipKeys.SACK_IS_EMPTY.getTranslation()).withStyle(ChatFormatting.ITALIC));
                else if (numberOfItemStacks - printableItemStacks > 0)
                    tooltips.add(Component.translatable(TooltipKeys.SACK_OVERFLOW.getTranslation(), numberOfItemStacks - printableItemStacks).withStyle(ChatFormatting.ITALIC));
            }
        }
        else
            tooltips.add(Component.translatable(TooltipKeys.SACK_IS_EMPTY.getTranslation()).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter blockGetter, @NotNull BlockPos pos, @NotNull CollisionContext context) { return state.getValue(OPEN) ? SACK_OPENED_SHAPE : SACK_CLOSED_SHAPE; }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState state) { return true; }

    @Override
    public int getAnalogOutputSignal(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos)
    {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof SackBlockEntity sackBlockEntity)
            return sackBlockEntity.getRedstoneSignal();

        return super.getAnalogOutputSignal(state, world, pos);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter blockGetter, BlockPos pos, Player player)
    {
        ItemStack cloneItemStack = super.getCloneItemStack(state, target, blockGetter, pos, player);
        blockGetter.getBlockEntity(pos, AllBlockEntities.SACK.get()).ifPresent((sackBlockEntity) -> { sackBlockEntity.saveToItem(cloneItemStack); });
        return cloneItemStack;
    }

    @Nullable
    public DyeColor getColor() { return this.color; }

    public static Block getBlockByColor(@Nullable DyeColor dyeColor)
    {
        if (dyeColor == null)
            return AllBlocks.SACK.get();

        return switch (dyeColor)
        {
            case WHITE -> AllBlocks.SACK_WHITE.get();
            case ORANGE -> AllBlocks.SACK_ORANGE.get();
            case MAGENTA -> AllBlocks.SACK_MAGENTA.get();
            case LIGHT_BLUE -> AllBlocks.SACK_LIGHT_BLUE.get();
            case YELLOW -> AllBlocks.SACK_YELLOW.get();
            case LIME -> AllBlocks.SACK_LIME.get();
            case PINK -> AllBlocks.SACK_PINK.get();
            case GRAY -> AllBlocks.SACK_GRAY.get();
            case LIGHT_GRAY -> AllBlocks.SACK_LIGHT_GRAY.get();
            case CYAN -> AllBlocks.SACK_CYAN.get();
            case PURPLE -> AllBlocks.SACK_PURPLE.get();
            case BLUE -> AllBlocks.SACK_BLUE.get();
            case BROWN -> AllBlocks.SACK_BROWN.get();
            case GREEN -> AllBlocks.SACK_GREEN.get();
            case RED -> AllBlocks.SACK_RED.get();
            case BLACK -> AllBlocks.SACK_BLACK.get();
            default -> AllBlocks.SACK.get();
        };
    }

    public static ItemStack getColoredItemStack(@Nullable DyeColor dyeColor) { return new ItemStack(getBlockByColor(dyeColor)); }

    @Nullable
    public static DyeColor getColorFromItem(Item item) { return getColorFromBlock(Block.byItem(item)); }

    @Nullable
    public static DyeColor getColorFromBlock(Block block) { return block instanceof SackBlock ? ((SackBlock)block).getColor() : null; }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level world, @NotNull BlockState state, @NotNull BlockEntityType<T> entityType) { return SackBlockEntity.getTicker(world); }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) { return AllBlockEntities.SACK.get().create(pos, state); }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { builder.add(OPEN); }
}
