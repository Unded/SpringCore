package com.test.spring.core.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Scope("prototype")
public class Event {

    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private int id;
    private String msg;

    @Autowired
    @Qualifier("newDate")
    private Date date;

    @Autowired
    @Qualifier("dateFormat")
    private DateFormat dateFormat;

    public Event() {
        this.id = AUTO_ID.getAndIncrement();
    }

    public Event(Date date, DateFormat dateFormat) {
        this();
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{Id = " + id +
                ", msg = '" + msg + '\'' +
                ", date = " + dateFormat.format(date) +
                '}';
    }
}
