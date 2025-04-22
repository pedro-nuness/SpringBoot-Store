package com.hype.application.exception;

public class EventErrorUnauthoriazedException extends RuntimeException{
    public EventErrorUnauthoriazedException() { super("Unauthorized");}

    public EventErrorUnauthoriazedException(String message) { super(message);}
}
