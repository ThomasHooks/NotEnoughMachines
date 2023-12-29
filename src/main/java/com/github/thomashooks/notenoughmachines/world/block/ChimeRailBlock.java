package com.github.thomashooks.notenoughmachines.world.block;

import com.github.thomashooks.notenoughmachines.util.KeyboardInputHelper;
import com.github.thomashooks.notenoughmachines.util.ToolTipKeys;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DetectorRailBlock;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChimeRailBlock extends DetectorRailBlock
{
    public static final EnumProperty<NoteBlockInstrument> INSTRUMENT = BlockStateProperties.NOTEBLOCK_INSTRUMENT;
    public static final IntegerProperty NOTE = BlockStateProperties.NOTE;
    public static final float VOLUME = 4.0F;

    public ChimeRailBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(SHAPE, RailShape.NORTH_SOUTH)
                .setValue(POWERED, false)
                .setValue(INSTRUMENT, NoteBlockInstrument.HARP)
                .setValue(NOTE, 0)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter blockGetter, List<Component> toolTips, TooltipFlag flag)
    {
        if (KeyboardInputHelper.isPressingShift())
        {
            toolTips.add(Component.literal(""));
            toolTips.add(Component.translatable(ToolTipKeys.CHIME_RAIL.getTranslation()).withStyle(ChatFormatting.GREEN));
        }
        else
            toolTips.add(Component.translatable(ToolTipKeys.MORE_INFO_PRESS_SHIFT.getTranslation()).withStyle(ChatFormatting.GRAY));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.setInstrument(context.getLevel(), context.getClickedPos(), super.getStateForPlacement(context));
    }

    @Override
    public void onRemove(@NotNull BlockState oldState, @NotNull Level world, @NotNull BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (!newState.isAir())
        {
            if (!oldState.getValue(POWERED) && newState.getValue(POWERED))
                this.playNote((Entity)null, newState, world, pos);
        }
        super.onRemove(oldState, world, pos, newState, isMoving);
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState state, Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor accessor, @NotNull BlockPos pos, @NotNull BlockPos facingPos)
    {
        if (facing.getAxis() == Direction.Axis.Y)
            return this.setInstrument(accessor, pos, super.updateShape(state, facing, facingState, accessor, pos, facingPos));
        else
            return super.updateShape(state, facing, facingState, accessor, pos, facingPos);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult)
    {
        ItemStack itemInHand = player.getItemInHand(hand);
        if ((itemInHand.is(ItemTags.NOTE_BLOCK_TOP_INSTRUMENTS) && hitResult.getDirection() == Direction.UP) || player.isShiftKeyDown())
            return InteractionResult.PASS;
        else if (world.isClientSide)
            return InteractionResult.SUCCESS;
        else
        {
            int noteID = net.minecraftforge.common.ForgeHooks.onNoteChange(world, pos, state, state.getValue(NOTE), state.cycle(NOTE).getValue(NOTE));
            if (noteID == -1)
                return InteractionResult.FAIL;

            state = state.setValue(NOTE, noteID);
            world.setBlock(pos, state, 3);
            this.playNote(player, state, world, pos);
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public void attack(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player)
    {
        if (!world.isClientSide())
            this.playNote(player, state, world, pos);
    }

    @Override
    public boolean triggerEvent(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, int id, int param)
    {
        net.minecraftforge.event.level.NoteBlockEvent.Play event = new net.minecraftforge.event.level.NoteBlockEvent.Play(world, pos, state, state.getValue(NOTE), state.getValue(INSTRUMENT));
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
            return false;

        state = state.setValue(NOTE, event.getVanillaNoteId()).setValue(INSTRUMENT, event.getInstrument());
        NoteBlockInstrument instrument = state.getValue(INSTRUMENT);
        float pitch;
        if (instrument.isTunable())
        {
            int note = state.getValue(NOTE);
            pitch = NoteBlock.getPitchFromNote(note);
            world.addParticle(ParticleTypes.NOTE, (double)pos.getX() + 0.5D, (double)pos.getY() + 1.2D, (double)pos.getZ() + 0.5D, (double)note / 24.0D, 0.0D, 0.0D);
        }
        else
            pitch = 1.0F;

        Holder<SoundEvent> holder;
        if (instrument.hasCustomSound())
        {
            ResourceLocation resourcelocation = this.getCustomSoundId(world, pos);
            if (resourcelocation == null)
                return false;

            holder = Holder.direct(SoundEvent.createVariableRangeEvent(resourcelocation));
        }
        else
            holder = instrument.getSoundEvent();

        world.playSeededSound((Player)null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, holder, SoundSource.RECORDS, VOLUME, pitch, world.random.nextLong());
        return true;
    }

    protected BlockState setInstrument(LevelAccessor worldAccessor, BlockPos pos, BlockState state)
    {
        NoteBlockInstrument blockInstrument = worldAccessor.getBlockState(pos.above()).instrument();
        if (blockInstrument.worksAboveNoteBlock())
            return state.setValue(INSTRUMENT, blockInstrument);
        else
        {
            NoteBlockInstrument instrument = worldAccessor.getBlockState(pos.below()).instrument();
            return state.setValue(INSTRUMENT, instrument.worksAboveNoteBlock() ? NoteBlockInstrument.HARP : instrument);
        }
    }

    protected void playNote(@Nullable Entity entity, BlockState state, Level world, BlockPos pos)
    {
        world.blockEvent(pos, this, 0, 0); //eventID and eventParam
        world.gameEvent(entity, GameEvent.NOTE_BLOCK_PLAY, pos);
    }

    @Nullable
    protected ResourceLocation getCustomSoundId(Level world, BlockPos pos)
    {
        BlockEntity blockentity = world.getBlockEntity(pos.above());
        if (blockentity instanceof SkullBlockEntity skullblockentity)
            return skullblockentity.getNoteBlockSound();
        else
            return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(SHAPE, POWERED, INSTRUMENT, NOTE, WATERLOGGED);
    }
}
