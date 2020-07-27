package com.gmail.merikbest2015.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class for launch an application.
 * @SpringBootApplication is a convenience annotation that adds all of the following:
 * @Configuration: tags the class as a source of bean definitions for the application context.
 * @EnableAutoConfiguration: tells Spring Boot to start adding beans based on classpath settings,
 * other beans, and various property settings.
 * @ComponentScan: tells Spring to look for other components, configurations,
 * and services in the package, letting it find the controllers.
 *
 * @author Miroslav Khotinskiy (merikbest2015@gmail.com)
 * @version 1.0
 */
@SpringBootApplication
public class ServingWebContentApplication {
    /**
     * The main() method uses Spring Boot’s SpringApplication.run() method to launch an application.
     */
    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }
}
