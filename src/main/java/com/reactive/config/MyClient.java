package com.reactive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

@SpringBootApplication
public class MyClient implements CommandLineRunner {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MyClient.class)
                .run(args);
    }

    @Autowired
    private WebClient webClient;
    @Override
    public void run(final String... args){
        System.out.println("args = " + Arrays.toString(args));
    }
}
