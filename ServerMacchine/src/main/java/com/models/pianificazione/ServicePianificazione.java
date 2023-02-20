package com.models.pianificazione;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.models.macchine.MacchineDataManager;

import lotto.Lavorazione;
import lotto.Lotto;
import macchina.Macchina;
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

	public void confermaPianificazione() {
		pianificazioniDataManager.confermaPianificazione();
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

	public boolean updateLotto(String idLotto, String idProdotto, int nPezzi, String priorita, String[] listaLavorazioni) {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return false;
		return pianificazioniDataManager.getPianificazioneCorrente()
				.updateLotto(idLotto, idProdotto, nPezzi, priorita, listaLavorazioni);
	
	}

	public Pianificazione getPianificazioneCorrente() {
		if(pianificazioniDataManager.getPianificazioneCorrente() == null)
			return null;
		return pianificazioniDataManager.getPianificazioneCorrente();
	
	}

	public List<Lavorazione> visualizzaPianificazioneByMacchina(String idMacchina) {
		if(pianificazioniDataManager.getPianificazioneInLavorazione() == null)
			return new ArrayList<Lavorazione>();
		else if(pianificazioniDataManager.getPianificazioneInLavorazione().getPianificazioneByMacchina(idMacchina) == null)
			return new ArrayList<Lavorazione>();
		else 
			return pianificazioniDataManager.getPianificazioneInLavorazione().getPianificazioneByMacchina(idMacchina);
		
	}

	public List<Lotto> getLottiResiduiPianificazioneCorrente() {
		return pianificazioniDataManager.getPianificazioneCorrente().getLottiResidui();
	}
}
