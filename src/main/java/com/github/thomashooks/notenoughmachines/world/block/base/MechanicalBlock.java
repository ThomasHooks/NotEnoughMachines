package com.github.thomashooks.notenoughmachines.world.block.base;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.entity.base.MechanicalBlockEntity;
import com.github.thomashooks.notenoughmachines.world.block.state.NEMBlockStateProperties;
import com.github.thomashooks.notenoughmachines.world.power.MechanicalContext;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;

public abstract class MechanicalBlock extends Block  implements EntityBlock
{
    public static final BooleanProperty SHIFTED = NEMBlockStateProperties.SHIFTED;

    protected MechanicalBlock(Properties properties)
    {
        super(properties);
    }

    /**
     * Gets an array that contains this machine's mechanical connection points
     * @param world This machine's World
     * @param pos This machine's Block Position
     * @param state This machine's current Block State
     */
    abstract public ArrayList<MechanicalContext> getMechanicalConnections(Level world, BlockPos pos, BlockState state);

    /**
     * Gets an array of neighboring machines that are attached to this machine.
     * It is possible that the returned array is empty if there are not neighboring machines.
     * @param world This machine's World
     * @param pos This machine's Block Position
     * @param state This machine's current Block State
     * @return An array of neighboring machines to this machine
     */
    public ArrayList<MechanicalBlockEntity> getNeighbors(Level world, BlockPos pos, BlockState state)
    {
        ArrayList<MechanicalBlockEntity> neighbors = new ArrayList<MechanicalBlockEntity>();
        for (MechanicalContext io : this.getMechanicalConnections(world, pos, state))
        {
            MechanicalBlock neighborBlock = world.getBlockState(io.getPos()).getBlock() instanceof MechanicalBlock ? (MechanicalBlock)world.getBlockState(io.getPos()).getBlock() : null;
            if (neighborBlock != null)
            {
                BlockPos neighborPos = io.getPos();
                BlockState neighborState = world.getBlockState(neighborPos);
                MechanicalBlockEntity neighborTile = neighborBlock.getMechanicalEntity(world, neighborPos, neighborState);
                if (neighborBlock.isAlignedWith(world, neighborPos, neighborState, io) && !neighbors.contains(neighborTile))
                {
                    neighbors.add(neighborTile);
                }
            }
        }
        return neighbors;
    }

    /**
     * Checks if the given Mechanical Input/Output is aligned with this machine.
     * @param world This machine's World
     * @param pos This machine's Block Position
     * @param state This machine's current Block State
     * @param context The Mechanical Input/Output that is being checked for alignment
     * @return True if the given MechanicalContext is aligned with this machine
     */
    public boolean isAlignedWith(Level world, BlockPos pos, BlockState state, MechanicalContext context)
    {
        for (MechanicalContext mc : this.getMechanicalConnections(world, pos, state))
        {
            switch (context.getFacing())
            {
                case UP:
                case DOWN:
                    if (mc.getPos().getX() == context.getPos().getX() && mc.getPos().getZ() == context.getPos().getZ() && mc.getFacing() == context.getFacing().getOpposite() && mc.isAxle() == context.isAxle())
                        return true;
                    else
                        break;

                case WEST:
                case EAST:
                    if (mc.getPos().getY() == context.getPos().getY() && mc.getPos().getZ() == context.getPos().getZ() && mc.getFacing() == context.getFacing().getOpposite() && mc.isAxle() == context.isAxle())
                        return true;
                    else
                        break;

                case NORTH:
                case SOUTH:
                    if (mc.getPos().getX() == context.getPos().getX() && mc.getPos().getY() == context.getPos().getY() && mc.getFacing() == context.getFacing().getOpposite() && mc.isAxle() == context.isAxle())
                        return true;
                    else
                        break;

                default:
                    throw new IllegalStateException("A neighboring machine to '" + this.getDescriptionId() + "' at position (" + pos.toString() + "), is in an unknown state while checking for alignment!");
            }
        }
        return false;
    }

    /**
     * Gets this machine's Block Entity
     * @param world This machine's World
     * @param pos This machine's Block Position
     * @param state This machine's current Block State
     * @return This machine's Mechanical Block Entity
     */
    public MechanicalBlockEntity getMechanicalEntity(LevelAccessor world, BlockPos pos, BlockState state)
    {
        MechanicalBlockEntity tile = world.getBlockEntity(pos) instanceof MechanicalBlockEntity ? (MechanicalBlockEntity)world.getBlockEntity(pos) : null;
        return Objects.requireNonNull(tile, "'MechanicalBlock:" + state.getBlock().getDescriptionId() + "' tile entity must be an instance of MechanicalBlockEntity!");
    }

    /**
     * Gets the Mechanical Block at the given location
     * @param world This machine's World
     * @param pos This machine's Block Position
     * @return The machine's block at the given location or null if there is not a machine present
     */
    @Nullable
    public static MechanicalBlock getMechanicalBlock(LevelAccessor world, BlockPos pos)
    {
        return world.getBlockState(pos).getBlock() instanceof MechanicalBlock ? (MechanicalBlock)world.getBlockState(pos).getBlock() : null;
    }

    @Override
    public void onRemove(BlockState oldState, Level world, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (!world.isClientSide() && newState.isAir())
        {
            NotEnoughMachines.AETHER.removeFromPowerNetwork(getMechanicalEntity(world, pos, oldState));
        }
        super.onRemove(oldState, world, pos, newState, isMoving);
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack)
    {
        //was onBlockPlacedBy()
        if (world.isClientSide())
            return;

        for (MechanicalContext mc : getMechanicalConnections(world, pos, state))
        {
            MechanicalBlock neighborBlock = MechanicalBlock.getMechanicalBlock(world, mc.getPos());
            if (neighborBlock == null)
                continue;
            BlockState neighborState = world.getBlockState(mc.getPos());
            if (!neighborBlock.isAlignedWith(world, mc.getPos(), neighborState, mc))
                continue;
            if (!mc.isAxle())
                world.setBlockAndUpdate(pos, state.setValue(SHIFTED, !neighborState.getValue(SHIFTED)));
            else
                world.setBlockAndUpdate(pos, state.setValue(SHIFTED, neighborState.getValue(SHIFTED)));
            break;
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Override
    public abstract BlockEntity newBlockEntity(BlockPos pos, BlockState state);
}
