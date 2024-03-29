package com.macchine;

import java.util.List;
import java.util.concurrent.Semaphore;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.MocClientApplication;
import com.models.lotto.Lavorazione;
import com.models.macchine.TipoMacchina;

import java.time.LocalDateTime;

public class MacchinaFisica extends Thread implements Machinable {

	private String ip = MocClientApplication.IP;
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
	protected List<Lavorazione> codaLavorazioni;
	protected Lavorazione inCorso;
	private Semaphore s;
	private boolean finito;

	protected RestTemplate restTemplate;

	public MacchinaFisica(float probGuasto, float probFineMateriali, String IDMacchina, String tipoMacchina,
			int waitTime, RestTemplate restTemplate, Semaphore s) {
		this.s = s;
		this.probFineMateriali = probFineMateriali;
		this.probGuasto = probGuasto;
		this.IDMacchina = IDMacchina;
		this.tipoMacchina = TipoMacchina.valueOf(tipoMacchina);
		this.statoMacchina = StatoMacchina.Fermo;
		this.restTemplate = restTemplate;
		this.countSlotPart = 0;
		this.waitTimeRiparazione = 0;
		this.waitTimeMateriale = 0;
		this.maxWaitTime = waitTime;
		this.finito = true;
	}

	@Override
	public void run() {

		while (true) {
			while (codaLavorazioni != null && !codaLavorazioni.isEmpty()) {
				aggiornaMacchina();
				if (codaLavorazioni.isEmpty()) {
					System.out.println(IDMacchina + ": finito");
					this.statoMacchina = StatoMacchina.Fermo;
					this.finito = true;
				}
				caricaSuServer();
			}
		}

	}

	/**
	 * metodo che aggiorna lo stato della macchina sul server, aggiungendo un log
	 */
	@Override
	public void caricaSuServer() {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		MultiValueMap<String, String> map2 = new LinkedMultiValueMap<String, String>();
		map.add("idLog", getIDMacchina() + "--" + LocalDateTime.now());
		map.add("idLogger", getIDMacchina());
		map.add("title", "titolo log");
		map.add("body", "body log");
		map.add("statoMacchina", statoMacchina.toString());
		map.add("codiceLotto", inCorso == null ? "" : inCorso.getIdLotto());

		map2.add("idMacchina", IDMacchina);
		map2.add("codiceLotto", inCorso == null ? "" : inCorso.getIdLotto());
		map2.add("statoMacchina", statoMacchina.toString());

		try {
			s.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		restTemplate.postForObject("http://" + ip + ":8081/log/addLog", map, Object.class);
		restTemplate.postForObject("http://" + ip + ":8081/macchine/update", map2, boolean.class);
		s.release();

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
			System.out.println(IDMacchina + " in lavorazione: " + codaLavorazioni.get(0));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			countSlotPart++;
			inCorso = codaLavorazioni.remove(0);
			double fm = Math.random();
			if (fm > probFineMateriali)
				statoMacchina = StatoMacchina.AttesaMateriale;

			g = Math.random();
			if (g > probGuasto)
				statoMacchina = StatoMacchina.Guasta;

			break;

		case AttesaMateriale:
			System.out.println(IDMacchina + " attende materiali");
			waitTimeMateriale++;
			if (waitTimeMateriale >= maxWaitTime) {
				waitTimeMateriale = 0;
				statoMacchina = StatoMacchina.Lavorazione;
				System.out.println(IDMacchina + " ha ricevuto i materiali");
				countSlotPart--;
			}
			break;

		case Guasta:
			System.out.println(IDMacchina + " guasta.");
			waitTimeRiparazione++;
			if (waitTimeRiparazione >= maxWaitTime) {
				waitTimeRiparazione = 0;
				statoMacchina = StatoMacchina.Lavorazione;
				System.out.println(IDMacchina + " aggiustata;");
				countSlotPart--;
			}
			break;

		case Fermo:
			System.out.println(IDMacchina + " ferma.");
			break;

		default:
			break;
		}

	}

	@Override
	public String getStato() {
		return getIDMacchina() + ": stato " + statoMacchina + ", "
				+ (statoMacchina.equals(StatoMacchina.Fermo) ? "" : inCorso.toString());
	}

	public String getIDMacchina() {
		return IDMacchina;
	}

	public TipoMacchina getTipoMacchina() {
		return tipoMacchina;
	}

	public void setListaLavorazioni(List<Lavorazione> codaLavorazioni) {
		this.codaLavorazioni = codaLavorazioni;
		if (!codaLavorazioni.isEmpty()) {
			this.maxSlotPart = getMaxSlot(codaLavorazioni);
			this.statoMacchina = StatoMacchina.Lavorazione;
			this.finito = false;
		} else {
			this.statoMacchina = StatoMacchina.Fermo;
			this.finito = true;
		}
	}

	private int getMaxSlot(List<Lavorazione> codaLavorazioni) {
		int max = -1;
		for (int i = 0; i < codaLavorazioni.size(); i++) {
			if (codaLavorazioni.get(i).getSlot() > max)
				max = codaLavorazioni.get(i).getSlot();
		}
		return max;
	}

	public boolean getFinito() {
		return finito;
	}

}
