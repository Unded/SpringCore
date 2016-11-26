package com.test.spring.core;

import com.test.spring.core.controller.EventLogger;
import com.test.spring.core.entity.Client;
import com.test.spring.core.entity.Event;
import com.test.spring.core.entity.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import static com.test.spring.core.entity.EventType.ERROR;
import static com.test.spring.core.entity.EventType.INFO;

public class App {

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("spring.xml");
        App app = appContext.getBean(App.class);

        Client client = appContext.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        Event event = appContext.getBean(Event.class);
        app.logEvent(INFO, event, "Some event for user: 1");

        event = appContext.getBean(Event.class);
        app.logEvent(ERROR, event, "Some event for user: 2");

        event = appContext.getBean(Event.class);
        app.logEvent(null, event, "Some event for user: 3");

        appContext.close();
//        appContext.registerShutdownHook();
    }

    private void logEvent(EventType eventType, Event event, String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null){
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }
}
