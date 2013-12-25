package me.michidk.DKLib.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Copyright by michidk
 * Date: 25.12.13
 * Time: 15:12
 */
public class SimpleCommand extends Command
{

    /**
     * Creates a new command
     *
     * @param name          the name of the command /name
     */
    public SimpleCommand(String name)
    {
        super(name);
    }

    /**
     * Creates a new command
     *
     * @param name          the name e.g. /name
     * @param description   the description
     */
    public SimpleCommand(String name, String description)
    {
        super(name);
        this.setDescription(description);
    }

    /**
     * Creates a new Command
     *
     * @param name          the name e.g. /name
     * @param aliases       the aliases e.g. /alias
     */
    public SimpleCommand(String name, String ... aliases)
    {
        super(name);

        for (String alias:aliases)
        {
            this.getAliases().add(alias);
        }

    }

    /**
     * Creates a new Command
     *
     * @param name          the name e.g. /name
     * @param description   the description
     * @param aliases       the aliases e.g. /alias
     */
    public SimpleCommand(String name, String description, String ... aliases)
    {
        super(name);

        this.setDescription(description);

        for (String alias:aliases)
        {
            this.getAliases().add(alias);
        }
    }

    /**
     * Creates a new Command
     *
     * @param name          the name e.g. /name
     * @param description   the description
     * @param usageMessage  appears if return false
     */
    public SimpleCommand(String name, String description, String usageMessage)
    {
        super(name);

        this.setDescription(description);

        this.setUsage(usageMessage);
    }

    /**
     * Creates a new Command
     *
     * @param name          the name e.g. /name
     * @param description   the description
     * @param usageMessage  appears if return false
     * @param aliases       the aliases e.g. /alias
     */
    public SimpleCommand(String name, String description, String usageMessage, String ... aliases)
    {
        super(name);

        this.setDescription(description);

        this.setUsage(usageMessage);

        for (String alias:aliases)
        {
            this.getAliases().add(alias);
        }
    }

    /**
     * will execute if the command is called
     *
     * @param sender        the sender that called the command
     * @param name          the name of the command
     * @param args          the arguments that are called with the command e.g. /name args0 args1 args2
     *
     * @return
     */
    public boolean execute(CommandSender sender, String name, String[] args)
    {
        return false;
    }

    /**
     * add an alias
     *
     * @param alias         the alias to add
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
     * @param alias         the alias to remove
     */
    public void removeAlias(String alias)
    {
        List<String> aliasez = this.getAliases();
        aliasez.remove(alias);
        this.setAliases(aliasez);
    }


}
