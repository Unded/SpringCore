package com.test.spring.core.controller;

import com.test.spring.core.entity.Event;

public class ConsoleEventLogger implements EventLogger{
    public void logEvent(Event event){
        System.out.println(event);
    }
}
