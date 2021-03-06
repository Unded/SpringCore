package com.test.spring.core.entity;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {

    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private int id;
    private String msg;
    private Date date;
    private DateFormat dateFormat;

    public Event(Date date, DateFormat dateFormat) {
        this.id = AUTO_ID.getAndIncrement();
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

    public static boolean isDay(){
        LocalTime time = LocalTime.now();
        return time.getHour() < 18 & time.getHour() > 6;
    }

    @Override
    public String toString() {
        return "Event{Id = " + id +
                ", msg = '" + msg + '\'' +
                ", date = " + dateFormat.format(date) +
                '}';
    }
}
