package me.michidk.DKLib.Test;

import me.michidk.DKLib.Command.CommandManager;
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
    CommandManager commandManager;

    @Override
    public void onEnable()
    {

        //listener test
        listeners = new ListenerCollection(this, new Listener[]{new TestListener()});
        listeners.addListener(this);

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
