package com.models.data;

import com.models.macchine.TipoMacchina;

public class Lotto {

	private String idLotto;
	private String idProdotto;
	private int nPezzi;
	private PrioritaLotto priorita;
	private TipoMacchina listaLavorazioni[];

	public Lotto(String idLotto, String idProdotto, int nPezzi, String priorita, float tempoLavorazionePezzoTornio, float tempoLavorazionePezzoFresa, TipoMacchina[] listaLavorazioni) {
		this.idLotto = idLotto;
		this.idProdotto = idProdotto;
		this.nPezzi = nPezzi;
		this.priorita = PrioritaLotto.valueOf(priorita);
		this.listaLavorazioni = listaLavorazioni;
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
	
	public TipoMacchina[] getListaLavorazioni() {
		return listaLavorazioni;
	}
	

}
