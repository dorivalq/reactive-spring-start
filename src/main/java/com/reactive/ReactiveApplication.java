package com.reactive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveApplication //implements CommandLineRunner
{

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

//	@Override
//	public void run(final String... args) throws Exception {
//		System.out.println("args = " + new DefenderClient().returnClient());
//	}
}
