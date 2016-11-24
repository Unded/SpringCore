package com.test.spring.core.controller;

import com.test.spring.core.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class ConsoleEventLogger implements EventLogger{

    public ConsoleEventLogger() {
    }

    @Override
    public void logEvent(Event event){
        System.out.println(event);
    }
}
