package com.hype.application.exception;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException() { super("Invalid User"); }

    public InvalidUserException(String message) { super(message);}
}
