package com.kilroy790.notenoughmachines.power;

import java.util.Map.Entry;
import java.util.TreeMap;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;

import net.minecraft.util.math.BlockPos;




public class PowerNetwork {

	private long id;

	private int powerCapacity;
	private int powerLoad;

	private TreeMap<BlockPos, MechanicalTile> nodeMap;



	/**
	 * 
	 * @param idIn This power network's ID
	 */
	public PowerNetwork(long idIn) {
		this.nodeMap = new TreeMap<BlockPos, MechanicalTile>();
		this.id = idIn;
		this.powerCapacity = 0;
		this.powerLoad = 0;
	}



	/**
	 * Adds the given machine to this power network
	 * 
	 * @param tile The node to be added to this power network
	 * @param silently If true this action will not trigger a network update
	 */
	public void addNode(MechanicalTile tile, boolean silently) {
		
		if(tile == null) return;	
		
		if(!nodeMap.containsKey(tile.getPos())) {
			nodeMap.put(tile.getPos(), tile);
			NotEnoughMachines.LOGGER.debug("Tile Entity '" + tile.getType().getRegistryName() + "' has been added to the Power Network ID: " + this.id);
			if(!silently) update();
		}
	}
	
	
	
	/**
	 * Removes the given machine from this power network.
	 * 
	 * @param tile The node to be removed from this power network
	 * @param silently If true action this will not trigger a network update
	 */
	public void removeNode(MechanicalTile tile, boolean silently) {
		
		if(tile == null) return;
		
		if(nodeMap.containsKey(tile.getPos())) {
			nodeMap.remove(tile.getPos());
			tile.networkUpdate(0, 0);
			NotEnoughMachines.LOGGER.debug("Tile Entity '" + tile.getType().getRegistryName() + "' has been removed from the Power Network ID: " + this.id);
			if(!silently) update();
		}
	}



	/**
	 * Triggers a power network update, and sends all nodes a message containing the power network's current state.
	 */
	public void update() {

		int load = 0;
		int power = 0;		
		
		for(Entry<BlockPos, MechanicalTile> itr : nodeMap.entrySet()) {
			switch(itr.getValue().getMachineType()) {
			
			case SOURCE:
				power += itr.getValue().getCapacity();
				break;

			case SINK:
				load += itr.getValue().getLoad();
				break;

			default:
				break;
			}
		}
		
		this.powerLoad = load;
		this.powerCapacity = power;
		
		for(Entry<BlockPos, MechanicalTile> itr : nodeMap.entrySet()) {
			itr.getValue().networkUpdate(powerCapacity, powerLoad);
		}
	}
	
	
	
	/**
	 * Merges another power network into this power network.
	 * It will take ownership of the nodes of the other power network,
	 * and updates all nodes on this network.
	 * 
	 * @param other The power network that is to be merged into this power network
	 */
	public void mergeNetwork(PowerNetwork other) {
		
		for(Entry<BlockPos, MechanicalTile> itr : other.nodeMap.entrySet()) {
			itr.getValue().setNetworkID(id);
			addNode(itr.getValue(), true);
		}
		update();
	}
	
	
	
	public int numberOfNodes() {
		return nodeMap.size();
	}



	public int getCapacity() {
		return this.powerCapacity;
	}



	public int getLoad() {
		return this.powerLoad;
	}



	public long getID() {
		return this.id;
	}
}







