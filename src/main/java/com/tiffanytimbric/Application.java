package com.tiffanytimbric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.util.annotation.NonNull;

@SpringBootApplication
public class Application {

    public static void main( @NonNull final String... args ) {
        SpringApplication.run( Application.class, args );
    }

}
