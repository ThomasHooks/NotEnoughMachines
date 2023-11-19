package com.github.thomashooks.notenoughmachines.world.power;

public interface IMechanicalPower
{
    /**
     * @return The amount of energy that is required by the machine
     */
    int getLoad();

    /**
     * Sets the power load of this machine to the given amount
     * @param newLoad This machine's new power load
     */
    void setLoad(int newLoad);

    /**
     * @return The amount of energy that can be generated or stored by the machine
     */
    int getCapacity();

    /**
     * Sets the power capacity of this machine to the given amount
     * @param newCapacity This machine's new power capacity
     */
    void setCapacity(int newCapacity);

    /**
     * @return If the machine is currently powered
     */
    boolean isPowered();

    /**
     * @return The mechanical power type of this machine
     */
    MechanicalType getMachineType();

    /**
     * Sets the number of teeth for this machine
     * <p>
     * Note: This method is only used by machines with cog mechanical connections
     * @return The number of teeth of this machine, by default it is 1
     */
    default float getNumberOfTeeth() { return 1.0F; }

    /**
     * @return The machine's power network ID or 0 if it does not have a network.
     */
    long getNetworkID();

    /**
     * Attaches this machine to a new power network.
     * This method should only be called by this machine's power network or a power network that is adding this machine.
     * @param id The new power network ID for this tile
     */
    void setNetworkID(long id);

    /**
     * Updates this machine with the power networks new state.
     * This method should only be called by this machine's power network.
     * @param currentNetworkCapacity The new power capacity of the power network
     * @param currentNetworkLoad The new power load of the power network
     */
    void networkUpdate(int currentNetworkCapacity, int currentNetworkLoad);
}
