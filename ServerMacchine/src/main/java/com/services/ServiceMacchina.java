package com.services;



import java.util.List;

import org.springframework.stereotype.Service;

import com.datamanager.MacchineDataManager;
import com.models.macchine.Macchina;

@Service
public class ServiceMacchina {
	
	private MacchineDataManager macchineDataManager = MacchineDataManager.getMacchineDataManager();
	
	public String getDatiMacchina(String idMacchina) {
		return macchineDataManager.getInfoMacchina(idMacchina);
	}
	
	public List<Macchina> getAllMacchine(){
		return macchineDataManager.getAllMacchine();
	}
	
	public List<Macchina> getMacchineByStato(String stato) {
		return macchineDataManager.getMacchineByStato(stato);
	}
	
	public Macchina aggiungiMacchina(String m) {
		return macchineDataManager.addMacchina(m);
	}
	
	public boolean rimuoviMacchina(String idMacchina) {
		return macchineDataManager.deleteMacchina(idMacchina);
	}
}
