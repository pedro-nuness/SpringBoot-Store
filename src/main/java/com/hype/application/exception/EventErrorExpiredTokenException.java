package com.hype.application.exception;

public class EventErrorExpiredTokenException extends RuntimeException{
    public EventErrorExpiredTokenException() { super("Expired Token"); ;}

    public EventErrorExpiredTokenException(String message) { super(message);}

}



