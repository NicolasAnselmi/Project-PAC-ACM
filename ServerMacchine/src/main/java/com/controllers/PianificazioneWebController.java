package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.models.data.Lavorazione;
import com.models.data.Lotto;
import com.services.ServicePianificazione;

@RestController
public class PianificazioneWebController {

	@Autowired
	private ServicePianificazione servicePianificazione;

	@PostMapping("/pianificazione/lotto/aggiungi")
	public void inserisciLotto(@RequestParam String idLotto, @RequestParam String idProdotto, @RequestParam int nPezzi,
			@RequestParam String priorita, @RequestParam String[] sequenzaLavorazioni) {
		servicePianificazione.inserisciLotto(idLotto, idProdotto, nPezzi, priorita, sequenzaLavorazioni);
	}

	@DeleteMapping("/pianificazione/lotto/elimina")
	public boolean cancellaLotto(@RequestParam String idLotto) {
		return servicePianificazione.cancellaLotto(idLotto);
	}

	@GetMapping("/pianificazione/lotto")
	public List<Lotto> visualizzaLottiPianificazioneCorrente() {
		return servicePianificazione.visualizzaLottiPianificazioneCorrente();
	}

	@PostMapping("/pianificazione/lotto")
	public boolean updateLotto(@RequestParam String idLotto, @RequestParam int nPezzi,
			@RequestParam float tempoLavorazionePezzoFresa, @RequestParam float tempoLavorazionePezzoTornio,
			@RequestParam String priorita) {
		return servicePianificazione.updateLotto(idLotto, nPezzi, tempoLavorazionePezzoFresa,
				tempoLavorazionePezzoTornio, priorita);
	}
	
	@GetMapping("/pianificazione/idMacchina/{idMacchina}")
	public List<Lavorazione> getPianificazioneByMacchina(@PathVariable String idMacchina){
		return servicePianificazione.visualizzaPianificazioneByMacchina(idMacchina);
	}
	
	@GetMapping("/pianificazione/calcolo")
	public List<Lavorazione> calcoloPianificazione(@RequestParam String[] listaMacchine, int slotSettimanali){
		return servicePianificazione.calcoloPianificazione(listaMacchine, slotSettimanali);
	}

}
