package com.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datamanager.PianificazioniDataManager;
import com.models.data.Lavorazione;
import com.models.data.Lotto;
import com.models.data.Pianificazione;
;

@Service
public class ServicePianificazione {
	
	private PianificazioniDataManager pianificazioniDataManager = PianificazioniDataManager.getPianificazioniDataManager();
	
	public boolean inserisciLotto(Lotto lotto) {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return false;
		pianificazioniDataManager.getPianificazioneCorrente().inserisciLotto(lotto);
		return true;
	}

	public boolean confermaPianificazione() {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return false;
		pianificazioniDataManager.getPianificazioneCorrente().confermaPianificazione();
		return true;
	}

	public boolean calcoloPianificazione() {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return false;
		List<Lotto> residui = pianificazioniDataManager.getPianificazioneCorrente().calcoloPianificazione();
		Pianificazione nuova = new Pianificazione(null, null);
		nuova.setListaLotti(residui);
		pianificazioniDataManager.addPianificazione(null, null);
		return true;
	}

	public boolean cancellaLotto(String idLotto) {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return false;
		return pianificazioniDataManager.getPianificazioneCorrente().cancellaLotto(idLotto);
	}

	public Pianificazione getPianificazioneCorrente() {
		return pianificazioniDataManager.getPianificazioneCorrente();
	}

	public List<Lotto> visualizzaLotti() {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return null;
		return pianificazioniDataManager.getPianificazioneCorrente().getListaLotti();
	}

	public boolean updateLotto(String idLotto, int nPezzi, float tempoLavorazionePezzoFresa,
			float tempoLavorazionePezzoTornio, String priorita) {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return false;
		return pianificazioniDataManager.getPianificazioneCorrente()
				.updateLotto(idLotto, nPezzi, tempoLavorazionePezzoFresa, tempoLavorazionePezzoTornio, priorita);
	
	}

	public List<Lavorazione> getPianificazione() {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return null;
		return pianificazioniDataManager.getPianificazioneCorrente().getPianificazione();
	
	}

	public List<Lavorazione> visualizzaPianificazioneByMacchina(String idMacchina) {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return null;
		return pianificazioniDataManager.getPianificazioneCorrente().getPianificazioneByMacchina(idMacchina);
	
	}
}
