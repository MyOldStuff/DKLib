package me.michidk.DKLib.Test;

import me.michidk.DKLib.Command.SubCommand;
import org.bukkit.command.CommandSender;

/**
 * Copyright by michidk
 * Date: 26.12.13
 * Time: 00:41
 */
public class SpecialSubCommand extends SubCommand
{

    public SpecialSubCommand()
    {
        super("nameOfSub", "simple test subcommand", "subcommand <value>");

        //set permission
        this.setPermission("puste.kuchen");
    }

    @Override
    public boolean onCommand(CommandSender sender, String command, String[] args)
    {
        sender.sendMessage("§2SUBCOMMAND WORKS!!! §6CHEEEAASY!");
        return true;
    }

}
