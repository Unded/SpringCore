package com.test.spring.core.spring_config;

import com.test.spring.core.controller.EventLogger;
import com.test.spring.core.entity.EventType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import static com.test.spring.core.entity.EventType.ERROR;
import static com.test.spring.core.entity.EventType.INFO;

@Configuration
public class LoggerConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Resource(name = "consoleEventLogger")
    private EventLogger consoleEventLogger;

    @Resource(name = "fileEventLogger")
    private EventLogger fileEventLogger;

    @Resource(name = "cacheFileEventLogger")
    private EventLogger cacheEventLogger;

    @Resource(name = "combinedEventLogger")
    private EventLogger combinedEventLogger;


    @Bean
    public Map<EventType, EventLogger> loggerMap(){
        Map<EventType, EventLogger> map = new EnumMap<>(EventType.class);
        map.put(INFO, consoleEventLogger);
        map.put(ERROR, combinedEventLogger);
        return map;
    }

    @Bean
    public Collection<EventLogger> combinedEventLoggers(){
        Collection<EventLogger> loggers = new ArrayList<>(2);
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);
        return loggers;
    }

    @Bean
    public EventLogger defaultEventLogger(){
        return cacheEventLogger;
    }
}
