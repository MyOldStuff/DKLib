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
        this.setPermissionMessage(CommandManager.NOPERMS_MESSAGE);
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
        this.setPermissionMessage(CommandManager.NOPERMS_MESSAGE);
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
        this.setPermissionMessage(CommandManager.NOPERMS_MESSAGE);
    }

    /**
     * D0N'T 0V3RRID3 IT!
     * 0R Y0U WILL FUCK THE SYST3M!
     */
    @Override
    public boolean execute(CommandSender sender, String command, String[] args)
    {

        //subcommand handling
        if (args.length >= 1 && subCommands.size() >= 1)
        {
            for (SubCommand sc:subCommands)
            {
                if (args[0].equalsIgnoreCase(sc.getName()))
                {

                    //permission handling
                    if (sc.getPermission() != null && !sender.hasPermission(sc.getPermission()))
                    {
                        sender.sendMessage(getPermissionMessage());
                        return true;
                    }

                    boolean subsuccees = false;

                    //execute onCommands
                    if (sc.onCommand(sender, command, args))
                        subsuccees = true;

                    if (sender instanceof Player)
                    {
                        if (sc.onPlayerCommand((Player) sender, command, args))
                            subsuccees = true;
                    }
                    else if (sender instanceof ConsoleCommandSender)
                    {
                        if (sc.onConsoleCommand((ConsoleCommandSender) sender, command, args))
                            subsuccees = true;
                    }
                    else
                    {
                        subsuccees = false;
                    }

                    //print usage
                    if (subsuccees == false)
                    {
                        sender.sendMessage(CommandManager.USAGE_MESSAGE + usageMessage);
                        return false;
                    }

                    return true;
                }
            }

        }

        //permissions handling
        if (getPermission() != null && !sender.hasPermission(getPermission()))
        {
            sender.sendMessage(getPermissionMessage());
            return true;
        }

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
            sender.sendMessage(CommandManager.USAGE_MESSAGE + usageMessage);
            return false;
        }

        return true;

    }

    /**
     * look at ComplexCommandExecuter Interface
     */
    @Override
    public boolean onCommand(CommandSender sender, String command, String[] args)
    {
        return false;
    }

    /**
     * look at ComplexCommandExecuter Interface
     */
    @Override
    public boolean onPlayerCommand(Player player, String command, String[] args)
    {
        return false;
    }

    /**
     * look at ComplexCommandExecuter Interface
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
