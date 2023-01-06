package com.models.macchine;

import com.service.notifiche.GestoreNotifiche;

public class Macchina implements Machinable
{
	//campi macchina
	private GestoreNotifiche gestoreNotifiche;
	
	public Macchina()
	{
		gestoreNotifiche = GestoreNotifiche.getGestoreNotifiche();
	}
	
	public void aggiornaMacchina(String json) //scompatta json, aggiunge log
	{
		//in caso venga trovato un caso critico
		JSONObject j = new JSONObject();
		if(false)
			gestoreNotifiche.sendOperai("corpo", "titolo");
	}

	@Override
	public String getInfoMacchina(String json) {
		// TODO Auto-generated method stub
		return null;
	}
}
