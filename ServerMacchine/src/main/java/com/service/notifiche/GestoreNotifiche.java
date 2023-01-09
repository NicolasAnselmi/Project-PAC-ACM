package com.service.notifiche;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

public class GestoreNotifiche 
{
	//aggiungere liste dispositivi
	private ArrayList<String> tokenOperai;
	private ArrayList<String> tokenManager;
	
	//singleton pattern
	private static GestoreNotifiche g = null;
	
	private GestoreNotifiche()
	{
		
	}
	
	public static GestoreNotifiche getGestoreNotifiche()
	{
		if(g == null)
			g = new GestoreNotifiche();
		return g;
	}
	
	public void sendOperai(String body, String title)
	{
		send(body, title, tokenOperai);
	}
	
	public void sendManager(String body, String title)
	{
		send(body, title, tokenManager);
	}
	
	private void send(String body, String title, ArrayList<String> list)
	{
		try
		{
			FileInputStream f = new FileInputStream("/ServerMacchine/src/main/resources/servermacchine-firebase-adminsdk-75eqt-df0ea8a9a2.json");
			FirebaseOptions options = FirebaseOptions.builder()
			    .setCredentials(GoogleCredentials.fromStream(f))
			    .build();
			FirebaseApp.initializeApp(options);
		}catch(IOException e) {System.out.println("IO ex"); e.printStackTrace();}
		
		Message message = Message.builder()
			    .putData("Body", body)		//info a caso del json che sarà da inviare al client
			    .putData("Title", title)
			    .setToken(list)	//we have to use who to decide which devices get the notification
			    .build();
		
		String response;
		try
		{
			response = FirebaseMessaging.getInstance().send(message);
		}catch(Exception e) {System.out.println(e.getClass());}

	}
	
	
	
	
	
}
