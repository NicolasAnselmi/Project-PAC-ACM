package com.models.data;

import java.util.ArrayList;
import java.util.List;

import com.google.cloud.Timestamp;
import com.models.scheduler.Scheduler;

public class Pianificazione implements Comparable<Pianificazione> {

	private List<Lavorazione> listaLavorazioni;
	private List<Lotto> listaLotti;
	private boolean confermata;
	private String timeStampInizioPeriodo;
	private String timeStampFinePeriodo;

	public Pianificazione(String timeStampInizioPeriodo, String timeStampFinePeriodo) {
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

	public List<Lotto> calcoloPianificazione() {
		Scheduler pippo = new Scheduler();
		pippo.setLottiDaSchedulare(listaLotti);
		listaLavorazioni = pippo.getScheule();
		List<Lotto> residui = pippo.getLottiResidui();
		listaLotti.removeIf(x-> residui.contains(x));
		return residui;
	}

	public boolean cancellaLotto(String idLotto) {
		return listaLotti.removeIf(x -> x.getIdLotto().equals(idLotto));
	}

	public boolean updateLotto(String idLotto, int nPezzi, float tempoLavorazionePezzoFresa,
			float tempoLavorazionePezzoTornio, String priorita) {
		Lotto l = listaLotti.stream().filter(x -> x.getIdLotto().equals(idLotto)).findFirst().get();
		if (l == null)
			return false;
		l.setnPezzi(nPezzi);
		l.setTempoLavorazionePezzoFresa(tempoLavorazionePezzoFresa);
		l.setTempoLavorazionePezzoTornio(tempoLavorazionePezzoTornio);
		l.setPriorita(PrioritaLotto.valueOf(priorita));
		return true;
	}

	public List<Lavorazione> getPianificazioneByMacchina(String idMacchina) {
		if (listaLavorazioni == null)
			return null;
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

	@Override
	public int compareTo(Pianificazione o) {
		return Timestamp.parseTimestamp(this.timeStampInizioPeriodo)
				.compareTo(Timestamp.parseTimestamp(o.getTimeStampInizioPeriodo()));

	}

	public void setListaLotti(List<Lotto> listaLotti) {
		this.listaLotti = listaLotti;
	}

}
