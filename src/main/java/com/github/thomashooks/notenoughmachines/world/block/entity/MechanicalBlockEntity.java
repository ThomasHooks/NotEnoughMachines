package com.github.thomashooks.notenoughmachines.world.block.entity;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.MechanicalBlock;
import com.github.thomashooks.notenoughmachines.world.power.IMechanicalPower;
import com.github.thomashooks.notenoughmachines.world.power.MechanicalContext;
import com.github.thomashooks.notenoughmachines.world.power.MechanicalType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

//TODO: move the power feature into a capability
abstract public class MechanicalBlockEntity extends LazyTickingBlockEntity implements IMechanicalPower
{
    protected long networkID = 0;
    private int powerCapacity = 0;
    private int powerLoad = 0;
    protected int networkCapacity = 0;
    protected int networkLoad = 0;
    protected MechanicalType mechanicalType = MechanicalType.SHAFT;
    private float speed = 0.0f;
    protected BlockPos driverPos = null;
    protected final static int VALIDATE_TICK = 20;
    private boolean updateNetwork = false;
    private boolean speedChanged = false;

    protected MechanicalBlockEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state, int startPowerCapacity, int startPowerLoad, MechanicalType type)
    {
        super(entityType, pos, state);
        this.powerCapacity = startPowerCapacity;
        this.powerLoad = startPowerLoad;
        this.mechanicalType = type;
        setLazyTimerAlarm(VALIDATE_TICK);
    }

    @Override
    public void onLoad()
    {
        if (!level.isClientSide())
        {
            NotEnoughMachines.AETHER.joinPowerNetwork(this);
        }
        super.onLoad();
    }

    @Override
    public void setRemoved()
    {
        super.setRemoved();
    }

    @Override
    public void tick()
    {
        if (!isPowered())
        {
            this.speed = 0.0F;
            setChanged();
            syncClient();
        }
        propagateSpeed();

        super.tick();
    }

    @Override
    public void lazyTick()
    {
        if (level.isClientSide())
            return;

        updatePowerNetwork();
    }

    /**
     * Updates this machine's power network if its power state has changed
     */
    private void  updatePowerNetwork()
    {
        if (this.updateNetwork)
        {
            NotEnoughMachines.AETHER.updatePowerNetwork(this);
            this.updateNetwork = false;
            setChanged();
        }
    }

    /**
     * Attempts to drive the next machine that is attached to this machine
     */
    private void propagateSpeed()
    {
        if (!speedChanged)
            return;

        MechanicalBlock block = (MechanicalBlock) getBlockState().getBlock();
        for (MechanicalContext mc : block.getMechanicalConnections(getLevel(), getBlockPos(), getBlockState()))
        {
            MechanicalBlock neighborBlock = MechanicalBlock.getMechanicalBlock(getLevel(), mc.getPos());
            if (neighborBlock == null)
                continue;
            MechanicalBlockEntity neighbor = neighborBlock.getMechanicalEntity(getLevel(), mc.getPos(), getLevel().getBlockState(mc.getPos()));
            if (neighbor.getBlockPos().equals(this.driverPos))
                continue;
            else if (neighborBlock.isAlignedWith(neighbor.getLevel(), neighbor.getBlockPos(), neighbor.getBlockState(), mc))
            {
                if (mc.isAxle())
                    neighbor.changeSpeed(this, this.speed);
                else
                {
                    float gearRatio = getNumberOfTeeth() / neighbor.getNumberOfTeeth();
                    neighbor.changeSpeed(this, this.speed * -gearRatio);
                }
            }
        }
    }

    @Override
    public int getLoad() { return this.mechanicalType != MechanicalType.SOURCE ? this.powerLoad : 0; }

    @Override
    public void setLoad(int newLoad)
    {
        if (newLoad == this.powerLoad || this.mechanicalType == MechanicalType.SOURCE)
            return;

        this.powerLoad = Math.abs(newLoad);
        this.updateNetwork = true;
    }

    @Override
    public int getCapacity()
    {
        return this.mechanicalType == MechanicalType.SOURCE ? this.powerCapacity : 0;
    }

    @Override
    public void setCapacity(int newCapacity)
    {
        if (newCapacity == this.powerCapacity  || this.mechanicalType != MechanicalType.SOURCE)
            return;

        this.powerCapacity = Math.abs(newCapacity);
        this.updateNetwork = true;
    }

    public float getSpeed() { return this.speed; }

    public void changeSpeed(MechanicalBlockEntity driver, float speedIn)
    {
        if (speedIn == this.speed && getLevel().getGameTime() % 10 != 1)
            return;

        this.speed = speedIn;
        this.driverPos = driver.getBlockPos();
        this.speedChanged = true;
        setChanged();
        syncClient();
    }

    @Override
    public boolean isPowered()
    {
        return this.networkCapacity - this.networkLoad > 0;
    }

    @Override
    public MechanicalType getMachineType() { return this.mechanicalType; }

    /**
     * Sets the number of teeth for this machine
     * <p>
     * Note: This method is only used by machines with cog mechanical connections
     * @return The number of teeth of this machine, by default it is 1
     */
    @Override
    public float getNumberOfTeeth() { return 1.0f; }

    @Override
    public long getNetworkID()
    {
        return this.networkID;
    }

    @Override
    public void setNetworkID(long idIn)
    {
        if (idIn > 0 && this.networkID != idIn)
        {
            this.networkID = idIn;
            setChanged();
        }
    }

    @Override
    public void networkUpdate(int currentNetworkCapacity, int currentNetworkLoad)
    {
        this.networkCapacity = currentNetworkCapacity;
        this.networkLoad = currentNetworkLoad;
        setChanged();
        syncClient();
    }

    @Override
    public void load(CompoundTag nbt)
    {
        this.networkID = nbt.getLong("network_id");
        this.powerCapacity = nbt.getInt("capacity");
        this.networkCapacity = nbt.getInt("network_capacity");
        this.powerLoad = nbt.getInt("load");
        this.networkLoad = nbt.getInt("network_load");
        this.speed = nbt.getFloat("speed");
        this.mechanicalType = MechanicalType.byName(nbt.getString("mechanical_type"));
        if (nbt.contains("driverpos"))
            this.driverPos = NbtUtils.readBlockPos(nbt.getCompound("driverpos"));

        super.load(nbt);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt)
    {
        nbt.putLong("network_id", this.networkID);
        nbt.putInt("capacity", this.powerCapacity);
        nbt.putInt("network_capacity", this.networkCapacity);
        nbt.putInt("load", this.powerLoad);
        nbt.putInt("network_load", this.networkLoad);
        nbt.putFloat("speed", this.speed);
        nbt.putString("mechanical_type", this.mechanicalType.getSerializedName());
        if (this.driverPos != null)
            nbt.put("driverpos", NbtUtils.writeBlockPos(this.driverPos));

        super.saveAdditional(nbt);
    }
}
