package me.michidk.DKLib.Test;

import me.michidk.DKLib.Command.CommandInfo;
import me.michidk.DKLib.Command.CommandManager;
import me.michidk.DKLib.Command.SimpleCommand;
import org.bukkit.command.CommandSender;

/**
 * Copyright by michidk
 * Date: 25.12.13
 * Time: 14:43
 */

@CommandInfo(description = "asasdasdd", usage = "test command: <command> aaa", permission = "asd", onlyIngame = true)
public class TestCommand extends SimpleCommand
{

    public TestCommand(CommandManager commandManager)
    {
        super(commandManager, "test");

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
