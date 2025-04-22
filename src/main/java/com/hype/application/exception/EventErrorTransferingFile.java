package com.hype.application.exception;


public class EventErrorTransferingFile extends RuntimeException{
    public EventErrorTransferingFile(String Message, String JsonMessage) { super(Message + ": " + JsonMessage);}
    public EventErrorTransferingFile(String message) { super(message);}
}
