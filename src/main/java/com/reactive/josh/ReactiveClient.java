package com.reactive.josh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@SpringBootApplication
public class ReactiveClient implements CommandLineRunner {

    @Autowired
    private WebClient client;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ReactiveClient.class)
                .properties(Collections.singletonMap("server.port", 8081))
                .properties("spring.main.web-application-type", "reactive")
                .properties(Collections.singletonMap("spring.main.allow-circular-references", true))
                .run(args);
    }

    @Override
    public void run( String... args) throws Exception {

        if (args == null) {
            client.get().uri("/")
                    .accept(MediaType.TEXT_EVENT_STREAM)
                    .exchangeToMono(cr -> cr.bodyToMono(String.class))
                    .subscribe(evt -> System.out.println(evt));
        }
//        client.get().uri("/events/events")
//                .accept(MediaType.TEXT_EVENT_STREAM)
//                .exchangeToFlux(cr -> cr.bodyToFlux(Event.class))
//                .subscribe(evt -> System.out.println(evt));
    }
}
