package com.hype.application.exception;

public class EventFullException extends RuntimeException{
    public EventFullException() { super("Event full");}

    public EventFullException(String message) { super(message);}
}
