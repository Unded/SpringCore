package com.test.spring.core.controller;

import com.test.spring.core.entity.Event;

public interface EventLogger {
    void logEvent(Event event);
}
