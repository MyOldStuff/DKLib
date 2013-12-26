package me.michidk.DKLib.Command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Copyright by michidk
 * Date: 25.12.13
 * Time: 23:18
 */
public interface ComplexCommandExecuter
{

    /**
     * called when a player performs a command
     * use only if you dont use onPlayerCommand and onConsoleCommand
     *
     * @param sender - the sender that performed the command
     * @param command - the name or alias of the command
     * @param args - the args that were given
     * @return
     */
    public boolean onCommand(CommandSender sender, String command, String[] args);

    /**
     * called when a player performs a command
     * use it only if you don't use onCommand
     *
     * @param player - the player that performed the command
     * @param command - the name or alias of the command
     * @param args - the args that were given
     * @return
     */
    public boolean onPlayerCommand(Player player, String command, String[] args);

    /**
     * called when the console performs a command
     * use it only if you don't use onCommand
     *
     * @param console - the console that performed the command
     * @param command - the name or alias of the command
     * @param args - the args that were given
     * @return
     */
    public boolean onConsoleCommand(ConsoleCommandSender console, String command, String[] args);

}
