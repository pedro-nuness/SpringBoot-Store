package com.hype.application.exception;

public class EventErrorTokenGenerationException extends RuntimeException{
    public EventErrorTokenGenerationException() { super("Error while generating authentication token");}

    public EventErrorTokenGenerationException(String message) { super(message);}
}
