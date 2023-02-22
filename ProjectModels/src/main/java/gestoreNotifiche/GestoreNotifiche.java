package gestoreNotifiche;

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
	private String tk1 = "cl96XGoPTm-cl96XGoPTm-mKnE9lVH3sb:APA91bGtQ4Fp795KpKnQ9LGR7MTV4WaNRydnCwkd3JdBjNsA6xLuKPqENACQLraDHZN5P3UvOSispCg8xi2WExuNufCRXPnv486X18EBYqQqnZyUyMeTWGG3Cccq2KwOqTtphfwnBSZS";
	private String tk2 = "epxkbCpgSYWqkB9_kKZt-8:APA91bFkqprtiQwygg-fEYQVAvl8KW20VoTQtQMuTsAf8bfyTVks4C6iQCOMrCg1lMCcDmDV4niK1TTTowJBgIRa-yP6q1oN8SPa_4YuGJGJR1u0KOXPAZW8N5DazgtIJfjN8evN2x9i";
	private String tk3 = "cktaDyOlR7uEd70C-aZcEY:APA91bEOaA21ayys4L-1FHo-886JdLinTYnhirdRPgKDuYTydxM8Rbb2Tg_eWBKmyhGiqLlbobWObPoSjhs2ve9cRQ6HGOqbscLa8rD7uRELhwc6nGP60JA6hsFKBaXSENaDwzTSeHmu";
	// singleton pattern
	private static GestoreNotifiche g = null;

	private GestoreNotifiche() {
		tokenOperai = new ArrayList<>();
		tokenManager = new ArrayList<>();
		
		tokenOperai.add(tk1); tokenOperai.add(tk2); tokenOperai.add(tk3);
		tokenManager.add(tk1); tokenManager.add(tk2); tokenManager.add(tk3);
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
			System.out.println("invio " + title + " a " + reciever.substring(5));
			response = FirebaseMessaging.getInstance().send(message);
			System.out.println("response " + response + " di " + reciever.substring(5));
			return response;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());

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

}
