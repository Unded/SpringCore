package com.test.spring.core.spring_config;

import com.test.spring.core.controller.EventLogger;
import com.test.spring.core.entity.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import static com.test.spring.core.entity.EventType.ERROR;
import static com.test.spring.core.entity.EventType.INFO;

/**Комбинировать в одном классе аннотации @Autowired и @Resource нельзя
 * вылетает ошибка NullPointerException.
 * Происходит "взрыв мозга" т.к. непонятно какую из этих анотаций использовать
 *
 * Рекомендуется использовать аннотацию @Autowired на setter`ы,
 * так будет удобнее тестировать приложение в данном классе связывание выполнено неправльно).*/

@Configuration
@ComponentScan("com.test.spring.core")
public class LoggerConfig {

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }

//    @Resource(name = "consoleEventLogger")
    @Autowired
    @Qualifier("consoleEventLogger")
    private EventLogger consoleEventLogger;


//    @Resource(name = "fileEventLogger")
    @Autowired
    @Qualifier("fileEventLogger")
    private EventLogger fileEventLogger;

//    @Resource(name = "cacheFileEventLogger")
    @Autowired
    @Qualifier("cacheFileEventLogger")
    private EventLogger cacheFileEventLogger;

//    @Resource(name = "combinedEventLogger")
    @Autowired
    @Qualifier("combinedEventLogger")
    private EventLogger combinedEventLogger;


    @Bean
    public Map<EventType, EventLogger> loggerMap() {
        Map<EventType, EventLogger> map = new EnumMap<>(EventType.class);
        map.put(INFO, consoleEventLogger);
        map.put(ERROR, combinedEventLogger);
        return map;
    }

    @Bean
    public Collection<EventLogger> combinedEventLoggers() {
        Collection<EventLogger> loggers = new ArrayList<>(2);
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);
        return loggers;
    }

    @Bean
    public EventLogger defaultEventLogger() {
        return cacheFileEventLogger;
    }
}
