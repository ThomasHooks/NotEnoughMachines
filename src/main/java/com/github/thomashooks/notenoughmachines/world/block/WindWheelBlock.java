package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.common.util.KeyboardInputHelper;
import com.github.thomashooks.notenoughmachines.common.util.ToolTipKeys;
import com.github.thomashooks.notenoughmachines.common.util.VoxelShapeHelper;
import com.github.thomashooks.notenoughmachines.world.block.entity.AllBlockEntities;
import com.github.thomashooks.notenoughmachines.world.block.entity.WindWheelBlockEntity;
import com.github.thomashooks.notenoughmachines.world.power.MechanicalConnectionHelper;
import com.github.thomashooks.notenoughmachines.world.power.MechanicalContext;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WindWheelBlock extends HorizontalMechanicalBlock
{
    public WindWheelBlock(Properties properties) { super(properties); }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> toolTips, TooltipFlag flags)
    {
        if (KeyboardInputHelper.isPressingShift())
        {
            toolTips.add(Component.literal(""));
            toolTips.add(Component.translatable(ToolTipKeys.WIND_WHEEL1.getTranslation()).withStyle(ChatFormatting.GREEN));
            toolTips.add(Component.translatable(ToolTipKeys.WIND_WHEEL2.getTranslation()));
        }
        else
            toolTips.add(Component.translatable(ToolTipKeys.MORE_INFO_PRESS_SHIFT.getTranslation()).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext)
    {
        return VoxelShapeHelper.AXLE[VoxelShapeHelper.AXIS_LOOKUP.get(state.getValue(FACING).getAxis())];
    }

    @Override
    public RenderShape getRenderShape(BlockState state) { return RenderShape.ENTITYBLOCK_ANIMATED; }

    @Override
    public ArrayList<MechanicalContext> getMechanicalConnections(Level world, BlockPos pos, BlockState state)
    {
        return MechanicalConnectionHelper.monoAxle(pos, state.getValue(FACING).getAxis());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> blockEntityType)
    {
        return WindWheelBlockEntity.getTicker(world);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return AllBlockEntities.WIND_WHEEL.get().create(pos, state);
    }
}
