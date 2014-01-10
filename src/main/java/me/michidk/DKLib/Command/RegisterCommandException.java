package me.michidk.DKLib.command;

/**
 * Copyright by michidk
 * Date: 05.01.14
 * Time: 13:33
 */

public class RegisterCommandException extends Exception
{
    public RegisterCommandException()
    {

    }

    public RegisterCommandException(String message)
    {
        super(message);
    }

    public RegisterCommandException(Throwable cause)
    {
        super(cause);
    }

    public RegisterCommandException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
