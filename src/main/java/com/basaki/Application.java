package com.basaki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@code Application} represents the entry point for the Spring
 * boot application example.
 * <p/>
 *
 * @author Indra Basak
 * @since 10/16/17
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.basaki"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
