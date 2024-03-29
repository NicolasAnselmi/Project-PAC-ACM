package com.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

public class GestoreNotifiche {
	// aggiungere liste dispositivi
	private ArrayList<String> tokenOperai;
	private ArrayList<String> tokenManager;

	// singleton pattern
	private static GestoreNotifiche g = null;

	private GestoreNotifiche() {
		tokenOperai = new ArrayList<>();
		tokenManager = new ArrayList<>();
		
		try {
			FileInputStream f = new FileInputStream(
					"/Users/anselminicolas/Desktop/Project-PAC-ACM/ServerMacchine/src/main/resources/servermacchine-firebase-adminsdk-75eqt-df0ea8a9a2.json");
			FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(f)).build();
			FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			System.out.println("IO ex");
			e.printStackTrace();
		}
	}

	public static GestoreNotifiche getGestoreNotifiche() {
		if (g == null)
			g = new GestoreNotifiche();
		return g;
	}

	public void sendOperai(String body, String title) {
		send(body, title, tokenOperai);
	}

	public void sendManager(String body, String title) {
		send(body, title, tokenManager);
	}

	
	/* va bene? se no si usa solo il metodo con parametro la lista  di recievers e si mette il foreach 
	 * li dentro, così non si rininizalizza tutto ogni volta, LMK...
	 */
	private String send(String body, String title, String reciever) {
		
		Message message = Message.builder().putData("body", body) // info a caso del json che sarà da inviare al client
				.putData("title", title)
				.setToken(reciever) // we have to use who to decide which devices get the notification
				.build();
		String response;
		try {
			System.out.println("invio " + message.toString());
			response = FirebaseMessaging.getInstance().send(message);
			System.out.println("response " + response);
			return response;
			
		} catch (Exception e) {
			System.out.println(e.getClass());

			return null;
		}
		
	}

	private List<String> send(String body, String title, ArrayList<String> recieversList) {
		List<String> responses = new ArrayList<String>();
		for (String r : recieversList) {
			responses.add(this.send(body, title, r));
		}
		return responses;

	}

	public void addToken(String token, String tipo) {
		if(tipo.equals("operaio")) {
			if(!tokenOperai.contains(token))
				tokenOperai.add(token);
		}
		if(tipo.equals("manager")) {
			if(!tokenManager.contains(token))
				tokenManager.add(token);
		}
	}

}
