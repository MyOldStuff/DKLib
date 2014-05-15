package me.michidk.DKLib;

import me.michidk.DKLib.event.EventCaller;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author michidk
 */

public class Main extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(new EventCaller(), this);
    }

    @Override
    public void onDisable()
    {

    }


}
