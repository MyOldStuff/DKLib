package me.michidk.DKLib.test;

import me.michidk.DKLib.ListenerCollection;
import me.michidk.DKLib.PersistableLocation;
import me.michidk.DKLib.command.CommandManager;
import me.michidk.DKLib.event.EventCaller;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Copyright by michidk
 * Date: 01.12.13
 * Time: 17:47
 */
public class ExamplePlugin extends JavaPlugin implements Listener
{

    ListenerCollection listeners;
    CommandManager commandManager;

    @Override
    public void onEnable()
    {

        ConfigurationSerialization.registerClass(PersistableLocation.class);

        Bukkit.getPluginManager().registerEvents(new EventCaller(), this);

        //listener test
        listeners = new ListenerCollection(this, new Listener[]{new TestListener()});
        listeners.addListener(this);
        listeners.enable();

        //command test
        commandManager = new CommandManager(this);
        commandManager.registerCommand(new TestCommand(commandManager));
        commandManager.PREFIX = "§f[§4TEST§f] ";

    }

    @Override
    public void onDisable()
    {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {

        //listener test
        if (listeners.isEnabled())
        {
            listeners.disable();
        }
        else
        {
            listeners.enable();
        }


        return true;
    }
}
