package com.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datamanager.MacchineDataManager;
import com.datamanager.PianificazioniDataManager;
import com.models.data.Lavorazione;
import com.models.data.Lotto;
import com.models.data.Pianificazione;
import com.models.macchine.Macchina;
;

@Service
public class ServicePianificazione {
	
	private PianificazioniDataManager pianificazioniDataManager = PianificazioniDataManager.getPianificazioniDataManager();
	private MacchineDataManager macchineDataManager = MacchineDataManager.getMacchineDataManager();
	
	public boolean inserisciLotto(String idLotto, String idProdotto, int nPezzi, String priorita, String[] sequenzaLavorazioni) {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return false;
		pianificazioniDataManager.getPianificazioneCorrente().inserisciLotto(new Lotto(idLotto, idProdotto, nPezzi, priorita, sequenzaLavorazioni));
		return true;
	}

	public boolean confermaPianificazione() {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return false;
		pianificazioniDataManager.getPianificazioneCorrente().confermaPianificazione();
		return true;
	}

	public List<Lavorazione> calcoloPianificazione(String[] listaMacchine, int slotSettimanali) {
		List<Macchina> macchine= macchineDataManager.getMacchineById(listaMacchine);
		return pianificazioniDataManager.getCalcoloPianificazione(getPianificazioneCorrente().getListaLotti(),macchine, slotSettimanali);
	}

	public boolean cancellaLotto(String idLotto) {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return false;
		return pianificazioniDataManager.getPianificazioneCorrente().cancellaLotto(idLotto);
	}

	public List<Lotto> visualizzaLottiPianificazioneCorrente() {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return null;
		return pianificazioniDataManager.getPianificazioneCorrente().getListaLotti();
	}

	public boolean updateLotto(String idLotto, int nPezzi, String priorita, String[] listaLavorazioni) {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return false;
		return pianificazioniDataManager.getPianificazioneCorrente()
				.updateLotto(idLotto, nPezzi, priorita, listaLavorazioni);
	
	}

	public Pianificazione getPianificazioneCorrente() {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return null;
		return pianificazioniDataManager.getPianificazioneCorrente();
	
	}

	public List<Lavorazione> visualizzaPianificazioneByMacchina(String idMacchina) {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return null;
		return pianificazioniDataManager.getPianificazioneCorrente().getPianificazioneByMacchina(idMacchina);
	
	}

	public List<Lotto> getLottiResiduiPianificazioneCorrente() {
		return pianificazioniDataManager.getPianificazioneCorrente().getLottiResidui();
	}
}
