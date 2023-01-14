package com.models.pianificazione;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.datamanager.MacchineDataManager;
import com.google.cloud.Timestamp;
import com.models.data.Lavorazione;
import com.models.data.Lotto;
import com.models.data.PrioritaLotto;
import com.models.macchine.Macchina;

public class Pianificazione implements Comparable<Pianificazione> {
	
	private String idPianificazione;
	private List<Lavorazione> listaLavorazioni;
	private List<Lotto> listaLotti;
	private boolean confermata;
	private String timeStampInizioPeriodo;
	private String timeStampFinePeriodo;

	public Pianificazione(String timeStampInizioPeriodo, String timeStampFinePeriodo) {
		this.idPianificazione = timeStampInizioPeriodo + "-" + timeStampFinePeriodo;
		listaLavorazioni = null;
		listaLotti = new ArrayList<Lotto>();
		this.confermata = false;
		this.timeStampInizioPeriodo = timeStampInizioPeriodo;
		this.timeStampFinePeriodo = timeStampFinePeriodo;
	}

	public void inserisciLotto(Lotto lotto) {
		listaLotti.add(lotto);
	}

	public void confermaPianificazione() {
		this.confermata = true;
	}

	public List<Lavorazione> calcoloPianificazione(List<Lotto> listaLotti, List<Macchina> listaMacchine, int slotSettimanali) {
		SchedulerInterface scheduler = Scheduler.getScheduler(slotSettimanali);
		listaLavorazioni = scheduler.getSchedule(listaLotti, listaMacchine);
		listaLotti = scheduler.getLottiResidui();
		return listaLavorazioni;
	}

	public boolean cancellaLotto(String idLotto) {
		return listaLotti.removeIf(x -> x.getIdLotto().equals(idLotto));
	}

	public boolean updateLotto(String idLotto, String idProdotto, int nPezzi, String priorita, String[] listaLavorazioni) {
		Optional<Lotto> opt = listaLotti.stream().filter(x -> x.getIdLotto().equals(idLotto)).findFirst();
		if (opt.isEmpty())
			return false;
		else {
			Lotto l = opt.get();
			l.setIdProdotto(idProdotto);
			l.setnPezzi(nPezzi);
			l.setPriorita(PrioritaLotto.valueOf(priorita));
			l.setListaLavorazioni(listaLavorazioni);
			return true;
		}
	}

	public List<Lavorazione> getPianificazioneByMacchina(String idMacchina) {
		if (listaLavorazioni == null)
			return null;
		Collections.sort(listaLavorazioni);
		return listaLavorazioni.stream().filter(x -> x.getIdMacchina().equals(idMacchina)).toList();
	}

	public List<Lavorazione> getPianificazione() {
		return listaLavorazioni;
	}

	public List<Lotto> getListaLotti() {
		return listaLotti;
	}

	public String getTimeStampInizioPeriodo() {
		return timeStampInizioPeriodo;
	}

	public String getTimeStampFinePeriodo() {
		return timeStampFinePeriodo;
	}
	
	public String getIdPianificazione() {
		return this.idPianificazione;
	}

	@Override
	public int compareTo(Pianificazione o) {
		return Timestamp.parseTimestamp(this.timeStampInizioPeriodo)
				.compareTo(Timestamp.parseTimestamp(o.getTimeStampInizioPeriodo()));

	}

	public void setListaLotti(List<Lotto> listaLotti) {
		this.listaLotti = listaLotti;
	}

	public List<Lotto> getLottiResidui() {
		List<Lotto> residui = Scheduler.getScheduler(0).getLottiResidui();
		if(residui == null || residui.isEmpty())
			return this.listaLotti;
		return residui;
	}

}
