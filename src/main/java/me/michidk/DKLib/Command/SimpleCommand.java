package me.michidk.DKLib.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright by michidk
 * Date: 25.12.13
 * Time: 15:12
 */
public abstract class SimpleCommand extends Command
{

    /**
     * Creates a new command
     *
     * @param name              the name of the command /name
     */
    public SimpleCommand(String name)
    {
        super(name, "", "/" + name, new ArrayList<String>());
    }

    /**
     * Creates a new command
     *
     * @param name              the name e.g. /name
     * @param description       the description
     */
    public SimpleCommand(String name, String description)
    {
        super(name, description, "/" + name, new ArrayList<String>());
    }

    /**
     * Creates a new Command
     *
     * @param name              the name e.g. /name
     * @param aliases           the aliases e.g. /alias
     */
    public SimpleCommand(String name, String ... aliases)
    {
        super(name, "", "/" + name, Arrays.asList(aliases));
    }

    /**
     * Creates a new Command
     *
     * @param name              the name e.g. /name
     * @param description       the description
     * @param usageMessage      appears if return false
     */
    public SimpleCommand(String name, String description, String usageMessage)
    {
        super(name, description, usageMessage, new ArrayList<String>());
    }

    /**
     * Creates a new Command
     *
     * @param name              the name e.g. /name
     * @param description       the description
     * @param aliases           the aliases e.g. /alias
     */
    public SimpleCommand(String name, String description, String ... aliases)
    {
        super(name, description, "/" + name, Arrays.asList(aliases));
    }

    /**
     * Creates a new Command
     *
     * @param name              the name e.g. /name
     * @param description       the description
     * @param usageMessage      appears if return false
     * @param aliases           the aliases e.g. /alias
     */
    public SimpleCommand(String name, String description, String usageMessage, String ... aliases)
    {
        super(name, description, usageMessage, Arrays.asList(aliases));
    }

    /**
     * will execute when the command is called
     * calls the onPlayerCommand if the sender is a Player
     * or the onConsoleCommand if the sender is a Console
     *
     * if you override execute, then onPlayerCommand and onConsoleCommand won't work
     *
     * @param sender            the sender that called the command
     * @param command           the name of the command
     * @param args              the arguments that are called with the command e.g. /name args0 args1 args2
     *
     * @return
     */
    @Override
    public boolean execute(CommandSender sender, String command, String[] args)
    {
        if (sender instanceof Player)
        {
            return onPlayerCommand((Player) sender, command, args);
        }
        else if (sender instanceof ConsoleCommandSender)
        {
            return onConsoleCommand((ConsoleCommandSender) sender, command, args);
        }
        else
        {
            return false;
        }
    }

    public abstract boolean onPlayerCommand(Player p, String command, String[] args);

    public abstract boolean onConsoleCommand(ConsoleCommandSender sender, String command, String[] args);

    /**
     * add an alias
     *
     * @param alias             the alias to add
     */
    public void addAlias(String alias)
    {
        List<String> aliasez = this.getAliases();
        aliasez.add(alias);
        this.setAliases(aliasez);
    }

    /**
     * remove an alias
     *
     * @param alias             the alias to remove
     */
    public void removeAlias(String alias)
    {
        List<String> aliasez = this.getAliases();
        aliasez.remove(alias);
        this.setAliases(aliasez);
    }

}
