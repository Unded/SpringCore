package com.test.spring.core;

import com.test.spring.core.controller.EventLogger;
import com.test.spring.core.entity.Client;
import com.test.spring.core.entity.Event;
import com.test.spring.core.entity.EventType;
import com.test.spring.core.spring_config.AppConfig;
import com.test.spring.core.spring_config.LoggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

import static com.test.spring.core.entity.EventType.ERROR;
import static com.test.spring.core.entity.EventType.INFO;

@Service
public class App {

    @Autowired
    private Client client;

    @Resource(name = "defaultEventLogger")
    private EventLogger defaultLogger;

    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;

    public App() {}

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class, LoggerConfig.class);
        ctx.scan("com.test.spring.core");
        ctx.refresh();

        App app = (App) ctx.getBean("app");

        Client client = ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        ctx.close();
    }

    private void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }
}
