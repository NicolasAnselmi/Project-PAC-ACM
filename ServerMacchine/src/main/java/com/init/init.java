package com.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.datamanager.LogDataManager;
import com.datamanager.MacchineDataManager;
import com.datamanager.PianificazioniDataManager;
import com.models.data.LogMacchina;
import com.models.data.Lotto;
import com.models.data.PrioritaLotto;

@Component
public class init implements CommandLineRunner{
	
	private LogDataManager logDataManager = LogDataManager.getLogDataManager();
	private MacchineDataManager macchineDataManager = MacchineDataManager.getMacchineDataManager();
	private PianificazioniDataManager pianificazioneDataManager = PianificazioniDataManager.getPianificazioniDataManager();
	
	@Override
	public void run(String... args) throws Exception {
		
		macchineDataManager.addMacchina("tornio1","tornio");
		macchineDataManager.addMacchina("tornio2","tornio");
		macchineDataManager.addMacchina("fresa1","fresa");
		String[] s1 = {"tornio","fresa","tornio"};
		String[] s2 = {"fresa","tornio"};
		String[] s3 = {"tornio"};
		String[] s4 = {"fresa","tornio","fresa"};
		String[] s5 = {"tornio","fresa"};
		String[] s6 = {"fresa"};
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

		pianificazioneDataManager.getPianificazioneCorrente().inserisciLotto(lotto1);
		pianificazioneDataManager.getPianificazioneCorrente().inserisciLotto(lotto2);
		pianificazioneDataManager.getPianificazioneCorrente().inserisciLotto(lotto3);
		pianificazioneDataManager.getPianificazioneCorrente().inserisciLotto(lotto4);
		pianificazioneDataManager.getPianificazioneCorrente().inserisciLotto(lotto5);
		pianificazioneDataManager.getPianificazioneCorrente().inserisciLotto(lotto6);
		pianificazioneDataManager.getPianificazioneCorrente().inserisciLotto(lotto7);
		pianificazioneDataManager.getPianificazioneCorrente().inserisciLotto(lotto8);

		String title1 = "RICHIESTA MATERIALI";
		String title2 = "FINE LOTTO";
		String title3 = "GUASTO";
		String body1 = "Richiedo 100pz di A";
		String body2 = "Fine lavorazione lotto 134312";
		String body3 = "Guasto a ventola di raffreddamento";
		LogMacchina l1 = new LogMacchina(1, "tornio1", title1, body1);
		LogMacchina l2 = new LogMacchina(2, "tornio2", title2, body2);
		LogMacchina l3 = new LogMacchina(3, "tornio3", title3, body3);
		logDataManager.addLogMacchina(l1);
		logDataManager.addLogMacchina(l2);
		logDataManager.addLogMacchina(l3);
		
		
	}
	
	

}
