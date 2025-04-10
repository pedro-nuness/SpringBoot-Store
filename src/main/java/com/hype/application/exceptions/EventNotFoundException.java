package com.hype.application.exceptions;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException() { super("Not Found");}

    public EventNotFoundException(String message) { super(message);}
}
