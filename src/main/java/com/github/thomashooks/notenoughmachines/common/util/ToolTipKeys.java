package com.github.thomashooks.notenoughmachines.common.util;

import com.github.thomashooks.notenoughmachines.NotEnoughMachines;
import org.jetbrains.annotations.NotNull;

public enum ToolTipKeys
{
    ACTIVATOR_RAIL("activator_rail"),
    BUFFER_STOP_RAIL("buffer_stop_rail"),
    CHIME_RAIL("chime_rail"),
    COKE_OVEN("coke_oven"),
    CROSSOVER_RAIL1("crossover_rail1"),
    CROSSOVER_RAIL2("crossover_rail2"),
    DETECTOR_RAIL("detector_rail"),
    GEARBOX("gearbox"),
    IS_DYEABLE("is_dyeable"),
    ITEM_FILTER("item_filter"),
    LIMITER_RAIL1("limiter_rail1"),
    LIMITER_RAIL2("limiter_rail2"),
    LOCKING_RAIL1("locking_rail1"),
    LOCKING_RAIL2("locking_rail2"),
    MECHANICAL_POWER_SIDES("mechanical_power_sides"),
    MECHANICAL_POWER_STRAIGHT("mechanical_power_straight"),
    MILLSTONE("millstone"),
    MINECARTS_MOVE_FASTER("minecarts_move_faster"),
    MORE_INFO_PRESS_SHIFT("more_info_press_shift"),
    ONE_WAY_RAIL1("one_way_rail1"),
    ONE_WAY_RAIL2("one_way_rail2"),
    POWERED_RAIL("powered_rail"),
    REDUCES_FALL_DAMAGE("reduces_fall_damage"),
    ROLLING_MILL("rolling_mill"),
    TRIP_HAMMER1("trip_hammer1"),
    TRIP_HAMMER2("trip_hammer2"),
    WATER_WHEEL("water_wheel"),
    WIND_WHEEL1("wind_wheel1"),
    WIND_WHEEL2("wind_wheel2");

    private final String key;

    ToolTipKeys(@NotNull String key)
    {
        this.key = key;
    }

    @Override
    public String toString() { return this.key; }

    public @NotNull String getKey() { return this.key; }

    public @NotNull String getTranslation() { return getTranslationKey(this.key); }

    public static @NotNull String getTranslationKey(@NotNull String key) { return "tooltip." + NotEnoughMachines.MOD_ID + "." + key; }
}