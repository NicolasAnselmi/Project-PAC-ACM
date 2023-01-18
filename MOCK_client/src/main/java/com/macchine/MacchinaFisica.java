package com.macchine;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.data.Lavorazione;
import com.models.macchine.Macchina;
import com.models.macchine.TipoMacchina;

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
	protected TipoMacchina tipoMacchina;
	protected StatoMacchina statoMacchina;
	protected ArrayList<Lavorazione> codaLavorazioni;
	protected Lavorazione inCorso;

	protected RestTemplate restTemplate;

	public MacchinaFisica(float probGuasto, float probFineMateriali, String IDMacchina, String tipoMacchina,
			RestTemplate restTemplate, int maxSlotPart, int maxWaitTime) {
		this.probFineMateriali = probFineMateriali;
		this.probGuasto = probGuasto;
		this.IDMacchina = IDMacchina;
		this.tipoMacchina = TipoMacchina.valueOf(tipoMacchina);
		this.statoMacchina = StatoMacchina.Fermo;
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
		map.add("codiceLotto", inCorso == null ? "" : inCorso.getLotto());
		restTemplate.postForObject("http://localhost:8081/log/addLog", map, Object.class);
	}

	/**
	 * metodo che registra la macchina sul server e scarica la coda delle
	 * lavorazioni
	 */
	@Override
	public void inizializzaMacchina() {

		/* Aggiungo la macchina al server */
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("idMacchina", IDMacchina);
		map.add("tipoMacchina", tipoMacchina.toString());
		restTemplate.postForObject("http://localhost:8081/macchine/aggiungi", map, Macchina.class);

		codaLavorazioni = restTemplate.getForObject("http://localhost:8081/pianificazione/idMacchina/" + IDMacchina,
				ArrayList.class);
		if(codaLavorazioni != null)
			this.maxSlotPart = codaLavorazioni.size();
	}

	/**
	 * Metodo che simula il cambiamento di stati della macchina e che gestisce
	 * l'avanzamento delle lavorazioni
	 */
	
	@Override
	public void aggiornaMacchina() {
		double g;
		switch (statoMacchina) {
		case Lavorazione:
			countSlotPart++;

			if (countSlotPart >= maxSlotPart || codaLavorazioni == null || codaLavorazioni.isEmpty()) {
				countSlotPart = 0;
				statoMacchina = StatoMacchina.Fermo;
				break;
			}
			
			if(codaLavorazioni instanceof ArrayList<Lavorazione>)
				codaLavorazioni = (ArrayList<Lavorazione>) codaLavorazioni;
			else
				System.out.println("noon ok");
			
			inCorso = (Lavorazione)codaLavorazioni.get(0);

			double fm = Math.random();
			if (fm > probFineMateriali)
				statoMacchina = StatoMacchina.AttesaMateriale;

			g = Math.random();
			if (g > probGuasto)
				statoMacchina = StatoMacchina.Guasta;

			break;

		case AttesaMateriale:
			waitTimeMateriale++;
			if (waitTimeMateriale >= maxWaitTime) {
				waitTimeMateriale = 0;
				statoMacchina = StatoMacchina.Lavorazione;
			}
			break;

		case Guasta:
			waitTimeRiparazione++;
			if (waitTimeRiparazione >= maxWaitTime) {
				waitTimeRiparazione = 0;
				statoMacchina = StatoMacchina.Lavorazione;
			}
			break;

		case Fermo:
			String json = restTemplate. getForObject("http://localhost:8081/pianificazione/idMacchina/" + IDMacchina,
					String.class);
			if(json != null) {
				System.out.println(json);
				try {
					codaLavorazioni = (ArrayList<Lavorazione>) getObjectList(json, Lavorazione.class);
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (codaLavorazioni != null && !codaLavorazioni.isEmpty()) {
				statoMacchina = StatoMacchina.Lavorazione;
				maxSlotPart = codaLavorazioni.size();
			}
			break;

		default:
			break;
		}

	}

	@Override
	public String getStato() {
		return IDMacchina + ": stato " + statoMacchina + " "
				+ (statoMacchina.equals(StatoMacchina.Fermo) || this.inCorso == null ? "" : inCorso.toString());
	}
	
	public static <T> List<T> getObjectList(final String json, final Class<T> cls) throws JsonMappingException, JsonProcessingException 
	{
		ObjectMapper objectMapper = new ObjectMapper();
	    return objectMapper
	        .readValue(json, objectMapper.getTypeFactory()
	        .constructCollectionType(ArrayList.class, cls));
	}

}
