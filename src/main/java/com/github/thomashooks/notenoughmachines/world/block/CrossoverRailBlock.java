package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.util.KeyboardInputHelper;
import com.github.thomashooks.notenoughmachines.util.ToolTipKeys;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrossoverRailBlock extends BaseRailBlock
{
    public static final EnumProperty<RailShape> SHAPE = BlockStateProperties.RAIL_SHAPE_STRAIGHT;

    public CrossoverRailBlock(Properties properties)
    {
        super(true, properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(SHAPE, RailShape.NORTH_SOUTH)
                .setValue(WATERLOGGED, false));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> toolTips, TooltipFlag flag)
    {
        if (KeyboardInputHelper.isPressingShift())
        {
            toolTips.add(Component.literal(""));
            toolTips.add(Component.translatable(ToolTipKeys.CROSSOVER_RAIL1.getTranslation()).withStyle(ChatFormatting.GREEN));
            toolTips.add(Component.translatable(ToolTipKeys.CROSSOVER_RAIL2.getTranslation()).withStyle(ChatFormatting.GRAY));
        }
        else
            toolTips.add(Component.translatable(ToolTipKeys.MORE_INFO_PRESS_SHIFT.getTranslation()).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Property<RailShape> getShapeProperty() { return SHAPE; }

    @Override
    public boolean canMakeSlopes(BlockState state, BlockGetter level, BlockPos pos) { return false; }

    @Override
    public RailShape getRailDirection(BlockState state, BlockGetter world, BlockPos pos, @Nullable AbstractMinecart cart)
    {
        if (cart != null)
        {
            Direction direction = RedstoneRailBlock.getMinecartMovementDirection(cart);
            if (direction != null)
            {
                return switch (direction)
                {
                    case EAST, WEST -> RailShape.EAST_WEST;
                    default -> RailShape.NORTH_SOUTH;
                };
            }
        }

        return super.getRailDirection(state, world, pos, cart);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { builder.add(SHAPE, WATERLOGGED); }
}
