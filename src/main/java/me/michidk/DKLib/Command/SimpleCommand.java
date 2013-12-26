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

    /**
     * Creates a new command
     *
     * @param name          the name of the command /name
     */
    public SimpleCommand(String name)
    {
        super(name, "", "/" + name, new ArrayList<String>());
        this.setPermissionMessage(CommandManager.NOPERMS_MESSAGE);
    }

    /**
     * Creates a new command
     *
     * @param name          the name e.g. /name
     * @param description   the description
     */
    public SimpleCommand(String name, String description)
    {
        super(name, description, "/" + name, new ArrayList<String>());
        this.setPermissionMessage(CommandManager.NOPERMS_MESSAGE);
    }

    /**
     * Creates a new Command
     *
     * @param name          the name e.g. /name
     * @param description   the description
     * @param usageMessage  appears if return false
     * @param aliases       the aliases in a String array e.g. /alias
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
            return true;
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
