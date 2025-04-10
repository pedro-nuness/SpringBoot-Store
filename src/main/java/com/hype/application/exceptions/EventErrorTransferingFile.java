package com.hype.application.exceptions;


public class EventErrorTransferingFile extends RuntimeException{
    public EventErrorTransferingFile(String Message, String JsonMessage) { super(Message + ": " + JsonMessage);}
    public EventErrorTransferingFile(String message) { super(message);}
}
