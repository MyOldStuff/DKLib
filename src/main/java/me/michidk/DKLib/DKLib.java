package me.michidk.DKLib;

import me.michidk.DKLib.event.EventCaller;
import me.michidk.DKLib.libs.protocol.ProtocolManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author michidk
 */

public class DKLib extends JavaPlugin
{
    private static DKLib instance;
    private ProtocolManager protocolManager;

    @Override
    public void onEnable()
    {
        instance = this;

        Bukkit.getPluginManager().registerEvents(new EventCaller(), this);
        this.protocolManager = new ProtocolManager(this);
    }

    @Override
    public void onDisable()
    {

    }

    public static ProtocolManager getProtocolManager(){
        return instance.protocolManager;
    }
}
