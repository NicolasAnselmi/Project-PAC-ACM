package com.utility;

import org.springframework.stereotype.Service;

@Service
public class ServiceNotifiche {
	
	private GestoreNotifiche gestoreNotifiche = GestoreNotifiche.getGestoreNotifiche();

	public void addToken(String token, String tipo) {
		gestoreNotifiche.addToken(token, tipo);
	}

}
