package com.hype.application.exceptions;

public class EventErrorTokenGenerationException extends RuntimeException{
    public EventErrorTokenGenerationException() { super("Error while generating authentication token");}

    public EventErrorTokenGenerationException(String message) { super(message);}
}
