package com.utility;

import javax.annotation.PreDestroy;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ServerMacchineApplication;

@RestController
public class ShutDownController {
	
	@PostMapping("shutdown")
	public boolean shutDown() {
		ServerMacchineApplication.ctx.close();
		return true;
	}
	
	@PreDestroy
	private void preDestroy() {
		System.out.println("Server spento...");
	}

}
