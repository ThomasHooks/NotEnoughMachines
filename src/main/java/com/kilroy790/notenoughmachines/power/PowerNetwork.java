package com.kilroy790.notenoughmachines.power;

import java.util.ArrayList;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;




public class PowerNetwork {

	private long id;

	private int powerCapacity;
	private int powerLoad;

	private ArrayList<MechanicalTile> nodeArray;



	public PowerNetwork(long idIn) {
		this.nodeArray = new ArrayList<MechanicalTile>();
		this.id = idIn;
		this.powerCapacity = 0;
		this.powerLoad = 0;
	}



	/**
	 * 
	 * @param tile
	 * @param isSilent
	 */
	public void addNode(MechanicalTile tile, boolean isSilent) {
		
		if(tile == null) return;
		
		if(!nodeArray.contains(tile)) {
			nodeArray.add(tile);
			NotEnoughMachines.logger.debug("Tile Entity '" + tile.getType().getRegistryName() + "' has been added to the Power Network ID: " + this.id);
			if(!isSilent) update();
		}
		else NotEnoughMachines.logger.warn("Tile Entity '" + tile.getType().getRegistryName() + "' has already been added to the Power Network ID:" + this.id);
	}
	
	
	
	/**
	 * 
	 * @param tile
	 * @param isSilent
	 */
	public void removeNode(MechanicalTile tile, boolean isSilent) {
		
		if(tile == null) return;
		
		if(nodeArray.contains(tile)) {
			nodeArray.remove(tile);
			tile.networkUpdate(0, 0);
			if(!isSilent) update();
		}
	}



	/**
	 * 
	 */
	public void update() {

		int load = 0;
		int power = 0;
		for(MechanicalTile tile : nodeArray) {

			switch(tile.getMechType()) {
			
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
		syncNodes();
	}
	
	
	
	private void syncNodes() {
		for(MechanicalTile tile : nodeArray) {
			tile.networkUpdate(this.powerCapacity, this.powerLoad);
		}
	}
	
	
	
	/**
	 * Merges another power network into this power network.
	 * 
	 * @param other The power network that is to be merged into this power network
	 */
	public void mergeNetwork(PowerNetwork other) {
		
		for(MechanicalTile tile : other.nodeArray) {
			tile.setNetworkID(this.id, true);
			addNode(tile, true);
		}
		update();
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







