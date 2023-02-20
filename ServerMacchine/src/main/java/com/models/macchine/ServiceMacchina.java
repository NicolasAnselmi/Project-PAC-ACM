package com.models.macchine;



import java.util.List;

import org.springframework.stereotype.Service;

import com.google.cloud.Timestamp;

import macchina.Macchina;
import macchina.StatoMacchina;

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
	
	public Macchina aggiungiMacchina(String idMacchina, String tipoMacchina) {
		return macchineDataManager.addMacchina(idMacchina, tipoMacchina);
	}
	
	public boolean rimuoviMacchina(String idMacchina) {
		return macchineDataManager.deleteMacchina(idMacchina);
	}

	public boolean updateMacchina(String idMacchina, String codiceLottoInLavorazione, String statoMacchina) {
		return macchineDataManager.aggiornaMacchina(idMacchina, codiceLottoInLavorazione, Timestamp.now().toString(), StatoMacchina.valueOf(statoMacchina));
	}
}
