package com.test.spring.core.controller;

import com.test.spring.core.entity.Event;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

@Component
public class CombinedEventLogger implements EventLogger{

    @Resource(name ="combinedEventLoggers")
    private Collection<EventLogger> loggers;

    @Override
    public void logEvent(Event event) {
        for (EventLogger eventLogger : loggers){
            eventLogger.logEvent(event);
        }
    }
}
