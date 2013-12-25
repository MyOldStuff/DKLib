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

    private List<SimpleCommand> list = new ArrayList<SimpleCommand>();

    /**
     * init the CommandManager
     * with
     * new CommandManager();
     */
    public CommandManager(Plugin plugin)
    {

        this.plugin = plugin;

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
        command.setPlugin(plugin);
        cmap.register("", command);
        list.add(command);
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
        return list;
    }

}
