package com.kilroy790.notenoughmachines.power;

import java.util.HashMap;
import java.util.Map;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;




public class PowerNetworkStack {
	
	private static Map<IWorld, Map<Long, PowerNetwork>> universePowerNetworks = new HashMap<>();
	private static long nextID = 1;
	
	
	
	public void onWorldLoad(IWorld worldIn) {
		universePowerNetworks.put(worldIn, new HashMap<>());
		NotEnoughMachines.logger.info("Creating Power Networks for world " + worldIn.getDimension().getType().getRegistryName());
	}
	
	
	
	public void onWorldUnload(IWorld worldIn) {
		universePowerNetworks.remove(worldIn);
		NotEnoughMachines.logger.info("Removing Power Networks for world " + worldIn.getDimension().getType().getRegistryName());
	}
	
	
	
	public long joinPowerNetwork(MechanicalTile te) {
		
		long networkID = te.getNetworkID();
		World world = te.getWorld();
		Map<Long, PowerNetwork> worldPowerNetworks = universePowerNetworks.get(world);
		
		if(networkID != 0) {
			PowerNetwork network = worldPowerNetworks.get(networkID);
			if( network == null) {
				network = new PowerNetwork(networkID);
				worldPowerNetworks.put(networkID, network);
			}
			network.addNode(te);
		}
		else networkID = addToPowerNetwork(te);
		return networkID;
	}
	
	
	
	private long addToPowerNetwork(MechanicalTile te) {
		
		long networkID = 0;
		World world = te.getWorld();
		Map<Long, PowerNetwork> worldPowerNetworks = universePowerNetworks.get(world);
		
		//TODO: look into changing this
		//TODO: add network merging 
		for(MechanicalInputOutputWrapper io : te.getMechIO()) {
			TileEntity otherTE = world.getTileEntity(io.getPos());
			MechanicalTile mechTE = otherTE instanceof MechanicalTile ? (MechanicalTile)otherTE : null;
			if(mechTE != null) {
				for(MechanicalInputOutputWrapper io2 : mechTE.getMechIO()) {
					if(io2.getPos() == te.getPos()) {
						networkID = mechTE.getNetworkID();
						worldPowerNetworks.get(networkID).addNode(te);
						break;
					}
				}
			}
			if(networkID != 0) break;
		}
		if(networkID == 0) {
			networkID = nextID++;
			PowerNetwork network = new PowerNetwork(networkID);
			network.addNode(te);
			worldPowerNetworks.put(networkID, network);
		}
		return networkID;
	}
	
	
	
	public void removeFromPowerNetwork(MechanicalTile te) {
		universePowerNetworks.get(te.getWorld()).get(te.getNetworkID()).removeNode(te);
	}
}







