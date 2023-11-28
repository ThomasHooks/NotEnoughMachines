package com.github.thomashooks.notenoughmachines.world.power;

import com.github.thomashooks.notenoughmachines.world.block.entity.MechanicalBlockEntity;
import net.minecraft.world.level.LevelAccessor;

import java.util.ArrayList;

public class PowerNetwork
{
    private final long id;
    private final LevelAccessor world;
    private int powerCapacity;
    private int powerLoad;
    private ArrayList<MechanicalBlockEntity> nodes = new ArrayList<MechanicalBlockEntity>();

    public PowerNetwork(long idIn, LevelAccessor worldIn)
    {
        this.id = idIn;
        this.world = worldIn;
        this.powerCapacity = 0;
        this.powerLoad = 0;
    }

    /**
     * Adds the given machine to this power network
     * @param entity The node to be added to this power network
     * @param silently If true this action will not trigger a network update
     */
    public void addNode(MechanicalBlockEntity entity, boolean silently)
    {
        if (!nodes.contains(entity))
        {
            nodes.add(entity);
            if (!silently)
                update();
        }
    }

    /**
     * Removes the given machine from this power network.
     * @param entity The node to be removed from this power network
     * @param silently If true action this will not trigger a network update
     */
    public void removeNode(MechanicalBlockEntity entity, boolean silently)
    {
        if (nodes.contains(entity))
        {
            nodes.remove(entity);
            if (!silently)
                update();
        }
    }

    /**
     * Triggers a power network update, and sends all nodes a message containing the power network's current state.
     */
    public void update()
    {
        int load = 0;
        int power = 0;
        for (MechanicalBlockEntity entities : nodes)
        {
            switch(entities.getMachineType())
            {
                case SOURCE:
                    power += entities.getCapacity();
                    break;

                case SINK:
                    load += entities.getLoad();
                    break;

                default:
                    break;
            }
        }
        this.powerLoad = load;
        this.powerCapacity = power;
        for (MechanicalBlockEntity entities : nodes)
        {
            entities.networkUpdate(powerCapacity, powerLoad);
        }
    }

    /**
     * Merges another power network into this power network.
     * It will take ownership of the nodes of the other power network,
     * and updates all nodes on this network.
     * @param other The power network that is to be merged into this power network
     */
    public void mergeNetwork(PowerNetwork other)
    {
        for (MechanicalBlockEntity entity : other.nodes)
        {
            entity.setNetworkID(id);
            addNode(entity, true);
        }
        update();
    }

    public int numberOfNodes() { return nodes.size(); }

    public int getCapacity() { return this.powerCapacity; }

    public int getLoad() { return this.powerLoad; }

    public long getID() { return this.id; }

    public LevelAccessor getWorld() { return this.world; }
}
