package com.controllers;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ServerMacchineApplication;

@RestController
public class ShutDownController {
	
	@PostMapping("shutdown")
	public void shutDown() {
		ServerMacchineApplication.ctx.close();
	}
	
	@PreDestroy
	private void preDestroy() {
		System.out.println("Server spento...");
	}

}
