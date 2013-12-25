package me.michidk.DKLib.Test;

import me.michidk.DKLib.Command.SimpleCommand;
import org.bukkit.command.CommandSender;

/**
 * Copyright by michidk
 * Date: 25.12.13
 * Time: 14:43
 */
public class TestCommand extends SimpleCommand
{

    public TestCommand()
    {
        super("abc", "desc", "usage");
        this.addAlias("alias");
        this.addAlias("kuchen");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings)
    {
        commandSender.sendMessage("HII!!!!");
        return true;
    }

}
