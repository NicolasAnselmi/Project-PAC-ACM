package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ServerMacchineApplication {
	
	public static ConfigurableApplicationContext ctx;  

	public static void main(String[] args) {
		ctx = SpringApplication.run(ServerMacchineApplication.class, args);
	}

}
