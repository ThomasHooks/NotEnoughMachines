package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.client.KeyboardInputHelper;
import com.github.thomashooks.notenoughmachines.util.TooltipKeys;
import com.github.thomashooks.notenoughmachines.world.block.entity.FilterBlockEntity;
import com.github.thomashooks.notenoughmachines.world.block.entity.AllBlockEntities;
import com.github.thomashooks.notenoughmachines.world.inventory.FilterMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FilterBlock extends ItemConduitBlock
{
    public FilterBlock(Properties properties)
    {
        super(properties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        //Filter's can only move item down and side to side
        return this.defaultBlockState()
                .setValue(FACING, Direction.DOWN)
                .setValue(NORTH, this.canConnectTo(world, pos.north(), Direction.NORTH))
                .setValue(EAST, this.canConnectTo(world, pos.east(), Direction.EAST))
                .setValue(SOUTH, this.canConnectTo(world, pos.south(), Direction.SOUTH))
                .setValue(WEST, this.canConnectTo(world, pos.west(), Direction.WEST))
                .setValue(UP, this.canConnectTo(world, pos.above(), Direction.UP))
                .setValue(DOWN, this.canConnectTo(world, pos.below(), Direction.DOWN));
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult hitResult)
    {
        if (!world.isClientSide())
        {
            if (world.getBlockEntity(pos) instanceof FilterBlockEntity filterEntity)
            {
                MenuProvider menuProvider = new MenuProvider()
                {
                    @Override
                    public Component getDisplayName() { return FilterMenu.TITLE; }

                    @Nullable
                    @Override
                    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player)
                    {
                        return new FilterMenu(containerId, playerInventory, player.level().getBlockEntity(pos));
                    }
                };
                NetworkHooks.openScreen((ServerPlayer) player, menuProvider, filterEntity.getBlockPos());
            }
            else
                throw new IllegalStateException(NotEnoughMachines.MOD_ID + ": FilterBlock menu provider is missing!");
        }
        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> toolTips, TooltipFlag flags)
    {
        if (KeyboardInputHelper.getInstance().isPressingShift())
        {
            toolTips.add(Component.literal(""));
            toolTips.add(Component.translatable(TooltipKeys.ITEM_FILTER.getTranslation()).withStyle(ChatFormatting.GREEN));
        }
        else
            toolTips.add(Component.translatable(TooltipKeys.MORE_INFO_PRESS_SHIFT.getTranslation()).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> entityType)
    {
        return FilterBlockEntity.getTicker(world);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return AllBlockEntities.FILTER.get().create(pos, state);
    }
}
