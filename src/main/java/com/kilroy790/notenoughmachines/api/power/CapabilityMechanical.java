package com.kilroy790.notenoughmachines.api.power;

import com.kilroy790.notenoughmachines.power.MechanicalType;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;




public class CapabilityMechanical {

	
    @CapabilityInject(IMechanicalPower.class)
    public static Capability<IMechanicalPower> MECHANICAL = null;
    
    
    public static void register() {
    	
    	CapabilityManager.INSTANCE.register(IMechanicalPower.class, new Capability.IStorage<IMechanicalPower>() {
    		
            @Override
            public INBT writeNBT(Capability<IMechanicalPower> capability, IMechanicalPower instance, Direction side) {
                return new IntNBT(instance.getStoredEngergy());
            }

            
            
            @Override
            public void readNBT(Capability<IMechanicalPower> capability, IMechanicalPower instance, Direction side, INBT nbt) {
                if (!(instance instanceof MechanicalPower))
                    throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                ((MechanicalPower)instance).storedPower = ((IntNBT)nbt).getInt();
            }
		}, () -> new MechanicalPower(500, 500, 500, MechanicalType.CHANNEL));
    }
}
