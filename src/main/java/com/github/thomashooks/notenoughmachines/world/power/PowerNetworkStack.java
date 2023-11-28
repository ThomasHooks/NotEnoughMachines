package com.github.thomashooks.notenoughmachines.world.power;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import com.github.thomashooks.notenoughmachines.world.block.MechanicalBlock;
import com.github.thomashooks.notenoughmachines.world.block.entity.MechanicalBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.LevelAccessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PowerNetworkStack
{
    private static Map<LevelAccessor, Map<Long, PowerNetwork>> dimensionNetworks = new HashMap<>();
    private static long nextID = 1;

    public void onWorldLoad(LevelAccessor worldIn)
    {
        dimensionNetworks.put(worldIn, new HashMap<>());
        NotEnoughMachines.LOGGER.debug("Creating Power Network space for world " + worldIn.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE).getKey(worldIn.dimensionType()));
    }

    public void onWorldUnload(LevelAccessor worldIn)
    {
        dimensionNetworks.remove(worldIn);
        NotEnoughMachines.LOGGER.debug("Removing Power Network space for world " + worldIn.registryAccess().registryOrThrow(Registries.DIMENSION_TYPE).getKey(worldIn.dimensionType()));
    }

    /**
     * @param worldIn The world that has the power network
     * @param networkID The ID of the power network
     * @return True if the given World has a power network with the given ID
     */
    public boolean hasPowerNetwork(LevelAccessor worldIn, long networkID)
    {
        return dimensionNetworks.get(worldIn).containsKey(networkID);
    }

    /**
     * @param entity The mechanical tile that is joining a power network
     * @return The power network's ID, or 0 if the tile is null
     */
    public void joinPowerNetwork(MechanicalBlockEntity entity)
    {
        if (entity == null)
            return;

        long networkID = entity.getNetworkID();
        Map<Long, PowerNetwork> worldPowerNetworks = dimensionNetworks.get(entity.getLevel());
        if (networkID != 0)
            addBackToPowerNetwork(entity, worldPowerNetworks);

        else
            networkID = createOrAddToPowerNetwork(entity, worldPowerNetworks);

        entity.setNetworkID(networkID);
    }

    private void addBackToPowerNetwork(MechanicalBlockEntity entity, Map<Long, PowerNetwork> worldPowerNetworks)
    {
        long networkID = entity.getNetworkID();
        PowerNetwork network = worldPowerNetworks.get(networkID);
        if (network == null)
        {
            network = new PowerNetwork(networkID, entity.getLevel());
            worldPowerNetworks.put(networkID, network);
        }
        network.addNode(entity, true);
    }

    private long createOrAddToPowerNetwork(MechanicalBlockEntity entity, Map<Long, PowerNetwork> worldPowerNetworks)
    {
        long networkID = 0;
        MechanicalBlock block = (MechanicalBlock)entity.getBlockState().getBlock();
        ArrayList<MechanicalBlockEntity> neighbors = block.getNeighbors(entity.getLevel(), entity.getBlockPos(), entity.getBlockState());

        //Merge all neighboring machines into one power network
        if (neighbors.size() > 1)
        {
            ArrayList<PowerNetwork> networks = new ArrayList<PowerNetwork>();
            for (MechanicalBlockEntity neighbor : neighbors)
            {
                PowerNetwork network = worldPowerNetworks.get(neighbor.getNetworkID());
                if (!networks.contains(network))
                    networks.add(network);
            }
            mergePowerNetworks(worldPowerNetworks, networks, entity.getLevel());
        }

        //Join the power network of the first neighboring machine that is aligned with this machine
        for (MechanicalBlockEntity neighbor : neighbors)
        {
            if (neighbor != null)
            {
                networkID = neighbor.getNetworkID();
                worldPowerNetworks.get(networkID).addNode(entity, false);
                break;
            }
        }

        //Because there are no neighboring machines create a new power network
        if (networkID == 0)
        {
            //Because power networks are not saved in chunk or world data
            //it is possible for a new power network to overwrite an older
            //power network after the world has been reloaded
            while (true)
            {
                networkID = nextID++;
                if (!worldPowerNetworks.containsKey(networkID))
                    break;
            }
            //networkID = entity.getBlockPos().asLong();

            PowerNetwork network = new PowerNetwork(networkID, entity.getLevel());
            network.addNode(entity, false);
            worldPowerNetworks.put(networkID, network);
        }
        return networkID;
    }

    /**
     * Merges multiple power networks into one new power network.
     * @param networks An array of networks that are to be merged
     * @param worldPowerNetworks A map of a world's power networks
     */
    private void mergePowerNetworks(Map<Long, PowerNetwork> worldPowerNetworks, ArrayList<PowerNetwork> networks, LevelAccessor world)
    {
        if (networks.size() <= 1)
            return;

        long networkID = nextID++;
        PowerNetwork network = new PowerNetwork(networkID, world);
        for (PowerNetwork other : networks)
        {
            network.mergeNetwork(other);
            worldPowerNetworks.remove(other.getID());
        }
        worldPowerNetworks.put(networkID, network);
    }

    /**
     * Removes the given machine from its current power network
     * @param entity The machine that is to be removed from its power network
     */
    public void removeFromPowerNetwork(MechanicalBlockEntity entity)
    {
        if (entity == null)
            return;

        if (entity.getNetworkID() > 0)
        {
            PowerNetwork network = dimensionNetworks.get(entity.getLevel()).get(entity.getNetworkID());
            MechanicalBlock block = (MechanicalBlock)entity.getBlockState().getBlock();
            ArrayList<MechanicalBlockEntity> neighbors = block.getNeighbors(entity.getLevel(), entity.getBlockPos(), entity.getBlockState());
            //When a machine only has one neighboring machine it must be along the edge of its network
            if (neighbors.size() > 1)
            {
                //Because this machine has more than one neighboring machine
                //the power network it belongs to must be split into multiple power networks
                splitPowerNetwork(network, entity, neighbors);
            }
            network.removeNode(entity, false);
        }
    }

    /**
     * Splits the prime power network into multiple power networks.
     * @param networkPrime The power network that the machine is part of
     * @param entityOrigin The block entity that is the origin of the network split
     * @param neighbors The machines that are neighboring origin machine
     */
    private void splitPowerNetwork(PowerNetwork networkPrime, MechanicalBlockEntity entityOrigin, ArrayList<MechanicalBlockEntity> neighbors)
    {
        //Find all subnetworks that are attached to the machine
        ArrayList<ArrayList<MechanicalBlockEntity>> subnetworks = new ArrayList<ArrayList<MechanicalBlockEntity>>();
        for (MechanicalBlockEntity tileNext : neighbors)
        {
            ArrayList<MechanicalBlockEntity> subnetwork = new ArrayList<MechanicalBlockEntity>();
            findMachinesInSubnetwork(subnetwork, entityOrigin, tileNext);
            subnetworks.add(subnetwork);
        }

        //The subnetwork with the most members will keep the original power network's ID
        int indexOfLargestSubnetwork = 0;
        for (int i = 0; i < subnetworks.size(); i++)
        {
            if (subnetworks.get(i).size() > subnetworks.get(indexOfLargestSubnetwork).size())
                indexOfLargestSubnetwork = i;
        }

        //All other subnetworks will be put into their own new power network and removed from the prime power network
        Map<Long, PowerNetwork> worldPowerNetworks = dimensionNetworks.get(entityOrigin.getLevel());
        for (int i = 0; i < subnetworks.size(); i++)
        {
            if (i == indexOfLargestSubnetwork)
                continue;

            PowerNetwork network = createNetworkFromSubnetwork(networkPrime, subnetworks.get(i), entityOrigin.getLevel());
            worldPowerNetworks.put(network.getID(), network);
            network.update();
        }
    }

    /**
     * Creates an array of machines by recursively looping through a power subnetwork
     * @param subnetwork The array to populate with machines
     * @param entityFrom The starting machine
     * @param entityCurrent the current machine
     */
    private void findMachinesInSubnetwork(ArrayList<MechanicalBlockEntity> subnetwork, MechanicalBlockEntity entityFrom, MechanicalBlockEntity entityCurrent)
    {
        if (subnetwork.contains(entityCurrent))
            return;

        subnetwork.add(entityCurrent);

        MechanicalBlock block = (MechanicalBlock)entityCurrent.getBlockState().getBlock();
        ArrayList<MechanicalBlockEntity> neighbors = block.getNeighbors(entityCurrent.getLevel(), entityCurrent.getBlockPos(), entityCurrent.getBlockState());
        for (MechanicalBlockEntity tileNext : neighbors)
        {

            if (tileNext == entityFrom)
                continue;

            findMachinesInSubnetwork(subnetwork, entityCurrent, tileNext);
        }
    }

    /**
     * Creates a new power network from a subnetwork of the given prime power network.
     * @param networkPrime The prime network of the given subnetwork
     * @param subnetwork An array of machines that are part of a subnetwork of the prime network
     * @return A new power network that was a subnetwork of the given prime network
     */
    private PowerNetwork createNetworkFromSubnetwork(PowerNetwork networkPrime, ArrayList<MechanicalBlockEntity> subnetwork, LevelAccessor world)
    {
        long networkID = nextID++;
        PowerNetwork network = new PowerNetwork(networkID, world);
        for (MechanicalBlockEntity tile : subnetwork)
        {
            network.addNode(tile, true);
            tile.setNetworkID(networkID);
            networkPrime.removeNode(tile, true);
        }
        return network;
    }



    /**
     * Triggers a power network update for the power network that given machine is attached to.
     * Because this will cause the entire power network to be iterated over it should not be call every tick.
     * @param entity The machine which is triggering a power network update
     */
    public void updatePowerNetwork(MechanicalBlockEntity entity)
    {
        if (entity == null)
            return;

        if (entity.getNetworkID() > 0)
            dimensionNetworks.get(entity.getLevel()).get(entity.getNetworkID()).update();
    }
}
