package com.macchine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import com.models.macchine.Macchina;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.accessori.Lavorazione;

public class MacchinaFisica implements Machinable {
	protected float probGuasto;
	protected float probFineMateriali;

	protected String IDMacchina;
	protected StatoMacchina statoMacchina;
	protected ArrayDeque<Lavorazione> codaLavorazioni;

	private RestTemplate restTemplate;

	public MacchinaFisica(float probGuasto, float probFineMateriali, String IDMacchina, RestTemplate restTemplate) {
		this.probFineMateriali = probFineMateriali;
		this.probGuasto = probGuasto;
		this.IDMacchina = IDMacchina;
		this.restTemplate = restTemplate;
	}

	@Override
	public void caricaSuServer() {

		// TODO Auto-generated method stub

		// calcolo time stamp
		// id macchina è fisso
		// stato macchina è la variabile
		// ID lotto lo prende dalla lavorazione corrente
		// pusha tutto sul server con api
	}

	@Override
	public void inizializzaMacchina() {

		/* Aggiungo la macchina al server */
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("idMacchina", IDMacchina);
		restTemplate.postForObject("http://localhost:8081/macchine/aggiungi", map, Macchina.class);

		// TODO scarica lista lavorazioni con api
	}

	@Override
	public void aggiornaMacchina() {
		// TODO Auto-generated method stub

	}

}
