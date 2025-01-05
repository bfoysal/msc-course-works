package com.uwindsor.comp8390.asynchronous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AsynchronousApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsynchronousApplication.class, args);
	}

}
