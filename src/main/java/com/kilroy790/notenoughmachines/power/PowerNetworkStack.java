package com.kilroy790.notenoughmachines.power;

import java.util.HashMap;
import java.util.Map;

import com.kilroy790.notenoughmachines.NotEnoughMachines;
import com.kilroy790.notenoughmachines.tiles.machines.MechanicalTile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;




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
	
	
	
	/*
	 * 
	 */
	public boolean hasPowerNetwork(IWorld worldIn, long networkID) {
		return universePowerNetworks.get(worldIn).containsKey(networkID);
	}
	
	
	/*
	 * @param te, the mechanical tile that is joining a power network
	 * 
	 * @return The power network's ID, or 0 if the tile is null
	 */
	public long joinPowerNetwork(MechanicalTile te) {
		
		if(te == null) return 0;
		
		long networkID = te.getNetworkID();
		Map<Long, PowerNetwork> worldPowerNetworks = universePowerNetworks.get(te.getWorld());
		if(networkID != 0) addBackToPowerNetwork(te, worldPowerNetworks);
		else networkID = createOrAddToPowerNetwork(te, worldPowerNetworks);
		
		return networkID;
	}
	
	
	
	private void addBackToPowerNetwork(MechanicalTile te, Map<Long, PowerNetwork> worldPowerNetworks) {
		
		long networkID = te.getNetworkID();
		PowerNetwork network = worldPowerNetworks.get(networkID);
		if( network == null) {
			network = new PowerNetwork(networkID);
			worldPowerNetworks.put(networkID, network);
		}
		network.addNode(te, false);
	}
	
	
	
	private long createOrAddToPowerNetwork(MechanicalTile te, Map<Long, PowerNetwork> worldPowerNetworks) {
		
		long networkID = 0;
		World world = te.getWorld();
		
		//TODO: add network merging 
		for(MechanicalInputOutput io : te.getMechIO()) {
			TileEntity otherTE = world.getTileEntity(io.getPos());
			MechanicalTile mechTE = otherTE instanceof MechanicalTile ? (MechanicalTile)otherTE : null;
			if(mechTE != null) {
				for(MechanicalInputOutput io2 : mechTE.getMechIO()) {
					BlockPos posIO = io2.getPos();
					BlockPos posTE = te.getPos();
					if(posIO.getX() == posTE.getX() && posIO.getY() == posTE.getY() && posIO.getZ() == posTE.getZ()) {
						networkID = mechTE.getNetworkID();
						worldPowerNetworks.get(networkID).addNode(te, false);
						break;
					}
				}
			}
			if(networkID != 0) break;
		}
		if(networkID == 0) {
			networkID = nextID++;
			PowerNetwork network = new PowerNetwork(networkID);
			network.addNode(te, false);
			worldPowerNetworks.put(networkID, network);
		}
		return networkID;
	}
	
	
	
	/*
	 * 
	 */
	public void removeFromPowerNetwork(MechanicalTile te) {
		
		if(te != null) {
			World world = te.getWorld();
			Map<Long, PowerNetwork> worldPowerNetworks = universePowerNetworks.get(world);
			worldPowerNetworks.get(te.getNetworkID()).removeNode(te);
		}
	}
}







