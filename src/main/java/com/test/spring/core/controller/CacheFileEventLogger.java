package com.test.spring.core.controller;

import com.test.spring.core.entity.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class CacheFileEventLogger extends FileEventLogger {


    @Value("${cacheSize:5}")
    private int cacheSize;

    private List<Event> cache;

    public CacheFileEventLogger() {

    }

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    @PostConstruct
    public void initCache() {
        this.cache = new ArrayList<>(cacheSize);
    }

    @PreDestroy
    void toDestroy() {
        if (!cache.isEmpty()) {
            writeEventFromCache();
        }
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cacheSize) {
            writeEventFromCache();
            cache.clear();
        }
    }

    private void writeEventFromCache() {
        cache.forEach(super::logEvent);
    }
}
