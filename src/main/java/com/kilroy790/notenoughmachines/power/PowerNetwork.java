package com.kilroy790.notenoughmachines.power;

import java.util.ArrayList;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.tiles.MechanicalTile;

import net.minecraft.world.IWorld;




public class PowerNetwork {

	private long id;
	private IWorld world;
	private int powerCapacity;
	private int powerLoad;
	private ArrayList<MechanicalTile> nodes;

	public PowerNetwork(long idIn, IWorld worldIn) {
		this.nodes = new ArrayList<MechanicalTile>();
		this.id = idIn;
		this.world = worldIn;
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
		if (!nodes.contains(tile)) {
			nodes.add(tile);
			NotEnoughMachines.LOGGER.debug("Tile entity '" + tile.getType().getRegistryName() + "' has been added to the Power Network ID: " + this.id);
			if (!silently) update();
		}
	}
	
	
	
	/**
	 * Removes the given machine from this power network.
	 * 
	 * @param tile The node to be removed from this power network
	 * @param silently If true action this will not trigger a network update
	 */
	public void removeNode(MechanicalTile tile, boolean silently) {
		if (nodes.contains(tile)) {
			nodes.remove(tile);
			NotEnoughMachines.LOGGER.debug("Tile Entity '" + tile.getType().getRegistryName() + "' has been removed from the Power Network ID: " + this.id);
			if (!silently) update();
		}
	}
	
	
	
	/**
	 * Triggers a power network update, and sends all nodes a message containing the power network's current state.
	 */
	public void update() {
		int load = 0;
		int power = 0;
		for (MechanicalTile tile : nodes) {
			switch(tile.getMachineType()) {

			case SOURCE:
				power += tile.getCapacity();
				break;
				
			case SINK:
				load += tile.getLoad();
				break;
				
			default:
				break;
			}
		}
		this.powerLoad = load;
		this.powerCapacity = power;
		for (MechanicalTile tile : nodes) {
			tile.networkUpdate(powerCapacity, powerLoad);
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
		for (MechanicalTile tile : other.nodes) {
			tile.setNetworkID(id);
			addNode(tile, true);
		}
		update();
	}
	
	
	
	public int numberOfNodes() {
		return nodes.size();
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
	
	
	
	public IWorld getWorld() {
		return this.world;
	}
}







