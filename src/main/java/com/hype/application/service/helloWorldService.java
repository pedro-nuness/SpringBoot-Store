package com.hype.application.service;

import org.springframework.stereotype.Service;

@Service
public class helloWorldService {
    
    public String helloWorld(String name){
        return "Hello World " + name;
    }

}
