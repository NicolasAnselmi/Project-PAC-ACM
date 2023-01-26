package com.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.models.log.LogDataManager;
import com.models.log.LogMacchina;
import com.models.lotto.Lotto;
import com.models.lotto.PrioritaLotto;
import com.models.macchine.MacchineDataManager;
import com.models.pianificazione.PianificazioniDataManager;

@Component
public class init implements CommandLineRunner{
	
	private LogDataManager logDataManager = LogDataManager.getLogDataManager();
	private MacchineDataManager macchineDataManager = MacchineDataManager.getMacchineDataManager();
	private PianificazioniDataManager pianificazioneDataManager = PianificazioniDataManager.getPianificazioniDataManager();
	
	@Override
	public void run(String... args) throws Exception {
		
		String[] s1 = {"tornio","fresa","tornio"};
		String[] s2 = {"fresa","tornio"};
		String[] s3 = {"tornio","tornio","fresa"};
		String[] s4 = {"fresa","tornio","fresa"};
		String[] s5 = {"tornio","fresa","fresa"};
		String[] s6 = {"fresa","tornio","tornio","fresa"};
		String[] s7 = {"tornio","fresa"};
		String[] s8 = {"fresa","fresa"};
		
		Lotto lotto1 = new Lotto("lotto1", "pezzo1", 10, "bassa",s1);
		Lotto lotto2 = new Lotto("lotto2", "pezzo2", 10, "media",s2);
		Lotto lotto3 = new Lotto("lotto3", "pezzo3", 10, "alta",s3);
		Lotto lotto4 = new Lotto("lotto4", "pezzo4", 10, "alta",s4);
		Lotto lotto5 = new Lotto("lotto5", "pezzo5", 10, "alta",s5);
		Lotto lotto6 = new Lotto("lotto6", "pezzo6", 10, "bassa",s6);
		Lotto lotto7 = new Lotto("lotto7", "pezzo7", 10, "media",s7);
		Lotto lotto8 = new Lotto("lotto8", "pezzo8", 10, "media",s8);

		var a = pianificazioneDataManager.getPianificazioneCorrente();
		
		a.inserisciLotto(lotto1);
		a.inserisciLotto(lotto2);
		a.inserisciLotto(lotto3);
		a.inserisciLotto(lotto4);
		a.inserisciLotto(lotto5);
		a.inserisciLotto(lotto6);
		a.inserisciLotto(lotto7);
		a.inserisciLotto(lotto8);
		
	}
	
	

}
