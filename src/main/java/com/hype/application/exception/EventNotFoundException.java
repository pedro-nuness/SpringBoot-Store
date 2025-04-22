package com.hype.application.exception;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException() { super("Not Found");}

    public EventNotFoundException(String message) { super(message);}
}
