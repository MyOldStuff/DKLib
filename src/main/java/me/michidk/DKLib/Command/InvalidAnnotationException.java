package me.michidk.DKLib.command;

/**
 * Copyright by michidk
 * Date: 05.01.14
 * Time: 13:33
 */

public class InvalidAnnotationException extends Exception
{
    public InvalidAnnotationException()
    {

    }

    public InvalidAnnotationException(String message)
    {
        super(message);
    }

    public InvalidAnnotationException(Throwable cause)
    {
        super(cause);
    }

    public InvalidAnnotationException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
