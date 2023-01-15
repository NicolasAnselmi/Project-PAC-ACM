package com.macchine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import com.models.macchine.Macchina;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.models.data.Lotto;
import com.models.data.Lavorazione;
import com.models.data.PrioritaLotto;

public class MacchinaFisica implements Machinable {
	protected float probGuasto;
	protected float probFineMateriali;
	protected int countSlotPart;
	protected int maxSlotPart;

	protected String IDMacchina;
	protected StatoMacchina statoMacchina;
	protected ArrayList<Lavorazione> codaLavorazioni;
	protected Lavorazione inCorso;

	private RestTemplate restTemplate;

	public MacchinaFisica(float probGuasto, float probFineMateriali, String IDMacchina, RestTemplate restTemplate, int maxSlotPart) {
		this.probFineMateriali = probFineMateriali;
		this.probGuasto = probGuasto;
		this.IDMacchina = IDMacchina;
		this.restTemplate = restTemplate;
		this.countSlotPart = maxSlotPart;
		this.maxSlotPart = maxSlotPart;
	}

	@Override
	public void caricaSuServer() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("idLog", IDMacchina + 1);
		map.add("idLogger", IDMacchina);
		map.add("title", "titolo log");
		map.add("body", "body log");
		map.add("statoMacchina", statoMacchina.toString());
		map.add("codiceLotto", inCorso.getLotto());
		restTemplate.postForObject("http://localhost:8081/log/addLog", map, Object.class);
	}

	@Override
	public void inizializzaMacchina() {

		/* Aggiungo la macchina al server */
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("idMacchina", IDMacchina);
		restTemplate.postForObject("http://localhost:8081/macchine/aggiungi", map, Macchina.class);

		codaLavorazioni = restTemplate.getForObject("http://localhost:8081/pianificazione/idMacchina/" + IDMacchina, ArrayList.class);
	}

	@Override
	public void aggiornaMacchina() {
		
		if(statoMacchina.equals(StatoMacchina.Lavorazione))
		{
			countSlotPart++;
		
			if(countSlotPart >= maxSlotPart) {
				countSlotPart = 0;
				inCorso = codaLavorazioni.remove(0);
			}
		
			double fm = Math.random();
			if(fm > probFineMateriali)
				statoMacchina = StatoMacchina.AttesaMateriale;
		
			double g = Math.random();
			if(g > probGuasto)
				statoMacchina = StatoMacchina.Guasta;
			
			caricaSuServer();
		}
	}

}
