package me.michidk.DKLib.Test;

import me.michidk.DKLib.ListenerCollection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Copyright by michidk
 * Date: 01.12.13
 * Time: 17:47
 */
public class Main extends JavaPlugin implements Listener
{

    ListenerCollection listeners;

    @Override
    public void onEnable()
    {
        listeners = new ListenerCollection(this, new Listener[]{new TestListener()});
        listeners.addListener(this);
    }

    @Override
    public void onDisable()
    {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
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
