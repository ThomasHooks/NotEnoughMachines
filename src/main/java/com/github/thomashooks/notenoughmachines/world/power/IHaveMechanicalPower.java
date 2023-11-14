package com.github.thomashooks.notenoughmachines.world.power;

import com.github.thomashooks.notenoughmachines.world.block.entity.AbstractMechanicalBlockEntity;

public interface IHaveMechanicalPower
{
    /**
     * @return The amount of energy that is required by the machine
     */
    int getLoad();



    /**
     * @return The amount of energy that can be generated or stored by the machine
     */
    int getCapacity();



    /**
     * Gets this machine's angular velocity in revolutions per second.
     * If the returned value is positive then this machine is rotating counterclockwise, and clockwise if negative.
     *
     * @return The angular velocity of this machine in revolutions per second
     */
    float getSpeed();



    /**
     * Changes this machine's angular velocity to the given amount.
     * Unit is in revolutions per second.
     *
     * @param driver The machine the change is coming from
     * @param speedIn This machine's new angular velocity
     */
    void changeSpeed(AbstractMechanicalBlockEntity driver, float speedIn);



    /**
     * @return Gets this machine's torque.
     */
    float getTorque();



    /**
     * @return If the machine is currently powered
     */
    boolean isPowered();



    /**
     * @return The mechanical power type of this machine
     */
    MechanicalType getMachineType();
}
