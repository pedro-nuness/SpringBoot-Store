package com.hype.application.exceptions;

public class EventErrorUnauthoriazedException extends RuntimeException{
    public EventErrorUnauthoriazedException() { super("Unauthorized");}

    public EventErrorUnauthoriazedException(String message) { super(message);}
}
