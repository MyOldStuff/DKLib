package me.michidk.DKLib.Command;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Copyright by michidk
 * Date: 25.12.13
 * Time: 23:10
 */
public class SubCommand implements ComplexCommandExecuter
{

    @Override
    public boolean onPlayerCommand(Player p, String command, String[] args)
    {
        return false;
    }

    @Override
    public boolean onConsoleCommand(ConsoleCommandSender sender, String command, String[] args)
    {
        return false;
    }
}
