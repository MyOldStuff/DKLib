package me.michidk.DKLib.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright by michidk
 * Date: 25.12.13
 * Time: 15:12
 */
public class SimpleCommand extends Command implements CommandExecutable, PluginIdentifiableCommand, CommandInfo
{

    private Plugin plugin;
    private CommandManager commandManager;

    private boolean onlyIngame;

    public SimpleCommand(CommandManager commandManager, String name)
    {
        super(name);


        //link commandmanager
        this.commandManager = commandManager;
        /*      //dont work, need plugin before constructur
        for (CommandManager cm:CommandManager.getCommandManagers())
        {
            for (SimpleCommand sc:cm.getCommands())
            {
                if (sc.equals(this))
                {
                    commandManager = cm;
                }
            }
        }
        */

        if (commandManager == null)
        {
            try
            {
                throw new RegisterCommandException("failed to register the command " + name);
            }
            catch (RegisterCommandException e)
            {
                e.printStackTrace();
            }

            return;
        }


        //link plugin
        plugin = this.commandManager.getPlugin();


        //test for annotation
        if(!this.getClass().isAnnotationPresent(CommandInfo.class))
        {
            try
            {
                throw new InvalidAnnotationException("no annotations found");
            }
            catch (InvalidAnnotationException e)
            {
                e.printStackTrace();
            }

            return;
        }


        //set infos
        this.setDescription(description());
        this.setUsage(commandManager.PREFIX + commandManager.USAGE_MESSAGE + usage());
        this.setPermission(permission());
        this.setPermissionMessage(commandManager.PREFIX + commandManager.NOPERMS_MESSAGE);
        this.setOnlyIngame(onlyIngame());


        //debug
        //Bukkit.broadcastMessage(name + " - " + description() + " - " + usage() + " - " + permission() + " - " + getPermissionMessage() + " - " + onlyIngame());

    }

    /**
     * D0N'T 0V3RRID3 IT!
     * 0R Y0U WILL FUCK THE SYST3M!
     */
    @Override
    public boolean execute(CommandSender sender, String command, String[] args)
    {
        //was our command succeesfully executed?
        boolean success = false;

        //check if plugin is enabled
        if (!plugin.isEnabled()) {
            try {
                throw new CommandException("Unhandled exception while executing command " + command + "in plugin " + plugin.getDescription().getFullName() + ": plugin is not enabled!");
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }

        //handle permission
        if (!testPermission(sender)) {
            return true;
        }


        //is ingame?
        if (onlyIngame() == true && !(sender instanceof Player))
        {
            sender.sendMessage(commandManager.PREFIX + commandManager.ONLYINGAME_MESSAGE);
            return true;
        }

        /**
         * from {@link org.bukkit.command.PluginCommand}
         */
        try {
            success = onCommand(sender, command, args);
        } catch (Throwable ex) {
            throw new CommandException("Unhandled exception executing command '" + command + "' in plugin " + plugin.getDescription().getFullName(), ex);
        }

        if (!success && usageMessage.length() > 0) {
            for (String line : usageMessage.replace("<command>", command).split("\n")) {
                sender.sendMessage(line);
            }
        }

        return success;
    }

    /**
     * see {@link CommandExecutable}
     */
    @Override
    public boolean onCommand(CommandSender sender, String command, String[] args)
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
     * @param aliases - register aliases
     */
    public void addAliases(String... aliases)
    {
        List<String> aliasez = this.getAliases();
        for (String alias:aliases)
        {
            aliasez.add(alias);
        }
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
     * removes all aliases
     */
    public void removeAliases()
    {
        List<String> aliasez = new ArrayList<String>();
        this.setAliases(aliasez);
    }

    public boolean isOnlyIngame()
    {
        return onlyIngame;
    }

    public void setOnlyIngame(boolean onlyIngame)
    {
        this.onlyIngame = onlyIngame;
    }

    protected void setPlugin(Plugin plugin)
    {
        this.plugin = plugin;
    }

    /**
     * @return          the plugin that registered the command
     */
    @Override
    public Plugin getPlugin()
    {
        return plugin;
    }

    /**
     * @return          the annotation object
     */
    public CommandInfo getCommandInfo()
    {
        return (CommandInfo) this.getClass().getAnnotation(CommandInfo.class);
    }

    @Override
    public String description()
    {
        return getCommandInfo().description();
    }

    @Override
    public String usage()
    {
        return getCommandInfo().usage();
    }

    @Override
    public String permission()
    {
        return getCommandInfo().permission();
    }

    @Override
    public boolean onlyIngame()
    {
        return getCommandInfo().onlyIngame();
    }

    @Override
    public Class<? extends Annotation> annotationType()
    {
        return getCommandInfo().annotationType();
    }
}
