package org.example.config;

import org.example.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan({"org.example.service", "org.example.repository"})
@PropertySource("classpath:application.properties")
//@PropertySource("classpath:application2.properties")
//@PropertySources({
//        @PropertySource("classpath:application.properties"),
//        @PropertySource("classpath:application2.properties")
//})
public class ConfigB {

    static {
        System.setProperty("value1", "78");
    }
//SpEL

//    @Value("#{systemProperties['value1']}")
    @Value("#{ T(java.lang.Math).random()*1000}")
    private int value;

    @Autowired
    private Environment environment;

    @Bean
    PropertyService propertyService(@Value("${times:9876}") int times) {
        System.out.println("========== value: "+value);

        return new PropertyService(times);
    }
}
