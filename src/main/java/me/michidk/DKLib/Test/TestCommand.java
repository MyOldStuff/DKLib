package me.michidk.DKLib.test;

import me.michidk.DKLib.command.CommandInfo;
import me.michidk.DKLib.command.CommandManager;
import me.michidk.DKLib.command.SimpleCommand;
import org.bukkit.command.CommandSender;

/**
 * Copyright by michidk
 * Date: 25.12.13
 * Time: 14:43
 */

@CommandInfo(description = "this is the description of the commamd", usage = "test command: <command> aaa", permission = "permm.ission", onlyIngame = true)
public class TestCommand extends SimpleCommand
{

    public TestCommand(CommandManager commandManager)
    {
        super(commandManager, "command");

        //adding more aliases
        this.addAlias("alsoAnAlias");
        this.addAlias("kuchen");


    }

    @Override
    public boolean onCommand(CommandSender sender, String command, String[] args)
    {

        sender.sendMessage("YEEAYY §4 IT WORKS!");


        return false;

    }

}
