package me.michidk.DKLib.command;

/**
 * Copyright by michidk
 * Date: 05.01.14
 * Time: 19:36
 */

import org.bukkit.command.CommandSender;

/**
 * Represents a class which contains a single method for executing commands
 */
public interface CommandExecutable
{

    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    public boolean onCommand(CommandSender sender, String command, String[] args);

}
