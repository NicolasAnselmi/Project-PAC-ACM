package com.init;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.models.pianificazione.PianificazioniDataManager;

import lotto.Lotto;

@Component
public class init implements CommandLineRunner{
	
	private PianificazioniDataManager pianificazioneDataManager = PianificazioniDataManager.getPianificazioniDataManager();
	
	@Override
	public void run(String... args) throws Exception {
		final int MAX = Integer.parseInt(args[0]);
		String[] s1 = {"tornio","fresa","tornio"};
		String[] s2 = {"fresa","tornio"};
		String[] s3 = {"tornio","tornio","fresa"};
		String[] s4 = {"fresa","tornio","fresa"};
		String[] s5 = {"tornio","fresa","fresa"};
		String[] s6 = {"fresa","tornio","tornio","fresa"};
		String[] s7 = {"tornio","fresa"};
		String[] s8 = {"fresa","fresa"};
		
		List<String[]> lav = new ArrayList<>();
		lav.add(s1); lav.add(s2); lav.add(s3); lav.add(s4);
		lav.add(s5);lav.add(s6);lav.add(s7);lav.add(s8);
		
		Lotto lotto1 = new Lotto("lotto1", "pezzo1", 10, "bassa",s1);
		Lotto lotto2 = new Lotto("lotto2", "pezzo2", 10, "media",s2);
		Lotto lotto3 = new Lotto("lotto3", "pezzo3", 10, "alta",s3);
		Lotto lotto4 = new Lotto("lotto4", "pezzo4", 10, "alta",s4);
		Lotto lotto5 = new Lotto("lotto5", "pezzo5", 10, "alta",s5);
		Lotto lotto6 = new Lotto("lotto6", "pezzo6", 10, "bassa",s6);
		Lotto lotto7 = new Lotto("lotto7", "pezzo7", 10, "media",s7);
		Lotto lotto8 = new Lotto("lotto8", "pezzo8", 10, "media",s8);
		
		List<Lotto> l = new ArrayList<Lotto>();
		l.add(lotto1);l.add(lotto2);l.add(lotto3);l.add(lotto4);
		l.add(lotto5);l.add(lotto6);l.add(lotto7);l.add(lotto8);
		
		String[] pri = {"bassa","media","alta"};
		

		var a = pianificazioneDataManager.getPianificazioneCorrente();
		
		
		for(int i = 0; i < MAX; i++) {
			int nPezzo = (int) Math.round(Math.random()*7);
			int nPr = (int) Math.round(Math.random()*2);
			a.inserisciLotto(new Lotto("lotto"+i, "pezzo"+nPezzo, 10, pri[nPr],lav.get(nPezzo)));
		}
		
	}
	
	

}
