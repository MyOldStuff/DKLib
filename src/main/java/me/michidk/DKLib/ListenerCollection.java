package me.michidk.DKLib;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright by michidk
 * Date: 01.12.13
 * Time: 18:10
 */
public class ListenerCollection
{

    private JavaPlugin plugin;
    private Set<Listener> listeners = new HashSet<>();
    private boolean enabled;

    public ListenerCollection(JavaPlugin plugin)
    {
        this.plugin = plugin;
        enabled = false;
    }

    public ListenerCollection(JavaPlugin plugin, Listener[] listeners)
    {
        this.plugin = plugin;

        for (Listener lis : Arrays.asList(listeners))
            this.listeners.add(lis);

        enabled = false;
    }

    public void enable()
    {
        for (Listener l : listeners)
        {
            Bukkit.getPluginManager().registerEvents(l, plugin);
        }
        enabled = true;
    }

    public void disable()
    {
        for (Listener l : listeners)
        {
            HandlerList.unregisterAll(l);
        }
        enabled = false;
    }

    public void addListener(Listener listener)
    {
        listeners.add(listener);
        if (enabled)
        {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
        }
    }

    public void removeListener(Listener listener)
    {
        listeners.remove(listener);
        HandlerList.unregisterAll(listener);
    }

    public boolean isEnabled()
    {
        return enabled;
    }

}
