package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.datamanager.MacchineDataManager;

@SpringBootApplication
public class ServerMacchineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerMacchineApplication.class, args);
	}

}
