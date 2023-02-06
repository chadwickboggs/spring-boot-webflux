package com.tiffanytimbric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.util.annotation.NonNull;

@SpringBootApplication
public class Application {

    public static void main( @NonNull final String... args ) {
        SpringApplication.run( Application.class, args );
    }

/*
    @Bean
    public CommandLineRunner commandLineRunner( ApplicationContext ctx ) {
        return args -> {
            System.out.println( "Let's inspect the beans provided by Spring Boot:" );

            final String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort( beanNames );
            for ( String beanName : beanNames ) {
                System.out.println( beanName );
            }
        };
    }
*/
}
