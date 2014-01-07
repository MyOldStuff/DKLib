package me.michidk.DKLib.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright by michidk
 * Date: 25.12.13
 * Time: 14:02
 */
public class CommandManager
{

    private Plugin plugin;

    private static SimpleCommandMap cmap;

    private static List<CommandManager> list = new ArrayList<CommandManager>();
    private List<SimpleCommand> commandList = new ArrayList<SimpleCommand>();


    /**
     * feel free to change the messages with new CommandManager.MESSAGE = "";
     */
    public String PREFIX = "";
    public String USAGE_MESSAGE = "§cCorrect usage: §f";
    public String NOPERMS_MESSAGE = "§cYou need the permission §f\"§6<permission>§f\" §cto perform this command!";
    public String ONLYINGAME_MESSAGE = "You can only perform this command ingame.";

    /**
     * init the CommandManager
     * with
     * new CommandManager(this);
     */
    public CommandManager(Plugin plugin)
    {

        this.plugin = plugin;
        list.add(this);

        //get commandMap from CraftServer
        try{

            final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);

            cmap = (SimpleCommandMap)f.get(Bukkit.getServer());

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * register a command
     *
     * @param command       the command
     */
    public void registerCommand(SimpleCommand command)
    {
        cmap.register("", command);
        commandList.add(command);
    }

    /**
     * register a command-list
     *
     * @param commands      the commandlist List<SimpleCommand>
     */
    public void registerCommands(List<SimpleCommand> commands)
    {
        for (SimpleCommand command:commands)
        {
            registerCommand(command);
        }
    }

    /**
     * register a command-array
     *
     * @param commands      the command array
     */
    public void registerCommands(SimpleCommand[] commands)
    {
        registerCommands(Arrays.asList(commands));
    }

    /**
     *
     * @return              all, in this manager, registered commands
     */
    public List<SimpleCommand> getCommands()
    {
        return commandList;
    }

    public Plugin getPlugin()
    {
        return plugin;
    }

    public static List<CommandManager> getCommandManagers()
    {
        return list;
    }

}
