package com.reactive.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.reactive.function.client.WebClient;

public class MyClient implements CommandLineRunner {
    public static void main(String[] args) {

    }

    @Autowired
    private WebClient webClient;
    @Override
    public void run(final String... args) throws Exception {

    }
}
