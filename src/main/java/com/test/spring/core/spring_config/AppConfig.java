package com.test.spring.core.spring_config;

import com.test.spring.core.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.text.DateFormat;
import java.util.Date;

/**
 * Комбинировать в одном классе аннотации @Autowired и @Resource нельзя
 * вылетает ошибка NullPointerException.
 * Происходит "взрыв мозга" т.к. непонятно какую из этих анотаций использовать
 * <p>
 * Рекомендуется использовать аннотацию @Autowired на setter`ы,
 * так будет удобнее тестировать приложение
 * <p>
 * Одним из вариантов для получение данных: Environment.
 * Из среды можно получить
 */

@Configuration
@Import(LoggerConfig.class)
@PropertySource("classpath:client.properties")
public class AppConfig {

    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public Client clientBean() {
        Client client = new Client();
        client.setId(environment.getRequiredProperty("id"));
        client.setFullName(environment.getRequiredProperty("name"));
        client.setGreeting(environment.getRequiredProperty("greeting"));
        return client;
    }

    @Bean
    public Date newDate() {
        return new Date();
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }

}
