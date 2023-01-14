package com.models.data;

import java.util.ArrayList;
import java.util.List;

import com.models.macchine.TipoMacchina;

public class Lotto implements Comparable<Lotto>{

	private String idLotto;
	private String idProdotto;
	private int nPezzi;
	private PrioritaLotto priorita;
	private List<TipoMacchina> sequenzaLavorazioni;

	public Lotto(String idLotto, String idProdotto, int nPezzi, String priorita, String[] sequenzaLavorazioni) {
		this.idLotto = idLotto;
		this.idProdotto = idProdotto;
		this.nPezzi = nPezzi;
		this.priorita = PrioritaLotto.valueOf(priorita);
		this.sequenzaLavorazioni = new ArrayList<TipoMacchina>();
		for (String s : sequenzaLavorazioni) {
			this.sequenzaLavorazioni.add(TipoMacchina.valueOf(s));
		}
	}
	
	public Lotto(String idLotto, String idProdotto, int nPezzi, PrioritaLotto priorita, List<TipoMacchina> listaLavorazioni) {
		this.idLotto = idLotto;
		this.idProdotto = idProdotto;
		this.nPezzi = nPezzi;
		this.priorita = priorita;
		this.sequenzaLavorazioni = listaLavorazioni;
	}

	
	public String getIdLotto() {
		return idLotto;
	}

	public String getIdProdotto() {
		return idProdotto;
	}

	public int getnPezzi() {
		return nPezzi;
	}

	public PrioritaLotto getPriorita() {
		return priorita;
	}

	public void setnPezzi(int nPezzi) {
		this.nPezzi = nPezzi;
	}


	public void setPriorita(PrioritaLotto priorita) {
		this.priorita = priorita;
	}
	
	public List<TipoMacchina> getListaLavorazioni() {
		return sequenzaLavorazioni;
	}


	@Override
	public int compareTo(Lotto o) {
		Lotto that = (Lotto)o;
		return priorita.ordinal()*1000 - sequenzaLavorazioni.size() - (that.getPriorita().ordinal()*1000 - that.getListaLavorazioni().size());
	}
	
	@Override
	public String toString() {
		return idLotto + " - " + priorita + " - " + sequenzaLavorazioni.toString();
	}

	public void setListaLavorazioni(String[] listaLavorazioni) {
		for (String s : listaLavorazioni) {
			sequenzaLavorazioni = new ArrayList<>();
			this.sequenzaLavorazioni.add(TipoMacchina.valueOf(s));
		}

	}
	

}
