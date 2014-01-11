package me.michidk.DKLib;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author michidk
 */

public class Main extends JavaPlugin
{

    @Override
    public void onEnable()
    {

    }

    @Override
    public void onDisable()
    {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        sender.sendMessage("§f[§bDKLib§f] §2It works!");
        return true;
    }
}
