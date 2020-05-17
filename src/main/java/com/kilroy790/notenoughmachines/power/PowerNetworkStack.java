package com.kilroy790.notenoughmachines.power;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;

import net.minecraft.world.IWorld;




public class PowerNetworkStack {
	
	private static Map<IWorld, Map<Long, PowerNetwork>> universePowerNetworks = new HashMap<>();
	private static long nextID = 1;
	
	
	
	public void onWorldLoad(IWorld worldIn) {
		NotEnoughMachines.logger.info("Creating Power Network space for world " + worldIn.getDimension().getType().getRegistryName());
		universePowerNetworks.put(worldIn, new HashMap<>());
	}
	
	
	
	public void onWorldUnload(IWorld worldIn) {
		NotEnoughMachines.logger.info("Removing Power Network space for world " + worldIn.getDimension().getType().getRegistryName());
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
		
		if(tile == null) return;
		
		long networkID = tile.getNetworkID();
		Map<Long, PowerNetwork> worldPowerNetworks = universePowerNetworks.get(tile.getWorld());
		if(networkID != 0) addBackToPowerNetwork(tile, worldPowerNetworks);
		else networkID = createOrAddToPowerNetwork(tile, worldPowerNetworks);
		
		tile.setNetworkID(networkID, false);
	}
	
	
	
	/**
	 * 
	 * @param tile
	 * @param worldPowerNetworks
	 */
	private void addBackToPowerNetwork(MechanicalTile tile, Map<Long, PowerNetwork> worldPowerNetworks) {
		
		long networkID = tile.getNetworkID();
		PowerNetwork network = worldPowerNetworks.get(networkID);
		if( network == null) {
			network = new PowerNetwork(networkID);
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
		ArrayList<MechanicalTile> neighbors = tile.getNeighbors();
		
		//Merge all neighboring machines into one power network
		if(neighbors.size() > 1) {
			ArrayList<PowerNetwork> networks = new ArrayList<PowerNetwork>();
			for(MechanicalTile neighbor : neighbors) {
				PowerNetwork network = worldPowerNetworks.get(neighbor.getNetworkID());
				if(!networks.contains(network)) networks.add(network);
			}
			mergePowerNetworks(worldPowerNetworks, networks);
		}
		
		//Join the power network of the first neighboring machine that is aligned with this machine
		for(MechanicalTile neighbor : neighbors) {
			if(neighbor != null) {
				networkID = neighbor.getNetworkID();
				worldPowerNetworks.get(networkID).addNode(tile, false);
				break;
			}
		}
		//Because there are no neighboring machines create a new power network
		if(networkID == 0) {
			networkID = nextID++;
			PowerNetwork network = new PowerNetwork(networkID);
			network.addNode(tile, false);
			worldPowerNetworks.put(networkID, network);
		}
		return networkID;
	}

		
	//TODO: optimize
	/**
	 * Merges multiple power networks into one new power network.
	 * 
	 * @param networks An array of networks that are to be merged
	 * @param worldPowerNetworks A map of a world's power networks
	 */
	private void mergePowerNetworks(Map<Long, PowerNetwork> worldPowerNetworks, ArrayList<PowerNetwork> networks) {
		
		if(networks.size() <= 1) return;
		
		long networkID = nextID++;
		PowerNetwork network = new PowerNetwork(networkID);
		for(PowerNetwork other : networks) {
			network.mergeNetwork(other);
			worldPowerNetworks.remove(other.getID());
		}
		worldPowerNetworks.put(networkID, network);
	}
	
	
	
	/**
	 * 
	 * @param tile
	 * @param isSilent
	 */
	public void removeFromPowerNetwork(MechanicalTile tile, boolean isSilent) {
		if(tile == null) return;
		//TODO: add network splitting
		if(tile.getNetworkID() > 0) universePowerNetworks.get(tile.getWorld()).get(tile.getNetworkID()).removeNode(tile, isSilent);
	}
	
	
	//TODO
	/**
	 * 
	 * @param network
	 */
	public void splitPowerNetwork(PowerNetwork network) {}
	
	
	
	/**
	 * 
	 * @param tile
	 */
	public void updatePowerNetwork(MechanicalTile tile) {
		if(tile == null) return;
		if(tile.getNetworkID() > 0) universePowerNetworks.get(tile.getWorld()).get(tile.getNetworkID()).update();
	}
}







