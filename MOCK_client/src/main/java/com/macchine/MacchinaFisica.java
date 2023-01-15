package com.macchine;

import java.util.ArrayList;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.models.data.Lavorazione;
import com.models.macchine.Macchina;
import java.time.LocalDateTime;

public class MacchinaFisica implements Machinable {
	protected float probGuasto;
	protected float probFineMateriali;
	protected int countSlotPart;
	protected int maxSlotPart;
	protected int waitTimeRiparazione;
	protected int waitTimeMateriale;
	protected int maxWaitTime;

	protected String IDMacchina;
	protected StatoMacchina statoMacchina;
	protected ArrayList<Lavorazione> codaLavorazioni;
	protected Lavorazione inCorso;

	protected RestTemplate restTemplate;

	public MacchinaFisica(float probGuasto, float probFineMateriali, String IDMacchina, RestTemplate restTemplate, int maxSlotPart, int maxWaitTime) {
		this.probFineMateriali = probFineMateriali;
		this.probGuasto = probGuasto;
		this.IDMacchina = IDMacchina;
		this.restTemplate = restTemplate;
		this.countSlotPart = maxSlotPart;
		this.maxSlotPart = maxSlotPart;
		this.waitTimeRiparazione = 0;
		this.waitTimeMateriale = 0;
		this.maxWaitTime = maxWaitTime;
	}

	/**
	 * metodo che aggiorna lo stato della macchina sul server, aggiungendo un log
	 */
	@Override
	public void caricaSuServer() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("idLog", IDMacchina + "--" + LocalDateTime.now());
		map.add("idLogger", IDMacchina);
		map.add("title", "titolo log");
		map.add("body", "body log");
		map.add("statoMacchina", statoMacchina.toString());
		map.add("codiceLotto", inCorso.getLotto());
		restTemplate.postForObject("http://localhost:8081/log/addLog", map, Object.class);
	}

	/**
	 * metodo che registra la macchina sul server e scarica la coda delle lavorazioni
	 */
	@Override
	public void inizializzaMacchina() {

		/* Aggiungo la macchina al server */
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("idMacchina", IDMacchina);
		restTemplate.postForObject("http://localhost:8081/macchine/aggiungi", map, Macchina.class);

		codaLavorazioni = restTemplate.getForObject("http://localhost:8081/pianificazione/idMacchina/" + IDMacchina, ArrayList.class);
	}
	
	/**
	 * Metodo che simula il cambiamento di stati della macchina e che gestisce
	 * l'avanzamento delle lavorazioni
	 */
	@Override
	public void aggiornaMacchina() {
		
		if(statoMacchina.equals(StatoMacchina.Lavorazione)) 
		{
			countSlotPart++;
		
			if(countSlotPart >= maxSlotPart) {
				countSlotPart = 0;
				if(!codaLavorazioni.isEmpty())
					inCorso = codaLavorazioni.remove(0);
				else statoMacchina = StatoMacchina.Fermo;
			}
		
			double fm = Math.random();
			if(fm > probFineMateriali)
				statoMacchina = StatoMacchina.AttesaMateriale;
		
			double g = Math.random();
			if(g > probGuasto)
				statoMacchina = StatoMacchina.Guasta;
		}
		else if (statoMacchina.equals(StatoMacchina.AttesaMateriale))
		{
			waitTimeMateriale++;
			if(waitTimeMateriale >= maxWaitTime) {
				waitTimeMateriale = 0;
				statoMacchina = StatoMacchina.Lavorazione;
			};
			
			double g = Math.random();
			if(g > probGuasto)
			{
				statoMacchina = StatoMacchina.Guasta;
				waitTimeMateriale = 0;
			}
		}
		else if (statoMacchina.equals(StatoMacchina.Guasta))
		{
			waitTimeRiparazione++;
			if(waitTimeRiparazione >= maxWaitTime)
			{
				waitTimeRiparazione = 0;
				statoMacchina = StatoMacchina.Lavorazione;
			}
		}

	}

}
