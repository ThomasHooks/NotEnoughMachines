package com.github.thomashooks.notenoughmachines.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfigs
{
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Float> HIGH_SPEED_RAIL_MAX_SPEED;
    public static final ForgeConfigSpec.ConfigValue<Float> HIGH_SPEED_RAIL_MAX_SPEED_WATERLOGGED;
    public static final ForgeConfigSpec.ConfigValue<Float> HIGH_SPEED_RAIL_MAX_SPEED_MINECART_FURNACE;
    public static final ForgeConfigSpec.ConfigValue<Float> HIGH_SPEED_RAIL_MAX_SPEED_MINECART_FURNACE_WATERLOGGED;

    static
    {
        BUILDER.push("Configs for Not Enough Machines");

        HIGH_SPEED_RAIL_MAX_SPEED = BUILDER.comment("The maximum speed of a minecart while it's on a high-speed rail")
                .define("High-Speed Rail's Max Speed", 0.8F);
        HIGH_SPEED_RAIL_MAX_SPEED_WATERLOGGED = BUILDER.comment("The maximum speed of a minecart while it's on a waterlogged high-speed rail")
                .define("Waterlogged High-Speed Rail's Max Speed", 0.4F);
        HIGH_SPEED_RAIL_MAX_SPEED_MINECART_FURNACE = BUILDER.comment("The maximum speed of a furnace minecart while it's on a high-speed rail")
                .define("Furnace Minecart's Max Speed on High-Speed Rail", 0.4F);
        HIGH_SPEED_RAIL_MAX_SPEED_MINECART_FURNACE_WATERLOGGED = BUILDER.comment("The maximum speed of a furnace minecart while it's on a waterlogged high-speed rail")
                .define("Furnace Minecart's Max Speed on a waterlogged High-Speed Rail", 0.3F);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
