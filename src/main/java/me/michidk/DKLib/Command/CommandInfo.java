package me.michidk.DKLib.Command;

import java.lang.annotation.*;

/**
 * Copyright by michidk
 * Date: 27.12.13
 * Time: 17:58
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommandInfo
{

    /**
     *  the description of the command
     */
    public abstract String description() default "no description available";

    /**
     *  a short help, how to use the command
     */
    public abstract String usage() default "/<command> ";

    /**
     *  the permission that the player need to perform the command
     *  NOPERMS_MESSAG from CommandManager.java will send if the executer have no permission
     */
    public abstract String permission() default "";

    /**
     *  if true:
     *  if the sender is a console the message ONLYINGAME_MESSAGE from CommandManager.java will be send
     */
    public abstract boolean onlyIngame() default false;

}
