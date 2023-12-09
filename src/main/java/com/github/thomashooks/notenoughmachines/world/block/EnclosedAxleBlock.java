package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.util.KeyboardInputHelper;
import com.github.thomashooks.notenoughmachines.util.VoxelShapeHelper;
import com.github.thomashooks.notenoughmachines.world.block.entity.AllBlockEntities;
import com.github.thomashooks.notenoughmachines.world.block.entity.EnclosedAxleBlockEntity;
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

public class EnclosedAxleBlock extends RotatingShaftBlock
{
    public EnclosedAxleBlock(Properties properties) { super(properties); }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> toolTips, TooltipFlag flags)
    {
        if (KeyboardInputHelper.isPressingShift())
        {
            toolTips.add(Component.literal(""));
            toolTips.add(Component.literal("Transfers mechanical power in a straight line").withStyle(ChatFormatting.GREEN));
        }
        else
            toolTips.add(Component.literal(KeyboardInputHelper.MORE_INFO_PRESS_SHIFT).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext)
    {
        return VoxelShapeHelper.ENCLOSED_AXLE[VoxelShapeHelper.AXIS_LOOKUP.get(state.getValue(AXIS))];
    }

    @Override
    public RenderShape getRenderShape(BlockState state) { return RenderShape.MODEL; }

    @Override
    public ArrayList<MechanicalContext> getMechanicalConnections(Level world, BlockPos pos, BlockState state)
    {
        return MechanicalConnectionHelper.monoAxle(pos, state.getValue(AXIS));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> blockEntityType)
    {
        return EnclosedAxleBlockEntity.getTicker(world);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return AllBlockEntities.ENCLOSED_AXLE.get().create(pos, state);
    }
}
