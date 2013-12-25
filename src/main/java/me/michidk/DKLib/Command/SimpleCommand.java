package me.michidk.DKLib.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright by michidk
 * Date: 25.12.13
 * Time: 15:12
 */
public class SimpleCommand extends Command implements PluginIdentifiableCommand, ComplexCommandExecuter
{

    private Plugin plugin;
    private List<SubCommand> subCommands = new ArrayList<SubCommand>();

    /**
     * Creates a new command
     *
     * @param name - the name of the command /name
     */
    public SimpleCommand(String name)
    {
        super(name, "", "/" + name, new ArrayList<String>());
    }

    /**
     * Creates a new command
     *
     * @param name - the name e.g. /name
     * @param description - the description
     */
    public SimpleCommand(String name, String description)
    {
        super(name, description, "/" + name, new ArrayList<String>());
    }

    /**
     * Creates a new Command
     *
     * @param name - the name e.g. /name
     * @param description - the description
     * @param usageMessage - appears if return false
     * @param aliases - the aliases in a String array e.g. /alias
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
     * if you override execute(), then onPlayerCommand() and onConsoleCommand() call super().execute() or they won't work
     *
     * @param sender - the sender that called the command
     * @param command - the name of the command
     * @param args - the arguments that are called with the command e.g. /name args0 args1 args2
     *
     * @return
     */
    @Override
    public boolean execute(CommandSender sender, String command, String[] args)
    {
        boolean succees = false;

        //execute onCommands
        if (onCommand(sender, command, args))
            succees = true;

        if (sender instanceof Player)
        {
            if (onPlayerCommand((Player) sender, command, args))
                succees = true;
        }
        else if (sender instanceof ConsoleCommandSender)
        {
            if (onConsoleCommand((ConsoleCommandSender) sender, command, args))
                succees = true;
        }
        else
        {
            succees = false;
        }

        //print usage
        if (succees == false)
        {
            sender.sendMessage("§cCorrect usage: §f" + usageMessage);
            return false;
        }

        return true;

    }

    /**
     * called when a player performs a command
     * use only if you dont use onPlayerCommand and onConsoleCommand
     *
     * @param sender - the sender that performed the command
     * @param command - the name or alias of the command
     * @param args - the args that were given
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, String command, String[] args)
    {
        return false;
    }

    /**
     * called when a player performs a command
     * use it only if you don't use onCommand
     *
     * @param player - the player that performed the command
     * @param command - the name or alias of the command
     * @param args - the args that were given
     * @return
     */
    @Override
    public boolean onPlayerCommand(Player player, String command, String[] args)
    {
        return false;
    }

    /**
     * called when the console performs a command
     * use it only if you don't use onCommand
     *
     * @param console - the console that performed the command
     * @param command - the name or alias of the command
     * @param args - the args that were given
     * @return
     */
    @Override
    public boolean onConsoleCommand(ConsoleCommandSender console, String command, String[] args)
    {
        return false;
    }

    /**
     * @param alias - register the alias
     */
    public void addAlias(String alias)
    {
        List<String> aliasez = this.getAliases();
        aliasez.add(alias);
        this.setAliases(aliasez);
    }

    /**
     * @param alias - remove the alias
     */
    public void removeAlias(String alias)
    {
        List<String> aliasez = this.getAliases();
        aliasez.remove(alias);
        this.setAliases(aliasez);
    }

    /**
     * register a subcommand
     *
     * @param subCommand - the subcommand to register
     */
    public void addSubCommand(SubCommand subCommand)
    {
        subCommands.add(subCommand);
    }

    /**
     * @return - a list filled with all subcommands
     */
    public List<SubCommand> getSubCommands()
    {
        return subCommands;
    }

    protected void setPlugin(Plugin plugin)
    {
        this.plugin = plugin;
    }

    /**
     * @return - the plugin that registered that command
     */
    @Override
    public Plugin getPlugin()
    {
        return plugin;
    }
}
