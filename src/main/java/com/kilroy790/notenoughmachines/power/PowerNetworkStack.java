package com.kilroy790.notenoughmachines.power;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.blocks.machines.MechanicalBlock;
import com.kilroy790.notenoughmachines.tiles.MechanicalTile;

import net.minecraft.world.IWorld;




public class PowerNetworkStack {
	
	private static Map<IWorld, Map<Long, PowerNetwork>> universePowerNetworks = new HashMap<>();
	private static long nextID = 1;
	
	public void onWorldLoad(IWorld worldIn) {
		NotEnoughMachines.LOGGER.info("Creating Power Network space for world " + worldIn.getDimension().getType().getRegistryName());
		universePowerNetworks.put(worldIn, new HashMap<>());
	}
	
	
	
	public void onWorldUnload(IWorld worldIn) {
		NotEnoughMachines.LOGGER.info("Removing Power Network space for world " + worldIn.getDimension().getType().getRegistryName());
		universePowerNetworks.remove(worldIn);
	}
	
	
	
	/**
	 * 
	 * @param worldIn The world that has the power network
	 * @param networkID The ID of the power network
	 * 
	 * @return True if the given World has a power network with the given ID
	 */
	public boolean hasPowerNetwork(IWorld worldIn, long networkID) {
		return universePowerNetworks.get(worldIn).containsKey(networkID);
	}
	
	

	/**
	 * 
	 * @param tile The mechanical tile that is joining a power network
	 * 
	 * @return The power network's ID, or 0 if the tile is null
	 */
	public void joinPowerNetwork(MechanicalTile tile) {
		
		if (tile == null) return;
		
		long networkID = tile.getNetworkID();
		Map<Long, PowerNetwork> worldPowerNetworks = universePowerNetworks.get(tile.getWorld());
		if (networkID != 0) addBackToPowerNetwork(tile, worldPowerNetworks);
		
		else networkID = createOrAddToPowerNetwork(tile, worldPowerNetworks);
		
		tile.setNetworkID(networkID);
	}
	
	
	
	/**
	 * 
	 * @param tile
	 * @param worldPowerNetworks
	 */
	private void addBackToPowerNetwork(MechanicalTile tile, Map<Long, PowerNetwork> worldPowerNetworks) {
		
		long networkID = tile.getNetworkID();
		PowerNetwork network = worldPowerNetworks.get(networkID);
		if (network == null) {
			network = new PowerNetwork(networkID, tile.getWorld());
			worldPowerNetworks.put(networkID, network);
		}
		network.addNode(tile, true);
	}
	
	
	
	/**
	 * 
	 * @param tile
	 * @param worldPowerNetworks
	 * 
	 * @return
	 */
	private long createOrAddToPowerNetwork(MechanicalTile tile, Map<Long, PowerNetwork> worldPowerNetworks) {
		
		long networkID = 0;
		MechanicalBlock block = (MechanicalBlock)tile.getBlockState().getBlock();
		ArrayList<MechanicalTile> neighbors = block.getNeighbors(tile.getWorld(), tile.getPos(), tile.getBlockState());
		
		//Merge all neighboring machines into one power network
		if (neighbors.size() > 1) {
			ArrayList<PowerNetwork> networks = new ArrayList<PowerNetwork>();
			for (MechanicalTile neighbor : neighbors) {
				PowerNetwork network = worldPowerNetworks.get(neighbor.getNetworkID());
				if (!networks.contains(network)) networks.add(network);
			}
			mergePowerNetworks(worldPowerNetworks, networks, tile.getWorld());
		}
		
		//Join the power network of the first neighboring machine that is aligned with this machine
		for (MechanicalTile neighbor : neighbors) {
			if (neighbor != null) {
				networkID = neighbor.getNetworkID();
				worldPowerNetworks.get(networkID).addNode(tile, false);
				break;
			}
		}
		
		//Because there are no neighboring machines create a new power network
		if (networkID == 0) {
			//Because power networks are not saved in chunk or world data 
			//it is possible for a new power network to overwrite an older 
			//power network after the world has been reloaded
			while (true) {
				networkID = nextID++;
				if (!worldPowerNetworks.containsKey(networkID)) break;
			}
			
			PowerNetwork network = new PowerNetwork(networkID, tile.getWorld());
			network.addNode(tile, false);
			worldPowerNetworks.put(networkID, network);
		}
		return networkID;
	}
	
	
	
	/**
	 * Merges multiple power networks into one new power network.
	 * 
	 * @param networks An array of networks that are to be merged
	 * @param worldPowerNetworks A map of a world's power networks
	 */
	private void mergePowerNetworks(Map<Long, PowerNetwork> worldPowerNetworks, ArrayList<PowerNetwork> networks, IWorld world) {
		
		if (networks.size() <= 1) return;
		
		long networkID = nextID++;
		PowerNetwork network = new PowerNetwork(networkID, world);
		for (PowerNetwork other : networks) {
			network.mergeNetwork(other);
			worldPowerNetworks.remove(other.getID());
		}
		worldPowerNetworks.put(networkID, network);
	}
	
	
	
	/**
	 * Removes the given machine from its current power network
	 * 
	 * @param tile The machine that is to be removed from its power network
	 */
	public void removeFromPowerNetwork(MechanicalTile tile) {
		
		if (tile == null) return;
		
		if (tile.getNetworkID() > 0) {
			
			PowerNetwork network = universePowerNetworks.get(tile.getWorld()).get(tile.getNetworkID());
			MechanicalBlock block = (MechanicalBlock)tile.getBlockState().getBlock();
			ArrayList<MechanicalTile> neighbors = block.getNeighbors(tile.getWorld(), tile.getPos(), tile.getBlockState());
			//When a machine only has one neighboring machine it must be along the edge of its network
			if (neighbors.size() > 1) {
				//Because this machine has more than one neighboring machine 
				//the power network it belongs to must be split into multiple power networks
				splitPowerNetwork(network, tile, neighbors);
			}
			
			network.removeNode(tile, false);
		}
	}
	
	
	
	/**
	 * Splits the prime power network into multiple power networks.
	 * 
	 * @param networkPrime The power network that the machine is part of
	 * @param tileOrigin The tile that is the origin of the network split
	 * @param neighbors The machines that are neighboring origin machine
	 */
	private void splitPowerNetwork(PowerNetwork networkPrime, MechanicalTile tileOrigin, ArrayList<MechanicalTile> neighbors) {
				
		//Find all subnetworks that are attached to the machine
		ArrayList<ArrayList<MechanicalTile>> subnetworks = new ArrayList<ArrayList<MechanicalTile>>();
		for (MechanicalTile tileNext : neighbors) {
			
			ArrayList<MechanicalTile> subnetwork = new ArrayList<MechanicalTile>();
			findMachinesInSubnetwork(subnetwork, tileOrigin, tileNext);
			subnetworks.add(subnetwork);
		}
		
		//The subnetwork with the most members will keep the original power network's ID
		int indexOfLargestSubnetwork = 0;
		for (int i = 0; i < subnetworks.size(); i++) {
			if (subnetworks.get(i).size() > subnetworks.get(indexOfLargestSubnetwork).size()) indexOfLargestSubnetwork = i;
		}
		
		//All other subnetworks will be put into their own new power network and removed from the prime power network
		Map<Long, PowerNetwork> worldPowerNetworks = universePowerNetworks.get(tileOrigin.getWorld());
		for (int i = 0; i < subnetworks.size(); i++) {
			
			if (i == indexOfLargestSubnetwork) continue;
			
			PowerNetwork network = createNetworkFromSubnetwork(networkPrime, subnetworks.get(i), tileOrigin.getWorld());
			worldPowerNetworks.put(network.getID(), network);
			network.update();
		}
	}
	
	
	
	/**
	 * Creates an array of machines by recursively looping through a power subnetwork
	 * 
	 * @param subnetwork The array to populate with machines
	 * @param tileFrom The starting machine
	 * @param tileCurrent the current machine
	 */
	private void findMachinesInSubnetwork(ArrayList<MechanicalTile> subnetwork, MechanicalTile tileFrom, MechanicalTile tileCurrent) {
		
		if (subnetwork.contains(tileCurrent)) return;
		
		subnetwork.add(tileCurrent);

		MechanicalBlock block = (MechanicalBlock)tileCurrent.getBlockState().getBlock();
		ArrayList<MechanicalTile> neighbors = block.getNeighbors(tileCurrent.getWorld(), tileCurrent.getPos(), tileCurrent.getBlockState());
		for (MechanicalTile tileNext : neighbors) {
			
			if (tileNext == tileFrom) continue;
			
			findMachinesInSubnetwork(subnetwork, tileCurrent, tileNext);
		}
	}
	
	
	
	/**
	 * Creates a new power network from a subnetwork of the given prime power network.
	 * 
	 * @param networkPrime The prime network of the given subnetwork
	 * @param subnetwork An array of machines that are part of a subnetwork of the prime network
	 * 
	 * @return A new power network that was a subnetwork of the given prime network
	 */
	private PowerNetwork createNetworkFromSubnetwork(PowerNetwork networkPrime, ArrayList<MechanicalTile> subnetwork, IWorld world) {
		
		long networkID = nextID++;
		PowerNetwork network = new PowerNetwork(networkID, world);
		for (MechanicalTile tile : subnetwork) {
			
			network.addNode(tile, true);
			tile.setNetworkID(networkID);
			networkPrime.removeNode(tile, true);
		}
		return network;
	}
	
	
	
	/**
	 * Triggers a power network update for the power network that given machine is attached to.
	 * Because this will cause the entire power network to be iterated over it should not be call every tick.
	 * 
	 * @param tile The machine which is triggering a power network update
	 */
	public void updatePowerNetwork(MechanicalTile tile) {
		
		if (tile == null) return;
		
		if (tile.getNetworkID() > 0) universePowerNetworks.get(tile.getWorld()).get(tile.getNetworkID()).update();
	}
}







