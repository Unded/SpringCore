package com.test.spring.core.controller;

import com.test.spring.core.entity.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>(cacheSize);
    }

    void toDestroy(){
        if (!cache.isEmpty()){
            writeEventFromCache();
        }
    }

    @Override
    public void logEvent(Event event){
        cache.add(event);
        if (cache.size() == cacheSize){
            writeEventFromCache();
            cache.clear();
        }
    }

    private void writeEventFromCache() {
        cache.forEach(super::logEvent);
    }
}
