package com.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.datamanager.LogDataManager;
import com.datamanager.MacchineDataManager;
import com.models.data.LogMacchina;

@Component
public class init implements CommandLineRunner{
	
	private LogDataManager logDataManager = LogDataManager.getLogDataManager();
	private MacchineDataManager macchineDataManager = MacchineDataManager.getMacchineDataManager();
	@Override
	public void run(String... args) throws Exception {
		
		macchineDataManager.addMacchina("tornio1");
		macchineDataManager.addMacchina("tornio2");
		macchineDataManager.addMacchina("tornio3");
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
