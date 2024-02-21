package com.github.thomashooks.notenoughmachines.integration.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfigs
{
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Float> LAUNCHING_RAIL_BOOST_FACTOR;
    public static final ForgeConfigSpec.ConfigValue<Float> LAUNCHING_RAIL_LAUNCH_FACTOR;
    public static final ForgeConfigSpec.ConfigValue<Float> HIGH_SPEED_RAIL_MAX_SPEED;
    public static final ForgeConfigSpec.ConfigValue<Float> HIGH_SPEED_RAIL_MAX_SPEED_WATERLOGGED;
    public static final ForgeConfigSpec.ConfigValue<Float> HIGH_SPEED_RAIL_MAX_SPEED_MINECART_FURNACE;
    public static final ForgeConfigSpec.ConfigValue<Float> HIGH_SPEED_RAIL_MAX_SPEED_MINECART_FURNACE_WATERLOGGED;
    public static final ForgeConfigSpec.ConfigValue<Float> HIGH_SPEED_POWERED_RAIL_BOOST_FACTOR;
    public static final ForgeConfigSpec.ConfigValue<Float> HIGH_SPEED_POWERED_RAIL_LAUNCH_FACTOR;
    public static final ForgeConfigSpec.ConfigValue<Integer> PROSPECTOR_PICK_RANGE;
    public static final ForgeConfigSpec.ConfigValue<Integer> ITEM_CONDUIT_TRANSFER_RATE;

    static
    {
        BUILDER.push("Configs for Not Enough Machines");

        LAUNCHING_RAIL_BOOST_FACTOR = BUILDER.comment("The boost factor of a minecart while it's on a launching rail").define("Launching Rail's Boost Factor", 0.09F);
        LAUNCHING_RAIL_LAUNCH_FACTOR = BUILDER.comment("The launch factor of a minecart while it's on a launching rail").define("Launching Rail's Launch Factor", 0.05F);
        HIGH_SPEED_RAIL_MAX_SPEED = BUILDER.comment("The maximum speed of a minecart while it's on a high-speed rail").define("High-Speed Rail's Max Speed", 0.9F);
        HIGH_SPEED_RAIL_MAX_SPEED_WATERLOGGED = BUILDER.comment("The maximum speed of a minecart while it's on a waterlogged high-speed rail").define("Waterlogged High-Speed Rail's Max Speed", 0.4F);
        HIGH_SPEED_RAIL_MAX_SPEED_MINECART_FURNACE = BUILDER.comment("The maximum speed of a furnace minecart while it's on a high-speed rail").define("Furnace Minecart's Max Speed on High-Speed Rail", 0.6F);
        HIGH_SPEED_RAIL_MAX_SPEED_MINECART_FURNACE_WATERLOGGED = BUILDER.comment("The maximum speed of a furnace minecart while it's on a waterlogged high-speed rail").define("Furnace Minecart's Max Speed on a waterlogged High-Speed Rail", 0.3F);
        HIGH_SPEED_POWERED_RAIL_BOOST_FACTOR = BUILDER.comment("The boost factor of a minecart while it's on a high-speed powered rail").define("High-Speed Powered Rail's Boost Factor", 0.07F);
        HIGH_SPEED_POWERED_RAIL_LAUNCH_FACTOR = BUILDER.comment("The launch factor of a minecart while it's on a high-speed powered rail").define("High-Speed Powered Rail's Launch Factor", 0.03F);

        PROSPECTOR_PICK_RANGE = BUILDER.comment("The prospector pick's maximum scanning range").define("Prospector Pick's Max Range", 7);

        ITEM_CONDUIT_TRANSFER_RATE = BUILDER.comment("The item transfer rate of all item conduits in ticks").define("Item Conduit Transfer Rate", 40);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
