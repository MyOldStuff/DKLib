package me.michidk.DKLib;

import org.bukkit.plugin.Plugin;

/**
 * @author michidk
 */

public class Cooldown
{

    private Plugin plugin;
    private int ticks;
    private int schedulerID;

    private boolean over = false;

    public Cooldown(Plugin plugin, int ticks)
    {
        this.plugin = plugin;
        this.ticks = ticks;

        schedulerID = plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable()
        {
            @Override
            public void run()
            {
                over = true;
            }
        }, ticks);
    }

    public boolean isOver()
    {
        return over;
    }

    public void cancelCooldown()
    {
        plugin.getServer().getScheduler().cancelTask(schedulerID);
    }


}
