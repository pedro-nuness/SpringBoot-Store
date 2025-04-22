package com.hype.application.exception;


public class EventInvalidJsonException extends RuntimeException{
    public EventInvalidJsonException(String Message, String JsonMessage) { super(Message + ": " + JsonMessage);}
    public EventInvalidJsonException(String message) { super(message);}
}
