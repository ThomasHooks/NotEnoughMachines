package com.github.thomashooks.notenoughmachines.client;

public class ClientTimer
{
    private static long tick = 0;

    public long getTick()
    {
        return tick;
    }

    public void tickClient()
    {
        tick++;
    }
}
