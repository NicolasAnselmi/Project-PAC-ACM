package com.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificheWebController {
	
	@Autowired
	private ServiceNotifiche serviceNotifiche;
	
	@PostMapping("/notifiche/addToken")
	public void addToken(@RequestParam String token, @RequestParam String tipo) {
		serviceNotifiche.addToken(token, tipo);
	}

}
