package com.services;



import java.util.List;

import org.springframework.stereotype.Service;

import com.datamanager.LogDataManager;
import com.datamanager.MacchineDataManager;
import com.models.data.LogMacchina;
import com.models.macchine.Macchina;
import com.models.macchine.StatoMacchina;

@Service
public class ServiceMacchina {
	
	private LogDataManager logDataManager = LogDataManager.getLogDataManager();
	private MacchineDataManager macchineDataManager = MacchineDataManager.getMacchineDataManager();
	
	public void inviaLog(LogMacchina log) {
		logDataManager.addLogMacchina(log);
	}
	
	public String getDatiMacchina(String idMacchina) {
		return macchineDataManager.getInfoMacchina(idMacchina);
	}
	
	public List<Macchina> getMacchineByStato(String stato) {
		return macchineDataManager.getMacchineByStato(stato);
	}
	
	public void aggiungiMacchina(String m) {
		macchineDataManager.addMacchina(m);
	}
	
	public void rimuoviMacchina(String idMacchina) {
		macchineDataManager.deleteMacchina(idMacchina);
	}

}
