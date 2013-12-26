package me.michidk.DKLib.Test;

import me.michidk.DKLib.Command.SimpleCommand;
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
        super("test", "description", "/test <whatever>", new String[] {"aAlias", "anotherAlias"});

        //adding more aliases
        this.addAlias("alsoAnAlias");
        this.addAlias("kuchen");

        //adding subcommands
        this.addSubCommand(new SpecialSubCommand());
    }

    /*
    //you don't need that
    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings)
    {
        super.execute(commandSender, s, strings);
        //commandSender.sendMessage("this will be called if player and console execute the command");
        return false;
    }
    */

    /*
    @Override
    public boolean onCommand(CommandSender sender, String command, String[] args)
    {
        sender.sendMessage("abc");
        return false;
    }
    */

    @Override
    public boolean onPlayerCommand(Player p, String command, String[] args)
    {
        p.sendMessage("§4OMFG it works");

        if (args.length < 1)
        {
            return false;
        }

        return true;
    }

    @Override
    public boolean onConsoleCommand(ConsoleCommandSender sender, String command, String[] args)
    {
        sender.sendMessage("only ingame");
        return true;
    }


}
