package com.test.spring.core.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**Для получения данных из файла "client.properties"
 * можно воспользоваться "Environment", как это следално в AppConfig
 * или описать BEAN прямо в классе.*/

//@Component
//@Scope("singleton")
//@PropertySource("classpath:client.properties")
public class Client {

//    @Value("${id}")
    private String id;

//    @Value("${name}")
    private String fullName;

//    @Value("${greeting}")
    private String greeting;

    public Client() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
