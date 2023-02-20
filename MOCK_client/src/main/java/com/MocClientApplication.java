package com;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.macchine.Fresa;
import com.macchine.MacchinaFisica;
import com.macchine.Tornio;

import lotto.*;


@SpringBootApplication
public class MocClientApplication {

	public static String IP = null;
	static float pg = 0.9f;
	static float pf = 0.9f;
	static float nTorni = 1;
	static float nFrese = 1;
	static int quant = 1;
	static int slotPart = 5;
	static int waitTime = 3;
	static Semaphore s = new Semaphore(1);;

	public static void main(String[] args) {
		IP = args[0];
		System.out.println(IP);
		SpringApplication.run(MocClientApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	CommandLineRunner run(RestTemplate restTemplate) {
		return args -> {
			List<MacchinaFisica> macchine = inizializzaMacchine(restTemplate);
			avviaMacchine(macchine, restTemplate);

			while (true) {
				if (therIsLavorazioni(restTemplate, macchine) && lavorazioniFinite(macchine)) {
					Thread.sleep(5000);
					assegnaLavorazioni(macchine, restTemplate);
					while(!lavorazioniFinite(macchine)) {
						Thread.sleep(1000);
					}
					if (lavorazioniFinite(macchine))
						postLavorazioniEseguite(macchine, restTemplate);
				}

				Thread.sleep(1000);
			}
		};
	}

	private void assegnaLavorazioni(List<MacchinaFisica> macchine, RestTemplate restTemplate) {
		for (MacchinaFisica m : macchine) {
			List<Lavorazione> list = getCodaLavorazioni(restTemplate, m.getIDMacchina());
			if (list != null && !list.isEmpty())
				m.setListaLavorazioni(list);
		}
		s.release();
	}

	private boolean lavorazioniFinite(List<MacchinaFisica> macchine) {
		boolean res = true;
		for (MacchinaFisica m : macchine) {
			res = res && m.getFinito();
		}
		return res;
	}

	private void postLavorazioniEseguite(List<MacchinaFisica> macchine, RestTemplate restTemplate) {
		// deleteMacchine(macchine,restTemplate, s);
		try {
			s.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		restTemplate.postForEntity("http://" + IP + ":8081/pianificazione/conferma", null, null);
		s.release();
	}

	
	private void avviaMacchine(List<MacchinaFisica> macchine, RestTemplate restTemplate) {
		for (MacchinaFisica m : macchine) {
			m.setListaLavorazioni(getCodaLavorazioni(restTemplate, m.getIDMacchina()));
			m.start();
			System.out.println(m.getIDMacchina() + " running...");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private boolean therIsLavorazioni(RestTemplate restTemplate, List<MacchinaFisica> macchine) {
		for (MacchinaFisica m : macchine) {
			List<Lavorazione> list = getCodaLavorazioni(restTemplate, m.getIDMacchina());
			if (list != null && !list.isEmpty())
				return true;
		}
		return false;
	}

	private List<MacchinaFisica> inizializzaMacchine(RestTemplate restTemplate) {
		List<MacchinaFisica> macchine = new ArrayList<MacchinaFisica>();
		for (int i = 0; i < nTorni; i++)
			macchine.add(new Tornio(pg, pf, waitTime, restTemplate,s));

		for (int i = 0; i < nFrese; i++)
			macchine.add(new Fresa(pg, pf, waitTime, restTemplate,s));

		for (MacchinaFisica m : macchine) {
			postMacchinaSulServer(m, restTemplate);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return macchine;
	}

	private void postMacchinaSulServer(MacchinaFisica m, RestTemplate restTemplate) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("idMacchina", m.getIDMacchina());
		map.add("tipoMacchina", m.getTipoMacchina().toString());

		try {
			s.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		restTemplate.postForObject("http://" + IP + ":8081/macchine/aggiungi", map, Object.class);

		s.release();

	}

	public static <T> List<T> getObjectList(final String json, final Class<T> cls)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json,
				objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, cls));
	}

	private ArrayList<Lavorazione> getCodaLavorazioni(RestTemplate restTemplate, String IDMacchina) {
		try {
			s.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String json = restTemplate.getForObject("http://" + IP + ":8081/pianificazione/idMacchina/" + IDMacchina,
				String.class);
		s.release();
		ArrayList<Lavorazione> list = null;
		if (json != null) {
			try {
				list = (ArrayList<Lavorazione>) getObjectList(json, Lavorazione.class);
				if (list.isEmpty()) {
					s.release();
				}
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

}
