package com.test.spring.core.controller;

import com.test.spring.core.entity.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;


@Component
public class FileEventLogger implements EventLogger {
    private File file;

    @Value("${events.file:target/events_log.txt}")
    private String fileName;

    public FileEventLogger() {
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    public void init()throws IOException{
        this.file = new File(fileName);
        if (file.exists() && !file.canWrite()) {
            throw new IllegalArgumentException("Can't write to file " + fileName);
        } else if (!file.exists()) {
            file.createNewFile();
        }
    }

    @Override
    public void logEvent(Event event){
        try {
            FileUtils.writeStringToFile(this.file, event.toString() + "\n","UTF-8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
