package com.models.pianificazione;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.models.lotto.Lavorazione;
import com.models.lotto.Lotto;

@RestController
public class PianificazioneWebController {

	@Autowired
	private ServicePianificazione servicePianificazione;

	@PostMapping("/pianificazione/lotto/aggiungi")
	public void inserisciLotto(@RequestParam String idLotto, @RequestParam String idProdotto, @RequestParam int nPezzi,
			@RequestParam String priorita, @RequestParam String sequenzaLavorazioni) {

		servicePianificazione.inserisciLotto(idLotto, idProdotto, nPezzi, priorita, getToken(sequenzaLavorazioni));
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
	public boolean updateLotto(@RequestParam String idLotto, @RequestParam String idProdotto, @RequestParam int nPezzi,
			@RequestParam String priorita, @RequestParam String sequenzaLavorazioni) {
		return servicePianificazione.updateLotto(idLotto, idProdotto, nPezzi, priorita, getToken(sequenzaLavorazioni));
	}

	@GetMapping("/pianificazione/idMacchina/{idMacchina}")
	public List<Lavorazione> getPianificazioneByMacchina(@PathVariable String idMacchina) {
		return servicePianificazione.visualizzaPianificazioneByMacchina(idMacchina);
	}

	@GetMapping("/pianificazione/calcolo")
	public List<Lavorazione> calcoloPianificazione(@RequestParam String listaMacchine,
			@RequestParam int slotSettimanali) {
		return servicePianificazione.calcoloPianificazione(getToken(listaMacchine), slotSettimanali);
	}

	@GetMapping("/pianificazione/residui")
	public List<Lotto> getLottiResiduiPianificazioneCorrente() {
		return servicePianificazione.getLottiResiduiPianificazioneCorrente();
	}
	
	@PostMapping("/pianificazione/conferma")
	public void confermaPianificazione() {
		servicePianificazione.confermaPianificazione();
	}

	private String[] getToken(String sequenzaLavorazioni) {
		StringTokenizer tokenizer = new StringTokenizer(sequenzaLavorazioni, ",");
		String[] token = new String[tokenizer.countTokens()];
		for (int i = 0; i < token.length; i++) {
			token[i] = tokenizer.nextToken();
		}

		return token;
	}

}
