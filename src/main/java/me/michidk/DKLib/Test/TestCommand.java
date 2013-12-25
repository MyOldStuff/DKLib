package me.michidk.DKLib.Test;

import me.michidk.DKLib.Command.SimpleCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

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

    //dont du this
    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings)
    {
        super.execute(commandSender, s, strings);
        commandSender.sendMessage("HII!!!!");
        return false;
    }

    @Override
    public boolean onPlayerCommand(Player p, String s, String[] strings)
    {
        p.sendMessage("§4OMFG it works");
        return false;
    }

    @Override
    public boolean onConsoleCommand(ConsoleCommandSender sender, String s, String[] strings)
    {
        sender.sendMessage("only ingame");
        return true;
    }

}
