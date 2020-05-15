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



	public void addNode(MechanicalTile te, boolean addSilently) {
		
		if(te == null) return;
		
		if(!nodeArray.contains(te)) {
			nodeArray.add(te);
			NotEnoughMachines.logger.debug("Tile Entity '" + te.getType().getRegistryName() + "' has been added to the Power Network ID: " + this.id);
			if(!addSilently) updateNetwork();
		}
		else NotEnoughMachines.logger.warn("Tile Entity '" + te.getType().getRegistryName() + "' has already been added to the Power Network ID:" + this.id);
	}
	
	
	
	public void removeNode(MechanicalTile te) {
		
		if(te == null) return;
		
		if(nodeArray.contains(te)) {
			nodeArray.remove(te);
			te.networkUpdate(0, 0);
			updateNetwork();
		}
	}



	private void updateNetwork() {

		int load = 0;
		int power = 0;
		for(MechanicalTile te : nodeArray) {

			switch(te.getMechType()) {
			
			case SOURCE:
				power += te.getCapacity();
				break;

			case SINK:
				load += te.getLoad();
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
		for(MechanicalTile te : nodeArray) {
			te.networkUpdate(this.powerCapacity, this.powerLoad);
		}
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







