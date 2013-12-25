package me.michidk.DKLib.Command;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Copyright by michidk
 * Date: 25.12.13
 * Time: 23:18
 */
public interface ComplexCommandExecuter
{

    public boolean onPlayerCommand(Player p, String command, String[] args);

    public boolean onConsoleCommand(ConsoleCommandSender sender, String command, String[] args);

}
