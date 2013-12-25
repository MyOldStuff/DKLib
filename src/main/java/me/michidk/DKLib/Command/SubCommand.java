package me.michidk.DKLib.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Copyright by michidk
 * Date: 25.12.13
 * Time: 23:10
 */
public class SubCommand implements ComplexCommandExecuter
{

    private String name;
    private String description;
    private String usage;
    private boolean includeIntoHelp = true;

    public SubCommand(String name, String description, String usage)
    {
        this.name = name;
        this.description = description;
        this.usage = usage;
    }

    @Override
    public boolean onCommand(CommandSender sender, String command, String[] args)
    {
        return false;
    }

    @Override
    public boolean onPlayerCommand(Player player, String command, String[] args)
    {
        return false;
    }

    @Override
    public boolean onConsoleCommand(ConsoleCommandSender console, String command, String[] args)
    {
        return false;
    }

}
